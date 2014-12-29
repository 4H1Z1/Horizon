package com.horizon.game.inventory;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

	int size;
	Item[] items;
	public Inventory(int size){
		this.size = size;
		this.items = new Item[size];
	}
	public int getSize(){
		return size;
	}
	public Item getItem(int i){
		return items[i];
	}
	public Item[] getItemOfType(ItemType i){
		List<Item> list= new ArrayList<Item>();
		for(Item a:items){
			if(a!=null&&a.getItemType()==i){
				list.add(a);
			}
		}
		Item[] newItems = new Item[list.size()];
		for(int x = 0; x<list.size();x++){
			newItems[x] = list.get(x);
		}
		return newItems;
	}
	public Item[] getItems(){
		return items;
	}
	public void clear(){
		items = new Item[size];
	}
	public void removeItem(int i){
		if(items.length>i){
			items[i] = null;
		}
	}
	public void addItem(Item i){
		for(int x = 0; x<size;x++){
			if(items[x]==null){
				items[x]=i;
			}
		}
	}
	
}
