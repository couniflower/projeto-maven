package controller.cadastro;

import controller.busca.ControllerBcidade;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import model.bo.Cidade;
import view.telasBusca.TelaBuscaCidade;
import view.telasCadastro.TelaCadastroCidade;

public class ControllerCidade implements ActionListener {
   TelaCadastroCidade tela;
   public static int codigo;

   public ControllerCidade(TelaCadastroCidade telaCadastroCidade) {
      tela = telaCadastroCidade;
      
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
         Cidade cidade = service.CidadeService.Listar(codigo);
         tela.getTextoDescricao().setText(cidade.getDescricao());
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
         Cidade cidade = new Cidade();
         cidade.setDescricao(tela.getTextoDescricao().getText());
         
         if(codigo == 0){
            service.CidadeService.Incluir(cidade);
         }else{
            cidade.setId(service.CidadeService.Listar(codigo).getId());
            service.CidadeService.Atualizar(cidade);
         }
         
         Ativa(true);
         LimpaEstadoComponentes(false);
      }
      
      if(e.getSource() == tela.getBuscar()){
         codigo = 0;
         view.telasBusca.TelaBuscaCidade telaBusca = new TelaBuscaCidade(null, true);
         new ControllerBcidade(telaBusca);
         telaBusca.setVisible(true);
         
         if(codigo != 0){
            Ativa(false);
            LimpaEstadoComponentes(true);
            Cidade cidade = service.CidadeService.Listar(codigo);
            tela.getTextoDescricao().setText(cidade.getDescricao());
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
