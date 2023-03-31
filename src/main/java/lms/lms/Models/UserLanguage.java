package lms.lms.Models;


import jakarta.persistence.*;

@Entity
@Table(name = "userlanguages")
public class UserLanguage {
    // TODO: ADD the relationship for the User.user_id and the languages.id


    public UserLanguage(Long userlanguesId, Language language, User user) {
        this.userlanguesId = userlanguesId;
        this.language = language;
        this.user = user;
    }

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userlanguesId;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
