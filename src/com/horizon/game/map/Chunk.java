package com.horizon.game.map;

public class Chunk {
	public int[][] tiles;
	public boolean isLoaded;
	public float[][] tileFrequancy;
	Chunk(int[][] tiles,float[][] tileFrequancy, boolean isLoaded) {
		this.tiles = tiles;
		this.isLoaded = isLoaded;
		this.tileFrequancy = tileFrequancy;
	}

	public int[][] getTileIDs() {
		return tiles;
	}
	public float[][] getTileFrequancys(int x, int y) {
		return tileFrequancy;
	}
	public int getTileID(int x, int y) {
		if(x<0||y<0){
			return 0;
		}
		return tiles[x][y];
	}
	public float getTileFrequancy(int x, int y) {
		return tileFrequancy[x][y];
	}

	public boolean isLoaded() {
		return isLoaded;
	}
	public void Unload() {
		isLoaded = false;
	}
	public void Load() {
		isLoaded = true;
	}
}
