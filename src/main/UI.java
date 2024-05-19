package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import object.OBJ_Heart;
import object.SuperObject;

public class UI {

	GamePanel gp;
	Graphics2D g2;
	Font arial_40, arial_80B;
	BufferedImage heart_full, heart_half, heart_blank;
	int messageCounter = 0;
	public boolean gameFinished = false;
	public int commandNum = 0;
	public int titleScreenState = 0;
	
	/**
	 * Purpose: Sets up GUI
	 * @param gp
	 */
	public UI(GamePanel gp) {
		this.gp = gp;
		arial_40 = new Font("Cambria", Font.PLAIN, 40);
		arial_80B = new Font("Arial", Font.BOLD, 80);
		heartHUD();
	}
	
	/**
	 * Purpose: Sets up HUD hearts
	 */
	public void heartHUD(){
		//Create HUD object 
		SuperObject heart = new OBJ_Heart(gp);
		heart_full = heart.image;
		heart_half = heart.image2;
		heart_blank = heart.image3;
	}
	
	/**
	 * Purpose: Draws GUI for different gamestates
	 * @param g2
	 */
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		
		g2.setFont(arial_40);
		g2.setColor(Color.WHITE);
		
		//Title state
		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		
		//Play state
		if(gp.gameState == gp.playState) {
			drawPlayerLife();
		}
		
		//Pause state
		if(gp.gameState == gp.pauseState) {
			drawPlayerLife();
			drawPauseScreen();
		}
	}
	
	/**
	 * Purpose: draws how much health player has
	 */
	public void drawPlayerLife() {
		int x = gp.tileSize/2;
		int y = gp.tileSize/2;
		int i = 0;
		
		//Draw Max hearts
		while (i < gp.player.maxLife/2) {
			g2.drawImage(heart_blank, x, y, null);
			i++;
			x += gp.tileSize;
		}
		
		//Reset
		x = gp.tileSize/2;
		y = gp.tileSize/2;
		i = 0;
		
		//Draw Current life
		while (i < gp.player.life) {
			g2.drawImage(heart_half, x, y, null);
			i++;
			if (i < gp.player.life) {
				g2.drawImage(heart_full, x, y, null);
			}
			i++;
			x += gp.tileSize;
		}
	}
	
	/**
	 * Purpose: draws title screen
	 */
	public void drawTitleScreen() {
		g2.setColor(new Color(5, 102, 8));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		//Title name
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,96f));
		String text = "Secret Hunter RPG";
		int x = getXforCenteredText(text);
		int y = gp.tileSize*3;
		
		//Shadow
		g2.setColor(Color.BLACK);
		g2.drawString(text, x+5, y+5);
		
		//Main color
		g2.setColor(Color.WHITE);
		g2.drawString(text, x, y);
		
		//Player sprite
		x = gp.screenWidth/2 - (gp.tileSize*2)/2;
		y += gp.tileSize*2;
		g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,48f));
		
		//Menu
		text = "NEW GAME";
		x = getXforCenteredText(text);
		y += gp.tileSize*4;
		g2.drawString(text, x, y);
		if (commandNum == 0) {
			g2.drawString(">", x-gp.tileSize, y);
		}
		
		text = "LOAD GAME";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if (commandNum == 1) {
			g2.drawString(">", x-gp.tileSize, y);
		}
		
		text = "QUIT GAME";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if (commandNum == 2) {
			g2.drawString(">", x-gp.tileSize, y);
		}	
	}
	
	/**
	 * Purpose: Draws pause screen
	 */
	public void drawPauseScreen() {
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80f));
		String text = "Paused";
		int x = getXforCenteredText(text);
		int y = gp.screenHeight/2;
		g2.drawString(text, x, y);
	}

	/**
	 * Purpose: sets text in the middle of screen
	 * @param text
	 * @return x
	 */
	public int getXforCenteredText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
	}
}