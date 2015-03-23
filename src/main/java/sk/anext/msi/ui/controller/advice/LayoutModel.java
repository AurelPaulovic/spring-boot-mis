package sk.anext.msi.ui.controller.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import sk.anext.msi.bo.User;
import sk.anext.msi.service.UserService;
import sk.anext.msi.ui.controller.UserTableController;
import sk.anext.msi.ui.controller.advice.annotation.WithLayoutModel;
import sk.anext.msi.util.PageWrapper;

@ControllerAdvice(annotations = WithLayoutModel.class )
public class LayoutModel {
    @Autowired
    private UserService userService;
    
    @ModelAttribute("userTable")
    public PageWrapper<User> getUserTable() {
        Pageable defaultPage = UserTableController.getDefaultPageable();
        return new PageWrapper<User>(userService.getUsers(defaultPage), "/userTable");
    }
}
