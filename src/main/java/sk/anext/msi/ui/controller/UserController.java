package sk.anext.msi.ui.controller;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sk.anext.msi.ui.form.UserForm;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final Log log = LogFactory.getLog(UserController.class);
    
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String showAddForm(Model model) {
        model.addAttribute("user", new UserForm());
        return "user/form";
    }
    
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String submitAddForm(@Valid @ModelAttribute("user") UserForm user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("got some errors");
            return "user/form";
        }
        
        log.info("no errors");
        return "redirect:/user/add";
    }
}
