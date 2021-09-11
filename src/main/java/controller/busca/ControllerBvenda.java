package controller.busca;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import jtextfields.WrapLineJtable;
import model.bo.ItemVenda;
import model.bo.Receber;
import model.bo.Venda;
import view.telasBusca.TelaBuscaVenda;

public class ControllerBvenda implements ActionListener {

   // FAZER DEPOIS

   TelaBuscaVenda tela;

   public ControllerBvenda(TelaBuscaVenda telaBuscaVenda) {
      this.tela = telaBuscaVenda;
      
      this.tela.getCarregar().addActionListener(this);
      this.tela.getDeletar().addActionListener(this);
      this.tela.getSair().addActionListener(this);
      
      DefaultTableModel tabela = (DefaultTableModel) this.tela.getjTable1().getModel();
      if(service.ReceberService.Listar().size() != 0){
         Ativa(true);
         for(Receber receber : service.ReceberService.Listar()) {
            List<ItemVenda> its = new ArrayList<>();
            String itens = "";
            if(service.ItemVendaService.Listar().size() != 0){
               for(ItemVenda item : service.ItemVendaService.Listar()){
                  if(item.getVenda().getIdentificacao().equals(receber.getVenda().getIdentificacao())){
                     its.add(item);
                  }
               }
            }

            for(ItemVenda item : its){
               if(its.indexOf(item) != (its.size() - 1)){
                  itens += item.getProduto().getDescricao() + " (" + item.getQuantidade() + ") - R$ " + String.format("%.2f", item.getSubtotal()) + "\n";
               }else{
                  itens += item.getProduto().getDescricao() + " (" + item.getQuantidade() + ") - R$ " + String.format("%.2f", item.getSubtotal());
               }
            }

            StringBuilder str = new StringBuilder();
            str.append("<html>");
            str.append(itens.replaceAll("\n","<br>"));
            str.append("</html>");

            tabela.addRow(new Object[]{receber.getId(), new WrapLineJtable().wrapLine(receber.getVenda().getAluno().getNome(), 5).toString(), receber.getVenda().getPersonal().getNome(), str.toString(), receber.getVenda().getValorTotal(), receber.getValorDesconto(), receber.getValorAcrescimo(), receber.getValorEmitido(), new WrapLineJtable().wrapLine(receber.getVenda().getObs(), 10).toString()});
         }
      }

      this.tela.getjTable1().getSelectionModel().addListSelectionListener(new ListSelectionListener(){
         public void valueChanged(ListSelectionEvent event) {
            AtivaTodos(true);
         }
      });
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
      if(e.getSource() == this.tela.getCarregar()){
         //ControllerVenda.codigo = (int) this.tela.getjTable1().getValueAt(this.tela.getjTable1().getSelectedRow(), 0);
         this.tela.dispose();
      }
      
      if(e.getSource() == this.tela.getDeletar()){
         DefaultTableModel tabela = (DefaultTableModel) this.tela.getjTable1().getModel();
         if((int) this.tela.getjTable1().getValueAt(this.tela.getjTable1().getSelectedRow(), 0) > 0){
            Receber receber = service.ReceberService.Listar((int) this.tela.getjTable1().getValueAt(this.tela.getjTable1().getSelectedRow(), 0));
            Venda venda = service.VendaService.Listar(receber.getVenda().getId());
            List<ItemVenda> itens = new ArrayList();
            if(service.ItemVendaService.Listar().size() != 0){
               for(ItemVenda item : service.ItemVendaService.Listar()){
                  if(item.getVenda().getIdentificacao().equals(venda.getIdentificacao())){
                     itens.add(item);
                  }
               }
            }
            service.ReceberService.Excluir(receber);
            service.VendaService.Excluir(venda);
            for(var item : itens){
               service.ItemVendaService.Excluir(item);
            }
            tabela.removeRow(this.tela.getjTable1().getSelectedRow());
         }
         Ativa(true);
      }
      
      if(e.getSource() == this.tela.getSair()){
         this.tela.dispose();
      }
   }
   
   public void Ativa(boolean estado){
      this.tela.getCarregar().setEnabled(!estado);
      this.tela.getDeletar().setEnabled(!estado);
      this.tela.getSair().setEnabled(estado);
   }
   
   public void AtivaTodos(boolean estado){
      this.tela.getCarregar().setEnabled(estado);
      this.tela.getDeletar().setEnabled(estado);
      this.tela.getSair().setEnabled(estado);
   }

}
