package edu.iit.sat.itmd4515.ysharma7.web;

import edu.iit.sat.itmd4515.ysharma7.domain.Agent;
import edu.iit.sat.itmd4515.ysharma7.service.AgentService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.logging.Logger;

/**
 * JSF controller to load and refresh the logged-in Agent’s data.  
 * @author yashica
 */
@Named("AgentWelcomeController")
@RequestScoped
public class AgentWelcomeController {

    private static final Logger LOG = Logger.getLogger(AgentWelcomeController.class.getName());
       
    @EJB private AgentService agentSvc;
    @Inject LoginController loginController;
    
    private Agent agent;
        
    public AgentWelcomeController() {
    }
    
    @PostConstruct
    private void postConstruct() {
        agent = agentSvc.findByUsername(loginController.getAuthenticatedUser());
    }
    
    public void refreshAgent(){
        agent = agentSvc.findByUsername(loginController.getAuthenticatedUser());
    }    
    
    public Agent getAgent() {
        return agent;
    }
    public void setAgent(Agent agent) {
        this.agent = agent;
    }    
}