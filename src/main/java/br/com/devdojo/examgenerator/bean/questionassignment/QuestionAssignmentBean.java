package br.com.devdojo.examgenerator.bean.questionassignment;

import br.com.devdojo.examgenerator.persistence.dao.AssignmentDAO;
import br.com.devdojo.examgenerator.persistence.dao.QuestionAssignmentDAO;
import br.com.devdojo.examgenerator.persistence.model.Assignment;
import br.com.devdojo.examgenerator.persistence.model.Question;
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
public class QuestionAssignmentBean implements Serializable {
    private List<QuestionAssignment> questionAssignmentList;
    private List<Question> availableQuestionList;
    private Question selectedQuestion;
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
    }

    public void associateQuestion() {
        QuestionAssignment questionAssignment = QuestionAssignment
                .Builder
                .newQuestionAssignment()
                .assignment(assignment)
                .question(selectedQuestion)
                .grade(0)
                .build();
        updateQuestionAssignmentList(questionAssignmentDAO.associateQuestionToAssignment(questionAssignment));
        updateAvailableQuestionList();
    }

    private void updateQuestionAssignmentList(QuestionAssignment questionAssignment) {
        questionAssignmentList.add(questionAssignment);
    }

    private void updateAvailableQuestionList() {
        availableQuestionList.remove(selectedQuestion);
        selectedQuestion = null;
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

    public Question getSelectedQuestion() {
        return selectedQuestion;
    }

    public void setSelectedQuestion(Question selectedQuestion) {
        this.selectedQuestion = selectedQuestion;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }
}
