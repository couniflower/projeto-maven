package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.bo.Fornecedor;
import model.bo.Receber;

public class ReceberDAO extends SuperClassDAO<Receber> {
   private static ReceberDAO instance;

   public static ReceberDAO getInstance() {
      if(instance == null) instance = new ReceberDAO();
      return instance;
   }

   private ReceberDAO() {
      super();
   }

   @Override
   public List<Receber> Retrieve() {
      return entityManager.createQuery("select x from Receber x", Receber.class).getResultList();
   }

   @Override
   public Receber Retrieve(int id) {
      return entityManager.find(Receber.class, id);
   }

   @Override
   public void Delete(Receber objeto) {
      try {
         entityManager.getTransaction().begin();
         Receber obj = entityManager.find(Receber.class, objeto.getId());
         entityManager.remove(obj);
         entityManager.getTransaction().commit();
      } catch (Exception e) {
         e.printStackTrace();
         entityManager.getTransaction().rollback();
      }
   }

}
