package model.DAO;

import java.util.List;
import model.bo.Bairro;

public class BairroDAO extends SuperClassDAO<Bairro> {
   private static BairroDAO instance;

   public static BairroDAO getInstance() {
      if(instance == null) instance = new BairroDAO();
      return instance;
   }

   private BairroDAO() {
      super();
   }

   @Override
   public List<Bairro> Retrieve() {
      return entityManager.createQuery("select x from Bairro x", Bairro.class).getResultList();
   }

   @Override
   public Bairro Retrieve(int id) {
      return entityManager.find(Bairro.class, id);
   }


   public Bairro Retrieve(String nome) {
      return entityManager.find(Bairro.class, nome);
   }

   @Override
   public void Delete(Bairro objeto) {
      try {
         entityManager.getTransaction().begin();
         Bairro obj = entityManager.find(Bairro.class, objeto.getId());
         entityManager.remove(obj);
         entityManager.getTransaction().commit();
      } catch (Exception e) {
         e.printStackTrace();
         entityManager.getTransaction().rollback();
      }
   }

}
