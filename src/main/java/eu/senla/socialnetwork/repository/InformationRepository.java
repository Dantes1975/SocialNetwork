package eu.senla.socialnetwork.repository;

import eu.senla.socialnetwork.model.Information;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InformationRepository extends JpaRepository<Information, Long> {
}
