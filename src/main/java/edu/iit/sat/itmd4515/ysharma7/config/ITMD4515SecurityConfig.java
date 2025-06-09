package edu.iit.sat.itmd4515.ysharma7.config;

import jakarta.annotation.security.DeclareRoles;
import jakarta.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.LoginToContinue;
import jakarta.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;

/**
 * Configures Jakarta Security for authentication and role declarations.  
 * @author yashica
 */
@DeclareRoles({"ADMIN_ROLE","CUSTOMER_ROLE","AGENT_ROLE"})
@CustomFormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/login.xhtml",
                errorPage = "/error.xhtml"
        )
)
@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "java:app/jdbc/itmd4515DS",
        callerQuery = "select PASSWORD from SEC_USER where USERNAME = ?",
        groupsQuery = "select GROUPNAME from SEC_USER_GROUPS where USERNAME = ?"
)
public class ITMD4515SecurityConfig {
    
}
