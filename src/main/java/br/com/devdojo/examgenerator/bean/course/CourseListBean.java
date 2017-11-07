package br.com.devdojo.examgenerator.bean.course;

import br.com.devdojo.examgenerator.persistence.dao.CourseDAO;
import br.com.devdojo.examgenerator.persistence.model.Course;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * @author William Suane for DevDojo on 11/7/17.
 */
@Named
@ViewScoped
public class CourseListBean implements Serializable {
    private final CourseDAO courseDAO;
    private String name;

    @Inject
    public CourseListBean(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    @PostConstruct
    public void init() {
        List<Course> list = courseDAO.list("");
        System.out.println(list);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
