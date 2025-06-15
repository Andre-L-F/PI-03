/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Geral;

/**
 *
 * @author duliz
 */

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            Geral.MenuPrincipal menu = new Geral.MenuPrincipal();
            menu.setVisible(true);
        });
    }
}
