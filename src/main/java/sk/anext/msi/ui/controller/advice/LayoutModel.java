package sk.anext.msi.ui.controller.advice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import sk.anext.msi.bo.User;
import sk.anext.msi.service.UserService;
import sk.anext.msi.ui.controller.advice.annotation.WithLayoutModel;

@ControllerAdvice(annotations = WithLayoutModel.class )
public class LayoutModel {
    @Autowired
    private UserService userService;
    
    @ModelAttribute("userTable")
    public List<User> getUserTable() {
        // should probably transform bo object to ui objects
        return userService.getUsers();
    }
}
