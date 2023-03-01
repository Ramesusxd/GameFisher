package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class FolderSelectionProgram implements ActionListener {
    JComboBox<String> comboBox, comboBox2, comboBox3;
    JButton button;

    public static void main(String[] args) {
        new FolderSelectionProgram();
    }

    public FolderSelectionProgram() {
        JFrame frame = new JFrame("Folder Selection Program");
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        comboBox = new JComboBox<>();
        comboBox.setPreferredSize(new Dimension(300, 30));
        File folder = new File("\\\\lvstore\\builds\\Title\\QA\\Titles");
        String[] folderNames = folder.list();
        if (folderNames == null) {
            JOptionPane.showMessageDialog(null, "The folder is empty or does not exist.");
            System.exit(0);
        }
        comboBox.setModel(new DefaultComboBoxModel<>(folderNames));
        comboBox.addActionListener(this);
        frame.add(comboBox);

        comboBox2 = new JComboBox<>();
        comboBox2.setPreferredSize(new Dimension(300, 30));
        comboBox2.addActionListener(this);
        frame.add(comboBox2);

        comboBox3 = new JComboBox<>();
        comboBox3.setPreferredSize(new Dimension(300, 30));
        comboBox3.addActionListener(this);
        frame.add(comboBox3);

        button = new JButton("Move selected item to desktop");
        button.setPreferredSize(new Dimension(300, 30));
        button.addActionListener(this);
        frame.add(button);

        frame.setSize(350, 200);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == comboBox) {
            String selectedFolder = (String) comboBox.getSelectedItem();
            if (selectedFolder != null) {
                File folder = new File("\\\\lvstore\\builds\\Title\\QA\\Titles\\" + selectedFolder);
                String[] folderNames = folder.list();
                if (folderNames == null) {
                    JOptionPane.showMessageDialog(null, "The folder is empty or does not exist.");
                    System.exit(0);
                }
                comboBox2.setModel(new DefaultComboBoxModel<>(folderNames));
            }
        } else if (e.getSource() == comboBox2) {
            String selectedFolder = (String) comboBox2.getSelectedItem();
            if (selectedFolder != null) {
                File folder = new File("\\\\lvstore\\builds\\Title\\QA\\Titles\\" + comboBox.getSelectedItem() + "\\" + selectedFolder);
                String[] folderNames = folder.list();
                if (folderNames == null) {
                    JOptionPane.showMessageDialog(null, "The folder is empty or does not exist.");
                    System.exit(0);
                }
                comboBox3.setModel(new DefaultComboBoxModel<>(folderNames));
            }
        } else if (e.getSource() == button) {
            String selectedItem = (String) comboBox3.getSelectedItem();
            if (selectedItem != null) {
                File sourceFile = new File("\\\\lvstore\\builds\\Title\\QA\\Titles\\" + comboBox.getSelectedItem() + "\\" + comboBox2.getSelectedItem() + "\\" + selectedItem);
                File destFile = new File(System.getProperty("user.home") + "\\Desktop\\" + selectedItem);
                try {
                    java.nio.file.Files.copy(sourceFile.toPath(), destFile.toPath());
                    JOptionPane.showMessageDialog(null, "The file has been copied to the desktop.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "An error occurred while copying the file to the desktop.");
                }
            }
        }
    }
}
