package pl.kowalewski.warehouserecords.user.Role;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer>{
    List<Role> findAll();
    Optional<Role> findById(Integer id);
}
