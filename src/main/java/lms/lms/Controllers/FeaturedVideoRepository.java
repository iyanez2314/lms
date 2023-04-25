package lms.lms.Controllers;

import lms.lms.Models.FeaturedVideos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeaturedVideoRepository  extends JpaRepository<FeaturedVideos, Long> {
}
