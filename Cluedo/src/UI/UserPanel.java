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

import Cluedo.Player;

/**
 * The userPanel displays which player is currently undergoing a Turn
 * @author newtondavi2
 *
 */
public class UserPanel extends JPanel {

		BufferedImage playerTurnDisplay;
		Font font = new Font("Courier", Font.BOLD,42);
		JLabel playerName = new JLabel();

		public UserPanel(){
			playerName.setForeground(Color.WHITE);
			playerName.setFont(font);
			playerName.setPreferredSize(new Dimension(500,100));
			playerName.setVisible(true);
			playerName.setAlignmentX(250);
			playerName.setHorizontalAlignment(SwingConstants.CENTER);
			playerName.setVerticalAlignment(SwingConstants.CENTER);

			this.setLayout(new BorderLayout());
			this.add(playerName, BorderLayout.CENTER);
		}

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			g.drawImage(playerTurnDisplay, 0,0, 500, 100, null);
		}

		public Dimension getPreferredSize() {
			Dimension d = new Dimension(500, 100);
			return d;
	    }

		/**
		 * Sets the current userPanel colour to the current player who is undergoing a turn.
		 * @param player
		 * @throws IOException
		 */
		public void setPanel(Player player) throws IOException {
			System.out.println("IN HERE");
			playerTurnDisplay = player.getLabel();
			playerName.setText(player.getPName());
			playerName.setHorizontalAlignment(SwingConstants.CENTER);
			playerName.setVerticalAlignment(SwingConstants.CENTER);
			playerName.setToolTipText(player.getPiece());
			repaint();
		}
}