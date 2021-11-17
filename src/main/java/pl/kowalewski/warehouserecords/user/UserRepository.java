package pl.kowalewski.warehouserecords.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
@RestController
public interface UserRepository extends JpaRepository<User, Long>{
    User findByUsername(String username);
}
