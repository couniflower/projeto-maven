package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.bo.Aluno;
import model.bo.Fornecedor;

public class AlunoDAO extends SuperClassDAO<Aluno> {
   private static AlunoDAO instance;

   public static AlunoDAO getInstance() {
      if(instance == null) instance = new AlunoDAO();
      return instance;
   }

   private AlunoDAO() {
      super();
   }

   @Override
   public List<Aluno> Retrieve() {
      return entityManager.createQuery("select x from Aluno x", Aluno.class).getResultList();
   }

   @Override
   public Aluno Retrieve(int id) {
      return entityManager.find(Aluno.class, id);
   }

   @Override
   public void Delete(Aluno objeto) {
      try {
         entityManager.getTransaction().begin();
         Aluno obj = entityManager.find(Aluno.class, objeto.getId());
         entityManager.remove(obj);
         entityManager.getTransaction().commit();
      } catch (Exception e) {
         e.printStackTrace();
         entityManager.getTransaction().rollback();
      }
   }

}
