package pl.kowalewski.warehouserecords.user;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.websocket.server.PathParam;

import java.nio.file.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import pl.kowalewski.warehouserecords.user.Role.Role;
import pl.kowalewski.warehouserecords.user.Role.RoleRepository;
import pl.kowalewski.warehouserecords.user.UserDTO.UserDTO;

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

    @GetMapping("/currentUser")
    public ResponseEntity<UserDTO> getCurrentUser(Authentication auth){
        return ResponseEntity.ok(new UserDTO(((MyUserPrincipal) auth.getPrincipal()).getUser()));
    }

    @GetMapping("/currentUser/avatar")
    public ResponseEntity<byte[]> getCurrentUserAvatar(Authentication auth) throws IOException{
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-type", MediaType.IMAGE_JPEG_VALUE);

        byte[] avatar = ((MyUserPrincipal) auth.getPrincipal()).getAvatar();
        if(avatar==null) {
            File f = new File("default_user.webp");
            if(!f.exists()) f.createNewFile();
            avatar=Files.readAllBytes(f.toPath());
        }

        return ResponseEntity.ok().headers(headers).body(avatar);
    }

    @PostMapping("/currentUser/avatar")
    public ResponseEntity<HttpStatus> uploadAvatar(Authentication auth, @RequestParam("file") MultipartFile avatar){
        User user = ((MyUserPrincipal) auth.getPrincipal()).getUser();
        try {
            user.setAvatar(avatar.getBytes());
            userRepository.save(user);
        } catch (IOException e) {
            ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/currentUser")
    public ResponseEntity<HttpStatus> updateUser(@RequestParam MultiValueMap<String, Object> values, Authentication auth){

        // logger.info(values.toString());

        User u = ((MyUserPrincipal) auth.getPrincipal()).getUser();
        
        String name=values.get("name").get(0).toString();
        String lastName=values.get("lastName").get(0).toString();
        
        if(!name.equals(""))
            u.setName(name);

        if(!lastName.equals(""))
            u.setLastName(lastName);

        userRepository.save(u);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping
    public ResponseEntity<Long> addUser(@RequestBody MultiValueMap<String, Object> values){

        User u = new User();
        u.setEmail(values.get("email").get(0).toString());
        u.setName(values.get("name").get(0).toString());
        u.setLastName(values.get("lastName").get(0).toString());
        u.setUsername(values.get("userName").get(0).toString());
        
        if(userRepository.existsByUsernameOrEmail(u.getUsername(), u.getEmail())) return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).build();

        u.setPassword(BCrypt.hashpw(values.get("password").get(0).toString(), BCrypt.gensalt()));
        
        Set<Role> roles = new HashSet<Role>();
        values.get("roles").forEach(roleId -> {
            roles.add(roleRepository.getById(Integer.valueOf(roleId.toString())));
        });
        
        u.setRoles(roles);

        return ResponseEntity.ok(userRepository.save(u).getId());
    }

    @PostMapping("/{id}/avatar")
    public ResponseEntity<HttpStatus> uploadAvatar(@PathVariable("id") Long id, @RequestParam("file") MultipartFile avatar){
        User user = userRepository.getById(id);
        try {
            user.setAvatar(avatar.getBytes());
            userRepository.save(user);
        } catch (IOException e) {
            ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        ArrayList<UserDTO> list = new ArrayList<UserDTO>();

        userRepository.findAll().forEach(u -> {
           list.add(new UserDTO(u)); 
        });

        return ResponseEntity.ok(list);
    }

    @GetMapping(params = {"username", "name", "lastName", "email"})
    public ResponseEntity<List<UserDTO>> getUsers(@RequestParam(value="username") String username, @RequestParam(value="name") String name, @RequestParam(value="lastName") String lastName, @RequestParam(value="email") String email, @RequestParam(value="roles", defaultValue = "") List<Integer> roles){
        List<User> users = new ArrayList<User>();
        
        if(roles.isEmpty()){
            if(username.equals("")&&email.equals("")){
                if(lastName.equals("")&&name.equals("")){
                    return getAllUsers();
                }else if(!lastName.equals("")&&name.equals("")){
                    users = userRepository.findByLastNameIgnoreCaseOrderById(lastName);
                }else if(lastName.equals("")&&!name.equals("")){
                    users = userRepository.findByNameIgnoreCaseOrderById(name);
                }else{
                    users = userRepository.findByLastNameIgnoreCaseAndNameIgnoreCaseOrderById(lastName, name);
                }
            }else if(username.equals("")&&!email.equals("")){
                if(lastName.equals("")&&name.equals("")){
                    users = userRepository.findByEmailIgnoreCase(email);
                }else if(!lastName.equals("")&&name.equals("")){
                    users = userRepository.findByEmailIgnoreCaseAndLastNameIgnoreCaseOrderById(email, lastName);
                }else if(lastName.equals("")&&!name.equals("")){
                    users = userRepository.findByEmailIgnoreCaseAndNameIgnoreCaseOrderById(email, name);
                }else{
                    users = userRepository.findByEmailIgnoreCaseAndLastNameIgnoreCaseAndNameIgnoreCaseOrderById(email, lastName, name);
                }
            }else if(!username.equals("")&&email.equals("")){
                if(lastName.equals("")&&name.equals("")){
                    users = userRepository.findByUsernameIgnoreCase(username);
                }else if(!lastName.equals("")&&name.equals("")){
                    users = userRepository.findByUsernameIgnoreCaseAndLastNameIgnoreCaseOrderById(username, lastName);
                }else if(lastName.equals("")&&!name.equals("")){
                    users = userRepository.findByUsernameIgnoreCaseAndNameIgnoreCaseOrderById(username, name);
                }else{
                    users = userRepository.findByUsernameIgnoreCaseAndLastNameIgnoreCaseAndNameIgnoreCaseOrderById(username, lastName, name);
                }
            }else{
                if(lastName.equals("")&&name.equals("")){
                    users = userRepository.findByUsernameIgnoreCaseAndEmailIgnoreCase(username, email);
                }else if(!lastName.equals("")&&name.equals("")){
                    users = userRepository.findByUsernameIgnoreCaseAndEmailIgnoreCaseAndLastNameIgnoreCaseOrderById(username, email, lastName);
                }else if(lastName.equals("")&&!name.equals("")){
                    users = userRepository.findByUsernameIgnoreCaseAndEmailIgnoreCaseAndNameIgnoreCaseOrderById(username, email, name);
                }else{
                    users = userRepository.findByUsernameIgnoreCaseAndEmailIgnoreCaseAndLastNameIgnoreCaseAndNameIgnoreCaseOrderById(username, email, lastName, name);
                }
            }
        }else{
            if(username.equals("")&&email.equals("")){
                if(lastName.equals("")&&name.equals("")){
                    users = userRepository.findByRolesIdInOrderById(roles);
                }else if(!lastName.equals("")&&name.equals("")){
                    users = userRepository.findByRolesIdInAndLastNameIgnoreCaseOrderById(roles, lastName);
                }else if(lastName.equals("")&&!name.equals("")){
                    users = userRepository.findByRolesIdInAndNameIgnoreCaseOrderById(roles, name);
                }else{
                    users = userRepository.findByRolesIdInAndLastNameIgnoreCaseAndNameIgnoreCaseOrderById(roles, lastName, name);
                }
            }else if(username.equals("")&&!email.equals("")){
                if(lastName.equals("")&&name.equals("")){
                    users = userRepository.findByRolesIdInAndEmailIgnoreCase(roles, email);
                }else if(!lastName.equals("")&&name.equals("")){
                    users = userRepository.findByRolesIdInAndEmailIgnoreCaseAndLastNameIgnoreCaseOrderById(roles, email, lastName);
                }else if(lastName.equals("")&&!name.equals("")){
                    users = userRepository.findByRolesIdInAndEmailIgnoreCaseAndNameIgnoreCaseOrderById(roles, email, name);
                }else{
                    users = userRepository.findByRolesIdInAndEmailIgnoreCaseAndLastNameIgnoreCaseAndNameIgnoreCaseOrderById(roles, email, lastName, name);
                }
            }else if(!username.equals("")&&email.equals("")){
                if(lastName.equals("")&&name.equals("")){
                    users = userRepository.findByRolesIdInAndUsernameIgnoreCase(roles, username);
                }else if(!lastName.equals("")&&name.equals("")){
                    users = userRepository.findByRolesIdInAndUsernameIgnoreCaseAndLastNameIgnoreCaseOrderById(roles, username, lastName);
                }else if(lastName.equals("")&&!name.equals("")){
                    users = userRepository.findByRolesIdInAndUsernameIgnoreCaseAndNameIgnoreCaseOrderById(roles, username, name);
                }else{
                    users = userRepository.findByRolesIdInAndUsernameIgnoreCaseAndLastNameIgnoreCaseAndNameIgnoreCaseOrderById(roles, username, lastName, name);
                }
            }else{
                if(lastName.equals("")&&name.equals("")){
                    users = userRepository.findByRolesIdInAndUsernameIgnoreCaseAndEmailIgnoreCase(roles, username, email);
                }else if(!lastName.equals("")&&name.equals("")){
                    users = userRepository.findByRolesIdInAndUsernameIgnoreCaseAndEmailIgnoreCaseAndLastNameIgnoreCaseOrderById(roles, username, email, lastName);
                }else if(lastName.equals("")&&!name.equals("")){
                    users = userRepository.findByRolesIdInAndUsernameIgnoreCaseAndEmailIgnoreCaseAndNameIgnoreCaseOrderById(roles, username, email, name);
                }else{
                    users = userRepository.findByRolesIdInAndUsernameIgnoreCaseAndEmailIgnoreCaseAndLastNameIgnoreCaseAndNameIgnoreCaseOrderById(roles, username, email, lastName, name);
                }
            }
        }

        ArrayList<UserDTO> list = new ArrayList<UserDTO>();

        users.forEach(u -> {
            list.add(new UserDTO(u)); 
         });

        return ResponseEntity.ok(list);
    }
}
