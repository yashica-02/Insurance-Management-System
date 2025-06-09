package edu.iit.sat.itmd4515.ysharma7.security;

import jakarta.inject.Inject;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;

/**
 * Hashes user passwords before persisting or updating User entities.  
 * @author yashica
 */
public class HashTheUserPasswordListener {
    @Inject
    private Pbkdf2PasswordHash hash;
    
    @PrePersist
    @PreUpdate
    private void hashThatPassword(User u){
        String hashedPassword = hash.generate(u.getPassword().toCharArray());
        u.setPassword(hashedPassword);
    }
}
