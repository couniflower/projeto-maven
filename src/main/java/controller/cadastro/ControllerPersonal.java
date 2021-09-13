package controller.cadastro;

import controller.DataSC;
import controller.busca.ControllerBpersonal;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import model.bo.Cep;
import model.bo.Personal;
import service.CepService;
import view.telasBusca.TelaBuscaPersonal;
import view.telasCadastro.TelaCadastroPersonal;

public class ControllerPersonal extends DataSC implements ActionListener, ItemListener {
   TelaCadastroPersonal tela;
   public static int codigo;
   
   public ControllerPersonal(TelaCadastroPersonal telaCadastroPersonal) {
      tela = telaCadastroPersonal;
      
      tela.getNovo().addActionListener(this);
      tela.getCancelar().addActionListener(this);
      tela.getBuscar().addActionListener(this);
      tela.getSair().addActionListener(this);
      tela.getGravar().addActionListener(this);
      
      tela.getListaCep().addItemListener(this);
      
      tela.getTextoEndereco().setEditable(false);
      tela.getListaCep().setEnabled(false);
      
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
         Personal personal = service.PersonalService.Listar(codigo);
         tela.getTextoNome().setText(personal.getNome());
         tela.getTextoRg().setText(personal.getRg());
         tela.getTextoObs().setText(personal.getObs());
         tela.getTextoEmail().setText(personal.getEmail());
         tela.getTextoCpf().setText(personal.getCpf());
         tela.getTextoRg().setText(personal.getRg());
         if(personal.getDtNasc() == null){
            tela.getTextoDataNasc().setText("");
         }else{
            tela.getTextoDataNasc().setText(toStringJava(personal.getDtNasc()));
         }
         tela.getTextoCompleEndereco().setText(personal.getCompleEndereco());
         tela.getTextoFone1().setText(personal.getFone1());
         tela.getTextoFone2().setText(personal.getFone2());
         String cepFormatado = personal.getCep().getCep().substring(0, 5) + "-" + personal.getCep().getCep().substring(5, 8);
         tela.getListaCep().setSelectedItem(cepFormatado);
         tela.getTextoEndereco().setText(personal.getCep().toString());
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
         Personal personal = new Personal();
         personal.setNome(tela.getTextoNome().getText());
         personal.setCompleEndereco(tela.getTextoCompleEndereco().getText());
         personal.setEmail(tela.getTextoEmail().getText());
         personal.setObs(tela.getTextoObs().getText());
         
         personal.setFone1(tela.getTextoFone1().getText().replace("(", "").replace(")", "").replace("-", "").replace(" ", ""));
         personal.setFone2(tela.getTextoFone2().getText().replace("(", "").replace(")", "").replace("-", "").replace(" ", ""));
         
         String rg = tela.getTextoRg().getText().replace("-", "").replace(".", "");
         personal.setRg(rg);
         
         String cpf = tela.getTextoCpf().getText().replace("-", "").replace(".", "");
         personal.setCpf(cpf);
         
         String data = tela.getTextoDataNasc().getText();
         String dataFormat = data.substring(6, 10) + "-" + data.substring(3, 5) + "-" + data.substring(0, 2);
         
         if(tela.getTextoDataNasc().getValue() == null){
            personal.setDtNasc(null);
         }else{
            personal.setDtNasc(toDate(tela.getTextoDataNasc().getText()));
         }
         
         int idC = 0;
         
         for(Cep cep : CepService.Listar()){
            String original = cep.getCep();
            String formatada = original.substring(0, 5) + "-" + original.substring(5, 8);
            if(formatada.equals(tela.getListaCep().getSelectedItem())){
               idC = cep.getId();
            }
         }
         
         Cep cep = CepService.Listar(idC);
         personal.setCep(cep);
         
         if(codigo == 0){
            service.PersonalService.Incluir(personal);
         }else{
            personal.setId(service.PersonalService.Listar(codigo).getId());
            service.PersonalService.Atualizar(personal);
         }
         
         Ativa(true);
         tela.getListaCep().setEnabled(false);
         tela.getListaCep().setSelectedItem(" - ");
         LimpaEstadoComponentes(false);
      }
      
      if(e.getSource() == tela.getBuscar()){
         codigo = 0;
         view.telasBusca.TelaBuscaPersonal telaBusca = new TelaBuscaPersonal(null, true);
         ControllerBpersonal.quemchama = 0;
         new ControllerBpersonal(telaBusca);
         telaBusca.setVisible(true);
         
         if(codigo != 0){
            Ativa(false);
            LimpaEstadoComponentes(true);
            tela.getListaCep().setEnabled(true);
            Personal personal = service.PersonalService.Listar(codigo);
            tela.getTextoNome().setText(personal.getNome());
            tela.getTextoRg().setText(personal.getRg());
            tela.getTextoObs().setText(personal.getObs());
            tela.getTextoEmail().setText(personal.getEmail());
            tela.getTextoCpf().setText(personal.getCpf());
            if(personal.getDtNasc() == null){
               tela.getTextoDataNasc().setText("");
            }else{
               tela.getTextoDataNasc().setText(toStringJava(personal.getDtNasc()));
            }
            tela.getTextoCompleEndereco().setText(personal.getCompleEndereco());
            tela.getTextoFone1().setText(personal.getFone1());
            tela.getTextoFone2().setText(personal.getFone2());
            String cepFormatado = personal.getCep().getCep().substring(0, 5) + "-" + personal.getCep().getCep().substring(5, 8);
            tela.getListaCep().setSelectedItem(cepFormatado);
            tela.getTextoEndereco().setText(personal.getCep().toString());
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
