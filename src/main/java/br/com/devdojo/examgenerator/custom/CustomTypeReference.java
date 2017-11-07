package br.com.devdojo.examgenerator.custom;

import org.springframework.core.ParameterizedTypeReference;

/**
 * @author William Suane for DevDojo on 11/7/17.
 */
public class CustomTypeReference<T> extends ParameterizedTypeReference<T> {

    public ParameterizedTypeReference<T> typeReference() {
        return new ParameterizedTypeReference<T>() {
        };
    }
}
