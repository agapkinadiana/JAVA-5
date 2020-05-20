package main.java.validator;


import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CardValidator implements Validator {
    private Pattern pattern;
    private Matcher matcher;

    @Override
    public void validate(FacesContext facesContext,
                         UIComponent uiComponent, Object o)
            throws ValidatorException {
        String componentValue = o.toString();
        // pattern = Pattern.compile("^(0[1-9]{1}|1[0-2]{1})/\\d{2}$");
        //matcher = pattern.matcher(componentValue);
        if (/*!matcher.find()*/ o == null) {
            String message = MessageFormat.format("{0} wrong input(", componentValue);
            FacesMessage facesMessage = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, message, "--"
            );
            throw new ValidatorException(facesMessage);
        }
    }
}
