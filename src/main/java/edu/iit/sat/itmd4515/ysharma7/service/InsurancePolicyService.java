package edu.iit.sat.itmd4515.ysharma7.service;

import edu.iit.sat.itmd4515.ysharma7.domain.InsurancePolicy;
import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import java.util.List;

/**
 * Provides CRUD operations for InsurancePolicy entities.  
 * @author yashica
 */
@Named("InsurancePolicyService")
@Stateless
public class InsurancePolicyService extends AbstractService<InsurancePolicy> {

    public InsurancePolicyService() {
        super(InsurancePolicy.class);
    }
    
    public List<InsurancePolicy> readAll() {
        return super.readAll(); 
    }
}