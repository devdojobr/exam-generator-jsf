package br.com.devdojo.examgenerator.persistence.model;

/**
 * @author William Suane for DevDojo on 11/02/2018.
 */
public class QuestionAssignment extends AbstractEntity {
    private Professor professor;
    private Question question;
    private Assignment assignment;
    private double grade;


    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }


    public static final class Builder {
        private QuestionAssignment questionAssignment;

        private Builder() {
            questionAssignment = new QuestionAssignment();
        }

        public static Builder newQuestionAssignment() {
            return new Builder();
        }

        public Builder professor(Professor professor) {
            questionAssignment.setProfessor(professor);
            return this;
        }

        public Builder id(Long id) {
            questionAssignment.setId(id);
            return this;
        }

        public Builder question(Question question) {
            questionAssignment.setQuestion(question);
            return this;
        }

        public Builder enabled(boolean enabled) {
            questionAssignment.setEnabled(enabled);
            return this;
        }

        public Builder assignment(Assignment assignment) {
            questionAssignment.setAssignment(assignment);
            return this;
        }

        public Builder grade(double grade) {
            questionAssignment.setGrade(grade);
            return this;
        }

        public QuestionAssignment build() {
            return questionAssignment;
        }
    }
}
