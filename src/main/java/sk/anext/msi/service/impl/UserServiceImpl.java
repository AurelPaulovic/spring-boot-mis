package sk.anext.msi.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import sk.anext.msi.bo.User;
import sk.anext.msi.service.UserService;

@Service
@Scope("singleton")
public class UserServiceImpl implements UserService {
    private List<User> users;

    @PostConstruct
    public void initialize() {
        this.users = new ArrayList<User>(); 
    }
    
    synchronized public void addUser(User user) {
        users.add(user);
    }
    
    synchronized public List<User> getUsers() {
        // let us pretend shallow copy is enough
        return new ArrayList<User>(this.users);
    }
}
