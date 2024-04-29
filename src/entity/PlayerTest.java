package entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.GamePanel;
import main.KeyHandler;

class PlayerTest {
	GamePanel gp = new GamePanel();
	KeyHandler keyH = new KeyHandler();
	
	@Test
	void testHP() {
		Player player = new Player(gp, keyH);
		player.lowerHP();
		assertNotEquals(10, player.HP);
		player.heal();
		assertEquals(10, player.HP);
		player.heal();
		assertEquals(10, player.HP);
	}
	
	@Test
	void testDirection() {
		Player player = new Player(gp, keyH);
		
		keyH.upPressed = true;
		player.update();
		assertEquals("up", player.direction);
		
		keyH.downPressed = true;
		keyH.upPressed = false;
		player.update();
		assertEquals("down", player.direction);
		
		keyH.rightPressed = true;
		keyH.downPressed = false;
		player.update();
		assertEquals("right", player.direction);
		
		keyH.leftPressed = true;
		keyH.rightPressed = false;
		player.update();
		assertEquals("left", player.direction);
	}

}
