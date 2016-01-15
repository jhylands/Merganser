package com.mygdx.input;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Map;
import com.mygdx.game.MapFeature;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Portal;
import com.mygdx.sprite.Repeatable;



public class MapLoader {

	//required for asset manager to hold new background images
	private MyGdxGame game;

	public MapLoader(MyGdxGame game) {
		this.game = game;
	}

	public Map[] loadXML(String file) {
		//initiate the list of maps
		Map[] arrMaps;
		try {
			// create a file connection to pass to the SaxBuilder
			File inputFile = new File(file);
			// define a new saxBuilder
			SAXBuilder saxBuilder = new SAXBuilder();
			// get the saxBuilder to create a document from the file connection
			Document document = saxBuilder.build(inputFile);
			// get a list of the maps contained in the maps file
			List<Element> maps = document.getRootElement().getChildren();
			// create Structure to hold the maps (needs to be update to AVL tree
				// for faster linking but currently uses integer references which
				// are faster anyway even if less user friendly)
			arrMaps = new Map[maps.size()];

			// iterate through the maps
			for (int mapIterator = 0; mapIterator < maps.size(); mapIterator++) {
				Element map = maps.get(mapIterator);
				

				MapFeature[] arrFeatures;
				// check if there are any mapFeatures if not arrFeatures is an empty array
				if (map.getChild("MapFeatures") != null) {
					// get list of mapFeatures
					List<Element> features = map.getChild("MapFeatures").getChildren();
					// initiate the array of MapFeatures to be passed to the map
					arrFeatures = new MapFeature[features.size()];
					// parse the xml into MapFeature type
					for (int featureIterator = 0; featureIterator < features.size(); featureIterator++) {
						//check if feature contains random position 
							//if it does implement the random feature so a different elm2Mapfeature function 
						//else
							arrFeatures[featureIterator] = elm2MapFeature(features.get(featureIterator));
					}
				} else {
					arrFeatures = new MapFeature[0];
				}
				Portal[] arrPortals;
				//check if there any portals if not arrPortals is empty array
				if (map.getChild("MapPortals") != null) {
					// get list of portals
					List<Element> portals = map.getChild("MapPortals").getChildren();
					arrPortals = new Portal[portals.size()];
					for (int portalIterator = 0; portalIterator < portals.size(); portalIterator++) {
						arrPortals[portalIterator] = elm2Portal(portals.get(portalIterator));
					}
				} else {
					arrPortals = new Portal[0];
				}
				// add this map to the maps array
				Texture mapTex = game.getAssetManager().get(map.getChildText("background"), Texture.class);
				arrMaps[mapIterator] = new Map(map.getChildText("name"), 
						mapTex, 
						arrFeatures, 
						arrPortals,
						getGlobal(map),
						generateEnemies(map, game.getAssetManager()));

			} // end MAP loop

			// loop through updating the links
			for (int mapIterator = 0; mapIterator < arrMaps.length; mapIterator++) {
				// given a map 'map'

				// get total portals
				int NoOfPortals = arrMaps[mapIterator].getPortalNo();

//				int[] portalRefs = new int[NoOfPortals];
				// for each portal in 'map'
				for (int portalIterator = 0; portalIterator < NoOfPortals; portalIterator++) {
					arrMaps[mapIterator].setPortalExit(portalIterator,
							arrMaps[arrMaps[mapIterator].getPortalRefs()[portalIterator]]);
				}

			}
		} catch (JDOMException e) {
			e.printStackTrace();
			arrMaps = new Map[0];
		} catch (IOException ioe) {
			ioe.printStackTrace();
			arrMaps = new Map[0];
		}
		return arrMaps;
	}

	/**
	 * function to take an element and extracts the mapFeature data from it defining a new map feature
	 * @param feature
	 * @return
	 */
	private MapFeature elm2MapFeature(Element feature) {
		return new MapFeature(element2rectangle(feature),
				Str2Bool(feature.getChildText("groundImpeedence")),
				Str2Bool(feature.getChildText("flightImpeedence")),
				Str2Bool(feature.getChildText("isWater")));
	}
	/**
	 * function to take an element and extract Portal data from it and defines a new Portal
	 * @param portal
	 * @return
	 */
	//NEEDS DATA VALIDATION
	private Portal elm2Portal(Element portal){
		return new Portal(element2rectangle(portal),
				Integer.parseInt(portal.getChild("to").getChildText("ref")), portal2Vector(portal));
	}

	// requires data validation!
	private Boolean Str2Bool(String text) {
		return Integer.parseInt(text) > 0;
	}
	
	/**
	 * Takes XML element and extracts rectangle data from it (x,y,width,height)
	 * @param elm
	 * @return
	 */
	//NEEDS DATA VALIDATION
	private Rectangle element2rectangle(Element elm) {
		return new Rectangle().set(Integer.parseInt(elm.getChildText("x")), Integer.parseInt(elm.getChildText("y")),
				Integer.parseInt(elm.getChildText("width")), Integer.parseInt(elm.getChildText("height")));
	}

	/**
	 * Takes XML element representing a portal and extract vector data (x,y)
	 * @param portal
	 * @return
	 */
	private Vector2 portal2Vector(Element portal) {
		return getVector2(portal.getChild("to"));
	}
	
	/**
	 * Takes XML element and extract vector data (x,y)
	 * @param portal
	 * @return
	 */
	//NEEDS DATA VALIDATION
	private Vector2 getVector2(Element elm) {
		return new Vector2(Integer.parseInt(elm.getChildText("x")), Integer.parseInt(elm.getChildText("y")));
	}
	/**
	 * Takes XML element and extract vector data (x,y,z)
	 * @param portal
	 * @return
	 */
	//NEEDS DATA VALIDATION
	private Vector3 getVector3(Element elm) {
		return new Vector3(Integer.parseInt(elm.getChildText("x")), Integer.parseInt(elm.getChildText("y")),Integer.parseInt(elm.getChildText("z")));
	}
	
	/**
	 * Takes XML element representing a map and returs its global coordinates
	 * @param map
	 * @return
	 */
	private Vector3 getGlobal(Element map) {
		return getVector3(map.getChild("globalPosition"));
	}

	private Repeatable[] generateEnemies(Element map, AssetManager manager){
		if (map.getChild("enemy") != null) {
			Repeatable[] repeatables = new Repeatable[1];
			repeatables[0] = new Repeatable(3,manager);
			return repeatables;
		}
		return new Repeatable[0];
	}
}
