package br.com.devdojo.examgenerator.converter;

import br.com.devdojo.examgenerator.persistence.model.AbstractEntity;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.Map;

/**
 * @author William Suane for DevDojo on 12/02/2018.
 */
@FacesConverter(value = "entityConverter")
public class EntityConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value == null || !value.matches("\\d+"))
            return null;
        return this.getAttributesFrom(uiComponent).get(value);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value != null && !value.equals("")) {
            AbstractEntity entity = (AbstractEntity) value;
            if (entity.getId() != null) {
                this.addAttribute(uiComponent, entity);
                return entity.getId().toString();
            }
        }
        return null;
    }

    private Map<String, Object> getAttributesFrom(UIComponent component) {
        return component.getAttributes();
    }

    private void addAttribute(UIComponent component, AbstractEntity entity) {
        this.getAttributesFrom(component).put(entity.getId().toString(), entity);
    }
}