package br.com.devdojo.examgenerator.config;

import org.springframework.web.client.RestTemplate;

/**
 * @author William Suane for DevDojo on 10/20/17.
 * Without this class the @Inject for resttemplate will not work
 */
public class CustomRestTemplate extends RestTemplate {
}
