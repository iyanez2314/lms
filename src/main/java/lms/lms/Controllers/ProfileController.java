package lms.lms.Controllers;


import lms.lms.Models.Playlist;
import lms.lms.Models.PlaylistVideo;
import lms.lms.Models.User;
import lms.lms.Models.Video;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProfileController {

    private final UserRepository userDao;
    private final PlaylistRepository playlistDao;

    private final VideoRepository videoDao;

    private final PlaylistVideoRepository playlistVideoDao;

    public ProfileController(UserRepository userDao, PlaylistRepository playlistDao, VideoRepository videoDao, PlaylistVideoRepository playlistVideoDao) {
        this.userDao = userDao;
        this.playlistDao = playlistDao;
        this.videoDao = videoDao;
        this.playlistVideoDao = playlistVideoDao;
    }

    @GetMapping("/profile")
    public String HomeController(Model model){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = loggedInUser.getId();
        User user = userDao.findById(userId).get();
        List<Video> videos = videoDao.findAll();
        List<Playlist> usersPlayList = user.getPlaylists();
        int usersPlaylistCount = countUsersPlaylist(user);
        model.addAttribute("playlists", usersPlayList);
        model.addAttribute("videos", videos);
        model.addAttribute("plalistcount", usersPlaylistCount);
        model.addAttribute("user", user);
        return "Profile/Profile";
    }

    private int countUsersPlaylist(User user){
        int playlistCount = 0;
        List<Playlist> usersPlaylists = user.getPlaylists();
        if(usersPlaylists == null){
            return 0;
        }
        for( Playlist playlist : usersPlaylists){
            playlistCount++;
        }
        return playlistCount;
    }


    public static void main(String[] args) {

    }
}
