package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
	GamePanel gp;
	KeyHandler keyH;
	int maxHP = 10;
	public final int screenX;
	public final int screenY;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		setDefaultValues();
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
	}
	
	public void setDefaultValues() {
		worldX= gp.tileSize * 23;
		worldY= gp.tileSize * 21;
		speed = 4;
		HP = maxHP;
	}
	
	public void update() {
		if (keyH.upPressed == true) {
			worldY-= speed;
		}
		if (keyH.downPressed == true) {
			worldY+= speed;
		}
		if (keyH.leftPressed == true) {
			worldX-= speed;
		}
		if (keyH.rightPressed == true) {
			worldX+= speed;
		}
	}
	
	public void draw(Graphics2D g2) {
		g2.setColor(Color.red);
		
		g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
	}
	
	public void lowerHP() {
		HP -= 1;
	}
	
	public void heal() {
		HP += 1;
		if (HP > maxHP) {
			HP = maxHP;
		}
	}
}
