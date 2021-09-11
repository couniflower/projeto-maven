package model.bo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "vendas")
public class Venda implements Serializable {
   @JoinColumn @ManyToOne
   private Personal personal;

   @JoinColumn @ManyToOne
   private Aluno aluno;

   @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   @Column
   private String identificacao;

   @Column(name = "dataVenda") @Temporal(TemporalType.DATE)
   private Date data;

   @Column
   private float valorTotal;

   @Column
   private String obs;

   public Venda() {
   }

   public Venda(Personal personal, Aluno aluno, int id, String identificacao, Date data, float valorTotal, String obs) {
      this.personal = personal;
      this.aluno = aluno;
      this.id = id;
      this.identificacao = identificacao;
      this.data = data;
      this.valorTotal = valorTotal;
      this.obs = obs;
   }

   public Personal getPersonal() {
      return personal;
   }

   public void setPersonal(Personal personal) {
      this.personal = personal;
   }

   public Aluno getAluno() {
      return aluno;
   }

   public void setAluno(Aluno aluno) {
      this.aluno = aluno;
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

   public Date getData() {
      return data;
   }

   public void setData(Date data) {
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