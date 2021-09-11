package controller;

import model.bo.Item;
import model.bo.ItemVenda;
import model.bo.Produto;
import service.ProdutoService;
import view.telasBusca.TelaBuscaEstoque;
import view.telasBusca.TelaBuscaItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class ControllerFitem implements ActionListener {
    TelaBuscaEstoque telaProduto;
    TelaBuscaItem telaItem;

    public ControllerFitem(TelaBuscaEstoque telaBuscaProduto) {
        telaProduto = telaBuscaProduto;

        telaProduto.getSair().addActionListener(this);
        telaProduto.getCarregar().addActionListener(this);

        DefaultTableModel tabela = (DefaultTableModel) telaProduto.getjTable1().getModel();
        if(service.ProdutoService.Listar().size() != 0){
            for(Produto produto : service.ProdutoService.Listar()) {
                if(produto.getQtdEstoque() != 0) {
                    tabela.addRow(new Object[]{produto.getId(), produto.getDescricao(), produto.getValor(), produto.getQtdEstoque()});
                }
            }
        }

        telaProduto.getjTable1().addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                switch (evt.getKeyCode()) {
                    case KeyEvent.VK_ESCAPE -> telaProduto.dispose();
                    case KeyEvent.VK_ENTER -> carregar("produto");
                }
            }
        });
    }

    public ControllerFitem(TelaBuscaItem telaBuscaItem) {
        telaItem = telaBuscaItem;

        telaItem.getSair().addActionListener(this);
        telaItem.getCarregar().addActionListener(this);

        DefaultTableModel tabela = (DefaultTableModel) telaItem.getjTable1().getModel();
        if(ControllerFaturamento.listaItens.size() != 0){
            for(ItemVenda item : ControllerFaturamento.listaItens) {
                if(item != null) {
                    tabela.addRow(new Object[]{ControllerFaturamento.listaItens.indexOf(item) + 1, item.getProduto().getDescricao(), item.getProduto().getValor(), item.getQuantidade(), item.getSubtotal()});
                }
            }
        }

        telaItem.getjTable1().addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                switch (evt.getKeyCode()) {
                    case KeyEvent.VK_ESCAPE -> telaItem.dispose();
                    case KeyEvent.VK_ENTER -> carregar("item");
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(telaProduto != null){
            if(e.getSource() == telaProduto.getSair()){
                telaProduto.dispose();
            }

            if(e.getSource() == telaProduto.getCarregar()){
                carregar("produto");
            }
        }

        if(telaItem != null){
            if(e.getSource() == telaItem.getSair()){
                telaItem.dispose();
            }

            if(e.getSource() == telaItem.getCarregar()){
                carregar("item");
            }
        }
    }

    private void carregar(String tela) {
        if(tela.equalsIgnoreCase("produto")){
            Produto produto = ProdutoService.Listar((int) telaProduto.getjTable1().getValueAt(telaProduto.getjTable1().getSelectedRow(), 0));
            //ControllerFaturamento.tela.getCodigoBarras().setText(produto.getCodigoBarra());

            int quantidade = Integer.parseInt(telaProduto.getQuantidade().getValue().toString());
            if(quantidade < produto.getQtdEstoque()){
                ItemVenda item = new ItemVenda(produto, quantidade);

                Produto p = ProdutoService.Listar(produto.getId());

                ControllerFaturamento.listaItens.add(item);
                p.setQtdEstoque(produto.getQtdEstoque() - quantidade);
                ControllerFaturamento.valor += item.getSubtotal();

                ProdutoService.Atualizar(p);

                ControllerFaturamento.tabela.addRow(new Object[]{ControllerFaturamento.listaItens.indexOf(item) + 1, produto.getDescricao(), item.getQuantidade(), produto.getValor(), item.getSubtotal()});
            }else{
                JOptionPane.showMessageDialog(null, "Quantidade indisponível!");
            }

            telaProduto.dispose();
        }

        if(tela.equalsIgnoreCase("item")){
            int linha = (int) telaItem.getjTable1().getValueAt(telaItem.getjTable1().getSelectedRow(), 0);

            ControllerFaturamento.tabela.setValueAt("-", linha - 1, 3);
            ControllerFaturamento.tabela.setValueAt("CANCELADO", linha - 1, 4);

            Item item = null;
            int index = (int) telaItem.getjTable1().getValueAt(telaItem.getjTable1().getSelectedRow(), 0);
            for(var i : ControllerFaturamento.listaItens){
                if(ControllerFaturamento.listaItens.indexOf(i) == (index - 1)){
                    item = i;
                }
            }

            if (item != null) {
                Produto p = ProdutoService.Listar(item.getProduto().getId());
                int estoque = p.getQtdEstoque();

                p.setQtdEstoque(estoque + item.getQuantidade());

                ProdutoService.Atualizar(p);


                ControllerFaturamento.valor -= item.getSubtotal();
                ControllerFaturamento.listaItens.remove(item);
                ControllerFaturamento.listaItens.add((index - 1), null);
            }

            telaItem.dispose();
        }
    }
}
