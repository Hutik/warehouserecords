package pl.kowalewski.warehouserecords.index;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
@RestController
public interface IndexRepository extends JpaRepository<Index, Long> {
    @EntityGraph(attributePaths = {"category"})
    List<Index> findAll();
    @EntityGraph(attributePaths = {"category"})
    List<Index> findByIndex(@Param("index") Long index);
}
