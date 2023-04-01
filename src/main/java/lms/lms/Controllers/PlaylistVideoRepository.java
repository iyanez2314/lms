package lms.lms.Controllers;

import lms.lms.Models.PlaylistVideo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistVideoRepository extends JpaRepository<PlaylistVideo, Long> {
}
