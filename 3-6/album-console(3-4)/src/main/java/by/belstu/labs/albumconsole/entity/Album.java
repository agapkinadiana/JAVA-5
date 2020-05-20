package by.belstu.labs.albumconsole.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.Year;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "albums")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "{validation.album.title.null}")
    @Size(max = 32, message = "{validation.album.title.size}")
    private String title;

    @Size(max = 200, message = "{validation.album.description.size}")
    private String description;

    @NotNull(message = "{validation.album.release-year.null}")
    @PastOrPresent
    private Year releaseYear;

    @ManyToOne(optional = false)
    @JoinColumn(name = "artistId")
    @NotNull
    private Artist artist;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}