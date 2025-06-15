/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Geral.View;

/**
 *
 * @author duliz
 */
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.plot.PlotOrientation;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import Geral.IngressoController;

public class EstatisticaVendas extends JFrame {

    private JComboBox<String> filtroComboBox;
    private JButton botaoMostrar, botaoGrafico;
    private JTextArea areaEstatisticas;

    public EstatisticaVendas() {

    UIManager.put("Label.font", new Font("SansSerif", Font.PLAIN, 15));
    UIManager.put("TextField.font", new Font("SansSerif", Font.PLAIN, 15));
    UIManager.put("ComboBox.font", new Font("SansSerif", Font.PLAIN, 15));
    UIManager.put("Button.font", new Font("SansSerif", Font.BOLD, 14));

    setTitle("Estatísticas de Vendas");
    setSize(800, 600);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    setLayout(null);

    // 🔴 Cor de fundo da janela
    getContentPane().setBackground(new Color(191,65,124));

    // ComboBox de filtro
    filtroComboBox = new JComboBox<>(new String[]{
        "Todas", "Por Peça", "Por Sessão", "Por Área", "Peça por Período"
    });
    filtroComboBox.setBounds(30, 20, 200, 30);
    filtroComboBox.setBackground(Color.WHITE);
    filtroComboBox.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
    add(filtroComboBox);

    // Botão Mostrar
    botaoMostrar = new JButton("Mostrar Estatísticas");
    botaoMostrar.setBounds(250, 20, 180, 30);
    add(botaoMostrar);

    // Botão Gráfico
    botaoGrafico = new JButton("Gerar Gráfico");
    botaoGrafico.setBounds(450, 20, 180, 30);
    add(botaoGrafico);

    // Área de estatísticas
    areaEstatisticas = new JTextArea();
    areaEstatisticas.setEditable(false);
    areaEstatisticas.setFont(new Font("Monospaced", Font.PLAIN, 14));
    areaEstatisticas.setBackground(Color.WHITE);
    areaEstatisticas.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

    JScrollPane scroll = new JScrollPane(areaEstatisticas);
    scroll.setBounds(30, 70, 720, 200);
    scroll.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
    add(scroll);

    // Eventos
    botaoMostrar.addActionListener(e -> {
        String filtro = (String) filtroComboBox.getSelectedItem();
        mostrarEstatisticas(filtro);
    });

    botaoGrafico.addActionListener(e -> {
        String filtro = (String) filtroComboBox.getSelectedItem();
        gerarGrafico(filtro);
    });

    setVisible(true);
}

    private void mostrarEstatisticas(String filtro) {
        StringBuilder sb = new StringBuilder();

        Map<String, Integer> vendasPeca = IngressoController.ingressosPorPeca();
        Map<String, Integer> vendasSessao = IngressoController.ingressosPorSessao();
        Map<String, Double> lucroPeca = IngressoController.lucroPorPeca();
        Map<String, Double> receitaArea = IngressoController.receitaPorArea();

        if (vendasPeca.isEmpty()) {
            sb.append("Nenhuma venda realizada.\n");
            areaEstatisticas.setText(sb.toString());
            return;
        }

        switch (filtro) {
            case "Por Peça":
                sb.append("Receita total por peça:\n");
                for (String peca : vendasPeca.keySet()) {
                    double total = lucroPeca.getOrDefault(peca, 0.0);
                    sb.append(" - ").append(peca).append(": R$ ").append(String.format("%.2f", total)).append("\n");
                }
                break;

            case "Por Sessão":
                sb.append("Ingressos por sessão:\n");
                for (String sessao : vendasSessao.keySet()) {
                    sb.append(" - ").append(sessao).append(": ").append(vendasSessao.get(sessao)).append(" ingressos\n");
                }
                break;

            case "Por Área":
                sb.append("Receita por área:\n");
                for (String area : receitaArea.keySet()) {
                    sb.append(" - ").append(area).append(": R$ ").append(String.format("%.2f", receitaArea.get(area))).append("\n");
                }
                break;

            case "Por Período":
                sb.append("Filtro por período ainda não implementado.\n");
                break;

            case "Todas":
            default:
                String maisVendida = vendasPeca.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
                String menosVendida = vendasPeca.entrySet().stream().min(Map.Entry.comparingByValue()).get().getKey();

                sb.append("Peça mais vendida: ").append(maisVendida)
                        .append(" (").append(vendasPeca.get(maisVendida)).append(" ingressos)\n");
                sb.append("Peça menos vendida: ").append(menosVendida)
                        .append(" (").append(vendasPeca.get(menosVendida)).append(" ingressos)\n\n");

                String maisOcupada = vendasSessao.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
                String menosOcupada = vendasSessao.entrySet().stream().min(Map.Entry.comparingByValue()).get().getKey();

                sb.append("Sessão mais ocupada: ").append(maisOcupada)
                        .append(" (").append(vendasSessao.get(maisOcupada)).append(" ingressos)\n");
                sb.append("Sessão menos ocupada: ").append(menosOcupada)
                        .append(" (").append(vendasSessao.get(menosOcupada)).append(" ingressos)\n\n");

                String maisLucrativa = lucroPeca.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
                String menosLucrativa = lucroPeca.entrySet().stream().min(Map.Entry.comparingByValue()).get().getKey();

                sb.append("Peça mais lucrativa: ").append(maisLucrativa)
                        .append(" (R$ ").append(String.format("%.2f", lucroPeca.get(maisLucrativa))).append(")\n");
                sb.append("Peça menos lucrativa: ").append(menosLucrativa)
                        .append(" (R$ ").append(String.format("%.2f", lucroPeca.get(menosLucrativa))).append(")\n\n");

                sb.append("Lucro médio por peça:\n");
                for (String peca : vendasPeca.keySet()) {
                    double lucroMedio = IngressoController.lucroMedioPorPeca(peca);
                    sb.append(" - ").append(peca).append(": R$ ").append(String.format("%.2f", lucroMedio)).append("\n");
                }

                sb.append("\nReceita média por área:\n");
                for (String area : receitaArea.keySet()) {
                    sb.append(" - ").append(area).append(": R$ ").append(String.format("%.2f", receitaArea.get(area))).append("\n");
                }

                // uso da função recursiva
                List<Integer> ingressos = new ArrayList<>(vendasPeca.values());
                int totalIngressos = calcularTotalIngressosRecursivo(ingressos, 0);

                sb.append("\nTotal de ingressos vendidos (recursivo): ")
                        .append(totalIngressos).append("\n");

                sb.append("\nTicket médio por cliente: R$ ")
                        .append(String.format("%.2f", IngressoController.ticketMedioPorCliente())).append("\n");

                break;
        }

        areaEstatisticas.setText(sb.toString());
    }

