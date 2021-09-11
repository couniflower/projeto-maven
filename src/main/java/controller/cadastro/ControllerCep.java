package controller.cadastro;

import controller.busca.ControllerBcep;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import model.bo.Bairro;
import model.bo.Cep;
import model.bo.Cidade;
import service.BairroService;
import service.CidadeService;
import view.telasBusca.TelaBuscaCep;
import view.telasCadastro.TelaCadastroCep;

public class ControllerCep implements ActionListener {
   TelaCadastroCep tela;
   public static int codigo;
   
   public ControllerCep(TelaCadastroCep telaCadastroCep) {
      tela = telaCadastroCep;
      
      tela.getNovo().addActionListener(this);
      tela.getCancelar().addActionListener(this);
      tela.getBuscar().addActionListener(this);
      tela.getSair().addActionListener(this);
      tela.getGravar().addActionListener(this);

      tela.getListaBairros().setEnabled(false);
      tela.getListaCidade().setEnabled(false);
      
      Ativa(true);
      
      //combobox
      tela.getListaCidade().removeAll();
      tela.getListaCidade().addItem(" - ");
      for(Cidade cidade : CidadeService.Listar()){
         tela.getListaCidade().addItem(cidade.getDescricao());
      }
      
      tela.getListaBairros().removeAll();
      tela.getListaBairros().addItem(" - ");
      for(Bairro bairro : BairroService.Listar()){
         tela.getListaBairros().addItem(bairro.getDescricao());
      }
      
      LimpaEstadoComponentes(false);
      
      if(codigo != 0){
            Ativa(false);
            LimpaEstadoComponentes(true);
            Cep cep = service.CepService.Listar(codigo);
            tela.getTextoCep().setText(cep.getCep());
            tela.getTextoLogradouro().setText(cep.getLogradouro());
            tela.getTextoObs().setText(cep.getObs());
            tela.getListaCidade().setSelectedItem(cep.getCidade().getDescricao());
            tela.getListaBairros().setSelectedItem(cep.getBairro().getDescricao());
      }
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
      if(e.getSource() == tela.getNovo()){
         Ativa(false);
         tela.getListaBairros().setEnabled(true);
         tela.getListaCidade().setEnabled(true);
         LimpaEstadoComponentes(true);
         codigo = 0;
      }
      
      if(e.getSource() == tela.getCancelar()){
         Ativa(true);
         tela.getListaBairros().setEnabled(false);
         tela.getListaBairros().setSelectedItem(" - ");
         tela.getListaCidade().setEnabled(false);
         tela.getListaCidade().setSelectedItem(" - ");
         LimpaEstadoComponentes(false);
      }
      
      if(e.getSource() == tela.getGravar()){
         Cep cep = new Cep();
         String string = tela.getTextoCep().getText();
         String nova = string.replace("-", "");
         cep.setCep(nova);
         cep.setLogradouro(tela.getTextoLogradouro().getText());
         cep.setObs(tela.getTextoObs().getText());

         int idC = 0;
         
         for(Cidade cidade : CidadeService.Listar()){
            if(cidade.getDescricao().equals(tela.getListaCidade().getSelectedItem())){
               idC = cidade.getId();
            }
         }
         
         Cidade cidade = CidadeService.Listar(idC);
         cep.setCidade(cidade);

         int idB = 0;
         
         for(Bairro bairro : BairroService.Listar()){
            if(bairro.getDescricao().equals(tela.getListaBairros().getSelectedItem())){
               idB = bairro.getId();
            }
         }
         
         Bairro bairro = BairroService.Listar(idB);
         cep.setBairro(bairro);
         
         if(codigo == 0){
            service.CepService.Incluir(cep);
         }else{
            cep.setId(service.CepService.Listar(codigo).getId());
            service.CepService.Atualizar(cep);
         }

         tela.getListaBairros().setEnabled(false);
         tela.getListaBairros().setSelectedItem(" - ");
         tela.getListaCidade().setEnabled(false);
         tela.getListaCidade().setSelectedItem(" - ");
         
         Ativa(true);
         LimpaEstadoComponentes(false);
      }
      
      if(e.getSource() == tela.getBuscar()){
         view.telasBusca.TelaBuscaCep telaBusca = new TelaBuscaCep(null, true);
         new ControllerBcep(telaBusca);
         telaBusca.setVisible(true);
         
         if(codigo != 0){
            Ativa(false);
            LimpaEstadoComponentes(true);
            tela.getListaCidade().setEnabled(true);
            tela.getListaBairros().setEnabled(true);
            Cep cep = service.CepService.Listar(codigo);
            tela.getTextoCep().setText(cep.getCep());
            tela.getTextoLogradouro().setText(cep.getLogradouro());
            tela.getTextoObs().setText(cep.getObs());
            tela.getListaCidade().setSelectedItem(cep.getCidade().getDescricao());
            tela.getListaBairros().setSelectedItem(cep.getBairro().getDescricao());
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
}
