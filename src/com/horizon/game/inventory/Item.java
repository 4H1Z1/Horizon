package com.horizon.game.inventory;

public enum Item {
	Starter_Bow("Starter Bow",0,0,"You will need this adventurer",ItemType.Combat);
	protected String name;
	protected int x;
	protected int y;
	protected String description;
	protected ItemType type;
	Item(String name,int x, int y, String description,ItemType type){
		this.name = name;
		this.x =x;
		this.y = y;
		this.description = description;
		this.type = type;
	}
	public String getName(){
		return name;
	}
	public String getDescription(){
		return description;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public ItemType getItemType(){
		return type;
	}
}
