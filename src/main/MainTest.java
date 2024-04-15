package main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MainTest {

	@Test
	void testTitle() {
		Main main = new Main();
		assertEquals("Secret Hunter RPG", main.title);
	}

}
