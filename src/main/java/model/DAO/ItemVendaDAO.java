package model.DAO;

import java.util.List;

import model.bo.ItensVenda;

public class ItemVendaDAO extends SuperClassDAO<ItensVenda> {
   private static ItemVendaDAO instance;

   public static ItemVendaDAO getInstance() {
      if(instance == null) instance = new ItemVendaDAO();
      return instance;
   }

   private ItemVendaDAO() {
      super();
   }

   @Override
   public List<ItensVenda> Retrieve() {
      return entityManager.createQuery("select x from ItensVenda x", ItensVenda.class).getResultList();
   }

   @Override
   public ItensVenda Retrieve(int id) {
      return entityManager.find(ItensVenda.class, id);
   }

   @Override
   public void Delete(ItensVenda objeto) {
      try {
         entityManager.getTransaction().begin();
         ItensVenda obj = entityManager.find(ItensVenda.class, objeto.getId());
         entityManager.remove(obj);
         entityManager.getTransaction().commit();
      } catch (Exception e) {
         e.printStackTrace();
         entityManager.getTransaction().rollback();
      }
   }

}
