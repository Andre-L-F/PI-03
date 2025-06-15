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
import Geral.Usuario;
import Geral.UsuarioController;

public class CadastroUsuario extends JFrame {

    private JTextField campoNome;
    private JTextField campoCpf;
    private JTextField campoTelefone;
    private JTextField campoEndereco;
    private JTextField campoDataNascimento;
    private JButton botaoCadastrar;

    public CadastroUsuario() {

        UIManager.put("Label.font", new Font("SansSerif", Font.BOLD, 15));
        UIManager.put("TextField.font", new Font("SansSerif", Font.BOLD, 15));
        UIManager.put("ComboBox.font", new Font("SansSerif", Font.BOLD, 15));
        UIManager.put("Button.font", new Font("SansSerif", Font.BOLD, 14));

        setTitle("Cadastro de Usuario");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        getContentPane().setBackground(new Color(191, 65, 124));

        JLabel labelNome = new JLabel("Nome:");
        labelNome.setBounds(30, 30, 100, 25);
        labelNome.setOpaque(true);
        labelNome.setBackground(Color.WHITE);
        labelNome.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        add(labelNome);

        campoNome = new JTextField();
        campoNome.setBounds(140, 30, 200, 25);
        add(campoNome);

        JLabel labelCpf = new JLabel("CPF:");
        labelCpf.setBounds(30, 70, 100, 25);
        labelCpf.setOpaque(true);
        labelCpf.setBackground(Color.WHITE);
        labelCpf.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        add(labelCpf);
        
        campoCpf = new JTextField();
        campoCpf.setBounds(140, 70, 200, 25);
        add(campoCpf);

        JLabel labelTelefone = new JLabel("Telefone:");
        labelTelefone.setBounds(30, 110, 100, 25);
        labelTelefone.setOpaque(true);
        labelTelefone.setBackground(Color.WHITE);
        labelTelefone.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        add(labelTelefone);

        campoTelefone = new JTextField();
        campoTelefone.setBounds(140, 110, 200, 25);
        add(campoTelefone);

        JLabel labelEndereco = new JLabel("Endereco:");
        labelEndereco.setBounds(30, 150, 100, 25);
        labelEndereco.setOpaque(true);
        labelEndereco.setBackground(Color.WHITE);
        labelEndereco.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        add(labelEndereco);

        campoEndereco = new JTextField();
        campoEndereco.setBounds(140, 150, 200, 25);
        add(campoEndereco);

        JLabel labelDataNascimento = new JLabel("Data Nasc. (dd/mm/aaaa):");
        labelDataNascimento.setBounds(30, 190, 150, 25);
        labelDataNascimento.setOpaque(true);
        labelDataNascimento.setBackground(Color.WHITE);
        labelDataNascimento.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        add(labelDataNascimento);

        campoDataNascimento = new JTextField();
        campoDataNascimento.setBounds(190, 190, 150, 25);
        add(campoDataNascimento);

        botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.setBounds(140, 240, 120, 30);
        add(botaoCadastrar);

        botaoCadastrar.addActionListener(e -> cadastrarUsuario());

        setVisible(true);
    }

    private void cadastrarUsuario() {
        String nome = campoNome.getText();
        String cpf = campoCpf.getText();
        String telefone = campoTelefone.getText();
        String endereco = campoEndereco.getText();
        String dataNascimento = campoDataNascimento.getText();

        if (nome.isEmpty() || cpf.isEmpty() || telefone.isEmpty() || endereco.isEmpty() || dataNascimento.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Mensagem para o validador de cpf
        if (!UsuarioController.cpfValido(cpf)) {
            JOptionPane.showMessageDialog(this, "CPF inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuario usuario = new Usuario(nome, cpf, telefone, endereco, dataNascimento);
        boolean sucesso = UsuarioController.cadastrarUsuario(usuario);

        // Mensagem para se o usuario já foi cadastrado
        if (sucesso) {
            JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso.");
            limparCampos();
        } else {
            JOptionPane.showMessageDialog(this, "CPF já cadastrado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    //metodo para limpar os campos e cadastrar outro usuaro
    private void limparCampos() {
        campoNome.setText("");
        campoCpf.setText("");
        campoTelefone.setText("");
        campoEndereco.setText("");
        campoDataNascimento.setText("");
    }

    public static void main(String[] args) {
        new CadastroUsuario();
    }
}
