package CRUD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CRUDGUI extends JFrame {
    private JTextField nombreTextField;
    private JTextField identificacionTextField;
    private JTextField edadTextField;
    private JTextField sexoTextField;
    private JTextField correoTextField;
    private JTextField direccionTextField;
    private JTextArea personasTextArea;
    private JButton agregarButton;
    private JButton mostrarButton;
    private JButton borrarButton;
    private JButton editarButton;


    private List<Persona> personas;

    public CRUDGUI() {
        personas = new ArrayList<>();

        setTitle("CRUD GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
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
        personasTextArea = new JTextArea(10, 30);
        agregarButton = new JButton("Agregar");
        mostrarButton = new JButton("Mostrar");
        borrarButton = new JButton("Borrar");
        editarButton = new JButton("Editar");

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

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        formularioPanel.add(agregarButton, gbc);
        gbc.gridx = 1;
        formularioPanel.add(borrarButton, gbc);
        gbc.gridx = 2;
        formularioPanel.add(editarButton, gbc);

        add(formularioPanel, BorderLayout.NORTH);

        JPanel mostrarPanel = new JPanel();
        mostrarPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mostrarPanel.setLayout(new BorderLayout());
        mostrarPanel.add(new JLabel("Personas:"), BorderLayout.NORTH);
        mostrarPanel.add(new JScrollPane(personasTextArea), BorderLayout.CENTER);

        mostrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPersonas();
            }
        });

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarPersona();
            }
        });

        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirFormularioBorrar();
            }
        });

        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String identificacion = identificacionTextField.getText();
                for (Persona persona : personas) {
                    if (persona.getIdentificacion().equals(identificacion)) {
                        // Crear una instancia de la clase UpdateForm y pasar la persona a editar
                        UPDATE update = new UPDATE(persona);
                        update.mostrar();

                        // Salir del bucle, ya que se encontró la persona a editar
                        break;
                    }
                }
            }
        });


        mostrarPanel.add(mostrarButton, BorderLayout.SOUTH);

        add(mostrarPanel, BorderLayout.CENTER);

        cargarPersonas();

        setVisible(true);
    }

    public void agregarPersona() {
        String nombre = nombreTextField.getText();
        String identificacion = identificacionTextField.getText();
        int edad = Integer.parseInt(edadTextField.getText());
        String sexo = sexoTextField.getText();
        String correo = correoTextField.getText();
        String direccion = direccionTextField.getText();

        Persona persona = new Persona(nombre, identificacion, edad, sexo, correo, direccion);
        personas.add(persona);

        guardarPersonas();
        limpiarCampos();
    }

    public void mostrarPersonas() {
        personasTextArea.setText("");
        for (Persona persona : personas) {
            personasTextArea.append(persona.toString() + "\n");
        }
    }

    public void cargarPersonas() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/CRUD/personas.txt"));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 6) {
                    String nombre = partes[0].trim();
                    String identificacion = partes[1].trim();
                    String edadStr = partes[2].trim();
                    String sexo = partes[3].trim();
                    String correo = partes[4].trim();
                    String direccion = partes[5].trim();
                    try {
                        int edad = Integer.parseInt(edadStr);
                        Persona persona = new Persona(nombre, identificacion, edad, sexo, correo, direccion);
                        personas.add(persona);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: formato incorrecto de edad en el archivo personas.txt");
                    }
                } else {
                    System.out.println("Error: formato de línea incorrecto en el archivo personas.txt");
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void guardarPersonas() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/CRUD/personas.txt"));
            for (Persona persona : personas) {
                bw.write(persona.toString());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void limpiarCampos() {
        nombreTextField.setText("");
        identificacionTextField.setText("");
        edadTextField.setText("");
        sexoTextField.setText("");
        correoTextField.setText("");
        direccionTextField.setText("");
        nombreTextField.requestFocus();
    }

    private void abrirFormularioBorrar() {
        DELETE borrarFormulario = new DELETE(this);
        borrarFormulario.mostrar();
    }

    public List<Persona> getPersonas() {
        return personas;
    }
}
