package model.bo;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "itensVenda")
public class ItemVenda extends Item implements Serializable {
   @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   @JoinColumn @ManyToOne
   private Venda venda;

   public ItemVenda() {
   }

   public ItemVenda(Produto produto, int quantidade) {
      super(produto, quantidade);
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public Venda getVenda() {
      return venda;
   }

   public void setVenda(Venda venda) {
      this.venda = venda;
   }
}