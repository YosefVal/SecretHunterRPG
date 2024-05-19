package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	public boolean upPressed, downPressed, rightPressed, leftPressed;
	GamePanel gp;
	
	/**
	 * Purpose: Gets gamePanel
	 * @param gp
	 */
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	/**
	 * Purpose: Responds to key input by user
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if(gp.gameState == gp.titleState) { //Title screen commands
			if (code == KeyEvent.VK_W) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 2;
				}
			}
			if (code == KeyEvent.VK_S) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 2) {
					gp.ui.commandNum = 0;
				}
			}
			if(code == KeyEvent.VK_ENTER) { //What happens when player presses enter
				if(gp.ui.commandNum == 0) {
					gp.gameState = gp.playState; //Goes to game
				}
				if(gp.ui.commandNum == 1) {
					//Later
				}
				if(gp.ui.commandNum == 2) { //Exits
					System.exit(0);
				}
			}
		}
		
		if (gp.gameState == gp.playState) { //Responds to input in-game
			if (code == KeyEvent.VK_W) {
				upPressed = true;
			}
			if (code == KeyEvent.VK_A) {
				leftPressed = true;
			}
			if (code == KeyEvent.VK_S) {
			downPressed = true;
			}
			if (code == KeyEvent.VK_D) {
				rightPressed = true;
			}
		}
		if (code == KeyEvent.VK_P) { //Pauses and unpauses game
			if(gp.gameState == gp.playState) {
				gp.gameState = gp.pauseState;
			}
			else if(gp.gameState == gp.pauseState) {
				gp.gameState = gp.playState;
			}
		}
	}

	/**
	 * Purpose: breaks direcrion input when key is released
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if (code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if (code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if (code == KeyEvent.VK_D) {
			rightPressed = false;
		}			
	}
}