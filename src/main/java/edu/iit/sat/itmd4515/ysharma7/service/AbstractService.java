package edu.iit.sat.itmd4515.ysharma7.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Provides generic CRUD operations for JPA entities via an EntityManager.  
 * @author yashica
 */
public abstract class AbstractService<T> {

    private static final Logger LOG = Logger.getLogger(AbstractService.class.getName());

    @PersistenceContext(name = "itmd4515PU")
    protected EntityManager em;

    private final Class<T> entityClass;

    public AbstractService(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void create(T entity) {
        try {
            em.persist(entity);
            LOG.info("Created: " + entity.toString());
        } catch (PersistenceException e) {
            LOG.log(Level.SEVERE, "Error creating entity: " + entity, e);
            throw new RuntimeException("Could not persist entity: " + entity, e);
        }
    }

    public T read(Long id) {
        try {
            return em.find(entityClass, id);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error finding entity with ID: " + id, e);
            throw new RuntimeException("Could not find entity with ID: " + id, e);
        }
    }

    public List<T> readAll() {
        try {
            return em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass).getResultList();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error retrieving all entities", e);
            throw new RuntimeException("Could not retrieve entities", e);
        }
    }

    public void update(T entity) {
        try {
            em.merge(entity);
            LOG.info("Updated: " + entity.toString());
        } catch (PersistenceException e) {
            LOG.log(Level.SEVERE, "Error updating entity: " + entity, e);
            throw new RuntimeException("Could not update entity: " + entity, e);
        }
    }

    public void delete(T entity) {
        try {
            em.remove(em.merge(entity));
            LOG.info("Deleted: " + entity.toString());
        } catch (PersistenceException e) {
            LOG.log(Level.SEVERE, "Error deleting entity: " + entity, e);
            throw new RuntimeException("Could not delete entity: " + entity, e);
        }
    }
}