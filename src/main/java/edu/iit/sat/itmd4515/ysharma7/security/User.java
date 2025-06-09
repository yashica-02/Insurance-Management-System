package edu.iit.sat.itmd4515.ysharma7.security;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a system user with credentials and associated security groups.  
 * @author yashica
 */
@Entity
@Table(name = "SEC_USER")
@EntityListeners(HashTheUserPasswordListener.class)
public class User {
    
    @Id
    @NotBlank(message = "Please enter a username")
    private String username;
    @NotBlank(message = "Please enter a password")
    private String password;
    
    @ManyToMany
    @JoinTable(name ="SEC_USER_GROUPS",
            joinColumns = @JoinColumn(name="USERNAME"),
            inverseJoinColumns = @JoinColumn(name = "GROUPNAME"))
    private List<Group> groups = new ArrayList();

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public void addToGroup(Group g){
        this.groups.add(g);
        g.getUsers().add(this);
    }

    public void removeFromGroup(Group g){
        this.groups.remove(g);
        g.getUsers().remove(this);
    }
   
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public List<Group> getGroups() {
        return groups;
    }
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.username);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        return Objects.equals(this.username, other.username);
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", groups=" + groups + '}';
    }
}