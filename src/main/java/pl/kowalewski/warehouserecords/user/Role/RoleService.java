package pl.kowalewski.warehouserecords.user.Role;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleService {
    @Autowired RoleRepository repo;

    Logger logger = LoggerFactory.getLogger(RoleService.class);

    @GetMapping
    public ResponseEntity<List<Role>> getRoles(){
        return ResponseEntity.ok(repo.findAll());
    }


}
