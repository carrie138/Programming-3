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
	private JTextField feedPig = new JTextField("When your guinea pig is hungry he says wheak wheak");
	private static Random random = new Random();
	private JFrame aFrame = new JFrame("Guinea Pig Trough");
	private boolean cancel = false;
	private JButton wheakButton = new JButton("Wheak Wheak");
	private JButton cancelButton = new JButton("Cancel");
	
	public void GuineaPigTrough()
	{
		aFrame.setSize(400,300);
		aFrame.setLocationRelativeTo(null);
		aFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aFrame.getContentPane().setLayout(new BorderLayout());
		aFrame.add(getBottomPanel(), BorderLayout.SOUTH);
		aFrame.getContentPane().add(feedPig, BorderLayout.CENTER);
		setJMenuBar(getMyMenuBar());
		aFrame.setVisible(true);
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
				feedPig.setText("Red peppers, Orange oranges, Yellow yams, Green kiwi, Blue berries, Indigo ice cream, Violet vegetables!");
			}
			else
			{
				feedPig.setText("Oh yay, its hay.....again. Weak.");
			}	
		
		cancel = false;
		wheakButton.setEnabled(false);
		cancelButton.setEnabled(true);
		textArea.setText("Feeding the pig\n");
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
					Thread.sleep(1000);
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
		
		JMenuItem openItem = new JMenuItem("Open");
		openItem.setMnemonic('O');
		fileMenu.add(openItem);
		
		openItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				loadFromFile();
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
	
	private void loadFromFile()
	{
		JFileChooser jfc = new JFileChooser();
		
		File chosenFile = jfc.getSelectedFile();
	
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(chosenFile));
			String line = reader.readLine();
			reader.close();
			if( line == null || reader.readLine() != null)
				throw new Exception("Unexpected file format");
		
			StringTokenizer sToken = new StringTokenizer(line);
			
			if( sToken.countTokens() != 1)
				throw new Exception("Unexpected file format");
			
		}
			
		catch(Exception ex)
		{
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Could not read file.", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main(String[] args)
	{
		new MyGUI();
	}
}

