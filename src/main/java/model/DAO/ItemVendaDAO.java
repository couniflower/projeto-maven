package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.bo.Fornecedor;
import model.bo.ItemVenda;

public class ItemVendaDAO extends SuperClassDAO<ItemVenda> {
   private static ItemVendaDAO instance;

   public static ItemVendaDAO getInstance() {
      if(instance == null) instance = new ItemVendaDAO();
      return instance;
   }

   private ItemVendaDAO() {
      super();
   }

   @Override
   public List<ItemVenda> Retrieve() {
      return entityManager.createQuery("select x from ItemVenda x", ItemVenda.class).getResultList();
   }

   @Override
   public ItemVenda Retrieve(int id) {
      return entityManager.find(ItemVenda.class, id);
   }

   @Override
   public void Delete(ItemVenda objeto) {
      try {
         entityManager.getTransaction().begin();
         ItemVenda obj = entityManager.find(ItemVenda.class, objeto.getId());
         entityManager.remove(obj);
         entityManager.getTransaction().commit();
      } catch (Exception e) {
         e.printStackTrace();
         entityManager.getTransaction().rollback();
      }
   }

}
