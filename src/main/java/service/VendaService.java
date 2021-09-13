package service;

import java.util.List;
import model.DAO.VendaDAO;
import model.bo.Vendas;

public class VendaService {
   public static void Incluir(Vendas objeto){
      VendaDAO.getInstance().Create(objeto);
   }
   
   public static List<Vendas> Listar(){
      return VendaDAO.getInstance().Retrieve();
   }
   
   public static Vendas Listar(int id){
      return VendaDAO.getInstance().Retrieve(id);
   }
   
   public static void Atualizar(Vendas objeto){
      VendaDAO.getInstance().Update(objeto);
   }
   
   public static void Excluir(Vendas objeto){
      VendaDAO.getInstance().Delete(objeto);
   }
}
