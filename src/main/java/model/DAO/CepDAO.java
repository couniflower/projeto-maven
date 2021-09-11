package model.DAO;

import java.util.List;
import model.bo.Cep;

public class CepDAO extends SuperClassDAO<Cep> {
   private static CepDAO instance;

   public static CepDAO getInstance() {
      if(instance == null) instance = new CepDAO();
      return instance;
   }

   private CepDAO() {
      super();
   }

   @Override
   public List<Cep> Retrieve() {
      return entityManager.createQuery("select x from Cep x", Cep.class).getResultList();
   }

   @Override
   public Cep Retrieve(int id) {
      return entityManager.find(Cep.class, id);
   }

   @Override
   public void Delete(Cep objeto) {
      try {
         entityManager.getTransaction().begin();
         Cep obj = entityManager.find(Cep.class, objeto.getId());
         entityManager.remove(obj);
         entityManager.getTransaction().commit();
      } catch (Exception e) {
         e.printStackTrace();
         entityManager.getTransaction().rollback();
      }
   }

}
