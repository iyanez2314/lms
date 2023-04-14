package lms.lms.Controllers.Api;


import lms.lms.Controllers.PlaylistRepository;
import lms.lms.Controllers.UserRepository;
import lms.lms.Controllers.VideoRepository;
import lms.lms.Models.Playlist;
import lms.lms.Models.User;
import lms.lms.Models.Video;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestApi {
    private final VideoRepository videoDao;
    private final UserRepository userDao;

    private final PlaylistRepository playlistDao;

    public TestApi(VideoRepository videoDao, UserRepository userDao, PlaylistRepository playlistDao) {
        this.videoDao = videoDao;
        this.userDao = userDao;
        this.playlistDao = playlistDao;
    }

    @GetMapping("/users")
    public List<User> showUsers(){
        return userDao.findAll();
    }

    @PostMapping("/vid")
    public ResponseEntity<?> saveVideo(@Validated @RequestBody Video video){
        Video saveVideo = videoDao.save(video);
       return  new ResponseEntity<>(saveVideo, HttpStatus.CREATED);
    }

//    @PostMapping("/add-video-to-playlist")
//    public ResponseEntity<?> saveVideoToPlaylist(@RequestParam("videoId") Long videoId, @RequestParam("playlistId") Long playlistId){
//        System.out.println("VideoID is =>" + videoId);
//
//        Playlist playlist = playlistDao.findById(playlistId).get();
//        Video video = videoDao.findById(videoId).get();
//
//        System.out.println("the playlist => "+ playlist.getPlayListName());
//        System.out.println("the video name => " + video.getVideo_title());
//
//        // Save the association to the join table
//        // PlaylistVideo newPlaylistVideo = new PlaylistVideo(playlist, video);
//        // playlistVideoDao.save(newPlaylistVideo);
//
//        // Return a response
//        return ResponseEntity.ok().body("Video added to playlist");
//    }
}
