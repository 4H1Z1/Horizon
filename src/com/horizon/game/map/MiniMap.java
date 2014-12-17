package com.horizon.game.map;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import com.horizon.game.level.Level;

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
		int[] pixles = ((DataBufferInt) image.getRaster().getDataBuffer())
				.getData();
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int xa = ((playerX/16)-(width/2))+x;
				int ya = ((playerY/16)-(height/2))+y;
				if(level.map.getChunk(xa,ya,false,4)!=null&&level.map.getChunk(xa,ya,false,4).isLoaded){
					pixles[x+y*width] = level.getTile(xa,ya,4).getBaseColour();
				}else{
					pixles[x+y*width] = 0xff000000;
				}
				if(((width/2)-1==x&&height/2==y)||((width/2)==x&&height/2==y)||((width/2)+1==x&&height/2==y)||((height/2)-1==y&&width/2==x)||((height/2)+1==y)&&width/2==x){
					pixles[x + y * width] = 0xff000000;
				}
			}
		}
	}
}
