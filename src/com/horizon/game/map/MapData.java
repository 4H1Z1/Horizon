package com.horizon.game.map;

import com.horizon.game.level.tiles.Tile;


public class MapData {
	public int seed;
		//lower right
	Chunk[][] chunksLR;
		//upper right
	Chunk[][] chunksUR;
		//upper left
	Chunk[][] chunksUL;
		//lower left
	Chunk[][] chunksLL;
	private int chunkCount;
	public MapData(int seed) {
		this.seed = seed;
		this.chunksUR = new Chunk[5000][5000];
		this.chunksUL = new Chunk[5000][5000];
		this.chunksLR = new Chunk[5000][5000];
		this.chunksLL = new Chunk[5000][5000];
	}

	public void generateChunk(int x, int y, boolean shouldLoad) {
		int xb = x;
		int yb = y;
		if(xb<0){
			xb=(int)(((Math.floor((x/16)*16)+-16)/16)*-1);
		}else{
			xb = (int)(Math.floor((x+16)/16)-1);
		}
		if(yb<0){
			yb=(int)(((Math.floor((y/16)*16)+-16)/16)*-1);
		}else{
			yb = (int)(Math.floor((y+16)/16)-1);
		}
		chunkCount++;
		if(x>=0&&y>=0){
			float[][] newTiles = NoiseGenerator.generateOctavedSimplexNoise(16, 16,
					xb*16, yb*16, 5, 0.4F, 0.003f);
			chunksLR[xb][yb] = new Chunk(generateTarrain(newTiles),newTiles, shouldLoad);
			System.out.println("Saved chunks "+chunkCount+" new Chunk("+xb+","+yb+")");
		}else if(x>=0&&y<0){
			//useing *-1 to make it into a positive not to make a out of bounds exeption
			float[][] newTiles = NoiseGenerator.generateOctavedSimplexNoise(16, 16,
					xb*16, yb*-16, 5, 0.4F, 0.003f);
			chunksUR[xb][yb] = new Chunk(generateTarrain(newTiles),newTiles, shouldLoad);
			System.out.println("Saved chunks "+chunkCount+" new Chunk("+xb+","+yb+")");
		}else if(x<0&y<0){
			float[][] newTiles = NoiseGenerator.generateOctavedSimplexNoise(16, 16,
					xb*-16, yb*-16, 5, 0.4F, 0.003f);
			chunksUL[xb][yb] = new Chunk(generateTarrain(newTiles),newTiles, shouldLoad);
			System.out.println("Saved chunks "+chunkCount+" new Chunk("+xb+","+yb+")");
		}else{
			float[][] newTiles = NoiseGenerator.generateOctavedSimplexNoise(16, 16,
					xb*-16, yb*16, 5, 0.4F, 0.003f);
			chunksLL[xb][yb] = new Chunk(generateTarrain(newTiles),newTiles, shouldLoad);
			System.out.println("Saved chunks "+chunkCount+" new Chunk("+xb+","+yb+")");
		}
		
		/*if(x>=0&&y>=0){
			float[][] newTiles = NoiseGenerator.generateOctavedSimplexNoise(16, 16,
					(x/16)*16, (y/16)*16, 2, 0.4F, 0.005f);
			chunksLR[x/16][y/16] = new Chunk(generateTarrain(newTiles),newTiles, shouldLoad);
		}else if(x>=0&&y<0){
			float[][] newTiles = NoiseGenerator.generateOctavedSimplexNoise(16, 16,
					(x/16)*16, ((y/-16)*-16)+-16, 2, 0.4F, 0.005f);
			//useing *-1 to make it into a positive not to make a out of bounds exeption
			chunksUR[x/16][((y/16)*-1)+1] = new Chunk(generateTarrain(newTiles),newTiles, shouldLoad);
		}else if(x<0&y<0){
			float[][] newTiles = NoiseGenerator.generateOctavedSimplexNoise(16, 16,
					x, y, 2, 0.4F, 0.005f);
			chunksUL[((x/16)*-1)+1][((y/16)*-1)+1] = new Chunk(generateTarrain(newTiles),newTiles, shouldLoad);
		}else{
			float[][] newTiles = NoiseGenerator.generateOctavedSimplexNoise(16, 16,
					x, y, 2, 0.4F, 0.005f);
			chunksLL[((x/16)*-1)+1][y/16] = new Chunk(generateTarrain(newTiles),newTiles, shouldLoad);
		}*/
	}

	public int[][] getChunkTiles(int x, int y) {
		if(x>=0&&y>=0){
			// + + Lower right
			if(chunksLR[x/16][y/16]==null){
				return null;
			}
			return chunksLR[x/16][y/16].tiles;
		}else if(x>=0&&y<0){
			//+ - upper right
			if(chunksUR[x/16][y/16]==null){
				return null;
			}
			return chunksUR[x/16][y/16].tiles;
		}else if(x<0&y<0){
			//- - upper left
			if(chunksUL[x/16][y/16]==null){
				return null;
			}
			return chunksUL[x/16][y/16].tiles;
		}else{
			//- + lower left
			if(chunksLL[x/16][y/16]==null){
				return null;
			}
			return chunksLL[x/16][y/16].tiles;
		}
	}
	
