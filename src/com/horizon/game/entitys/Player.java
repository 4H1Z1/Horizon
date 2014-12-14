package com.horizon.game.entitys;

import com.horizon.game.CurrentScreen;
import com.horizon.game.InputHandler;
import com.horizon.game.horizon;
import com.horizon.game.gfx.Colours;
import com.horizon.game.gfx.Screen;
import com.horizon.game.level.Level;
import com.horizon.game.level.tiles.Tile;

public class Player extends Mob {

	private InputHandler input;
	private int direction = 0;
	private int moveTick = 0;
	private int moveTickCap = 15;
	private int movePos = 0;
	private int runningModifier = 8;
	private Tile currentTile;
	public Player(Level lever, int x, int y, InputHandler input,int gender) {
		super(lever, "Player", x, y, 1,gender);
		this.gender = gender;
		//male = 0 female = 1
		this.input = input;
		this.currentTile = Tile.GRASS;
	}

	@Override
	public boolean hasColided(int xa, int ya) {
		return false;
	}

	@Override
	public void tick() {
		int xa = 0;
		int ya = 0;
		moveTick++;
		if (horizon.currentScreen == CurrentScreen.GAME) {
			if (input.up.isPressed()) {
				if(input.shift.isPressed()){
					ya-=runningModifier;
					moveTickCap = 7;
				}else{
					ya--;
					moveTickCap = 7*runningModifier;
				}
				direction = 3;
			}
			if (input.down.isPressed()) {
				if(input.shift.isPressed()){
					ya+=runningModifier;
					moveTickCap = 7;
				}else{
					ya++;
					moveTickCap = 7*runningModifier;
				}
				direction = 0;
			}
			if (input.left.isPressed()) {
				if(input.shift.isPressed()){
					xa-=runningModifier;
					moveTickCap = 7;
				}else{
					xa--;
					moveTickCap = 7*runningModifier;
				}
				
				direction = 1;
			}
			if (input.right.isPressed()) {
				if(input.shift.isPressed()){
					xa+=runningModifier;
					moveTickCap = 7;
				}else{
					xa++;
					moveTickCap = 7*runningModifier;
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
					if(movePos==1){
						screen.render(xOffset, yOffset+(modifier/2), xTile + (yTile+(direction*2)) * 20);
						screen.render(xOffset + modifier, yOffset+(modifier/2), (xTile + 1) + (yTile+(direction*2)) * 20);
					}else{
						screen.render(xOffset, yOffset+(modifier/2), (xTile+4) + (yTile+(direction*2)) * 20);
						screen.render(xOffset + modifier, yOffset+(modifier/2), (xTile + 5) + (yTile+(direction*2)) * 20);
					}
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
					screen.render(xOffset, yOffset+(modifier/2), (xTile+2) + (yTile+(direction*2)) * 20);
					screen.render(xOffset + modifier, yOffset+(modifier/2), (xTile + 3) + (yTile+(direction*2)) * 20);
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
					if(movePos==1){
						screen.render(xOffset, yOffset+(modifier/2), (xTile+6) + (yTile+(direction*2)) * 20);
						screen.render(xOffset + modifier, yOffset+(modifier/2), (xTile + 7) + (yTile+(direction*2)) * 20);
					}else{
						screen.render(xOffset, yOffset+(modifier/2), (xTile+10) + (yTile+(direction*2)) * 20);
						screen.render(xOffset + modifier, yOffset+(modifier/2), (xTile + 11) + (yTile+(direction*2)) * 20);
					}
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
					screen.render(xOffset, yOffset+(modifier/2), (xTile+8) + (yTile+(direction*2)) * 20);
					screen.render(xOffset + modifier, yOffset+(modifier/2), (xTile + 9) + (yTile+(direction*2)) * 20);
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
