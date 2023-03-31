package lms.lms.Controllers.User;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {


    @GetMapping("/login")
    public String showLoginFormView(){
        return "/AuthenticationViews/Login";
    }


    @PostMapping("/login")
    public String loginFormPost(){
        return "";
    }

}
