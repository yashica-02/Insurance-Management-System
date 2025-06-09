package edu.iit.sat.itmd4515.ysharma7.domain;

import edu.iit.sat.itmd4515.ysharma7.security.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an insurance agent entity with associated user and policies.  
 * @author yashica
 */
@Entity
@NamedQuery(name="Agent.findByUsername", query="select a from Agent a where a.user.username=:uname")
public class Agent {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long agentId;
    
    @NotBlank
    private String name;

    @ManyToMany(mappedBy = "agents")
    private List<InsurancePolicy> policies = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "USERNAME")
    private User user;
    
    public Agent() {
        }

    public Agent(String name) {
        this.name = name;
    }
  
    public void addPolicy(InsurancePolicy policy) {
        policies.add(policy);
        policy.getAgents().add(this);
    }

    public void removePolicy(InsurancePolicy policy) {
        policies.remove(policy);
        policy.getAgents().remove(this);
    }
    
    
    public long getAgentId() {
        return agentId;
    }  
    public void setAgentId(long agentId) {
        this.agentId = agentId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<InsurancePolicy> getPolicies() {
        return policies;
    }
    public void setPolicies(List<InsurancePolicy> policies) {
        this.policies = policies;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + (int) (this.agentId ^ (this.agentId >>> 32));
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
        final Agent other = (Agent) obj;
        return this.agentId == other.agentId;
    }

    @Override
    public String toString() {
        return "Agent{" + "agentId=" + agentId + ", name=" + name + '}';
    }        
}
