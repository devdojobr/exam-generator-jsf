package br.com.devdojo.examgenerator.persistence.dao;

import br.com.devdojo.examgenerator.annotation.ExceptionHandler;
import br.com.devdojo.examgenerator.custom.CustomRestRemplate;
import br.com.devdojo.examgenerator.custom.CustomTypeReference;
import br.com.devdojo.examgenerator.persistence.model.Course;
import br.com.devdojo.examgenerator.util.ApiUtil;
import br.com.devdojo.examgenerator.util.JsonUtil;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.PUT;

/**
 * @author William Suane for DevDojo on 11/7/17.
 */
public class CourseDAO implements Serializable {
    private final String LIST_URL = ApiUtil.BASE_URL + "/professor/course/list";
    private final String FIND_ONE_URL = ApiUtil.BASE_URL + "/professor/course/{id}";
    private final String UPDATE_URL = ApiUtil.BASE_URL + "/professor/course/";
    private final CustomRestRemplate restRemplate;
    private final JsonUtil jsonUtil;
    private final CustomTypeReference<List<Course>> listCourse;

    @Inject
    public CourseDAO(CustomRestRemplate restRemplate, JsonUtil jsonUtil, CustomTypeReference<List<Course>> listCourse) {
        this.restRemplate = restRemplate;
        this.jsonUtil = jsonUtil;
        this.listCourse = listCourse;
    }

    @ExceptionHandler
    public List<Course> list(String name) {
        UriComponents url = UriComponentsBuilder.fromUriString(LIST_URL).queryParam("name", name).build();
        ResponseEntity<List<Course>> exchange = restRemplate.exchange(url.toUriString(), GET, jsonUtil.tokenizedHttpEntityHeader(), listCourse.typeReference());
        return exchange.getBody();
    }

    @ExceptionHandler
    public Course findOne(long id) {
        return restRemplate.exchange(FIND_ONE_URL, GET, jsonUtil.tokenizedHttpEntityHeader(), Course.class, id).getBody();
    }

    public Course update(Course course){
        return restRemplate.exchange(UPDATE_URL, PUT, jsonUtil.tokenizedHttpEntityHeader(course), Course.class).getBody();
    }
}
