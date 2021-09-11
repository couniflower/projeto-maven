package service;

import java.util.List;
import model.DAO.PagarDAO;
import model.bo.Pagar;

public class PagarService {
   public static void Incluir(Pagar objeto){
      PagarDAO.getInstance().Create(objeto);
   }
   
   public static List<Pagar> Listar(){
      return PagarDAO.getInstance().Retrieve();
   }
   
   public static Pagar Listar(int id){
      return PagarDAO.getInstance().Retrieve(id);
   }
   
   public static void Atualizar(Pagar objeto){
      PagarDAO.getInstance().Update(objeto);
   }
   
   public static void Excluir(Pagar objeto){
      PagarDAO.getInstance().Delete(objeto);
   }
}
