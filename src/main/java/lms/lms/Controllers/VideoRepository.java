package lms.lms.Controllers;

import lms.lms.Models.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
}
