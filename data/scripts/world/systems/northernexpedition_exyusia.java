package data.scripts.world.systems;

import java.awt.Color;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.*;
import com.fs.starfarer.api.impl.campaign.ids.*;
import com.fs.starfarer.api.impl.campaign.ids.Conditions;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
import com.fs.starfarer.api.impl.campaign.ids.Terrain;
import com.fs.starfarer.api.impl.campaign.ids.Commodities;
import com.fs.starfarer.api.impl.campaign.ids.Entities;
import com.fs.starfarer.api.impl.campaign.ids.Tags;
import com.fs.starfarer.api.impl.campaign.ids.Industries;
import com.fs.starfarer.api.impl.campaign.ids.Items;
import com.fs.starfarer.api.impl.campaign.ids.Submarkets;
import com.fs.starfarer.api.impl.campaign.DerelictShipEntityPlugin;

import com.fs.starfarer.api.impl.campaign.submarkets.StoragePlugin;

import com.fs.starfarer.api.impl.campaign.procgen.NebulaEditor;
import com.fs.starfarer.api.impl.campaign.procgen.StarAge;
import com.fs.starfarer.api.impl.campaign.procgen.StarSystemGenerator;
import com.fs.starfarer.api.impl.campaign.procgen.PlanetConditionGenerator;
import com.fs.starfarer.api.impl.campaign.procgen.DefenderDataOverride;

import com.fs.starfarer.api.impl.campaign.procgen.themes.BaseThemeGenerator;
import com.fs.starfarer.api.impl.campaign.procgen.themes.DerelictThemeGenerator;
import com.fs.starfarer.api.impl.campaign.procgen.themes.SalvageSpecialAssigner;


import com.fs.starfarer.api.impl.campaign.terrain.BaseTiledTerrain;
import com.fs.starfarer.api.impl.campaign.ids.Terrain;

import com.fs.starfarer.api.impl.campaign.terrain.AsteroidFieldTerrainPlugin;
import com.fs.starfarer.api.impl.campaign.terrain.AsteroidFieldTerrainPlugin.AsteroidFieldParams;
import com.fs.starfarer.api.impl.campaign.terrain.DebrisFieldTerrainPlugin;
import com.fs.starfarer.api.impl.campaign.terrain.DebrisFieldTerrainPlugin.DebrisFieldParams;
import com.fs.starfarer.api.impl.campaign.terrain.DebrisFieldTerrainPlugin.DebrisFieldSource;
import com.fs.starfarer.api.impl.campaign.terrain.HyperspaceTerrainPlugin;
import com.fs.starfarer.api.impl.campaign.terrain.MagneticFieldTerrainPlugin;
import com.fs.starfarer.api.impl.campaign.terrain.MagneticFieldTerrainPlugin.MagneticFieldParams;
import com.fs.starfarer.api.impl.campaign.terrain.BaseRingTerrain.RingParams;
import com.fs.starfarer.api.campaign.econ.EconomyAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.util.Misc;
import org.lazywizard.lazylib.MathUtils;
import data.scripts.world.AddMarketplace;

public class northernexpedition_exyusia {	

