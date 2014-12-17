package com.horizon.game.level.tiles;

import com.horizon.game.gfx.Screen;
import com.horizon.game.level.Level;

public abstract class Tile {

	public static final Tile[] tiles = new Tile[256];
	// (id,x on sprite sheet, y on sprite sheet, colours)
	// (White,light gray, dark gray, black)
	public static final Tile VOID = new BasicTile(0, 0, 0, true, false, false, "Void", 0xff000000);
	public static final Tile STONE = new BasicTile(1, 1, 0,false, false, false, "Stone", 0xffC0C0C0);
	public static final Tile GRASS = new BasicTile(2, 2, 0, false, false, false, "Grass", 0xff4CFF00);
	public static final Tile WATER = new BasicTile(3, 3, 0, false, true, false, "Water", 0xff42A0FF);
	public static final Tile DEEP_WATER = new BasicTile(4, 3, 0, false, true, false, "Deep Water", 0xff3D40FF);
	public static final Tile OAK_LOG = new BasicTile(5, 4, 0, true, false, false, "Oak Log", 0xffC4A637);
	public static final Tile OAK_LEAVES = new BasicTile(6, 5, 0, true, false, false, "Oak Leaves", 0xff00C690);
	public static final Tile SAND = new BasicTile(7, 6, 0, true, false, false, "Sand", 0xffF3F781);

	protected byte id;
	protected boolean isSolid;
	// light source
	protected boolean isEmiter;
	private boolean isLiquid;
	private String name; 
	protected int baseColour;

	public Tile(int ID, boolean isSolid, boolean isLiquid, boolean isEmiter,
			String name, int baseColour) {
		this.id = (byte) ID;
		if (tiles[ID] != null) {
			throw new RuntimeException("Multiple tiles with same id, ID:" + id);
		}
		this.baseColour = baseColour;
		this.isSolid = isSolid;
		this.isEmiter = isEmiter;
		this.isLiquid = isLiquid;
		this.name = name;
		tiles[id] = this;
	}

	public byte getID() {
		return id;
	}

	public String getName() {
		return name;
	}

	public boolean isSolid() {
		return isSolid;
	}

	public int getBaseColour() {
		return baseColour;
	}

	public boolean isLightSource() {
		return isEmiter;
	}

	public boolean isLiquid() {
		return isLiquid;
	}

	public abstract void render(Screen screen, Level level, int x, int y);

	public static Tile getTileByID(int i) {
		for (Tile t : tiles) {
			if ((byte) i == t.getID()) {
				return t;
			}
		}
		return Tile.VOID;
	}
}