	public Chunk getChunk(int x, int y,boolean genIfNull) {
		if(x>=0&&y>=0){
			// + + Lower right
			if(chunksLR[(x/16)][(y/16)]==null){
				if(genIfNull){
					generateChunk(x,y,true);
				}else{
					return null;
				}
			}
			return chunksLR[x/16][y/16];
		}else if(x>=0&&y<0){
			//+ - upper right
			if(chunksUR[x/16][((y/16)*-1)+1]==null){
				if(genIfNull){
					generateChunk(x,y,true);
				}else{
					return null;
				}
			}
			return chunksUR[x/16][((y/16)*-1)+1];
		}else if(x<0&y<0){
			//- - upper left
			if(chunksUL[((x/16)*-1)+1][((y/16)*-1)+1]==null){
				if(genIfNull){
					generateChunk(x,y,true);
				}else{
					return null;
				}
			}
			return chunksUL[((x/16)*-1)+1][((y/16)*-1)+1];
		}else{
			//- + lower left
			if(chunksLL[((x/16)*-1)+1][y/16]==null){
				if(genIfNull){
					generateChunk(x,y,true);
				}else{
					return null;
				}
			}
			return chunksLL[((x/16)*-1)+1][y/16];
		}
	}
	
	public void unloadChunk(int x, int y){
		if(x>=0&&y>=0){
			// + + Lower right
			if(chunksLR[x/16][y/16]==null){
				return;
			}
			chunksLR[x/16][y/16].Unload();
		}else if(x>=0&&y<0){
			//+ - upper right
			if(chunksUR[x/16][y/16]==null){
				return;
			}
			chunksUR[x/16][y/16].Unload();
		}else if(x<0&y<0){
			//- - upper left
			if(chunksUL[x/16][y/16]==null){
				return;
			}
			chunksUL[x/16][y/16].Unload();
		}else{
			//- + lower left
			if(chunksLL[x/16][y/16]==null){
				return;
			}
			chunksLL[x/16][y/16].Unload();
		}
	}
	
	public void loadChunk(int x, int y){
		if(x>=0&&y>=0){
			// + + Lower right
			if(chunksLR[x/16][y/16]==null){
				return;
			}
			chunksLR[x/16][y/16].Load();
		}else if(x>=0&&y<0){
			//+ - upper right
			if(chunksUR[x/16][y/16]==null){
				return;
			}
			chunksUR[x/16][y/16].Load();
		}else if(x<0&y<0){
			//- - upper left
			if(chunksUL[x/16][y/16]==null){
				return;
			}
			chunksUL[x/16][y/16].Load();
		}else{
			//- + lower left
			if(chunksLL[x/16][y/16]==null){
				return;
			}
			chunksLL[x/16][y/16].Load();
		}
	}

	
	
	private int[][] generateTarrain(float[][] frequancy){
		int[][] newTileIDs = new int[frequancy.length][frequancy[0].length];
		for(int x = 0;x< frequancy.length; x++){
			for(int y = 0;y< frequancy[0].length; y++){
				if(frequancy[x][y]<0.00F){
					if(frequancy[x][y]<-0.25F){
						newTileIDs[x][y] = Tile.DEEP_WATER.getID();
					}else{
						newTileIDs[x][y] = Tile.WATER.getID();
					}
					
				}else{
					newTileIDs[x][y] = Tile.GRASS.getID();
				}
			}
		}
		return newTileIDs;
	}

	public Tile getTile(int x, int y) {
		Chunk chunk = getChunk(x, y,true);
		if(chunk==null){
			System.out.println("Generating void tiles when i should not be (Crash protection)");
			return Tile.VOID;
		}
		if(x>=0&&y>=0){
			// + + Lower right
			return Tile.getTileByID(chunk.getTileID(x%16, y%16));
		}else if(x>=0&&y<0){
			//+ - upper right
			return Tile.getTileByID(chunk.getTileID(x%16, (15-(y%-16)*-1)));
			
		}else if(x<0&y<0){
			//- - upper left
			return Tile.getTileByID(chunk.getTileID((15-(x%-16)*-1), 15-((y%-16)*-1)));
		}else{
			//- + lower left
			
			return Tile.getTileByID(chunk.getTileID(15-((x%-16)*-1), y%16));
		}
	}
	public float getTileFrequancy(int x, int y) {
		Chunk chunk = getChunk(x, y,true);
		if(chunk==null){
			System.out.println("Generating void tiles when i should not be (Crash protection)");
			return 0;
		}
		if(x>=0&&y>=0){
			// + + Lower right
			return chunk.getTileFrequancy(x%16, y%16);
		}else if(x>=0&&y<0){
			//+ - upper right
			return chunk.getTileFrequancy(x%16, (15-(y%-16)*-1));
			
		}else if(x<0&y<0){
			//- - upper left
			return chunk.getTileFrequancy((15-(x%-16)*-1), 15-(y%-16)*-1);
		}else{
			//- + lower left
			
			return chunk.getTileFrequancy(15-((x%-16)*-1), y%16);
		}
	}
	public int getTileID(int x, int y) {
		Chunk chunk = getChunk(x, y,true);
		if(chunk==null){
			System.out.println("Generating void tiles when i should not be (Crash protection)");
			return 0;
		}
		if(x>=0&&y>=0){
			// + + Lower right
			return chunk.getTileID(x%16, y%16);
		}else if(x>=0&&y<0){
			//+ - upper right
			return chunk.getTileID(x%16, (15-(y%-16)*-1));
			
		}else if(x<0&y<0){
			//- - upper left
			return chunk.getTileID((15-(x%-16)*-1), 15-(y%-16)*-1);
		}else{
			//- + lower left
			
			return chunk.getTileID(15-((x%-16)*-1), y%16);
		}
	}
}
