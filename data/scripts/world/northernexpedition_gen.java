package data.scripts.world;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.RepLevel;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorGeneratorPlugin;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
import com.fs.starfarer.api.impl.campaign.shared.SharedData;
import data.scripts.world.systems.northernexpedition_exyusia;


import java.io.IOException;
import java.util.List;

@SuppressWarnings("unchecked")
public class northernexpedition_gen implements SectorGeneratorPlugin {

    @Override
    public void generate(SectorAPI sector) {
	
        new northernexpedition_exyusia().generate(sector);
		
        SharedData.getData().getPersonBountyEventData().addParticipatingFaction("northernexpedition");

        FactionAPI northernexpedition = sector.getFaction("northernexpedition");
        FactionAPI player = sector.getFaction(Factions.PLAYER);
        FactionAPI hegemony = sector.getFaction(Factions.HEGEMONY);
        FactionAPI tritachyon = sector.getFaction(Factions.TRITACHYON);
        FactionAPI pirates = sector.getFaction(Factions.PIRATES);
        FactionAPI independent = sector.getFaction(Factions.INDEPENDENT); 
        FactionAPI church = sector.getFaction(Factions.LUDDIC_CHURCH);
        FactionAPI path = sector.getFaction(Factions.LUDDIC_PATH);   
        FactionAPI kol = sector.getFaction(Factions.KOL);	
        FactionAPI diktat = sector.getFaction(Factions.DIKTAT); 
		FactionAPI persean = sector.getFaction(Factions.PERSEAN);
        FactionAPI guard = sector.getFaction(Factions.LIONS_GUARD);

        northernexpedition.setRelationship(player.getId(), 0.2f);	
        northernexpedition.setRelationship(hegemony.getId(), -0.2f);
        northernexpedition.setRelationship(tritachyon.getId(), -0.2f);
        northernexpedition.setRelationship(pirates.getId(), -1.0f);
        northernexpedition.setRelationship(independent.getId(), 0.4f);
        northernexpedition.setRelationship(persean.getId(), -0.15f);	
        northernexpedition.setRelationship(church.getId(), -0.3f);
        northernexpedition.setRelationship(path.getId(), -0.4f);    
        northernexpedition.setRelationship(kol.getId(), -0.15f);    
        northernexpedition.setRelationship(diktat.getId(), -0.1f);
        northernexpedition.setRelationship(guard.getId(), -0.2f);     
                 
         //modded factions
        northernexpedition.setRelationship("SCY", RepLevel.SUSPICIOUS);        
        northernexpedition.setRelationship("pn_colony", RepLevel.NEUTRAL);       
        northernexpedition.setRelationship("neutrinocorp", RepLevel.SUSPICIOUS);        
        northernexpedition.setRelationship("dassault_mikoyan", RepLevel.SUSPICIOUS); 
        northernexpedition.setRelationship("JYD", RepLevel.NEUTRAL);        
        northernexpedition.setRelationship("diableavionics", RepLevel.NEUTRAL);		
        northernexpedition.setRelationship("cabal", RepLevel.NEUTRAL);        
        northernexpedition.setRelationship("the_deserter", RepLevel.SUSPICIOUS);
        northernexpedition.setRelationship("blade_breakers", RepLevel.SUSPICIOUS);        
        northernexpedition.setRelationship("the_deserter", RepLevel.SUSPICIOUS);
		northernexpedition.setRelationship("kingdom_of_terra", RepLevel.SUSPICIOUS);
		northernexpedition.setRelationship("unitedpamed", RepLevel.SUSPICIOUS);		
        northernexpedition.setRelationship("brighton", RepLevel.NEUTRAL);
		northernexpedition.setRelationship("hiigaran_descendants", RepLevel.NEUTRAL);
		northernexpedition.setRelationship("prv", RepLevel.SUSPICIOUS);		
        northernexpedition.setRelationship("scalartech", RepLevel.SUSPICIOUS);
        northernexpedition.setRelationship("star_federation", RepLevel.SUSPICIOUS);
        northernexpedition.setRelationship("kadur_remnant", RepLevel.SUSPICIOUS);		
        northernexpedition.setRelationship("keruvim", RepLevel.NEUTRAL);
        northernexpedition.setRelationship("mayasura", RepLevel.NEUTRAL);	      
        northernexpedition.setRelationship("noir", RepLevel.NEUTRAL);
        northernexpedition.setRelationship("Lte", RepLevel.NEUTRAL);
        northernexpedition.setRelationship("GKSec", RepLevel.NEUTRAL);
        northernexpedition.setRelationship("gmda", RepLevel.SUSPICIOUS);
        northernexpedition.setRelationship("oculus", RepLevel.NEUTRAL);
        northernexpedition.setRelationship("nomads", RepLevel.SUSPICIOUS);
        northernexpedition.setRelationship("thulelegacy", RepLevel.SUSPICIOUS);
        northernexpedition.setRelationship("infected", RepLevel.HOSTILE);
        northernexpedition.setRelationship("ORA", RepLevel.SUSPICIOUS);
        northernexpedition.setRelationship("HMI", RepLevel.NEUTRAL);
        northernexpedition.setRelationship("draco", RepLevel.NEUTRAL);
        northernexpedition.setRelationship("roider", RepLevel.NEUTRAL);
        northernexpedition.setRelationship("ironshell", RepLevel.SUSPICIOUS);
        northernexpedition.setRelationship("magellan_protectorate", RepLevel.NEUTRAL);
        northernexpedition.setRelationship("exalted", RepLevel.HOSTILE);
        northernexpedition.setRelationship("fang", RepLevel.NEUTRAL);
        northernexpedition.setRelationship("xhanempire", RepLevel.NEUTRAL);
        northernexpedition.setRelationship("xlu", RepLevel.NEUTRAL);
        northernexpedition.setRelationship("fpe", RepLevel.SUSPICIOUS);
        northernexpedition.setRelationship("al_ars", RepLevel.SUSPICIOUS);   

    }
}
