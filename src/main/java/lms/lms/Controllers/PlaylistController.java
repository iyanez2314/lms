package lms.lms.Controllers;


import lms.lms.Models.Playlist;
import lms.lms.Models.PlaylistVideo;
import lms.lms.Models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PlaylistController {

    private final PlaylistRepository playlistDao;
    private final UserRepository userDao;

    public PlaylistController(PlaylistRepository playlistDao, UserRepository userDao){
        this.playlistDao = playlistDao;
        this.userDao = userDao;
    }

    @GetMapping("/playlistview/{id}")
    public String showPlaylistView(@PathVariable("id") Long id, Model model){
        Playlist playlist = playlistDao.findById(id).get();
        List<PlaylistVideo> playlistVideos = playlist.getPlaylistVideos();
        model.addAttribute("videos", playlistVideos);
        model.addAttribute("playlist", playlist);
        return "playlist/show";
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
