package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Cluedo.Card;
import Cluedo.Hand;
import Cluedo.Player;

/**
 * Displays the images of the current players cards in thier hand
 * @author newtondavi2
 *
 */
public class HandPanel extends JPanel {

	ArrayList<String> cardNames;
	ArrayList<Card> cards;
	JLabel container = new JLabel();
	ArrayList<JLabel> labels;
	BufferedImage handp;

	/**
	 * Draws the handPanel background and sets up the container which holds each card
	 */
	public HandPanel(){

		try {
			handp = ImageIO.read(new File("src/card_images/handPanel.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setLayout(new BorderLayout());
		setSize(900,200);
		this.setPreferredSize(getPreferredSize());
		this.setVisible(true);
		container.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.container.setSize(new Dimension(900,200));
		this.container.setPreferredSize(getPreferredSize());
		this.container.setBackground(Color.BLACK);
		add(container, BorderLayout.WEST);

	}

	/**
	 * Adds the card to the handPanel display
	 * @param image
	 * @param i
	 */
	public void addImage(Image image, int i){

		JLabel label = new JLabel(new ImageIcon(image));
		label.setSize(new Dimension(120,200));
		label.setBounds(120*i, 0, 120, 200);
		labels.add(label);

	}

	/**
	 * Clears all the images in the display
	 */
	public void clearHand(){
		labels = new ArrayList<JLabel>();
		container.removeAll();
	}
	/**
	 * Gets the current players hand and the images which relate to each card
	 * @param p
	 */
	public void getPlayersHand(Player p){
		clearHand();
		cards = p.getHand().returnHand();
		for(int i = 0; i < cards.size(); i++){
			this.addImage(cards.get(i).getImage(), i);
		}
		for(int i = 0; i < labels.size(); i++){
			container.add(labels.get(i));
		}

		add(container, BorderLayout.WEST);
		repaint();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
			g.drawImage(handp, 0,0, 900, 200, null);
	}

	public Dimension getPreferredSize() {
		Dimension d = new Dimension(900, 200);
		return d;
    }
}
