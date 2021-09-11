package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.bo.Fornecedor;
import model.bo.Personal;

public class PersonalDAO extends SuperClassDAO<Personal> {
   private static PersonalDAO instance;

   public static PersonalDAO getInstance() {
      if(instance == null) instance = new PersonalDAO();
      return instance;
   }

   private PersonalDAO() {
      super();
   }

   @Override
   public List<Personal> Retrieve() {
      return entityManager.createQuery("select x from Personal x", Personal.class).getResultList();
   }

   @Override
   public Personal Retrieve(int id) {
      return entityManager.find(Personal.class, id);
   }

   @Override
   public void Delete(Personal objeto) {
      try {
         entityManager.getTransaction().begin();
         Personal obj = entityManager.find(Personal.class, objeto.getId());
         entityManager.remove(obj);
         entityManager.getTransaction().commit();
      } catch (Exception e) {
         e.printStackTrace();
         entityManager.getTransaction().rollback();
      }
   }

}
