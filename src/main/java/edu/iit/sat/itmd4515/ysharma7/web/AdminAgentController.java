package edu.iit.sat.itmd4515.ysharma7.web;

import edu.iit.sat.itmd4515.ysharma7.domain.Agent;
import edu.iit.sat.itmd4515.ysharma7.security.Group;
import edu.iit.sat.itmd4515.ysharma7.security.GroupService;
import edu.iit.sat.itmd4515.ysharma7.security.User;
import edu.iit.sat.itmd4515.ysharma7.security.UserService;
import edu.iit.sat.itmd4515.ysharma7.service.AgentService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * JSF controller for admins to create, edit and delete Agent entities.  
 * @author yashica
 */
@Named("adminAgentController")
@SessionScoped
public class AdminAgentController implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private AgentService agentSvc;
    @EJB
    private UserService userSvc;
    @EJB
    private GroupService groupSvc;

    private List<Agent> agents;
    private Agent newAgent;
    private String username;
    private String password;

    @PostConstruct
    public void init() {
        agents   = agentSvc.readAll();
        newAgent = new Agent();
    }

    public String addAgent() {
        User u = new User(username, password);
        Group g = groupSvc.readAll().stream()
             .filter(x -> "AGENT_GROUP".equals(x.getGroupname()))
             .findFirst()
             .orElseThrow(() -> new RuntimeException("Agent group not found"));
        u.addToGroup(g);
        userSvc.create(u);
        newAgent.setUser(u);
        agentSvc.create(newAgent);
        agents   = agentSvc.readAll();
        newAgent = new Agent();
        username = password = null;

        return "agents.xhtml?faces-redirect=true";
    }
    
    public String prepareEdit(Agent a) {
        newAgent = a;
        return "editAgent.xhtml?faces-redirect=true";
    }

    public String updateAgent() {
        agentSvc.update(newAgent);
        agents = agentSvc.readAll();
        newAgent = new Agent();
        return "agents.xhtml?faces-redirect=true";
    }

    public String deleteAgent(Agent a) {
        agentSvc.delete(a);
        agents = agentSvc.readAll();
        return "agents.xhtml?faces-redirect=true";
    }


    public List<Agent> getAgents() {
        return agents;
    }
    public Agent getNewAgent() {
        return newAgent;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    public void setNewAgent(Agent a){
        this.newAgent = a;
    }
    public void setUsername(String u){ 
        this.username = u;
    }
    public void setPassword(String p){ 
        this.password = p;
    }
}