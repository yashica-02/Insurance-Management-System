package edu.iit.sat.itmd4515.ysharma7.service;

import edu.iit.sat.itmd4515.ysharma7.domain.Agent;
import edu.iit.sat.itmd4515.ysharma7.domain.Claim;
import edu.iit.sat.itmd4515.ysharma7.domain.InsurancePolicy;
import jakarta.ejb.Stateless;
import java.util.List;

/**
 * Handles business operations for Agent entities, including policy management.  
 * @author yashica
 */
@Stateless
public class AgentService extends AbstractService<Agent> {

    public AgentService() {
        super(Agent.class);
    }  
    
    public List<Agent> readAll() {
        return super.readAll(); 
    }
    
    public Agent findByUsername(String username){
        return em.createNamedQuery("Agent.findByUsername", Agent.class).setParameter("uname",username).getSingleResult();
    }

    public void createPolicyForAgent(Agent agent, InsurancePolicy insurancePolicy) {
        em.persist(insurancePolicy);
        
        Agent agentRef = em.getReference(Agent.class, agent.getAgentId());
        agentRef.addPolicy(insurancePolicy);
        em.merge(agentRef);
    } 
    
    public void editPolicyForAgent(Agent a, InsurancePolicy ip) {
        InsurancePolicy managedPolicyRef = em.getReference(InsurancePolicy.class, ip.getPolicyId());
        Agent managedAgentRef = em.getReference(Agent.class, a.getAgentId());
        
        managedPolicyRef.setCustomer(ip.getCustomer());
        managedPolicyRef.setCoverageAmount(ip.getCoverageAmount());
        managedPolicyRef.setStartDate(ip.getStartDate());
        managedPolicyRef.setEndDate(ip.getEndDate());
        managedPolicyRef.setPolicyType(ip.getPolicyType());
        
        em.merge(managedPolicyRef);        
    } 
    
    public void deletePolicyForAgent(Agent a, InsurancePolicy ip) {
        InsurancePolicy managedPolicyRef = em.getReference(InsurancePolicy.class, ip.getPolicyId());
        Agent managedAgentRef = em.getReference(Agent.class, a.getAgentId());
        
        managedAgentRef.removePolicy(managedPolicyRef);

        for(Claim c : em.createNamedQuery("Claim.findClaimForIP", Claim.class).setParameter("policyId", managedPolicyRef.getPolicyId()).getResultList()){
//            c.
        }
    }
}
