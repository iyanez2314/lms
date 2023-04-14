package lms.lms.Controllers;


import lms.lms.Models.Playlist;
import lms.lms.Models.PlaylistVideo;
import lms.lms.Models.Video;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@Controller
public class PlaylistVideoController {

    private final PlaylistVideoRepository playlistVideoDao;
    private final PlaylistRepository playlistDao;
    private final VideoRepository videoDao;


    public PlaylistVideoController(PlaylistVideoRepository playlistVideoDao, PlaylistRepository playlistDao, VideoRepository videoDao) {
        this.playlistVideoDao = playlistVideoDao;
        this.playlistDao = playlistDao;
        this.videoDao = videoDao;
    }

    @PostMapping("/add-video-to-playlist")
    public String addVideoToPlaylist(@RequestParam("videoId") Long videoId, @RequestParam("playlistId") Long playlistId) {
        Playlist playlist = playlistDao.findById(playlistId).get();
        Video video = videoDao.findById(videoId).get();
        // Save the association to the join table
         PlaylistVideo newPlaylistVideo = new PlaylistVideo(playlist, video);
         playlistVideoDao.save(newPlaylistVideo);
        // Return a response
        return "redirect:/profile";
    }
}
