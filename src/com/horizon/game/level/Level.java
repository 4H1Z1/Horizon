package com.horizon.game.level;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.List;

import com.horizon.game.horizon;
import com.horizon.game.entitys.Entity;
import com.horizon.game.entitys.Player;
import com.horizon.game.gfx.Screen;
import com.horizon.game.level.tiles.Tile;
import com.horizon.game.map.MapData;
import com.horizon.game.map.MiniMap;

public class Level {
	
	public horizon horizon;
	public int seed;
	public MapData map;
	public List<Entity> entitys = new ArrayList<Entity>();
	public BufferedImage image;
	public Point recomendedSpawn;
	public MiniMap miniMap;
	private int mapWidth;
	private int mapHeight;
	private float time;
	private boolean isDay;
	
	public Level(horizon horizon,int seed) {
		this.seed = seed;
		map = new MapData(122112,this);
		/*for(int x = 0; x<20;x++){
			for(int y = 0; y<20; y++){
				map.generateChunk(x*16, y*16, true);
			}
		}*/
		image = new BufferedImage(horizon.getWidth(), horizon.getHeight(),BufferedImage.TYPE_INT_ARGB);	
		this.mapWidth = horizon.getWidth();
		this.mapHeight = horizon.getHeight();
		recomendedSpawn = new Point(0,0);
		miniMap = new MiniMap(80,80,recomendedSpawn.x,recomendedSpawn.y,this);
		time = 1F;
		isDay = true;
	}
	public String getTileType(int x, int y){
		if(getTile(x,y,4)==null){
			return Tile.VOID.getName();
		}
		int id = getTile(x, y,4).getID();
		return Tile.getTileByID(id).getName();
	}

	public void tick() {
		for (Entity e : entitys) {
			e.tick();
		}
	}
	public void timeTick(){
		if(isDay){
			//suposed to be 0.001F debuging atm
			time-=0.001F;
			if(time<0){
				isDay=false;
			}
		}else{
			time+=0.001F;
			if(time>0){
				isDay=true;
			}
		}
	}
	public void renderTiles(Screen screen, int xOffset, int yOffset,Player player,int vue) {
		screen.setOffset(xOffset, yOffset);
		for(int x = (player.x/16)-12;x<(player.x/16)+24;x++){
			for(int y = (player.y/16)-12;y<(player.y/16)+24;y++){
				if(getTile(x,y,vue)!=null&&getTile(x,y,vue)!=Tile.VOID){
					getTile(x, y,vue).render(screen, this, x << 4, y << 4);
				}
				if(map.getChunk(x, y, false,vue)!=null&&!map.getChunk(x, y, false,vue).isLoaded){
					map.getChunk(x, y, false,vue).Load();
				}
				map.stitchMap(x, y);
			}
		}
	}
	public void renderEntitys(Screen screen) {
		for (Entity e : entitys) {
			e.render(screen);
		}
	}

	public Tile getTile(int x, int y,int vue) {
		return map.getTile(x,y,vue);
	}

	public void addEntity(Entity entity) {
		this.entitys.add(entity);

	}
	public void refreshMap(int playerX, int playerY){
		int[] pixles = ((DataBufferInt) image.getRaster().getDataBuffer())
				.getData();
		int thickness = 1;
		int markerThickness = 2;
		int markerScale = 3;
		for(int x = 0; x<mapWidth/thickness;x++){
			for(int y = 0; y<mapWidth/thickness; y++){
				int xa = (playerX/16)-((mapWidth/thickness)/2);
				int ya = (playerY/16)-((mapHeight/thickness)/2);
				for(int t = 0; t<thickness;t++){
					for(int b = 0; b<thickness;b++){
						if(pixles.length>(x+t)+(y+b)*mapWidth){
							if(map.getChunk(xa+x,ya+y,true,1)!=null&&map.getChunk(xa+x,ya+y,false,1).isLoaded){
							//if(map.getChunk(xa+x,ya+y,false,4)!=null&&map.getChunk(xa+x,ya+y,false,4).isLoaded){
								pixles[(x+t)+(y+b)*mapWidth] = getTile(xa+x,ya+y,4).getBaseColour();
							}else{
								pixles[(x+t)+(y+b)*mapWidth] = 0x000000;
							}
							for(int i = 0; i< markerThickness; i++){
								if((x==((mapWidth/thickness)/2)-i&&y>((mapHeight/thickness)/2)-(markerThickness+markerThickness*markerScale)&&y<((mapHeight/thickness)/2)+(markerThickness+markerThickness*markerScale-markerThickness))||(y==((mapHeight/thickness)/2)-i&&x>((mapWidth/thickness)/2)-(markerThickness+markerThickness*markerScale)&&x<((mapWidth/thickness)/2)+(markerThickness+markerThickness*markerScale-markerThickness))){
									pixles[(x+t)+(y+b)*mapWidth] = 0x000000;
									break;
								}
							}
						}
					}
				}
			}
		}
	}
}
