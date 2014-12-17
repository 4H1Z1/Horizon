package com.horizon.game.map;

import java.util.Random;

import com.horizon.game.level.Level;
import com.horizon.game.level.tiles.Tile;
import com.horizon.game.structures.Building;
import com.horizon.game.structures.Plant;
import com.horizon.game.structures.Structures;


public class MapData {
	public int seed;
	//Back layer
		//lower right
	Chunk[][] FirstLayer_chunksLR;
		//upper right
	Chunk[][] FirstLayer_chunksUR;
		//upper left
	Chunk[][] FirstLayer_chunksUL;
		//lower left
	Chunk[][] FirstLayer_chunksLL;
	
	//entitylayer/block colision layer
		//lower right
	Chunk[][] SeccondLayer_chunksLR;
		//upper right
	Chunk[][] SeccondLayer_chunksUR;
		//upper left
	Chunk[][] SeccondLayer_chunksUL;
		//lower left
	Chunk[][] SeccondLayer_chunksLL;
	
	//front layer/ infront of players
		//lower right
	Chunk[][] ThirdLayer_chunksLR;
		//upper right
	Chunk[][] ThirdLayer_chunksUR;
		//upper left
	Chunk[][] ThirdLayer_chunksUL;
		//lower left
	Chunk[][] ThirdLayer_chunksLL;
	
		//Stiched map
		//lower right
	Chunk[][] StichedMap_chunksLR;
		//upper right
	Chunk[][] StichedMap_chunksUR;
		//upper left
	Chunk[][] StichedMap_chunksUL;
		//lower left
	Chunk[][] StichedMap_chunksLL;
	
	Chunk[][] Structure_chunkLR;
		//upper right
	Chunk[][] Structure_chunkUR;
		//upper left
	Chunk[][] Structure_chunkUL;
		//lower left
	Chunk[][] Structure_chunkLL;
	public int loadedChunkCount;
	private Level level;
	public MapData(int seed,Level level) {
		this.seed = seed;
		this.FirstLayer_chunksUR = new Chunk[1000][1000];
		this.FirstLayer_chunksUL = new Chunk[1000][1000];
		this.FirstLayer_chunksLR = new Chunk[1000][1000];
		this.FirstLayer_chunksLL = new Chunk[1000][1000];
		
		this.SeccondLayer_chunksUR = new Chunk[1000][1000];
		this.SeccondLayer_chunksUL = new Chunk[1000][1000];
		this.SeccondLayer_chunksLR = new Chunk[1000][1000];
		this.SeccondLayer_chunksLL = new Chunk[1000][1000];
		
		this.ThirdLayer_chunksUR = new Chunk[1000][1000];
		this.ThirdLayer_chunksUL = new Chunk[1000][1000];
		this.ThirdLayer_chunksLR = new Chunk[1000][1000];
		this.ThirdLayer_chunksLL = new Chunk[1000][1000];
		
		this.StichedMap_chunksUR = new Chunk[1000][1000];
		this.StichedMap_chunksUL = new Chunk[1000][1000];
		this.StichedMap_chunksLR = new Chunk[1000][1000];
		this.StichedMap_chunksLL = new Chunk[1000][1000];
		this.level = level;
		
		this.Structure_chunkLR = new Chunk[1000][1000];
		this.Structure_chunkLL = new Chunk[1000][1000];
		this.Structure_chunkUR = new Chunk[1000][1000];
		this.Structure_chunkUL = new Chunk[1000][1000];
	}

