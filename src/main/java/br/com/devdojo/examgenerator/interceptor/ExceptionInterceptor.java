package br.com.devdojo.examgenerator.interceptor;

import br.com.devdojo.examgenerator.annotation.ExceptionHandler;
import br.com.devdojo.examgenerator.custom.CustomObjectMapper;
import br.com.devdojo.examgenerator.persistence.model.support.ErrorDetail;
import br.com.devdojo.examgenerator.persistence.model.support.Errors;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

/**
 * @author William Suane for DevDojo on 10/26/17.
 */
@Interceptor
@ExceptionHandler
public class ExceptionInterceptor implements Serializable {
    private final ExternalContext externalContext;

    @Inject
    public ExceptionInterceptor(ExternalContext externalContext) {
        this.externalContext = externalContext;
    }

    @AroundInvoke
    public Object invoke(InvocationContext context) throws IOException {
        Object result = null;
        try {
            result = context.proceed();
        } catch (Exception e) {
            if (e instanceof HttpClientErrorException || e instanceof HttpServerErrorException) {
                String errorMessage = defineErrorMessage((HttpStatusCodeException) e);
                addMessage(FacesMessage.SEVERITY_ERROR, errorMessage, false);
            } else {
                e.printStackTrace();
            }
        }
        return result;
    }

    private String defineErrorMessage(HttpStatusCodeException e) throws IOException {
        ErrorDetail errorDetail = new CustomObjectMapper().readValue(e.getResponseBodyAsString(), ErrorDetail.class);
        return errorDetail.getErrorsList() == null ? errorDetail.getMessage() :
                errorDetail.getErrorsList()
                        .stream()
                        .map(Errors::getDefaultMessage)
                        .collect(joining(","));
    }

    private void addMessage(FacesMessage.Severity severity, String msg, boolean keepMessages) {
        final FacesMessage facesMessage = new FacesMessage(severity, msg, "");
        if(keepMessages) externalContext.getFlash().setKeepMessages(true);
        externalContext.getFlash().setRedirect(true);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }
}
