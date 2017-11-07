package br.com.devdojo.examgenerator.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.faces.annotation.RequestCookieMap;
import javax.inject.Inject;
import javax.servlet.http.Cookie;
import java.io.Serializable;
import java.util.Map;

import static br.com.devdojo.examgenerator.custom.CustomURLEncoderDecoder.decodeUTF8;

/**
 * @author William Suane for DevDojo on 10/26/17.
 */
public class JsonUtil implements Serializable {
    @Inject
    @RequestCookieMap
    private Map<String, Object> cookieMap;

    public HttpHeaders createJsonHeader() {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return header;
    }

    public HttpHeaders createTokenizedHeader() {
        HttpHeaders header = createJsonHeader();
        Cookie tokenCookie = (Cookie) cookieMap.get("token");
        header.add("Authorization", decodeUTF8(tokenCookie.getValue()));
        return header;
    }

    public HttpEntity tokenizedHttpEntityHeader() {
        return new HttpEntity(createTokenizedHeader());
    }

    public <E> HttpEntity<E> tokenizedHttpEntityHeader(E e) {
        return new HttpEntity<>(e,createTokenizedHeader());
    }


}
