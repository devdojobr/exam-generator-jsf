package br.com.devdojo.examgenerator.bean.question;

import br.com.devdojo.examgenerator.annotation.ExceptionHandler;
import br.com.devdojo.examgenerator.persistence.dao.CourseDAO;
import br.com.devdojo.examgenerator.persistence.dao.QuestionDAO;
import br.com.devdojo.examgenerator.persistence.model.Course;
import br.com.devdojo.examgenerator.persistence.model.Question;
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
public class QuestionRegisterBean implements Serializable {
    private final CourseDAO courseDAO;
    private final QuestionDAO questionDAO;
    private Course course;
    private Question question = new Question();
    private long courseId;

    @Inject
    public QuestionRegisterBean(CourseDAO courseDAO, QuestionDAO questionDAO) {
        this.courseDAO = courseDAO;
        this.questionDAO = questionDAO;
    }

    @ExceptionHandler
    public void init() {
        course = courseDAO.findOne(courseId);
    }

    @ExceptionHandler
    public void save() {
        question.setCourse(course);
        questionDAO.create(question);
        Messages.addGlobalInfo("The question {0} was successfully added.", question.getTitle());
        question = new Question();
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
