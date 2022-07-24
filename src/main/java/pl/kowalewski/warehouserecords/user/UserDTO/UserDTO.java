package pl.kowalewski.warehouserecords.user.UserDTO;

import java.util.HashSet;
import java.util.Set;

import pl.kowalewski.warehouserecords.user.User;
import pl.kowalewski.warehouserecords.user.Role.Role;

public class UserDTO {
    Long id;
    String name;
    String lastName;
    String username;
    String email;
    Set<Role> roles = new HashSet<Role>();

    public UserDTO(){};

    public UserDTO(User u){
        this.id=u.getId();
        this.name=u.getName();
        this.lastName=u.getLastName();
        this.username=u.getUsername();
        this.email=u.getEmail();
        this.roles=u.getRoles();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
