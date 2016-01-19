package com.mygdx.game.desktop.tests;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Map;
import com.mygdx.game.MapFeature;
import com.mygdx.game.Portal;
import com.mygdx.sprite.PlayerDuck;
import com.mygdx.sprite.Repeatable;

import junit.framework.TestCase;

public class MapTests extends AssetTestCase {

	Map map;

	public void setUp() {
		super.setUp();
	}

	public void testValidSpaceFalseNonFlying() {
		map = new Map("test", null, new MapFeature[] { new MapFeature(new Rectangle(0, 0, 50, 50), true, true, false) },
				new Portal[] {}, new Vector3(50, 50, 0), new Repeatable[] {});
		Rectangle testSpace = new Rectangle(5, 5, 5, 5);
		boolean flying = false;
		boolean canSwim = false;
		assertEquals(false, map.validSpace(testSpace, flying, canSwim));
	}

	public void testValidSpaceFalseFlying() {
		map = new Map("test", null, new MapFeature[] { new MapFeature(new Rectangle(0, 0, 50, 50), true, true, false) },
				new Portal[] {}, new Vector3(50, 50, 0), new Repeatable[] {});
		Rectangle testSpace = new Rectangle(5, 5, 5, 5);
		boolean flying = true;
		boolean canSwim = false;
		assertEquals(false, map.validSpace(testSpace, flying, canSwim));
	}

	public void testValidSpaceTrue() {
		map = new Map("test", null, new MapFeature[] { new MapFeature(new Rectangle(0, 0, 50, 50), true, true, false) },
				new Portal[] {}, new Vector3(50, 50, 0), new Repeatable[] {});
		Rectangle testSpace = new Rectangle(51, 51, 5, 5);
		boolean flying = false;
		boolean canSwim = false;
		assertEquals(true, map.validSpace(testSpace, flying, canSwim));
	}

	public void testValidSpaceTrueFlying() {
		map = new Map("test", null,
				new MapFeature[] { new MapFeature(new Rectangle(0, 0, 50, 50), true, false, false) }, new Portal[] {},
				new Vector3(50, 50, 0), new Repeatable[] {});
		Rectangle testSpace = new Rectangle(5, 5, 5, 5);
		boolean flying = true;
		boolean canSwim = false;
		assertEquals(true, map.validSpace(testSpace, flying, canSwim));
	}

	public void testValidSpaceFalseSwim() {
		map = new Map("test", null,
				new MapFeature[] { new MapFeature(new Rectangle(0, 0, 50, 50), false, false, true) }, new Portal[] {},
				new Vector3(50, 50, 0), new Repeatable[] {});
		Rectangle testSpace = new Rectangle(5, 5, 5, 5);
		boolean flying = false;
		boolean canSwim = false;
		assertEquals(false, map.validSpace(testSpace, flying, canSwim));
	}

	public void testValidSpaceTrueSwim() {
		map = new Map("test", null,
				new MapFeature[] { new MapFeature(new Rectangle(0, 0, 50, 50), false, false, true) }, new Portal[] {},
				new Vector3(50, 50, 0), new Repeatable[] {});
		Rectangle testSpace = new Rectangle(5, 5, 5, 5);
		boolean flying = false;
		boolean canSwim = false;
		assertEquals(false, map.validSpace(testSpace, flying, canSwim));
	}

	public void testValidSpaceFalseLarge() {
		map = new Map("test", null, new MapFeature[] { new MapFeature(new Rectangle(0, 0, 50, 50), true, true, false) },
				new Portal[] {}, new Vector3(50, 50, 0), new Repeatable[] {});
		Rectangle testSpace = new Rectangle(0, 0, 60, 60);
		boolean flying = false;
		boolean canSwim = false;
		assertEquals(false, map.validSpace(testSpace, flying, canSwim));
	}

	public void testValidSpaceTrueZero() {
		map = new Map("test", null, new MapFeature[] { new MapFeature(new Rectangle(0, 0, 50, 50), true, true, false) },
				new Portal[] {}, new Vector3(50, 50, 0), new Repeatable[] {});
		Rectangle testSpace = new Rectangle(0, 0, 0, 0);
		boolean flying = false;
		boolean canSwim = false;
		assertEquals(true, map.validSpace(testSpace, flying, canSwim));
	}

	public void testMangePortalsTrue() {
		Portal newPortal = new Portal(new Rectangle(20, 100, 50, 50), 0, new Vector2(0, 0));
		System.out.println(newPortal.getExitLocal());
		Vector2 beforeMove = game.duck.getPosition();
		map = new Map("test", null, new MapFeature[] {},
				new Portal[] {newPortal},
				new Vector3(50, 50, 0), new Repeatable[] {});
		map.managePortals(game.duck);
		Vector2 afterMove = game.duck.getPosition();
		assertEquals(newPortal.getExitLocal(), afterMove);
	}

	public void testManagePortalsFalse() {
		Portal newPortal = new Portal(new Rectangle(0, 0, 50, 50), 0, new Vector2(0, 0));
		System.out.println(newPortal.getExitLocal());
		Vector2 beforeMove = game.duck.getPosition();
		map = new Map("test", null, new MapFeature[] {},
				new Portal[] {newPortal},
				new Vector3(50, 50, 0), new Repeatable[] {});
		map.managePortals(game.duck);
		Vector2 afterMove = game.duck.getPosition();
		assertEquals(beforeMove, afterMove);
	}

	public void testIsSwimmingTrue() {
		Rectangle duckPosition = new Rectangle(5, 5, 5, 5);
		map = new Map("test", null, new MapFeature[] { new MapFeature(new Rectangle(0, 0, 50, 50), false, false, true) },
				new Portal[] {}, new Vector3(50, 50, 0), new Repeatable[] {});
		assertTrue(map.isSwimming(duckPosition));
	}
	
	public void testValidSpaceTrueMultiple() {
		map = new Map("test", null, new MapFeature[] { new MapFeature(new Rectangle(0, 0, 50, 50), true, true, false),
				new MapFeature(new Rectangle(0, 50, 50, 50), true, true, false)},
				new Portal[] {}, new Vector3(50, 50, 0), new Repeatable[] {});
		Rectangle testSpace = new Rectangle(51, 51, 5, 5);
		boolean flying = false;
		boolean canSwim = false;
		assertEquals(true, map.validSpace(testSpace, flying, canSwim));
	}

}