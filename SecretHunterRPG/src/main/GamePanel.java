package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable{

	final int originalTileSize = 32; //32x32 size
	final int scale = 2;
	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	
	//World setting
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
	//FPS
	int FPS = 30;
	
	double drawInterval = 1000000000/FPS;
	
	//Background color
	Color BGColor = Color.BLACK;
	
	TileManager tileM = new TileManager(this);
	
	KeyHandler keyH = new KeyHandler(this);
	Thread gameThread;
	
	//Entity and objects
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	public Player player = new Player(this,keyH);
	public SuperObject obj[] = new SuperObject[10];
	public Entity npc[] = new Entity[10];
	
	//Gamestate
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;

	/**
	 * Puspose: Sets up Screen 
	 */
	public GamePanel() {
		this.setPreferredSize(new Dimension (screenWidth, screenHeight));
		this.setBackground(BGColor);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);	
	}
	
	/**
	 * Purpose; sets up obkects, NPCs, and gamestate
	 */
	public void setupGame() {
		aSetter.setObject();
		aSetter.setNPC();
		gameState = titleState;
	}
	
	/**
	 * Purpose: sets up game Thread
	 */
	public void startGameThread(){
		gameThread = new Thread(this);
		gameThread.start();
	}

	/**
	 * Purpose: Update the game by FPS (Frames per second)
	 */
	@Override
	public void run() {
		double nextDrawTime = System.nanoTime() + drawInterval;
		while (gameThread != null) {
			update();
			repaint();
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime / 1000000;
				if (remainingTime < 0) {
					remainingTime = 0;
				}
				Thread.sleep((long) remainingTime);
				nextDrawTime += drawInterval;
			} catch (InterruptedException e) {
				System.out.println("Error: Run interrupted (GamePanel line 105)");
			}
		}
	}
	
	/**
	 * Purpose: Updates player and NPCs if game is not paused
	 */
	public void update() {
		if(gameState == playState) {
			player.update();
			for (int i = 0; i < npc.length; i++) {
				if (npc[i] != null) {
					npc[i].update();
				}
			}
		}
		if (gameState == pauseState) {} //Doesn't update anything if paused
	}
	
	/**
	 * Purpose: Paints all of the game componenets
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		//TitleScreen 
		if (gameState == titleState) { //Paints title Screen
			ui.draw(g2);
		}
		else { // If game not in title screen
			//Tile
			tileM.draw(g2); //first layer
			
			//Player
			player.draw(g2);
			
			//Object
			for (int i = 0; i < obj.length; i++) {
				if (obj[i] != null) {
					obj[i].draw(g2, this);
				}
			}
			for (int i = 0; i < npc.length; i++) {
				if (npc[i] != null) {
					npc[i].draw(g2);
				}
			}
			ui.draw(g2);
		}
		g2.dispose();	
	}	
}
