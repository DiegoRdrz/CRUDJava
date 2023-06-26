package CRUD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CRUDGUI extends JFrame {
    private JTextArea personasTextArea;

    public CRUDGUI() {
        setTitle("CRUD GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new BorderLayout());

        personasTextArea = new JTextArea(10, 30);
        JPanel mostrarPanel = new JPanel();
        mostrarPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mostrarPanel.setLayout(new BorderLayout());
        mostrarPanel.add(new JLabel("Personas:"), BorderLayout.NORTH);
        mostrarPanel.add(new JScrollPane(personasTextArea), BorderLayout.CENTER);

        JButton agregarButton = new JButton("Crear");
        JButton editarButton = new JButton("Editar");
        JButton eliminarButton = new JButton("Eliminar");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(agregarButton);
        buttonPanel.add(editarButton);
        buttonPanel.add(eliminarButton);

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CREATE agregarPersonaGUI = new CREATE();
                agregarPersonaGUI.setVisible(true);
                agregarPersonaGUI.setLocationRelativeTo(null);
                CRUDGUI.this.dispose();
            }
        });

        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String identificacion = JOptionPane.showInputDialog("Ingrese la identificación de la persona a editar:");
                if (identificacion != null && !identificacion.isEmpty()) {
                    UPDATE editarPersonaGUI = new UPDATE(identificacion);
                    editarPersonaGUI.setVisible(true);
                    editarPersonaGUI.setLocationRelativeTo(null);
                    CRUDGUI.this.dispose();
                }
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DELETE eliminarPersonaGUI = new DELETE();
                eliminarPersonaGUI.setVisible(true);
                eliminarPersonaGUI.setLocationRelativeTo(null);
                CRUDGUI.this.dispose();
            }
        });

        add(mostrarPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        cargarPersonas();
        setVisible(true);
    }

    public void mostrarPersonas() {
        personasTextArea.setText("");
        try (BufferedReader br = new BufferedReader(new FileReader("src/CRUD/personas.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                personasTextArea.append(linea + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarPersonas() {
        mostrarPersonas();
    }
}
