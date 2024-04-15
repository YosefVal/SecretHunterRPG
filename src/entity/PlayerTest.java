package entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.GamePanel;
import main.KeyHandler;

class PlayerTest {
	GamePanel gp;
	KeyHandler keyH;
	
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

}
