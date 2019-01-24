package game1;


import java.awt.*;


import static game1.Constants.DT;
import static game1.Constants.FRAME_HEIGHT;
import static game1.Constants.FRAME_WIDTH;

public class BasicAsteroid {
    private double x,y, vx, vy;
    public static final int RADIUS = 10;
    public static final int MAX_SPEED = 100;

    public BasicAsteroid(double x,double y, double vx, double vy){
        this.x = x;
        this.y= y;
        this.vx = vx;
        this.vy = vy;

    }

    public static BasicAsteroid makeRandomAsteroid(){
        return (new BasicAsteroid(Math.random() *(FRAME_WIDTH + 1),
                Math.random() *(FRAME_HEIGHT + 1),Math.random() *(MAX_SPEED + 1),Math.random() *(MAX_SPEED + 1)));
    }

    public void update(){
        x += vx * DT;
        y += vy * DT;
        x = (x+FRAME_WIDTH) % FRAME_WIDTH;
        y = (y + FRAME_HEIGHT) % FRAME_HEIGHT;
    }

    public void draw(Graphics g){
        g.setColor(Color.red);
        g.fillOval((int) x - RADIUS, (int) y - RADIUS, 2* RADIUS, 2*RADIUS);
    }
}
