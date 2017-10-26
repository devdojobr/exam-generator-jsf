package br.com.devdojo.examgenerator.persistence.dao;

import br.com.devdojo.examgenerator.custom.CustomObjectMapper;
import br.com.devdojo.examgenerator.custom.CustomRestRemplate;
import br.com.devdojo.examgenerator.persistence.model.support.ErrorDetail;
import br.com.devdojo.examgenerator.persistence.model.support.Token;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.io.IOException;
import java.io.Serializable;

import static org.springframework.http.HttpMethod.POST;

/**
 * @author William Suane for DevDojo on 10/20/17.
 */
public class LoginDAO implements Serializable {
    private final String BASE_URL = "http://localhost:8085/login";
    private final CustomRestRemplate restTemplate;

    @Inject
    public LoginDAO(CustomRestRemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Token loginReturningToken(String username, String password) {
        String loginJson = "{\"username\":" + addQuotes(username) + ",\"password\":" + addQuotes(password) + "}";
        try {
            ResponseEntity<Token> tokenExchange = restTemplate.exchange(BASE_URL, POST, new HttpEntity<>(loginJson, createJsonHeader()), Token.class);
            return tokenExchange.getBody();
        } catch (HttpClientErrorException e) {
            try {
                ErrorDetail errorDetail = new CustomObjectMapper().readValue(e.getResponseBodyAsString(), ErrorDetail.class);
                System.out.println(errorDetail);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            System.out.println(e.getResponseBodyAsString());
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("StringBufferReplaceableByString")
    private String addQuotes(String value) {
        return new StringBuilder(300).append("\"").append(value).append("\"").toString();
    }

    private HttpHeaders createJsonHeader() {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return header;
    }
}
