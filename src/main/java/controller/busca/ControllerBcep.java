package controller.busca;

import controller.cadastro.ControllerCep;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import model.bo.Cep;
import view.telasBusca.TelaBuscaCep;

public class ControllerBcep implements ActionListener {
   TelaBuscaCep tela;
   
   public ControllerBcep(TelaBuscaCep telaBuscaCep) {
      this.tela = telaBuscaCep;
      
      this.tela.getCarregar().addActionListener(this);
      this.tela.getDeletar().addActionListener(this);
      this.tela.getSair().addActionListener(this);
      
      DefaultTableModel tabela = (DefaultTableModel) this.tela.getjTable1().getModel();
      if(service.CepService.Listar().size() != 0){
         ativacao("todos");
         for(Cep cep : service.CepService.Listar()) {
            String formatado = cep.getCep().substring(0, 5) + "-" + cep.getCep().substring(5, 8);
            tabela.addRow(new Object[]{cep.getId(), formatado, cep.getCidade().getDescricao(), cep.getBairro().getDescricao(), cep.getLogradouro(), cep.getObs()});
         }
      }

      this.tela.getjTable1().getSelectionModel().addListSelectionListener(event -> ativacao("todos"));
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if(e.getSource() == this.tela.getCarregar()){
         if((int) this.tela.getjTable1().getValueAt(this.tela.getjTable1().getSelectedRow(), 0) > 0){
            ControllerCep.codigo = (int) this.tela.getjTable1().getValueAt(this.tela.getjTable1().getSelectedRow(), 0);
         }
         this.tela.dispose();
      }
      
      if(e.getSource() == this.tela.getDeletar()){
         DefaultTableModel tabela = (DefaultTableModel) this.tela.getjTable1().getModel();
         if((int) this.tela.getjTable1().getValueAt(this.tela.getjTable1().getSelectedRow(), 0) > 0){
            service.CepService.Excluir(service.CepService.Listar((int) this.tela.getjTable1().getValueAt(this.tela.getjTable1().getSelectedRow(), 0)));
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
      if(tipo.equalsIgnoreCase("todos")){
         this.tela.getCarregar().setEnabled(true);
         this.tela.getDeletar().setEnabled(true);
         this.tela.getSair().setEnabled(true);
      }
   }
}
