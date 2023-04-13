package lms.lms.Controllers.Api;


import lms.lms.Controllers.UserRepository;
import lms.lms.Controllers.VideoRepository;
import lms.lms.Models.User;
import lms.lms.Models.Video;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestApi {
    private final VideoRepository videoDao;
    private final UserRepository userDao;

    public TestApi(VideoRepository videoDao, UserRepository userDao) {
        this.videoDao = videoDao;
        this.userDao = userDao;
    }



    @GetMapping("/users")
    public List<User> showUsers(){
        return userDao.findAll();
    }

    @PostMapping("/vid")
    public Video saveVideo(@Validated @RequestBody Video video){
        System.out.println("here");
        System.out.println(video);
        return videoDao.save(video);
    }
}
