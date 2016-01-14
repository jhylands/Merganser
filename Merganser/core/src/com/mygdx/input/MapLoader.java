package com.mygdx.input;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Map;
import com.mygdx.game.MapFeature;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Portal;



public class MapLoader {

	private MyGdxGame game;

	public MapLoader(MyGdxGame game) {
		this.game = game;
	}

	public Map[] loadXML(String file) {
		Map[] arrMaps;
		try {
			// create a file connection to pass to the SaxBuilder
			File inputFile = new File(file);
			// define a new saxBuilder
			SAXBuilder saxBuilder = new SAXBuilder();
			// get the saxBuilder to create a document from the file connection
			Document document = saxBuilder.build(inputFile);
			// root element should be Maps
			// System.out.println(document.getRootElement().getName()=="Maps");
			// get a list of the maps contained in the maps file
			List<Element> maps = document.getRootElement().getChildren();
			// create Structure to hold the maps needs to be update to AVL tree
			// for faster linking but currently uses integer references which
			// are faster anyway even if less user friendly
			arrMaps = new Map[maps.size()];

			// iterate through the maps
			for (int mapIterator = 0; mapIterator < maps.size(); mapIterator++) {
				Element map = maps.get(mapIterator);
				// given this map create map class from it leaving portal
				// destinations numerical referenced but not connected

				// check if there are any mapFeatures
				MapFeature[] arrFeatures;
				if (map.getChild("MapFeatures") != null) {
					// get list of mapFeatures
					List<Element> features = map.getChild("MapFeatures").getChildren();
					// initiate the array of MapFeatures to be passed to the map
					// constructor
					arrFeatures = new MapFeature[features.size()];
					// parse the xml into MapFeature type
					// System.out.println(features.get(0).getChild("groundImpeedence").getText());
					for (int featureIterator = 0; featureIterator < features.size(); featureIterator++) {
						Element feature = features.get(featureIterator);
						arrFeatures[featureIterator] = new MapFeature(element2rectangle(feature),
								Str2Bool(feature.getChildText("groundImpeedence")),
								Str2Bool(feature.getChildText("flightImpeedence")),
								Str2Bool(feature.getChildText("isWater")));
					}
				} else {
					arrFeatures = new MapFeature[0];
				}

				Portal[] arrPortals;
				if (map.getChild("MapPortals") != null) {
					// get list of portals
					List<Element> portals = map.getChild("MapPortals").getChildren();
					arrPortals = new Portal[portals.size()];
					for (int portalIterator = 0; portalIterator < portals.size(); portalIterator++) {
						Element portal = portals.get(portalIterator);
						arrPortals[portalIterator] = new Portal(element2rectangle(portal),
								Integer.parseInt(portal.getChild("to").getChildText("ref")), portal2Vector(portal));
					}
				} else {
					arrPortals = new Portal[0];
				}

				// add this map to the maps array

				Texture mapTex = game.getAssetManager().get(map.getChildText("background"), Texture.class);
				arrMaps[mapIterator] = new Map(map.getChildText("name"), mapTex, arrFeatures, arrPortals,
						getGlobal(map));

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

	// requires data validation!
	private Boolean Str2Bool(String text) {
		return Integer.parseInt(text) > 0;
	}

	private Rectangle element2rectangle(Element elm) {
		return new Rectangle().set(Integer.parseInt(elm.getChildText("x")), Integer.parseInt(elm.getChildText("y")),
				Integer.parseInt(elm.getChildText("width")), Integer.parseInt(elm.getChildText("height")));
	}

	private Vector2 portal2Vector(Element portal) {
		return getVector2(portal.getChild("to"));
	}

	private Vector2 getVector2(Element elm) {
		return new Vector2(Integer.parseInt(elm.getChildText("x")), Integer.parseInt(elm.getChildText("y")));
	}
	
	private Vector3 getVector3(Element elm) {
		return new Vector3(Integer.parseInt(elm.getChildText("x")), Integer.parseInt(elm.getChildText("y")),Integer.parseInt(elm.getChildText("z")));
	}

	private Vector3 getGlobal(Element map) {
		return getVector3(map.getChild("globalPosition"));

	}

}
