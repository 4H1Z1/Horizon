package com.horizon.game.entitys;

import com.horizon.game.level.Level;
import com.horizon.game.level.tiles.Tile;

public abstract class Mob extends Entity{

	protected String name;
	protected int speed;
	protected int numSteps;
	protected boolean isMoving;
	protected int movingDirection =1;
	protected int scale;
	protected boolean isSwimming;
	protected int gender;
	protected Tile currentTile;
	protected Level level;
	protected int health;
	public Mob(Level level,String name, int x, int y,int speed, int gender) {
		super(level);
		this.speed = speed;
		this.name = name;
		this.x=x;
		this.y=y;
		this.gender = gender;
		this.isSwimming = false;
		this.currentTile = Tile.GRASS;
		this.level = level;
		this.health = 1000;
	}
	public void move(int xa, int ya){
		numSteps++;
		if(!hasColided(xa,ya)){
			if(ya<0){
				movingDirection =0;
			}
			if(ya>0){
				movingDirection =1;
			}
			if(xa<0){
				movingDirection =2;
			}
			if(xa>0){
				movingDirection =3;
			}
			x+=xa*speed;
			y+=ya*speed;
		}
		if(level.map.getTile(x/16, y/16,1)!=null){
			currentTile = level.map.getTile(x/16, y/16,1);
		}
		if(level.map.getTile(x/16, y/16,1)!=null&&level.map.getTile(x/16, y/16,1)==Tile.WATER||level.map.getTile(x/16, y/16,1)!=null&&level.map.getTile(x/16, y/16,1)==Tile.DEEP_WATER){
			isSwimming=true;
		}else{
			isSwimming=false;
		}
	}
	public abstract boolean hasColided(int xa, int ya);
	
	public String getName(){
		return name;
	}
	public int getHealth(){
		return health;
	}
	public void doDamage(int damage){
		health-=damage;
	}
}
