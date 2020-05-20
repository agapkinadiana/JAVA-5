package by.belstu.labs.albumconsole.dto;

import by.belstu.labs.albumconsole.validator.annotation.ArtistExists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.Year;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddAlbumDto {

    @NotNull(message = "{validation.album.title.null}")
    @Size(max = 32, message = "{validation.album.title.size}")
    private String title;

    @NotNull(message = "{validation.album.artist.null}")
    @ArtistExists(message = "{validation.album.artist.exists}")
    private String artist;

    @NotNull(message = "{validation.album.year.null}")
    @PastOrPresent(message = "{validation.album.year.past.present}")
    private Year releaseYear;

    @Size(max = 200, message = "{validation.album.description.size}")
    private String description;
}
