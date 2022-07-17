package pl.kowalewski.warehouserecords.user;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import java.nio.file.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.kowalewski.warehouserecords.user.Role.Role;
import pl.kowalewski.warehouserecords.user.Role.RoleRepository;

@Service
@RequestMapping("/users")
public class UserService implements UserDetailsService {

    ApplicationContext context;

    @Autowired private UserRepository userRepository;
    @Autowired private RoleRepository roleRepository;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(user);
    }

    @GetMapping("/currentUser/avatar")
    public ResponseEntity<byte[]> getCurrentUserAvatar(Authentication auth) throws IOException{
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-type", MediaType.IMAGE_JPEG_VALUE);

        byte[] avatar = ((MyUserPrincipal) auth.getPrincipal()).getAvatar();
        // if(avatar==null) avatar=Files.readAllBytes(context.getResource("classpath:D:\Studia\Praca inżynierska\warehouserecords\src\main\resources\static\images\default_user.webp").getFile().toPath());
        if(avatar==null) {
            File f = new File("src\\main\\resources\\static\\images\\default_user.webp");
            logger.info(String.valueOf(f.exists()));
            avatar=Files.readAllBytes(f.toPath());
        }

        return ResponseEntity.ok().headers(headers).body(avatar);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addUser(@RequestBody MultiValueMap<String, String> values){

        logger.info(values.toString());

        User u = new User();
        u.setEmail(values.get("email").get(0));
        u.setName(values.get("name").get(0));
        u.setLastName(values.get("lastName").get(0));
        u.setUsername(values.get("userName").get(0));
        u.setPassword(BCrypt.hashpw(values.get("password").get(0), BCrypt.gensalt()));
        
        Set<Role> roles = new HashSet<Role>();
        values.get("roles").forEach(roleId -> {
            roles.add(roleRepository.getById(Integer.valueOf(roleId)));
        });
        
        u.setRoles(roles);

        userRepository.save(u);

        return ResponseEntity.ok(HttpStatus.CREATED);
    }

}
