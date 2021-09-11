package service;

import java.util.List;
import model.DAO.FornecedorDAO;
import model.bo.Fornecedor;

public class FornecedorService {
   public static void Incluir(Fornecedor objeto){
      FornecedorDAO.getInstance().Create(objeto);
   }
   
   public static List<Fornecedor> Listar(){
      return FornecedorDAO.getInstance().Retrieve();
   }
   
   public static Fornecedor Listar(int id){
      return FornecedorDAO.getInstance().Retrieve(id);
   }
   
   public static void Atualizar(Fornecedor objeto){
      FornecedorDAO.getInstance().Update(objeto);
   }
   
   public static void Excluir(Fornecedor objeto){
      FornecedorDAO.getInstance().Delete(objeto);
   }
}
