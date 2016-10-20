package mygui;

import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class MyGUI
{
	private JTextField feedPig = new JTextField("When your guinea pig is hungry he says wheak wheak");
	private static Random random = new Random();
	private JButton wheakButton = new JButton("wheak wheak");
	private JFrame aFrame = new JFrame("Guinea Pig Trough");
	
	public void GuineaPigTrough()
	{
		aFrame.setSize(200,200);
		aFrame.setLocationRelativeTo(null);
		aFrame.setVisible(true);
		aFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aFrame.getContentPane().setLayout(new BorderLayout());
		aFrame.getContentPane().add(wheakButton, BorderLayout.SOUTH);
		aFrame.getContentPane().add(feedPig, BorderLayout.CENTER);
		wheakButton.addActionListener(new WheakActionListener());
	}
	
	private class WheakActionListener implements ActionListener
	{		
		public void actionPerformed(ActionEvent event)
		{
			if( random.nextFloat() < 0.5 )
			{
				feedPig.setText("Red peppers, Orange oranges, Yellow yams, Green kiwi, Blue berries, Indigo ice cream, Violet vegetables!");
			}
			else
			{
				feedPig.setText("Oh yay, its hay.....again. Weak.");
			}
		}
	}
	
public static void main(String[] args)
{
	new MyGUI();
}
}

