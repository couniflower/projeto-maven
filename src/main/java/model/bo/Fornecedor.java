package model.bo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Fornecedor extends Pessoa implements Serializable {
   @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   @Column
   private String razaoSocial;

   @Column
   private String cnpj;

   @Column
   private String inscEstadual;

   public Fornecedor() {
   }

   public Fornecedor(int id, String razaoSocial, String cnpj, String inscEstadual, Cep cep, String nome, String compleEndereco, String fone1, String fone2, String email, String obs) {
      super(cep, nome, compleEndereco, fone1, fone2, email, obs);
      this.id = id;
      this.razaoSocial = razaoSocial;
      this.cnpj = cnpj;
      this.inscEstadual = inscEstadual;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getRazaoSocial() {
      return razaoSocial;
   }

   public void setRazaoSocial(String razaoSocial) {
      this.razaoSocial = razaoSocial;
   }

   public String getCnpj() {
      return cnpj;
   }

   public void setCnpj(String cnpj) {
      this.cnpj = cnpj;
   }

   public String getInscEstadual() {
      return inscEstadual;
   }

   public void setInscEstadual(String inscEstadual) {
      this.inscEstadual = inscEstadual;
   }
}
