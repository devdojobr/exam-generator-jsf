package br.com.devdojo.examgenerator.bean.question;

import br.com.devdojo.examgenerator.annotation.ExceptionHandler;
import br.com.devdojo.examgenerator.persistence.dao.QuestionDAO;
import br.com.devdojo.examgenerator.persistence.model.Question;
import org.omnifaces.util.Messages;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * @author William Suane for DevDojo on 11/9/17.
 */
@Named
@ViewScoped
public class QuestionEditBean implements Serializable {
    private final QuestionDAO questionDAO;
    private Question question;
    private long questionId;

    @Inject
    public QuestionEditBean(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }

    @ExceptionHandler
    public void init() {
        question = questionDAO.findOne(questionId);
    }

    @ExceptionHandler
    public String update() {
        questionDAO.update(question);
        Messages.create("The question {0} was successfully updated.", question.getTitle()).flash().add();
        return "list.xhtml?faces-redirect=true&courseId=" + question.getCourse().getId();
    }
    @ExceptionHandler
    public String delete() {
        questionDAO.delete(question);
        Messages.create("The question {0} was successfully deleted.", question.getTitle()).flash().add();
        return "list.xhtml?faces-redirect=true&courseId=" + question.getCourse().getId();
    }
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }
}
