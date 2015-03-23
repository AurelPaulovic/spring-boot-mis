package sk.anext.msi.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
    
    synchronized public Page<User> getUsers(Pageable pageable) {
        int pageNumber =  pageable.getPageNumber(); 
        int size = pageable.getPageSize();
        int listSize = this.users.size();
        
        int idxStart = (pageNumber * size <= listSize) ? pageNumber * size : 0;
        int idxEnd = (idxStart + size <= listSize) ? idxStart + size : listSize;
        
        return new PageImpl<User>(this.users.subList(idxStart, idxEnd), pageable, listSize);
    }
}
