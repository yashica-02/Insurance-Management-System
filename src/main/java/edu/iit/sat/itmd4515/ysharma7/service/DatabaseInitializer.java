package edu.iit.sat.itmd4515.ysharma7.service;

import edu.iit.sat.itmd4515.ysharma7.domain.Agent;
import edu.iit.sat.itmd4515.ysharma7.domain.Customer;
import edu.iit.sat.itmd4515.ysharma7.domain.InsurancePolicy;
import edu.iit.sat.itmd4515.ysharma7.domain.PolicyType;
import edu.iit.sat.itmd4515.ysharma7.security.Group;
import edu.iit.sat.itmd4515.ysharma7.security.GroupService;
import edu.iit.sat.itmd4515.ysharma7.security.User;
import edu.iit.sat.itmd4515.ysharma7.security.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import java.time.LocalDate;
import java.util.logging.Logger;

/**
 * Initializes the database with sample groups, users, agents, customers and policies at startup.  
 * @author yashica
 */
@Startup
@Singleton
public class DatabaseInitializer {
    private static final Logger LOG = Logger.getLogger(DatabaseInitializer.class.getName());

    @EJB
    private GroupService groupSvc;
    @EJB
    private UserService userSvc;
    @EJB
    private AgentService agentSvc;
    @EJB
    private CustomerService customerSvc;
    @EJB
    private InsurancePolicyService policySvc;

    @PostConstruct
    private void postConstruct() {
        LOG.info("Running DatabaseInitializer.postConstruct()");

        // Security groups
        Group customerGroup = new Group("CUSTOMER_GROUP", "Security group of customers");
        Group agentGroup = new Group("AGENT_GROUP", "agent group");
        Group adminGroup    = new Group("ADMIN_GROUP", "Superusers");
        groupSvc.create(adminGroup);
        groupSvc.create(customerGroup);
        groupSvc.create(agentGroup);

        // Users
        User admin = new User("admin", "admin");
        admin.addToGroup(adminGroup);
        userSvc.create(admin);

        User agentUser1 = new User("agent1", "agent1");
        agentUser1.addToGroup(agentGroup);
        userSvc.create(agentUser1);

        User agentUser2 = new User("agent2", "agent2");
        agentUser2.addToGroup(agentGroup);
        agentUser2.addToGroup(customerGroup);
        userSvc.create(agentUser2);

        User custUser1 = new User("cust1", "cust1");
        custUser1.addToGroup(customerGroup);
        userSvc.create(custUser1);

        User custUser2 = new User("cust2", "cust2");
        custUser2.addToGroup(customerGroup);
        userSvc.create(custUser2);

        // Agents
        Agent a1 = new Agent("Jenelia");
        a1.setUser(agentUser1);
        agentSvc.create(a1);

        Agent a2 = new Agent("Alizeh");
        a2.setUser(agentUser2);
        agentSvc.create(a2);

        // Customers
        Customer c1 = new Customer("SRK", "srk@example.com");
        c1.setUser(custUser1);
        customerSvc.create(c1);

        Customer c2 = new Customer("Katrina", "Katrina@example.com");
        c2.setUser(custUser2);
        customerSvc.create(c2);

        Customer c3 = new Customer("Agent two as Customer", "agent2@example.com");
        c3.setUser(agentUser2);
        customerSvc.create(c3);

        // Insurance Policies
        InsurancePolicy p1 = new InsurancePolicy();
        p1.setCustomer(c1);
        p1.setCoverageAmount(50000);
        p1.setStartDate(LocalDate.of(2023, 1, 1));
        p1.setEndDate(LocalDate.of(2024, 1, 1));
        p1.setPolicyType(PolicyType.CAR);
        c1.addPolicy(p1);
        p1.addAgent(a1);
        policySvc.create(p1);

        InsurancePolicy p2 = new InsurancePolicy();
        p2.setCustomer(c2);
        p2.setCoverageAmount(100000);
        p2.setStartDate(LocalDate.of(2023, 6, 1));
        p2.setEndDate(LocalDate.of(2024, 6, 1));
        p2.setPolicyType(PolicyType.LIFE);
        c2.addPolicy(p2);
        p2.addAgent(a2);
        policySvc.create(p2);

        LOG.info("Sample data loaded with customers tied to policies.");
    }
}