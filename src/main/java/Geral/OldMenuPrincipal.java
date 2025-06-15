/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Geral;

/**
 *
 * @author duliz
 */

import Geral.View.ComprarIngresso;
import Geral.View.CadastroUsuario;
import Geral.View.ImprimirIngresso;
import Geral.View.EstatisticaVendas;
import javax.swing.*;
import java.awt.event.*;

public class OldMenuPrincipal extends JFrame {

    private JButton btnCadastro, btnCompra, btnImprimir, btnEstatisticas;

    public OldMenuPrincipal() {
        setTitle("Sistema Teatro ABC - Menu Principal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        btnCadastro = new JButton("Cadastro de Usuário");
        btnCadastro.setBounds(100, 30, 200, 30);
        add(btnCadastro);

        btnCompra = new JButton("Comprar Ingresso");
        btnCompra.setBounds(100, 80, 200, 30);
        add(btnCompra);

        btnImprimir = new JButton("Imprimir Ingresso");
        btnImprimir.setBounds(100, 130, 200, 30);
        add(btnImprimir);

        btnEstatisticas = new JButton("Estatísticas de Vendas");
        btnEstatisticas.setBounds(100, 180, 200, 30);
        add(btnEstatisticas);

        // Ações dos botões
        btnCadastro.addActionListener(e -> abrirCadastro());
        btnCompra.addActionListener(e -> abrirCompra());
        btnImprimir.addActionListener(e -> abrirImprimir());
        btnEstatisticas.addActionListener(e -> abrirEstatisticas());
    }

    private void abrirCadastro() {
        CadastroUsuario cadastro = new CadastroUsuario();
        cadastro.setVisible(true);
    }

    private void abrirCompra() {
        ComprarIngresso compra = new ComprarIngresso();
        compra.setVisible(true);
    }

    private void abrirImprimir() {
        ImprimirIngresso imprimir = new ImprimirIngresso();
        imprimir.setVisible(true);
    }

    private void abrirEstatisticas() {
        EstatisticaVendas estatistica = new EstatisticaVendas();
        estatistica.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new OldMenuPrincipal().setVisible(true);
        });
    }
}
