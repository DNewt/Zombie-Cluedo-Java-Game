package UI;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * This class is redundent, it is currently set up for a later version of Zombie Cluedo
 * for when zombie character animation is added. 
 * The rate of Thread repaint updates is currently controlled by a boolean rather than 
 * a consistent update. 
 * This is just for more control over the repaints while it is not neccessary to have consistent updates. 
 * @author newtondavi2
 *
 */
public class Timer extends Thread {

	BoardCanvas myCanvas = new BoardCanvas(null);

	public Timer(BoardCanvas c){
		myCanvas = c;
	}

	public void run() {
		while(1==1) {

			try {
				Thread.sleep(1); // 0.1s delay
				if(BoardCanvas.repaintNow){
					//System.out.println("IN HERE NOW");
					myCanvas.repaint(); // repaint the canvas

				}
				BoardCanvas.repaintNow = false;
			} catch(InterruptedException e) {

			}
		}


	}
}