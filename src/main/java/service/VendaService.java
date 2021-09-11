package service;

import java.util.List;
import model.DAO.VendaDAO;
import model.bo.Venda;

public class VendaService {
   public static void Incluir(Venda objeto){
      VendaDAO.getInstance().Create(objeto);
   }
   
   public static List<Venda> Listar(){
      return VendaDAO.getInstance().Retrieve();
   }
   
   public static Venda Listar(int id){
      return VendaDAO.getInstance().Retrieve(id);
   }
   
   public static void Atualizar(Venda objeto){
      VendaDAO.getInstance().Update(objeto);
   }
   
   public static void Excluir(Venda objeto){
      VendaDAO.getInstance().Delete(objeto);
   }
}
