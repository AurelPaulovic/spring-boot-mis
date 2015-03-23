package sk.anext.msi.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import sk.anext.msi.bo.User;
import sk.anext.msi.service.UserService;
import sk.anext.msi.util.PageWrapper;


@Controller
@RequestMapping("/userTable")
public class UserTableController {
    public static final int USER_TABLE_PER_PAGE = 3;
    
    @Autowired
    private UserService userService;
    
    @RequestMapping
    public String getPage(@PageableDefault(page = 0, size = USER_TABLE_PER_PAGE) Pageable pageable, Model model) {
        model.addAttribute("userTable", new PageWrapper<User>(userService.getUsers(pageable), "/userTable"));
        
        return "component/userTable :: userTable";
    }
    
    public static Pageable getDefaultPageable() {
        return new PageRequest(0, USER_TABLE_PER_PAGE);
    }
}
