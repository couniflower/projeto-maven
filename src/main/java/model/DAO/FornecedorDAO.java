package model.DAO;

import java.util.List;

import model.bo.Fornecedor;

public class FornecedorDAO extends SuperClassDAO<Fornecedor> {
   private static FornecedorDAO instance;

   public static FornecedorDAO getInstance() {
      if(instance == null) instance = new FornecedorDAO();
      return instance;
   }

   private FornecedorDAO() {
      super();
   }

   @Override
   public List<Fornecedor> Retrieve() {
      return entityManager.createQuery("select x from Fornecedor x", Fornecedor.class).getResultList();
   }

   @Override
   public Fornecedor Retrieve(int id) {
      return entityManager.find(Fornecedor.class, id);
   }

   @Override
   public void Delete(Fornecedor objeto) {
      try {
         entityManager.getTransaction().begin();
         Fornecedor obj = entityManager.find(Fornecedor.class, objeto.getId());
         entityManager.remove(obj);
         entityManager.getTransaction().commit();
      } catch (Exception e) {
         e.printStackTrace();
         entityManager.getTransaction().rollback();
      }
   }

}
