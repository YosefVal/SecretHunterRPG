package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Key extends SuperObject {
	GamePanel gp;
	
	/**
	 * Purpose: Gets Key Image and scales it
	 * @param gp
	 */
	public OBJ_Key(GamePanel gp) {
		name = "Key";
		try{
			image = ImageIO.read(getClass().getResourceAsStream("/objects/key_big.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		} catch(IOException e) {
			System.out.println("Error: Missing image/Scaling gone wrong (OBJ_Key line 18)");
		}
		solidArea.x = 5;
	}
}
