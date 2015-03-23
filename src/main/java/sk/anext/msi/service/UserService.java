package sk.anext.msi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import sk.anext.msi.bo.User;

public interface UserService {
    void addUser(User user);
    
    Page<User> getUsers(Pageable pageable);
}
