package com.horizon.game.structures;

import com.horizon.game.level.Level;
import com.horizon.game.level.tiles.Tile;
import com.horizon.game.map.Chunk;

public abstract class Structures {

	public static final Structures oak_tree = new Plant(new byte[] { 0, 0, 6, 0, 0,
																	0, 6, 6, 6, 0, 
																	0, 6, 6, 6, 0, 
																	0, 0, 5, 0, 0, 
																	0, 0, 5, 0, 0 },
																	new byte[] { 0, 0, 3, 0, 0,
																				0, 3, 3, 3, 0, 
																				0, 3, 3, 3, 0, 
																				0, 0, 3, 0, 0, 
																				0, 0, 2, 0, 0 }, 5, 5);
	public static final Structures ree = new Plant(new byte[] { 6, 6, 6, 6, 6,
																	6, 6, 6, 6, 6, 
																	6, 6, 6, 6, 6, 
																	6, 6, 0, 6, 6, 
																	6, 6, 0, 6, 6 },
																	new byte[] { 6, 6, 6, 6, 6,
																				6, 6, 6, 6, 6, 
																				6, 6, 6, 6, 6, 
																				6, 6, 0, 6, 6, 
																				6, 6, 0, 6, 6 }, 5, 5);
	byte[] IDArray;
	byte[] level;
	int width;
	int height;
	Structures(byte[] IDArray,byte[] level, int width, int height) {
		this.height = height;
		this.width = width;
		this.IDArray = IDArray;
		this.level = level;
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	public byte[] getIDArray(){
		return IDArray;
	}
	public byte[] getLevel(){
		return level;
	}
}
