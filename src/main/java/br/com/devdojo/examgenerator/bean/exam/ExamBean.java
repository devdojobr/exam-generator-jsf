package br.com.devdojo.examgenerator.bean.exam;

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

    @Inject
    public ExamBean(ExamDAO examDAO) {
        this.examDAO = examDAO;
    }

    public void init(){
        Map<Question, List<Choice>> list = examDAO.list(1234);
        System.out.println(list);
    }
}
