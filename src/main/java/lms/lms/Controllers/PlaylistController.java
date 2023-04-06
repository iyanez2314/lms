package lms.lms.Controllers;


import lms.lms.Models.Playlist;
import lms.lms.Models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PlaylistController {

    private final PlaylistRepository playlistDao;
    private final UserRepository userDao;

    public PlaylistController(PlaylistRepository playlistDao, UserRepository userDao){
        this.playlistDao = playlistDao;
        this.userDao = userDao;
    }

    @PostMapping("/playlist/create")
    public String playlistCreation(@RequestParam(name = "playlistname") String playlistname){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("here");
        Long userId = loggedInUser.getId();
        User user = userDao.findById(userId).get();
        Playlist newPlaylist = new Playlist(playlistname);
        newPlaylist.setUser(user);
        playlistDao.save(newPlaylist);
        return "redirect:/profile";
    }
}