	public void generateChunk(int x, int y, boolean shouldLoad) {
		/*
		 old values
		 float scale = 0.003F;
		float roughness = 0.4F;
		int octaves = 5;
		 */
		float scale = 0.002F;
		float roughness = 0.60F;
		int octaves = 5;
		int xb = x;
		int yb = y;
		if(xb<0){
			xb=(int)(((Math.floor((x/16)*16)-16)/16)*-1);
		}else{
			xb = (int)(Math.floor((x+16)/16)-1);
		}
		if(yb<0){
			yb=(int)(((Math.floor((y/16)*16)-16)/16)*-1);
		}else{
			yb = (int)(Math.floor((y+16)/16)-1);
		}
		loadedChunkCount++;
		if(x>=0&&y>=0){
			float[][] newTiles = NoiseGenerator.generateOctavedSimplexNoise(16, 16,
					xb*16, yb*16, octaves, roughness, scale);
			int[][] tarrain = generateTarrain(newTiles);
			FirstLayer_chunksLR[xb][yb] = new Chunk(tarrain,newTiles, shouldLoad);
			StichedMap_chunksLR[xb][yb] = new Chunk(tarrain,newTiles, shouldLoad);
			for(int xc = 0;xc<14;xc++){
				for(int yc = 0;yc<15;yc++){
					if(new Random().nextInt(20)==1){
						generateStructure((xb*16)+xc, (yb*16)+yc, Structures.oak_tree);
					}
				}	
			}
		}else if(x>=0&&y<0){
			//useing *-1 to make it into a positive not to make a out of bounds exeption
			float[][] newTiles = NoiseGenerator.generateOctavedSimplexNoise(16, 16,
					xb*16, yb*-16, octaves, roughness, scale);
			int[][] tarrain = generateTarrain(newTiles);
			FirstLayer_chunksUR[xb][yb] = new Chunk(tarrain,newTiles, shouldLoad);
			StichedMap_chunksUR[xb][yb] = new Chunk(tarrain,newTiles, shouldLoad);
			for(int xc = 0;xc<14;xc++){
				for(int yc = 0;yc<15;yc++){
					if(new Random().nextInt(20)==1){
						generateStructure((xb*16)+xc, (yb*-16)+yc, Structures.oak_tree);
					}
				}	
			}
		}else if(x<0&y<0){
			float[][] newTiles = NoiseGenerator.generateOctavedSimplexNoise(16, 16,
					xb*-16, yb*-16, octaves, roughness, scale);
			int[][] tarrain = generateTarrain(newTiles);
			FirstLayer_chunksUL[xb][yb] = new Chunk(tarrain,newTiles, shouldLoad);
			StichedMap_chunksUL[xb][yb] = new Chunk(tarrain,newTiles, shouldLoad);
			for(int xc = 0;xc<13;xc++){
				for(int yc = 0;yc<13;yc++){
					if(new Random().nextInt(20)==1){
						generateStructure((xb*-16)+xc, (yb*-16)+yc, Structures.oak_tree);
					}
				}	
			}
		}else{
			float[][] newTiles = NoiseGenerator.generateOctavedSimplexNoise(16, 16,
					xb*-16, yb*16, octaves, roughness, scale);
			int[][] tarrain = generateTarrain(newTiles);
			FirstLayer_chunksLL[xb][yb] = new Chunk(tarrain,newTiles, shouldLoad);
			StichedMap_chunksLL[xb][yb] = new Chunk(tarrain,newTiles, shouldLoad);
			for(int xc = 0;xc<14;xc++){
				for(int yc = 0;yc<15;yc++){
					if(new Random().nextInt(20)==1){
						generateStructure((xb*-16)+xc, (yb*16)+yc, Structures.oak_tree);
					}
				}	
			}
		}
		System.out.println("Saved chunk, new Chunk("+xb+","+yb+")");
		
	}

