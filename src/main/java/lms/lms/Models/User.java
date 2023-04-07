package lms.lms.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email", "username"})
    }
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 55)
    private String username;

    @Column(nullable = false, length = 55)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;


    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
    private List<Playlist> playlists;


    @OneToMany(cascade =  CascadeType.PERSIST, mappedBy = "user")
    private List<UserLanguage> userLanguages;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public User() {
    }

    public User(User copy) {
        id = copy.id;
        email = copy.email;
        username = copy.username;
        password = copy.password;
    }



    public int countPlaylist(){
        return this.playlists.size();
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public List<UserLanguage> getUserLanguages() {
        return userLanguages;
    }

    public void setUserLanguages(List<UserLanguage> userLanguages) {
        this.userLanguages = userLanguages;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
