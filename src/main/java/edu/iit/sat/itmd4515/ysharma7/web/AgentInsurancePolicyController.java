package edu.iit.sat.itmd4515.ysharma7.web;

import edu.iit.sat.itmd4515.ysharma7.domain.Customer;
import edu.iit.sat.itmd4515.ysharma7.domain.InsurancePolicy;
import edu.iit.sat.itmd4515.ysharma7.domain.PolicyType;
import edu.iit.sat.itmd4515.ysharma7.service.AgentService;
import edu.iit.sat.itmd4515.ysharma7.service.CustomerService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * JSF controller allowing agents to create, view, edit, and delete insurance policies.  
 * @author yashica
 */
@Named("agentInsurancePolicyController")
@SessionScoped
public class AgentInsurancePolicyController implements Serializable {

    private static final Logger LOG = Logger.getLogger(AgentInsurancePolicyController.class.getName());

    @EJB
    private AgentService agentSvc;

    @EJB
    private CustomerService customerSvc;

    @Inject
    private AgentWelcomeController awc;

    private InsurancePolicy insurancePolicy;
    private List<Customer> customers;

    @PostConstruct
    public void init() {
        insurancePolicy = new InsurancePolicy();
        customers = customerSvc.readAll();
    }

    public String createPolicy() {
        try {
            agentSvc.createPolicyForAgent(awc.getAgent(), insurancePolicy);
            LOG.info("Created insurance policy: " + insurancePolicy);
            awc.refreshAgent();
            return "/agent/welcome.xhtml?faces-redirect=true";
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error creating insurance policy", e);
            return null;
        }
    }

    public String editPolicy() {
        try {
            agentSvc.editPolicyForAgent(awc.getAgent(), insurancePolicy);
            LOG.info("Edited insurance policy: " + insurancePolicy);
            awc.refreshAgent();
            return "/agent/welcome.xhtml?faces-redirect=true";
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error editing insurance policy", e);
            return null;
        }
    }

    public String deletePolicy() {
        try {
            agentSvc.deletePolicyForAgent(awc.getAgent(), insurancePolicy);
            LOG.info("Deleted insurance policy: " + insurancePolicy);
            awc.refreshAgent();
            return "/agent/welcome.xhtml?faces-redirect=true";
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error deleting insurance policy", e);
            return null;
        }
    }

    public String displayCreateIPPage() {
        insurancePolicy = new InsurancePolicy();
        return "/agent/createIP.xhtml?faces-redirect=true";
    }

    public String displayReadIPPage(InsurancePolicy ip) {
        insurancePolicy = ip;
        return "/agent/readIP.xhtml?faces-redirect=true";
    }

    public String displayEditIPPage(InsurancePolicy ip) {
        insurancePolicy = ip;
        return "/agent/editIP.xhtml?faces-redirect=true";
    }

    public String displayDeleteIPPage(InsurancePolicy ip) {
        insurancePolicy = ip;
        return "/agent/deleteIP.xhtml?faces-redirect=true";
    }

    public InsurancePolicy getInsurancePolicy() {
        return insurancePolicy;
    }

    public void setInsurancePolicy(InsurancePolicy insurancePolicy) {
        this.insurancePolicy = insurancePolicy;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<PolicyType> getPolicyTypes() {
        return Arrays.asList(PolicyType.values());
    }
}