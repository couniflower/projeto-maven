package model.DAO;

import java.util.List;

import model.bo.ItensCompra;

public class ItemCompraDAO extends SuperClassDAO<ItensCompra> {
   private static ItemCompraDAO instance;

   public static ItemCompraDAO getInstance() {
      if(instance == null) instance = new ItemCompraDAO();
      return instance;
   }

   private ItemCompraDAO() {
      super();
   }

   @Override
   public List<ItensCompra> Retrieve() {
      return entityManager.createQuery("select x from ItensCompra x", ItensCompra.class).getResultList();
   }

   @Override
   public ItensCompra Retrieve(int id) {
      return entityManager.find(ItensCompra.class, id);
   }

   @Override
   public void Delete(ItensCompra objeto) {
      try {
         entityManager.getTransaction().begin();
         ItensCompra obj = entityManager.find(ItensCompra.class, objeto.getId());
         entityManager.remove(obj);
         entityManager.getTransaction().commit();
      } catch (Exception e) {
         e.printStackTrace();
         entityManager.getTransaction().rollback();
      }
   }

}
