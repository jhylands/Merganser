package com.mygdx.game.desktop.tests;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Map;
import com.mygdx.game.Portal;

import junit.framework.TestCase;

public class PortalTest extends TestCase {
	
	Portal portal;
	Map map;
	
	public void setUp(){
	}
	
	public void testGetExitMap(){
		portal = new Portal(new Rectangle(5, 5, 10, 10), 5, new Vector2(80, 100));
		assertEquals(5, portal.getRef());
	}
	
	public void testSetDestination(){
		portal = new Portal(new Rectangle(5, 5, 10, 10), 5, new Vector2(80, 100));
		map = new Map(null, null, null, null, null, null);
		portal.setDestination(map);
		assertEquals(map, portal.getDestination());
	}
	
	public void testGetExitLocalCoord(){
		portal = new Portal(new Rectangle(5, 5, 10, 10), 5, new Vector2(80, 100));
		assertEquals(new Vector2(80, 100), portal.getExitLocal());
	}

}
