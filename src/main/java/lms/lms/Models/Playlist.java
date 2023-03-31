package lms.lms.Models;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "playlists")
public class Playlist {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long playlist_id;

    @Column(nullable = false, length = 55)
    private String playListName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "playlist")
    private List<PlaylistVideo> playlistVideos;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    // Constructors
    public Playlist() {
    }

    public Playlist(String playListName) {
        this.playListName = playListName;
    }

    public Playlist(String playListName, User user) {
        this.playListName = playListName;
        this.user = user;
    }

    public Playlist(long playlist_id, String playListName, User user) {
        this.playlist_id = playlist_id;
        this.playListName = playListName;
        this.user = user;
    }

    // Getters and Setters
    public long getPlaylist_id() {
        return playlist_id;
    }

    public void setPlaylist_id(long playlist_id) {
        this.playlist_id = playlist_id;
    }

    public String getPlayListName() {
        return playListName;
    }

    public void setPlayListName(String playListName) {
        this.playListName = playListName;
    }
}
