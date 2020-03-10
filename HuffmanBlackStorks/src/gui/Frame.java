package gui;
import logic.*;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.*;
import java.awt.event.*;


public class Frame extends javax.swing.JFrame {


	private JFrame frame;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	/**
	 * Create the application.
	 */
	public Frame() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(169, 169, 169));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setBackground(new Color(0, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				
				JFileChooser fileChooser = new JFileChooser(new File("c:\\"));
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fileChooser.setDialogTitle("Save file");
				fileChooser.setFileFilter(new Filter(".txt", "Text File"));
				int result = fileChooser.showSaveDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					String content = textArea.getText();
					File fi = fileChooser.getSelectedFile();
					try {
						FileWriter fw = new FileWriter(fi.getPath());
						fw.write(content);
						fw.flush();
						fw.close();
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, e2.getMessage());
						
					}
					
				}
				
			}
		});
		
		
		
		btnNewButton.setBounds(10, 227, 124, 23);
		frame.getContentPane().add(btnNewButton);
	
		
		JButton btnNewButton_2 = new JButton("Unzip");
		btnNewButton_2.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser(new File("c:\\"));
				fileChooser.setDialogTitle("Unzip File");
				fileChooser.setFileFilter(new Filter(".txt", "Text File"));
				int result = fileChooser.showOpenDialog(null);
				if(result == JFileChooser.APPROVE_OPTION) {
					try {
						File fi = fileChooser.getSelectedFile();
						BufferedReader br = new BufferedReader(new FileReader(fi.getPath()));
						String line = "";
						String s = "";
						while ((line=br.readLine()) != null) {
							s += line;
	//						s += "\n";
						}
						String decompressed = Huffman.decompress(s);
						textArea.setText(decompressed);
						if ( br != null)
							br.close();
					
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, e2.getMessage());
					}
				}
			}
		});
		
		
		btnNewButton_2.setBackground(new Color(0, 255, 255));
		btnNewButton_2.setBounds(283, 193, 141, 57);
		frame.getContentPane().add(btnNewButton_2);
		
		textArea = new JTextArea();
		/*textArea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});*/
	//	textArea.setBounds(10, 11, 414, 156);
		frame.getContentPane().add(textArea);
		textArea.setColumns(10);
		scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(10, 11, 414, 156);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(scrollPane);
		//scrollPane.setBounds(10, 11, 414, 156);
		
		
		JButton btnNewButton_1 = new JButton("Zip");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser(new File("c:\\"));
				fileChooser.setDialogTitle("Zip File");
				fileChooser.setFileFilter(new Filter(".txt", "Text File"));
				int result = fileChooser.showOpenDialog(null);
				if(result == JFileChooser.APPROVE_OPTION) {
					try {
						File fi = fileChooser.getSelectedFile();
						BufferedReader br = new BufferedReader(new FileReader(fi.getPath()));
						String line = "";
						String s = "";
						while ((line=br.readLine()) != null) {
							s += line;
		//					s += "\n";
						}
						String compressed = Huffman.compress(s);
						textArea.setText(compressed);
						if ( br != null)
							br.close();
					
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, e2.getMessage());
					}
					
				}
				
				
				
			}
		});
		btnNewButton_1.setBackground(Color.CYAN);
		btnNewButton_1.setBounds(10, 193, 124, 23);
		frame.getContentPane().add(btnNewButton_1);
	}
}