	public void generate(SectorAPI sector) {
		
		
		StarSystemAPI system = sector.createStarSystem("Exyusia");
		LocationAPI hyper = Global.getSector().getHyperspace();
		
	
		// create the star and generate the hyperspace anchor for this system
		PlanetAPI star = system.initStar("Exyusia_star", // unique id for this star
											 "star_blue_giant", // id in planets.json
										 700f, 		// radius (in pixels at default zoom)
										 850); // corona radius, from star edge
		system.setLightColor(new Color(195, 218, 235)); // light color in entire system, affects all entities
		system.getLocation().set(2000, 21000); //Update up north...

		//Nebula from png
        SectorEntityToken exyusia_nebula = Misc.addNebulaFromPNG("data/campaign/terrain/exyusia_nebula.png",
				  0, 0, 
				  system, 
				  "terrain", "nebula", 
				  4, 4, StarAge.AVERAGE); 				  
			
				
		//Planets
		PlanetAPI exyusia1 = system.addPlanet("exyusia1", star, "Austice", "tundra", 750, 200, 5210, 400);
		exyusia1.getSpec().setGlowTexture(Global.getSettings().getSpriteName("hab_glows", "volturn"));
		exyusia1.getSpec().setGlowColor(new Color(255, 255, 255, 255));
		exyusia1.getSpec().setUseReverseLightForGlow(true);
		exyusia1.applySpecChanges();
		//exyusia1.setCustomDescriptionId("ne_austice");
		
		// Austice magnetic field
		SectorEntityToken barad_field = system.addTerrain(Terrain.MAGNETIC_FIELD,
				new MagneticFieldParams(exyusia1.getRadius() + 200f, // terrain effect band width 
						(exyusia1.getRadius() + 150f) / 2f, // terrain effect middle radius
						exyusia1, // entity that it's around
						exyusia1.getRadius() + 25f, // visual band start
						exyusia1.getRadius() + 25f + 125f, // visual band end
						new Color(50, 20, 100, 40), // base color
						0.5f, // probability to spawn aurora sequence, checked once/day when no aurora in progress
						new Color(140, 100, 235),
						new Color(180, 110, 210),
						new Color(150, 140, 190),
						new Color(140, 190, 210),
						new Color(90, 200, 170), 
						new Color(65, 230, 160),
						new Color(20, 220, 70)
				));
		barad_field.setCircularOrbit(exyusia1, 0, 0, 100);
		
		// Austice stellar shade x3
		SectorEntityToken austice_shade = system.addCustomEntity("austice_shade", "Austice Stellar Shade", "stellar_shade", "northernexpedition");
		austice_shade.setCircularOrbitPointingDown(exyusia1, 350, 550, 8000);		
		austice_shade.setCustomDescriptionId("stellar_shade");
		
		SectorEntityToken austice_shade1 = system.addCustomEntity("austice_shade1", "Austice Stellar Shade", "stellar_shade", "northernexpedition");
		austice_shade1.setCircularOrbitPointingDown(exyusia1, 330, 550, 8000);		
		austice_shade1.setCustomDescriptionId("stellar_shade");
		
		SectorEntityToken austice_shade2 = system.addCustomEntity("austice_shade2", "Austice Stellar Shade", "stellar_shade", "northernexpedition");
		austice_shade2.setCircularOrbitPointingDown(exyusia1, 310, 550, 8000);		
		austice_shade2.setCustomDescriptionId("stellar_shade");
		
		
		//Inner Jump Point
		JumpPointAPI jumpPoint = Global.getFactory().createJumpPoint("exyusia_jump_point1", "Austice Jump-point");
		OrbitAPI orbit = Global.getFactory().createCircularOrbit(star, 340, 4800, 250);
		jumpPoint.setOrbit(orbit);
		orbit.setEntity(jumpPoint);
		jumpPoint.setRelatedPlanet(exyusia1);
		jumpPoint.setStandardWormholeToHyperspaceVisual();
		system.addEntity(jumpPoint);
		
		
		// Inner Belt
		system.addAsteroidBelt(star, 300, 6800, 512, 550, 600, Terrain.ASTEROID_BELT, "Exyusia Asteroid Belt");
		system.addRingBand(star, "misc", "rings_ice0", 256f, 2, Color.white, 256f, 6800, 550f);		
		system.addRingBand(star, "misc", "rings_dust0", 256f, 0, Color.white, 256f, 6700, 450f, null, null);

		
		//Planet #2
		PlanetAPI exyusia2 = system.addPlanet("exyusia2", star, "Elyria", "frozen", 320, 155, 8100, 550);
		exyusia2.getSpec().setGlowTexture(Global.getSettings().getSpriteName("hab_glows", "volturn"));
		exyusia2.getSpec().setGlowColor(new Color(100, 100, 255, 255));
		exyusia2.getSpec().setUseReverseLightForGlow(true);
		exyusia2.applySpecChanges();
		exyusia2.setCustomDescriptionId("planet_elyria"); 		


		//Planet #3
		//Its travelling so fast... (Fix this)
		PlanetAPI exyusia3 = system.addPlanet("exyusia3", star, "Niolia", "ice_giant", 360f, 375, 11200, 600);
		exyusia3.getSpec().setGlowTexture(Global.getSettings().getSpriteName("hab_glows", "volturn"));
		exyusia3.getSpec().setGlowColor(new Color(255, 255, 255, 255));
		exyusia3.getSpec().setUseReverseLightForGlow(true);
		exyusia3.applySpecChanges();
		//exyusia3.setCustomDescriptionId("planet_niolia");
		//exyusia3.setInteractionImage("illustrations", "midline");
		
		// Moon Phorix
			PlanetAPI viculas = system.addPlanet("viculas", exyusia3, "Viculas", "barren-bombarded", 0, 125, 750, 30);
			viculas.getSpec().setTexture(Global.getSettings().getSpriteName("planets", "barren03"));
			//viculas.getSpec().setPlanetColor(new Color(225,240,255,255));
			exyusia3.getSpec().setPitch(-45f);
			exyusia3.getSpec().setTilt(-30f);
			viculas.applySpecChanges();
		
		
		//Planet #4
        PlanetAPI exyusia4 = system.addPlanet("exyusia4", star, "Phorix KF", "barren-bombarded", 210, 300, 13200, 300);
            
            Misc.initConditionMarket(exyusia4);
			exyusia4.getMarket().addCondition(Conditions.ORE_RICH);	
            exyusia4.getMarket().addCondition(Conditions.NO_ATMOSPHERE);
            exyusia4.getMarket().addCondition(Conditions.LOW_GRAVITY);
            exyusia4.getMarket().addCondition(Conditions.METEOR_IMPACTS);
            exyusia4.getMarket().addCondition(Conditions.VOLATILES_TRACE);	
			
		// Phorix KF rings
			system.addRingBand(exyusia4, "misc", "rings_dust0", 256f, 2, Color.white, 256f, 600, 31f);
			system.addRingBand(exyusia4, "misc", "rings_ice0", 256f, 3, Color.white, 256f, 750, 35f);
			
			SectorEntityToken ring = system.addTerrain(Terrain.RING, new RingParams(150 + 256, 675, null, null));
			ring.setCircularOrbit(exyusia4, 0, 0, 100);
			

		
		// exyusia Gate
		SectorEntityToken gate = system.addCustomEntity("exyusia_gate", // unique id
				 "Exyusia Gate", // name - if null, defaultName from custom_entities.json will be used
				 "inactive_gate", // type of object, defined in custom_entities.json
				 null); // faction
		gate.setCircularOrbit(system.getEntityById("Exyusia_star"), 40, 13900, 400);
		
			
		//Far Outer belt
		system.addAsteroidBelt(star, 550, 15300, 500, 100, 190, Terrain.ASTEROID_BELT, "Far Outer Belt");
        system.addRingBand(star, "misc", "rings_dust0", 256f, 0, Color.white, 256f, 15300, 201f, null, null);
        system.addRingBand(star, "misc", "rings_ice0", 256f, 1, Color.white, 256f, 15300, 225f, null, null);
        
		/*
		//Outer Jump point
		JumpPointAPI fringe = Global.getFactory().createJumpPoint("exyusia_jump_point2", "Outer belt Jump-point");
		orbit = Global.getFactory().createCircularOrbit(star, 120, 12300, 600);
		fringe.setOrbit(orbit);
		fringe.setStandardWormholeToHyperspaceVisual();
		system.addEntity(fringe);	
		*/
		
		// Relays
		SectorEntityToken exyusia_relay = system.addCustomEntity("exyusia_relay", // unique id
				"Exyusia Relay", // name - if null, defaultName from custom_entities.json will be used
				"comm_relay", // type of object, defined in custom_entities.json
				"northernexpedition"); // faction
		exyusia_relay.setCircularOrbitPointingDown(star, 820, 4150, 600);

		SectorEntityToken exyusia_buoy = system.addCustomEntity("exyusia_buoy", // unique id
				"Exyusia Nav Buoy", // name - if null, defaultName from custom_entities.json will be used
				"nav_buoy", // type of object, defined in custom_entities.json
				"northernexpedition"); // faction
		exyusia_buoy.setCircularOrbitPointingDown(star, 45, 16420, 540);

		SectorEntityToken exyusia_sensor = system.addCustomEntity("exyusia_sensor", // unique id
				"Exyusia Sensor Array", // name - if null, defaultName from custom_entities.json will be used
				"sensor_array", // type of object, defined in custom_entities.json
				"northernexpedition"); // faction
		exyusia_sensor.setCircularOrbitPointingDown(star, 157, 5500, 1275);		
		
		/*
		//Nebula - I have no idea what parameters these mean (The saddest nebula has been made in starsector with this)
		SectorEntityToken nebula = system.addTerrain(Terrain.NEBULA, new BaseTiledTerrain.TileParams(
				"x  xx " +
				"  xx x" +
				"x  xx " +
				"x    x" +
				"  xx  " +
				"x   x ",
				6, 6, // size of the nebula grid, should match above string
				"terrain", "nebula_blue", 4, 4, null));
		nebula.getLocation().set(exyusia1.getLocation().x + 1000f, exyusia1.getLocation().y);
		nebula.setCircularOrbit(star, 140f, 10000, 500);
		*/
			
		
           //debris field... testing
            DebrisFieldTerrainPlugin.DebrisFieldParams params1 = new DebrisFieldTerrainPlugin.DebrisFieldParams(
                420, // field radius - should not go above 1000 for performance reasons
                1.4f, // density, visual - affects number of debris pieces
                10000000f, // duration in days 
                0f); // days the field will keep generating glowing pieces
            params1.source = DebrisFieldTerrainPlugin.DebrisFieldSource.MIXED;
            params1.baseSalvageXP = 650; // base XP for scavenging in field
            SectorEntityToken debrisBelt1 = Misc.addDebrisField(system, params1, StarSystemGenerator.random);
            debrisBelt1.setSensorProfile(1000f);
            debrisBelt1.setDiscoverable(true);
            debrisBelt1.setCircularOrbit(star, 265f, 6880, 240f);
            debrisBelt1.setId("exyusia_debris1");
                
 
		system.autogenerateHyperspaceJumpPoints(true, true);
	}
	
	
}