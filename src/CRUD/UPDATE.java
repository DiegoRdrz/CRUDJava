package CRUD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UPDATE extends JFrame {
    private JTextField nombreTextField;
    private JTextField identificacionTextField;
    private JTextField edadTextField;
    private JTextField sexoTextField;
    private JTextField correoTextField;
    private JTextField direccionTextField;
    private JButton guardarButton;

    private Persona persona;

    public UPDATE(Persona persona) {
        this.persona = persona;

        setTitle("Editar Persona");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
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
        guardarButton = new JButton("Guardar");

        nombreTextField.setText(persona.getNombre());
        identificacionTextField.setText(persona.getIdentificacion());
        edadTextField.setText(Integer.toString(persona.getEdad()));
        sexoTextField.setText(persona.getSexo());
        correoTextField.setText(persona.getCorreo());
        direccionTextField.setText(persona.getDireccion());

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
        formularioPanel.add(guardarButton, gbc);

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Actualizar los atributos de la persona con los nuevos valores ingresados
                persona.setNombre(nombreTextField.getText());
                persona.setEdad(Integer.parseInt(edadTextField.getText()));
                persona.setSexo(sexoTextField.getText());
                persona.setCorreo(correoTextField.getText());
                persona.setDireccion(direccionTextField.getText());

                // Cerrar el formulario de edición
                dispose();
            }
        });

        add(formularioPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public void mostrar() {
        setVisible(true);
    }
}
