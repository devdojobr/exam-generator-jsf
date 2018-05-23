package br.com.devdojo.examgenerator.persistence.dao;

import br.com.devdojo.examgenerator.custom.CustomRestTemplate;
import br.com.devdojo.examgenerator.persistence.model.Assignment;
import br.com.devdojo.examgenerator.util.ApiUtil;
import br.com.devdojo.examgenerator.util.JsonUtil;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

import static org.springframework.http.HttpMethod.*;

/**
 * @author William Suane for DevDojo on 11/7/17.
 */
public class AssignmentDAO implements Serializable {
    private final String LIST_URL = ApiUtil.BASE_URL + "/professor/course/assignment/list/{courseId}/";
    private final String DELETE_OR_FIND_ONE_URL = ApiUtil.BASE_URL + "/professor/course/assignment/{id}";
    private final String CREATE_UPDATE_URL = ApiUtil.BASE_URL + "/professor/course/assignment";
    private final CustomRestTemplate restTemplate;
    private final JsonUtil jsonUtil;
    private final ParameterizedTypeReference<List<Assignment>> assignmentListTypeReference = new ParameterizedTypeReference<List<Assignment>>() {
    };

    @Inject
    public AssignmentDAO(CustomRestTemplate restTemplate, JsonUtil jsonUtil) {
        this.restTemplate = restTemplate;
        this.jsonUtil = jsonUtil;
    }

    public List<Assignment> list(long courseId, String title) {
        UriComponents url = UriComponentsBuilder.fromUriString(LIST_URL).queryParam("title", title).build();
        ResponseEntity<List<Assignment>> exchange = restTemplate.exchange(url.toUriString(), GET, jsonUtil.tokenizedHttpEntityHeader(), assignmentListTypeReference, courseId);
        return exchange.getBody();
    }

    public Assignment findOne(long id) {
        return restTemplate.exchange(DELETE_OR_FIND_ONE_URL, GET, jsonUtil.tokenizedHttpEntityHeader(), Assignment.class, id).getBody();
    }

    public Assignment update(Assignment assignment) {
        return createOrUpdate(PUT, assignment);
    }

    public Assignment create(Assignment assignment) {
        return createOrUpdate(POST, assignment);
    }

    private Assignment createOrUpdate(HttpMethod httpMethod, Assignment assignment) {
        return restTemplate.exchange(CREATE_UPDATE_URL, httpMethod, jsonUtil.tokenizedHttpEntityHeader(assignment), Assignment.class).getBody();
    }

    public void delete(Assignment assignment) {
        restTemplate.exchange(DELETE_OR_FIND_ONE_URL, DELETE,
                jsonUtil.tokenizedHttpEntityHeader(assignment),
                Assignment.class, assignment.getId());
    }
}
