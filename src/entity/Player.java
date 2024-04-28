package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
	GamePanel gp;
	KeyHandler keyH;
	int maxHP = 10;
	public final int screenX;
	public final int screenY;
	int hasKey = 0;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		setDefaultValues();
		getPlayerImage();
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
	}
	
	public void setDefaultValues() {
		worldX= gp.tileSize * 23;
		worldY= gp.tileSize * 21;
		speed = 4;
		HP = maxHP;
	}
	
	public void getPlayerImage() {
		try {
			//up
			upIdle = ImageIO.read(getClass().getResource("/player/Player_Dwarf/U.Idle.png"));
			up1 = ImageIO.read(getClass().getResource("/player/Player_Dwarf/U.Walk1.png"));
			up2 = ImageIO.read(getClass().getResource("/player/Player_Dwarf/U.walk2.png"));
			up3 = ImageIO.read(getClass().getResource("/player/Player_Dwarf/U.walk3.png"));
			up4 = ImageIO.read(getClass().getResource("/player/Player_Dwarf/U.walk4.png"));
		
			//down
			downIdle = ImageIO.read(getClass().getResource("/player/Player_Dwarf/D.Idle.png"));
			down1 = ImageIO.read(getClass().getResource("/player/Player_Dwarf/D.walk1.png"));
			down2 = ImageIO.read(getClass().getResource("/player/Player_Dwarf/D.walk2.png"));
			down3 = ImageIO.read(getClass().getResource("/player/Player_Dwarf/D.walk3.png"));
			down4 = ImageIO.read(getClass().getResource("/player/Player_Dwarf/D.walk4.png"));
			
			//right
			rightIdle = ImageIO.read(getClass().getResource("/player/Player_Dwarf/Idle.png"));
			right1 = ImageIO.read(getClass().getResource("/player/Player_Dwarf/Walk1.png"));
			right2 = ImageIO.read(getClass().getResource("/player/Player_Dwarf/Walk2.png"));
			right3 = ImageIO.read(getClass().getResource("/player/Player_Dwarf/Walk3.png"));
			right4 = ImageIO.read(getClass().getResource("/player/Player_Dwarf/Walk4.png"));
			
			//left
			leftIdle = ImageIO.read(getClass().getResource("/player/Player_Dwarf/L.Idle.png"));
			left1 = ImageIO.read(getClass().getResource("/player/Player_Dwarf/L.Walk1.png"));
			left2 = ImageIO.read(getClass().getResource("/player/Player_Dwarf/L.walk2.png"));
			left3 = ImageIO.read(getClass().getResource("/player/Player_Dwarf/L.walk3.png"));
			left4 = ImageIO.read(getClass().getResource("/player/Player_Dwarf/L.walk4.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true 
				|| keyH.rightPressed == true) {
			if (keyH.upPressed == true) {
				direction = "up";
			}
			if (keyH.downPressed == true) {
				direction = "down";
			}
			if (keyH.leftPressed == true) {
				direction = "left";
			}
			if (keyH.rightPressed == true) {
				direction = "right";
			}
			
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			
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
					spriteNum = 3;
				}
				else if (spriteNum == 3) {
					spriteNum = 4;
				}
				else if (spriteNum == 4) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
	}
	
	public void pickUpObject(int i) {
		if (i != 999) {
			String objectName = gp.obj[i].name;
			switch(objectName) {
			case "Key":
				hasKey++;
				gp.obj[i] = null;
				break;
			case "Door":
				if (hasKey > 0) {
					gp.obj[i] = null;
					hasKey--;
				}
				break;
			}
			
		}
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		switch(direction) {
		case "up":
			if (spriteNum == 1) {image = up1;}
			if (spriteNum == 2) {image = up2;}
			if (spriteNum == 3) {image = up3;}
			if (spriteNum == 4) {image = up4;}
			break;
			
		case "down":
			if (spriteNum == 1) {image = down1;}
			if (spriteNum == 2) {image = down2;}
			if (spriteNum == 3) {image = down3;}
			if (spriteNum == 4) {image = down4;}
				break;
				
		case "right":
			if (spriteNum == 1) {image = right1;}
			if (spriteNum == 2) {image = right2;}
			if (spriteNum == 3) {image = right3;}
			if (spriteNum == 4) {image = right4;}
				break;
				
		case "left":	
			if (spriteNum == 1) {image = left1;}
			if (spriteNum == 2) {image = left2;}
			if (spriteNum == 3) {image = left3;}
			if (spriteNum == 4) {image = left4;}
			break;
		}
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
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
