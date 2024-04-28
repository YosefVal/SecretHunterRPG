package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
	
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	
	//Entity
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public Player player = new Player(this,keyH);
	public SuperObject obj[] = new SuperObject[10];

	public GamePanel() {
		this.setPreferredSize(new Dimension (screenWidth, screenHeight));
		this.setBackground(BGColor);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);	
	}
	
	public void setupGame() {
		aSetter.setObject();
	}
	
	public void startGameThread(){
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {

		double nextDrawTime = System.nanoTime() + drawInterval;
		
		while (gameThread != null) {
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
					e.printStackTrace();
				}
			}
	}
	
	public void update() {
		player.update();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		//Tile
		tileM.draw(g2); //first layer
		
		//Object
		for (int i = 0; i < obj.length; i++) { //second layer
			if (obj[i] != null) {
				obj[i].draw(g2, this);
			}
		}
		
		//Player
		player.draw(g2); //third layer
		
		g2.dispose();
	}
	
}