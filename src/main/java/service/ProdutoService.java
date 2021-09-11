package service;

import java.util.List;
import model.DAO.ProdutoDAO;
import model.bo.Produto;

public class ProdutoService {
   public static void Incluir(Produto objeto){
      ProdutoDAO.getInstance().Create(objeto);
   }
   
   public static List<Produto> Listar(){
      return ProdutoDAO.getInstance().Retrieve();
   }
   
   public static Produto Listar(int id){
      return ProdutoDAO.getInstance().Retrieve(id);
   }

   public static Produto Listar(String codigo){
      int id = 0;
      for(Produto produto : Listar()){
         if(produto.getCodigoBarra().equalsIgnoreCase(codigo)) id = produto.getId();
      }
      if(id == 0) return null;
      return Listar(id);
   }
   
   public static void Atualizar(Produto objeto){
      ProdutoDAO.getInstance().Update(objeto);
   }
   
   public static void Excluir(Produto objeto){
      ProdutoDAO.getInstance().Delete(objeto);
   }
}
