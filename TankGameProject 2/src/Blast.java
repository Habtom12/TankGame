import java.awt.Graphics;

import java.awt.image.BufferedImage;

import javax.swing.JComponent;

public class Blast extends JComponent{
	private final TRE tankGameInstance;
	private static BufferedImage image;
	private int xCoor = 0;
	private int yCoor = 0;
	
	/**
	 * 
	 * @param tre
	 * @param img
	 */
	public Blast(TRE tre, BufferedImage img) {
		this.tankGameInstance = tre;
		Blast.image = img;
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public void setXY( int x, int y) {
		this.xCoor = x;
		this.yCoor = y;
	}
	
	/**
	 * 
	 * @param g
	 */
	public void drawImage(Graphics g) {
		g.drawImage(image, this.xCoor, this.yCoor, Blast.image.getWidth(), Blast.image.getHeight(), null);
	}

	/**
	 * 
	 * @return
	 */
	public TRE getTRE() {
		return tankGameInstance;
	}
}
