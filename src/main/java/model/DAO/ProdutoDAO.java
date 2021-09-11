package model.DAO;
import java.util.List;

import model.bo.Bairro;
import model.bo.Produto;

public class ProdutoDAO extends SuperClassDAO<Produto> {
   private static ProdutoDAO instance;

   public static ProdutoDAO getInstance() {
      if(instance == null) instance = new ProdutoDAO();
      return instance;
   }

   private ProdutoDAO() {
      super();
   }

   @Override
   public List<Produto> Retrieve() {
      return entityManager.createQuery("select x from Produto x", Produto.class).getResultList();
   }

   @Override
   public Produto Retrieve(int id) {
      return entityManager.find(Produto.class, id);
   }

   @Override
   public void Delete(Produto objeto) {
      try {
         entityManager.getTransaction().begin();
         Produto obj = entityManager.find(Produto.class, objeto.getId());
         entityManager.remove(obj);
         entityManager.getTransaction().commit();
      } catch (Exception e) {
         e.printStackTrace();
         entityManager.getTransaction().rollback();
      }
   }

}
