package lms.lms.Controllers;


import lms.lms.Models.Language;
import lms.lms.Models.User;
import lms.lms.Models.UserLanguage;
import lms.lms.Models.UserWithRoles;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class SettingsController {
    private final UserRepository userDao;
    private final PasswordEncoder passwordEncoder;

    private final LanguageRepository languageDao;

    private final UserLanguageRepository userLanguageDao;

    public SettingsController(UserRepository userDao, PasswordEncoder passwordEncoder, LanguageRepository languageDao, UserLanguageRepository userLanguageDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.languageDao = languageDao;
        this.userLanguageDao = userLanguageDao;
    }

    @GetMapping("/settings")
    public String showSettingsView(Model model){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Language> allProgrammingLanguages = languageDao.findAll();
        model.addAttribute("allProgrammingLanguages", allProgrammingLanguages);
        model.addAttribute("selectedLanguages", new ArrayList<>());
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



    @PostMapping("/settings/languages")
    public String saveUserLanguages(@RequestParam("selectedLanguageIds") List<Long> selectedLanguageIds) {
        System.out.println(selectedLanguageIds);

        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = loggedInUser.getId();
        User user = userDao.findById(userId).get();
        System.out.println(user);

        List<Language> selectedLanguages = languageDao.findAllById(selectedLanguageIds);

        // Create and save new UserLanguage instances
        for (Language language : selectedLanguages) {
            // This loops over the selected languages the user chose
            UserLanguage userLanguage = new UserLanguage(language, user);
            // I save the selected language from the user to the database
            userLanguageDao.save(userLanguage);
            // Once I have finished saving the selected languages I then add the item to the arraylist in the user entity
            user.getUserLanguages().add(userLanguage);
        }
        userDao.save(user);
        return "redirect:/profile";
    }


}

