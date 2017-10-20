package br.com.devdojo.examgenerator.persistence.dao;

import br.com.devdojo.examgenerator.persistence.model.Token;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.io.Serializable;

import static org.springframework.http.HttpMethod.POST;

/**
 * @author William Suane for DevDojo on 10/20/17.
 */
public class LoginDAO implements Serializable {
    private final String BASE_URL = "http://localhost:8085/login";
    private final RestTemplate restTemplate;

    @Inject
    public LoginDAO(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Token loginReturningToken(String username, String password){
        String loginJson = "{\"username\":" + addQuotes(username) + ",\"password\":" + addQuotes(password) + "}";
        ResponseEntity<Token> tokenExchange = restTemplate
                .exchange(BASE_URL, POST, new HttpEntity<>(loginJson, createJsonHeader()), Token.class);
        return tokenExchange.getBody();
    }

    @SuppressWarnings("StringBufferReplaceableByString")
    private String addQuotes(String value) {
        return new StringBuilder(300).append("\"").append(value).append("\"").toString();
    }

    private HttpHeaders createJsonHeader(){
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return header;
    }
}
