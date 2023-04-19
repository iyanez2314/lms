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

    @PostMapping("profilepic-upload")
    public ExampleData saveProfilePic(@RequestBody ExampleData requestData){
        Long userId = requestData.userId;
        User user = userDao.findById(userId).get();
        user.setProfilePic_url(requestData.message);
        userDao.save(user);
        System.out.println("Received request data: " + requestData);
        return requestData;
    }


    public static class ExampleData {
        private String message;
        private Long userId;


        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return "ExampleData{" +
                    "message='" + message + '\'' +
                    '}';
        }
    }
}
