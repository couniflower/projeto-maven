package controller.busca;

import controller.cadastro.ControllerFornecedor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import model.bo.Fornecedor;
import view.telasBusca.TelaBuscaFornecedor;

public class ControllerBfornecedor implements ActionListener {
   TelaBuscaFornecedor tela;

   public ControllerBfornecedor(TelaBuscaFornecedor telaBuscaFornecedor) {
      this.tela = telaBuscaFornecedor;
      
      this.tela.getCarregar().addActionListener(this);
      this.tela.getDeletar().addActionListener(this);
      this.tela.getSair().addActionListener(this);
      
      DefaultTableModel tabela = (DefaultTableModel) this.tela.getjTable1().getModel();
      if(service.FornecedorService.Listar().size() != 0){
         ativacao("normal");
         for(Fornecedor fornecedor : service.FornecedorService.Listar()) {
            String fone1 = "", fone2 = "";
            if(fornecedor.getFone1() != null){
               fone1 = "(" + fornecedor.getFone1().substring(0, 2) + ") " + fornecedor.getFone1().substring(2, 7) + "-" + fornecedor.getFone1().substring(7, 11);
            }
            if(fornecedor.getFone2() != null){
               fone2 = "(" + fornecedor.getFone2().substring(0, 2) + ") " + fornecedor.getFone2().substring(2, 7) + "-" + fornecedor.getFone2().substring(7, 11);
            }
            String telefones = fone1 + "\n" + fone2;

            String cepFormatado = fornecedor.getCep().getCep().substring(0, 5) + "-" + fornecedor.getCep().getCep().substring(5, 8);

            String str = "<html>" + telefones.replaceAll("\n", "<br>") + "</html>";

            tabela.addRow(new Object[]{fornecedor.getId(), fornecedor.getNome(), fornecedor.getRazaoSocial(), fornecedor.getCnpj(), fornecedor.getInscEstadual(), cepFormatado, str, fornecedor.getObs()});
         }
      }

      this.tela.getjTable1().getSelectionModel().addListSelectionListener(event -> ativacao("todos"));
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
      if(e.getSource() == this.tela.getCarregar()){
         if((int) this.tela.getjTable1().getValueAt(this.tela.getjTable1().getSelectedRow(), 0) > 0){
            ControllerFornecedor.codigo = (int) this.tela.getjTable1().getValueAt(this.tela.getjTable1().getSelectedRow(), 0);
         }
         this.tela.dispose();
      }
      
      if(e.getSource() == this.tela.getDeletar()){
         DefaultTableModel tabela = (DefaultTableModel) this.tela.getjTable1().getModel();
         if((int) this.tela.getjTable1().getValueAt(this.tela.getjTable1().getSelectedRow(), 0) > 0){
            service.FornecedorService.Excluir(service.FornecedorService.Listar((int) this.tela.getjTable1().getValueAt(this.tela.getjTable1().getSelectedRow(), 0)));
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
