package eu.senla.socialnetwork.repository;

import eu.senla.socialnetwork.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
