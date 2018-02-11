package br.com.devdojo.examgenerator.persistence.dao;

import br.com.devdojo.examgenerator.custom.CustomRestRemplate;
import br.com.devdojo.examgenerator.persistence.model.QuestionAssignment;
import br.com.devdojo.examgenerator.util.ApiUtil;
import br.com.devdojo.examgenerator.util.JsonUtil;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

import static org.springframework.http.HttpMethod.GET;

/**
 * @author William Suane for DevDojo on 11/02/2018.
 */
public class QuestionAssignmentDAO implements Serializable{
    private final String LIST_URL = ApiUtil.BASE_URL + "/professor/course/assignment/questionassignment/{assignmentId}";
    private final CustomRestRemplate restRemplate;
    private final JsonUtil jsonUtil;
    private final ParameterizedTypeReference<List<QuestionAssignment>> questionAssignmentListTypeReference = new ParameterizedTypeReference<List<QuestionAssignment>>() {
    };

    @Inject
    public QuestionAssignmentDAO(CustomRestRemplate restRemplate, JsonUtil jsonUtil) {
        this.restRemplate = restRemplate;
        this.jsonUtil = jsonUtil;
    }

    public List<QuestionAssignment> list(long assignmentId) {
        ResponseEntity<List<QuestionAssignment>> exchange = restRemplate.exchange(LIST_URL, GET, jsonUtil.tokenizedHttpEntityHeader(), questionAssignmentListTypeReference, assignmentId);
        return exchange.getBody();
    }
}
