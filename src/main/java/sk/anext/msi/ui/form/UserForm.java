package sk.anext.msi.ui.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import sk.anext.msi.bo.UserType;

public class UserForm {
    @NotNull(message = "Name must be non-empty")
    @Size(min = 1, max = 20, message = "Name must be between {min} and {max} characters long")
    private String name;
    
    @NotNull(message = "Age must be non-empty")
    @Min(value = 0, message = "The user must be older than {value}")
    @Max(value = 120, message = "The user must be younger than {value}")
    private Integer age;
    
    @NotNull
    private UserType type;
    
    private Boolean enabled;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}