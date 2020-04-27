package gui;

import logic.*;

import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.BitSet;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.DatatypeConverter;

//import com.sun.javafx.scene.paint.GradientUtils.Point;

//import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

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
		frame.getContentPane().add(textArea);
		textArea.setColumns(10);
		scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(111, 11, 313, 40);
		frame.getContentPane().add(scrollPane);

		JRadioButton RLEs = new JRadioButton("RLE");
		JRadioButton Huffmanes = new JRadioButton("Huffman");
		JRadioButton Encodes = new JRadioButton("Encode");
		JRadioButton Decodes = new JRadioButton("Decode");

		Huffmanes.setSelected(true);
		Encodes.setSelected(true);
		
		JButton btnNewButton_1 = new JButton("Open txt");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (Huffmanes.isSelected() && Decodes.isSelected()) {
					  JFileChooser fileChooser = new JFileChooser(new File("c:\\"));
					  fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
					  fileChooser.setDialogTitle("Zip File"); 
					  fileChooser.setFileFilter(new Filter(".txt", "Text File")); 
					  int res1 = fileChooser.showOpenDialog(null);
					  JFileChooser fileChooser2 = new JFileChooser(new File("c:\\"));
					  fileChooser2.setFileSelectionMode(JFileChooser.FILES_ONLY);
					  fileChooser.setDialogTitle("Zip File"); 
					  fileChooser2.setFileFilter(new Filter(".txt", "Text File")); 
					   int res2 = fileChooser2.showOpenDialog(null);
					   if (res1 == JFileChooser.APPROVE_OPTION && res2 == JFileChooser.APPROVE_OPTION) {
							try {
								String concatenate = fileToString(fileChooser.getSelectedFile(), fileChooser2.getSelectedFile());
								textArea.setText(concatenate);
								openedText = true;
								openedPicture = false;

							} catch (Exception e2) {
								JOptionPane.showMessageDialog(null, e2.getMessage());
							}
					   }
				} else {
				JFileChooser fileChooser = new JFileChooser(new File("c:\\"));
				fileChooser.setDialogTitle("Zip File");
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
				fileChooser.setFileFilter(new Filter(".bmp", "Bitmap Image"));
				int result = fileChooser.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					try {
						File fi = fileChooser.getSelectedFile();
						BufferedImage img = ImageIO.read(fi);
						int[] pixels = img.getRGB(0, 0, img.getWidth(), img.getHeight(), null, 0, img.getWidth());
						String[] miniS = new String[101];
						for (int i = 0; i < 101; i++) {
							miniS[i] = "";
						}
						String s = "";
						int percent = 0;
						int onePerc = pixels.length/100;
			//			int current = 0;
						for (int i = 0, current = 0; i < pixels.length; i++) {
							current++;
							String bin = Integer.toBinaryString(pixels[i]);
							if (bin.equals("11111111111111111111111111111111")) {
								bin = "0";
							} else {
								bin = "1";
							}
							if (current == onePerc) {
								percent++;
								System.out.println(percent + "%");
								current = 0;
							}
							miniS[percent] = miniS[percent] + bin;
						}
						
						for (String miniString : miniS) {
							s = s + miniString;
						}
						textArea.setText(s);
						openedText = false;
						openedPicture = true;
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
		txtEncodingAlgoritm.setBounds(141, 102, 153, 20);
		txtEncodingAlgoritm.setEditable(false);
		frame.getContentPane().add(txtEncodingAlgoritm);
		txtEncodingAlgoritm.setColumns(10);


		Huffmanes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Huffmanes.setSelected(true);
				if (Huffmanes.isSelected()) {
					RLEs.setSelected(false);
				}
			}
		});
		Huffmanes.setBounds(47, 131, 109, 23);
		frame.getContentPane().add(Huffmanes);
		RLEs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				RLEs.setSelected(true);
				if (RLEs.isSelected()) {
					Huffmanes.setSelected(false);
				}

			}
		});
		RLEs.setBounds(283, 131, 109, 23);
		frame.getContentPane().add(RLEs);

		txtMode = new JTextField();
		txtMode.setText("Mode");
		txtMode.setHorizontalAlignment(SwingConstants.CENTER);
		txtMode.setColumns(10);
		txtMode.setBackground(SystemColor.scrollbar);
		txtMode.setBounds(141, 163, 153, 20);
		txtMode.setEditable(false);
		frame.getContentPane().add(txtMode);

		Encodes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Encodes.setSelected(true);
				if (Encodes.isSelected()) {
					Decodes.setSelected(false);
				}
			}
		});
		Encodes.setBounds(47, 193, 109, 23);
		frame.getContentPane().add(Encodes);

		Decodes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Decodes.setSelected(true);
				if (Decodes.isSelected()) {
					Encodes.setSelected(false);
				}
			}
		});
		Decodes.setBounds(283, 193, 109, 23);
		frame.getContentPane().add(Decodes);

		JButton btnNewButton = new JButton("Save");
		btnNewButton.setBackground(new Color(0, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(java.awt.event.ActionEvent evt) {

				if (openedText) {

					if (Huffmanes.isSelected() && Encodes.isSelected()) {
						// saves Huffman encoded text as binary
						String encoded = Huffman.compress(textArea.getText());
		//				System.out.println(encoded);
						// save encoded in a file
						String[] split = encoded.split("(?=\\{)");
						System.out.println(split[0]);
						System.out.println(split[1]);
						String result1=split[0];
						String result2=split[1];
								
						    BitSet bitset = new BitSet(result1.length());
						    for (int i = 0; i < result1.length(); i++) {
						        if (result1.charAt(i) == '1') {
						            bitset.set(i);
						        }
						    }
										
						  JFileChooser fileChooser = new JFileChooser(new File("c:\\"));
						  fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
						  fileChooser.setDialogTitle("Save file"); 
						  fileChooser.setFileFilter(new Filter(".txt", "Text File")); 
						  int res1 = fileChooser.showSaveDialog(null);
						  JFileChooser fileChooser2 = new JFileChooser(new File("c:\\"));
						  fileChooser2.setFileSelectionMode(JFileChooser.FILES_ONLY);
						  fileChooser2.setDialogTitle("Save file"); 
						  fileChooser2.setFileFilter(new Filter(".txt", "Text File")); 
						  int res2 = fileChooser2.showSaveDialog(null);
						  if (res1 == JFileChooser.APPROVE_OPTION && res2 == JFileChooser.APPROVE_OPTION) { 
							  File fi = fileChooser.getSelectedFile();
							  File fi2 = fileChooser2.getSelectedFile();
							  try { 
								  DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(fi.getPath()));
								  DataOutputStream dataOutputStream2 = new DataOutputStream(new FileOutputStream(fi2.getPath()));
								  dataOutputStream.write(bitset.toByteArray()); 
								  dataOutputStream2.write(result2.getBytes());
								  dataOutputStream.close();
								  dataOutputStream2.close();
						  } catch (Exception e2) { 
							  JOptionPane.showMessageDialog(null, e2.getMessage());
						  }
						  }
					} else if (Huffmanes.isSelected() && Decodes.isSelected()) {
						// saves Huffman decoded text as regular file
						String decoded = Huffman.decompress(textArea.getText());
		//				System.out.println(decoded);
						// save decoded in a file
						 JFileChooser fileChooser = new JFileChooser(new File("c:\\"));
						  fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
						  fileChooser.setDialogTitle("Save file"); 
						  fileChooser.setFileFilter(new Filter(".txt", "Text File")); 
						  int res1 = fileChooser.showSaveDialog(null);
						  if (res1 == JFileChooser.APPROVE_OPTION) { 
							  File fi = fileChooser.getSelectedFile();
							  try { 
								  FileWriter fw = new FileWriter(fi.getPath());
									fw.write(decoded);
									fw.flush();
									fw.close();
						  } catch (Exception e2) { 
							  JOptionPane.showMessageDialog(null, e2.getMessage());
						  }
						  }

					} else if (RLEs.isSelected() && Encodes.isSelected()) {
						// saves RLE encoded text as whatever eats less space (needs for next labdarba)
						String encoded = RLE.compress(textArea.getText());
		//				System.out.println(encoded);
						// save encoded in a file
						JFileChooser fileChooser = new JFileChooser(new File("c:\\"));
						fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
						fileChooser.setDialogTitle("Save file");
						fileChooser.setFileFilter(new Filter(".txt", "Text File"));
						fileChooser.setFileFilter(new Filter(".docx", "Microsoft Word Documents"));
						int result = fileChooser.showSaveDialog(null);
						if (result == JFileChooser.APPROVE_OPTION) {
							File fi = fileChooser.getSelectedFile();
							try {
								FileWriter fw = new FileWriter(fi.getPath());
								fw.write(encoded);
								fw.flush();
								fw.close();
							} catch (Exception e2) {
								JOptionPane.showMessageDialog(null, e2.getMessage());

							}

						}

					} else if (RLEs.isSelected() && Decodes.isSelected()) {
						// saves RLE decoded text as regular file (needs for next labdarba)
						String decoded = RLE.decompress(textArea.getText());
		//				System.out.println(decoded);
						// save decoded in a file
						JFileChooser fileChooser = new JFileChooser(new File("c:\\"));
						fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
						fileChooser.setDialogTitle("Save file");
						fileChooser.setFileFilter(new Filter(".txt", "Text File"));
						fileChooser.setFileFilter(new Filter(".docx", "Microsoft Word Documents"));
						int result = fileChooser.showSaveDialog(null);
						if (result == JFileChooser.APPROVE_OPTION) {
							File fi = fileChooser.getSelectedFile();
							try {
								FileWriter fw = new FileWriter(fi.getPath());
								fw.write(decoded);
								fw.flush();
								fw.close();
							} catch (Exception e2) {
								JOptionPane.showMessageDialog(null, e2.getMessage());
							}

						}

					}

				} else if (openedPicture) {

					if (Huffmanes.isSelected() && Encodes.isSelected()) {
						// saves Huffman encoded text as binary (for next labdarba)
						String encoded = Huffman.compress(textArea.getText());
		//				System.out.println(encoded);
						// save encoded in a file

					} else if (Huffmanes.isSelected() && Decodes.isSelected()) {
						// saves Huffman decoded text as bmp (for next labdarba)
						String decoded = Huffman.decompress(textArea.getText());
		//				System.out.println(decoded);
						decoded = toARGB(decoded);
						// save decoded in a file

					} else if (RLEs.isSelected() && Encodes.isSelected()) {
						// Saves RLE encoded picture.
						
						String encoded = RLE.compressPicture(textArea.getText());
		//				System.out.println(encoded);
						// save encoded in a file
						JFileChooser fileChooser = new JFileChooser(new File("c:\\"));
						fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
						fileChooser.setDialogTitle("Save file");
						int result = fileChooser.showSaveDialog(null);
						if (result == JFileChooser.APPROVE_OPTION) {
							File fi = fileChooser.getSelectedFile();
							try {
								FileWriter fw = new FileWriter(fi.getPath());
								fw.write(encoded);
								fw.flush();
								fw.close();
							} catch (Exception e2) {
								JOptionPane.showMessageDialog(null, e2.getMessage());

							}

						}

					} else if (RLEs.isSelected() && Decodes.isSelected()) {
						// Saves decoded picture.

						String decoded = RLE.decompressPicture(textArea.getText());
						int[] intPixels = new int[decoded.length()];
						for (int i = 0; i < decoded.length(); i++) {
							if (decoded.charAt(i) == '0') {
								intPixels[i] = -1;
							} else {
								intPixels[i] = -16777216;
							}
						}

						int width = (int) Math.sqrt((double) intPixels.length);
						int height = (int) Math.sqrt((double) intPixels.length);

						BufferedImage intImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
						Graphics2D g2d = intImage.createGraphics();
						Color white = new Color(-1, true);
						Color black = new Color(-16777216, true);
						int counter = 0;

						for (int i = 0; i < height; i++) {
							for (int j = 0; j < width; j++) {
								if (intPixels[counter] == -1) {
									g2d.setColor(white);
								} else {
									g2d.setColor(black);
								}
								g2d.fillRect(j, i, 2, 2);
								counter++;
							}
						}
						g2d.dispose();

						JFileChooser fileChooser = new JFileChooser(new File("c:\\"));
						fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
						fileChooser.setDialogTitle("Save file");
						fileChooser.setFileFilter(new Filter(".png", "Image"));
						int result = fileChooser.showSaveDialog(null);
						if (result == JFileChooser.APPROVE_OPTION) {
							File fi = fileChooser.getSelectedFile();
							try {
								ImageIO.write(intImage, "png", fi);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}

				} else {
					JFrame f = new JFrame();
					JOptionPane.showMessageDialog(f, "Open any file first!");
				}
			}
		});

		btnNewButton.setBounds(141, 225, 153, 23);
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_3 = new JButton("Open RLE'd image");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser(new File("c:\\"));
				fileChooser.setDialogTitle("Unzip Picture");
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
						openedText = false;
						openedPicture = true;

						if (br != null)
							br.close();

					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, e2.getMessage());
					}

				}
			}
		});
		btnNewButton_3.setBounds(10, 69, 146, 25);
		btnNewButton_3.setBackground(Color.CYAN);
		frame.getContentPane().add(btnNewButton_3);
	}
		
    private static String fileToString(File str, File map) {

    	byte[] a, b;
    	
		  //init array with file length
		a = new byte[(int) str.length()]; 
		b = new byte[(int) map.length()];
		//concatenated = new byte[(int) str.length() + (int) map.length()];

		FileInputStream fis, fis2;
		try {
			fis = new FileInputStream(str);
			fis.read(a); 
			fis2 = new FileInputStream(map);
			fis2.read(b); 
			  fis.close();
			  fis2.close(); 					
		} catch (IOException e1) {
			e1.printStackTrace();
		}		
		BitSet bS = BitSet.valueOf(a);
		String decode = "";
		for (int i=0;i<=bS.length();i++){
			char newDigit = (bS.get(i)==true?'1':'0');			
			decode = decode + newDigit;
		}

		String result = decode + new String(b, java.nio.charset.StandardCharsets.ISO_8859_1);
		return result;  	
    }

	public static String toARGB(String s) {
		s = s.replace("0", "z");
		s = s.replace("1", "11111111000000000000000000000000");
		s = s.replace("z", "11111111111111111111111111111111");
		return s;
	}
}
