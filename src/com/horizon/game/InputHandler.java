package com.horizon.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener{

	private horizon horizon;
	public InputHandler(horizon game){
		game.addKeyListener(this);
		this.horizon = game;
	}
	
	public class Key{
		private boolean isPressed = false;
		private int numTimesPressed = 0;
		
		public int getNumPressed(){
			return numTimesPressed;
		}
		
		public boolean isPressed(){
			return isPressed;
		}
		public void toggle(boolean isPressed){
			this.isPressed = isPressed;
			if(isPressed){
				numTimesPressed++;
			}
		}
	}
	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();
	public Key esc = new Key();
	public Key shift = new Key();
	public Key m = new Key();
	public Key e = new Key();
	public Boolean m_toggle = false;
	public Boolean e_toggle = false;
	@Override
	public void keyPressed(KeyEvent e) {
		toggleKey(e.getKeyCode(),true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		toggleKey(e.getKeyCode(),false);
		switch (e.getKeyCode()){
		case KeyEvent.VK_M:
			if(m_toggle){
				m_toggle=false;
			}else{
				m_toggle=true;
			}
			break;
		case KeyEvent.VK_E:
			if(e_toggle){
				e_toggle=false;
			}else{
				e_toggle=true;
			}
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	public void toggleKey(int keyCode,boolean isPressed){
		if(keyCode==KeyEvent.VK_ESCAPE){
			esc.toggle(isPressed);
			if(horizon.currentScreen==CurrentScreen.MAP||horizon.currentScreen==CurrentScreen.INVENTORY){
				m_toggle = false;
				e_toggle = false;
				horizon.currentScreen = CurrentScreen.GAME;
			}
		}
		if(keyCode==KeyEvent.VK_W||keyCode==KeyEvent.VK_UP){
			up.toggle(isPressed);
		}
		if(keyCode==KeyEvent.VK_A||keyCode==KeyEvent.VK_LEFT){
			left.toggle(isPressed);
		}
		if(keyCode==KeyEvent.VK_S||keyCode==KeyEvent.VK_DOWN){
			down.toggle(isPressed);
		}
		if(keyCode==KeyEvent.VK_D||keyCode==KeyEvent.VK_RIGHT){
			right.toggle(isPressed);
		}
		if(keyCode==KeyEvent.VK_SHIFT){
			shift.toggle(isPressed);
		}
		if(keyCode==KeyEvent.VK_M){
			m.toggle(isPressed);
			if(m_toggle){
				horizon.currentScreen=CurrentScreen.MAP;
				horizon.level.refreshMap(horizon.player.x, horizon.player.y);
			}else{
				horizon.currentScreen=CurrentScreen.GAME;
			}
		}
		if(keyCode==KeyEvent.VK_E){
			e.toggle(isPressed);
			if(e_toggle){
				horizon.currentScreen=CurrentScreen.INVENTORY;
			}else{
				horizon.currentScreen=CurrentScreen.GAME;
			}
		}
	}
}
