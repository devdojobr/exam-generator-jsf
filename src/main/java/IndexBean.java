import br.com.devdojo.examgenerator.persistence.dao.LoginDAO;
import br.com.devdojo.examgenerator.persistence.model.Token;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * @author William Suane for DevDojo on 10/20/17.
 */
@Named
@ViewScoped
public class IndexBean implements Serializable {
    private String message = "Woooooooooooorking";
    private final LoginDAO loginDAO;

    @Inject
    public IndexBean(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }

    public void login(){
        Token token = loginDAO.loginReturningToken("william", "devdojo");
        System.out.println(token);
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
