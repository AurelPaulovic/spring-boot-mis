package sk.anext.msi.ui.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    private static final Log log = LogFactory.getLog(HomeController.class);

    @RequestMapping
    public String home() {
        return "redirect:/user/add";
    }
}
