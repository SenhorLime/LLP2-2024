package Source.Components;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Source.Main;
import Source.Pages.AlunoTable;

public class ButtonRow extends JPanel {
    private JButton createButton;
    private JButton readButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton saveButton;

    public ButtonRow() {
        setLayout(new GridBagLayout());

        initComponents();
    }

    private void initComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5);

        createButton = new JButton("Create");
        readButton = new JButton("Read");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        saveButton = new JButton("Save");

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(createButton, gbc);

        gbc.gridx = 1;
        add(readButton, gbc);

        gbc.gridx = 2;
        add(updateButton, gbc);

        gbc.gridx = 3;
        add(deleteButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(saveButton, gbc);
    }

    public void handleEvents(Main main) {
        createButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event) {
                System.out.println("Create clicado");
            }
        });

        readButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event) {
                AlunoTable alunoTable = new AlunoTable();
                alunoTable.getAluno();

                main.getContentPane().setLayout(new FlowLayout());
                main.getContentPane().removeAll();
                main.getContentPane().add(alunoTable);
                main.revalidate();
                main.repaint();
            }
        });

        updateButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event) {
                String alunoIDString = JOptionPane.showInputDialog(null, "Digite o numero do aluno: ", "Alunos CRUD",
                        JOptionPane.QUESTION_MESSAGE);

                int alunoID = Integer.parseInt(alunoIDString);

                main.alunoForm.updateAluno(alunoID);
            }
        });

        deleteButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event) {
                String alunoIDString = JOptionPane.showInputDialog(null, "Digite o numero do aluno: ", "Alunos CRUD",
                        JOptionPane.QUESTION_MESSAGE);

                int alunoID = Integer.parseInt(alunoIDString);

                main.alunoForm.deletaAluno(alunoID);
            }
        });
    }
}
