package service;

import java.util.List;
import model.DAO.ItemCompraDAO;
import model.bo.ItemCompra;

public class ItemCompraService {
   public static void Incluir(ItemCompra objeto){
      ItemCompraDAO.getInstance().Create(objeto);
   }
   
   public static List<ItemCompra> Listar(){
      return ItemCompraDAO.getInstance().Retrieve();
   }
   
   public static ItemCompra Listar(int id){
      return ItemCompraDAO.getInstance().Retrieve(id);
   }
   
   public static void Atualizar(ItemCompra objeto){
      ItemCompraDAO.getInstance().Update(objeto);
   }
   
   public static void Excluir(ItemCompra objeto){
      ItemCompraDAO.getInstance().Delete(objeto);
   }
}
