package edu.iit.sat.itmd4515.ysharma7.domain;

import edu.iit.sat.itmd4515.ysharma7.security.User;
import jakarta.persistence.*;
import jakarta.persistence.FetchType;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a customer with personal details and their insurance policies.  
 * @author yashica
 */
@Entity
@Table(name = "customer")
@NamedQuery(name="Customer.findByUsername", query="select c from Customer c where c.user.username=:uname")
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long customerId;
    
    @NotBlank
    private String name;
    
    @NotBlank
    private String email;
    
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private List<InsurancePolicy> policies= new ArrayList<>();

    @OneToOne
    @JoinColumn(name ="USERNAME")
    private User user;

    public Customer() {
    }

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }
    
    public void addPolicy(InsurancePolicy policy) {
        policies.add(policy);
        policy.setCustomer(this);
    }

    public void removePolicy(InsurancePolicy policy) {
        policies.remove(policy);
        policy.setCustomer(null);
    }

        
    public long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + (int) (this.customerId ^ (this.customerId >>> 32));
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
        final Customer other = (Customer) obj;
        return this.customerId == other.customerId;
    }

    @Override
    public String toString() {
        return "Customer{" + "customerId=" + customerId + ", name=" + name + ", email=" + email + '}';
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
}