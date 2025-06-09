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
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import jakarta.faces.context.FacesContext;
import java.security.Principal;

/**
 * JSF controller to handle customer account creation and policy navigation.  
 * @author yashica
 */
@Named("customerController")
@SessionScoped
public class CustomerController implements Serializable {

    @EJB
    private CustomerService customerSvc;
    @EJB
    private UserService userSvc;
    @EJB
    private GroupService groupSvc;  

    @Inject
    private AgentInsurancePolicyController agentIPController;

    private Customer customer;
    private List<Customer> customers;
    private String username;
    private String password;

    @PostConstruct
    public void init() {
        customer = new Customer();
        customers = customerSvc.readAll();
    }

    public String displayCreateCustomerPage() {
        customer = new Customer();
        return "createCustomer?faces-redirect=true";
    }

    public String create() {
        User u = new User(username, password);
        Group g = groupSvc.readAll().stream()
             .filter(x -> "CUSTOMER_GROUP".equals(x.getGroupname()))
             .findFirst()
             .orElseThrow(() -> new RuntimeException("Customer group not found"));
        u.addToGroup(g);
        userSvc.create(u);
        customer.setUser(u);
        customerSvc.create(customer);
        customers = customerSvc.readAll();
        agentIPController.getCustomers().add(customer);
        agentIPController.getInsurancePolicy().setCustomer(customer);
        username = password = null;
        return "createIP?faces-redirect=true";
    }
    
    public String dashboard() {
        Principal p = FacesContext.getCurrentInstance()
                                 .getExternalContext()
                                 .getUserPrincipal();
        if (p != null) {
            customer = customerSvc.findByUsername(p.getName());
        }
        return "/customer/welcome.xhtml?faces-redirect=true";
    }
    
    public void loadCustomer() {
        Principal user = FacesContext.getCurrentInstance()
                                     .getExternalContext()
                                     .getUserPrincipal();
        if (user != null) {
            this.customer = customerSvc.findByUsername(user.getName());
        }
    }

    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public List<Customer> getCustomers() {
        return customers;
    }
    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}