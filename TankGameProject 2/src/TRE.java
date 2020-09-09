/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TRE extends JPanel {

	
	public static final int GAMEWIDTH = 1920;
	public static final int GAMEHEIGHT = 1440;
	public static final int FRAMEWIDTH = 1280;
	public static final int FRAMEHEIGHT = 960;
	private int[][] gameLevel;

	BufferedImage world;
	Graphics2D buffer;
	BufferedImage bimg;
	BufferedImage shotImg;
	BufferedImage tileImg;
	BufferedImage breakableWallImg;
	BufferedImage unBreakableWallImg;
	BufferedImage healthImg;
	BufferedImage explosionImg;
	BufferedImage titleImg;
	String soundName;
	JFrame frame;
	Tank tankOne;
	Tank tankTwo;
	ArrayList<TankShot> shotsFired;
	TankShot shot;
	TankGameTile floorField;
	TankGameMap level;
	DescructiblePanel breakableWall;
	UnDesctructiblePanel unbreakableWall;
	ArrayList<DescructiblePanel> breakableWallList;
	ArrayList<UnDesctructiblePanel> unbreakableWallList;
	TankGameUtility lifePanel;
	GainLife powerUp;
	ArrayList<GainLife> powerUpList;
	Blast blast;
	private final TankGameObservable observer;
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		//Thread newThread;
		TRE tankRE = new TRE();
		tankRE.init();
		
		try {
			while (true) {
				tankRE.observer.setChanged();
				tankRE.observer.notifyObservers();
				tankRE.repaint();
				Thread.sleep(1000 / 144);
			}
		} catch (InterruptedException ex) {
			Logger.getLogger(TRE.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
	/**
	 * 
	 */
	public TRE() {
		this.observer = new TankGameObservable();
	}
	
	/**
	 * 
	 */
	private void init() {
		powerUpList = new ArrayList<GainLife>();
		breakableWallList = new ArrayList<DescructiblePanel>();
		unbreakableWallList = new ArrayList<UnDesctructiblePanel>();
		shotsFired = new ArrayList<TankShot>();
		level = new TankGameMap();
		gameLevel = TankGameMap.getMap_level().clone();
		this.frame = new JFrame("Tank Rotation");
		tankOne = new Tank(this, "Player 1", 330, 330, 0, 0, (short) 45);
		tankTwo = new Tank(this, "Player 2", 1570, 1030, 0, 0, (short) 225);
		floorField = new TankGameTile();
		try {
			world = new BufferedImage(GAMEWIDTH, GAMEHEIGHT, BufferedImage.TYPE_3BYTE_BGR); 
																								
			BufferedImage tankOneImg = ImageIO.read(new File("./Resources/Tank1.gif"));
			BufferedImage i2 = ImageIO.read(new File("./Resources/Tank2.gif"));
			shotImg = ImageIO.read(new File("./Resources/Rocket.gif"));
			tileImg = ImageIO.read(new File("./Resources/Background.bmp"));
			breakableWallImg = ImageIO.read(new File("./Resources/Wall1.gif"));
			unBreakableWallImg = ImageIO.read(new File("./Resources/Wall2.gif"));
			healthImg = ImageIO.read(new File("./Resources/Shield2.gif"));
			explosionImg = ImageIO.read(new File("./Resources/Explosion_large.gif"));
			titleImg = ImageIO.read(new File("./Resources/Title.bmp"));
			soundName= "./Resources/Music.mid";
			tankOne.setImg(tankOneImg);
			tankTwo.setImg(i2);
			floorField.setImg(tileImg);

			for (int x = 0; x < gameLevel.length; x++) {
				for (int y = 0; y < gameLevel[x].length; y++) {
					if (gameLevel[x][y] == 2) {
						breakableWallList.add(new DescructiblePanel(this, breakableWallImg, x * this.breakableWallImg.getWidth(),
								y * this.breakableWallImg.getHeight()));
					} else if (gameLevel[x][y] == 3) {
						unbreakableWallList.add(new UnDesctructiblePanel(this, unBreakableWallImg, x * this.unBreakableWallImg.getWidth(),
								y * this.unBreakableWallImg.getHeight()));
					} else if (gameLevel[x][y] == 4) { 
						powerUpList.add(new GainLife(this, healthImg, x * (this.healthImg.getWidth() / 2),
								y * (this.healthImg.getHeight() / 2)));
					}
				}
			}

		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
		TankControl tc1 = new TankControl(tankTwo, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT,
				KeyEvent.VK_ENTER);
		TankControl tc2 = new TankControl(tankOne, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D,
				KeyEvent.VK_SPACE);
		lifePanel = new TankGameUtility(this);
		blast = new Blast(this, explosionImg);

		this.frame.add(this);

		this.frame.addKeyListener(tc1);
		this.frame.addKeyListener(tc2);

		this.observer.addObserver(tankOne);
		this.observer.addObserver(tankTwo);

		this.frame.setSize(TRE.FRAMEWIDTH, TRE.FRAMEHEIGHT);
		this.frame.setResizable(false);
		this.frame.setLocationRelativeTo(null);
		this.frame.setIconImage(titleImg);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setVisible(true);
		try {
	          Clip clip = AudioSystem.getClip();
	          AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
	          clip.open(inputStream);
	          clip.start(); 
	        } catch (Exception e) {
	          System.err.println(e.getMessage());
	        }
	      
	  }
	
	/**
	 * 
	 * @param t
	 * @param x
	 * @param y
	 * @param vx
	 * @param vy
	 * @param angle
	 */
	public void loadBullet(Tank t, int x, int y, int vx, int vy, short angle) {
		this.shotsFired.add(new TankShot(this, t, shotImg, x, y, vx, vy, angle));
	}
	/**
	 * 
	 */
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		buffer = world.createGraphics();
		super.paintComponent(g2);
		for (int i = 0; i <= ((int) (TRE.GAMEHEIGHT / this.tileImg.getHeight())); i++) {
			for (int j = 0; j <= ((int) (TRE.GAMEWIDTH / this.tileImg.getWidth())); j++) {
				this.floorField.setXY((j * this.tileImg.getWidth()), (i * this.tileImg.getHeight()));
				this.floorField.drawImage(buffer);
			}
		}

		this.tankOne.drawImage(buffer);
		this.tankTwo.drawImage(buffer);

		for (int i = 0; i < this.shotsFired.size(); i++) {
			this.shot = this.shotsFired.get(i);

			this.shot.update();
			this.shot.drawImage(buffer);
		}

		for (int z = 0; z < this.breakableWallList.size(); z++) {
			this.breakableWall = this.breakableWallList.get(z);
			this.breakableWallList.get(z).drawImage(buffer);
			this.breakableWall.next();
		}

		for (int y = 0; y < this.unbreakableWallList.size(); y++) {
			this.unbreakableWall = this.unbreakableWallList.get(y);
			this.unbreakableWallList.get(y).drawImage(buffer);
			this.unbreakableWall.next();
		}

		for (int x = 0; x < this.powerUpList.size(); x++) {
			this.powerUp = this.powerUpList.get(x);
			this.powerUpList.get(x).drawImage(buffer);
			this.powerUp.update();
		}

		BufferedImage lefthalf = world.getSubimage(this.tankOne.getLeft_x(), this.tankOne.getLeft_y(), FRAMEWIDTH / 2,
				FRAMEHEIGHT);
		g2.drawImage(lefthalf, 0, 0, null);
		BufferedImage righthalf = world.getSubimage(this.tankTwo.getRight_x(), this.tankTwo.getRight_y(), FRAMEWIDTH / 2,
				FRAMEHEIGHT);
		g2.drawImage(righthalf, FRAMEWIDTH / 2, 0, null);

		lifePanel.drawImage(g2);

		BufferedImage miniMap = world.getSubimage(0, 0, GAMEWIDTH, GAMEHEIGHT);
		g2.scale(0.2, 0.2);
		g2.drawImage(miniMap, 2200, 3200, null);

	}

}
