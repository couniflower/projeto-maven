package controller.busca;

import controller.ControllerFaturamento;
import controller.DataSC;
import controller.cadastro.ControllerAluno;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.table.DefaultTableModel;
import model.bo.Aluno;
import view.telasBusca.TelaBuscaAluno;

public class ControllerBaluno extends DataSC implements ActionListener {
   TelaBuscaAluno tela;
   public static int quemchama;

   public ControllerBaluno(TelaBuscaAluno telaBuscaAluno) {
      this.tela = telaBuscaAluno;
      
      this.tela.getCarregar().addActionListener(this);
      this.tela.getDeletar().addActionListener(this);
      this.tela.getSair().addActionListener(this);
      
      DefaultTableModel tabela = (DefaultTableModel) this.tela.getjTable1().getModel();
      if(service.AlunoService.Listar().size() != 0){
         if(quemchama == 0){
            ativacao("todos");
         }else{
            ativacao("carregar");
         }

         for(Aluno aluno : service.AlunoService.Listar()) {
            String fone1 = "", fone2 = "";
            if(!aluno.getFone1().isEmpty()){
               if(aluno.getFone1().length() == 10){
                  fone1 += "(" + aluno.getFone1().substring(0, 2) + ") " + aluno.getFone1().substring(2, 6) + "-" + aluno.getFone1().substring(6, 10);
               }else if(aluno.getFone1().length() == 11){
                  fone1 += "(" + aluno.getFone1().substring(0, 2) + ") " + aluno.getFone1().substring(2, 7) + "-" + aluno.getFone1().substring(7, 11);
               }
            }
            if(!aluno.getFone2().isEmpty()){
               if(aluno.getFone2().length() == 10){
                  fone2 += "(" + aluno.getFone2().substring(0, 2) + ") " + aluno.getFone2().substring(2, 6) + "-" + aluno.getFone2().substring(6, 10);
               }else if(aluno.getFone2().length() == 11){
                  fone2 += "(" + aluno.getFone2().substring(0, 2) + ") " + aluno.getFone2().substring(2, 7) + "-" + aluno.getFone2().substring(7, 11);
               }
            }
            String telefones = fone1 + "\n" + fone2;

            String dataNasc = "";

            if(aluno.getDtNasc() != null){
               dataNasc = toStringJava(aluno.getDtNasc());
            }

            String cepFormatado = aluno.getCep().getCep().substring(0, 5) + "-" + aluno.getCep().getCep().substring(5, 8);

            String str = "<html>" + telefones.replaceAll("\n", "<br>") + "</html>";

            tabela.addRow(new Object[]{aluno.getId(), aluno.getNome(), aluno.getRg(), aluno.getCpf(), dataNasc, cepFormatado, str, aluno.getObs()});
         }
      }

      tela.getjTable1().addKeyListener(new java.awt.event.KeyAdapter() {
         public void keyPressed(java.awt.event.KeyEvent evt) {
            switch (evt.getKeyCode()) {
               case KeyEvent.VK_ESCAPE -> tela.dispose();
               case KeyEvent.VK_ENTER -> carregar();
            }
         }
      });

      if(quemchama == 1) {
         tela.getDeletar().setVisible(false);
         tela.getSair().setVisible(false);
         tela.getCarregar().setVisible(false);
      }

      if(quemchama == 0) {
         tela.getEnter().setVisible(false);
         tela.getEsc().setVisible(false);
      }

      this.tela.getjTable1().getSelectionModel().addListSelectionListener(event -> ativacao("todos"));
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if(e.getSource() == this.tela.getCarregar()){
         carregar();
      }
      
      if(e.getSource() == this.tela.getDeletar()){
         DefaultTableModel tabela = (DefaultTableModel) this.tela.getjTable1().getModel();
         if ((int) this.tela.getjTable1().getValueAt(this.tela.getjTable1().getSelectedRow(), 0) > 0) {
            service.AlunoService.Excluir(service.AlunoService.Listar((int) this.tela.getjTable1().getValueAt(this.tela.getjTable1().getSelectedRow(), 0)));
            tabela.removeRow(this.tela.getjTable1().getSelectedRow());
            ativacao("normal");
         }
      }
      
      if(e.getSource() == this.tela.getSair()){
         this.tela.dispose();
      }
   }
   
   public void ativacao(String tipo){
      if(tipo.equalsIgnoreCase("normal")){
         this.tela.getCarregar().setEnabled(false);
         this.tela.getDeletar().setEnabled(false);
         this.tela.getSair().setEnabled(true);
      }
      if(tipo.equalsIgnoreCase("carregar")){
         this.tela.getCarregar().setEnabled(true);
         this.tela.getDeletar().setEnabled(false);
         this.tela.getSair().setEnabled(true);
      }
      if(tipo.equalsIgnoreCase("todos")){
         this.tela.getCarregar().setEnabled(true);
         this.tela.getDeletar().setEnabled(true);
         this.tela.getSair().setEnabled(true);
      }
   }

   private void carregar() {
      if((int) this.tela.getjTable1().getValueAt(this.tela.getjTable1().getSelectedRow(), 0) > 0){
         if(quemchama == 0){
            ControllerAluno.codigo = (int) this.tela.getjTable1().getValueAt(this.tela.getjTable1().getSelectedRow(), 0);
         }else{
            ControllerFaturamento.codigoAluno = (int) this.tela.getjTable1().getValueAt(this.tela.getjTable1().getSelectedRow(), 0);

            ControllerFaturamento.tela.getTextoNomeA().setEnabled(true);
            ControllerFaturamento.tela.getTextoEnderecoA().setEnabled(true);
            ControllerFaturamento.tela.getTextoTelefonesA().setEnabled(true);
         }
      }
      this.tela.dispose();
   }
}
