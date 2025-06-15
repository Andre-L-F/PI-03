/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Geral.View;

/**
 *
 * @author duliz
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.*;

import java.util.ArrayList;
import javax.swing.border.LineBorder;
import Geral.Ingresso;
import Geral.IngressoController;
import Geral.Usuario;
import Geral.UsuarioController;

public class ComprarIngresso extends JFrame {

    private JTextField campoCpf;
    private JComboBox<String> comboPeca;
    private JComboBox<String> comboSessao;
    private JComboBox<String> comboArea;
    private JComboBox<Integer> comboPoltrona;
    private JComboBox<Integer> comboPeriodo;
    private JButton botaoComprar;
    private JButton botaoImagem;

    public ComprarIngresso() {

    UIManager.put("Label.font", new Font("SansSerif", Font.PLAIN, 15));
    UIManager.put("TextField.font", new Font("SansSerif", Font.PLAIN, 15));
    UIManager.put("ComboBox.font", new Font("SansSerif", Font.PLAIN, 15));
    UIManager.put("Button.font", new Font("SansSerif", Font.BOLD, 14));

    setTitle("Compra de Ingresso");
    setSize(400, 450);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    setLayout(null);

    // 🔴 Cor de fundo da janela
    getContentPane().setBackground(new Color(191,65,124));

    // CPF
    JLabel labelCpf = new JLabel("CPF:");
    labelCpf.setBounds(30, 30, 100, 25);
    labelCpf.setOpaque(true);
    labelCpf.setBackground(Color.WHITE);
    labelCpf.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
    add(labelCpf);

    campoCpf = new JTextField();
    campoCpf.setBounds(130, 30, 200, 25);
    add(campoCpf);

    // Peça
    JLabel labelPeca = new JLabel("Peça:");
    labelPeca.setBounds(30, 70, 100, 25);
    labelPeca.setOpaque(true);
    labelPeca.setBackground(Color.WHITE);
    labelPeca.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
    add(labelPeca);

    comboPeca = new JComboBox<>(new String[] {
        "Gato de botas", "Meninas Malvadas",
        "Harry Potter e a Criança Amaldiçoada", "Wicked", "Wonka"
    });
    comboPeca.setBounds(130, 70, 200, 25);
    add(comboPeca);

    // Sessão
    JLabel labelSessao = new JLabel("Sessão:");
    labelSessao.setBounds(30, 110, 100, 25);
    labelSessao.setOpaque(true);
    labelSessao.setBackground(Color.WHITE);
    labelSessao.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
    add(labelSessao);

    comboSessao = new JComboBox<>(new String[] { "Manhã", "Tarde", "Noite" });
    comboSessao.setBounds(130, 110, 200, 25);
    add(comboSessao);

    // Área
    JLabel labelArea = new JLabel("Área:");
    labelArea.setBounds(30, 150, 100, 25);
    labelArea.setOpaque(true);
    labelArea.setBackground(Color.WHITE);
    labelArea.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
    add(labelArea);

    comboArea = new JComboBox<>(new String[] {
        "Plateia A", "Plateia B", "Frisa", "Camarote", "Balcao Nobre"
    });
    comboArea.setBounds(130, 150, 200, 25);
    add(comboArea);

    // Poltrona
    JLabel labelPoltrona = new JLabel("Poltrona:");
    labelPoltrona.setBounds(30, 190, 100, 25);
    labelPoltrona.setOpaque(true);
    labelPoltrona.setBackground(Color.WHITE);
    labelPoltrona.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
    add(labelPoltrona);

    comboPoltrona = new JComboBox<>();
    comboPoltrona.setBounds(130, 190, 200, 25);
    add(comboPoltrona);

    // Dia do mês
    JLabel labelPeriodo = new JLabel("Dia do mês:");
    labelPeriodo.setBounds(30, 230, 100, 25);
    labelPeriodo.setOpaque(true);
    labelPeriodo.setBackground(Color.WHITE);
    labelPeriodo.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
    add(labelPeriodo);

    comboPeriodo = new JComboBox<>();
    for (int i = 1; i <= 30; i++) {
        comboPeriodo.addItem(i);
    }
    comboPeriodo.setBounds(130, 230, 200, 25);
    add(comboPeriodo);

    // Botões
    botaoComprar = new JButton("Comprar");
    botaoComprar.setBounds(130, 280, 120, 30);
    add(botaoComprar);

    botaoImagem = new JButton("Ver Mapa");
    botaoImagem.setBounds(130, 330, 120, 30);
    add(botaoImagem);

    // Atualizar poltronas ao mudar opções
    comboArea.addActionListener(e -> atualizarPoltronas());
    comboPeca.addActionListener(e -> atualizarPoltronas());
    comboSessao.addActionListener(e -> atualizarPoltronas());
    comboPeriodo.addActionListener(e -> atualizarPoltronas());

    botaoComprar.addActionListener(e -> comprarIngresso());
    botaoImagem.addActionListener(e -> abrirImagem());

    atualizarPoltronas();

    setVisible(true);
}

    private void atualizarPoltronas() {
        comboPoltrona.removeAllItems();

        String peca = (String) comboPeca.getSelectedItem();
        String sessao = (String) comboSessao.getSelectedItem();
        String area = (String) comboArea.getSelectedItem();
        int periodo = (Integer) comboPeriodo.getSelectedItem();

        ArrayList<Integer> ocupadas = IngressoController.poltronasOcupadas(peca, sessao, area, periodo);
        int total = IngressoController.getTotalPoltronas(area);

        for (int i = 1; i <= total; i++) {
            if (!ocupadas.contains(i)) {
                comboPoltrona.addItem(i);
            }
        }

        if (comboPoltrona.getItemCount() == 0) {
            comboPoltrona.addItem(-1); // Sem vagas
        }
    }

    private void comprarIngresso() {
        String cpf = campoCpf.getText();
        Usuario usuario = UsuarioController.buscarPorCpf(cpf);

        if (usuario == null) {
            JOptionPane.showMessageDialog(this, "CPF não encontrado. Cadastre o usuário primeiro.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String peca = (String) comboPeca.getSelectedItem();
        String sessao = (String) comboSessao.getSelectedItem();
        String area = (String) comboArea.getSelectedItem();
        int poltrona = (Integer) comboPoltrona.getSelectedItem();
        int periodo = (Integer) comboPeriodo.getSelectedItem();

        if (poltrona == -1) {
            JOptionPane.showMessageDialog(this, "Não há poltronas disponíveis nesta área.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double preco = IngressoController.getPrecoPorArea(area);
        Ingresso ingresso = new Ingresso(cpf, peca, sessao, area, poltrona, preco, periodo);

        boolean sucesso = IngressoController.comprarIngresso(ingresso);

        if (sucesso) {
            JOptionPane.showMessageDialog(this, "Ingresso comprado com sucesso.\nValor: R$ " + preco);
            atualizarPoltronas();
        } else {
            JOptionPane.showMessageDialog(this, "Poltrona já ocupada neste período!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void abrirImagem() {
    JFrame frameImagem = new JFrame("Mapa do Teatro");
    frameImagem.setSize(620, 470); // um pouco maior que 610x431 para caber com bordas
    frameImagem.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frameImagem.setLocationRelativeTo(null);

    ImageIcon originalIcon = new ImageIcon(getClass().getResource("/imagens/Plateia.png"));
    Image imagemOriginal = originalIcon.getImage();
    Image imagemReduzida = imagemOriginal.getScaledInstance(610, 431, Image.SCALE_SMOOTH);

    ImageIcon imagemIconReduzida = new ImageIcon(imagemReduzida);
    JLabel labelImagem = new JLabel(imagemIconReduzida);
    frameImagem.add(labelImagem);

    frameImagem.setVisible(true);
}

    public static void main(String[] args) {
        new ComprarIngresso();
    }
}