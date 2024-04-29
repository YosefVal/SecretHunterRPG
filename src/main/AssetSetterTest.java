package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import object.OBJ_Door;
import object.OBJ_Key;

class AssetSetterTest {
	
	GamePanel gp = new GamePanel();

	@Test
	void setObjectTest() {
		gp.obj[0] = new OBJ_Key();
		gp.obj[0].worldX = 24 * gp.tileSize; //sets key on tile 25 of X axis
		gp.obj[0].worldY = 7 * gp.tileSize; //sets key on tile 8 of Y axis
		assertEquals(1536, gp.obj[0].worldX);
		assertEquals(448, gp.obj[0].worldY);
		
		gp.obj[1] = new OBJ_Door();
		gp.obj[1].worldX = 29 * gp.tileSize; //sets key on tile 30 of X axis
		gp.obj[1].worldY = 10 * gp.tileSize; //sets key on tile 11 of Y axis
		assertEquals(1856, gp.obj[1].worldX);
		assertEquals(640, gp.obj[1].worldY);
	}

}
