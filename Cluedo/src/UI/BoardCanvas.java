package UI;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Cluedo.Player;
import Cluedo.Setup;
import Cluedo.Turn;
import Cluedo.WeaponToken;

/**
 * The Board Canvas holds the Zombie Cluedo Board.
 * It takes the BoardFrame game Frame as its constructor as this is what it will be displayed on/attached to
 * @author newtondavi2
 *
 */
public class BoardCanvas extends JPanel implements MouseListener {

	public static boolean repaintNow; //Used for animation

	public BufferedImage board; //Board image
	private BoardFrame frame; //The frame this canvas is connected to
	public int playerAmount = 0; //Amount of players
	public int playerRow = 0; //player row
	public int playerCol = 0; //player col
	public String playerPiece = "";
	public ArrayList<String> playersToDraw = new ArrayList<String>();
	public KeyListener key;
	private int weaponRow; //weapons row
	private int weaponCol; // weapons col

	/**
	 * Sets parent frame to be the BoardFrame frame.
	 * Also sets the size of the board and adds a mouse listener.
	 * Sets the board picture.
	 * @param frame
	 */
	public BoardCanvas(BoardFrame frame){
		this.frame = frame;
		try {
			board = ImageIO.read(new File("src/card_images/Cluedo_Board.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.setPreferredSize(getPreferredSize());
		this.addMouseListener(this);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

	}

	/**
	 * Paints the graphics on the board and sets a label to each player/weapon so it can interact with the CardDisplay on hover.
	 */
	public void paint(Graphics g){
		removeAll(); //Removes all the created labels from the players
		g.drawImage(board, 0, 0, 600, 600, null);

		//Paints every player on the board and creates a label on the label which holds a mouse listener
		for(Player p : Setup.players){
			if(!p.getEliminated()){
				JLabel setLabel = new JLabel();
				drawPlayer(p);
				g.drawImage(p.getImage(), this.playerCol, this.playerRow, 24, 25, null);
				Image dimg = p.getImage().getScaledInstance(25, 24,
						Image.SCALE_SMOOTH);
				setLabel.setIcon(new ImageIcon(dimg));
				//setLabel.setToolTipText(p.getPiece());  THIS WAS GOING TO BE USED BEFORE I UPDATED THE HOVER TO DISPLAY A PICTURE IN THE CARDDISPLAY
				//Did not use ToolTipText as I was not interested in calling an extra repaint after hover on the components underneath the tooltip.

				setLabel.setBounds(this.playerCol,this.playerRow, 24, 25); //Sets the size of the players label
				this.add(setLabel);
				setLabel.addMouseListener(new MouseAdapter(){
					@Override
					/*
					 * This sets the side image to be what is currently being hovered over.
					 */
					public void mouseEntered(java.awt.event.MouseEvent e){
						CardDisplay.drawPicture = true;
						if(CardDisplay.drawPicture){
							frame.cardDisplay.setSideImage(p.getImage());
						}
					}

					@Override
					/*
					 * This unpaints the card that was previously being hovered over.
					 * It clears the side image
					 */
					public void mouseExited(java.awt.event.MouseEvent e){
						if(CardDisplay.drawPicture){
							frame.cardDisplay.repaint();
							CardDisplay.drawPicture = false;
						}
					}
				});
			}
		}

		//Paints every weapon on the board and creates a label on the label which holds a mouse listener
		for(WeaponToken p : Setup.weapons){

			JLabel setLabel = new JLabel();
			drawWeapon(p);
			g.drawImage(p.getImage(), this.weaponCol-75, this.weaponRow-72, 75, 72, null);
			Image dimg = p.getImage().getScaledInstance(75, 72,
					Image.SCALE_SMOOTH);
			setLabel.setIcon(new ImageIcon(dimg));
			//setLabel.setToolTipText(p.getPiece());
			setLabel.setBounds(this.weaponCol-75,this.weaponRow-72, 75, 72);
			this.add(setLabel);
			setLabel.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseEntered(java.awt.event.MouseEvent e){
					CardDisplay.drawPicture = true;
					if(CardDisplay.drawPicture){
						frame.cardDisplay.setSideImage(p.getImage());
					}
				}

				@Override
				public void mouseExited(java.awt.event.MouseEvent e){
					if(CardDisplay.drawPicture){
						frame.cardDisplay.repaint();
						CardDisplay.drawPicture = false;
					}
				}
			});
		}
	}

	/**
	 * Sets the weaponCol and weaponRow to the correct position and scale
	 * @param t
	 */
	private void drawWeapon(WeaponToken t){
		this.weaponCol = t.getCol()*25;
		this.weaponRow = t.getRow()*24;
	}

	/**
	 * Sets the playerCol and playerRow to the correct position and scale
	 * @param p
	 */
	private void drawPlayer(Player p) {
		this.playerCol = p.getCol()*25;
		this.playerRow = p.getRow()*24;
	}

	/**
	 * Gets the preferred size of the board
	 */
	public Dimension getPreferredSize() {
		Dimension d = new Dimension(600, 600);
		return d;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Turn.mouseClickPosX = e.getX(); // returns x position at mouse click
		Turn.mouseClickPosY = e.getY(); // returns y position at mouse click
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		//System.out.println("IN COMP");
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}