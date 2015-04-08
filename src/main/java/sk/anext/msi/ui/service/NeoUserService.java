package sk.anext.msi.ui.service;

import java.util.List;

import sk.anext.msi.bo.UserType;
import sk.anext.msi.ui.form.UserForm;

public interface NeoUserService {

    public UserForm createNewUser(String name, Integer age,Boolean isEnabled,UserType type);

    public List<UserForm> getUsers();
    
    public Long getCountOfUsers();
}
