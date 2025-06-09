package edu.iit.sat.itmd4515.ysharma7.service;

import edu.iit.sat.itmd4515.ysharma7.domain.Claim;
import jakarta.ejb.Stateless;
import java.util.List;

/**
 * Provides CRUD operations for Claim entities.  
 * @author yashica
 */
@Stateless
public class ClaimService extends AbstractService<Claim> {

    public ClaimService() {
        super(Claim.class);
    } 
    
    public List<Claim> readAll() {
        return super.readAll(); 
    }
}