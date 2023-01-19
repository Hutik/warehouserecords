package pl.kowalewski.warehouserecords.index.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryService {
    
    @Autowired
    CategoryRepository repo;

    @GetMapping
    ResponseEntity<List<Category>> findAll(){
        return ResponseEntity.ok(repo.findAll());
    }
}
