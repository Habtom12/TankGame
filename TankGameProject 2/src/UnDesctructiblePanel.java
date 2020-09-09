import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

public class UnDesctructiblePanel extends JComponent {
	
	private final TRE tankGameInstance;
	private static BufferedImage image;
	private int xCoor;
	private int yCoor;
	private Rectangle rectangleBoundary;
	
	/**
	 * 
	 * @param tre
	 * @param img
	 * @param x
	 * @param y
	 */
	public UnDesctructiblePanel(TRE tre,BufferedImage img, int x, int y ) {
		this.tankGameInstance = tre;
		UnDesctructiblePanel.image = img;
		this.xCoor = x;
		this.yCoor = y;
		this.rectangleBoundary = new Rectangle(this.xCoor, this.yCoor, UnDesctructiblePanel.image.getWidth(), UnDesctructiblePanel.image.getHeight() );
	}
	/**
	 * 
	 */
	public Rectangle getBounds() {
		return this.rectangleBoundary;
	}

	/**
	 * 
	 * @param g
	 */
	public void drawImage(Graphics g) {
		g.drawImage(image, this.xCoor, this.yCoor, UnDesctructiblePanel.image.getWidth(), UnDesctructiblePanel.image.getHeight(), null);
	}
	
	/**
	 * 
	 */
	public void next() {
		this.collisionShot();
		this.collisionTankOne();
		this.collisionTankTwo();
		this.collisionTankOne();
		this.collisionTankTwo();
		this.collisionTankOne();
		this.collisionTankTwo();
	}
	
	/**
	 * 
	 */
	public void collisionTankOne() {
		if(this.rectangleBoundary.intersects(this.tankGameInstance.tankOne.getBounds())) {
			if(this.tankGameInstance.tankOne.getX() > this.xCoor) {
				this.tankGameInstance.tankOne.setX(this.tankGameInstance.tankOne.getX() + 3);
				System.out.println("Tank 1 Collision");
				this.tankGameInstance.tankOne.getBounds().setLocation(this.tankGameInstance.tankOne.getX(), this.tankGameInstance.tankOne.getY());
			} 
			else {
				this.tankGameInstance.tankOne.setX(this.tankGameInstance.tankOne.getX() - 3);
				System.out.println("Tank 1 Collision");
				this.tankGameInstance.tankOne.getBounds().setLocation(this.tankGameInstance.tankOne.getX(), this.tankGameInstance.tankOne.getY());
			}
			if(this.tankGameInstance.tankOne.getY() > this.yCoor) {
				this.tankGameInstance.tankOne.setY(this.tankGameInstance.tankOne.getY() + 3);
				System.out.println("Tank 1 Collision");
				this.tankGameInstance.tankOne.getBounds().setLocation(this.tankGameInstance.tankOne.getX(), this.tankGameInstance.tankOne.getY());
			} 
			else {
				this.tankGameInstance.tankOne.setY(this.tankGameInstance.tankOne.getY() - 3);
				System.out.println("Tank 1 Collision");
				this.tankGameInstance.tankOne.getBounds().setLocation(this.tankGameInstance.tankOne.getX(), this.tankGameInstance.tankOne.getY());
			}
		} 
	}
	
	/**
	 * 
	 */
	public void collisionTankTwo() {
		if(this.rectangleBoundary.intersects(this.tankGameInstance.tankTwo.getBounds())) {
			if(this.tankGameInstance.tankTwo.getX() > this.xCoor) {
				this.tankGameInstance.tankTwo.setX(this.tankGameInstance.tankTwo.getX() + 3);
				System.out.println("Tank 2 Collision");
				this.tankGameInstance.tankTwo.getBounds().setLocation(this.tankGameInstance.tankTwo.getX(), this.tankGameInstance.tankTwo.getY());
			} 
			else {
				this.tankGameInstance.tankTwo.setX(this.tankGameInstance.tankTwo.getX() - 3);
				System.out.println("Tank 2 Collision");
				this.tankGameInstance.tankTwo.getBounds().setLocation(this.tankGameInstance.tankTwo.getX(), this.tankGameInstance.tankTwo.getY());
			}
			if(this.tankGameInstance.tankTwo.getY() > this.yCoor) {
				this.tankGameInstance.tankTwo.setY(this.tankGameInstance.tankTwo.getY() + 3);
				this.tankGameInstance.tankTwo.getBounds().setLocation(this.tankGameInstance.tankTwo.getX(), this.tankGameInstance.tankTwo.getY());
			} 
			else {
				this.tankGameInstance.tankTwo.setY(this.tankGameInstance.tankTwo.getY() - 3);
				System.out.println("Tank 2 Collision");
				this.tankGameInstance.tankTwo.getBounds().setLocation(this.tankGameInstance.tankTwo.getX(), this.tankGameInstance.tankTwo.getY());
			}
		} 
	}
	/**
	 * 
	 */
	public void collisionShot() {
		if (this.tankGameInstance.shot != null) {
			if (this.tankGameInstance.shot.getBounds().intersects(this.rectangleBoundary)) {
				this.tankGameInstance.shotsFired.remove(this.tankGameInstance.shot);
			}
		}
	}
}
