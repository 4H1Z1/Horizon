package com.horizon.game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class MouseInput implements MouseListener {
	
	horizon horizon;
	
	public MouseInput(horizon horizon){
		this.horizon = horizon;
	}

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    	if(com.horizon.game.horizon.currentScreen == CurrentScreen.GAME){
    		int xOffset = horizon.player.x - (horizon.screen.width / 2);
			int yOffset = horizon.player.y - (horizon.screen.height / 2);
			if (!(com.horizon.game.horizon.mouseCoords == null) && com.horizon.game.horizon.mouseCoords.x >= 0
					&& com.horizon.game.horizon.mouseCoords.y >= 0) {
				double x = 0, y = 0;
				if (xOffset <= 0) {
					x = com.horizon.game.horizon.mouseCoords.x / (16 * com.horizon.game.horizon.SCALE);
				} else {
					int holder = xOffset % 16;
					x = (com.horizon.game.horizon.mouseCoords.x + (holder * com.horizon.game.horizon.SCALE)) / (16 * com.horizon.game.horizon.SCALE)
							+ (xOffset / 16);
				}
				if (yOffset <= 0) {
					y = com.horizon.game.horizon.mouseCoords.y / (16 * com.horizon.game.horizon.SCALE);
				} else {
					int holder = yOffset % 16;
					y = (com.horizon.game.horizon.mouseCoords.y + (holder * com.horizon.game.horizon.SCALE)) / (16 * com.horizon.game.horizon.SCALE)
							+ (yOffset / 16);
				}
				//gets block click type
	    	}

		}
    }
}