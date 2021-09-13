package controller;

import controller.busca.ControllerBaluno;
import controller.busca.ControllerBpersonal;
import model.bo.*;
import service.*;
import view.telasBusca.*;
import view.telasCadastro.TelaVendas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ControllerFaturamento extends DataSC {

    public static TelaVendas tela = new TelaVendas();
    public static List<ItensVenda> listaItens = new ArrayList<>();
    public static DefaultTableModel tabela;
    public static float valor;
    public LocalDateTime dataHora;
    public Vendas vendas = new Vendas();
    public static int codigoAluno;
    public static int codigoPersonal;
    public static int codigoVenda;

    public ControllerFaturamento(TelaVendas telaVendas) {
        tela = telaVendas;

        start();
        horario(false);

        ativacao(false);
        tela.getTextoStatus().setEditable(false);
        tela.getTextoStatus().setText("OFF");

        tabela = (DefaultTableModel) tela.getListaItens().getModel();
        valor = 0;
        tela.getValorTotal().setText("R$ 0,00");
        listaItens.clear();

        tela.getCodigoBarras().addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                switch (evt.getKeyCode()) {
                    case KeyEvent.VK_ENTER -> // inserir o item digitado
                            inserirItem();
                    case KeyEvent.VK_F1 -> // botão busca produto
                            buscarProduto();
                    case KeyEvent.VK_F2 -> // criar novo faturamento
                            novoFaturamento();
                    case KeyEvent.VK_F3 -> // cancelar faturamento
                            cancelarFaturamento();
                    case KeyEvent.VK_F4 -> // encerrar faturamento
                            encerrarFaturamento();
                    case KeyEvent.VK_F5 -> // cancelar item faturado
                            cancelarItem();
                    case KeyEvent.VK_F6 -> // buscar aluno
                            buscarAluno();
                    case KeyEvent.VK_F7 -> // buscar personal
                            buscarPersonal();
                }
            }
        });
    }

    private void ativacao(boolean estado){
        tela.getListaItens().setEnabled(estado);
        tela.getTextoNomeA().setEnabled(estado);
        tela.getTextoNomeP().setEnabled(estado);
        tela.getTextoEnderecoA().setEnabled(estado);
        tela.getTextoEnderecoP().setEnabled(estado);
        tela.getTextoTelefonesA().setEnabled(estado);
        tela.getTextoTelefonesP().setEnabled(estado);
    }

    private void limpar(){
        tela.getCodigoBarras().setText(null);
        tela.getTextoStatus().setText("OFF");
        tela.getListaItens().removeAll();
        ((DefaultTableModel) tela.getListaItens().getModel()).setRowCount(0);
        tela.getTextoNomeA().setText(null);
        tela.getTextoNomeP().setText(null);
        tela.getTextoEnderecoA().setText(null);
        tela.getTextoEnderecoP().setText(null);
        tela.getTextoTelefonesA().setText(null);
        tela.getTextoTelefonesP().setText(null);
    }

    private void horario(boolean ativado){
        tela.getTextoData().setEditable(false);
        tela.getTextoData().setEnabled(ativado);
        tela.getTextoHora().setEditable(false);
        tela.getTextoHora().setEnabled(ativado);
    }

    public void start() {
        //cria uma thread
        Thread th = new Thread(() -> {
            while(true) { //roda indefinidamente
                dataHora = LocalDateTime.now(); //pega a hora do sistema
                tela.getTextoData().setText(dataHora.format(data));
                tela.getTextoHora().setText(dataHora.format(hora));
                try {
                    Thread.sleep(1000); //espera 1 segundo para fazer a nova evolução
                } catch(InterruptedException ex){
                    //é algo terrível a se fazer mas pelo jeito a API medonha do Java exige
                }
            }
        });
        th.start();
    }

    private void buscarAluno() {
        if(tela.getTextoStatus().getText().equalsIgnoreCase("off"))
            JOptionPane.showMessageDialog(null, "Não existe um faturamento em andamento para cancelar!\nAperte F2 para criar um novo faturamento.");
        else {
            tela.getTextoNomeA().setEditable(false);
            tela.getTextoEnderecoA().setEditable(false);
            tela.getTextoTelefonesA().setEditable(false);
            TelaBuscaAluno telaBuscaAluno = new TelaBuscaAluno(null, true);
            ControllerBaluno.quemchama = 1;
            new ControllerBaluno(telaBuscaAluno);
            telaBuscaAluno.setVisible(true);

            Aluno aluno = AlunoService.Listar(codigoAluno);

            if(aluno != null) {
                tela.getTextoNomeA().setText(aluno.getNome());
                tela.getTextoEnderecoA().setText(aluno.getCep().toString() + "\n" + aluno.getCompleEndereco());
                tela.scroll();

                String telefones = "";
                if (aluno.getFone1() != null) {
                    if (aluno.getFone1().length() == 10) {
                        telefones += "(" + aluno.getFone1().substring(0, 2) + ") " + aluno.getFone1().substring(2, 6) + "-" + aluno.getFone1().substring(6, 10);
                    } else if (aluno.getFone1().length() == 11) {
                        telefones += "(" + aluno.getFone1().substring(0, 2) + ") " + aluno.getFone1().substring(2, 7) + "-" + aluno.getFone1().substring(7, 11);
                    }
                }
                if (aluno.getFone2() != null) {
                    telefones += "\n";
                    if (aluno.getFone2().length() == 10) {
                        telefones += "(" + aluno.getFone2().substring(0, 2) + ") " + aluno.getFone2().substring(2, 6) + "-" + aluno.getFone2().substring(6, 10);
                    } else if (aluno.getFone2().length() == 11) {
                        telefones += "(" + aluno.getFone2().substring(0, 2) + ") " + aluno.getFone2().substring(2, 7) + "-" + aluno.getFone2().substring(7, 11);
                    }
                }
                tela.getTextoTelefonesA().setText(telefones);

                vendas.setAluno(aluno);
            }
        }
    }

    private void buscarPersonal() {
        if(tela.getTextoStatus().getText().equalsIgnoreCase("off"))
            JOptionPane.showMessageDialog(null, "Não existe um faturamento em andamento para cancelar!\nAperte F2 para criar um novo faturamento.");
        else {
            tela.getTextoNomeP().setEditable(false);
            tela.getTextoEnderecoP().setEditable(false);
            tela.getTextoTelefonesP().setEditable(false);
            TelaBuscaPersonal telaBuscaPersonal = new TelaBuscaPersonal(null, true);
            ControllerBpersonal.quemchama = 1;
            new ControllerBpersonal(telaBuscaPersonal);
            telaBuscaPersonal.setVisible(true);

            Personal personal = PersonalService.Listar(codigoPersonal);

            if(personal != null) {
                tela.getTextoNomeP().setText(personal.getNome());
                tela.getTextoEnderecoP().setText(personal.getCep().toString());
                tela.scroll();

                String telefones = "";
                if (personal.getFone1() != null) {
                    if (personal.getFone1().length() == 10) {
                        telefones += "(" + personal.getFone1().substring(0, 2) + ") " + personal.getFone1().substring(2, 6) + "-" + personal.getFone1().substring(6, 10);
                    } else if (personal.getFone1().length() == 11) {
                        telefones += "(" + personal.getFone1().substring(0, 2) + ") " + personal.getFone1().substring(2, 7) + "-" + personal.getFone1().substring(7, 11);
                    }
                }
                if (personal.getFone2() != null) {
                    telefones += "\n";
                    if (personal.getFone2().length() == 10) {
                        telefones += "(" + personal.getFone2().substring(0, 2) + ") " + personal.getFone2().substring(2, 6) + "-" + personal.getFone2().substring(6, 10);
                    } else if (personal.getFone2().length() == 11) {
                        telefones += "(" + personal.getFone2().substring(0, 2) + ") " + personal.getFone2().substring(2, 7) + "-" + personal.getFone2().substring(7, 11);
                    }
                }
                tela.getTextoTelefonesP().setText(telefones);

                vendas.setPersonal(personal);
            }
        }
    }

    private void buscarProduto() {
        if(tela.getTextoStatus().getText().equalsIgnoreCase("off")){
            JOptionPane.showMessageDialog(null, "Não existe um faturamento em andamento!\nAperte F2 para criar um novo faturamento.");
        }else{
            TelaBuscaEstoque telaBuscaProduto = new TelaBuscaEstoque(null, true);
            new ControllerFitem(telaBuscaProduto);
            telaBuscaProduto.setVisible(true);

            DecimalFormat df = new DecimalFormat("0.00");
            String valor = String.valueOf(df.format(ControllerFaturamento.valor));
            tela.getValorTotal().setText("R$ " + valor);
        }
    }

    private void atualizaEstoque(Produto produto, int quantidade) {
        if(quantidade < produto.getQtdEstoque()){
            ItensVenda item = new ItensVenda(produto, quantidade);
            listaItens.add(item);
            valor += item.getSubtotal();

            Produto p = ProdutoService.Listar(produto.getId());
            p.setQtdEstoque(produto.getQtdEstoque() - quantidade);
            ProdutoService.Atualizar(p);

            tabela.addRow(new Object[]{listaItens.indexOf(item) + 1, produto.getDescricao(), item.getQuantidade(), produto.getValor(), item.getSubtotal()});

            DecimalFormat df = new DecimalFormat("0.00");
            String valor = String.valueOf(df.format(ControllerFaturamento.valor));
            tela.getValorTotal().setText("R$ " + valor);

            tela.getCodigoBarras().setText(null);
        }else{
            JOptionPane.showMessageDialog(null, "Quantidade indisponível!");
        }
    }

    private void inserirItem() {
        if(tela.getTextoStatus().getText().equalsIgnoreCase("off")){
            JOptionPane.showMessageDialog(null, "Não existe um faturamento em andamento!\nAperte F2 para criar um novo faturamento.");
        }else{
            String codigo = tela.getCodigoBarras().getText();
            Produto produto;
            if(codigo.contains("x")){
                produto = ProdutoService.Listar(codigo.substring(codigo.indexOf('x') + 1));
                if(produto == null){
                    JOptionPane.showMessageDialog(null, "Esse código de barras não existe!");
                }else{
                    int qtd = Integer.parseInt(codigo.substring(0, codigo.indexOf('x')));
                    atualizaEstoque(produto, qtd);
                }
            }else{
                produto = ProdutoService.Listar(codigo);
                if (produto == null) {
                    JOptionPane.showMessageDialog(null, "Esse código de barras não existe!");
                } else {
                    atualizaEstoque(produto, 1);
                }
            }
        }
    }

    private void novoFaturamento() {
        if(tela.getTextoStatus().getText().equalsIgnoreCase("off")){
            ativacao(true);
            tela.getTextoStatus().setText("EM ANDAMENTO");
            vendas.setData(toDate(dataHora));
            vendas.setIdentificacao("Venda " + dataHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            horario(true);
        }else{
            JOptionPane.showMessageDialog(null, "Um novo faturamento não pode ser feito");
        }
    }

    private void cancelarItem() {
        if(tela.getTextoStatus().getText().equalsIgnoreCase("off")){
            JOptionPane.showMessageDialog(null, "Não existe um faturamento em andamento!\nAperte F2 para criar um novo faturamento.");
        }else{
            if(listaItens.isEmpty()){
                JOptionPane.showMessageDialog(null, "Não existe nenhum item na venda!\nInsira apertando F1 ou no botão 'Pesquisar Produto'");
            }else{
                TelaBuscaItem telaBuscaItem = new TelaBuscaItem(null, true);
                new ControllerFitem(telaBuscaItem);
                telaBuscaItem.setVisible(true);

                DecimalFormat df = new DecimalFormat("0.00");
                tela.getValorTotal().setText("R$ " + df.format(valor));
            }
        }
    }

    private void cancelarFaturamento() {
        if(tela.getTextoStatus().getText().equalsIgnoreCase("off"))
            JOptionPane.showMessageDialog(null, "Não existe um faturamento em andamento para cancelar!\nAperte F2 para criar um novo faturamento.");
        else{
            limpar();
            tela.getValorTotal().setText("R$ 0,00");
            valor = 0;
            ativacao(false);
        }
    }

    private void encerrarFaturamento() {
        if(tela.getTextoStatus().getText().equalsIgnoreCase("off")){
            JOptionPane.showMessageDialog(null, "Não existe um faturamento em andamento para encerrar!\nAperte F2 para criar um novo faturamento.");
        }else{
            if(vendas.getAluno() == null){
                JOptionPane.showMessageDialog(null, "Aluno não selecionado!");
            }else if(vendas.getPersonal() == null){
                JOptionPane.showMessageDialog(null, "Personal não selecionado!");
            }else{
                vendas.setValorTotal(valor);
                VendaService.Incluir(vendas);

                for(var v : VendaService.Listar()){
                    if(v.getIdentificacao().equalsIgnoreCase(vendas.getIdentificacao())){
                        codigoVenda = v.getId();
                    }
                }

                vendas = VendaService.Listar(codigoVenda);

                for(ItensVenda item : listaItens){
                    if(item != null) {
                        item.setVenda(vendas);
                        ItemVendaService.Incluir(item);
                    }
                }

                limpar();
                tela.getValorTotal().setText("R$ 0,00");
                valor = 0;
                adicionarRecebimento(vendas);
                ativacao(false);
            }
        }
    }

    private void adicionarRecebimento(Vendas vendas) {
        Receber receber = new Receber();

        receber.setVenda(vendas);
        receber.setDtEmissao(vendas.getData());
        receber.setValorEmitido(vendas.getValorTotal());

        LocalDateTime dataVenda = toLocalDateTime(vendas.getData());
        LocalDateTime vencimento = dataVenda.plusMonths(2);
        receber.setDtVencimento(toDate(vencimento));

        receber.setStatus("a");

        ReceberService.Incluir(receber);
    }
}