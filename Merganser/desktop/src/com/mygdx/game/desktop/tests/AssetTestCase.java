package com.mygdx.game.desktop.tests;

import org.junit.After;
import org.junit.Before;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

import junit.framework.*;

public class AssetTestCase extends TestCase {

	protected static MyGdxGame game;
	private static LwjglApplication app;

	@Before
	public void setUp() {
		
		// use same LwjglApplication window across all AssetTestCase tests
		if (app != null)
			return;
		
		game = new MyGdxGame();

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Merganser";
		config.height = 270;
		config.width = 480;
		config.resizable = true;
		app = new LwjglApplication(game, config);
		
		// don't run the test until the assetManager has been initialised.
		while (!game.created) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
