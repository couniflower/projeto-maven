package controller.cadastro;

import controller.busca.ControllerBaluno;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import model.bo.Aluno;
import model.bo.Cep;
import service.CepService;
import view.telasBusca.TelaBuscaAluno;
import view.telasCadastro.TelaCadastroAluno;

public class ControllerAluno implements ActionListener, ItemListener {
   TelaCadastroAluno tela;
   public static int codigo;
   List<Cep> ceps;
   
   public ControllerAluno(TelaCadastroAluno telaCadastroAluno) {
      tela = telaCadastroAluno;
      
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
      ceps = CepService.Listar();
      tela.getListaCep().addItem(" - ");
      for(Cep cep : ceps){
         String formatada = cep.getCep().substring(0, 5) + "-" + cep.getCep().substring(5, 8);
         tela.getListaCep().addItem(formatada);
      }
      
      LimpaEstadoComponentes(false);
      
      if(codigo != 0){
         Ativa(false);
         LimpaEstadoComponentes(true);
         Aluno aluno = service.AlunoService.Listar(codigo);
         tela.getTextoNome().setText(aluno.getNome());
         tela.getTextoRg().setText(aluno.getRg());
         tela.getTextoObs().setText(aluno.getObs());
         tela.getTextoEmail().setText(aluno.getEmail());
         tela.getTextoCpf().setText(aluno.getCpf());
         if(aluno.getDtNasc() != null){
            tela.getTextoDataNasc().setText("");
         }else{
            //String dataNasc = aluno.getDtNasc().substring(8, 10) + aluno.getDtNasc().substring(5, 7) + aluno.getDtNasc().substring(0, 4);
            //tela.getTextoDataNasc().setText(dataNasc);
         }
         tela.getTextoCompleEndereco().setText(aluno.getCompleEndereco());
         tela.getTextoFone1().setText(aluno.getFone1());
         tela.getTextoFone2().setText(aluno.getFone2());
         String cepFormatado = aluno.getCep().getCep().substring(0, 5) + "-" + aluno.getCep().getCep().substring(5, 8);
         tela.getListaCep().setSelectedItem(cepFormatado);
         tela.getTextoEndereco().setText(aluno.getCep().toString());
      }
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if(e.getSource() == this.tela.getNovo()){
         Ativa(false);
         tela.getListaCep().setEnabled(true);
         LimpaEstadoComponentes(true);
         codigo = 0;
      }
      
      if(e.getSource() == this.tela.getCancelar()){
         Ativa(true);
         tela.getListaCep().setEnabled(false);
         tela.getListaCep().setSelectedItem(" - ");
         LimpaEstadoComponentes(false);
      }
      
      if(e.getSource() == this.tela.getGravar()){
         Aluno aluno = new Aluno();
         aluno.setNome(this.tela.getTextoNome().getText());
         aluno.setCompleEndereco(this.tela.getTextoCompleEndereco().getText());
         aluno.setEmail(this.tela.getTextoEmail().getText());
         aluno.setObs(this.tela.getTextoObs().getText());
         
         
         aluno.setFone1(this.tela.getTextoFone1().getText().replace("(", "").replace(")", "").replace("-", "").replace(" ", ""));
         aluno.setFone2(this.tela.getTextoFone2().getText().replace("(", "").replace(")", "").replace("-", "").replace(" ", ""));
         
         String rg = this.tela.getTextoRg().getText().replace("-", "").replace(".", "");
         aluno.setRg(rg);
         
         String cpf = this.tela.getTextoCpf().getText().replace("-", "").replace(".", "");
         aluno.setCpf(cpf);
         
         String data = this.tela.getTextoDataNasc().getText();
         String dataFormat = data.substring(6, 10) + "-" + data.substring(3, 5) + "-" + data.substring(0, 2);
         
         if(this.tela.getTextoDataNasc().getValue() != null){
            //aluno.setDtNasc("não especificado");
         }else{
            //aluno.setDtNasc(dataFormat);
         }
         
         int idC = 0;
         
         for(Cep cep : ceps){
            String formatada = cep.getCep().substring(0, 5) + "-" + cep.getCep().substring(5, 8);
            if(formatada.equals(this.tela.getListaCep().getSelectedItem())){
               idC = cep.getId();
            }
         }
         
         Cep cep = CepService.Listar(idC);
         aluno.setCep(cep);
         
         if(codigo == 0){
            service.AlunoService.Incluir(aluno);
         }else{
            aluno.setId(service.AlunoService.Listar(codigo).getId());
            service.AlunoService.Atualizar(aluno);
         }
         
         Ativa(true);
         tela.getListaCep().setEnabled(false);
         tela.getListaCep().setSelectedItem(" - ");
         LimpaEstadoComponentes(false);
      }
      
      if(e.getSource() == this.tela.getBuscar()){
         codigo = 0;
         view.telasBusca.TelaBuscaAluno telaBusca = new TelaBuscaAluno(null, true);
         new ControllerBaluno(telaBusca);
         telaBusca.setVisible(true);
         
         if(codigo != 0){
            Ativa(false);
            LimpaEstadoComponentes(true);
            tela.getListaCep().setEnabled(true);
            Aluno aluno = service.AlunoService.Listar(codigo);
            tela.getTextoNome().setText(aluno.getNome());
            tela.getTextoRg().setText(aluno.getRg());
            tela.getTextoObs().setText(aluno.getObs());
            tela.getTextoEmail().setText(aluno.getEmail());
            tela.getTextoCpf().setText(aluno.getCpf());
            tela.getTextoRg().setText(aluno.getRg());
            if(aluno.getDtNasc() == null){
               tela.getTextoDataNasc().setText("");
            }else{
               //String dataNasc = aluno.getDtNasc().substring(8, 10) + aluno.getDtNasc().substring(5, 7) + aluno.getDtNasc().substring(0, 4);
               //tela.getTextoDataNasc().setText(dataNasc);
            }
            tela.getTextoCompleEndereco().setText(aluno.getCompleEndereco());
            tela.getTextoFone1().setText(aluno.getFone1());
            tela.getTextoFone2().setText(aluno.getFone2());
            String cepFormatado = aluno.getCep().getCep().substring(0, 5) + "-" + aluno.getCep().getCep().substring(5, 8);
            tela.getListaCep().setSelectedItem(cepFormatado);
            tela.getTextoEndereco().setText(aluno.getCep().toString());
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
      for(Cep cep : ceps){
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
