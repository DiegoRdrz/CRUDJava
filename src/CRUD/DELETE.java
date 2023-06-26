package CRUD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DELETE extends JFrame {
    private JTextField identificacionField;
    private CRUDGUI crudGui;

    public DELETE(CRUDGUI crudGui) {
        this.crudGui = crudGui;

        setTitle("Borrar Persona");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 150);
        setLayout(new FlowLayout());

        JLabel identificacionLabel = new JLabel("Identificación:");
        identificacionField = new JTextField(15);
        JButton borrarButton = new JButton("Borrar");

        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borrarPersona();
            }
        });

        add(identificacionLabel);
        add(identificacionField);
        add(borrarButton);
    }

    public void mostrar() {
        setVisible(true);
    }

    private void borrarPersona() {
        String identificacion = identificacionField.getText();
        boolean encontrado = false;

        for (Persona persona : crudGui.getPersonas()) {
            if (persona.getIdentificacion().equals(identificacion)) {
                crudGui.getPersonas().remove(persona);
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            crudGui.guardarPersonas();
            JOptionPane.showMessageDialog(this, "Persona borrada exitosamente.");
            identificacionField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró ninguna persona con la identificación proporcionada.");
        }
    }
}
