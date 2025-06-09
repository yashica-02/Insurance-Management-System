package edu.iit.sat.itmd4515.ysharma7.service;

import edu.iit.sat.itmd4515.ysharma7.domain.Customer;
import jakarta.ejb.Stateless;
import java.util.List;

/**
 * Handles Customer operations, including fetching by username with policies.  
 * @author yashica
 */
@Stateless
public class CustomerService extends AbstractService<Customer> {

    public CustomerService() {
        super(Customer.class);
    }
    
    public List<Customer> readAll() {
        return super.readAll(); 
    }
    
    public Customer findByUsername(String uname) {
        return em.createQuery(
            "SELECT c FROM Customer c " +
            "LEFT JOIN FETCH c.policies " +
            "WHERE c.user.username = :uname",
            Customer.class)
          .setParameter("uname", uname)
          .getSingleResult();
    }
    public Customer findById(Long id) {
        return em.find(Customer.class, id);
    }
}