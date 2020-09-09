import java.awt.Graphics;

import java.awt.image.BufferedImage;

import javax.swing.JComponent;

public class TankGameTile extends JComponent {
	private static BufferedImage img;
	private int x;
	private int y;
	/**
	 * 
	 */
	public TankGameTile() {

	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * 
	 * @param img
	 */
	public void setImg(BufferedImage img) {
		TankGameTile.img = img;
	}

	/**
	 * 
	 * @param g
	 */
	public void drawImage(Graphics g) {
		g.drawImage(img, this.x, this.y, TankGameTile.img.getWidth(), TankGameTile.img.getHeight(), null);
	}
}
