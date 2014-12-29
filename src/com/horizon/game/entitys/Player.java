package com.horizon.game.entitys;

import java.awt.Rectangle;

import com.horizon.game.CurrentScreen;
import com.horizon.game.InputHandler;
import com.horizon.game.horizon;
import com.horizon.game.gfx.Screen;
import com.horizon.game.inventory.Inventory;
import com.horizon.game.level.Level;
import com.horizon.game.level.tiles.Tile;

public class Player extends Mob {

	private InputHandler input;
	private int direction = 0;
	private int moveTick = 0;
	private int moveTickCap = 15;
	private int movePos = 0;
	private int runningModifier = 30;
	public Inventory inventory;
	public Player(Level lever, int x, int y, InputHandler input,int gender) {
		super(lever, "Player", x, y, 1,gender);
		this.gender = gender;
		//male = 0 female = 1
		this.input = input;
		this.inventory = new Inventory(27);
	}

	@Override
	public boolean hasColided(int xa, int ya) {
		//set to 8 wide so wont scrape sides going into doors
		Rectangle playerBox = new Rectangle((x-10)+xa, (y-12)+ya, 20, 32);
		//System.out.println(playerBox.x+" "+(playerBox.x+playerBox.width));
		if(xa>0){
			//right
			int posibleSolids = 2;
			if(playerBox.y%16>2||playerBox.y%16<13){
				posibleSolids=3;
			}
			for(int i=0;i<posibleSolids;i++){
				int xTile = (playerBox.x/16);
				int yTile = (playerBox.y/16)+i;
				if(x<0){
					xTile=xTile-1;
				}
				if(y<0){
					yTile=yTile-1;
				}
				if(level.map.getTile(xTile+2, yTile, 2)!=null&&level.map.getTile(xTile+2, yTile	, 2)!=Tile.VOID){
					return true;
				}
			}
		}else if(xa<0){
			//left
			int posibleSolids = 2;
			if(playerBox.y%16>2||playerBox.y%16<13){
				posibleSolids=3;
			}
			for(int i=0;i<posibleSolids;i++){
				int xTile = (playerBox.x/16);
				int yTile = (playerBox.y/16)+i;
				if(x<0){
					xTile=xTile-1;
				}
				if(y<0){
					yTile=yTile-1;
				}
				if(level.map.getTile(xTile, yTile, 2)!=null&&level.map.getTile(xTile, yTile	, 2)!=Tile.VOID){
					return true;
				}
			}
		}else if(ya>0){
			//down
			int posibleSolids = 2;
			if(playerBox.x%16>2||playerBox.x%16<13){
				posibleSolids=3;
			}
			for(int i=0;i<posibleSolids;i++){
				int xTile = (playerBox.x/16)+i;
				int yTile = playerBox.y/16;
				if(x<0){
					xTile=xTile-1;
				}
				if(y<0){
					yTile=yTile-1;
				}
				if(level.map.getTile(xTile, yTile+2, 2)!=null&&level.map.getTile(xTile, yTile+2	, 2)!=Tile.VOID){
					return true;
				}
			}
		}else if(ya<0){
			//up
			int posibleSolids = 2;
			if(playerBox.x%16>2||playerBox.x%16<13){
				posibleSolids=3;
			}
			for(int i=0;i<posibleSolids;i++){
				int xTile = (playerBox.x/16)+i;
				int yTile = playerBox.y/16;
				if(x<0){
					xTile=xTile-1;
				}
				if(y<0){
					yTile=yTile-1;
				}
				if(level.map.getTile(xTile, yTile, 2)!=null&&level.map.getTile(xTile, yTile, 2)!=Tile.VOID){
					return true;
				}
			}
		}
		//System.out.println(xTile+" "+yTile+" "+xRemander+" "+yRemander);
		return false;
	}

