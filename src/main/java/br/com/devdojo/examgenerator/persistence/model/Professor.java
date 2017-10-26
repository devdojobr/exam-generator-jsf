package br.com.devdojo.examgenerator.persistence.model;

/**
 * @author William Suane for DevDojo on 10/26/17.
 */
public class Professor extends AbstractEntity {
    //    @NotEmpty(message = "The field name cannot be empty")
    private String name;
    //    @Email(message = "This email is not valid")
//    @NotEmpty(message = "The field email cannot be empty")
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
