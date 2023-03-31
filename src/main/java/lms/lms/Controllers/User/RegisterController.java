package lms.lms.Controllers.User;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {


    @GetMapping("/register")
    public String showRegisterForm(){
        return "/AuthenticationViews/Register";
    }


    @PostMapping("/register")
    public String saveUserToDatabse(){
        return "";
    }


}
