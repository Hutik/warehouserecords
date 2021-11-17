package pl.kowalewski.warehouserecords.index;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
@RestController
public interface IndexRepository extends JpaRepository<Index, Long> {
    List<Index> findAll();
}
