package br.com.devdojo.examgenerator.persistence.model.support;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author William Suane for DevDojo on 10/26/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorDetail {
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZZ")
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private String exception;
    @JsonProperty("errors")
    private List<Errors> errorsList;

    public List<Errors> getErrorsList() {
        return errorsList;
    }

    public void setErrorsList(List<Errors> errorsList) {
        this.errorsList = errorsList;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