	public int[][] getChunkTiles(int x, int y,int vue) {
		switch(vue){
		case 1:
			if(x>=0&&y>=0){
				// + + Lower right
				if(FirstLayer_chunksLR[x/16][y/16]==null){
					return null;
				}
				return FirstLayer_chunksLR[x/16][y/16].tiles;
			}else if(x>=0&&y<0){
				//+ - upper right
				if(FirstLayer_chunksUR[x/16][y/16]==null){
					return null;
				}
				return FirstLayer_chunksUR[x/16][y/16].tiles;
			}else if(x<0&y<0){
				//- - upper left
				if(FirstLayer_chunksUL[x/16][y/16]==null){
					return null;
				}
				return FirstLayer_chunksUL[x/16][y/16].tiles;
			}else{
				//- + lower left
				if(FirstLayer_chunksLL[x/16][y/16]==null){
					return null;
				}
				return FirstLayer_chunksLL[x/16][y/16].tiles;
			}
		case 2:
			if(x>=0&&y>=0){
				// + + Lower right
				if(SeccondLayer_chunksLR[x/16][y/16]==null){
					return null;
				}
				return SeccondLayer_chunksLR[x/16][y/16].tiles;
			}else if(x>=0&&y<0){
				//+ - upper right
				if(SeccondLayer_chunksUR[x/16][y/16]==null){
					return null;
				}
				return SeccondLayer_chunksUR[x/16][y/16].tiles;
			}else if(x<0&y<0){
				//- - upper left
				if(SeccondLayer_chunksUL[x/16][y/16]==null){
					return null;
				}
				return SeccondLayer_chunksUL[x/16][y/16].tiles;
			}else{
				//- + lower left
				if(SeccondLayer_chunksLL[x/16][y/16]==null){
					return null;
				}
				return SeccondLayer_chunksLL[x/16][y/16].tiles;
			}
		case 3:
			if(x>=0&&y>=0){
				// + + Lower right
				if(ThirdLayer_chunksLR[x/16][y/16]==null){
					return null;
				}
				return ThirdLayer_chunksLR[x/16][y/16].tiles;
			}else if(x>=0&&y<0){
				//+ - upper right
				if(ThirdLayer_chunksUR[x/16][y/16]==null){
					return null;
				}
				return ThirdLayer_chunksUR[x/16][y/16].tiles;
			}else if(x<0&y<0){
				//- - upper left
				if(ThirdLayer_chunksUL[x/16][y/16]==null){
					return null;
				}
				return ThirdLayer_chunksUL[x/16][y/16].tiles;
			}else{
				//- + lower left
				if(ThirdLayer_chunksLL[x/16][y/16]==null){
					return null;
				}
				return ThirdLayer_chunksLL[x/16][y/16].tiles;
			}
		case 4:
			if(x>=0&&y>=0){
				// + + Lower right
				if(StichedMap_chunksLR[x/16][y/16]==null){
					return null;
				}
				return StichedMap_chunksLR[x/16][y/16].tiles;
			}else if(x>=0&&y<0){
				//+ - upper right
				if(StichedMap_chunksUR[x/16][y/16]==null){
					return null;
				}
				return StichedMap_chunksUR[x/16][y/16].tiles;
			}else if(x<0&y<0){
				//- - upper left
				if(StichedMap_chunksUL[x/16][y/16]==null){
					return null;
				}
				return StichedMap_chunksUL[x/16][y/16].tiles;
			}else{
				//- + lower left
				if(StichedMap_chunksLL[x/16][y/16]==null){
					return null;
				}
				return StichedMap_chunksLL[x/16][y/16].tiles;
			}	
		}
		return null;
	}
	
