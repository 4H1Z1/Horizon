package com.horizon.game.map;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import com.horizon.game.level.Level;
import com.horizon.game.level.tiles.Tile;

public class MiniMap {
	public int width;
	public int height;
	public Level level;
	public BufferedImage image;
	public byte[] tiles;

	// width and height is amount of blocks
	public MiniMap(int width, int height, int playerX, int playerY, Level level) {
		tiles = new byte[width * height];
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		this.width = width;
		this.height = height;
		this.level = level;
		updateMap(playerX, playerY);
	}

	public void updateMap(int playerX, int playerY) {
		/*int[] pixles = ((DataBufferInt) image.getRaster().getDataBuffer())
				.getData();
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (level.tiles.length<((playerX+x-(width/2))+(playerY+y-(height/2))*level.width)||
						0 > ((x + (playerX - (width / 2))) + (y + (playerY - (height - 2))) * level.width)) {
					pixles[x + y * width] = Tile.VOID.getBaseColour();
				} else {
					pixles[x + y * width] = Tile.getTileByID(
							level.tiles[(x + (playerX - (width / 2)))
									+ (y + (playerY - (height / 2)))
									* level.width]).getBaseColour();
				}
				if(((width/2)-1==x&&height/2==y)||((width/2)==x&&height/2==y)||((width/2)+1==x&&height/2==y)||((height/2)-1==y&&width/2==x)||((height/2)+1==y)&&width/2==x){
					pixles[x + y * width] = Tile.VOID.getBaseColour();
				}
			}
		}*/
	}
}
