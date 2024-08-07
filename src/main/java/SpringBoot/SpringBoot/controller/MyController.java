package SpringBoot.SpringBoot.controller;

import SpringBoot.SpringBoot.model.User;
import SpringBoot.SpringBoot.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller // This means that this class is a Controller
//@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class MyController {

    @Autowired
    private UserService usService;

    //@GetMapping(value = "/")
    @GetMapping("/")
    public String printWelcome(ModelMap model) {
        List<User> users = usService.findAll();
        model.addAttribute("users", users);
        System.out.println(users);
        return "index";
    }
    @GetMapping("/add")
    public String add(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        return "add";
    }

    @RequestMapping(value="/add", method=RequestMethod.POST)
    public String addNewOrder(@ModelAttribute User model) {
        usService.add(model);
        return "redirect:/";
    }
    @RequestMapping(value="delete", method= RequestMethod.GET)
    public String deleteItem(@RequestParam Long id) {
        User user = usService.getById(id);
        usService.delete(user);
        return "redirect:/";
    }
    @RequestMapping (value = "/edit", method= RequestMethod.GET)
    public String edit(ModelMap model,@RequestParam Long id) {
        //Long id = Long.parseLong(servletRequest.getParameter("id"));
        User user = usService.getById(id);
        model.addAttribute("user", user);
        return "edit";
    }

    @RequestMapping(value="/edit", method=RequestMethod.POST)
    public ModelAndView edit(@ModelAttribute User model,
                             @RequestParam(value="action", required=true) String action) {
        switch(action) {
            case "save":
                usService.add(model);
                break;
            case "cancel":
                // do stuff
                break;
            case "newthing":
                // do stuff
                break;
            default:
                // do stuff
                break;
        }
        return new ModelAndView( "redirect:/");
    }
}
