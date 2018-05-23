package br.com.devdojo.examgenerator.persistence.dao;

import br.com.devdojo.examgenerator.custom.CustomRestTemplate;
import br.com.devdojo.examgenerator.persistence.model.Choice;
import br.com.devdojo.examgenerator.persistence.model.Question;
import br.com.devdojo.examgenerator.util.ApiUtil;
import br.com.devdojo.examgenerator.util.JsonUtil;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpMethod.GET;

/**
 * @author William Suane for DevDojo on 21/05/2018.
 */
public class ExamDAO implements Serializable {
    private final String LIST_CHOICES_BASED_ON_ACCESS_CODE_URL = ApiUtil.BASE_URL + "/student/exam/choice/{accessCode}/";
    private final CustomRestTemplate restTemplate;
    private final JsonUtil jsonUtil;
    private final ParameterizedTypeReference<List<Choice>> choiceListTypeReference = new ParameterizedTypeReference<List<Choice>>() {
    };

    @Inject
    public ExamDAO(CustomRestTemplate restTemplate, JsonUtil jsonUtil) {
        this.restTemplate = restTemplate;
        this.jsonUtil = jsonUtil;
    }

    public Map<Question, List<Choice>> list(String accessCode) {
        ResponseEntity<List<Choice>> exchange = restTemplate.exchange(LIST_CHOICES_BASED_ON_ACCESS_CODE_URL, GET, jsonUtil.tokenizedHttpEntityHeader(),
                choiceListTypeReference, accessCode);
        Map<Question, List<Choice>> questionChoicesMap = exchange.getBody().stream().collect(Collectors.groupingBy(Choice::getQuestion));
        return questionChoicesMap;
    }


}
