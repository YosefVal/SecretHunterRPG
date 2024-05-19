package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity {
	GamePanel gp;
	public int worldX, worldY;
	public int speed;
	
	//Player and NPC sprite Direction
	public BufferedImage rightIdle, right1, right2, right3, right4;
	public BufferedImage upIdle, up1, up2, up3, up4;
	public BufferedImage downIdle, down1, down2, down3, down4;
	public BufferedImage leftIdle, left1, left2, left3, left4;
	public String direction;
	
	//Player Sprite and collision
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public Rectangle solidArea = new Rectangle (0,0,48,48);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
	
	//NPC 
	public int actionLockCounter = 0;
	
	//player status
	public int maxLife;
	public int life;
	
	/**
	 * Purpose: Gets gamePanel
	 * @param gp
	 */
	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	
	/**
	 * Purpose: Method to be overriden by different NPCs
	 */
	public void setAction() {};
	
	/**
	 * Purpose: Updates entity
	 */
	public void update() {
		setAction();
		if (collisionOn == false) {
			switch(direction) {
			case "up": worldY -= speed; break;
			case "down": worldY += speed; break;
			case "left": worldX -= speed; break;
			case "right": worldX += speed; break;
			}
		}
		
		spriteCounter++;
		if (spriteCounter > 10) {
			if (spriteNum == 1) {
				spriteNum = 2;
			}
			else if (spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkPlayer(this);
	}
	
	/**
	 * Purpose: Gets image and scales it
	 * @param imageName
	 * @return image
	 */
	public BufferedImage setup(String imageName) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imageName + ".png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		} catch (IOException e) {
			System.out.println("Error: Missing image/Scaling gone wrong (Entity line 95)");
		}
		return image;
	}

	/**
	 * Purpose: draws the entity's image
	 * @param g2
	 */
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
		   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
		   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
		   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			switch(direction) {
			case "up":
				if (spriteNum == 1) {image = up1;}
				if (spriteNum == 2) {image = up2;}
				break;
				
			case "down":
				if (spriteNum == 1) {image = down1;}
				if (spriteNum == 2) {image = down2;}
					break;
					
			case "right":
				if (spriteNum == 1) {image = right1;}
				if (spriteNum == 2) {image = right2;}
					break;
					
			case "left":	
				if (spriteNum == 1) {image = left1;}
				if (spriteNum == 2) {image = left2;}
				break;
			}
			g2.drawImage(image, screenX, screenY, null);
			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
	}	
}