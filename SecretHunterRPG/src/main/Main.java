package main;

import javax.swing.JFrame;

public class Main {
	static String title = "Secret Hunter RPG";
	
	/**
	 * Purpose: Starts game
	 * @param args
	 */
	public static void main(String[] args) {
		Game();
	}
	
	/**
	 * Purpose: Sets up Game
	 */
	public static void Game() {

		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle(title);
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gamePanel.setupGame();
		gamePanel.startGameThread();
	}
}
