package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.bo.Fornecedor;
import model.bo.Pagar;

public class PagarDAO extends SuperClassDAO<Pagar> {
   private static PagarDAO instance;

   public static PagarDAO getInstance() {
      if(instance == null) instance = new PagarDAO();
      return instance;
   }

   private PagarDAO() {
      super();
   }

   @Override
   public List<Pagar> Retrieve() {
      return entityManager.createQuery("select x from Pagar x", Pagar.class).getResultList();
   }

   @Override
   public Pagar Retrieve(int id) {
      return entityManager.find(Pagar.class, id);
   }

   @Override
   public void Delete(Pagar objeto) {
      try {
         entityManager.getTransaction().begin();
         Pagar obj = entityManager.find(Pagar.class, objeto.getId());
         entityManager.remove(obj);
         entityManager.getTransaction().commit();
      } catch (Exception e) {
         e.printStackTrace();
         entityManager.getTransaction().rollback();
      }
   }

}