	public Chunk getChunk(int x, int y,boolean genIfNull,int vue) {
		switch(vue){
		case 1:
			if(x>=0&&y>=0){
				// + + Lower right
				if(FirstLayer_chunksLR[(x/16)][(y/16)]==null){
					if(genIfNull){
						generateChunk(x,y,true);
					}else{
						return null;
					}
				}
				return FirstLayer_chunksLR[x/16][y/16];
			}else if(x>=0&&y<0){
				//+ - upper right
				if(FirstLayer_chunksUR[x/16][((y/16)*-1)+1]==null){
					if(genIfNull){
						generateChunk(x,y,true);
					}else{
						return null;
					}
				}
				return FirstLayer_chunksUR[x/16][((y/16)*-1)+1];
			}else if(x<0&y<0){
				//- - upper left
				if(FirstLayer_chunksUL[((x/16)*-1)+1][((y/16)*-1)+1]==null){
					if(genIfNull){
						generateChunk(x,y,true);
					}else{
						return null;
					}
				}
				return FirstLayer_chunksUL[((x/16)*-1)+1][((y/16)*-1)+1];
			}else{
				//- + lower left
				if(FirstLayer_chunksLL[((x/16)*-1)+1][y/16]==null){
					if(genIfNull){
						generateChunk(x,y,true);
					}else{
						return null;
					}
				}
				return FirstLayer_chunksLL[((x/16)*-1)+1][y/16];
			}
		case 2:
			if(x>=0&&y>=0){
			// + + Lower right
			if(SeccondLayer_chunksLR[(x/16)][(y/16)]==null){
				if(genIfNull){
					SeccondLayer_chunksLR[(x/16)][(y/16)]= new Chunk(new int[16][16], new float[16][16], true);
				}else{
					return null;
				}
			}
			return SeccondLayer_chunksLR[x/16][y/16];
		}else if(x>=0&&y<0){
			//+ - upper right
			if(SeccondLayer_chunksUR[x/16][((y/16)*-1)+1]==null){
				if(genIfNull){
					SeccondLayer_chunksUR[x/16][((y/16)*-1)+1]= new Chunk(new int[16][16], new float[16][16], true);
				}else{
					return null;
				}
			}
			return SeccondLayer_chunksUR[x/16][((y/16)*-1)+1];
		}else if(x<0&y<0){
			//- - upper left
			if(SeccondLayer_chunksUL[((x/16)*-1)+1][((y/16)*-1)+1]==null){
				if(genIfNull){
					SeccondLayer_chunksUL[((x/16)*-1)+1][((y/16)*-1)+1]= new Chunk(new int[16][16], new float[16][16], true);
				}else{
					return null;
				}
			}
			return SeccondLayer_chunksUL[((x/16)*-1)+1][((y/16)*-1)+1];
		}else{
			//- + lower left
			if(SeccondLayer_chunksLL[((x/16)*-1)+1][y/16]==null){
				if(genIfNull){
					SeccondLayer_chunksLL[((x/16)*-1)+1][y/16]= new Chunk(new int[16][16], new float[16][16], true);
				}else{
					return null;
				}
			}
			return SeccondLayer_chunksLL[((x/16)*-1)+1][y/16];
		}
		case 3:
			if(x>=0&&y>=0){
				// + + Lower right
				if(ThirdLayer_chunksLR[(x/16)][(y/16)]==null){
					if(genIfNull){
						ThirdLayer_chunksLR[(x/16)][(y/16)] = new Chunk(new int[16][16], new float[16][16], true);
					}else{
						return null;
					}
				}
				return ThirdLayer_chunksLR[x/16][y/16];
			}else if(x>=0&&y<0){
				//+ - upper right
				if(ThirdLayer_chunksUR[x/16][((y/16)*-1)+1]==null){
					if(genIfNull){
						ThirdLayer_chunksUR[x/16][((y/16)*-1)+1]= new Chunk(new int[16][16], new float[16][16], true);
					}else{
						return null;
					}
				}
				return ThirdLayer_chunksUR[x/16][((y/16)*-1)+1];
			}else if(x<0&y<0){
				//- - upper leftmm
				if(ThirdLayer_chunksUL[((x/16)*-1)+1][((y/16)*-1)+1]==null){
					if(genIfNull){
						ThirdLayer_chunksUL[((x/16)*-1)+1][((y/16)*-1)+1]= new Chunk(new int[16][16], new float[16][16], true);
					}else{
						return null;
					}
				}
				return ThirdLayer_chunksUL[((x/16)*-1)+1][((y/16)*-1)+1];
			}else{
				//- + lower left
				if(ThirdLayer_chunksLL[((x/16)*-1)+1][y/16]==null){
					if(genIfNull){
						ThirdLayer_chunksLL[((x/16)*-1)+1][y/16]= new Chunk(new int[16][16], new float[16][16], true);
					}else{
						return null;
					}
				}
				return ThirdLayer_chunksLL[((x/16)*-1)+1][y/16];
			}
		case 4:
			if(x>=0&&y>=0){
				// + + Lower right
				if(StichedMap_chunksLR[(x/16)][(y/16)]==null){
					if(genIfNull){
						StichedMap_chunksLR[(x/16)][(y/16)]= new Chunk(new int[16][16], new float[16][16], true);
					}else{
						return null;
					}
				}
				return StichedMap_chunksLR[x/16][y/16];
			}else if(x>=0&&y<0){
				//+ - upper right
				if(StichedMap_chunksUR[x/16][((y/16)*-1)+1]==null){
					if(genIfNull){
						StichedMap_chunksUR[x/16][((y/16)*-1)+1]= new Chunk(new int[16][16], new float[16][16], true);
					}else{
						return null;
					}
				}
				return StichedMap_chunksUR[x/16][((y/16)*-1)+1];
			}else if(x<0&y<0){
				//- - upper left
				if(StichedMap_chunksUL[((x/16)*-1)+1][((y/16)*-1)+1]==null){
					if(genIfNull){
						StichedMap_chunksUL[((x/16)*-1)+1][((y/16)*-1)+1]= new Chunk(new int[16][16], new float[16][16], true);
					}else{
						return null;
					}
				}
				return StichedMap_chunksUL[((x/16)*-1)+1][((y/16)*-1)+1];
			}else{
				//- + lower left
				if(StichedMap_chunksLL[((x/16)*-1)+1][y/16]==null){
					if(genIfNull){
						StichedMap_chunksLL[((x/16)*-1)+1][y/16]= new Chunk(new int[16][16], new float[16][16], true);
					}else{
						return null;
					}
				}
				return StichedMap_chunksLL[((x/16)*-1)+1][y/16];
			}
		}
		return null;
	}
	
