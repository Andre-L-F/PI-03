/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Geral;

/**
 *
 * @author duliz
 */

public class Ingresso {
    private String cpf;
    private String peca;
    private String sessao;
    private String area;
    private int poltrona;
    private double preco;
    private int periodo; // NOVO CAMPO

    public Ingresso(String cpf, String peca, String sessao, String area, int poltrona, double preco, int periodo) {
        this.cpf = cpf;
        this.peca = peca;
        this.sessao = sessao;
        this.area = area;
        this.poltrona = poltrona;
        this.preco = preco;
        this.periodo = periodo;
    }

    public String getCpf() {
        return cpf;
    }

    public String getPeca() {
        return peca;
    }

    public String getSessao() {
        return sessao;
    }

    public String getArea() {
        return area;
    }

    public int getPoltrona() {
        return poltrona;
    }

    public double getPreco() {
        return preco;
    }

    public int getPeriodo() {
        return periodo;
    }
}
