package br.com.devdojo.examgenerator.bean.exam;

import br.com.devdojo.examgenerator.annotation.ExceptionHandler;
import br.com.devdojo.examgenerator.persistence.dao.ExamDAO;
import br.com.devdojo.examgenerator.persistence.model.Choice;
import br.com.devdojo.examgenerator.persistence.model.Question;
import org.omnifaces.util.Messages;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
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
    private Map<Question, List<Choice>> questionListMap;
    private String multipleChoiceAnswer;
    private Map<Long, Long> questionChoiceIdsMap = new HashMap<>();

    @Inject
    public ExamBean(ExamDAO examDAO) {
        this.examDAO = examDAO;
    }

    @ExceptionHandler
    public void init() {
        if (examDAO.studentCanAnswerExam(accessCode))
            questionListMap = examDAO.list(accessCode);
    }

    @ExceptionHandler
    public String accessExam() {
        return examDAO.studentCanAnswerExam(accessCode) ? "exam.xhtml?faces-redirect=true&accessCode=" + accessCode : null;
    }

    public void storeAnswer() {
        if (multipleChoiceAnswer != null && !multipleChoiceAnswer.isEmpty()) {
            //[0] question.id [1] choice.id
            String questionChoiceIds[] = multipleChoiceAnswer.split("#");
            questionChoiceIdsMap.put(Long.parseLong(questionChoiceIds[0]), Long.parseLong(questionChoiceIds[1]));
        }
    }

    @ExceptionHandler
    public String save() {
        examDAO.save(accessCode, questionChoiceIdsMap);
        Messages.create("The assignment was successfully submitted.").flash().add();
        return "index-student.xhtml?faces-redirect=true";
    }

    public String getMultipleChoiceAnswer() {
        return multipleChoiceAnswer;
    }

    public void setMultipleChoiceAnswer(String multipleChoiceAnswer) {
        this.multipleChoiceAnswer = multipleChoiceAnswer;
    }

    public Map<Long, Long> getQuestionChoiceIdsMap() {
        return questionChoiceIdsMap;
    }

    public void setQuestionChoiceIdsMap(Map<Long, Long> questionChoiceIdsMap) {
        this.questionChoiceIdsMap = questionChoiceIdsMap;
    }

    public Map<Question, List<Choice>> getQuestionListMap() {
        return questionListMap;
    }

    public void setQuestionListMap(Map<Question, List<Choice>> questionListMap) {
        this.questionListMap = questionListMap;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }
}
