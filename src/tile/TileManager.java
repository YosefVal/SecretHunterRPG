package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {

	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	
	/**
	 * Purpose: Sets up map and tiles
	 * @param gp
	 */
	public TileManager (GamePanel gp) {
		this.gp = gp;
		tile = new Tile[10];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		getTileImage();
		loadMap();
	}
	
	/**
	 * Purpose: Gets tile name and sets collision
	 */
	public void getTileImage() {
		setup(0, "Grass", false);
		setup(1, "Dirt", false);
		setup(2, "Water", true);
		setup(3, "CastleWall", true);
		setup(4, "Tree", true);
		setup(5, "Tree", false);
	}
	
	/**
	 * Purpose: Gets tile image and scales it
	 * @param index
	 * @param imageName
	 * @param collision
	 */
	public void setup(int index, String imageName, boolean collision) {
		UtilityTool uTool = new UtilityTool();
		
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;
		} catch (IOException e) {
			System.out.println("Error: Missing image/Scaling gone wrong (TileManager line 59)");
		}
	}
	
	/**
	 * Purpose: Uses Multidimensional arrays in text files in order to create a map
	 */
	public void loadMap() {
		
		try {
			InputStream iS = getClass().getResourceAsStream("/maps/Map01.txt");
			BufferedReader BR = new BufferedReader(new InputStreamReader(iS));
			
			int col = 0;
			int row = 0;
					
			while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
				String Line = BR.readLine();
				
				while (col < gp.maxWorldCol) {
					String numbers[] = Line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
				}
				if (col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			BR.close();	
		}catch(Exception e) {
			System.out.println("Error: Missing text file (TileManager line 93)");
		}
	}
	
	/**
	 * Purpose: Draws Tile images
	 * @param g2
	 */
	public void draw (Graphics2D g2) {
		int worldCol = 0;
		int worldRow = 0;
		
		while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			int tileNum = mapTileNum[worldCol][worldRow];
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
			   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
			   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
			   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			
			g2.drawImage(tile[tileNum].image, screenX, screenY, null);
			}
			worldCol++;
			
			if (worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
	}
}
