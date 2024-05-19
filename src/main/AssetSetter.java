package main;

import entity.NPC_OldMan;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {

	GamePanel gp;
	
	/**
	 * Purpose: 
	 * @param gp
	 */
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	/**
	 * Purpose: Creates and sets objects to desired locations
	 * Values are -1 from desired coordinates
	 */
	public void setObject() {
		gp.obj[0] = new OBJ_Key(gp);
		gp.obj[0].worldX = 24 * gp.tileSize;
		gp.obj[0].worldY = 7 * gp.tileSize;
		
		gp.obj[1] = new OBJ_Key(gp);
		gp.obj[1].worldX = 4 * gp.tileSize;
		gp.obj[1].worldY = 38 * gp.tileSize;
		
		gp.obj[2] = new OBJ_Door(gp);
		gp.obj[2].worldX = 42 * gp.tileSize;
		gp.obj[2].worldY = 35 * gp.tileSize;
		
		gp.obj[3] = new OBJ_Door(gp);
		gp.obj[3].worldX = 5 * gp.tileSize;
		gp.obj[3].worldY = 1 * gp.tileSize;
	}
	
	/**
	 * Purpose: Creates and Sets NPC to desired position
	 * Values are -1 from desired coordinates
	 */
	public void setNPC () {
		gp.npc[0] = new NPC_OldMan(gp);
		gp.npc[0].worldX = gp.tileSize*22;
		gp.npc[0].worldY = gp.tileSize*23;
	}
}