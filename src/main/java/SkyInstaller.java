// IMPOSSIBLE NOT TAKEN FROM SKYBLOCK ADDONS???!?!?!?!?!??!?!?!

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import me.nat3z.JFrame.AskLoc;

public class SkyInstaller implements ActionListener {

	public static void main(String[] args) {
		new SkyInstaller();
	}
	
	public SkyInstaller() {
		
		  AskLoc.changetolook();
		
	      JFrame frame = new JFrame("Nate's Secret Mod Installer");
	      JPanel panel = new JPanel();
	      JButton button = new JButton("Install");
	      Dimension size = button.getPreferredSize();
	      button.setBounds(115, 180, 250, size.height);
	      
	      frame.getContentPane();
	      JLabel label = new JLabel("Nate's Secret Mod");
	      JLabel label1 = new JLabel("Delete all Past Versions And Install The Mod");
	      JLabel label2 = new JLabel("Still Requires Forge Client Installed");
	      
	      Dimension size2 = label1.getPreferredSize();
	      
		  label2.setFont(new Font("Arial", Font.PLAIN,10));
	      label2.setBounds(150, 155, 500, size2.height);
	     
		  label1.setFont(new Font("Arial", Font.PLAIN,10));
	      label1.setBounds(130, 145, 500, size2.height);
	      
		  label.setFont(new Font("Arial", Font.BOLD,30));
	      Dimension size1 = label.getPreferredSize();
	      label.setBounds(110, 100, size1.width, size1.height);
	      
	      button.addActionListener(this);
	      
	      panel.setLayout(null);
	      panel.add(label);
	      panel.add(label1);
	      panel.add(label2);
	      panel.add(button);

	      
	      
	      panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	      frame.add(panel);
		  frame.setPreferredSize(new Dimension(500, 360));
	      frame.setVisible(true);
	      frame.pack();
	      frame.setLocationRelativeTo(null);
	      
	      frame.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new AskLoc();
	}
	
}
