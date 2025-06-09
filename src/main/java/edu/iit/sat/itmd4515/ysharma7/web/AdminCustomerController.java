package edu.iit.sat.itmd4515.ysharma7.web;

import edu.iit.sat.itmd4515.ysharma7.domain.Customer;
import edu.iit.sat.itmd4515.ysharma7.security.Group;
import edu.iit.sat.itmd4515.ysharma7.security.GroupService;
import edu.iit.sat.itmd4515.ysharma7.security.User;
import edu.iit.sat.itmd4515.ysharma7.security.UserService;
import edu.iit.sat.itmd4515.ysharma7.service.CustomerService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * JSF controller for admins to create, edit, and delete Customer entities.  
 * @author yashica
 */
@Named("adminCustomerController")
@SessionScoped
public class AdminCustomerController implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private CustomerService customerSvc;
    @EJB
    private UserService userSvc;
    @EJB
    private GroupService groupSvc;

    private List<Customer> customers;
    private Customer newCustomer;
    private String username;
    private String password;

    @PostConstruct
    public void init() {
        customers    = customerSvc.readAll();
        newCustomer  = new Customer();
    }

    public String addCustomer() {
        User u = new User(username, password);
        Group g = groupSvc.readAll().stream()
             .filter(x -> "CUSTOMER_GROUP".equals(x.getGroupname()))
             .findFirst()
             .orElseThrow(() -> new RuntimeException("Customer group not found"));
        u.addToGroup(g);
        userSvc.create(u);
        newCustomer.setUser(u);
        customerSvc.create(newCustomer);

        customers    = customerSvc.readAll();
        newCustomer  = new Customer();
        username     = password = null;
        
        return "customers.xhtml?faces-redirect=true";
    }
    
    public String prepareEdit(Customer a) {
        newCustomer = a;
        return "editCustomer.xhtml?faces-redirect=true";
    }

    public String updateCustomer() {
        customerSvc.update(newCustomer);
        customers = customerSvc.readAll();
        newCustomer = new Customer();
        return "customers.xhtml?faces-redirect=true";
    }

    public String deleteCustomer(Customer a) {
        customerSvc.delete(a);
        customers = customerSvc.readAll();
        return "customers.xhtml?faces-redirect=true";
    }

    public List<Customer> getCustomers() {
        return customers;
    }
    public Customer getNewCustomer() {
        return newCustomer;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    public void setNewCustomer(Customer c) {
        this.newCustomer = c;
    }
    public void setUsername(String u) {
        this.username = u;
    }
    public void setPassword(String p) {
        this.password = p;
    }
}