
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fandatabaseapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class FanForm extends JFrame {

    // DB settings
    private static final String DB_URL = "jdbc:sqlite:databasedb.db";
    private static final String USER = "student1";
    private static final String PASS = "pass";

    // UI components
    private JTextField idField;
    private JTextField nameField;
    private JTextField teamField;

    public FanForm() {
        setTitle("Fan Database Form");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.CYAN);  // Cyan blue background
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Fan Database");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(Color.MAGENTA); // Magenta text
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        idField = new JTextField(20);
        nameField = new JTextField(20);
        teamField = new JTextField(20);

        JButton displayButton = new JButton("Display");
        JButton updateButton = new JButton("Update");

        // Button colors
        Color limeGreen = new Color(50, 205, 50);
        displayButton.setBackground(limeGreen);
        updateButton.setBackground(limeGreen);

        mainPanel.add(createLabeledField("ID:", idField));
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(createLabeledField("Name:", nameField));
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(createLabeledField("Favorite Team:", teamField));
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(displayButton);
        buttonPanel.add(updateButton);
        mainPanel.add(buttonPanel);

        // Event listeners
        displayButton.addActionListener(e -> displayFan());
        updateButton.addActionListener(e -> updateFan());

        add(mainPanel);
        setVisible(true);
    }

    private JPanel createLabeledField(String labelText, JTextField textField) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        JLabel label = new JLabel(labelText);
        label.setForeground(Color.MAGENTA); // Magenta labels
        panel.setOpaque(false);
        panel.add(label, BorderLayout.WEST);
        panel.add(textField, BorderLayout.CENTER);
        return panel;
    }

    private void displayFan() {
        String idText = idField.getText().trim();
        if (idText.isEmpty() || !idText.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric ID.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "SELECT firstname, favoriteteam FROM fans WHERE ID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(idText));
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                nameField.setText(rs.getString("firstname"));
                teamField.setText(rs.getString("favoriteteam"));
            } else {
                JOptionPane.showMessageDialog(this, "No fan found with ID " + idText);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
        }
    }

    private void updateFan() {
        String idText = idField.getText().trim();
        String name = nameField.getText().trim();
        String team = teamField.getText().trim();

        if (idText.isEmpty() || !idText.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric ID.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "UPDATE fans SET firstname = ?, favoriteteam = ? WHERE ID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, team);
            stmt.setInt(3, Integer.parseInt(idText));

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Record updated successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "No record found to update.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FanForm());
    }
}
