package com.mygdx.game.desktop.tests;

import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ HeartTests.class, LiveEntityTest.class,
		MapFeatureTest.class, MapTests.class, ObjectiveTest.class, PlayerDuckTest.class, PortalTest.class })
public class AllTests {
	public static void main(String[] args) {
		JUnitCore.main("com.mygdx.game.desktop.tests.AllTests");
	}
}
