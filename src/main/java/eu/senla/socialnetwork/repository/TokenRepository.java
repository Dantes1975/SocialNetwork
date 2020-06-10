package eu.senla.socialnetwork.repository;

import eu.senla.socialnetwork.model.Token;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<Token, Long> {
    Token findByUserId(Long id);
}
