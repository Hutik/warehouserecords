package pl.kowalewski.warehouserecords.user;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
    @EntityGraph(attributePaths = {"roles"})
    User findByUsername(String username);
    @EntityGraph(attributePaths = {"roles"})
    User getById(Long id);

    boolean existsByUsernameOrEmail(String username, String email);
}
