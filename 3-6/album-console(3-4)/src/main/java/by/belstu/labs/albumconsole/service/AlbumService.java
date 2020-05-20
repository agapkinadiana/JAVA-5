package by.belstu.labs.albumconsole.service;

import by.belstu.labs.albumconsole.repository.AlbumRepository;
import by.belstu.labs.albumconsole.repository.ArtistRepository;
import by.belstu.labs.albumconsole.dto.AddAlbumDto;
import by.belstu.labs.albumconsole.entity.Album;
import by.belstu.labs.albumconsole.entity.Artist;
import by.belstu.labs.albumconsole.exception.AlbumNotFoundException;
import by.belstu.labs.albumconsole.exception.ArtistNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {

    private AlbumRepository albumRepository;

    private ArtistRepository artistRepository;

    private Logger logger = LoggerFactory.getLogger(AlbumService.class);

    public AlbumService(AlbumRepository albumRepository, ArtistRepository artistRepository) {
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
    }

    public List<Album> findAllAlbums() {
        return albumRepository.findAll();
    }

    public Album findAlbum(long albumId) {
        return albumRepository.findById(albumId).orElseThrow(() -> new AlbumNotFoundException("Album not found"));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void saveNewAlbum(AddAlbumDto albumDto) {
        logger.info("Saving new album {}", albumDto);
        Album album = new Album();
        Optional<Artist> artistOptional = artistRepository.findArtistByName(albumDto.getArtist());
        album.setArtist(artistOptional.orElseThrow(() -> new ArtistNotFoundException("Artist not found")));
        album.setTitle(albumDto.getTitle());
        album.setDescription(albumDto.getDescription());
        album.setReleaseYear(albumDto.getReleaseYear());

        albumRepository.save(album);
        logger.info("New album has been saved. Generated id: {}", album.getId());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void updateAlbum(Album album) {
        albumRepository.save(album);
        logger.info("Album {} has been updated", album.getId());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteAlbum(long id) {
        albumRepository.deleteById(id);
        logger.info("Album {} has been deleted", id);
    }
}
