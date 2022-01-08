package pl.kowalewski.warehouserecords.index;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IndexRepository extends JpaRepository<Index, Long> {
    @EntityGraph(attributePaths = {"category"})
    List<Index> findAllByOrderByIndex();
    @EntityGraph(attributePaths = {"category"})
    List<Index> findByIndex(@Param("index") Long index);

    @EntityGraph(attributePaths = {"category"})
    List<Index> findByCodeIgnoreCase(String code);
    @EntityGraph(attributePaths = {"category"})
    List<Index> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);
    @EntityGraph(attributePaths = {"category"})
    List<Index> findByNameContainingIgnoreCase(String name);
}
