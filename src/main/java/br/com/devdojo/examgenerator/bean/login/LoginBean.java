package br.com.devdojo.examgenerator.bean.login;

import br.com.devdojo.examgenerator.persistence.dao.LoginDAO;
import br.com.devdojo.examgenerator.persistence.model.Token;

import javax.faces.context.ExternalContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author William Suane for DevDojo on 10/20/17.
 */
@Named
@ViewScoped
public class LoginBean implements Serializable {
    private String username;
    private String password;
    private final LoginDAO loginDAO;
    private final ExternalContext externalContext;

    @Inject
    public LoginBean(LoginDAO loginDAO, ExternalContext externalContext) {
        this.loginDAO = loginDAO;
        this.externalContext = externalContext;
    }

    public String login() throws UnsupportedEncodingException {
        Token token = loginDAO.loginReturningToken("william", "devdojo");
        return token == null ? null : addTokenAndExpirationTimeToCookiesAndReturnIndex(token);
    }

    public String logout(){
        externalContext.addResponseCookie("token",null,null);
        externalContext.addResponseCookie("expirationTime",null,null);
        return "login.xhtml?faces-redirect=true";
    }

    private String addTokenAndExpirationTimeToCookiesAndReturnIndex(Token token) throws UnsupportedEncodingException {
        externalContext.addResponseCookie("token", URLEncoder.encode(token.getToken(), "UTF-8"), null);
        externalContext.addResponseCookie("expirationTime", token.getExpirationTime().toString(), null);
        return "index.xhtml?faces-redirect=true";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
