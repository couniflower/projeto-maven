package service;

import java.util.List;
import model.DAO.AlunoDAO;
import model.bo.Aluno;

public class AlunoService {
   public static void Incluir(Aluno objeto){
      AlunoDAO.getInstance().Create(objeto);
   }
   
   public static List<Aluno> Listar(){
      return AlunoDAO.getInstance().Retrieve();
   }
   
   public static Aluno Listar(int id){
      return AlunoDAO.getInstance().Retrieve(id);
   }
   
   public static void Atualizar(Aluno objeto){
      AlunoDAO.getInstance().Update(objeto);
   }
   
   public static void Excluir(Aluno objeto){
      AlunoDAO.getInstance().Delete(objeto);
   }
}
