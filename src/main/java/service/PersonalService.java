package service;

import java.util.List;
import model.DAO.PersonalDAO;
import model.bo.Personal;

public class PersonalService {
   public static void Incluir(Personal objeto){
      PersonalDAO.getInstance().Create(objeto);
   }
   
   public static List<Personal> Listar(){
      return PersonalDAO.getInstance().Retrieve();
   }
   
   public static Personal Listar(int id){
      return PersonalDAO.getInstance().Retrieve(id);
   }
   
   public static void Atualizar(Personal objeto){
      PersonalDAO.getInstance().Update(objeto);
   }
   
   public static void Excluir(Personal objeto){
      PersonalDAO.getInstance().Delete(objeto);
   }
}
