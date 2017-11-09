package br.com.devdojo.examgenerator.persistence.model.support;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * @author William Suane for DevDojo on 11/9/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Errors {
    private String rejectedValue;

    private String field;

    private String defaultMessage;

    private List<Arguments> arguments;

    private String objectName;

    private List<String> codes;

    private String code;

    private String bindingFailure;

    public String getRejectedValue() {
        return rejectedValue;
    }

    public void setRejectedValue(String rejectedValue) {
        this.rejectedValue = rejectedValue;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }


    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public List<Arguments> getArguments() {
        return arguments;
    }

    public void setArguments(List<Arguments> arguments) {
        this.arguments = arguments;
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

    public String getBindingFailure() {
        return bindingFailure;
    }

    public void setBindingFailure(String bindingFailure) {
        this.bindingFailure = bindingFailure;
    }

}
