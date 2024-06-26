package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Door extends SuperObject{
	GamePanel gp;
	
	/**
	 * Purpose: Gets door image and sets collision
	 * @param gp
	 */
	public OBJ_Door(GamePanel gp) {
		name = "Door";
		try{
			image = ImageIO.read(getClass().getResourceAsStream("/objects/WoodDoor.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		} catch(IOException e) {
			System.out.println("Error: Missing image/Scaling gone wrong (OBJ_Door line 23)");
		}
		collision = true;
	}
}
