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
import org.springframework.web.bind.annotation.RequestParam;


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
    public String updateUserInformation(@ModelAttribute User updatedUserInfo) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = loggedInUser.getId();
        User user = userDao.findById(userId).get();
        System.out.println(user.toString());
        // Update the email and username fields
        if (updatedUserInfo.getEmail() != null && !updatedUserInfo.getEmail().isEmpty()) {
            user.setEmail(updatedUserInfo.getEmail());
        }
        if (updatedUserInfo.getUsername() != null && !updatedUserInfo.getUsername().isEmpty()) {
            user.setUsername(updatedUserInfo.getUsername());
        }
        // Update the password if not empty
        if (!updatedUserInfo.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(updatedUserInfo.getPassword()));
            System.out.println("Password has been changed");
        }
        userDao.save(user);
        return "redirect:/profile";
    }



}

