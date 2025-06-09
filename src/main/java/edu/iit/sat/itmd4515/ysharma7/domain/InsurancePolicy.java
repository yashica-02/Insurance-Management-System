package edu.iit.sat.itmd4515.ysharma7.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents an insurance policy with coverage, dates, type, and relationships.  
 * @author yashica
 */
@Entity
@Table(name = "InsurancePolicy")
public class InsurancePolicy {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long policyId;  
        
    @NotNull
    @Positive
    private double coverageAmount;
    
    @NotNull
    private LocalDate startDate;
       
    @NotNull
    private LocalDate endDate;
    
    @Enumerated(EnumType.STRING)
    private PolicyType pType;
    
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @NotNull(message = "A policy must have a customer")
    private Customer customer;

    @OneToMany(mappedBy = "policy")
    private List<Claim> claims = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "policy_agent",
            joinColumns = @JoinColumn(name = "policy_id"),
            inverseJoinColumns = @JoinColumn(name = "agent_id"))
    private List<Agent> agents = new ArrayList<>();


    public InsurancePolicy() {
    }

    public InsurancePolicy(double coverageAmount, LocalDate startDate, LocalDate endDate, PolicyType PolicyType) {
        this.coverageAmount = coverageAmount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pType = PolicyType;
    }

     public void addClaim(Claim claim) {
        claims.add(claim);
        claim.setPolicy(this);
    }

    public void addAgent(Agent agent) {
        agents.add(agent);
        agent.getPolicies().add(this);
    }

    public void removeAgent(Agent agent) {
        agents.remove(agent);
        agent.getPolicies().remove(this);
    }      
        
    public long getPolicyId() {
        return policyId;
    }
    public void setPolicyId(long policyId) {
        this.policyId = policyId;
    }
    public double getCoverageAmount() {
        return coverageAmount;
    }
    public void setCoverageAmount(double coverageAmount) {
        this.coverageAmount = coverageAmount;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
     public PolicyType getPolicyType() {
        return pType;
    }
    public void setPolicyType(PolicyType PolicyType) {
        this.pType = PolicyType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(policyId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) return false;
        InsurancePolicy that = (InsurancePolicy) obj;
        return Objects.equals(policyId, that.policyId);
    }    

    @Override
    public String toString() {
        return "InsurancePolicy{" + "policyId=" + policyId + ", coverageAmount=" + coverageAmount + ", startDate=" + startDate + ", endDate=" + endDate + ", PolicyType=" + pType + '}';
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public List<Claim> getClaims() {
        return claims;
    }
    public void setClaims(List<Claim> claims) {
        this.claims = claims;
    }
    public List<Agent> getAgents() {
        return agents;
    }
    public void setAgents(List<Agent> agents) {
        this.agents = agents;
    }
}