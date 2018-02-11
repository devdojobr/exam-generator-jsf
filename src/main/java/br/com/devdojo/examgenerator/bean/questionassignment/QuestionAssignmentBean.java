package br.com.devdojo.examgenerator.bean.questionassignment;

import br.com.devdojo.examgenerator.persistence.dao.QuestionAssignmentDAO;
import br.com.devdojo.examgenerator.persistence.model.QuestionAssignment;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * @author William Suane for DevDojo on 11/02/2018.
 */
@Named
@ViewScoped
public class QuestionAssignmentBean implements Serializable{
    private List<QuestionAssignment> questionAssignmentList;
    private long assignmentId;
    private final QuestionAssignmentDAO questionAssignmentDAO;
    @Inject
    public QuestionAssignmentBean(QuestionAssignmentDAO questionAssignmentDAO) {
        this.questionAssignmentDAO = questionAssignmentDAO;
    }

    public void init(){
        questionAssignmentList = questionAssignmentDAO.list(assignmentId);
    }

    public List<QuestionAssignment> getQuestionAssignmentList() {
        return questionAssignmentList;
    }

    public void setQuestionAssignmentList(List<QuestionAssignment> questionAssignmentList) {
        this.questionAssignmentList = questionAssignmentList;
    }

    public long getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(long assignmentId) {
        this.assignmentId = assignmentId;
    }
}
