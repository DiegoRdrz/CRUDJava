package CRUD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DELETE extends JFrame {
    private JTextField identificacionTextField;

    public DELETE() {
        setTitle("Eliminar Persona");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 150);
        setLayout(new BorderLayout());

        JPanel formularioPanel = new JPanel();
        formularioPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        identificacionTextField = new JTextField(15);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formularioPanel.add(new JLabel("Identificación:"), gbc);
        gbc.gridx = 1;
        formularioPanel.add(identificacionTextField, gbc);

        JButton eliminarButton = new JButton("Eliminar");


        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarPersona();
            }
        });
        JButton regresarButton = new JButton("Regresar");
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CRUDGUI crudGUI = new CRUDGUI();
                crudGUI.setLocationRelativeTo(null);
                crudGUI.setVisible(true);
                dispose(); // Cierra el formulario actual (CREATE)
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(eliminarButton);
        buttonPanel.add(regresarButton);
        add(formularioPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void eliminarPersona() {
        String identificacion = identificacionTextField.getText();

        StringBuilder personas = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader("src/CRUD/personas.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 6) {
                    String identificacionFromFile = partes[1].trim();
                    if (!identificacionFromFile.equals(identificacion)) {
                        personas.append(linea).append("\n");
                    }
                } else {
                    System.out.println("Error: formato de línea incorrecto en el archivo personas.txt");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/CRUD/personas.txt"))) {
            bw.write(personas.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        JOptionPane.showMessageDialog(this, "Persona eliminada correctamente");

        // Limpiar campos después de eliminar
        identificacionTextField.setText("");
    }
}