package by.belstu.labs.albumconsole.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class EditAlbumDto {

    private long id;

    @NotNull(message = "{validation.album.title.null}")
    @Size(max = 32, message = "{validation.album.title.size}")
    private String title;

    @Size(max = 200, message = "{validation.album.description.size}")
    private String description;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public EditAlbumDto (Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
}
