package controller.cadastro;

import controller.busca.ControllerBbairro;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import model.bo.Bairro;
import view.telasBusca.TelaBuscaBairro;
import view.telasCadastro.TelaCadastroBairro;

public class ControllerBairro implements ActionListener {
   TelaCadastroBairro tela;
   public static int codigo;
   
   public ControllerBairro(TelaCadastroBairro telaCadastroBairro) {
      tela = telaCadastroBairro;
      
      tela.getNovo().addActionListener(this);
      tela.getCancelar().addActionListener(this);
      tela.getBuscar().addActionListener(this);
      tela.getSair().addActionListener(this);
      tela.getGravar().addActionListener(this);
      
      codigo = 0;
      
      Ativa(true);
      
      LimpaEstadoComponentes(false);
      
      if(codigo != 0){
         Ativa(false);
         LimpaEstadoComponentes(true);
         Bairro bairro = service.BairroService.Listar(codigo);
         tela.getTextoDescricao().setText(bairro.getDescricao());
      }
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
      if(e.getSource() == tela.getNovo()){
         Ativa(false);
         LimpaEstadoComponentes(true);
         codigo = 0;
      }
      
      if(e.getSource() == tela.getCancelar()){
         Ativa(true);
         LimpaEstadoComponentes(false);
      }
      
      if(e.getSource() == tela.getGravar()){
         Bairro bairro = new Bairro();
         bairro.setDescricao(tela.getTextoDescricao().getText());
         
         if(codigo == 0){
            service.BairroService.Incluir(bairro);
         }else{
            bairro.setId(service.BairroService.Listar(codigo).getId());
            service.BairroService.Atualizar(bairro);
         }
         
         Ativa(true);
         LimpaEstadoComponentes(false);
      }
      
      if(e.getSource() == tela.getBuscar()){
         codigo = 0;
         view.telasBusca.TelaBuscaBairro telaBusca = new TelaBuscaBairro(null, true);
         new ControllerBbairro(telaBusca);
         telaBusca.setVisible(true);
         
         if(codigo != 0){
            Ativa(false);
            LimpaEstadoComponentes(true);
            Bairro bairro = service.BairroService.Listar(codigo);
            tela.getTextoDescricao().setText(bairro.getDescricao());
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
