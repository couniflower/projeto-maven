package service;

import java.util.List;
import model.DAO.ItemVendaDAO;
import model.bo.ItemVenda;

public class ItemVendaService {
   public static void Incluir(ItemVenda objeto){
      ItemVendaDAO.getInstance().Create(objeto);
   }
   
   public static List<ItemVenda> Listar(){
      return ItemVendaDAO.getInstance().Retrieve();
   }
   
   public static ItemVenda Listar(int id){
      return ItemVendaDAO.getInstance().Retrieve(id);
   }
   
   public static void Atualizar(ItemVenda objeto){
      ItemVendaDAO.getInstance().Update(objeto);
   }
   
   public static void Excluir(ItemVenda objeto){
      ItemVendaDAO.getInstance().Delete(objeto);
   }
}
