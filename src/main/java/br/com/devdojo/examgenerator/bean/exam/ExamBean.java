package br.com.devdojo.examgenerator.bean.exam;

import br.com.devdojo.examgenerator.annotation.ExceptionHandler;
import br.com.devdojo.examgenerator.persistence.dao.ExamDAO;
import br.com.devdojo.examgenerator.persistence.model.Choice;
import br.com.devdojo.examgenerator.persistence.model.Question;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author William Suane for DevDojo on 21/05/2018.
 */
@Named
@ViewScoped
public class ExamBean implements Serializable {
    private ExamDAO examDAO;
    private String accessCode;
    @Inject
    public ExamBean(ExamDAO examDAO) {
        this.examDAO = examDAO;
    }

    @ExceptionHandler
    public void accessExam(){
        Map<Question, List<Choice>> list = examDAO.list(accessCode);
        System.out.println(list);
    }

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }
}
