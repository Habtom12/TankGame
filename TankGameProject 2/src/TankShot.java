import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;

public class TankShot extends JComponent {
	private final TRE tankGameInstance;
	private int xPos;
	private int yPos;
	private final int r = 6;
	private int xVelocity;
	private int yVelocity;
	private short angle;
	private static BufferedImage img;
	private Tank tankOne;
	private Rectangle rectangularBoundary;
	private int offset = 50;
	private int shoot_x = 0;
	private int shoot_y = 0;
	private int offsetx = 0;
	private int offsety = 0;
	private String player;
	/**
	 * 
	 * @param tre
	 * @param t
	 * @param img
	 * @param x
	 * @param y
	 * @param vx
	 * @param vy
	 * @param angle
	 */
	public TankShot(TRE tre, Tank t, BufferedImage img, int x, int y, int vx, int vy, short angle) {
		this.tankGameInstance = tre;
		this.tankOne = t;
		this.player = t.getPlayer();
		TankShot.img = img;
		this.xPos = x;
		this.yPos = y + 50;
		this.xVelocity = vx;
		this.yVelocity = vy;
		this.angle = angle;
		this.rectangularBoundary = new Rectangle(x, y, TankShot.img.getWidth(), TankShot.img.getHeight());
	}
	/**
	 * 
	 * @param x
	 */
	public void setX(int x) {
		this.xPos = x;
	}
	/**
	 * 
	 * @param y
	 */
	public void setY(int y) {
		this.yPos = y;
	}
	
	/**
	 * 
	 * @param vx
	 */
	public void setVx(int vx) {
		this.xVelocity = vx;
	}
	
	/**
	 * 
	 * @param vy
	 */
	public void setVy(int vy) {
		this.yVelocity = vy;
	}
	/**
	 * 
	 * @param angle
	 */
	public void setAngle(short angle) {
		this.angle = angle;
	}
	
	/**
	 * 
	 * @param img
	 */
	public void setImage(BufferedImage img) {
		TankShot.img = img;
	}
	/**
	 * 
	 */
	public int getX() {
		return this.xPos;
	}
	/**
	 * 
	 */
	public int getY() {
		return this.yPos;
	}

	/**
	 * 
	 */
	public void update() {
		this.moveForwards();
		if (this.CollidesWithTank(this.tankGameInstance.tankOne)) {
			if (this.tankGameInstance.tankOne.getPlayer() != this.player) {
				System.out.println(" Tank 1 Hit");
				this.tankGameInstance.tankOne.setHealth(this.tankGameInstance.tankOne.getHealth() - 16);
				this.tankGameInstance.shotsFired.remove(this);
			}
		}

		if (this.CollidesWithTank(this.tankGameInstance.tankTwo)) {
			if (this.tankGameInstance.tankTwo.getPlayer() != this.player) {
				System.out.println("Tank 2 Hit");
				this.tankGameInstance.tankTwo.setHealth(this.tankGameInstance.tankTwo.getHealth() - 16);
				this.tankGameInstance.shotsFired.remove(this);
			}
		}

	}

	private void moveForwards() {
		xVelocity = (int) Math.round(r * Math.cos(Math.toRadians(angle)));
		yVelocity = (int) Math.round(r * Math.sin(Math.toRadians(angle)));
		xPos += xVelocity;
		yPos += yVelocity;
		// checkBorder();
		this.rectangularBoundary.setLocation(xPos, yPos);
	}
	
	/**
	 * 
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		AffineTransform rotation = AffineTransform.getTranslateInstance(this.xPos, this.yPos);
		rotation.rotate(Math.toRadians(angle), this.tankOne.getX() + (img.getWidth() / 2),
				this.tankOne.getY() + (img.getHeight() / 2));
		Graphics2D graphic2D = (Graphics2D) g;
		graphic2D.drawImage(img, rotation, null);

	}

	/**
	 * 
	 */
	public Rectangle getBounds() {
		return this.rectangularBoundary;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean checkBorder() {
		if (this.xPos < 0 || this.xPos >= 740 || this.yPos < 0 || this.yPos >= 720) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public String toSring() {
		return "Bullet: x=" + xPos + ", y=" + yPos + ", angle=" + angle;
	}
	
	/**
	 * 
	 * @param g
	 */
	public void drawImage(Graphics g) {

		AffineTransform rotation = AffineTransform.getTranslateInstance(xPos + offset, yPos);
		rotation.rotate(Math.toRadians(angle), img.getWidth() / 2, img.getHeight() / 2);
		Graphics2D graphic2D = (Graphics2D) g;
		graphic2D.drawImage(img, rotation, null);

	}
	
	/**
	 * 
	 * @param t1
	 * @return
	 */
	public boolean CollidesWithTank(Tank t1) {
		if (this.rectangularBoundary.intersects(t1.getBounds())) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 
	 * @return
	 */
	public int getShoot_x() {
		return shoot_x;
	}

	/**
	 * 
	 * @param shoot_x
	 */
	public void setShoot_x(int shoot_x) {
		this.shoot_x = shoot_x;
	}
	/**
	 * 
	 * @return
	 */
	public int getShoot_y() {
		return shoot_y;
	}
	/**
	 * 
	 * @param shoot_y
	 */
	public void setShoot_y(int shoot_y) {
		this.shoot_y = shoot_y;
	}

	/**
	 * 
	 * @return
	 */
	public int getOffsetx() {
		return offsetx;
	}

	public void setOffsetx(int offsetx) {
		this.offsetx = offsetx;
	}

	public int getOffsety() {
		return offsety;
	}

	public void setOffsety(int offsety) {
		this.offsety = offsety;
	}
}
