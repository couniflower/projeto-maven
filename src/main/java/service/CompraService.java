package service;

import java.util.List;
import model.DAO.CompraDAO;
import model.bo.Compra;

public class CompraService {
   public static void Incluir(Compra objeto){
      CompraDAO.getInstance().Create(objeto);
   }
   
   public static List<Compra> Listar(){
      return CompraDAO.getInstance().Retrieve();
   }
   
   public static Compra Listar(int id){
      return CompraDAO.getInstance().Retrieve(id);
   }
   
   public static void Atualizar(Compra objeto){
      CompraDAO.getInstance().Update(objeto);
   }
   
   public static void Excluir(Compra objeto){
      CompraDAO.getInstance().Delete(objeto);
   }
}
