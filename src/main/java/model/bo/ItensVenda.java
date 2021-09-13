package model.bo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "itensVenda")
public class ItensVenda extends Item implements Serializable {
   @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   @JoinColumn(name = "idVendas") @ManyToOne
   private Vendas vendas;

   public ItensVenda() {
   }

   public ItensVenda(Produto produto, int quantidade) {
      super(produto, quantidade);
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public Vendas getVenda() {
      return vendas;
   }

   public void setVenda(Vendas vendas) {
      this.vendas = vendas;
   }
}