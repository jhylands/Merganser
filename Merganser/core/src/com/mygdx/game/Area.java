package com.mygdx.game;

public class Area {
	String name;
	Map[] maps;
	
	public Area(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public Map[] getMaps() {
		return maps;
	}
	
	public void addMap(Map map){
//		this.maps.append(map);
	}
	
	public void setMaps(Map[] maps) {
		this.maps = maps;
	}

}
