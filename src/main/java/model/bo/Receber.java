package model.bo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Receber extends Impressao implements Serializable {
   @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   @JoinColumn(name = "idVendas") @ManyToOne
   private Vendas vendas;

   public Receber() {
   }

   public Receber(int id, Vendas vendas, Date dtEmissao, Date dtVencimento, Date dtPagamento, float valorEmitido, float valorDesconto, float valorAcrescimo, float valorPago, String status) {
      super(dtEmissao, dtVencimento, dtPagamento, valorEmitido, valorDesconto, valorAcrescimo, valorPago, status);
      this.id = id;
      this.vendas = vendas;
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
