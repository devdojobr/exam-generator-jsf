package br.com.devdojo.examgenerator.custom;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author William Suane for DevDojo on 10/26/17.
 */
public class CustomRestRemplate extends RestTemplate{
    public CustomRestRemplate(){
        this.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
    }
}
