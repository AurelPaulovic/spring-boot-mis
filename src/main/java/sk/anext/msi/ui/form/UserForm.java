package sk.anext.msi.ui.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserForm {
    @NotNull
    @Size(min = 1, max = 10)
    private String firstname;
    
    @NotNull
    @Size(min = 1, max = 20)
    private String lastname;
    
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
