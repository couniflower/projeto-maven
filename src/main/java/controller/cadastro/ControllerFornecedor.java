package controller.cadastro;

import controller.busca.ControllerBfornecedor;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import model.bo.Cep;
import model.bo.Fornecedor;
import service.CepService;
import view.telasBusca.TelaBuscaFornecedor;
import view.telasCadastro.TelaCadastroFornecedor;

public class ControllerFornecedor implements ActionListener, ItemListener {
   TelaCadastroFornecedor tela;
   public static int codigo;
   
   public ControllerFornecedor(TelaCadastroFornecedor telaCadastroFornecedor) {
      tela = telaCadastroFornecedor;
      
      tela.getNovo().addActionListener(this);
      tela.getCancelar().addActionListener(this);
      tela.getBuscar().addActionListener(this);
      tela.getSair().addActionListener(this);
      tela.getGravar().addActionListener(this);
      
      tela.getListaCep().addItemListener(this);
      
      tela.getTextoEndereco().setEditable(false);
      tela.getListaCep().setEnabled(false);
      
      codigo = 0;
      
      Ativa(true);
      
      //combobox
      tela.getListaCep().removeAll();
      tela.getListaCep().addItem(" - ");
      for(Cep cep : CepService.Listar()){
         String formatada = cep.getCep().substring(0, 5) + "-" + cep.getCep().substring(5, 8);
         tela.getListaCep().addItem(formatada);
      }

      tela.getListaCep().setEnabled(false);
      
      LimpaEstadoComponentes(false);
      
      if(codigo != 0){
         Ativa(false);
         LimpaEstadoComponentes(true);
         Fornecedor fornecedor = service.FornecedorService.Listar(codigo);
         tela.getTextoNome().setText(fornecedor.getNome());
         tela.getTextoRazaoSocial().setText(fornecedor.getRazaoSocial());
         tela.getTextoObs().setText(fornecedor.getObs());
         tela.getTextoEmail().setText(fornecedor.getEmail());
         tela.getTextoCnpj().setText(fornecedor.getCnpj());
         tela.getTextoInscEstadual().setText(fornecedor.getInscEstadual());
         tela.getTextoCompleEndereco().setText(fornecedor.getCompleEndereco());
         tela.getTextoFone1().setText(fornecedor.getFone1());
         tela.getTextoFone2().setText(fornecedor.getFone2());
         String cepFormatado = fornecedor.getCep().getCep().substring(0, 5) + "-" + fornecedor.getCep().getCep().substring(5, 8);
         tela.getListaCep().setSelectedItem(cepFormatado);
         tela.getTextoEndereco().setText(fornecedor.getCep().toString());
      }
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if(e.getSource() == tela.getNovo()){
         Ativa(false);
         tela.getListaCep().setEnabled(true);
         LimpaEstadoComponentes(true);
         codigo = 0;
      }
      
      if(e.getSource() == tela.getCancelar()){
         Ativa(true);
         tela.getListaCep().setEnabled(false);
         tela.getListaCep().setSelectedItem(" - ");
         LimpaEstadoComponentes(false);
      }
      
      if(e.getSource() == tela.getGravar()){
         Fornecedor fornecedor = new Fornecedor();
         fornecedor.setNome(tela.getTextoNome().getText());
         fornecedor.setCompleEndereco(tela.getTextoCompleEndereco().getText());
         fornecedor.setEmail(tela.getTextoEmail().getText());
         fornecedor.setObs(tela.getTextoObs().getText());
         fornecedor.setRazaoSocial(tela.getTextoRazaoSocial().getText());
         fornecedor.setCnpj(tela.getTextoCnpj().getText());
         fornecedor.setInscEstadual(tela.getTextoInscEstadual().getText());

         fornecedor.setFone1(tela.getTextoFone1().getText().replace("(", "").replace(")", "").replace("-", "").replace(" ", ""));
         fornecedor.setFone2(tela.getTextoFone2().getText().replace("(", "").replace(")", "").replace("-", "").replace(" ", ""));

         int idC = 0;
         
         for(Cep cep : CepService.Listar()){
            String original = cep.getCep();
            String formatada = original.substring(0, 5) + "-" + original.substring(5, 8);
            if(formatada.equals(tela.getListaCep().getSelectedItem())){
               idC = cep.getId();
            }
         }
         
         Cep cep = CepService.Listar(idC);
         fornecedor.setCep(cep);
         
         if(codigo == 0){
            service.FornecedorService.Incluir(fornecedor);
         }else{
            fornecedor.setId(service.FornecedorService.Listar(codigo).getId());
            service.FornecedorService.Atualizar(fornecedor);
         }
         
         Ativa(true);
         tela.getListaCep().setEnabled(false);
         tela.getListaCep().setSelectedItem(" - ");
         LimpaEstadoComponentes(false);
      }
      
      if(e.getSource() == tela.getBuscar()){
         codigo = 0;
         view.telasBusca.TelaBuscaFornecedor telaBusca = new TelaBuscaFornecedor(null, true);
         new ControllerBfornecedor(telaBusca);
         telaBusca.setVisible(true);
         
         if(codigo != 0){
            Ativa(false);
            LimpaEstadoComponentes(true);
            tela.getListaCep().setEnabled(true);
            Fornecedor fornecedor = service.FornecedorService.Listar(codigo);
            tela.getTextoNome().setText(fornecedor.getNome());
            tela.getTextoRazaoSocial().setText(fornecedor.getRazaoSocial());
            tela.getTextoObs().setText(fornecedor.getObs());
            tela.getTextoEmail().setText(fornecedor.getEmail());
            tela.getTextoCnpj().setText(fornecedor.getCnpj());
            tela.getTextoInscEstadual().setText(fornecedor.getInscEstadual());
            tela.getTextoCompleEndereco().setText(fornecedor.getCompleEndereco());
            tela.getTextoFone1().setText(fornecedor.getFone1());
            tela.getTextoFone2().setText(fornecedor.getFone2());
            String cepFormatado = fornecedor.getCep().getCep().substring(0, 5) + "-" + fornecedor.getCep().getCep().substring(5, 8);
            tela.getListaCep().setSelectedItem(cepFormatado);
         tela.getTextoEndereco().setText(fornecedor.getCep().toString());
         }
      }
      
      if(e.getSource() == tela.getSair()){
         tela.dispose();
      }
   }

   //mexer nos botões
   public void Ativa(boolean estado){
      tela.getNovo().setEnabled(estado);
      tela.getCancelar().setEnabled(!estado);
      tela.getBuscar().setEnabled(estado);
      tela.getSair().setEnabled(estado);
      tela.getGravar().setEnabled(!estado);
   }
   
   public void LimpaEstadoComponentes(boolean estado){
      Component[] componentes = tela.getCenter().getComponents();
      for(Component componente : componentes){
         if(componente instanceof JTextField){
            ((JTextField) componente).setText("");
            componente.setEnabled(estado);
         }
         
         if(componente instanceof JFormattedTextField){
            ((JFormattedTextField) componente).setValue(null);
            componente.setEnabled(estado);
         }
      }
   }

   @Override
   public void itemStateChanged(ItemEvent e) {
      for(Cep cep : CepService.Listar()){
         String formatada = cep.getCep().substring(0, 5) + "-" + cep.getCep().substring(5, 8);
         if(tela.getListaCep().getSelectedItem() != " - "){
            if(formatada.equals(tela.getListaCep().getSelectedItem())){
               tela.getTextoEndereco().setText(cep.toString());
            }
         }else{
            tela.getTextoEndereco().setText(null);
         }
      }
   }
}
