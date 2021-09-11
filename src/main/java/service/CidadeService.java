package service;

import java.util.List;
import model.DAO.CidadeDAO;
import model.bo.Cidade;

public class CidadeService {
   public static void Incluir(Cidade objeto){
       CidadeDAO.getInstance().Create(objeto);
   }
   
   public static List<Cidade> Listar(){
       return CidadeDAO.getInstance().Retrieve();
   }
   
   public static Cidade Listar(int id){
      return CidadeDAO.getInstance().Retrieve(id);
   }

   public static Cidade Listar(String nome){
      return CidadeDAO.getInstance().Retrieve(nome);
   }
   
   public static void Atualizar(Cidade objeto){
      CidadeDAO.getInstance().Update(objeto);
   }
   
   public static void Excluir(Cidade objeto){
      CidadeDAO.getInstance().Delete(objeto);
   }
}
