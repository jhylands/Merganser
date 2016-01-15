package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.mygdx.input.MapLoader;
import com.mygdx.screens.EndScreen;
import com.mygdx.screens.GameScreen;
import com.mygdx.screens.MainMenuScreen;
import com.mygdx.screens.MapScreen;
import com.mygdx.screens.ObjectiveScreen;
import com.mygdx.sprite.PlayerDuck;
import com.mygdx.sprite.Repeatable;

/**
 * This class holds the initialisation of the game. When libgdx creates the game
 * using the desktop launcher the create function in this class is called.
 * 
 * @author james
 *
 */
public class MyGdxGame extends Game {

	public PlayerDuck duck;
	private Map currentMap;
	private Heart heart;
	private BitmapFont myFont;
	private Repeatable[] badies;
	private Map[] maps;
	private float SCREENWIDTH;
	private float SCREENHEIGHT;
	private Objective currentObjective;
	private AssetManager assetManager;
	private ArrayList<Objective> objectives;
	private GameScreen mainGame;
	private MainMenuScreen mainMenu;
	private ObjectiveScreen objScreen;
	private MapScreen mapScreen;
	private Stamina stamina;
	private boolean newObjective = true;
	private boolean lastObjComplete = false;

	/**
	 * Creates resources needed for the whole game
	 */
	@Override
	public void create() {
		/**
		 * AssetManager implementation to allow loading of textures into game at start
		 * for speed efficiency later in the game
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
		assetManager.load("bio-lab-0.png", Texture.class);
		assetManager.load("bio-lab-1.png", Texture.class);
		assetManager.load("large_duck.png", Texture.class);
		assetManager.load("large_duck_down.png", Texture.class);
		assetManager.load("large_duck_left.png", Texture.class);
		assetManager.load("large_duck_right.png", Texture.class);
		assetManager.load("goose_up.png", Texture.class);
		assetManager.load("goose_down.png", Texture.class);
		assetManager.load("goose_left.png", Texture.class);
		assetManager.load("goose_right.png", Texture.class);
		assetManager.load("objective.png", Texture.class);
		assetManager.load("outside.png", Texture.class);
		assetManager.load("duck_action_left.png", Texture.class);
		assetManager.load("duck_action_right.png", Texture.class);
		assetManager.load("duck_action_up.png", Texture.class);
		assetManager.load("duck_action_down.png", Texture.class);
		assetManager.load("goose_action_left.png", Texture.class);
		assetManager.load("goose_action_right.png", Texture.class);
		assetManager.load("goose_action_up.png", Texture.class);
		assetManager.load("goose_action_down.png", Texture.class);
		assetManager.finishLoading();

		// Init map - load map from XML Map loader
		maps = this.mapGeneration();
		

		// Set the current map
		setCurrentMap(maps[0]);

		// Create a new PlayerDuck - contains all of the information for the
		// users duck
		duck = new PlayerDuck(assetManager);
		
		//initiate stamina
		stamina =  new Stamina();
		
		// Create Hearts from Heart class
		setHeart(new Heart(assetManager));

		// Create a new repeatable object where enemies/ badies are stored in the code 
		setBadies(new Repeatable[1]);
		getBadies()[0] = new Repeatable(1, assetManager);

		// SET ScreenWidth and ScreenHeight
		SCREENWIDTH = Gdx.graphics.getWidth();
		SCREENHEIGHT = Gdx.graphics.getHeight();

		// Create some test objectives into an ArrayList
		setObjectives(new ArrayList<Objective>());
		try {
			// Add new objective to ArrayList
			getObjectives().add(new Objective(this, maps[2], "Go to outside biology", 100));
			getObjectives().add(new Objective(this, maps[0], "Go back to the biology lab", 100));
		} catch (IndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException("Can't create an objective with map greater than map list length");
		}
		try {
			//Sets next objectives of objectives in the ArrayList. 
			//If objective not set a nextObjective then automatically defined as LastObjective
			//Game will show Game Complete screen on lastObjective
//			getObjectives().get(0).setNextObjective(getObjectives().get(1));
			getObjectives().get(1).setNextObjective(getObjectives().get(0));
			
		} catch (IndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException("Can't set next objective to greater than objective ArrayList length");
		}
		
		

		// Set current objective as objective1. Allows an objective for when the
		// game starts
		setCurrentObjective(getObjectives().get(0));

		// FONT Generation for Score
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("COUR.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 14;
		parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:-";
		setMyFont(generator.generateFont(parameter));
		getMyFont().setColor(Color.WHITE);
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
		mainGame.dispose();
		mainMenu.dispose();
		objScreen.dispose();
		mapScreen.dispose();
		super.dispose();
	}

	/**
	 * Initializes the XML map loader
	 * Returns a Map[] based on the results from the XML Loader
	 * XML file needs to be stored in desktop assets directory as an external file
	 * Can find XML documentation on group website
	 * @return
	 */
	public Map[] mapGeneration() {
		MapLoader loader = new MapLoader(this);
		// Map[] maps = new Map[2];
		/*
		 * //map1 MapFeature[] features = new MapFeature[1]; features[0] = new
		 * MapFeature(new Rectangle().set(1000,1000,0,0),true,false,false);
		 * Portal[] portals = new Portal[1]; portals[0] = new Portal(new
		 * Rectangle().set(100, 100, 100, 100),null); maps[0]= new Map(new
		 * Texture("bio-lab-1.png"),features,portals); //map2 MapFeature[]
		 * features2 = new MapFeature[1]; features2[0] = new MapFeature(new
		 * Rectangle().set(1000,1000,50,100),true,false,false); Portal[]
		 * portals2 = new Portal[1]; portals2[0] = new Portal(new
		 * Rectangle().set(0,0,100,100),maps[0]); maps[1] = new Map(new
		 * Texture("bio-lab-0.png"),features2,portals2);
		 * 
		 * maps[0].setPortalExit(0, maps[1]);
		 */
		return loader.loadXML("assets/test.xml");
		// Rectangle[] a = new Rectangle[1];
		// a[0] = new Rectangle()
		// map.setPortals([new Rectangle() ])
	}

	// GETTERS AND SETTERS
	
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

	public Heart getHeart() {
		return heart;
	}

	public BitmapFont getMyFont() {
		return myFont;
	}

	public void setMyFont(BitmapFont myFont) {
		this.myFont = myFont;
	}

	public Stamina getStamina() {
		return this.stamina;
	}

	public boolean isNewObjective() {
		return newObjective;
	}

	public void setNewObjective(boolean newObjective) {
		this.newObjective = newObjective;
	}

	public boolean isLastObjComplete() {
		return lastObjComplete;
	}

	public void setLastObjComplete(boolean lastObjComplete) {
		this.lastObjComplete = lastObjComplete;
	}

}
