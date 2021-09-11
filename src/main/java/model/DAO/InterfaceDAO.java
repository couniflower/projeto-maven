package model.DAO;

import java.util.List;

public interface InterfaceDAO<T> {
   void Create(T objeto);
   List<T> Retrieve();
   T Retrieve(int id);
   void Update(T objeto);
   void Delete(T objeto);
}
