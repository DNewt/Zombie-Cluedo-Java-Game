package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Displays the amount of steps the user can take in text
 * @author newtondavi2
 *
 */
public class DicePanel extends JPanel {

	BufferedImage dice;
	Font font = new Font("Courier", Font.BOLD,200);
	JLabel steps = new JLabel();

	/**
	 * Sets up the steps JLabel which displays the amount of steps left for the user to take
	 */
	public DicePanel(){
		steps.setForeground(Color.GRAY);
		steps.setFont(font);
		steps.setPreferredSize(new Dimension(500,100));
		steps.setVisible(true);
		steps.setHorizontalAlignment(SwingConstants.CENTER);
		steps.setVerticalAlignment(SwingConstants.CENTER);

		this.setLayout(new BorderLayout());
		this.add(steps, BorderLayout.CENTER);

		try {
			dice = ImageIO.read(new File("src/card_images/hello.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(dice, 0,0, 200, 200, null);
	}

	/**
	 * Sets the size of the dicePanel to be 200,200
	 */
	public Dimension getPreferredSize() {
		Dimension d = new Dimension(200, 200);
		return d;
	}


	/**
	 * Updates the number of steps the user can currently take, decrementing on each step;
	 * @param step
	 */
	public void updateSteps(int step) {
		if(step != 0){
			steps.setText("" + step);
			steps.setHorizontalAlignment(SwingConstants.CENTER);
			steps.setVerticalAlignment(SwingConstants.CENTER);
			steps.setToolTipText("Steps left");
		} else {
			steps.setText("");
		}

		repaint();

	}
}
