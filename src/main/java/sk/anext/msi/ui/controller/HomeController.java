package sk.anext.msi.ui.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {
    private static final Log log = LogFactory.getLog(HomeController.class);
    
    @RequestMapping
    public ModelAndView home() {
        log.info("render home");
        return new ModelAndView("home");
    }
}
