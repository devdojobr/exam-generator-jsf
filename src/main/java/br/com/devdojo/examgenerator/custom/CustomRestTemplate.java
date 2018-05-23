package br.com.devdojo.examgenerator.custom;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author William Suane for DevDojo on 10/26/17.
 * * Without CustomRestTemplate class the @Inject for resttemplate will not work
 */
public class CustomRestTemplate extends RestTemplate{
    public CustomRestTemplate(){
        this.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
    }
}
