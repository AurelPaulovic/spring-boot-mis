package sk.anext.msi.ui.controller;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sk.anext.msi.bo.User;
import sk.anext.msi.service.UserService;
import sk.anext.msi.ui.component.SessionPanel;
import sk.anext.msi.ui.controller.advice.annotation.WithLayoutModel;
import sk.anext.msi.ui.form.UserForm;
import sk.anext.msi.ui.service.NeoUserService;

@Controller
@RequestMapping("/user")
@WithLayoutModel
public class UserController {
    private static final Log log = LogFactory.getLog(UserController.class);

    @Autowired
    private SessionPanel sessionPanel;

    @Autowired
    private UserService userService;

    @Autowired
    private NeoUserService neoUserService;

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String showAddForm(Model model) {
        model.addAttribute("user", new UserForm());
        return "user/form";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String submitAddForm(@Valid @ModelAttribute("user") UserForm user, BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.info("got some errors");
            return "user/form";
        }
        sessionPanel.incrementAddedCount();
        userService.addUser(new User(user));

        redirectAttributes.addFlashAttribute("addedUser", user);
        redirectAttributes.addFlashAttribute("message", "User was successfully added!");

        neoUserService.createNewUser(user.getName(), user.getAge(), user.getEnabled(), user.getType());

        log.info("no errors");
        return "redirect:/user/add";
    }
}
