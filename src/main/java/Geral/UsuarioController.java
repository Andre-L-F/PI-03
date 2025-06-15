/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Geral;

/**
 *
 * @author duliz
 */

import java.util.ArrayList;

public class UsuarioController {
    private static ArrayList<Usuario> listaUsuarios = new ArrayList<>();

    public static boolean cadastrarUsuario(Usuario usuario) {
        String cpfFormatado = formatarCpf(usuario.getCpf());

        // Verifica se CPF é válido
        if (!cpfValido(cpfFormatado)) {
            return false; // CPF inválido
        }

        // Verifica se CPF já foi cadastrado
        for (Usuario u : listaUsuarios) {
            if (formatarCpf(u.getCpf()).equals(cpfFormatado)) {
                return false; // CPF duplicado
            }
        }

        // Atualiza CPF do usuário com o formato limpo antes de salvar
        usuario.setCpf(cpfFormatado);
        listaUsuarios.add(usuario);
        return true;
    }

    public static Usuario buscarPorCpf(String cpf) {
        String cpfFormatado = formatarCpf(cpf);
        for (Usuario u : listaUsuarios) {
            if (formatarCpf(u.getCpf()).equals(cpfFormatado)) {
                return u;
            }
        }
        return null;
    }

    public static boolean cpfValido(String cpf) {
        cpf = formatarCpf(cpf);

        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        try {
            int soma = 0, resto;

            for (int i = 1; i <= 9; i++) {
                soma += Integer.parseInt(cpf.substring(i - 1, i)) * (11 - i);
            }
            resto = (soma * 10) % 11;
            if (resto == 10 || resto == 11) resto = 0;
            if (resto != Integer.parseInt(cpf.substring(9, 10))) return false;

            soma = 0;
            for (int i = 1; i <= 10; i++) {
                soma += Integer.parseInt(cpf.substring(i - 1, i)) * (12 - i);
            }
            resto = (soma * 10) % 11;
            if (resto == 10 || resto == 11) resto = 0;
            return resto == Integer.parseInt(cpf.substring(10, 11));

        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String formatarCpf(String cpf) {
        return cpf.replaceAll("[^\\d]", "");
    }
}
