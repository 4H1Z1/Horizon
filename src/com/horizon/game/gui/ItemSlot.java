package com.horizon.game.gui;

import java.awt.Color;
import java.awt.Graphics;


public class ItemSlot extends Components{
	
	int amountInRow;
	int padding;
	int boxWidth;
	int boxHeight;
	int borderPadding;
	public ItemSlot(int boxWidth, int boxHeight,int borderPadding,int amountInRow,int padding) {
		super((boxWidth+padding)*amountInRow,boxHeight);
		this.amountInRow = amountInRow;
		this.padding = padding;
		this.boxWidth = boxWidth;
		this.boxHeight = boxHeight;
		this.borderPadding = borderPadding;
	}

	@Override
	public void draw(Graphics g,int x, int y) {
		for(int i=0;i<amountInRow;i++){
			g.setColor(Color.DARK_GRAY);
			g.fillRoundRect(x+((boxWidth+padding)*i), y,boxWidth, boxHeight, 4, 4);
			g.setColor(Color.LIGHT_GRAY);
			g.fillRoundRect(x+borderPadding+((boxWidth+padding)*i), y+borderPadding, boxWidth-(borderPadding*2), boxHeight-(borderPadding*2), 4, 4);
		}
		
	}
	
}
