package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.bo.Fornecedor;
import model.bo.Venda;

public class VendaDAO extends SuperClassDAO<Venda> {
   private static VendaDAO instance;

   public static VendaDAO getInstance() {
      if(instance == null) instance = new VendaDAO();
      return instance;
   }

   private VendaDAO() {
      super();
   }

   @Override
   public List<Venda> Retrieve() {
      return entityManager.createQuery("select x from Venda x", Venda.class).getResultList();
   }

   @Override
   public Venda Retrieve(int id) {
      return entityManager.find(Venda.class, id);
   }

   @Override
   public void Delete(Venda objeto) {
      try {
         entityManager.getTransaction().begin();
         Venda obj = entityManager.find(Venda.class, objeto.getId());
         entityManager.remove(obj);
         entityManager.getTransaction().commit();
      } catch (Exception e) {
         e.printStackTrace();
         entityManager.getTransaction().rollback();
      }
   }
   
}
