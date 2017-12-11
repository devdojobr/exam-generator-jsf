package br.com.devdojo.examgenerator.bean.assignment;

import br.com.devdojo.examgenerator.annotation.ExceptionHandler;
import br.com.devdojo.examgenerator.persistence.dao.AssignmentDAO;
import br.com.devdojo.examgenerator.persistence.model.Assignment;
import org.omnifaces.util.Messages;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * @author William Suane for DevDojo on 12/11/17.
 */
@Named
@ViewScoped
public class AssignmentEditBean implements Serializable {
    private final AssignmentDAO assignmentDAO;
    private Assignment assignment;
    private long assignmentId;

    @Inject
    public AssignmentEditBean(AssignmentDAO assignmentDAO) {
        this.assignmentDAO = assignmentDAO;
    }

    @ExceptionHandler
    public void init() {
        assignment = assignmentDAO.findOne(assignmentId);
    }

    @ExceptionHandler
    public String update() {
        assignmentDAO.update(assignment);
        Messages.create("The assignment {0} was successfully updated.", assignment.getTitle()).flash().add();
        return "list.xhtml?faces-redirect=true&courseId=" + assignment.getCourse().getId();
    }
    @ExceptionHandler
    public String delete() {
        assignmentDAO.delete(assignment);
        Messages.create("The assignment {0} was successfully deleted.", assignment.getTitle()).flash().add();
        return "list.xhtml?faces-redirect=true&courseId=" + assignment.getCourse().getId();
    }


    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public long getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(long assignmentId) {
        this.assignmentId = assignmentId;
    }
}