	public void unloadChunk(int x, int y,int vue) {
		switch(vue){
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		}
		if(x>=0&&y>=0){
			// + + Lower right
			if(FirstLayer_chunksLR[x/16][y/16]==null){
				return;
			}
			FirstLayer_chunksLR[x/16][y/16].Unload();
		}else if(x>=0&&y<0){
			//+ - upper right
			if(FirstLayer_chunksUR[x/16][y/16]==null){
				return;
			}
			FirstLayer_chunksUR[x/16][y/16].Unload();
		}else if(x<0&y<0){
			//- - upper left
			if(FirstLayer_chunksUL[x/16][y/16]==null){
				return;
			}
			FirstLayer_chunksUL[x/16][y/16].Unload();
		}else{
			//- + lower left
			if(FirstLayer_chunksLL[x/16][y/16]==null){
				return;
			}
			FirstLayer_chunksLL[x/16][y/16].Unload();
		}
	}
	
	public void loadChunk(int x, int y,int vue) {
		switch(vue){
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		}
		if(x>=0&&y>=0){
			// + + Lower right
			if(FirstLayer_chunksLR[x/16][y/16]==null){
				return;
			}
			FirstLayer_chunksLR[x/16][y/16].Load();
		}else if(x>=0&&y<0){
			//+ - upper right
			if(FirstLayer_chunksUR[x/16][y/16]==null){
				return;
			}
			FirstLayer_chunksUR[x/16][y/16].Load();
		}else if(x<0&y<0){
			//- - upper left
			if(FirstLayer_chunksUL[x/16][y/16]==null){
				return;
			}
			FirstLayer_chunksUL[x/16][y/16].Load();
		}else{
			//- + lower left
			if(FirstLayer_chunksLL[x/16][y/16]==null){
				return;
			}
			FirstLayer_chunksLL[x/16][y/16].Load();
		}
	}

	
	
	private int[][] generateTarrain(float[][] frequancy){
		int[][] newTileIDs = new int[frequancy.length][frequancy[0].length];
		int width = frequancy.length;
		int height = frequancy[0].length;
		for(int x = 0;x< width; x++){
			for(int y = 0;y< height; y++){
				if(frequancy[x][y]<0.00F){
					if(frequancy[x][y]<-0.35F){
						newTileIDs[x][y] = Tile.DEEP_WATER.getID();
					}else{
						newTileIDs[x][y] = Tile.WATER.getID();
					}
					
				}else{
					if(frequancy[x][y]<0.15F){
						newTileIDs[x][y] = Tile.SAND.getID();
					}else if(frequancy[x][y]>1.2F){
						newTileIDs[x][y] = Tile.STONE.getID();
					}else{
						newTileIDs[x][y] = Tile.GRASS.getID();
					}
				}
			}
		}
		return newTileIDs;
	}

