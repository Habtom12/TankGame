
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;

public class Tank extends JComponent implements Observer {
	
	private final TRE tankGameInstance;
    private int xPos;
    private int yPos;
    private final int r = 6;
    private int xVelocity;
    private int yVelocity;
    private int xAim = 0; 
    private int yAim = 0;
    private short angle;
    private static BufferedImage img;
    private boolean isUpPressed;
    private boolean isDownPressed;
    private boolean isRightPressed;
    private boolean isLeftPressed;
    private boolean isShootPressed;
    private int totalHitPoints = 80;
    private int totalLifes = 3;
    private int xPosLeft = 0;
    private int yPosLeft = 0;
    private int xPosRight = 0;
    private int yPosRight = 0;
    private String pName;
    private Rectangle rectangleBoundary;

    /**
     * 
     * @param tre
     * @param player
     * @param x
     * @param y
     * @param vx
     * @param vy
     * @param angle
     */
    public Tank(TRE tre, String player, int x, int y, int vx, int vy, short angle) {
        this.tankGameInstance = tre;
        this.pName = player;
    	this.xPos = x;
        this.yPos = y;
        this.xVelocity = vx;
        this.yVelocity = vy;
        this.angle = angle;
    }
    
    /**
     * 
     * @return
     */
    public String getPlayer() {
    	return this.pName;
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
     * @return
     */
    public BufferedImage getImg() {
    	return Tank.img;
    }
    /**
     * 
     * @param img
     */
    public void setImg(BufferedImage img) {
        Tank.img = img;
        this.setBounds();
    }
    
    /**
     * 
     */
    public void setBounds() {
    	this.rectangleBoundary = new Rectangle(xPos, yPos, Tank.img.getWidth(), Tank.img.getWidth());
    }
    
    /**
     * 
     * @return
     */
    public String printBounds() {
    	return "Bounds: " + this.rectangleBoundary;
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
     * @return
     */
    public int getVX() {
    	return this.xVelocity;
    }
    
    /**
     * 
     * @return
     */
    public int getVY() {
    	return this.yVelocity;
    }
    
    /**
     * 
     * @return
     */
    public int getTileX() {
    	return Tank.img.getWidth();
    }
    
    /**
     * 
     * @return
     */
    public int getTileY() {
    	return Tank.img.getHeight();
    }
    
    /**
     * 
     * @return
     */
    public String printImgXY() {
    	return "x: " + Tank.img.getWidth() + " y: " + Tank.img.getHeight();
    }
    
    /**
     * 
     * @return
     */
    public int getHealth() {
    	return this.totalHitPoints;
    }
    
    /**
     * 
     * @param h
     */
    public void setHealth(int h) {
    	this.totalHitPoints = h;
    }
    
    /**
     * 
     * @return
     */
    public int getLives() {
    	return this.totalLifes;
    }
    
    /**
     * 
     * @param l
     */
    public void setLives(int l) {
    	this.totalLifes = l;
    }
    
    /**
     * 
     * @return
     */
    public int getShootX() {
    	return this.xAim;
    }
    
    /**
     * 
     * @return
     */
    public int getShootY() {
    	return this.yAim;
    }
        
    public void toggleUpPressed() {
        this.isUpPressed = true;
    }

    public void toggleDownPressed() {
        this.isDownPressed = true;
    }

    public void toggleRightPressed() {
        this.isRightPressed = true;
    }

    public void toggleLeftPressed() {
        this.isLeftPressed = true;
    }
    
    /**
     * 
     */
    public void toggleFirePressed() {
    	this.isShootPressed = true;
    }

    /**
     * 
     */
    public void unToggleUpPressed() {
        this.isUpPressed = false;
    }

    /**
     * 
     */
    public void unToggleDownPressed() {
        this.isDownPressed = false;
    }

    /**
     * 
     */
    public void unToggleRightPressed() {
        this.isRightPressed = false;
    }

    /**
     * 
     */
    public void unToggleLeftPressed() {
        this.isLeftPressed = false;
    }
    
    /**
     * 
     */
    public void unToggleFirePressed() {
    	this.isShootPressed = false;
    }
    /**
     * 
     */
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        
        AffineTransform rot = AffineTransform.getTranslateInstance(xPos, yPos);
        rot.rotate(Math.toRadians(angle), img.getWidth() / 2, img.getHeight() / 2);
        Graphics2D graph2D = (Graphics2D) g;
        graph2D.drawImage(img, rot, null);
        
        int cXPos = img.getWidth() / 2; 
        int cYPos = img.getHeight() / 2;
        int cImgVX = (int) Math.round(43 * Math.cos(Math.toRadians(angle)));
        int cImgVY = (int) Math.round(43 * Math.sin(Math.toRadians(angle))); 
        
        this.xAim = cXPos + cImgVX;
        this.yAim = cYPos + cImgVY;
        
    }
    /**
     * 
     */
    public void update(Observable o, Object o1) {
        if (this.isUpPressed) {
            this.moveForwards();
        }
        if (this.isDownPressed) {
            this.moveBackwards();
        }

        if (this.isLeftPressed) {
            this.rotateLeft();
        }
        if (this.isRightPressed) {
            this.rotateRight();
        }
        if (this.isShootPressed) {
        	this.addBullet();
        }
        this.Center();
        this.repaint();
    }
    @Override
    public String toString() {
        return "x=" + xPos + ", y=" + yPos + ", angle=" + angle;
    }
    /**
     * 
     */
    public void addBullet() {
    	this.tankGameInstance.loadBullet(this, this.xPos, this.yPos, this.xVelocity, this.yVelocity, this.angle);
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
		
		AffineTransform rotation = AffineTransform.getTranslateInstance(xPos, yPos);
        rotation.rotate(Math.toRadians(angle), img.getWidth() / 2, img.getHeight() / 2);
        Graphics2D graphic2D = (Graphics2D) g;
        graphic2D.drawImage(img, rotation, null);
        
        int cImgX = img.getWidth() / 2; 
        int cImgY = img.getHeight() / 2;
        int cImgVX = (int) Math.round(43 * Math.cos(Math.toRadians(angle)));
        int cImgVY = (int) Math.round(43 * Math.sin(Math.toRadians(angle))); 
        
        this.xAim = cImgX + cImgVX;
        this.yAim = cImgY + cImgVY;
       
	}
	
	/**
	 * 
	 */
	public void Center() {
		if(this.tankGameInstance.tankOne.getX() - TRE.FRAMEWIDTH / 4 > 0 ) {
			xPosLeft = this.tankGameInstance.tankOne.getX() - TRE.FRAMEWIDTH / 4;
		} else {
			xPosLeft = 0;
		}
		if(this.tankGameInstance.tankOne.getY() - TRE.FRAMEHEIGHT > 0 ) {
			yPosLeft = this.tankGameInstance.tankOne.getY() - (TRE.GAMEHEIGHT - TRE.FRAMEHEIGHT);
		} else {
			yPosLeft = 0;
		}
		if( yPosLeft > TRE.GAMEHEIGHT - TRE.FRAMEHEIGHT ) {
			yPosLeft = TRE.GAMEHEIGHT - TRE.FRAMEHEIGHT;
		}
		if( xPosLeft > TRE.GAMEWIDTH - TRE.FRAMEWIDTH / 2 ) {
			xPosLeft = TRE.GAMEWIDTH - TRE.FRAMEWIDTH / 2;
		}	
		
		if(this.tankGameInstance.tankTwo.getX() - TRE.FRAMEWIDTH / 4 > 0 ) {
			xPosRight = this.tankGameInstance.tankTwo.getX() - TRE.FRAMEWIDTH / 4;
		} else {
			xPosRight = 0;
		}
		if(this.tankGameInstance.tankTwo.getY() - TRE.FRAMEHEIGHT > 0 ) {
			yPosRight = this.tankGameInstance.tankTwo.getY() - (TRE.GAMEHEIGHT - TRE.FRAMEHEIGHT);
		} else {
			yPosRight = 0;
		}
		if( yPosRight > TRE.GAMEHEIGHT - TRE.FRAMEHEIGHT ) {
			yPosRight = TRE.GAMEHEIGHT - TRE.FRAMEHEIGHT;
		}
		if( xPosRight > TRE.GAMEWIDTH - TRE.FRAMEWIDTH / 2 ) {
			xPosRight = TRE.GAMEWIDTH - TRE.FRAMEWIDTH / 2;
		}	
		
	
	}
	/**
	 * 
	 * @return
	 */
	public int getRight_x() {
		return this.xPosRight;
	}
	/**
	 * 
	 * @return
	 */
	public int getRight_y() {
		return this.yPosRight;
	}
	/**
	 * 
	 * @return
	 */
	public int getLeft_x() {
		return this.xPosLeft; 
	}
	
	/**
	 * 
	 * @return
	 */
	public int getLeft_y() {
		return this.yPosLeft;
	}
	/**
	 * 
	 */
    private void rotateLeft() {
        this.angle -= 3;
    }
    
    /**
     * 
     */
    private void rotateRight() {
        this.angle += 3;
    }
    /**
     * 
     */
    private void moveBackwards() {
        xVelocity = (int) Math.round(r * Math.cos(Math.toRadians(angle)));
        yVelocity = (int) Math.round(r * Math.sin(Math.toRadians(angle)));
        xPos -= xVelocity;
        yPos -= yVelocity;
        checkBorder();
        this.rectangleBoundary.setLocation(xPos,yPos);
    }
    /**
     * 
     */
    private void moveForwards() {
        xVelocity = (int) Math.round(r * Math.cos(Math.toRadians(angle)));
        yVelocity = (int) Math.round(r * Math.sin(Math.toRadians(angle)));
        xPos += xVelocity;
        yPos += yVelocity;
        checkBorder();
        this.rectangleBoundary.setLocation(xPos,yPos);
    }
    /**
     * 
     */
    private void checkBorder() {
        if (xPos < 0) {
            xPos = 0;
        }
        if (xPos >= 1910) {
            xPos = 1910;
        }
        if (yPos < 0) {
            yPos = 0;
        }
        if (yPos >= 1350) {
            yPos = 1350;
        }
    }

    
}
