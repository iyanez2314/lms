package lms.lms.Controllers;


import lms.lms.Models.Playlist;
import lms.lms.Models.PlaylistVideo;
import lms.lms.Models.User;
import lms.lms.Models.Video;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

import static java.lang.Long.parseLong;

@Controller
public class PlaylistController {
    private final PlaylistRepository playlistDao;
    private final UserRepository userDao;
    private final PlaylistVideoRepository playlistVideoDao;
    private final VideoRepository videoDao;
    public PlaylistController(PlaylistRepository playlistDao, UserRepository userDao, PlaylistVideoRepository playlistVideoDao, VideoRepository videoDao){
        this.playlistDao = playlistDao;
        this.userDao = userDao;
        this.playlistVideoDao = playlistVideoDao;
        this.videoDao = videoDao;
    }


    // Displays all the videos associated with the specified id
    @GetMapping("/playlistview/{id}")
    public String showPlaylistView(@PathVariable("id") Long id, Model model){
        Playlist playlist = playlistDao.findById(id).get();
        List<PlaylistVideo> playlistVideos = playlist.getPlaylistVideos();
        model.addAttribute("videos", playlistVideos);
        model.addAttribute("playlist", playlist);
        return "playlist/show";
    }

    // Post mapping to edit any videos the user wants to remove
    @PostMapping("/playlistview/{id}")
    public String editPlaylistView(@PathVariable("id") Long id, @RequestParam("videoId") Long videoId, Model model){
    Playlist playlist = playlistDao.findById(id).get();
    Video video = videoDao.findById(videoId).get();
    PlaylistVideo playlistVideo = playlistVideoDao.findAll().stream()
            .filter(pv -> pv.getPlaylist().equals(playlist) && pv.getVideo().equals(video))
            .findFirst()
            .orElse(null);

        if (playlistVideo != null) {
            playlistVideoDao.delete(playlistVideo);
        }
        return "redirect:/playlistview/" + id;
    }


    @PostMapping("/playlist/{id}/edit")
    public String editPlaylistName(@PathVariable("id") Long id, @RequestParam("playlist-name-change") String playlistNameChange){
        Playlist playlist = playlistDao.findById(id).get();
        if(playlistNameChange != null){
            playlist.setPlayListName(playlistNameChange);
            playlistDao.save(playlist);
        }
        return "redirect:/playlistview/" + id;
    }


    // Post mapping to create a playlist
    @PostMapping("/playlist/create")
    public String playlistCreation(@RequestParam(name = "playlistname") String playlistname){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = loggedInUser.getId();
        User user = userDao.findById(userId).get();
        Playlist newPlaylist = new Playlist(playlistname);
        newPlaylist.setUser(user);
        playlistDao.save(newPlaylist);
        return "redirect:/profile";
    }

    @PostMapping("/delete-playlist/{id}")
    public String deletePlaylist(@PathVariable Long id) {
        try {
            Playlist playlist = playlistDao.findById(id).get();
            List<PlaylistVideo> allPlayListVideos = playlistVideoDao.findAll();

            for (PlaylistVideo playlistVideo : allPlayListVideos) {
                Long playlistId = playlistVideo.getPlaylist().getPlaylist_id();

                if (playlistId.equals(id)) {
                    playlistVideoDao.delete(playlistVideo);
                    playlistDao.delete(playlist);
                    return "redirect:/profile";
                }
            }

            // If no associations are found, you can still delete the playlist
            playlistDao.delete(playlist);
            return "redirect:/profile";
        } catch (NoSuchElementException e) {
            // Handle the case when the playlist is not found
            System.out.println("Playlist not found");
            return "ErrorViews/error";
        }
    }
}