	@Override
	public void tick() {
		int xa = 0;
		int ya = 0;
		moveTick++;
		if (horizon.currentScreen == CurrentScreen.GAME) {
			if (input.up.isPressed()) {
				if(!hasColided(0,-1)){
					if(input.shift.isPressed()){
						if(isSwimming){
							ya-=(runningModifier/2);
							moveTickCap = 7;
						}else{
							ya-=runningModifier;
							moveTickCap = 7;	
						}
					}else{
						if(isSwimming){
							ya--;
							moveTickCap = 7*runningModifier;
						}else{
							ya--;
							moveTickCap = 7*runningModifier;	
						}
					}
				}
				direction = 3;
			}
			if (input.down.isPressed()) {
				if(!hasColided(0,1)){
					if(input.shift.isPressed()){
						if(isSwimming){
							ya+=(runningModifier/2);
							moveTickCap = 7;
						}else{
							ya+=runningModifier;
							moveTickCap = 7;	
						}
					}else{
						if(isSwimming){
							ya++;
							moveTickCap = 7*runningModifier;
						}else{
							ya++;
							moveTickCap = 7*runningModifier;
						}
					}
				}
				direction = 0;
			}
			if (input.left.isPressed()) {
				if(!hasColided(-1,0)){
					if(input.shift.isPressed()){
						if(isSwimming){
							xa-=(runningModifier/2);
							moveTickCap = 7;
						}else{
							xa-=runningModifier;
							moveTickCap = 7;
						}
					}else{
						if(isSwimming){
							xa--;
							moveTickCap = 7*runningModifier;
						}else{
							xa--;
							moveTickCap = 7*runningModifier;
						}
					}
				}
				direction = 1;
			}
			if (input.right.isPressed()) {
				if(!hasColided(1,0)){
					if(input.shift.isPressed()){
						if(isSwimming){
							xa+=(runningModifier/2);
							moveTickCap = 7;
						}else{
							xa+=runningModifier;
							moveTickCap = 7;
						}
					}else{
						if(isSwimming){
							xa++;
							moveTickCap = 7*runningModifier;
						}else{
							xa++;
							moveTickCap = 7*runningModifier;
						}
					}
				}
				direction = 2;
			}
			if (xa != 0 || ya != 0) {
				move(xa, ya);
				isMoving = true;
			} else {
				isMoving = false;
			}
		}
		if(moveTick>moveTickCap){
			moveTick = 0;
			if(movePos==1){
				movePos=0;
			}else{
				movePos=1;
			}
		}
	}