	public Tile getTile(int x, int y,int vue) {
		Chunk chunk = getChunk(x, y,true,vue);
		switch(vue){
		case 1:
			if(chunk==null){
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
		case 2:
			if(chunk==null){
				return null;
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
		case 3:
			if(chunk==null){
				return null;
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
		case 4:
			if(chunk==null){
				return null;
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
		return null;
	}
	public void setTile(int x, int y,int id,int vue) {
		Chunk chunk = getChunk(x, y,true,vue);
		switch(vue){
		case 1:
			if(chunk==null){
				System.out.println("Generating void tiles when i should not be (Crash protection)");
				return;
			}
			if(x>=0&&y>=0){
				// + + Lower right
				chunk.setTile(x%16, y%16,id);
			}else if(x>=0&&y<0){
				//+ - upper right
				chunk.setTile(x%16, (15-(y%-16)*-1),id);
			}else if(x<0&y<0){
				//- - upper left
				chunk.setTile((15-(x%-16)*-1), 15-((y%-16)*-1),id);
			}else{
				//- + lower left
				chunk.setTile(15-((x%-16)*-1), y%16,id);
			}
			stitchMap(x, y);
			break;
		case 2:
			if(chunk==null){
				System.out.println("Generating void tiles when i should not be (Crash protection)");
				return;
			}
			if(x>=0&&y>=0){
				// + + Lower right
				chunk.setTile(x%16, y%16,id);
			}else if(x>=0&&y<0){
				//+ - upper right
				chunk.setTile(x%16, (15-(y%-16)*-1),id);
			}else if(x<0&y<0){
				//- - upper left
				chunk.setTile((15-(x%-16)*-1), 15-((y%-16)*-1),id);
			}else{
				//- + lower left
				chunk.setTile(15-((x%-16)*-1), y%16,id);
			}
			stitchMap(x, y);
			break;
		case 3:
			if(chunk==null){
				System.out.println("Generating void tiles when i should not be (Crash protection)");
				return;
			}
			if(x>=0&&y>=0){
				// + + Lower right
				chunk.setTile(x%16, y%16,id);
			}else if(x>=0&&y<0){
				//+ - upper right
				chunk.setTile(x%16, (15-(y%-16)*-1),id);
			}else if(x<0&y<0){
				//- - upper left
				chunk.setTile((15-(x%-16)*-1), 15-((y%-16)*-1),id);
			}else{
				//- + lower left
				chunk.setTile(15-((x%-16)*-1), y%16,id);
			}
			stitchMap(x, y);
			break;
		case 4:
			if(chunk==null){
				System.out.println("Generating void tiles when i should not be (Crash protection)");
				return;
			}
			if(x>=0&&y>=0){
				// + + Lower right
				chunk.setTile(x%16, y%16,id);
			}else if(x>=0&&y<0){
				//+ - upper right
				chunk.setTile(x%16, (15-(y%-16)*-1),id);
			}else if(x<0&y<0){
				//- - upper left
				chunk.setTile((15-(x%-16)*-1), 15-((y%-16)*-1),id);
			}else{
				//- + lower left
				chunk.setTile(15-((x%-16)*-1), y%16,id);
			}
			break;
		}
	}
	public float getTileFrequancy(int x, int y ,int vue) {
		switch(vue){
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		}
		Chunk chunk = getChunk(x, y,true,vue);
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
	public int getTileID(int x, int y,int vue) {
		switch(vue){
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		}
		Chunk chunk = getChunk(x, y,true,vue);
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
	public void stitchMap(int x, int y){
		if(getTile(x,y,3)!=null&&getTile(x,y,3)!=Tile.VOID){
			setTile(x,y,getTile(x,y,3).getID(),4);
		}else if(getTile(x,y,2)!=null&&getTile(x,y,2)!=Tile.VOID){
			setTile(x,y,getTile(x,y,2).getID(),4);
		}else if(getTile(x,y,1)!=null&&getTile(x,y,1)!=Tile.VOID){
			setTile(x,y,getTile(x,y,1).getID(),4);
		}
	}
	public void generateStructure(int x, int y,Structures s){
		if(hasSpace(x,y,s)){
			if(shouldSpawn(x, y, Tile.GRASS,s)){
				for(int xa = 0; xa<s.getWidth();xa++){
					for(int ya = 0; ya<s.getHeight();ya++){
						if(x>=0&&y>=0){
							// + + Lower right
							if(s.getIDArray()[xa+ya*s.getWidth()]!=0){
								Structure_chunkLR[(x+xa)/16][(y+ya)/16].setTile((xa+x)%16, (ya+y)%16, s.getIDArray()[xa+ya*s.getWidth()]);
								setTile(x+xa, y+ya, s.getIDArray()[xa+ya*s.getWidth()], s.getLevel()[xa+ya*s.getWidth()]);
							}
						}else if(x>=0&&y<0){
							//+ - upper right
							if(s.getIDArray()[xa+ya*s.getWidth()]!=0){
								Structure_chunkUR[(x+xa)/16][(((y+ya)/16)*-1)+1].setTile((xa+x)%16, (15-((y+ya)%-16)*-1), s.getIDArray()[xa+ya*s.getWidth()]);
								setTile(x+xa, y+ya, s.getIDArray()[xa+ya*s.getWidth()], s.getLevel()[xa+ya*s.getWidth()]);
							}
						}else if(x<0&y<0){
							//- - upper left
							if(s.getIDArray()[xa+ya*s.getWidth()]!=0){
								Structure_chunkUL[(((x+xa)/16)*-1)+1][(((y+ya)/16)*-1)+1].setTile((15-((x+xa)%-16)*-1), (15-((y+ya)%-16)*-1), s.getIDArray()[xa+ya*s.getWidth()]);
								setTile(x+xa, y+ya, s.getIDArray()[xa+ya*s.getWidth()], s.getLevel()[xa+ya*s.getWidth()]);
							}
						}else{
							//- + lower left
							if(s.getIDArray()[xa+ya*s.getWidth()]!=0){
								Structure_chunkLL[(((x+xa)/16)*-1)+1][(y+ya)/16].setTile((15-((x+xa)%-16)*-1), (ya+y)%16, s.getIDArray()[xa+ya*s.getWidth()]);
								setTile(x+xa, y+ya, s.getIDArray()[xa+ya*s.getWidth()], s.getLevel()[xa+ya*s.getWidth()]);
							}
						}
					}
				}
			}
		}
		
	}
	private boolean shouldSpawn(int x, int y, Tile req,Structures s){
		if(getTile(x+(s.getWidth()/2), (y+s.getHeight())-1, 1)==req){
			return true;
		}
		return false;
	}
	private boolean hasSpace(int x, int y,Structures s){
		for(int xa = 0; xa<s.getWidth();xa++){
			for(int ya = 0; ya<s.getHeight();ya++){
				if(x+xa>=0&&y+ya>=0){
					// + + Lower right
					if(Structure_chunkLR[(x+xa)/16][(y+ya)/16]==null){
						Structure_chunkLR[(x+xa)/16][(y+ya)/16] = new Chunk(new int[16][16], new float[16][16], true);
					}
					if(Structure_chunkLR[(x+xa)/16][(y+ya)/16].getTile((x+xa)%16,(y+ya)%16).getID()!=0){
						return false;
					}
				}else if(x+xa>=0&&y+ya<0){
					//+ - upper right
					if(Structure_chunkUR[(x+xa)/16][(((y+ya)/16)*-1)+1]==null){
						Structure_chunkUR[(x+xa)/16][(((y+ya)/16)*-1)+1] = new Chunk(new int[16][16], new float[16][16], true);
					}
					if(Structure_chunkUR[(x+xa)/16][(((y+ya)/16)*-1)+1].getTile((x+xa)%16,15-((y+ya)%16)*-1).getID()!=0){
						return false;
					}
				}else if(x+xa<0&y+ya<0){
					//- - upper left
					if(Structure_chunkUL[(((x+xa)/16)*-1)+1][(((y+ya)/16)*-1)+1]==null){
						Structure_chunkUL[(((x+xa)/16)*-1)+1][(((y+ya)/16)*-1)+1] = new Chunk(new int[16][16], new float[16][16], true);
					}
					if(Structure_chunkUL[(((x+xa)/16)*-1)+1][(((y+ya)/16)*-1)+1].getTile(15-((x+xa)%16)*-1,15-((y+ya)%16)*-1).getID()!=0){
						return false;
					}
				}else{
					//- + lower left
					if(Structure_chunkLL[(((x+xa)/16)*-1)+1][(y+ya)/16]==null){
						Structure_chunkLL[(((x+xa)/16)*-1)+1][(y+ya)/16] = new Chunk(new int[16][16], new float[16][16], true);
					}
					if(Structure_chunkLL[(((x+xa)/16)*-1)+1][(y+ya)/16].getTile(15-(((x+xa)%16)*-1),(y+ya)%16).getID()!=0){
						return false;
					}
				}
			}
		}
		return true;
	}
}
