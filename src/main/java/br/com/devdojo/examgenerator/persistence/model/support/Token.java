package br.com.devdojo.examgenerator.persistence.model.support;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author William Suane for DevDojo on 10/20/17.
 */
public class Token implements Serializable {
    private String token;
    @JsonProperty("exp")
    private LocalDateTime expirationTime;
    private String accessType;

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }
}
