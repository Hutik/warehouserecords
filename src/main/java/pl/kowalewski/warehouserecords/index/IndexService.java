package pl.kowalewski.warehouserecords.index;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/indexes")
public class IndexService {
    @Autowired IndexRepository repo;

    @GetMapping
    ResponseEntity<List<Index>> findAllTodos(){
        return ResponseEntity.ok(repo.findAll());
    }



}
