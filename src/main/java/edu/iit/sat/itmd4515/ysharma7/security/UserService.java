package edu.iit.sat.itmd4515.ysharma7.security;

import edu.iit.sat.itmd4515.ysharma7.service.AbstractService;
import jakarta.ejb.Stateless;
import java.util.List;

/**
 * Provides CRUD operations for User entities, including retrieval of all users.  
 * @author yashica
 */

@Stateless
public class UserService extends AbstractService<User> {
    
    public UserService() {
        super(User.class);
    }
    
    public List<User> readAll() {
        return super.readAll(); 
    }    
}