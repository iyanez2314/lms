//package lms.lms.Controllers.Api;
//
//
//import lms.lms.Controllers.VideoRepository;
//import lms.lms.Models.Video;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api")
//public class VideosApi {
//
//    private final VideoRepository videoDao;
//
//    public VideosApi(VideoRepository videoDao) {
//        this.videoDao = videoDao;
//    }
//
//
//    @PostMapping("/videos")
//    public Video saveVideo(@Validated @RequestBody Video video){
//        System.out.println(video);
//        return videoDao.save(video);
//    }
//}
