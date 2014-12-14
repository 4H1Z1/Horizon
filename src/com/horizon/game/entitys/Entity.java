package com.horizon.game.entitys;

import com.horizon.game.gfx.Screen;
import com.horizon.game.level.Level;

public abstract class Entity {

	public int x,y;
	protected Level level;
	public Entity(Level lever){
		init(level);
	}
	public final void init(Level level){
		this.level = level;
	}
	public abstract void tick();
	
	public abstract void render(Screen screen);
}
