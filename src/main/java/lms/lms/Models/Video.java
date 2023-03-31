package lms.lms.Models;


import jakarta.persistence.*;

@Entity
@Table(name = "videos")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long video_id;

    @Column(nullable = false, length = 255)
    private String video_title;

    @Column(nullable = false, length = 255)
    private String video_url;

    public Video() {
    }

    public Video(long video_id, String video_title, String video_url) {
        this.video_id = video_id;
        this.video_title = video_title;
        this.video_url = video_url;
    }

    public long getVideo_id() {
        return video_id;
    }

    public void setVideo_id(long video_id) {
        this.video_id = video_id;
    }

    public String getVideo_title() {
        return video_title;
    }

    public void setVideo_title(String video_title) {
        this.video_title = video_title;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }
}
