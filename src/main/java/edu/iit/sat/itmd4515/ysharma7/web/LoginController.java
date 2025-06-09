package edu.iit.sat.itmd4515.ysharma7.web;

import edu.iit.sat.itmd4515.ysharma7.security.User;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.Password;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * JSF controller handling user login authentication and role-based navigation.  
 * @author yashica
 */
@Named("loginController")
@RequestScoped
public class LoginController {

    private static final Logger LOG = Logger.getLogger(LoginController.class.getName());

    @Inject FacesContext facesContext;
    @Inject SecurityContext securityContext;
    
    private User user;
   
    public LoginController() {
    }
    
    @PostConstruct
    private void postConstruct(){
        LOG.info("Inside the LoginController.postConstruct method");        
        user = new User();
    }
    
    // helper methods
    public String getAuthenticatedUser(){
        return securityContext.getCallerPrincipal().getName();
    }
    
    public boolean isAdmin(){
        return securityContext.isCallerInRole("ADMIN_ROLE");
    }

    public boolean isAgent(){
        return securityContext.isCallerInRole("AGENT_ROLE");
    }

    public boolean isCustomer(){
        return securityContext.isCallerInRole("CUSTOMER_ROLE");
    }
    
    public String doLogin(){
        
        HttpServletRequest request = (HttpServletRequest)facesContext.getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse)facesContext.getExternalContext().getResponse();
        
        Credential cred = new UsernamePasswordCredential(this.user.getUsername(), new Password(this.user.getPassword()));
        
        AuthenticationStatus status = securityContext.authenticate(request, response, AuthenticationParameters.withParams().credential(cred));
        
        if (status == AuthenticationStatus.SUCCESS) {
            if (isAdmin()) {
                return "/admin/welcome.xhtml?faces-redirect=true";
            }
            if (isAgent()) {
                return "/agent/welcome.xhtml?faces-redirect=true";
            }
            if (isCustomer()) {
                return "/customer/welcome.xhtml?faces-redirect=true";
            }
            // fallback if somehow no role matched
            return "/login.xhtml?faces-redirect=true";
        }

        // on failure
        return "/error.xhtml?faces-redirect=true";
    }
    
     public String doLogout(){
        HttpServletRequest request = (HttpServletRequest)facesContext.getExternalContext().getRequest();

        try {
            request.logout();
        } catch (ServletException ex) {
            LOG.log(Level.SEVERE, null, ex);
            return "/error.xhtml";
        }
        
        return "/login.xhtml?faces-redirect=true";
    }    
      
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}