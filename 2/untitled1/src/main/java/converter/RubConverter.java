package main.java.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.text.DecimalFormat;

@FacesConverter("rubConverter")
public class RubConverter implements Converter {
    @Override
    public Object getAsObject (FacesContext context,
                               UIComponent component, String
                               value) {
        return value;
    }
    @Override
    public String getAsString (FacesContext ctx,
                                UIComponent component, Object
                                value) {
        int amountInDollars = Integer.parseInt(value.toString());
        int amount = amountInDollars*3;
       return String.valueOf(amount);

    }
}
