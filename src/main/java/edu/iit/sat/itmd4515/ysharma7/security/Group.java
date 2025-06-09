package edu.iit.sat.itmd4515.ysharma7.security;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a security group entity for role-based authorization.  
 * @author yashica
 */
@Entity
@Table(name = "SEC_GROUP")
public class Group {
    
    @Id
    private String groupname;
    private String description;
    
    @ManyToMany(mappedBy = "groups")
    private List<User> users = new ArrayList<>();

    public Group() {
    }

    public Group(String groupname, String description) {
        this.groupname = groupname;
        this.description = description;
    }
    
    public String getGroupname() {
        return groupname;
    }
    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.groupname);
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
        final Group other = (Group) obj;
        return Objects.equals(this.groupname, other.groupname);
    }

    @Override
    public String toString() {
        return "Group{" + "groupname=" + groupname + ", description=" + description + '}';
    }
}