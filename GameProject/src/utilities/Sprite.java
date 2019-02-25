package utilities;

import java.awt.*;
import java.io.IOException;

public class Sprite {
    public static Image ASTEROID,MILKYWAY;

    static{
       System.out.println("LOADED");
        try {
            ASTEROID = ImageManager.loadImage("asteroid1");
            MILKYWAY = ImageManager.loadImage("milkyway1");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
