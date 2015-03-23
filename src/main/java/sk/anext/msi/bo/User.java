package sk.anext.msi.bo;

import sk.anext.msi.ui.form.UserForm;

public class User {
    private final boolean enabled;
    private final String name;
    private final int age;
    private final UserType type;

    public User(UserForm user) {
        enabled = user.getEnabled();
        name = user.getName();
        age = user.getAge();
        type = user.getType();
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public UserType getType() {
        return type;
    }

}
