package lms.lms.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "languages")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long language_id;

    @Column(nullable = false, length = 55)
    private String language_name;

    public Language() {
    }

    public Language(long language_id, String language_name) {
        this.language_id = language_id;
        this.language_name = language_name;
    }

    public long getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(long language_id) {
        this.language_id = language_id;
    }

    public String getLanguage_name() {
        return language_name;
    }

    public void setLanguage_name(String language_name) {
        this.language_name = language_name;
    }
}
