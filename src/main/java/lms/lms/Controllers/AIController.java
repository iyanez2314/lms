package lms.lms.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AIController {

    @GetMapping("/ai-assistant")
    public String showAiView(){
        return "/AIView/aiview";
    }
}
