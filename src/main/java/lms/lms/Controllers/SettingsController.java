package lms.lms.Controllers;


import lms.lms.Models.User;
import lms.lms.Models.UserWithRoles;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class SettingsController {
    private UserRepository userDao;
    private PasswordEncoder passwordEncoder;

    public SettingsController(UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/settings")
    public String showSettingsView(Model model){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", loggedInUser);
        return "userSettings";
    }


    @PostMapping("/settings")
    public String updateUserInformation(@ModelAttribute("user") UserWithRoles updatedUserInfo){
        UserWithRoles loggedInUser = (UserWithRoles) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        loggedInUser.setEmail(updatedUserInfo.getEmail());

        if(!updatedUserInfo.getPassword().isEmpty()){
            loggedInUser.setPassword(passwordEncoder.encode(updatedUserInfo.getPassword()));
        }
        userDao.save(loggedInUser);
        return "Profile/Profile";
    }
}

