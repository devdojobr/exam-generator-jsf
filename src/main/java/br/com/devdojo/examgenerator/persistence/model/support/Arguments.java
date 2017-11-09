package br.com.devdojo.examgenerator.persistence.model.support;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * @author William Suane for DevDojo on 11/9/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Arguments {
    private String defaultMessage;

    private List<String> codes;

    private String code;

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    public List<String> getCodes() {
        return codes;
    }

    public void setCodes(List<String> codes) {
        this.codes = codes;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
