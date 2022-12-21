package data.scripts.hullmods;

import java.util.HashMap;
import java.util.Map;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;
import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.impl.campaign.ids.Stats;


public class CHM_ne extends BaseHullMod {


public static final float DISSIPATION_BONUS = 5f;	
public static final float HULL_BONUS = 5f;


    @Override
    public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
        
		stats.getFluxDissipation().modifyPercent(id,DISSIPATION_BONUS);
		stats.getHullBonus().modifyPercent(id, HULL_BONUS);
    }
	
	 	public String getDescriptionParam(int index, HullSize hullSize) {
            if (index == 0) return "" + (int) DISSIPATION_BONUS + "%";
			if (index == 1) return "" + (int) HULL_BONUS + "%";
         
		return null;
	}
	 
	 public boolean isApplicableToShip(ShipAPI ship) {
		return ship != null && ship.getShield() != null;
	}
	
	public String getUnapplicableReason(ShipAPI ship) {
		return "Ship has no shields";
	}

}
