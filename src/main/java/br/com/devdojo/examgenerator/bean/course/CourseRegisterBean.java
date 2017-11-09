package br.com.devdojo.examgenerator.bean.course;

import br.com.devdojo.examgenerator.annotation.ExceptionHandler;
import br.com.devdojo.examgenerator.persistence.dao.CourseDAO;
import br.com.devdojo.examgenerator.persistence.model.Course;
import org.omnifaces.util.Messages;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * @author William Suane for DevDojo on 11/9/17.
 */
@Named
@ViewScoped
public class CourseRegisterBean implements Serializable {
    private final CourseDAO courseDAO;
    private Course course = new Course();

    @Inject
    public CourseRegisterBean(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    @ExceptionHandler
    public String save() {
        Course course = courseDAO.create(this.course);
        Messages.create("The course {0} was successfully added.", course.getName()).flash().add();
        return "list.xhtml?faces-redirect=true";
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
