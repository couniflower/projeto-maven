package model.bo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Receber extends Impressao implements Serializable {
   @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   @JoinColumn @ManyToOne
   private Venda venda;

   public Receber() {
   }

   public Receber(int id, Venda venda, Date dtEmissao, Date dtVencimento, Date dtPagamento, float valorEmitido, float valorDesconto, float valorAcrescimo, float valorPago, String status) {
      super(dtEmissao, dtVencimento, dtPagamento, valorEmitido, valorDesconto, valorAcrescimo, valorPago, status);
      this.id = id;
      this.venda = venda;
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
