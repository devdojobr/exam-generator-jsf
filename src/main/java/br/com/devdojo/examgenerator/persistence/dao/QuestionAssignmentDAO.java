package br.com.devdojo.examgenerator.persistence.dao;

import br.com.devdojo.examgenerator.custom.CustomRestRemplate;
import br.com.devdojo.examgenerator.persistence.model.Question;
import br.com.devdojo.examgenerator.persistence.model.QuestionAssignment;
import br.com.devdojo.examgenerator.util.ApiUtil;
import br.com.devdojo.examgenerator.util.JsonUtil;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

/**
 * @author William Suane for DevDojo on 11/02/2018.
 */
public class QuestionAssignmentDAO implements Serializable{
    private final String LIST_QUESTION_ASSIGNMENT_URL = ApiUtil.BASE_URL + "/professor/course/assignment/questionassignment/{assignmentId}";
    private final String LIST_AVAILABLE_QUESTIONS_URL = ApiUtil.BASE_URL + "/professor/course/assignment/questionassignment/{courseId}/{assignmentId}";
    private final String CREATE_UPDATE_URL = ApiUtil.BASE_URL + "/professor/course/assignment/questionassignment/";
    private final CustomRestRemplate restRemplate;
    private final JsonUtil jsonUtil;
    private final ParameterizedTypeReference<List<QuestionAssignment>> questionAssignmentListTypeReference = new ParameterizedTypeReference<List<QuestionAssignment>>() {
    };
    private final ParameterizedTypeReference<List<Question>> questionListTypeReference = new ParameterizedTypeReference<List<Question>>() {
    };

    @Inject
    public QuestionAssignmentDAO(CustomRestRemplate restRemplate, JsonUtil jsonUtil) {
        this.restRemplate = restRemplate;
        this.jsonUtil = jsonUtil;
    }

    public List<QuestionAssignment> listQuestionAssignment(long assignmentId) {
        ResponseEntity<List<QuestionAssignment>> exchange = restRemplate.exchange(LIST_QUESTION_ASSIGNMENT_URL, GET, jsonUtil.tokenizedHttpEntityHeader(), questionAssignmentListTypeReference, assignmentId);
        return exchange.getBody();
    }

    public List<Question> listAvailableQuestions(long courseId, long assignmentId) {
        ResponseEntity<List<Question>> exchange = restRemplate.exchange(LIST_AVAILABLE_QUESTIONS_URL, GET, jsonUtil.tokenizedHttpEntityHeader(), questionListTypeReference,courseId, assignmentId);
        return exchange.getBody();
    }
    public QuestionAssignment associateQuestionToAssignment(QuestionAssignment questionAssignment) {
        return createOrUpdate(POST, questionAssignment);
    }

    private QuestionAssignment createOrUpdate(HttpMethod httpMethod, QuestionAssignment questionAssignment) {
        return restRemplate.exchange(CREATE_UPDATE_URL, httpMethod, jsonUtil.tokenizedHttpEntityHeader(questionAssignment), QuestionAssignment.class).getBody();
    }
}
