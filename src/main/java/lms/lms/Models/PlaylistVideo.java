package lms.lms.Models;


import jakarta.persistence.*;

@Entity
@Table(name = "playlist_video")
public class PlaylistVideo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "playlist_id")
    private Playlist playlist;

    @ManyToOne
    @JoinColumn(name = "video_id")
    private Video video;


    public PlaylistVideo() {
    }

    public PlaylistVideo(long id, Playlist playlist, Video video) {
        this.id = id;
        this.playlist = playlist;
        this.video = video;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }
}
