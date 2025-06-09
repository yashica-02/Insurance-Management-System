package edu.iit.sat.itmd4515.ysharma7.security;

import edu.iit.sat.itmd4515.ysharma7.service.AbstractService;
import jakarta.ejb.Stateless;
import java.util.List;

/**
 * Provides CRUD operations for security Group entities.  
 * @author yashica
 */
@Stateless
public class GroupService extends AbstractService<Group> {
    
    public GroupService() {
        super(Group.class);
    }
    
    public List<Group> readAll() {
        return super.readAll(); 
    }    
}
