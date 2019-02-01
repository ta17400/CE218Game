package game1;


import utilities.Vector2D;

import java.awt.*;
import java.util.Random;


import static game1.Constants.DT;
import static game1.Constants.FRAME_HEIGHT;
import static game1.Constants.FRAME_WIDTH;

public class BasicAsteroid {
    private double vx, vy;
    private Vector2D vec;
    public static final int RADIUS = 10;
    public static final int MAX_SPEED = 100;

    public BasicAsteroid(double x,double y, double vx, double vy){
        this.vec = new Vector2D(x,y);
        this.vx = vx;
        this.vy = vy;

    }

    public static BasicAsteroid makeRandomAsteroid(){
        //Change the range to [-100,100] for x,y
        return (new BasicAsteroid(Math.random() *(FRAME_WIDTH + 1),
                Math.random() *(FRAME_HEIGHT + 1),(Math.random() - 0.5) *2*(MAX_SPEED + 1),(Math.random() - 0.5) *2*(MAX_SPEED + 1)));
    }

    public void update(){
        vec.x += vx * DT;
        vec.y += vy * DT;
        vec.wrap(FRAME_WIDTH,FRAME_HEIGHT);
    }

    public void draw(Graphics g){
        g.setColor(Color.red);
        g.fillOval((int) vec.x - RADIUS, (int) vec.y - RADIUS, 2* RADIUS, 2*RADIUS);
    }
}
