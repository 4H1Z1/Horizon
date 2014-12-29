package com.horizon.game.map;

public enum Biome {
	Ocean("Ocean"),
	Desert("Desert"),
	Tundra("Tundra"),
	Forest("Forest"),
	GrassLand("GrassLand"),
	WoodLand("WoodLand"),
	SeasonalForest("WoodLand"),
	RainForest("RainForest"),
	Savana("Savana");
	private String name;
	Biome(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
}
