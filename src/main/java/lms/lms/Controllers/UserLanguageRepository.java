package lms.lms.Controllers;

import lms.lms.Models.UserLanguage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLanguageRepository extends JpaRepository<UserLanguage, Long> {
}
