package model.bo;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "compras")
public class Compra implements Serializable {
   @JoinColumn @ManyToOne
   private Fornecedor fornecedor;

   @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   @Column
   private String identificacao;

   @Column(name = "dataCompra")
   private String data;

   @Column
   private float valorTotal;

   @Column
   private String obs;

   public Compra() {
   }

   public Compra(Fornecedor fornecedor, int id, String identificacao, String data, float valorTotal, String obs) {
      this.fornecedor = fornecedor;
      this.id = id;
      this.identificacao = identificacao;
      this.data = data;
      this.valorTotal = valorTotal;
      this.obs = obs;
   }

   public Fornecedor getFornecedor() {
      return fornecedor;
   }

   public void setFornecedor(Fornecedor fornecedor) {
      this.fornecedor = fornecedor;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getIdentificacao() {
      return identificacao;
   }

   public void setIdentificacao(String identificacao) {
      this.identificacao = identificacao;
   }

   public String getData() {
      return data;
   }

   public void setData(String data) {
      this.data = data;
   }

   public float getValorTotal() {
      return valorTotal;
   }

   public void setValorTotal(float valorTotal) {
      this.valorTotal = valorTotal;
   }

   public String getObs() {
      return obs;
   }

   public void setObs(String obs) {
      this.obs = obs;
   }
}