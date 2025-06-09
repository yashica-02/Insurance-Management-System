package edu.iit.sat.itmd4515.ysharma7.converter;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.faces.convert.ConverterException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Converts LocalDate objects to and from String for JSF forms.  
 * @author yashica
 */
@FacesConverter(value="localDateConverter", forClass=LocalDate.class)
public class LocalDateConverter implements Converter<LocalDate> {

    // List of the  allowed input formats for date
    private static final DateTimeFormatter[] FORMATTERS = new DateTimeFormatter[] {
        DateTimeFormatter.ofPattern("yyyy-MM-dd"),   // ISO
        DateTimeFormatter.ofPattern("dd-MM-yyyy"),   // European
        DateTimeFormatter.ofPattern("MM/dd/yyyy")    // US-style
    };

    @Override
    public LocalDate getAsObject(FacesContext ctx, UIComponent comp, String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        for (DateTimeFormatter fmt : FORMATTERS) {
            try {
                return LocalDate.parse(value, fmt);
            } catch (DateTimeParseException ignored) {
            }
        }
        throw new ConverterException("Date must be in one of: yyyy-MM-dd, dd-MM-yyyy, MM/dd/yyyy");
    }

    @Override
    public String getAsString(FacesContext ctx, UIComponent comp, LocalDate date) {
        return (date != null) 
            ? date.format(FORMATTERS[0])   // always render as yyyy-MM-dd
            : "";
    }
}