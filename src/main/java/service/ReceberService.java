package service;

import java.util.List;
import model.DAO.ReceberDAO;
import model.bo.Receber;

public class ReceberService {
   public static void Incluir(Receber objeto){
      ReceberDAO.getInstance().Create(objeto);
   }
   
   public static List<Receber> Listar(){
      return ReceberDAO.getInstance().Retrieve();
   }
   
   public static Receber Listar(int id){
      return ReceberDAO.getInstance().Retrieve(id);
   }
   
   public static void Atualizar(Receber objeto){
      ReceberDAO.getInstance().Update(objeto);
   }
   
   public static void Excluir(Receber objeto){
      ReceberDAO.getInstance().Delete(objeto);
   }
}
