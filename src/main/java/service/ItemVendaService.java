package service;

import java.util.List;
import model.DAO.ItemVendaDAO;
import model.bo.ItensVenda;

public class ItemVendaService {
   public static void Incluir(ItensVenda objeto){
      ItemVendaDAO.getInstance().Create(objeto);
   }
   
   public static List<ItensVenda> Listar(){
      return ItemVendaDAO.getInstance().Retrieve();
   }
   
   public static ItensVenda Listar(int id){
      return ItemVendaDAO.getInstance().Retrieve(id);
   }
   
   public static void Atualizar(ItensVenda objeto){
      ItemVendaDAO.getInstance().Update(objeto);
   }
   
   public static void Excluir(ItensVenda objeto){
      ItemVendaDAO.getInstance().Delete(objeto);
   }
}
