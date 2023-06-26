package CRUD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CREATE extends JFrame {
    private JTextField nombreTextField;
    private JTextField identificacionTextField;
    private JTextField edadTextField;
    private JTextField sexoTextField;
    private JTextField correoTextField;
    private JTextField direccionTextField;

    public CREATE() {
        setTitle("Agregar Persona");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 300);
        setLayout(new BorderLayout());

        JPanel formularioPanel = new JPanel();
        formularioPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        nombreTextField = new JTextField(15);
        identificacionTextField = new JTextField(15);
        edadTextField = new JTextField(15);
        sexoTextField = new JTextField(15);
        correoTextField = new JTextField(15);
        direccionTextField = new JTextField(15);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formularioPanel.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        formularioPanel.add(nombreTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formularioPanel.add(new JLabel("Identificación:"), gbc);
        gbc.gridx = 1;
        formularioPanel.add(identificacionTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formularioPanel.add(new JLabel("Edad:"), gbc);
        gbc.gridx = 1;
        formularioPanel.add(edadTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formularioPanel.add(new JLabel("Sexo:"), gbc);
        gbc.gridx = 1;
        formularioPanel.add(sexoTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formularioPanel.add(new JLabel("Correo:"), gbc);
        gbc.gridx = 1;
        formularioPanel.add(correoTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        formularioPanel.add(new JLabel("Dirección:"), gbc);
        gbc.gridx = 1;
        formularioPanel.add(direccionTextField, gbc);

        JButton agregarButton = new JButton("Agregar");
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarPersona();
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
        buttonPanel.add(agregarButton);
        buttonPanel.add(regresarButton);
        add(formularioPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void agregarPersona() {
        String nombre = nombreTextField.getText();
        String identificacion = identificacionTextField.getText();
        int edad = Integer.parseInt(edadTextField.getText());
        String sexo = sexoTextField.getText();
        String correo = correoTextField.getText();
        String direccion = direccionTextField.getText();

        String personaString = nombre + ", " + identificacion + ", " + edad + ", " + sexo + ", " + correo + ", " + direccion;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/CRUD/personas.txt", true))) {
            bw.write(personaString);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JOptionPane.showMessageDialog(this, "Persona agregada correctamente");

        // Limpiar campos después de agregar
        nombreTextField.setText("");
        identificacionTextField.setText("");
        edadTextField.setText("");
        sexoTextField.setText("");
        correoTextField.setText("");
        direccionTextField.setText("");
    }
}