package lms.lms.Controllers;


import lms.lms.Models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    private final UserRepository userDao;

    public ProfileController(UserRepository userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/profile")
    public String HomeController(Model model){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = loggedInUser.getId();
        User user = userDao.findById(userId).get();
        model.addAttribute("user", user);
        return "Profile/Profile";
    }
}
