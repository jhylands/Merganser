package com.mygdx.test.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import junit.framework.*;

public class AssetManagement extends TestCase {
	
	private AssetManager TestAssetManager = new AssetManager();
	
	public void setup(){
		
	}
	
	public void testAssets(){
		TestAssetManager.load("bio-lab-0-r.png", Texture.class);
		TestAssetManager.finishLoading();
		assertTrue((TestAssetManager.get("bio-lab-0-r.png", Texture.class)) instanceof Texture);
		 
	}

	

}