	@Override
	public void render(Screen screen) {
		// x and y for the top left block of the charicter
		int xTile = 0;
		int yTile = 12;
		int modifier = 16;
		int xOffset = x - modifier / 2;
		int yOffset = y - modifier / 2 - 4;
		if(gender==0){
			if(isMoving){
				if(isSwimming){
					//if(currentTile!=Tile.DEEP_WATER){
						if(movePos==1){
							screen.render(xOffset, yOffset+(modifier/2), xTile + (yTile+(direction*2)) * 20);
							screen.render(xOffset + modifier, yOffset+(modifier/2), (xTile + 1) + (yTile+(direction*2)) * 20);
						}else{
							screen.render(xOffset, yOffset+(modifier/2), (xTile+4) + (yTile+(direction*2)) * 20);
							screen.render(xOffset + modifier, yOffset+(modifier/2), (xTile + 5) + (yTile+(direction*2)) * 20);
						}
					//}
				}else{
					if(movePos==1){
						screen.render(xOffset, yOffset, xTile + (yTile+(direction*2)) * 20);
						screen.render(xOffset + modifier, yOffset, (xTile + 1) + (yTile+(direction*2)) * 20);
						screen.render(xOffset, yOffset + modifier, xTile + (yTile + 1+(direction*2)) * 20);
						screen.render(xOffset + modifier, yOffset + modifier, (xTile + 1) + (yTile + 1+(direction*2)) * 20);
					}else{
						screen.render(xOffset, yOffset, (xTile+4) + (yTile+(direction*2)) * 20);
						screen.render(xOffset + modifier, yOffset, (xTile + 5) + (yTile+(direction*2)) * 20);
						screen.render(xOffset, yOffset + modifier, (xTile+4) + (yTile + 1+(direction*2)) * 20);
						screen.render(xOffset + modifier, yOffset + modifier, (xTile + 5) + (yTile + 1+(direction*2)) * 20);
					}
				}
			}else{
				if(isSwimming){
					//if(currentTile!=Tile.DEEP_WATER){
						screen.render(xOffset, yOffset+(modifier/2), (xTile+2) + (yTile+(direction*2)) * 20);
						screen.render(xOffset + modifier, yOffset+(modifier/2), (xTile + 3) + (yTile+(direction*2)) * 20);
					//}
				}else{
					screen.render(xOffset, yOffset, (xTile+2) + (yTile+(direction*2)) * 20);
					screen.render(xOffset + modifier, yOffset, (xTile + 3) + (yTile+(direction*2)) * 20);
					screen.render(xOffset, yOffset + modifier, (xTile+2) + (yTile + 1+(direction*2)) * 20);
					screen.render(xOffset + modifier, yOffset + modifier, (xTile + 3) + (yTile + 1+(direction*2)) * 20);
				}
			}
		}else{
			if(isMoving){
				if(isSwimming){
					//if(currentTile!=Tile.DEEP_WATER){
						if(movePos==1){
							screen.render(xOffset, yOffset+(modifier/2), (xTile+6) + (yTile+(direction*2)) * 20);
							screen.render(xOffset + modifier, yOffset+(modifier/2), (xTile + 7) + (yTile+(direction*2)) * 20);
						}else{
							screen.render(xOffset, yOffset+(modifier/2), (xTile+10) + (yTile+(direction*2)) * 20);
							screen.render(xOffset + modifier, yOffset+(modifier/2), (xTile + 11) + (yTile+(direction*2)) * 20);
						}
					//}
				}else{
					if(movePos==1){
						screen.render(xOffset, yOffset, (xTile+6) + (yTile+(direction*2)) * 20);
						screen.render(xOffset + modifier, yOffset, (xTile + 7) + (yTile+(direction*2)) * 20);
						screen.render(xOffset, yOffset + modifier, (xTile+6) + (yTile + 1+(direction*2)) * 20);
						screen.render(xOffset + modifier, yOffset + modifier, (xTile + 7) + (yTile + 1+(direction*2)) * 20);
					}else{
						screen.render(xOffset, yOffset, (xTile+10) + (yTile+(direction*2)) * 20);
						screen.render(xOffset + modifier, yOffset, (xTile + 11) + (yTile+(direction*2)) * 20);
						screen.render(xOffset, yOffset + modifier, (xTile+10) + (yTile + 1+(direction*2)) * 20);
						screen.render(xOffset + modifier, yOffset + modifier, (xTile + 11) + (yTile + 1+(direction*2)) * 20);
					}
				}
			}else{
				if(isSwimming){
					//if(currentTile!=Tile.DEEP_WATER){
						screen.render(xOffset, yOffset+(modifier/2), (xTile+8) + (yTile+(direction*2)) * 20);
						screen.render(xOffset + modifier, yOffset+(modifier/2), (xTile + 9) + (yTile+(direction*2)) * 20);
					//}
				}else{
					screen.render(xOffset, yOffset, (xTile+8) + (yTile+(direction*2)) * 20);
					screen.render(xOffset + modifier, yOffset, (xTile + 9) + (yTile+(direction*2)) * 20);
					screen.render(xOffset, yOffset + modifier, (xTile+8) + (yTile + 1+(direction*2)) * 20);
					screen.render(xOffset + modifier, yOffset + modifier, (xTile + 9) + (yTile + 1+(direction*2)) * 20);
				}
			}
		}
		
	}
}
