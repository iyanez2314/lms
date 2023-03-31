package lms.lms.Models;


import jakarta.persistence.*;

@Entity
@Table(name = "userlanguages")
public class UserLanguage {
    // TODO: ADD the relationship for the User.user_id and the languages.id

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userlanguesId;

}
