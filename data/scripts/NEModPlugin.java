package data.scripts;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.PluginPick;
import com.fs.starfarer.api.campaign.CampaignPlugin;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.impl.campaign.ids.Skills;
//With Nex
import exerelin.campaign.SectorManager;

import data.scripts.world.northernexpedition_gen;
import data.scripts.world.systems.northernexpedition_exyusia;

public class NEModPlugin extends BaseModPlugin {
	
	/*
	//Without Nex
	 @Override
    public void onNewGame() {
        initNE();
    }
	
    public void initNE() {
	new northernexpedition_gen().generate(Global.getSector());
    }
	*/
	
    public void onNewGame() {
	boolean haveNexerelin = Global.getSettings().getModManager().isModEnabled("nexerelin");
	if (!haveNexerelin || SectorManager.getManager().getCorvusMode()){
            new northernexpedition_gen().generate(Global.getSector());
        }
    }
	
	
}
	
