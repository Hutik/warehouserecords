package pl.kowalewski.warehouserecords.user;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
@RestController
public interface UserRepository extends JpaRepository<User, Long>{
    @EntityGraph(attributePaths = {"roles"})
    User findByUsername(String username);
    @EntityGraph(attributePaths = {"roles"})
    User getById(Long id);
}
