package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
	
	KeyHandler keyH;
	public final int screenX;
	public final int screenY;
	int hasMainKey = 0;
	int hasKey = 0;
	int standCounter = 0;
	
	/**
	 * Purpose: Gets Entity constructor and necessary values
	 * @param gp
	 * @param keyH
	 */
	public Player(GamePanel gp, KeyHandler keyH) {
		super(gp);
		this.keyH = keyH;
		setDefaultValues();
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		setSolidArea();
		getPlayerImage();
	}
	
	/**
	 * Purpose: Sets collision hitbox for player
	 */
	public void setSolidArea() {
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
	}
	
	/**
	 * Purpose: sets players default values
	 */
	public void setDefaultValues() {
		worldX= gp.tileSize * 23;
		worldY= gp.tileSize * 21;
		direction = "right";
		speed = 8;
		
		//Player status
		maxLife = 10;
		life = maxLife;
	}
	
	/**
	 * Purpose: Gets images for player entity
	 */
	public void getPlayerImage() {
		//up
		up1 = setup("/playerSprite/boy_up_1");
		up2 = setup("/playerSprite/boy_up_2");
		
		//down
		down1 = setup("/playerSprite/boy_down_1");
		down2 = setup("/playerSprite/boy_down_2");
			
		//right
		right1 = setup("/playerSprite/boy_right_1");
		right2 = setup("/playerSprite/boy_right_2");
			
		//left
		left1 = setup("/playerSprite/boy_left_1");
		left2 = setup("/playerSprite/boy_left_2");
	}
	
	/**
	 * Purpose: Updates player direction and collision, and image 
	 */
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
			
			gp.cChecker.checkEntity(this, gp.npc);
			
			updateCollision();
			updateSpriteCounter();
		}
	}
	
	/**
	 * Purpose: Updates collision
	 */
	public void updateCollision() {
		if (collisionOn == false) {
			switch(direction) {
			case "up": worldY -= speed; break;
			case "down": worldY += speed; break;
			case "left": worldX -= speed; break;
			case "right": worldX += speed; break;
			}
		}
	}
	
	/**
	 * Purpose: updates player image number
	 */
	public void updateSpriteCounter() {
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
	}
	
	/**
	 * Purpose: Picks up object if player interacts with it
	 * @param i
	 */
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
	
	/**
	 * Purpose: Draws player image depending of image number
	 */
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
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
	}
	
	/**
	 * Purpose: Lowers player HP by 1
	 */
	public void lowerHP() {
		life -= 1;
	}
	
	/**
	 * Increases Player HP by 1 unless player is already at max hp
	 */
	public void heal() {
		life += 1;
		if (life > maxLife) {
			life = maxLife;
		}
	}
}