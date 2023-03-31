package lms.lms.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PlaylistController {


    @PostMapping("/playlist")
    public String createPlaylist(){
        return "";
    }
}
