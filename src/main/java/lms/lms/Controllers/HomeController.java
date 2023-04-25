package lms.lms.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final FeaturedVideoRepository featuredDao;

    public HomeController(FeaturedVideoRepository featuredDao) {
        this.featuredDao = featuredDao;
    }

    @GetMapping("/")
    public String showHomeScreen(Model model){
        model.addAttribute("featuredVideos", featuredDao.findAll());
        return "index";
    }
}
