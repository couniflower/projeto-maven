package model.bo;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@MappedSuperclass
public abstract class Impressao {
   @Column(name = "dataEmissao") @Temporal(TemporalType.DATE)
   private Date dtEmissao;

   @Column(name = "dataVencimento") @Temporal(TemporalType.DATE)
   private Date dtVencimento;

   @Column(name = "dataPagamento") @Temporal(TemporalType.DATE)
   private Date dtPagamento;

   @Column
   private float valorEmitido;

   @Column
   private float valorDesconto;

   @Column
   private float valorAcrescimo;

   @Column
   private float valorPago;

   @Column(name = "statusReceber")
   private String status;

   public Impressao() {
   }

   public Impressao(Date dtEmissao, Date dtVencimento, Date dtPagamento, float valorEmitido, float valorDesconto, float valorAcrescimo, float valorPago, String status) {
      this.dtEmissao = dtEmissao;
      this.dtVencimento = dtVencimento;
      this.dtPagamento = dtPagamento;
      this.valorEmitido = valorEmitido;
      this.valorDesconto = valorDesconto;
      this.valorAcrescimo = valorAcrescimo;
      this.valorPago = valorPago;
      this.status = status;
   }

   public Date getDtEmissao() {
      return dtEmissao;
   }

   public void setDtEmissao(Date dtEmissao) {
      this.dtEmissao = dtEmissao;
   }

   public Date getDtVencimento() {
      return dtVencimento;
   }

   public void setDtVencimento(Date dtVencimento) {
      this.dtVencimento = dtVencimento;
   }

   public Date getDtPagamento() {
      return dtPagamento;
   }

   public void setDtPagamento(Date dtPagamento) {
      this.dtPagamento = dtPagamento;
   }

   public float getValorEmitido() {
      return valorEmitido;
   }

   public void setValorEmitido(float valorEmitido) {
      this.valorEmitido = valorEmitido;
   }

   public float getValorDesconto() {
      return valorDesconto;
   }

   public void setValorDesconto(float valorDesconto) {
      this.valorDesconto = valorDesconto;
   }

   public float getValorAcrescimo() {
      return valorAcrescimo;
   }

   public void setValorAcrescimo(float valorAcrescimo) {
      this.valorAcrescimo = valorAcrescimo;
   }

   public float getValorPago() {
      return valorPago;
   }

   public void setValorPago(float valorPago) {
      this.valorPago = valorPago;
   }

   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
      this.status = status;
   }
}
