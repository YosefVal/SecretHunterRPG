package main;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.awt.Color;

import org.junit.jupiter.api.Test;

class GamePanelTest {
	
	@Test
	void testScreenSize() {  
		GamePanel gp = new GamePanel();
		assertEquals(gp.screenWidth, gp.tileSize * gp.maxScreenCol);
		assertEquals(gp.screenHeight, gp.tileSize * gp.maxScreenRow);
	}
	
	@Test
	void testStart() {
		GamePanel gp = new GamePanel();
		gp.startGameThread();
		assertNotEquals(null, gp.gameThread);
	}

	@Test
	void testRun() {
		GamePanel gp = new GamePanel();
		gp.run();
		assertNotEquals(null, gp);
	}
	@Test
	void testBackGround() {
		GamePanel gp = new GamePanel();
		gp.setBackground(gp.BGColor);
		assertEquals(Color.BLACK, gp.BGColor);
	}
}
