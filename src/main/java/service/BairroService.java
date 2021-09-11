package service;

import java.util.List;
import model.DAO.BairroDAO;
import model.DAO.CidadeDAO;
import model.bo.Bairro;
import model.bo.Cidade;

public class BairroService {
   public static void Incluir(Bairro objeto){
      BairroDAO.getInstance().Create(objeto);
   }
   
   public static List<Bairro> Listar(){
      return BairroDAO.getInstance().Retrieve();
   }
   
   public static Bairro Listar(int id){
      return BairroDAO.getInstance().Retrieve(id);
   }

   public static Cidade Listar(String nome){
      return CidadeDAO.getInstance().Retrieve(nome);
   }
   
   public static void Atualizar(Bairro objeto){
      BairroDAO.getInstance().Update(objeto);
   }
   
   public static void Excluir(Bairro objeto){
      BairroDAO.getInstance().Delete(objeto);
   }
}
