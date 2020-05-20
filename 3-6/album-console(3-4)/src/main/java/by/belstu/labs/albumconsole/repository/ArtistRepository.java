package by.belstu.labs.albumconsole.repository;

import by.belstu.labs.albumconsole.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

    Optional<Artist> findArtistByName(String name);
}
