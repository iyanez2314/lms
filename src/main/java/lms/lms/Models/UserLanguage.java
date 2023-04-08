package lms.lms.Models;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "userlanguages")
public class UserLanguage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userlanguesId;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    // Constructors
    public UserLanguage() {
    }


    public UserLanguage(Long userlanguesId, Language language, User user) {
        this.userlanguesId = userlanguesId;
        this.language = language;
        this.user = user;
    }

    public UserLanguage(Language language, User user) {
        this.language = language;
        this.user = user;
    }

    //    Getters and Setters
    public Long getUserlanguesId() {
        return userlanguesId;
    }

    public void setUserlanguesId(Long userlanguesId) {
        this.userlanguesId = userlanguesId;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
