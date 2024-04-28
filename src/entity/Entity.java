package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
	public int worldX, worldY;
	public int speed;
	public int HP;
	
	public BufferedImage rightIdle, right1, right2, right3, right4;
	public BufferedImage upIdle, up1, up2, up3, up4;
	public BufferedImage downIdle, down1, down2, down3, down4;
	public BufferedImage leftIdle, left1, left2, left3, left4;
	
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public Rectangle solidArea;
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
}