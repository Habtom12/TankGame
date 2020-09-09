import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class TankGameUtility extends JComponent {
	private final TRE tankGameInstance;
	/**
	 * 
	 * @param tre
	 */
	public TankGameUtility(TRE tre) {
		this.tankGameInstance = tre;
	}
	/**
	 * 
	 * @param g
	 */
	public void drawImage(Graphics g) {
		g.setColor(Color.GREEN);

		if (this.tankGameInstance.tankOne.getHealth() < 48) {
			g.setColor(Color.YELLOW);
		} else if (this.tankGameInstance.tankOne.getHealth() < 32) {
			g.setColor(Color.ORANGE);
		} else if (this.tankGameInstance.tankOne.getHealth() < 16) {
			g.setColor(Color.RED);
		}

		g.fillRect(50, 35, this.tankGameInstance.tankOne.getHealth(), 35);
		g.drawRect(50, 35, 80, 35);

		int space = 30;
		int xHealthOffsetT1 = 20;
		g.setColor(Color.RED);
		for (int i = 0; i < this.tankGameInstance.tankOne.getLives(); i++) {
			g.fillOval(xHealthOffsetT1 + space, 80, 20, 20);
			g.drawOval(xHealthOffsetT1 + space, 80, 20, 20);
			xHealthOffsetT1 += space;
		}

		if (this.tankGameInstance.tankOne.getHealth() <= 0 && this.tankGameInstance.tankOne.getLives() != 0) {
			this.tankGameInstance.tankOne.setHealth(80);
			this.tankGameInstance.tankOne.setLives(this.tankGameInstance.tankOne.getLives() - 1);
		} else if (this.tankGameInstance.tankOne.getHealth() <= 0 && this.tankGameInstance.tankOne.getLives() == 0) {
			this.tankGameInstance.tankOne.setHealth(0);
			g.setColor(Color.WHITE);
			g.setFont(new Font("TimesRoman", Font.BOLD, 90));
			g.drawString("GAME OVER", 50, TRE.FRAMEHEIGHT / 4);
			g.drawString("Player 2 WINS", TRE.FRAMEWIDTH / 2 + 50, TRE.FRAMEHEIGHT / 4);
			this.tankGameInstance.blast.setXY(this.tankGameInstance.tankOne.getX(), this.tankGameInstance.tankOne.getY());
			this.tankGameInstance.blast.drawImage(g);
		}

		g.setColor(Color.GREEN);

		if (this.tankGameInstance.tankTwo.getHealth() < 48) {
			g.setColor(Color.YELLOW);
		} else if (this.tankGameInstance.tankTwo.getHealth() < 32) {
			g.setColor(Color.ORANGE);
		} else if (this.tankGameInstance.tankTwo.getHealth() < 16) {
			g.setColor(Color.RED);
		}

		int xHealthOffSetT2 = 20;
		g.fillRect(TRE.FRAMEWIDTH / 2 + 500, 35, this.tankGameInstance.tankTwo.getHealth(), 35);
		g.drawRect(TRE.FRAMEWIDTH / 2 + 500, 35, 80, 35);
		g.setColor(Color.BLUE);
		for (int i = 0; i < this.tankGameInstance.tankTwo.getLives(); i++) {
			g.fillOval(TRE.FRAMEWIDTH / 2 + xHealthOffSetT2 + space + 450, 80, 20, 20);
			g.drawOval(TRE.FRAMEWIDTH / 2 + xHealthOffSetT2 + space + 450, 80, 20, 20);
			xHealthOffSetT2 += space;
		}

		if (this.tankGameInstance.tankTwo.getHealth() <= 0 && this.tankGameInstance.tankTwo.getLives() != 0) {
			this.tankGameInstance.tankTwo.setHealth(80);
			this.tankGameInstance.tankTwo.setLives(this.tankGameInstance.tankTwo.getLives() - 1);
		} else if (this.tankGameInstance.tankTwo.getHealth() <= 0 && this.tankGameInstance.tankTwo.getLives() == 0) {
			this.tankGameInstance.tankTwo.setHealth(0);
			g.setColor(Color.WHITE);
			g.setFont(new Font("TimesRoman", Font.BOLD, 90));
			g.drawString("GAME OVER", TRE.FRAMEWIDTH / 2 + 30, TRE.FRAMEHEIGHT / 4);
			g.drawString("Player 1 WINS", 20, TRE.FRAMEHEIGHT / 4);
			this.tankGameInstance.blast.setXY(this.tankGameInstance.tankTwo.getX(), this.tankGameInstance.tankTwo.getY());
			this.tankGameInstance.blast.drawImage(g);
		}
	}
}
