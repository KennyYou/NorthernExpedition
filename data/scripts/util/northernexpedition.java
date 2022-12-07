package data.scripts.util;

import com.fs.starfarer.api.Global;

public class northernexpedition {   
    private static final String northernexpedition="northernexpedition";
    public static String txt(String id){
        return Global.getSettings().getString(northernexpedition, id);
    }    
}