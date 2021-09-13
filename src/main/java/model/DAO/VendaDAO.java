package model.DAO;

import java.util.List;

import model.bo.Vendas;

public class VendaDAO extends SuperClassDAO<Vendas> {
   private static VendaDAO instance;

   public static VendaDAO getInstance() {
      if(instance == null) instance = new VendaDAO();
      return instance;
   }

   private VendaDAO() {
      super();
   }

   @Override
   public List<Vendas> Retrieve() {
      return entityManager.createQuery("select x from Vendas x", Vendas.class).getResultList();
   }

   @Override
   public Vendas Retrieve(int id) {
      return entityManager.find(Vendas.class, id);
   }

   @Override
   public void Delete(Vendas objeto) {
      try {
         entityManager.getTransaction().begin();
         Vendas obj = entityManager.find(Vendas.class, objeto.getId());
         entityManager.remove(obj);
         entityManager.getTransaction().commit();
      } catch (Exception e) {
         e.printStackTrace();
         entityManager.getTransaction().rollback();
      }
   }
   
}
