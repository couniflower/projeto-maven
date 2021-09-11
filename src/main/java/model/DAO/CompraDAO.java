package model.DAO;

import model.bo.Compra;

import java.util.List;

public class CompraDAO extends SuperClassDAO<Compra> {
   private static CompraDAO instance;

   public static CompraDAO getInstance() {
      if(instance == null) instance = new CompraDAO();
      return instance;
   }

   private CompraDAO() {
      super();
   }

   @Override
   public List<Compra> Retrieve() {
      return entityManager.createQuery("select x from Compra x", Compra.class).getResultList();
   }

   @Override
   public Compra Retrieve(int id) {
      return entityManager.find(Compra.class, id);
   }

   @Override
   public void Delete(Compra objeto) {
      try {
         entityManager.getTransaction().begin();
         Compra obj = entityManager.find(Compra.class, objeto.getId());
         entityManager.remove(obj);
         entityManager.getTransaction().commit();
      } catch (Exception e) {
         e.printStackTrace();
         entityManager.getTransaction().rollback();
      }
   }

}
