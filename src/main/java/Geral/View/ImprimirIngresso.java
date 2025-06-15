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
import javax.swing.*;
import java.util.ArrayList;
import javax.swing.border.LineBorder;
import Geral.Ingresso;
import Geral.IngressoController;
import Geral.Usuario;
import Geral.UsuarioController;

public class ImprimirIngresso extends JFrame {

    private JTextField campoCpf;
    private JButton botaoBuscar;
    private JTextArea areaResultado;

    public ImprimirIngresso() {

    UIManager.put("Label.font", new Font("SansSerif", Font.PLAIN, 15));
    UIManager.put("TextField.font", new Font("SansSerif", Font.PLAIN, 15));
    UIManager.put("ComboBox.font", new Font("SansSerif", Font.PLAIN, 15));
    UIManager.put("Button.font", new Font("SansSerif", Font.BOLD, 14));

    setTitle("Imprimir Ingresso");
    setSize(500, 400);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    setLayout(null);

    // 🔴 Cor de fundo da janela
    getContentPane().setBackground(new Color(191,65,124));

    // CPF Label
    JLabel labelCpf = new JLabel("CPF:");
    labelCpf.setBounds(30, 20, 50, 25);
    labelCpf.setOpaque(true);
    labelCpf.setBackground(Color.WHITE);
    labelCpf.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
    add(labelCpf);

    // Campo CPF
    campoCpf = new JTextField();
    campoCpf.setBounds(80, 20, 200, 25);
    add(campoCpf);

    // Botão Buscar
    botaoBuscar = new JButton("Buscar");
    botaoBuscar.setBounds(300, 20, 100, 25);
    add(botaoBuscar);

    // Área de Resultado
    areaResultado = new JTextArea();
    areaResultado.setEditable(false);
    areaResultado.setFont(new Font("Monospaced", Font.PLAIN, 14));
    areaResultado.setBackground(Color.WHITE);
    areaResultado.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

    JScrollPane scroll = new JScrollPane(areaResultado);
    scroll.setBounds(30, 60, 420, 280);
    scroll.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
    add(scroll);

    // Ação do botão
    botaoBuscar.addActionListener(e -> buscarIngressos());

    setVisible(true);
}
    private void buscarIngressos() {
        String cpf = campoCpf.getText();
        Usuario usuario = UsuarioController.buscarPorCpf(cpf);

        if (usuario == null) {
            JOptionPane.showMessageDialog(this, "Usuário não encontrado. Verifique o CPF.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ArrayList<Ingresso> lista = IngressoController.buscarPorCpf(cpf);

        if (lista.isEmpty()) {
            areaResultado.setText("Nenhum ingresso encontrado para este CPF.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Ingressos de ").append(usuario.getNome()).append(":\n\n");

        for (Ingresso i : lista) {
            sb.append("Peça: ").append(i.getPeca()).append("\n");
            sb.append("Sessão: ").append(i.getSessao()).append("\n");
            sb.append("Área: ").append(i.getArea()).append("\n");
            sb.append("Poltrona: ").append(i.getPoltrona()).append("\n");
            sb.append("Preço: R$ ").append(String.format("%.2f", i.getPreco())).append("\n");
            sb.append("-------------------------\n");
        }

        areaResultado.setText(sb.toString());
    }

    public static void main(String[] args) {
        new ImprimirIngresso();
    }
}
