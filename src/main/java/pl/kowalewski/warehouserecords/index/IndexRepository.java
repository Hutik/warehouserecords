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
    List<Index> findByCodeIgnoreCaseOrderByIndex(String code);
    @EntityGraph(attributePaths = {"category"})
    List<Index> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrderByIndex(String name, String description);
    @EntityGraph(attributePaths = {"category"})
    List<Index> findByNameContainingIgnoreCaseOrderByIndex(String name);
    @EntityGraph(attributePaths = {"category"})
    List<Index> findByDescriptionContainingIgnoreCaseOrderByIndex(String description);

    // Category
    @EntityGraph(attributePaths = {"category"})
    List<Index> findByCodeIgnoreCaseAndCategoryIdOrderByIndex(String code, Long categoryId);
    @EntityGraph(attributePaths = {"category"})
    List<Index> findByCategoryIdOrderByIndex(Long categoryId);
    @EntityGraph(attributePaths = {"category"})
    List<Index> findByNameContainingIgnoreCaseAndCategoryIdOrderByIndex(String name, Long categoryId);
    @EntityGraph(attributePaths = {"category"})
    List<Index> findByDescriptionContainingIgnoreCaseAndCategoryIdOrderByIndex(String description, Long categoryId);
}
