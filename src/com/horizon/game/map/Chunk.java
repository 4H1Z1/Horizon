package com.horizon.game.map;

import com.horizon.game.level.tiles.Tile;

public class Chunk {
	public int[][] tiles;
	public boolean isLoaded;
	public float[][] tileFrequancy;
	public Chunk(int[][] tiles,float[][] tileFrequancy, boolean isLoaded) {
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
	public Tile getTile(int x, int y) {
		if(x<0||y<0){
			return null;
		}
		return Tile.getTileByID(tiles[x][y]);
	}
	public void setTile(int x, int y, int id) {
		if(x<0||y<0){
			return;
		}
		tiles[x][y] = id;
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
