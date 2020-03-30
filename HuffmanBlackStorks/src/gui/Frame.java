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
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;

public class Frame extends javax.swing.JFrame {

	private JFrame frame;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JTextField txtEncodingAlgoritm;
	private JTextField txtMode;
	private boolean openedPicture, openedText = false;

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
		frame.getContentPane().setBackground(SystemColor.controlShadow);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);

		textArea = new JTextArea();
		textArea.setBounds(64, 1, 280, 94);
		/*
		 * textArea.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { } });
		 */
		// textArea.setBounds(10, 11, 414, 156);
		frame.getContentPane().add(textArea);
		textArea.setColumns(10);
		scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(111, 11, 313, 40);
		// scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(scrollPane);
		// scrollPane.setBounds(10, 11, 414, 156);

		JButton btnNewButton_1 = new JButton("Open txt");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser fileChooser = new JFileChooser(new File("c:\\"));
				fileChooser.setDialogTitle("Zip File");
				fileChooser.setFileFilter(new Filter(".docx", "Microsoft Word Documents"));
				fileChooser.setFileFilter(new Filter(".txt", "Text File"));
				int result = fileChooser.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					try {
						File fi = fileChooser.getSelectedFile();
						BufferedReader br = new BufferedReader(new FileReader(fi.getPath()));
						String line = "";
						String s = "";
						while ((line = br.readLine()) != null) {
							s += line;
						}
						textArea.setText(s);
						openedText = true;
						openedPicture = false;

						if (br != null)
							br.close();

					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, e2.getMessage());
					}

				}

			}
		});
		btnNewButton_1.setBackground(Color.CYAN);
		btnNewButton_1.setBounds(10, 11, 95, 23);
		frame.getContentPane().add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Open bmp");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser(new File("c:\\"));
				fileChooser.setDialogTitle("Zip File");
				// fileChooser.setFileFilter(new Filter(".docx", "Microsoft Word Documents"));
				fileChooser.setFileFilter(new Filter(".bmp", "Bitmap Image"));
				int result = fileChooser.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					try {
						File fi = fileChooser.getSelectedFile();
						BufferedImage img = ImageIO.read(fi);
						int[] pixels = img.getRGB(0, 0, img.getWidth(), img.getHeight(), null, 0, img.getWidth());
						String s = "";
						for (int p : pixels) {
							String bin = Integer.toBinaryString(p);
							// System.out.println(bin);
							if (bin.equals("11111111111111111111111111111111")) {
								bin = "0";
							} else {
								bin = "1";
							}
							s = s + bin;
						}
						// System.out.println(s.length());
						textArea.setText(s);
						openedText = false;
						openedPicture = true;

						/*
						 * File fi = fileChooser.getSelectedFile(); BufferedReader br = new
						 * BufferedReader(new FileReader(fi.getPath()));
						 * 
						 * String line = ""; String s = ""; while ((line=br.readLine()) != null) { s +=
						 * line; } textArea.setText(s); if ( br != null) br.close();
						 */
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, e2.getMessage());
					}

				}

			}
		});
		btnNewButton_2.setBackground(Color.CYAN);
		btnNewButton_2.setBounds(10, 40, 95, 23);
		frame.getContentPane().add(btnNewButton_2);

		txtEncodingAlgoritm = new JTextField();
		txtEncodingAlgoritm.setHorizontalAlignment(SwingConstants.CENTER);
		txtEncodingAlgoritm.setText("Encoding Algoritm");
		txtEncodingAlgoritm.setBackground(SystemColor.scrollbar);
		txtEncodingAlgoritm.setBounds(170, 55, 107, 20);
		txtEncodingAlgoritm.setEditable(false);
		frame.getContentPane().add(txtEncodingAlgoritm);
		txtEncodingAlgoritm.setColumns(10);
		JRadioButton RLEs = new JRadioButton("RLE");
		JRadioButton Huffmanes = new JRadioButton("Huffman");
		JRadioButton Encodes = new JRadioButton("Encode");
		JRadioButton Decodes = new JRadioButton("Decode");

		Huffmanes.setSelected(true);
		Encodes.setSelected(true);

		Huffmanes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Huffmanes.setSelected(true);
				if (Huffmanes.isSelected()) {
					RLEs.setSelected(false);
				}
			}
		});
		Huffmanes.setBounds(58, 76, 109, 23);
		frame.getContentPane().add(Huffmanes);

		// JRadioButton RLEs = new JRadioButton("RLE");
		RLEs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				RLEs.setSelected(true);
				if (RLEs.isSelected()) {
					Huffmanes.setSelected(false);
				}

			}
		});
		RLEs.setBounds(283, 76, 109, 23);
		frame.getContentPane().add(RLEs);

		txtMode = new JTextField();
		txtMode.setText("Mode");
		txtMode.setHorizontalAlignment(SwingConstants.CENTER);
		txtMode.setColumns(10);
		txtMode.setBackground(SystemColor.scrollbar);
		txtMode.setBounds(170, 124, 107, 20);
		txtMode.setEditable(false);
		frame.getContentPane().add(txtMode);

		// JRadioButton Encodes = new JRadioButton("Encode");
		Encodes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Encodes.setSelected(true);
				if (Encodes.isSelected()) {
					Decodes.setSelected(false);
				}
			}
		});
		Encodes.setBounds(58, 155, 109, 23);
		frame.getContentPane().add(Encodes);

		// JRadioButton Decodes = new JRadioButton("Decode");
		Decodes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Decodes.setSelected(true);
				if (Decodes.isSelected()) {
					Encodes.setSelected(false);
				}
			}
		});
		Decodes.setBounds(283, 155, 109, 23);
		frame.getContentPane().add(Decodes);

		JButton btnNewButton = new JButton("Save");
		btnNewButton.setBackground(new Color(0, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(java.awt.event.ActionEvent evt) {

				if (openedText) {

					if (Huffmanes.isSelected() && Encodes.isSelected()) {
						// saves Huffman encoded text as binary
						String encoded = Huffman.compress(textArea.getText());
						// save encoded in a file

					} else if (Huffmanes.isSelected() && Decodes.isSelected()) {
						// saves Huffman decoded text as regular file
						String decoded = Huffman.decompress(textArea.getText());
						// save decoded in a file

					} else if (RLEs.isSelected() && Encodes.isSelected()) {
						// saves RLE encoded text as regular file (needs for next labdarba)
						String encoded = RLE.compress(textArea.getText());
						// save encoded in a file

					} else if (RLEs.isSelected() && Decodes.isSelected()) {
						// saves RLE decoded text as regular file (needs for next labdarba)
						String decoded = RLE.decompress(textArea.getText());
						// save decoded in a file

					}

					/*
					 * HOW IT USED TO BE (HUFFMAN ENCODING + DECODING WITH TEXT (WAS WRONG))
					 * JFileChooser fileChooser = new JFileChooser(new File("c:\\"));
					 * fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
					 * fileChooser.setDialogTitle("Save file"); fileChooser.setFileFilter(new
					 * Filter(".txt", "Text File")); fileChooser.setFileFilter(new Filter(".docx",
					 * "Microsoft Word Documents")); int result = fileChooser.showSaveDialog(null);
					 * if (result == JFileChooser.APPROVE_OPTION) { String content =
					 * textArea.getText(); File fi = fileChooser.getSelectedFile(); try { FileWriter
					 * fw = new FileWriter(fi.getPath()); fw.write(content); fw.flush(); fw.close();
					 * } catch (Exception e2) { JOptionPane.showMessageDialog(null,
					 * e2.getMessage());
					 * 
					 * }
					 * 
					 * }
					 */

				} else if (openedPicture) {

					if (Huffmanes.isSelected() && Encodes.isSelected()) {
						// saves Huffman encoded text as binary (would be cool for next labrab but if you cant well ok)
						String encoded = Huffman.compress(textArea.getText());
						// save encoded in a file

						
						
					} else if (Huffmanes.isSelected() && Decodes.isSelected()) {
						// saves Huffman decoded text as bmp (would be cool for next labrab but if you cant well ok)
						String decoded = Huffman.decompress(textArea.getText());
						// save decoded in a file

						
						
						
					} else if (RLEs.isSelected() && Encodes.isSelected()) {
						// Saves RLE encoded picture as whatever takes less memory.
						String encoded = RLE.compressPicture(textArea.getText());
						// save encoded in a file
						
						
						
					} else if (RLEs.isSelected() && Decodes.isSelected()) {
						// Saves decoded picture as bmp.
						String decoded = RLE.decompressPicture(textArea.getText());
						// save decoded in a file
						
						
						
					}

				} else {
					JFrame f = new JFrame();
					JOptionPane.showMessageDialog(f, "Open any file first!");
				}
			}
		});

		btnNewButton.setBounds(170, 227, 124, 23);
		frame.getContentPane().add(btnNewButton);

		/*
		 * JButton btnNewButton_2 = new JButton("Unzip");
		 * btnNewButton_2.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { JFileChooser fileChooser = new
		 * JFileChooser(new File("c:\\")); fileChooser.setDialogTitle("Unzip File");
		 * fileChooser.setFileFilter(new Filter(".txt", "Text File"));
		 * fileChooser.setFileFilter(new Filter(".docx", "Microsoft Word Documents"));
		 * int result = fileChooser.showOpenDialog(null); if(result ==
		 * JFileChooser.APPROVE_OPTION) { try { File fi = fileChooser.getSelectedFile();
		 * BufferedReader br = new BufferedReader(new FileReader(fi.getPath())); String
		 * line = ""; String s = ""; while ((line=br.readLine()) != null) { s += line;
		 * 
		 * } String decompressed = Huffman.decompress(s);
		 * textArea.setText(decompressed); if ( br != null) br.close();
		 * 
		 * } catch (Exception e2) { JOptionPane.showMessageDialog(null,
		 * e2.getMessage()); } } } });
		 * 
		 * 
		 * btnNewButton_2.setBackground(new Color(0, 255, 255));
		 * btnNewButton_2.setBounds(10, 193, 141, 57);
		 * frame.getContentPane().add(btnNewButton_2);
		 */
	}
}