package model.bo;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "itensCompra")
public class ItemCompra extends Item implements Serializable {
   @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   @JoinColumn @ManyToOne
   private Compra compra;

   public ItemCompra() {
   }

   public ItemCompra(int id, Compra compra, Produto produto, int quantidade) {
      super(produto, quantidade);
      this.id = id;
      this.compra = compra;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public Compra getCompra() {
      return compra;
   }

   public void setCompra(Compra compra) {
      this.compra = compra;
   }
}
