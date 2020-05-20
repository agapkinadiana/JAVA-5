package by.belstu.labs.albumconsole.controller;

import by.belstu.labs.albumconsole.dto.EditAlbumDto;
import by.belstu.labs.albumconsole.entity.Album;
import by.belstu.labs.albumconsole.service.AlbumService;
import by.belstu.labs.albumconsole.dto.AddAlbumDto;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/albums")
public class AlbumController {

    private AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @InitBinder
    public void allowEmptyDateBinding(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping
    public ModelAndView showAllAlbums() {
        List<Album> albums = albumService.findAllAlbums();
        return new ModelAndView("albums", "albums", albums);
    }

    @GetMapping("/create")
    public ModelAndView showAlbumForm() {
        return new ModelAndView("album-add-form", "albumDto", new AddAlbumDto());
    }

    @PostMapping
    public ModelAndView createAlbum(@Valid @ModelAttribute("albumDto") AddAlbumDto albumDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("album-add-form", "albumDto", albumDto);
        }

        albumService.saveNewAlbum(albumDto);

        return new ModelAndView("redirect:/albums");
    }

    @GetMapping("/{albumId}/edit")
    public ModelAndView showEditForm(@PathVariable long albumId) {
        Album album = albumService.findAlbum(albumId);

        return new ModelAndView("album-edit-form", "editAlbumDto", new EditAlbumDto(albumId, album.getTitle(), album.getDescription()));
    }

    @PostMapping("/{albumId}")
    public String updateProfile(@PathVariable long albumId, @Valid @ModelAttribute EditAlbumDto editAlbumDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return String.format("redirect:/albums/%d/edit", albumId);
        }
        Album album = albumService.findAlbum(albumId);
        album.setTitle(editAlbumDto.getTitle());
        album.setDescription(editAlbumDto.getDescription());

        albumService.updateAlbum(album);

        return "redirect:/albums";
    }

    @DeleteMapping("/{albumId}")
    @Secured("ROLE_ADMIN")
    public void deleteProfile(@PathVariable Long albumId) {
        albumService.deleteAlbum(albumId);
    }
}
