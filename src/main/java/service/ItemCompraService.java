package service;

import java.util.List;
import model.DAO.ItemCompraDAO;
import model.bo.ItensCompra;

public class ItemCompraService {
   public static void Incluir(ItensCompra objeto){
      ItemCompraDAO.getInstance().Create(objeto);
   }
   
   public static List<ItensCompra> Listar(){
      return ItemCompraDAO.getInstance().Retrieve();
   }
   
   public static ItensCompra Listar(int id){
      return ItemCompraDAO.getInstance().Retrieve(id);
   }
   
   public static void Atualizar(ItensCompra objeto){
      ItemCompraDAO.getInstance().Update(objeto);
   }
   
   public static void Excluir(ItensCompra objeto){
      ItemCompraDAO.getInstance().Delete(objeto);
   }
}
