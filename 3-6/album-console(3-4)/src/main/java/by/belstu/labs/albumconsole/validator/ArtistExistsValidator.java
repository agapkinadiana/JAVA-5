package by.belstu.labs.albumconsole.validator;

import by.belstu.labs.albumconsole.repository.ArtistRepository;
import by.belstu.labs.albumconsole.entity.Artist;
import by.belstu.labs.albumconsole.validator.annotation.ArtistExists;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class ArtistExistsValidator implements ConstraintValidator<ArtistExists, String> {

    private ArtistRepository artistRepository;

    public ArtistExistsValidator(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public void initialize(ArtistExists constraintAnnotation) {
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        Optional<Artist> artistOptional = artistRepository.findArtistByName(name);
        return artistOptional.isPresent();
    }
}
