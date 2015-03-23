package sk.anext.msi.service;

import java.util.List;

import sk.anext.msi.bo.User;

public interface UserService {
    void addUser(User user);
    
    List<User> getUsers();
}
