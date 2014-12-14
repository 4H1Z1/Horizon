package com.horizon.game.gfx;

import java.awt.Point;

import com.horizon.game.horizon;

public class Screen {

	public static final int MAP_WIDTH = 64;
	public static final int MAP_WIDTH_MASK = MAP_WIDTH - 1;

	public static final byte bit_mirror_X =0x01;
	public static final byte bit_mirror_Y =0x02;
	
	public int[] pixles;

	public int xOffset = 0;
	public int yOffset = 0;

	public int width;
	public int height;

	public SpriteSheet sheet;
	public static Point getMouseLocOnScreen(horizon main,int x, int y){
		int xOffset = main.player.x - (main.screen.width / 2);
		int yOffset = main.player.y - (main.screen.height / 2);
		return new Point(xOffset+x,yOffset+y);
	}
	public Screen(int width, int height, SpriteSheet sheet) {
		this.width = width;
		this.height = height;
		this.sheet = sheet;

		this.pixles = new int[width * height];
	}

	public void render(int xPos, int yPos, int tile,
			int mirrorDir) {
		xPos -= xOffset;
		yPos -= yOffset;
		// amount of blocks in sprite sheet is the 20 X 20
		
		boolean mirrorX=(mirrorDir&bit_mirror_X)>0;
		boolean mirrorY=(mirrorDir&bit_mirror_Y)>0;
		
		int xTile = tile % 20;
		int yTile = tile / 20;
		int tileOffset = (xTile << 4) + (yTile << 4) * sheet.width;
		for (int y = 0; y < 16; y++) {
			int ySheet = y;
			if (mirrorY)
				ySheet = (16 - 1) - y;
			if (y + yPos < 0 || y + yPos >= height) {
				continue;
			}
			for (int x = 0; x < 16; x++) {
				int xSheet = x;
				if (mirrorX)
					xSheet = (16 - 1) - x;
				if (x + xPos < 0 || x + xPos >= width) {
					continue;
				}
				int col = sheet.pixles[xSheet + ySheet
						* sheet.width + tileOffset];
				if (col < 255) {
					pixles[(x + xPos) + (y + yPos) * width] = col;
				}
			}
		}
	}

	public void render(int xPos, int yPos, int tile) {
		render(xPos, yPos, tile, 0x00);
	}

	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
}
