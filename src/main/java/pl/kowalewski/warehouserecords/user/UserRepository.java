package pl.kowalewski.warehouserecords.user;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
    @EntityGraph(attributePaths = {"roles"})
    List<User> findAll();
    @EntityGraph(attributePaths = {"roles"})
    User findByUsername(String username);
    @EntityGraph(attributePaths = {"roles"})
    User getById(Long id);

    boolean existsByUsernameOrEmail(String username, String email);

    // Filters
    @EntityGraph(attributePaths = {"roles"})
    List<User> findByRolesIdIn(List<Integer> categoryIds);
    @EntityGraph(attributePaths = {"roles"})
    List<User> findByRolesIdInAndUsernameIgnoreCaseAndEmailIgnoreCase(List<Integer> roles, String username, String email);
    @EntityGraph(attributePaths = {"roles"})
    List<User> findByRolesIdInOrderById(List<Integer> roles);
    @EntityGraph(attributePaths = {"roles"})
    List<User> findByRolesIdInAndLastNameIgnoreCaseOrderById(List<Integer> roles, String lastName);
    @EntityGraph(attributePaths = {"roles"})
    List<User> findByRolesIdInAndNameIgnoreCaseOrderById(List<Integer> roles, String name);
    @EntityGraph(attributePaths = {"roles"})
    List<User> findByRolesIdInAndLastNameIgnoreCaseAndNameIgnoreCaseOrderById(List<Integer> roles, String lastName, String name);
    @EntityGraph(attributePaths = {"roles"})
    List<User> findByRolesIdInAndEmailIgnoreCase(List<Integer> roles, String email);
    @EntityGraph(attributePaths = {"roles"})
    List<User> findByRolesIdInAndEmailIgnoreCaseAndLastNameIgnoreCaseOrderById(List<Integer> roles, String email, String lastName);
    @EntityGraph(attributePaths = {"roles"})
    List<User> findByRolesIdInAndEmailIgnoreCaseAndNameIgnoreCaseOrderById(List<Integer> roles, String email, String name);
    @EntityGraph(attributePaths = {"roles"})
    List<User> findByRolesIdInAndEmailIgnoreCaseAndLastNameIgnoreCaseAndNameIgnoreCaseOrderById(List<Integer> roles, String email, String lastName, String name);
    @EntityGraph(attributePaths = {"roles"})
    List<User> findByRolesIdInAndUsernameIgnoreCase(List<Integer> roles, String username);
    @EntityGraph(attributePaths = {"roles"})
    List<User> findByRolesIdInAndUsernameIgnoreCaseAndLastNameIgnoreCaseOrderById(List<Integer> roles, String username, String lastName);
    @EntityGraph(attributePaths = {"roles"})
    List<User> findByRolesIdInAndUsernameIgnoreCaseAndNameIgnoreCaseOrderById(List<Integer> roles, String username, String name);
    @EntityGraph(attributePaths = {"roles"})
    List<User> findByRolesIdInAndUsernameIgnoreCaseAndLastNameIgnoreCaseAndNameIgnoreCaseOrderById(List<Integer> roles, String username, String lastName, String name);
    @EntityGraph(attributePaths = {"roles"})
    List<User> findByRolesIdInAndUsernameIgnoreCaseAndEmailIgnoreCaseAndLastNameIgnoreCaseOrderById(List<Integer> roles, String username, String email, String lastName);
    @EntityGraph(attributePaths = {"roles"})
    List<User> findByRolesIdInAndUsernameIgnoreCaseAndEmailIgnoreCaseAndNameIgnoreCaseOrderById(List<Integer> roles, String username, String email, String name);
    @EntityGraph(attributePaths = {"roles"})
    List<User> findByRolesIdInAndUsernameIgnoreCaseAndEmailIgnoreCaseAndLastNameIgnoreCaseAndNameIgnoreCaseOrderById(List<Integer> roles, String username, String email, String lastName, String name);
    @EntityGraph(attributePaths = {"roles"})
    List<User> findByLastNameIgnoreCaseOrderById(String lastName);
    @EntityGraph(attributePaths = {"roles"})
    List<User> findByNameIgnoreCaseOrderById(String name);
    @EntityGraph(attributePaths = {"roles"})
    List<User> findByLastNameIgnoreCaseAndNameIgnoreCaseOrderById(String lastName, String name);
    @EntityGraph(attributePaths = {"roles"})
    List<User> findByEmailIgnoreCase(String email);
    @EntityGraph(attributePaths = {"roles"})
    List<User> findByEmailIgnoreCaseAndLastNameIgnoreCaseOrderById(String email, String lastName);
    @EntityGraph(attributePaths = {"roles"})
    List<User> findByEmailIgnoreCaseAndNameIgnoreCaseOrderById(String email, String name);
    @EntityGraph(attributePaths = {"roles"})
    List<User> findByEmailIgnoreCaseAndLastNameIgnoreCaseAndNameIgnoreCaseOrderById(String email, String lastName, String name);
    @EntityGraph(attributePaths = {"roles"})
    List<User> findByUsernameIgnoreCase(String username);
    @EntityGraph(attributePaths = {"roles"})
    List<User> findByUsernameIgnoreCaseAndLastNameIgnoreCaseOrderById(String username, String lastName);
    @EntityGraph(attributePaths = {"roles"})
    List<User> findByUsernameIgnoreCaseAndNameIgnoreCaseOrderById(String username, String name);
    @EntityGraph(attributePaths = {"roles"})
    List<User> findByUsernameIgnoreCaseAndLastNameIgnoreCaseAndNameIgnoreCaseOrderById(String username, String lastName, String name);
    @EntityGraph(attributePaths = {"roles"})
    List<User> findByUsernameIgnoreCaseAndEmailIgnoreCase(String username, String email);
    @EntityGraph(attributePaths = {"roles"})
    List<User> findByUsernameIgnoreCaseAndEmailIgnoreCaseAndLastNameIgnoreCaseOrderById(String username, String email, String lastName);
    @EntityGraph(attributePaths = {"roles"})
    List<User> findByUsernameIgnoreCaseAndEmailIgnoreCaseAndNameIgnoreCaseOrderById(String username, String email, String name);
    @EntityGraph(attributePaths = {"roles"})
    List<User> findByUsernameIgnoreCaseAndEmailIgnoreCaseAndLastNameIgnoreCaseAndNameIgnoreCaseOrderById(String username, String email, String lastName, String name);
}
