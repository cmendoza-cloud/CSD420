/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package fandatabaseapp;

import javax.swing.*;

public class FanDatabaseApp {
    // Database connection details (for future use)
    static final String DB_URL = "jdbc:mysql://localhost:3306/databasedb";
    static final String USER = "student1";
    static final String PASS = "pass";

    public static void main(String[] args) {
        // Start the FanForm GUI
        SwingUtilities.invokeLater(() -> {
            new FanForm().setVisible(true);
        });
    }
}
