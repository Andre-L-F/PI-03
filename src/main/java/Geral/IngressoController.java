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
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class IngressoController {
    private static ArrayList<Ingresso> ingressos = new ArrayList<>();
    private static HashMap<String, Integer> totalPoltronas = new HashMap<>();

    static {
        totalPoltronas.put("Plateia A", 25);
        totalPoltronas.put("Plateia B", 100);
        totalPoltronas.put("Frisa", 5 * 5);
        totalPoltronas.put("Camarote", 3 * 10);
        totalPoltronas.put("Balcao Nobre", 50);
    }

    public static boolean comprarIngresso(Ingresso ingresso) {
        for (Ingresso i : ingressos) {
            if (i.getArea().equals(ingresso.getArea()) &&
                i.getSessao().equals(ingresso.getSessao()) &&
                i.getPeca().equals(ingresso.getPeca()) &&
                i.getPoltrona() == ingresso.getPoltrona() &&
                i.getPeriodo() == ingresso.getPeriodo()) {
                return false;
            }
        }
        ingressos.add(ingresso);
        return true;
    }

    public static ArrayList<Integer> poltronasOcupadas(String peca, String sessao, String area, int periodo) {
        ArrayList<Integer> ocupadas = new ArrayList<>();
        for (Ingresso i : ingressos) {
            if (i.getPeca().equals(peca) && i.getSessao().equals(sessao) &&
                i.getArea().equals(area) && i.getPeriodo() == periodo) {
                ocupadas.add(i.getPoltrona());
            }
        }
        return ocupadas;
    }

    public static int getTotalPoltronas(String area) {
        return totalPoltronas.getOrDefault(area, 0);
    }

    public static double getPrecoPorArea(String area) {
        switch (area) {
            case "Plateia A": return 40.0;
            case "Plateia B": return 60.0;
            case "Camarote": return 80.0;
            case "Frisa": return 120.0;
            case "Balcao Nobre": return 250.0;
            default: return 0.0;
        }
    }

    public static ArrayList<Ingresso> buscarPorCpf(String cpf) {
        ArrayList<Ingresso> lista = new ArrayList<>();
        for (Ingresso i : ingressos) {
            if (i.getCpf().equals(cpf)) {
                lista.add(i);
            }
        }
        return lista;
    }

    public static Map<String, Integer> ingressosPorPeca() {
        Map<String, Integer> map = new HashMap<>();
        for (Ingresso i : ingressos) {
            map.put(i.getPeca(), map.getOrDefault(i.getPeca(), 0) + 1);
        }
        return map;
    }

    public static Map<String, Integer> ingressosPorSessao() {
        Map<String, Integer> map = new HashMap<>();
        for (Ingresso i : ingressos) {
            map.put(i.getSessao(), map.getOrDefault(i.getSessao(), 0) + 1);
        }
        return map;
    }

    public static Map<String, Double> lucroPorPeca() {
        Map<String, Double> map = new HashMap<>();
        for (Ingresso i : ingressos) {
            map.put(i.getPeca(), map.getOrDefault(i.getPeca(), 0.0) + i.getPreco());
        }
        return map;
    }

    public static double lucroMedioPorPeca(String peca) {
        double soma = 0;
        int count = 0;
        for (Ingresso i : ingressos) {
            if (i.getPeca().equals(peca)) {
                soma += i.getPreco();
                count++;
            }
        }
        return count == 0 ? 0 : soma / count;
    }

    public static Map<String, Double> receitaTotalPorPeca() {
        Map<String, Double> map = new HashMap<>();
        for (Ingresso i : ingressos) {
            map.put(i.getPeca(), map.getOrDefault(i.getPeca(), 0.0) + i.getPreco());
        }
        return map;
    }

    public static Map<String, Double> receitaMediaPorArea() {
        Map<String, Double> receita = new HashMap<>();
        Map<String, Integer> contagem = new HashMap<>();

        for (Ingresso i : ingressos) {
            receita.put(i.getArea(), receita.getOrDefault(i.getArea(), 0.0) + i.getPreco());
            contagem.put(i.getArea(), contagem.getOrDefault(i.getArea(), 0) + 1);
        }

        Map<String, Double> media = new HashMap<>();
        for (String area : receita.keySet()) {
            double total = receita.get(area);
            int qtd = contagem.get(area);
            media.put(area, qtd == 0 ? 0 : total / qtd);
        }
        return media;
    }

    public static double ticketMedioPorCliente() {
        Map<String, Double> totalPorCliente = new HashMap<>();
        Map<String, Integer> qtdPorCliente = new HashMap<>();

        for (Ingresso i : ingressos) {
            String cpf = i.getCpf();
            totalPorCliente.put(cpf, totalPorCliente.getOrDefault(cpf, 0.0) + i.getPreco());
            qtdPorCliente.put(cpf, qtdPorCliente.getOrDefault(cpf, 0) + 1);
        }

        double somaTickets = 0;
        for (String cpf : totalPorCliente.keySet()) {
            somaTickets += totalPorCliente.get(cpf) / qtdPorCliente.get(cpf);
        }

        return totalPorCliente.isEmpty() ? 0 : somaTickets / totalPorCliente.size();
    }

    public static Map<String, Double> lucroPorSessao() {
        Map<String, Double> map = new HashMap<>();
        for (Ingresso i : ingressos) {
            map.put(i.getSessao(), map.getOrDefault(i.getSessao(), 0.0) + i.getPreco());
        }
        return map;
    }

    public static Map<String, Integer> ingressosPorArea() {
        Map<String, Integer> map = new HashMap<>();
        for (Ingresso i : ingressos) {
            map.put(i.getArea(), map.getOrDefault(i.getArea(), 0) + 1);
        }
        return map;
    }

    public static Map<String, Double> receitaPorArea() {
        Map<String, Double> map = new HashMap<>();
        for (Ingresso i : ingressos) {
            map.put(i.getArea(), map.getOrDefault(i.getArea(), 0.0) + i.getPreco());
        }
        return map;
    }

    // MÉTODOS FILTRADOS POR PERÍODO

    public static ArrayList<Ingresso> ingressosPorPeriodo(int periodo) {
        return ingressos.stream()
            .filter(i -> i.getPeriodo() == periodo)
            .collect(Collectors.toCollection(ArrayList::new));
    }

    public static Map<String, Integer> ingressosPorPeca(int periodo) {
        return ingressosPorPeriodo(periodo).stream()
            .collect(Collectors.groupingBy(Ingresso::getPeca, Collectors.summingInt(i -> 1)));
    }

    public static Map<String, Integer> ingressosPorSessao(int periodo) {
        return ingressosPorPeriodo(periodo).stream()
            .collect(Collectors.groupingBy(Ingresso::getSessao, Collectors.summingInt(i -> 1)));
    }

    public static Map<String, Double> lucroPorPeca(int periodo) {
        return ingressosPorPeriodo(periodo).stream()
            .collect(Collectors.groupingBy(Ingresso::getPeca, Collectors.summingDouble(Ingresso::getPreco)));
    }

    public static Map<String, Double> receitaPorArea(int periodo) {
        return ingressosPorPeriodo(periodo).stream()
            .collect(Collectors.groupingBy(Ingresso::getArea, Collectors.summingDouble(Ingresso::getPreco)));
    }
}