    // calculo da função recursiva
    private int calcularTotalIngressosRecursivo(List<Integer> valores, int i) {
        if (i >= valores.size()) {
            return 0;
        }
        return valores.get(i) + calcularTotalIngressosRecursivo(valores, i + 1);
    }

    private void gerarGrafico(String filtro) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        switch (filtro) {
            case "Por Peça":
                Map<String, Integer> porPeca = IngressoController.ingressosPorPeca();
                for (Map.Entry<String, Integer> entry : porPeca.entrySet()) {
                    dataset.setValue(entry.getValue(), "Ingressos", entry.getKey());
                }
                break;

            case "Por Sessão":
                Map<String, Integer> porSessao = IngressoController.ingressosPorSessao();
                for (Map.Entry<String, Integer> entry : porSessao.entrySet()) {
                    dataset.setValue(entry.getValue(), "Ingressos", entry.getKey());
                }
                break;

            case "Por Área":
                Map<String, Double> porArea = IngressoController.receitaPorArea();
                for (Map.Entry<String, Double> entry : porArea.entrySet()) {
                    dataset.setValue(entry.getValue(), "R$", entry.getKey());
                }
                break;

            case "Peça por Período":
                String input = JOptionPane.showInputDialog(this, "Informe o número do período:");
                if (input == null) {
                    return;
                }

                int periodo;
                try {
                    periodo = Integer.parseInt(input);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Período inválido.");
                    return;
                }

                Map<String, Integer> pecaPeriodo = IngressoController.ingressosPorPeca(periodo);

                // Colocar os dados em uma lista de pares (nome, qtd)
                List<Map.Entry<String, Integer>> lista = new ArrayList<>(pecaPeriodo.entrySet());

                // Ordenação manual (bubble sort)
                for (int i = 0; i < lista.size() - 1; i++) {
                    for (int j = 0; j < lista.size() - i - 1; j++) {
                        if (lista.get(j).getKey().compareTo(lista.get(j + 1).getKey()) > 0) {
                            Map.Entry<String, Integer> temp = lista.get(j);
                            lista.set(j, lista.get(j + 1));
                            lista.set(j + 1, temp);
                        }
                    }
                }

                // adicionando os dados ordenados ao dataset
                for (Map.Entry<String, Integer> entry : lista) {
                    dataset.setValue(entry.getValue(), "Ingressos", entry.getKey());
                }
                break;

            case "Todas":
            default:
                JOptionPane.showMessageDialog(this, "Escolha um filtro específico para gerar o gráfico.");
                return;
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Estatísticas - " + filtro,
                filtro.contains("Área") ? "Área" : filtro.contains("Sessão") ? "Sessão" : "Peça",
                filtro.contains("Área") ? "Receita (R$)" : "Ingressos",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBounds(30, 300, 720, 250);

        for (Component comp : getContentPane().getComponents()) {
            if (comp instanceof ChartPanel) {
                remove(comp);
            }
        }

        add(chartPanel);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        new EstatisticaVendas();
    }
}
