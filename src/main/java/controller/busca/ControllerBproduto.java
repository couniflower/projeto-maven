package controller.busca;

import controller.cadastro.ControllerProduto;
import model.bo.Produto;
import view.telasBusca.TelaBuscaProduto;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerBproduto implements ActionListener {
   TelaBuscaProduto tela;

   public ControllerBproduto(TelaBuscaProduto telaBuscaProduto) {
      this.tela = telaBuscaProduto;
      
      this.tela.getCarregar().addActionListener(this);
      this.tela.getDeletar().addActionListener(this);
      this.tela.getSair().addActionListener(this);
      
      DefaultTableModel tabela = (DefaultTableModel) this.tela.getjTable1().getModel();
      if(service.ProdutoService.Listar().size() != 0){
         ativacao("todos");

         for(Produto produto : service.ProdutoService.Listar()) {
            tabela.addRow(new Object[]{produto.getId(), produto.getDescricao(), produto.getValor(), produto.getQtdEstoque()});
         }
      }

      this.tela.getjTable1().getSelectionModel().addListSelectionListener(event -> ativacao("todos"));
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if(e.getSource() == this.tela.getCarregar()){
         if((int) this.tela.getjTable1().getValueAt(this.tela.getjTable1().getSelectedRow(), 0) > 0){
            ControllerProduto.codigo = (int) this.tela.getjTable1().getValueAt(this.tela.getjTable1().getSelectedRow(), 0);
         }
         this.tela.dispose();
      }
      
      if(e.getSource() == this.tela.getDeletar()){
         DefaultTableModel tabela = (DefaultTableModel) this.tela.getjTable1().getModel();
         if((int) this.tela.getjTable1().getValueAt(this.tela.getjTable1().getSelectedRow(), 0) > 0){
            service.ProdutoService.Excluir(service.ProdutoService.Listar((int) this.tela.getjTable1().getValueAt(this.tela.getjTable1().getSelectedRow(), 0)));
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
