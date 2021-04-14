package me.nat3z.JFrame;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import com.nat3z.skyqol.Main;

public class AskLoc {
	public AskLoc() {
		
		  changetolook();
		
	      JFrame frame = new JFrame("Nate's Secret Mod Installer");
	      JPanel panel = new JPanel();
	      JButton button = new JButton("Browse");
	      JTextField field2 = new JTextField("C:\\Users\\" + System.getProperty("user.name") + "\\AppData\\Roaming\\.minecraft\\mods\\");
	      
	      button.addActionListener(new ActionListener() {
	          @Override
	          public void actionPerformed(ActionEvent e) {
	             File currentDirectory = new File(field2.getText());

	             JFileChooser jFileChooser = new JFileChooser(currentDirectory);
	             jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	             jFileChooser.setAcceptAllFileFilterUsed(false);
	             
	             if (jFileChooser.showOpenDialog(jFileChooser) == 0) {
	                File file = jFileChooser.getSelectedFile();
	                field2.setText(file.getAbsolutePath());
	             }
	          }
	       });
	       button.setBounds(350, 40, 70, field2.getPreferredSize().height);
	      
	       frame.getContentPane();
	       JLabel label = new JLabel("Set Mod Folder Location");
	      
		   label.setFont(new Font("Arial", Font.BOLD, 20));
	       Dimension size1 = label.getPreferredSize();
	       label.setBounds(25, 10, size1.width, size1.height);
	      
	       JButton inst = new JButton("Install");
	       inst.setBounds(50, 80, 200, 25);
	       inst.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			      Path result = null;
			      try {
			    	 if (new File(Main.class.getProtectionDomain().getCodeSource().getLocation()
			        		    .toURI()).isDirectory()) {
			    		showErrorMessage("This is a directory.. Are you trying to do this in a development environment?");
			            return;
			         } else if (!new File(Main.class.getProtectionDomain().getCodeSource().getLocation()
			        		    .toURI()).exists()) {
			            showErrorMessage("This is awkward... This jar file does not exist.");
			            return;
			         } else if (!new File(field2.getText()).exists()) {
			            showErrorMessage("This directory does not exist.");
			            return;
			         }
			         File[] listOfFiles = new File(field2.getText()).listFiles();
			         
			    	 for (File file : listOfFiles) {
			    		    if (file.isFile() && file.getName().startsWith("Nate")) {
				    		    	attemptDelete(new File(field2.getText()));
				    		    	break;
			    		    }
			    	 }
			    	 
			    	 System.out.println(new File(field2.getText(), new File(Main.class.getProtectionDomain().getCodeSource().getLocation()
			        		    .toURI()).getName()).toPath());
			    	 System.out.println(field2.getText() + " ");
			    	  
			         result = Files.copy(new File(Main.class.getProtectionDomain().getCodeSource().getLocation()
			        		    .toURI()).toPath(), new File(field2.getText(), new File(Main.class.getProtectionDomain().getCodeSource().getLocation()
					        		    .toURI()).getName()).toPath());
			         
			         System.out.println("success");
			      } catch (IOException | URISyntaxException ex) {
			         System.out.println("Exception while moving file: " + ex.getMessage());
			         ex.printStackTrace();
			      }
			      if(result != null) {
			         System.out.println("File moved successfully.");
			         showYayMessage("Nate's Secret Mod has sucessfully installed to your mods folder.");
			         System.exit(0);
			         
			      } else{
			         System.out.println("File movement failed.");
			         showErrorMessage("Nate's Secret Mod installation error occurred. This usually happens if you already have the latest version installed.");
			      }	
			}
		});
	      
	      
	      
	    panel.setLayout(null);
	    panel.add(label);
	    panel.add(button);
	    panel.add(field2);
	    panel.add(inst);
	    frame.setResizable(false);
	    field2.setBounds(50, 40, 305, field2.getPreferredSize().height);
	      
	    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	    frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
	    frame.add(panel);
		frame.setPreferredSize(new Dimension(500, 150));
	    frame.setVisible(true);
	    frame.pack();
	    frame.setLocationRelativeTo(null);
	}
	
    public static void changetolook() {
		try {
	        // Set System L&F
			UIManager.setLookAndFeel(
	            UIManager.getSystemLookAndFeelClassName());
	    } 
	    catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
	    	e.printStackTrace();
	    }
    }
	
    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Nate's Secret Mod - Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public void showYayMessage(String message) {
        JOptionPane.showConfirmDialog(null, message, "Nate's Secret Mod - Success", JOptionPane.CANCEL_OPTION);
    }
    
    private void attemptDelete(File modfolder) {
    	try {
    		File[] listOfFiles = modfolder.listFiles();
    		for (File file : listOfFiles) {
    		    if (file.isFile() && file.getName().startsWith("Nate")) {
    		    	file.delete();
    		    }
    		}
		} catch (Exception e) {
			showErrorMessage("An Error Occured When Deleting Past Versions of Nate's Secret Mod.");
		}
    }
    
}
