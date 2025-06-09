package edu.iit.sat.itmd4515.ysharma7.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Represents an insurance claim with amount, date and linked policy.  
 * @author yashica
 */
@Entity
@NamedQuery(name = "Claim.findClaimForIP", query = "select c from Claim c where c.assignedAgent.agentId = :policyId")
public class Claim {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long claimId;
    
    @NotNull
    private double claimAmount;
    
    @NotNull
    private LocalDate claimDate;
    
    @ManyToOne
    @JoinColumn(name = "policy_id")
    private InsurancePolicy policy;
    
    @ManyToOne
    @JoinColumn(name = "agent_id", nullable = false)
    private Agent assignedAgent;

    public Claim() {
    }

    public Claim(double claimAmount, LocalDate claimDate) {
        this.claimAmount = claimAmount;
        this.claimDate = claimDate;
    }
   
    public long getClaimId() {
        return claimId;
    }
    public void setClaimId(long claimId) {
        this.claimId = claimId;
    }
    public double getClaimAmount() {
        return claimAmount;
    }
    public void setClaimAmount(double claimAmount) {
        this.claimAmount = claimAmount;
    }
    public LocalDate getClaimDate() {
        return claimDate;
    }
    public void setClaimDate(LocalDate claimDate) {
        this.claimDate = claimDate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + (int) (this.claimId ^ (this.claimId >>> 32));
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
        final Claim other = (Claim) obj;
        return this.claimId == other.claimId;
    }

    @Override
    public String toString() {
        return "Claim{" + "claimId=" + claimId + ", claimAmount=" + claimAmount + ", claimDate=" + claimDate + '}';
    }
    public InsurancePolicy getPolicy() {
        return policy;
    }
    public void setPolicy(InsurancePolicy policy) {
        this.policy = policy;
    }
    public Agent getAssignedAgent() {
        return assignedAgent;
    }
    public void setAssignedAgent(Agent assignedAgent) {
        this.assignedAgent = assignedAgent;
    }
}
