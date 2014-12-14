package com.horizon.game.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.horizon.game.CurrentScreen;
import com.horizon.game.horizon;
import com.horizon.game.level.Level;

public class GUI {

	public static void renderOverlay(Graphics g, horizon horizon, Level level) {
		if (com.horizon.game.horizon.currentScreen == CurrentScreen.GAME) {
			g.drawImage(horizon.image, 0, 0, horizon.getWidth(),
					horizon.getHeight(), null);
			g.drawImage(horizon.level.miniMap.image, (horizon.getWidth()/4)*3, 10, (horizon.getWidth()/4)-10,
					horizon.getHeight()/4, null);
		} else if (com.horizon.game.horizon.currentScreen == CurrentScreen.MAP) {
			g.drawImage(horizon.level.image, 0, 0, horizon.getWidth(),
					horizon.getHeight(), null);
		} else if (com.horizon.game.horizon.currentScreen == CurrentScreen.INVENTORY) {
			g.drawImage(horizon.image, 0, 0, horizon.getWidth(),
					horizon.getHeight(), null);
			drawInventory(50,horizon.getWidth(),horizon.getHeight(),g);
		}
	}
	private static void drawInventory(int padding, int screenWidth, int screenHeight,Graphics g){
		int width = screenWidth-(padding*2);
		int height = screenHeight-(padding*2);
		g.setColor(Color.GRAY);
		g.fillRoundRect(padding, padding, width, height, 50, 50);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRoundRect(padding+(width/2), padding+10, (width/2)-10, height-20, 50, 50);
		g.setColor(Color.GRAY);
		g.fillRoundRect(padding+(width/2)+5, padding+15, (width/2)-20, height-30, 50, 50);
		g.setColor(Color.black);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g.drawString("Inventory", padding+(width/2)+50, padding+40);
		g.drawString("Volume 0/10.0L", padding+(width/2)+200, padding+40);
		g.drawString("Weight 0KG", padding+(width/2)+200, padding+60);
		Components.ItemSlot_Row.draw(g,(screenWidth-Components.ItemSlot_Row.getWidth())-(padding)-35,120);
	}
}
