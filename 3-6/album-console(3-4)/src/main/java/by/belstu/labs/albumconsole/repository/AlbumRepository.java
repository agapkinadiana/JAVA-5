package by.belstu.labs.albumconsole.repository;

import by.belstu.labs.albumconsole.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}
