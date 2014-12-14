package com.horizon.game.structures;

import com.horizon.game.level.Level;
import com.horizon.game.level.tiles.Tile;

public enum Structures {

	oak_tree(new byte[] { 0, 0, 6, 0, 0,
						  0, 6, 6, 6, 0, 
						  0, 6, 6, 6, 0, 
						  0, 0, 5, 0, 0, 
						  0, 0, 5, 0, 0 }, 5, 5);
	public byte[] tiles;
	byte[] IDArray;
	int width;
	int height;

	Structures(byte[] IDArray, int width, int height) {
		this.IDArray = IDArray;
		this.width = width;
		this.height = height;
		this.tiles = new byte[5000 * 5000];
	}

	public void generateStructure(int x, int y,Level level) {
		/*if (hasSpace(x, y, level)) {
			// check its land
			for (int xa = 0; xa < width; xa++) {
				if (level.tiles.length > ((x + xa) + (y + (height - 1))
						* level.width)
						&& 0 <= ((x + xa) + (y + (height - 1)) * level.width)) {
					if (Tile.getTileByID(
							level.tiles[(x + xa) + (y + (height - 1))
									* level.width]).isLiquid()
							|| Tile.getTileByID(
									level.tiles[(x + xa) + (y + (height - 1))
											* level.width]).isLiquid()) {
						return;
					}
				}

			}
			int count = 0;
			for (int ya = 0; ya < height; ya++) {
				for (int xa = 0; xa < width; xa++) {
					if (IDArray[count] != 0) {
						if (level.tiles.length > ((x + xa) + (y + ya)
								* level.width)) {
							tiles[(x + xa) + (y + ya) * level.width] = IDArray[count];
							level.tiles[(x + xa) + (y + ya) * level.width] = IDArray[count];
						}
					}
					count++;
				}
			}
		}*/
	}

	public boolean hasSpace(int x, int y, Level level) {
		/*for (int ya = 0; ya < height; ya++) {
			for (int xa = 0; xa < width; xa++) {
				if (tiles[(xa + x) + (ya + y) * level.width] != 0) {
					return false;
				}
			}
		}*/
		return true;
	}

	public boolean canBuildHere(int x, int y, Level level) {
		return false;
	}
}
