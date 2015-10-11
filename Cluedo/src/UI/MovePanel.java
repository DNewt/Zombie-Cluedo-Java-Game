package UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import Cluedo.Turn;

/**
 * Sets up the Move choice panel which displays the 5 button choices avaliable to the user
 * @author newtondavi2
 *
 */
public class MovePanel extends JPanel {

	BufferedImage movePanel;
	Dimension buttonSize = new Dimension(100, 30);

	/**
	 * Sets up each button with its name and hotkey.
	 * Also adds a action listener to each button which causes the button to perform the choice of move
	 */
	public MovePanel(){

		JButton moveButton = new JButton("MOVE (Alt + 1)");
		JButton sugButton = new JButton("SUGGESTION (Alt + 2)");
		JButton accButton = new JButton("ACCUSATION (Alt + 3)");
		JButton stairButton = new JButton("STAIRCASE (Alt + 4)");
		JButton endButton = new JButton("END TURN (Alt + 5)");

		moveButton.setMnemonic('1');
		sugButton.setMnemonic('2');
		accButton.setMnemonic('3');
		stairButton.setMnemonic('4');
		endButton.setMnemonic('5');
		moveButton.setSize(buttonSize);
		sugButton.setSize(buttonSize);
		accButton.setSize(buttonSize);
		stairButton.setSize(buttonSize);
		endButton.setSize(buttonSize);

		this.add(moveButton, BorderLayout.CENTER);
		this.add(sugButton, BorderLayout.CENTER);
		this.add(accButton, BorderLayout.CENTER);
		this.add(stairButton, BorderLayout.CENTER);
		this.add(endButton, BorderLayout.CENTER);

		setVisible(true);

		moveButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Turn.moveButton = true;
				moveButton.setEnabled(false);
			}
		});

		sugButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Turn.suggestionButton = true;
				sugButton.setEnabled(false);
			}
		});

		endButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				moveButton.setEnabled(true);
				sugButton.setEnabled(true);
				accButton.setEnabled(true);
				stairButton.setEnabled(true);
				Turn.endButton = true;


			}
		});

		accButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				Turn.accusationButton = true;
				accButton.setEnabled(false);
				moveButton.setEnabled(true);
				sugButton.setEnabled(true);
				accButton.setEnabled(true);
				stairButton.setEnabled(true);


			}
		});

		stairButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Turn.stairButton = true;
				stairButton.setEnabled(false);
			}
		});


	}

}