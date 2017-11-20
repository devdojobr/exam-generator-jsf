package br.com.devdojo.examgenerator.persistence.model;


import java.io.Serializable;

/**
 * @author William Suane for DevDojo on 10/26/17.
 */
public class AbstractEntity implements Serializable {
    protected Long id;
    protected boolean enabled = true;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return id != null ? id.equals(that.id) : that.id == null;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
