package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity{
	GamePanel gp;
	KeyHandler keyH;
	public final int screenX;
	public final int screenY;
	int hasMainKey = 0;
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
		direction = "right";
		speed = 8;
		
		//Player status
		maxLife = 10;
		life = maxLife;
	}
	
	public void getPlayerImage() {
			//up
			up1 = setup("U.Walk1");
			up2 = setup("U.walk2");
			up3 = setup("U.walk3");
			up4 = setup("U.walk4");
		
			//down
			down1 = setup("D.walk1");
			down2 = setup("D.walk2");
			down3 = setup("D.walk3");
			down4 = setup("D.walk4");
			
			//right
			right1 = setup("Walk1");
			right2 = setup("Walk2");
			right3 = setup("Walk3");
			right4 = setup("Walk4");
			
			//left
			left1 = setup("L.Walk1");
			left2 = setup("L.walk2");
			left3 = setup("L.walk3");
			left4 = setup("L.walk4");
	}
	
	public BufferedImage setup(String imageName) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/player/Player_Dwarf/" + imageName + ".png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
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
		g2.drawImage(image, screenX, screenY, null);
	}
	
	public void lowerHP() {
		life -= 1;
	}
	
	public void heal() {
		life += 1;
		if (life > maxLife) {
			life = maxLife;
		}
	}
}
