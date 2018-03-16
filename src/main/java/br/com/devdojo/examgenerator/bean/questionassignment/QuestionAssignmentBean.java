package br.com.devdojo.examgenerator.bean.questionassignment;

import br.com.devdojo.examgenerator.annotation.ExceptionHandler;
import br.com.devdojo.examgenerator.persistence.dao.AssignmentDAO;
import br.com.devdojo.examgenerator.persistence.dao.QuestionAssignmentDAO;
import br.com.devdojo.examgenerator.persistence.model.Assignment;
import br.com.devdojo.examgenerator.persistence.model.Question;
import br.com.devdojo.examgenerator.persistence.model.QuestionAssignment;
import org.omnifaces.util.Messages;
import org.primefaces.event.RowEditEvent;

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
public class QuestionAssignmentBean implements Serializable {
    private List<QuestionAssignment> questionAssignmentList;
    private List<Question> availableQuestionList;
    private QuestionAssignment questionAssignment;
    private Assignment assignment;
    private long assignmentId;
    private long courseId;
    private final QuestionAssignmentDAO questionAssignmentDAO;
    private final AssignmentDAO assignmentDAO;

    @Inject
    public QuestionAssignmentBean(QuestionAssignmentDAO questionAssignmentDAO, AssignmentDAO assignmentDAO) {
        this.questionAssignmentDAO = questionAssignmentDAO;
        this.assignmentDAO = assignmentDAO;
    }

    public void init() {
        questionAssignmentList = questionAssignmentDAO.listQuestionAssignment(assignmentId);
        availableQuestionList = questionAssignmentDAO.listAvailableQuestions(courseId, assignmentId);
        assignment = assignmentDAO.findOne(assignmentId);
        createNewQuestionAssignmentInstance();
    }

    private void createNewQuestionAssignmentInstance() {
        questionAssignment = QuestionAssignment.Builder.newQuestionAssignment().assignment(assignment).build();
    }

    @ExceptionHandler
    public void associateQuestion() {
        addQuestionAssignmentToQuestionAssignmentList(questionAssignmentDAO.associateQuestionToAssignment(questionAssignment));
        removeQuestionFromAvailableQuestionList(this.questionAssignment);
        createNewQuestionAssignmentInstance();
    }

    private void addQuestionAssignmentToQuestionAssignmentList(QuestionAssignment questionAssignment) {
        questionAssignmentList.add(questionAssignment);
    }

    private void removeQuestionAssignmentFromQuestionAssignmentList(QuestionAssignment questionAssignment) {
        questionAssignmentList.remove(questionAssignment);
    }

    private void removeQuestionFromAvailableQuestionList(QuestionAssignment questionAssignment) {
        availableQuestionList.remove(questionAssignment.getQuestion());
    }

    private void addQuestionToAvailableQuestionList(QuestionAssignment questionAssignment) {
        availableQuestionList.add(questionAssignment.getQuestion());
    }

    @ExceptionHandler
    public void delete(QuestionAssignment questionAssignment) {
        questionAssignmentDAO.delete(questionAssignment);
        addQuestionToAvailableQuestionList(questionAssignment);
        removeQuestionAssignmentFromQuestionAssignmentList(questionAssignment);
        createNewQuestionAssignmentInstance();
        Messages.addGlobalInfo("The choice {0} was successfully deleted.", questionAssignment.getQuestion().getTitle());
    }

    @ExceptionHandler
    public void onRowEditUpdateQuestionAssignment(RowEditEvent event) {
        QuestionAssignment questionAssignment = (QuestionAssignment) event.getObject();
        questionAssignmentDAO.update(questionAssignment);
        Messages.addGlobalInfo("The question {0} was successfully updated.", questionAssignment.getQuestion().getTitle());
    }

    public QuestionAssignment getQuestionAssignment() {
        return questionAssignment;
    }

    public void setQuestionAssignment(QuestionAssignment questionAssignment) {
        this.questionAssignment = questionAssignment;
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

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public List<Question> getAvailableQuestionList() {
        return availableQuestionList;
    }

    public void setAvailableQuestionList(List<Question> availableQuestionList) {
        this.availableQuestionList = availableQuestionList;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }
}
