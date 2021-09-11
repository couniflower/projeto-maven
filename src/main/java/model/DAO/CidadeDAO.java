package model.DAO;

import java.util.List;
import model.bo.Cidade;

public class CidadeDAO extends SuperClassDAO<Cidade> {
   private static CidadeDAO instance;

   public static CidadeDAO getInstance() {
      if(instance == null) instance = new CidadeDAO();
      return instance;
   }

   private CidadeDAO() {
      super();
   }

   @Override
   public List<Cidade> Retrieve() {
      return entityManager.createQuery("select x from Cidade x", Cidade.class).getResultList();
   }

   @Override
   public Cidade Retrieve(int id) {
      return entityManager.find(Cidade.class, id);
   }

   public Cidade Retrieve(String nome) {
      return entityManager.find(Cidade.class, nome);
   }

   @Override
   public void Delete(Cidade objeto) {
      try {
         entityManager.getTransaction().begin();
         Cidade obj = entityManager.find(Cidade.class, objeto.getId());
         entityManager.remove(obj);
         entityManager.getTransaction().commit();
      } catch (Exception e) {
         e.printStackTrace();
         entityManager.getTransaction().rollback();
      }
   }

}
