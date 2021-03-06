package game1;


import utilities.Sprite;

import java.awt.*;

public class Constants {
    public static final int FRAME_HEIGHT = 480;
    public static final int FRAME_WIDTH = 640;
    public static final Dimension FRAME_SIZE = new Dimension(Constants.FRAME_WIDTH,Constants.FRAME_HEIGHT);
    public static final int DELAY = 20;
    public static final double DT = DELAY / 1000.0;
    public static final Color BG_COLOR = Color.BLACK;
    public static Image ASTEROID = Sprite.ASTEROID;
    public static Image MILKYWAY = Sprite.MILKYWAY;
}
