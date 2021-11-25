package pl.kowalewski.warehouserecords.index;

import java.util.List;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/indexes")
public class IndexService {
    @Autowired IndexRepository repo;

    Logger logger = LoggerFactory.getLogger(IndexService.class);
    
    @GetMapping
    ResponseEntity<List<Index>> findAll(){
        return ResponseEntity.ok(repo.findAllByOrderByIndex());
    }

    @GetMapping("/{index}")
    ResponseEntity<List<Index>> findByIndex(@PathVariable Long index){
        return ResponseEntity.ok(repo.findByIndex(index));
    }

    // var code = document.getElementById('inputCode').value;
    // var name = document.getElementById('inputName').value;
    // var description = document.getElementById('inputDescription').value;
    // var category = document.getElementById('selectCategory').value;


    @GetMapping(params={"code", "name", "description", "categoryId"})
    ResponseEntity<List<Index>> findByParams(@PathParam("code") String code, @PathParam("name") String name, @PathParam("description") String description, @PathParam("categoryId") Integer categoryId){
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/{id}")
    ResponseEntity<Index> update(@PathParam("id") Long id, Index index){
        logger.info("Patch request for id: "+id);
        return ResponseEntity.badRequest().build();
    }

}
