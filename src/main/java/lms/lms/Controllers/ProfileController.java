package lms.lms.Controllers;


import lms.lms.Models.Playlist;
import lms.lms.Models.PlaylistVideo;
import lms.lms.Models.User;
import lms.lms.Models.Video;
import lms.lms.Services.YoutubeApiService;
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

    private final YoutubeApiService youtubeApiService;

    public ProfileController(UserRepository userDao, PlaylistRepository playlistDao, VideoRepository videoDao, PlaylistVideoRepository playlistVideoDao, YoutubeApiService youtubeApiService) {
        this.userDao = userDao;
        this.playlistDao = playlistDao;
        this.videoDao = videoDao;
        this.playlistVideoDao = playlistVideoDao;
        this.youtubeApiService = youtubeApiService;
    }

    @GetMapping("/profile")
    public String HomeController(Model model){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = loggedInUser.getId();
        User user = userDao.findById(userId).get();
        List<Video> videos = youtubeApiService.getYoutubeVideos();
        System.out.println(videos);
        List<Playlist> usersPlayList = user.getPlaylists();
        int usersPlaylistCount = user.countPlaylist();
        model.addAttribute("playlists", usersPlayList);
        model.addAttribute("videos", videos);
        model.addAttribute("plalistcount", usersPlaylistCount);
        model.addAttribute("user", user);
        return "Profile/Profile";
    }

}
