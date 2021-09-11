package service;

import java.util.List;
import model.DAO.CepDAO;
import model.bo.Cep;

public class CepService {
   public static void Incluir(Cep objeto){
      CepDAO.getInstance().Create(objeto);
   }
   
   public static List<Cep> Listar(){
      return CepDAO.getInstance().Retrieve();
   }
   
   public static Cep Listar(int id){
      return CepDAO.getInstance().Retrieve(id);
   }
   
   public static void Atualizar(Cep objeto){
      CepDAO.getInstance().Update(objeto);
   }
   
   public static void Excluir(Cep objeto){
      CepDAO.getInstance().Delete(objeto);
   }
}
