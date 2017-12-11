package br.com.devdojo.examgenerator.bean.assignment;

import br.com.devdojo.examgenerator.annotation.ExceptionHandler;
import br.com.devdojo.examgenerator.persistence.dao.AssignmentDAO;
import br.com.devdojo.examgenerator.persistence.dao.CourseDAO;
import br.com.devdojo.examgenerator.persistence.model.Assignment;
import br.com.devdojo.examgenerator.persistence.model.Course;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * @author William Suane for DevDojo on 12/11/17.
 */
@Named
@ViewScoped
public class AssignmentListBean implements Serializable {
    private final AssignmentDAO assignmentDAO;
    private final CourseDAO courseDAO;
    private List<Assignment> assignmentList;
    private Course course;
    private String title = "";
    private long courseId;

    @Inject
    public AssignmentListBean(AssignmentDAO assignmentDAO, CourseDAO courseDAO) {
        this.assignmentDAO = assignmentDAO;
        this.courseDAO = courseDAO;
    }
    @ExceptionHandler
    public void init() {
        course = courseDAO.findOne(courseId);
        search();
    }

    public void search() {
        assignmentList = assignmentDAO.list(courseId, title);
    }

    public List<Assignment> getAssignmentList() {
        return assignmentList;
    }

    public void setAssignmentList(List<Assignment> assignmentList) {
        this.assignmentList = assignmentList;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }
}
