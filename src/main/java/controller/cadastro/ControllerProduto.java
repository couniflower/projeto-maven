package controller.cadastro;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import controller.busca.ControllerBproduto;
import model.bo.Produto;
import view.telasBusca.TelaBuscaProduto;
import view.telasCadastro.TelaCadastroProduto;

public class ControllerProduto implements ActionListener {
   TelaCadastroProduto tela;
   public static int codigo;
   public int salva;
   
   public ControllerProduto(TelaCadastroProduto telaCadastroProduto) {
        tela = telaCadastroProduto;

        tela.getNovo().addActionListener(this);
        tela.getCancelar().addActionListener(this);
        tela.getBuscar().addActionListener(this);
        tela.getSair().addActionListener(this);
        tela.getGravar().addActionListener(this);

        Ativa(true);
        salva = 0;
        LimpaEstadoComponentes(false);

        if(codigo != 0){
            Ativa(false);
            LimpaEstadoComponentes(true);
            Produto produto = service.ProdutoService.Listar(codigo);
            tela.getTextoDescricao().setText(produto.getDescricao());
            tela.getTextoUniCompra().setText(String.valueOf(produto.getUnidCompra()));
            tela.getTextoUniVenda().setText(String.valueOf(produto.getUnidVenda()));

            String str = String.valueOf(produto.getCorrelacao()), correlacao = "", correlacaoC = "", valor = "", valorC = "";
            String[] c = str.split("\\.");
            if(str.length() <= 6){
                correlacao = c[0];
                if(c[1].length() < 2){
                    correlacaoC = c[1] + "0";
                }else{
                    correlacaoC = c[1];
                }
            }
            tela.getTextoCorrelacao().setText(correlacao);
            tela.getTextoCorrelacaoCents().setText(correlacaoC);

            str = String.valueOf(produto.getValor());
            String[] v = str.split("\\.");
            if(str.length() <= 6){
                valor = v[0];
                if(v[1].length() < 2){
                    valorC = v[1] + "0";
                }else{
                    valorC = v[1];
                }
            }
            tela.getTextoValor().setText(valor);
            tela.getTextoValorCents().setText(valorC);

            tela.getTextoCodigoBarra().setText(produto.getCodigoBarra());
            tela.getTextoObs().setText(produto.getObs());
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
         Produto produto = new Produto();
         produto.setDescricao(tela.getTextoDescricao().getText());
         produto.setObs(tela.getTextoObs().getText());
         
         if(tela.getTextoUniVenda().getText().isEmpty()){
            produto.setUnidVenda(0);
         }else{
            produto.setUnidVenda(Integer.parseInt(tela.getTextoUniVenda().getText()));
         }
         
         if(tela.getTextoUniCompra().getText().isEmpty()){
            produto.setUnidCompra(0);
         }else{
            produto.setUnidCompra(Integer.parseInt(tela.getTextoUniCompra().getText()));
         }
         
         String correlacao = "";
         if(tela.getTextoCorrelacao().getText().isEmpty()){
            correlacao += "0.";
         }else{
            correlacao += tela.getTextoCorrelacao().getText() + ".";
         }
         
         if(tela.getTextoCorrelacaoCents().getText().isEmpty() || tela.getTextoCorrelacaoCents().getText().equals("0")){
            correlacao += "0";
         }else{
            correlacao += tela.getTextoCorrelacaoCents().getText();
         }
         produto.setCorrelacao(Float.parseFloat(correlacao));

         salva = 0;

          String valor = "";

          if(tela.getTextoValor().getText().isEmpty()){
              salva = 0;
              JOptionPane.showMessageDialog(null, "Adicione o valor do produto!");
          }else{
              valor += tela.getTextoValor().getText();
              salva += 1;
          }
         
         if(tela.getTextoValorCents().getText().isEmpty()){
            valor += ".0";
         }else{
            valor += "." + tela.getTextoValorCents().getText();
         }

         produto.setValor(Float.parseFloat(valor));

          if(tela.getTextoCodigoBarra().getText().isEmpty()){
              salva = 0;
              JOptionPane.showMessageDialog(null, "Adicione o código de barras do produto!");
          }else{
              produto.setCodigoBarra(tela.getTextoCodigoBarra().getText());
              salva += 1;
          }

          if(tela.getTextoQtdEstoque().getText().isEmpty() || Integer.parseInt(tela.getTextoQtdEstoque().getText()) == 0){
              salva = 0;
              JOptionPane.showMessageDialog(null, "Adicione uma quantidade do produto cadastrado!");
          }else{
              produto.setQtdEstoque(Integer.parseInt(tela.getTextoQtdEstoque().getText()));
              salva += 1;
          }

          if(salva == 3) {
              if (codigo == 0) {
                  service.ProdutoService.Incluir(produto);
              } else {
                  produto.setId(service.ProdutoService.Listar(codigo).getId());
                  service.ProdutoService.Atualizar(produto);
              }

              Ativa(true);
              LimpaEstadoComponentes(false);
          }
      }
      
      if(e.getSource() == tela.getBuscar()){
          view.telasBusca.TelaBuscaProduto telaBusca = new TelaBuscaProduto(null, true);
          new ControllerBproduto(telaBusca);
          telaBusca.setVisible(true);

          if(codigo != 0){
              Ativa(false);
              LimpaEstadoComponentes(true);
              Produto produto = service.ProdutoService.Listar(codigo);
              tela.getTextoDescricao().setText(produto.getDescricao());
              tela.getTextoUniCompra().setText(String.valueOf(produto.getUnidCompra()));
              tela.getTextoUniVenda().setText(String.valueOf(produto.getUnidVenda()));

              String str = String.valueOf(produto.getCorrelacao()), correlacao = "", correlacaoC = "", valor = "", valorC = "";
              String[] c = str.split("\\.");

              if(str.length() <= 6){
                  correlacao = c[0];
                  if(c[1].length() < 2){
                      correlacaoC = c[1] + "0";
                  }else{
                      correlacaoC = c[1];
                  }
              }
              tela.getTextoCorrelacao().setText(correlacao);
              tela.getTextoCorrelacaoCents().setText(correlacaoC);

              str = String.valueOf(produto.getValor());
              String[] v = str.split("\\.");

              if(str.length() <= 6){
                  valor = v[0];
                  if(v[1].length() < 2){
                      valorC = v[1] + "0";
                  }else{
                      valorC = v[1];
                  }
              }
              tela.getTextoValor().setText(valor);
              tela.getTextoValorCents().setText(valorC);

              tela.getTextoQtdEstoque().setText(String.valueOf(produto.getQtdEstoque()));
              tela.getTextoCodigoBarra().setText(produto.getCodigoBarra());
              tela.getTextoObs().setText(produto.getObs());
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
