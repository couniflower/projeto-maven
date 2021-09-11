package model.bo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Cidade implements Serializable {
   @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   @Column
   private String descricao;

   public Cidade() {
   }

   public Cidade(int id, String descricao) {
      this.id = id;
      this.descricao = descricao;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getDescricao() {
      return descricao;
   }

   public void setDescricao(String descricao) {
      this.descricao = descricao;
   }
}
