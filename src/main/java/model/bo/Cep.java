package model.bo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Cep implements Serializable {
   @JoinColumn @ManyToOne
   private Cidade cidade;

   @JoinColumn @ManyToOne
   private Bairro bairro;

   @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   @Column
   private String cep;

   @Column
   private String logradouro;

   @Column
   private String obs;

   public Cep() {
   }

   public Cep(Cidade cidade, Bairro bairro, int id, String cep, String logradouro, String obs) {
      this.cidade = cidade;
      this.bairro = bairro;
      this.id = id;
      this.cep = cep;
      this.logradouro = logradouro;
      this.obs = obs;
   }

   public Cidade getCidade() {
      return cidade;
   }

   public void setCidade(Cidade cidade) {
      this.cidade = cidade;
   }

   public Bairro getBairro() {
      return bairro;
   }

   public void setBairro(Bairro bairro) {
      this.bairro = bairro;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getCep() {
      return cep;
   }

   public void setCep(String cep) {
      this.cep = cep;
   }

   public String getLogradouro() {
      return logradouro;
   }

   public void setLogradouro(String logradouro) {
      this.logradouro = logradouro;
   }

   public String getObs() {
      return obs;
   }

   public void setObs(String obs) {
      this.obs = obs;
   }

   @Override
   public String toString() {
      return this.getLogradouro() + " - " + this.getBairro().getDescricao() + ", " + this.getCidade().getDescricao();
   }
}
