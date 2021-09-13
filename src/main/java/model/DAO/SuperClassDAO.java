package model.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public abstract class SuperClassDAO<T> {
    protected EntityManager entityManager;

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        if(entityManager == null) entityManager = factory.createEntityManager();
        return entityManager;
    }

    public SuperClassDAO() {
        this.entityManager = getEntityManager();
    }

    public void Create(T objeto) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(objeto);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public abstract List<T> Retrieve();

    public abstract T Retrieve(int id);

    public void Update(T objeto) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(objeto);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public abstract void Delete(T objeto);
}
