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

public class UPDATE extends JFrame {
    private JTextField identificacionTextField;
    private JTextField nombreTextField;
    private JTextField edadTextField;
    private JTextField sexoTextField;
    private JTextField correoTextField;
    private JTextField direccionTextField;

    public UPDATE(String identificacion) {
        setTitle("Editar Persona");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLayout(new BorderLayout());

        JPanel formularioPanel = new JPanel();
        formularioPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        identificacionTextField = new JTextField(15);
        nombreTextField = new JTextField(15);
        edadTextField = new JTextField(15);
        sexoTextField = new JTextField(15);
        correoTextField = new JTextField(15);
        direccionTextField = new JTextField(15);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formularioPanel.add(new JLabel("Identificación:"), gbc);
        gbc.gridx = 1;
        formularioPanel.add(identificacionTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formularioPanel.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        formularioPanel.add(nombreTextField, gbc);

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

        JButton editarButton = new JButton("Editar");

        cargarDatosPersona(identificacion);

        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarPersona();
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
        buttonPanel.add(editarButton);
        buttonPanel.add(regresarButton);
        add(formularioPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void cargarDatosPersona(String identificacion) {
        try (BufferedReader br = new BufferedReader(new FileReader("src/CRUD/personas.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 6) {
                    String nombre = partes[0].trim();
                    String identificacionFromFile = partes[1].trim();
                    String edadStr = partes[2].trim();
                    String sexo = partes[3].trim();
                    String correo = partes[4].trim();
                    String direccion = partes[5].trim();

                    if (identificacionFromFile.equals(identificacion)) {
                        identificacionTextField.setText(identificacion);
                        nombreTextField.setText(nombre);
                        edadTextField.setText(edadStr);
                        sexoTextField.setText(sexo);
                        correoTextField.setText(correo);
                        direccionTextField.setText(direccion);
                        return;
                    }
                } else {
                    System.out.println("Error: formato de línea incorrecto en el archivo personas.txt");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JOptionPane.showMessageDialog(this, "No se encontró una persona con la identificación especificada");
    }

    public void editarPersona() {
        String identificacion = identificacionTextField.getText();
        String nombre = nombreTextField.getText();
        String edadStr = edadTextField.getText();
        String sexo = sexoTextField.getText();
        String correo = correoTextField.getText();
        String direccion = direccionTextField.getText();

        boolean personaEncontrada = false;
        StringBuilder personas = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader("src/CRUD/personas.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 6) {
                    String identificacionFromFile = partes[1].trim();

                    if (identificacionFromFile.equals(identificacion)) {
                        personas.append(nombre).append(", ")
                                .append(identificacion).append(", ")
                                .append(edadStr).append(", ")
                                .append(sexo).append(", ")
                                .append(correo).append(", ")
                                .append(direccion)
                                .append("\n");

                        personaEncontrada = true;
                    } else {
                        personas.append(linea).append("\n");
                    }
                } else {
                    System.out.println("Error: formato de línea incorrecto en el archivo personas.txt");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!personaEncontrada) {
            JOptionPane.showMessageDialog(this, "No se encontró una persona con la identificación especificada");
        } else {
            // Guardar los cambios
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/CRUD/personas.txt"))) {
                bw.write(personas.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(this, "Persona actualizada correctamente");
        }

        // Limpiar campos después de editar
        identificacionTextField.setText("");
        nombreTextField.setText("");
        edadTextField.setText("");
        sexoTextField.setText("");
        correoTextField.setText("");
        direccionTextField.setText("");
    }
}
