package model.bo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Pagar extends Impressao implements Serializable {
   @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   @JoinColumn @ManyToOne
   private Compra compra;

   public Pagar() {
   }

   public Pagar(int id, Compra compra, Date dtEmissao, Date dtVencimento, Date dtPagamento, float valorEmitido, float valorDesconto, float valorAcrescimo, float valorPago, String status) {
      super(dtEmissao, dtVencimento, dtPagamento, valorEmitido, valorDesconto, valorAcrescimo, valorPago, status);
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
