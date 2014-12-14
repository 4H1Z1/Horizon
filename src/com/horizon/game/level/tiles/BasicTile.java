package com.horizon.game.level.tiles;

import com.horizon.game.gfx.Screen;
import com.horizon.game.level.Level;

public class BasicTile extends Tile{

	protected int tileID;
	
	
	
	public BasicTile(int ID,int x, int y,boolean isSolid,boolean isLiquid,boolean isEmmiter,String name, int baseColour) {
		super(ID, isSolid, isLiquid,isEmmiter,name,baseColour);
		this.tileID = x+y*20;
	}

	@Override
	public void render(Screen screen, Level level, int x, int y) {
		screen.render(x, y, tileID);
	}

}
