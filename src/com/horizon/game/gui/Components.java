package com.horizon.game.gui;

import java.awt.Graphics;


public abstract class Components {
	public static final ItemSlot ItemSlot = new ItemSlot(50,50,5,1,10);
	public static final ItemSlot ItemSlot_Row = new ItemSlot(50,50,5,6,10);
	private int width;
	private int height;
	public Components(int width, int height) {
		this.width = width;
		this.height = height;
		System.out.println(width+"");
	}
	public abstract void draw(Graphics g,int x, int y);
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
}
