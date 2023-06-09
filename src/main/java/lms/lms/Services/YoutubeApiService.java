package lms.lms.Services;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lms.lms.Controllers.UserRepository;
import lms.lms.Controllers.VideoRepository;
import lms.lms.Models.User;
import lms.lms.Models.Video;
import okhttp3.*;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class YoutubeApiService {

    private VideoRepository videoDao;
    private UserRepository userDao;

    public YoutubeApiService(VideoRepository videoDao, UserRepository userDao) {
        this.videoDao = videoDao;
        this.userDao = userDao;
    }

    private static final String YOUTUBE_API = "https://www.googleapis.com/youtube/v3/search?part=snippet&type=28&maxResults=50&q=react+javascript+python+node+java&key=AIzaSyCgPajuAVH3c8gRzI5AxVbLhyBRUwz-BQE";


    public static class YoutubeVideo {
        private String videoId;
        private String title;
        private String url;
        private String thumbnail;

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getVideoId() {
            return videoId;
        }

        public void setVideoId(String videoId) {
            this.videoId = videoId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class YoutubeResponse {

        @JsonProperty("items")
        private List<Item> items;

        public List<Item> getItems() {
            return items;
        }

        public void setItems(List<Item> items) {
            this.items = items;
        }

        public static class Item {
            @JsonProperty("id")
            private Id id;

            @JsonProperty("snippet")
            private Snippet snippet;

            public Id getId() {
                return id;
            }

            public void setId(Id id) {
                this.id = id;
            }

            public Snippet getSnippet() {
                return snippet;
            }

            public void setSnippet(Snippet snippet) {
                this.snippet = snippet;
            }
        }

        public static class Id {
            @JsonProperty("videoId")
            private String videoId;

            public String getVideoId() {
                return videoId;
            }

            public void setVideoId(String videoId) {
                this.videoId = videoId;
            }
        }

        public static class Snippet {

            @JsonProperty("thumbnails")
            private Thumbnails thumbnails;

            @JsonProperty("title")
            private String title;

            public String getTitle() {
                return title;
            }

            public Thumbnails getThumbnails() {
                return thumbnails;
            }

            public void setThumbnails(Thumbnails thumbnails) {
                this.thumbnails = thumbnails;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class Thumbnails {
            @JsonProperty("high")
            private Thumbnail defaultThumbnail;

            public Thumbnail getDefaultThumbnail() {
                return defaultThumbnail;
            }

            public void setDefaultThumbnail(Thumbnail defaultThumbnail) {
                this.defaultThumbnail = defaultThumbnail;
            }
        }

        public static class Thumbnail {
            @JsonProperty("url")
            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

    }
    @Bean
    private OkHttpClient getOkHttpClient(){
        return new OkHttpClient();
    }

    private List<YoutubeVideo> makeRequest() throws IOException {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = loggedInUser.getId();
        User user = userDao.findById(userId).get();
        OkHttpClient client = getOkHttpClient();
        String youtubeApiUrl = getYouTubeApiUrl(user);
        System.out.println("YouTube API URL: " + youtubeApiUrl);
        Request request = new Request.Builder()
                .url(youtubeApiUrl)
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            String responseBody = response.body().string();
            System.out.println("RAW JSON response: " + responseBody);
            YoutubeResponse youtubeResponse = objectMapper.readValue(responseBody, YoutubeResponse.class);
            List<YoutubeVideo> youtubeVideos = new ArrayList<>();

            for (YoutubeResponse.Item item : youtubeResponse.getItems()) {
                YoutubeVideo video = new YoutubeVideo();
                video.setVideoId(item.getId().getVideoId());
                video.setTitle(item.getSnippet().getTitle());
                video.setUrl("https://www.youtube.com/watch?v=" + item.getId().getVideoId());
                video.setThumbnail(item.getSnippet().getThumbnails().getDefaultThumbnail().getUrl());
                youtubeVideos.add(video);
            }
            return youtubeVideos;
        }
    }

        public List<Video> getYoutubeVideos() {
            try {
                List<YoutubeVideo> youtubeVideos = makeRequest();
                List<Video> videos = new ArrayList<>();
                for (YoutubeVideo youtubeVideo : youtubeVideos) {
                    Video video = new Video(youtubeVideo.getTitle(), youtubeVideo.getUrl(), youtubeVideo.getThumbnail());
                    videos.add(video);
                }
                return videos;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

    private String getYouTubeApiUrl(User user){
        String preferredLanguages = user.getPreferredLanguagesQueryString();
        if(preferredLanguages.isEmpty()){
            return YOUTUBE_API;
        }
        return "https://www.googleapis.com/youtube/v3/search?part=snippet&type=28&maxResults=50&q=" + preferredLanguages + "&key=AIzaSyCgPajuAVH3c8gRzI5AxVbLhyBRUwz-BQE";
    }
}
