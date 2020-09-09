import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

public class GainLife extends JComponent {
	
	private final TRE tankGameInstance;
	private static BufferedImage image;
	private int xCoor;
	private int yCoor;
	private Rectangle rectangleBoundary;
	private int time = 20;
	
	/**
	 * 
	 * @param tre
	 * @param _img
	 * @param x
	 * @param y
	 */
	public GainLife(TRE tre, BufferedImage _img, int x, int y) {
		this.tankGameInstance = tre;
		GainLife.image = _img;
		this.xCoor = x;
		this.yCoor = y;
		this.rectangleBoundary = new Rectangle(x, y, GainLife.image.getWidth(), GainLife.image.getHeight());
	}
	
	/**
	 * 
	 */
	public Rectangle getBounds() {
		return this.rectangleBoundary;
	}
	
	/**
	 * 
	 */
	public void update() {
		this.CollisionWithTanks();
	}
	
	/**
	 * 
	 */
	public void CollisionWithTanks() {
			if(this.rectangleBoundary.intersects(this.tankGameInstance.tankOne.getBounds())) {
				this.tankGameInstance.powerUpList.remove(this.tankGameInstance.powerUp);
				this.tankGameInstance.tankOne.setLives(this.tankGameInstance.tankOne.getLives() + 1);
				System.out.println("Tank1 picked up PowerUp");
			}
			if(this.rectangleBoundary.intersects(this.tankGameInstance.tankTwo.getBounds())) {
				this.tankGameInstance.powerUpList.remove(this.tankGameInstance.powerUp);
				this.tankGameInstance.tankTwo.setLives(this.tankGameInstance.tankTwo.getLives() + 1);
				System.out.println("Tank2 picked up PowerUp");
			}
	}
	/**
	 * 
	 * @param g
	 */
	public void drawImage(Graphics g) {
		g.drawImage(image, this.xCoor, this.yCoor, GainLife.image.getWidth(), GainLife.image.getHeight(), null);
	}
	/**
	 * 
	 * @param time
	 */
	public void UpdateTime(int time) {
		this.setTime(this.getTime() - time);
	}

	/**
	 * 
	 * @return
	 */
	public int getTime() {
		return time;
	}

	/**
	 * 
	 * @param time
	 */
	public void setTime(int time) {
		this.time = time;
	}
}
