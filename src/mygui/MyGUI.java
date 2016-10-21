package mygui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;
import java.util.StringTokenizer;

public class MyGUI extends JFrame
{
	private static final long serialVersionUID = 3794059922116115530L;
	private JTextArea textArea = new JTextArea();
	private JTextArea feedPig = new JTextArea("Your guinea pig is hungry: \"Wheak Wheak!\"");
	private static Random random = new Random();
	private JFrame aFrame = new JFrame("Guinea Pig Trough");
	private boolean cancel = false;
	private JButton wheakButton = new JButton("Feed pig");
	private JButton cancelButton = new JButton("Take food away");
	
	public MyGUI()
	{
		setLocationRelativeTo(null);
		setSize(600,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		add(getBottomPanel(), BorderLayout.SOUTH);
		getContentPane().add(feedPig, BorderLayout.CENTER);
		setJMenuBar(getMyMenuBar());
		setVisible(true);
	}
	
	private JPanel getBottomPanel()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
		wheakButton.addActionListener(new WheakActionListener());
		cancelButton.addActionListener(new CancelActionListener());
		panel.add(wheakButton);
		panel.add(cancelButton);
		
		return panel;
	}
	
	private class WheakActionListener implements ActionListener
	{		
		public void actionPerformed(ActionEvent e)
		{
			if( random.nextFloat() < 0.5 )
			{
				feedPig.setText("Whoop Whoop! Yum! Your guinea pig is eating - be patient or take his food away.");
			}
			else
			{
				feedPig.setText("Hay again? Weak Weak. Your guinea pig is eating - be patient or take away his food.");
			}	
		updateTextField();
		cancel = false;
		wheakButton.setEnabled(false);
		cancelButton.setEnabled(true);
		new Thread( new SlowActionRunnable()).start();
		}
	}
	
	private class SlowActionRunnable implements Runnable
	{
		public void run()
		{
			try
			{
				int numTimes = 0;
				
				while( ! cancel && numTimes < 100 )
				{
					numTimes++;
					Thread.sleep(100);
					
				}
			}
			catch(Exception ex)
			{
				textArea.setText(ex.getMessage());
				ex.printStackTrace();
			}
			try
			{
				SwingUtilities.invokeAndWait( new Runnable()
				{
					public void run()
					{
						wheakButton.setEnabled(true);
						feedPig.setText("Your guinea pig is hungry. Wheak wheak!");
						updateTextField();
						cancelButton.setEnabled(false);
					}
				});
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	private class CancelActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			cancel = true;
			feedPig.setText("Your guinea pig is hungry: \"Wheak Wheak!\"", BorderLayout.CENTER);
		}
	}

	private JMenuBar getMyMenuBar()
	{
		JMenuBar jmenuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');
		jmenuBar.add(fileMenu);
		
		JMenuItem saveItem = new JMenuItem("Save");
		fileMenu.add(saveItem);
		saveItem.setMnemonic('S');
		
		saveItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				saveToFile();
			}
		});
		
		return jmenuBar;
	}
	
	private void saveToFile()
	{
		JFileChooser jfc = new JFileChooser();
		
		File chosenFile = jfc.getSelectedFile();
		
		if( jfc.getSelectedFile().exists())
		{
			String message = "File " + jfc.getSelectedFile().getName() + " exists. Overwrite?";
			
			if( JOptionPane.showConfirmDialog(this, message) != JOptionPane.YES_OPTION)
				return;
		}
		
		try
		{
			BufferedWriter writer = new BufferedWriter(new FileWriter(chosenFile));
			writer.write(this.textArea + "\n");
			writer.flush(); writer.close();
		}

		catch(Exception ex)
		{
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Could not write file.", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void updateTextField()
	{
		feedPig.validate();		
	}
		
	public static void main(String[] args)
	{
		new MyGUI();
	}
}

