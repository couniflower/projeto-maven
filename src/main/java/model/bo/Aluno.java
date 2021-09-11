package model.bo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Aluno extends Pessoa implements Serializable {
   @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   @Column
   private String rg;

   @Column
   private String cpf;

   @Column(name = "dataNasc") @Temporal(TemporalType.DATE)
   private Date dtNasc;

   public Aluno() {
   }

   public Aluno(int id, String rg, String cpf, Date dtNasc, Cep cep, String nome, String compleEndereco, String fone1, String fone2, String email, String obs) {
      super(cep, nome, compleEndereco, fone1, fone2, email, obs);
      this.id = id;
      this.rg = rg;
      this.cpf = cpf;
      this.dtNasc = dtNasc;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getRg() {
      return rg;
   }

   public void setRg(String rg) {
      this.rg = rg;
   }

   public String getCpf() {
      return cpf;
   }

   public void setCpf(String cpf) {
      this.cpf = cpf;
   }

   public Date getDtNasc() {
      return dtNasc;
   }

   public void setDtNasc(Date dtNasc) {
      this.dtNasc = dtNasc;
   }
}
