package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.bo.Fornecedor;
import model.bo.ItemCompra;

public class ItemCompraDAO extends SuperClassDAO<ItemCompra> {
   private static ItemCompraDAO instance;

   public static ItemCompraDAO getInstance() {
      if(instance == null) instance = new ItemCompraDAO();
      return instance;
   }

   private ItemCompraDAO() {
      super();
   }

   @Override
   public List<ItemCompra> Retrieve() {
      return entityManager.createQuery("select x from ItemCompra x", ItemCompra.class).getResultList();
   }

   @Override
   public ItemCompra Retrieve(int id) {
      return entityManager.find(ItemCompra.class, id);
   }

   @Override
   public void Delete(ItemCompra objeto) {
      try {
         entityManager.getTransaction().begin();
         ItemCompra obj = entityManager.find(ItemCompra.class, objeto.getId());
         entityManager.remove(obj);
         entityManager.getTransaction().commit();
      } catch (Exception e) {
         e.printStackTrace();
         entityManager.getTransaction().rollback();
      }
   }

}
