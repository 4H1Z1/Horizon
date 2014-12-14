package com.horizon.game;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.RescaleOp;

import javax.swing.JFrame;
import javax.swing.event.MouseInputAdapter;

import com.horizon.game.entitys.Player;
import com.horizon.game.gfx.Screen;
import com.horizon.game.gfx.SpriteSheet;
import com.horizon.game.gui.GUI;
import com.horizon.game.level.Level;
import com.horizon.game.structures.Structures;

public class horizon extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	private static String version = "V0.0.1_8";
	static Point mouseCoords = new Point();
	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 3;
	public static final String NAME = "Horizon";
	public static CurrentScreen currentScreen;
	private int fps;
	private int tps;
	private JFrame frame;
	public boolean isGameRunning = false;
	public int tickCount = 0;
	public BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
			BufferedImage.TYPE_INT_ARGB);
	private int[] pixles = ((DataBufferInt) image.getRaster().getDataBuffer())
			.getData();

	public Screen screen;

	public Player player;
	public InputHandler input;

	public Level level;

	public horizon() {
		setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		frame.add(this, BorderLayout.CENTER);
		frame.pack();

		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		addMouseListener(new MouseInput(this));
		addMouseMotionListener(new MouseInputAdapter() {

			@Override
			public void mouseMoved(MouseEvent e) {
				mouseCoords = e.getPoint();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				mouseCoords = null;
			}
		});
		setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		// 60D is how many ticks per seccond
		double nsPerTick = 1000000000D / 60D;
		int frames = 0;
		int ticks = 0;
		long lastTimer = System.currentTimeMillis();
		double delta = 0;

		init();
		while (isGameRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = false;

			while (delta >= 1) {
				ticks++;
				tick();
				delta -= 1;
				shouldRender = true;
			}
			if (shouldRender == true) {
				frames++;
				render();
			}
			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				// System.out.println("FPS:" + frames + ",TPS:" + ticks);
				tps = frames;
				fps = ticks;
				frames = 0;
				ticks = 0;
			}
		}
		System.out.println("Exit");
	}

	public void init() {
		screen = new Screen(WIDTH, HEIGHT, new SpriteSheet("/spritesheet.png"));
		input = new InputHandler(this);
		level = new Level(this,400);
		player = new Player(level, 0, 0, input,1);
		level.addEntity(player);
		// will be changed to main menu at a later date
		currentScreen = CurrentScreen.GAME;
		Structures.oak_tree.generateStructure(-10, 0, level);
	}

	public void tick() {
		tickCount++;
		level.tick();
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		int xOffset = player.x - (screen.width / 2);
		int yOffset = player.y - (screen.height / 2);
		// rendering the world
		level.renderTiles(screen, xOffset, yOffset,player);
		level.renderEntitys(screen);

		for (int y = 0; y < screen.height; y++) {
			for (int x = 0; x < screen.width; x++) {
				int colourCode = screen.pixles[x + y * screen.width];
				if (colourCode < 255) {
					pixles[x + y * WIDTH] = colourCode;
				}
			}
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());

		// incase other screens like the map has a overlay
		GUI.renderOverlay(g, this, level);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		g.setColor(Color.YELLOW);
		if (!(mouseCoords == null) && mouseCoords.x >= 0 && mouseCoords.y >= 0) {
			double x = 0, y = 0;
				int holder = xOffset % 16;
				x = (mouseCoords.x + (holder * SCALE)) / (16 * SCALE)
						+ (xOffset / 16);
				int holdera = yOffset % 16;
				y = (mouseCoords.y + (holdera * SCALE)) / (16 * SCALE)
						+ (yOffset / 16);
			
			// System.out.println(x);
			g.drawString("Horizon Game ("+version+") (FPS:" + fps + " TPS:" + tps
					+ ")", 10, 20);
			g.drawString("PlayerLoc: (" + (player.x / 16) + ","
					+ (player.y / 16) + ")", 10, 40);
			g.drawString("CurrentBlock: (" + x + "," + y + ","+level.getTileType((int) x, (int) y)+","+level.map.getTileFrequancy((int)x, (int)y)+")", 10, 60);
			int xChunk = (player.x/16)/16;
			int yChunk =(player.y/16)/16;
			if(player.y<0){
				yChunk =((player.y/16)/16)+-1;
			}
			if(player.x<0){
				xChunk =((player.x/16)/16)+-1;
			}
			g.drawString("Current chunk X:"+xChunk+" y:"+yChunk, 10, 80);
		}
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		new horizon().start();
	}

	public synchronized void start() {
		isGameRunning = true;
		new Thread(this).start();
	}

	public synchronized void stop() {
		isGameRunning = false;
	}
}
