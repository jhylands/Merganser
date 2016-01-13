package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.mygdx.input.MapLoader;
import com.mygdx.screens.GameScreen;
import com.mygdx.screens.MainMenuScreen;
import com.mygdx.screens.MapScreen;
import com.mygdx.screens.ObjectiveScreen;
import com.mygdx.sprite.PlayerDuck;
import com.mygdx.sprite.Repeatable;
import com.badlogic.gdx.math.Rectangle;

public class MyGdxGame extends Game {
	
	public Map currentMap;
	public Heart heart;
	public PlayerDuck duck;
	public BitmapFont myFont;
	public Repeatable[] badies;
	public GameScreen mainGame;
	public Map[] maps;
	private float SCREENWIDTH;
	private float SCREENHEIGHT;
	private Objective currentObjective;
	private AssetManager assetManager;
	private ArrayList<Objective> objectives;
	private MainMenuScreen mainMenu;
	private ObjectiveScreen objScreen;
	private MapScreen mapScreen;

	@Override
	public void create() {
		/**
		 * AssetManager implementation to allow loading of textures into game
		 */
		assetManager = new AssetManager();
		assetManager.load("GUI panel.png", Texture.class);
		assetManager.load("map1.png", Texture.class);
		assetManager.load("map2.png", Texture.class);
		assetManager.load("Heart_0.png", Texture.class);
		assetManager.load("Heart_1.png", Texture.class);
		assetManager.load("Heart_2.png", Texture.class);
		assetManager.load("Heart_3.png", Texture.class);
		assetManager.load("Heart_4.png", Texture.class);
		assetManager.finishLoading();
		
		// Init map - load map from XML Map loader
		maps = this.mapGeneration();
		
		// Set the current map
		setCurrentMap(maps[0]);
		
		// Create a new PlayerDuck - contains all of the information for the users duck
		duck = new PlayerDuck();
		
		// Create Hearts
		heart = new Heart(assetManager);
		
		setBadies(new Repeatable[1]);
		getBadies()[0] = new Repeatable(1);
		
		// SET ScreenWidth and ScreenHeight
		SCREENWIDTH = Gdx.graphics.getWidth();
		SCREENHEIGHT = Gdx.graphics.getHeight();
		
		// Create some test objectives into an ArrayList
		setObjectives(new ArrayList<Objective>());
		getObjectives().add(new Objective(this, maps[1], "Go outside", 100));
		getObjectives().add(new Objective(this, maps[0], "Go inside", 100));
		getObjectives().get(0).setNextObjective(getObjectives().get(1));
		getObjectives().get(1).setNextObjective(getObjectives().get(0));
		
		// Set current objective as objective1. Allows an objective for when the game starts
		setCurrentObjective(getObjectives().get(0));
		
		// FONT Generation for Score
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("COUR.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 14;
		parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:-";
		myFont = generator.generateFont(parameter);
		myFont.setColor(Color.WHITE);
		generator.dispose();
		
		// Create a GameScreen so we can reference to it from different screens
		setMainGame(new GameScreen(this));
		setMainMenu(new MainMenuScreen(this));
		setMapScreen(new MapScreen(this));
		setObjScreen(new ObjectiveScreen(this));
		
		
		// Set current screen to MainMenu Screen (screen that first loads)
		this.setScreen(getMainMenu());
	}



	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	public Map[] mapGeneration(){
		MapLoader loader = new MapLoader();
		Map[] maps = new Map[2];
		/*//map1
		MapFeature[] features = new MapFeature[1];
		features[0] = new MapFeature(new Rectangle().set(1000,1000,0,0),true,false,false);
		Portal[] portals = new Portal[1];
		portals[0] = new Portal(new Rectangle().set(100, 100, 100, 100),null);
		maps[0]= new Map(new Texture("bio-lab-1.png"),features,portals);
		//map2
		MapFeature[] features2 = new MapFeature[1];
		features2[0] = new MapFeature(new Rectangle().set(1000,1000,50,100),true,false,false);
		Portal[] portals2 = new Portal[1];
		portals2[0] = new Portal(new Rectangle().set(0,0,100,100),maps[0]);
		maps[1] = new Map(new Texture("bio-lab-0.png"),features2,portals2);
		
		maps[0].setPortalExit(0, maps[1]);*/
		return loader.loadXML("assets/test.xml");
		//Rectangle[] a = new Rectangle[1];
		//a[0] = new Rectangle()
		//map.setPortals([new Rectangle() ])
	}

	public PlayerDuck getDuck() {
		return duck;
	}
	
	public AssetManager getAssetManager() {
		return assetManager;
	}

	public float getScreenHeight() {
		return SCREENHEIGHT;
	}

	public float getScreenWidth() {
		return SCREENWIDTH;
	}

	public Map getCurrentMap() {
		return currentMap;
	}

	public void setCurrentMap(Map currentMap) {
		this.currentMap = currentMap;
	}

	public Objective getCurrentObjective() {
		return currentObjective;
	}

	public void setCurrentObjective(Objective currentObjective) {
		this.currentObjective = currentObjective;
	}

	public Repeatable[] getBadies() {
		return badies;
	}

	public void setBadies(Repeatable[] badies) {
		this.badies = badies;
	}

	public void setHeart(Heart heart) {
		this.heart = heart;
	}

	public GameScreen getMainGame() {
		return mainGame;
	}

	public void setMainGame(GameScreen mainGame) {
		this.mainGame = mainGame;
	}

	public ArrayList<Objective> getObjectives() {
		return objectives;
	}

	public void setObjectives(ArrayList<Objective> objectives) {
		this.objectives = objectives;
	}



	public MapScreen getMapScreen() {
		return mapScreen;
	}



	public void setMapScreen(MapScreen mapScreen) {
		this.mapScreen = mapScreen;
	}



	public ObjectiveScreen getObjScreen() {
		return objScreen;
	}



	public void setObjScreen(ObjectiveScreen objScreen) {
		this.objScreen = objScreen;
	}



	public MainMenuScreen getMainMenu() {
		return mainMenu;
	}



	public void setMainMenu(MainMenuScreen mainMenu) {
		this.mainMenu = mainMenu;
	}
	
}
