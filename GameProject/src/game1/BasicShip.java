package game1;

import utilities.Action;
import utilities.BasicController;
import utilities.Vector2D;

import java.awt.*;
import java.util.Random;

import static game1.Constants.DT;
import static game1.Constants.FRAME_HEIGHT;
import static game1.Constants.FRAME_WIDTH;

public class BasicShip {
    public static final int RADIUS = 8;

    // rotation velocity in radians per second
    public static final double STEER_RATE = 2* Math.PI;

    // acceleration when thrust is applied
    public static final double MAG_ACC = 100;

    // constant speed loss factor
    public static final double DRAG = 0.50;

    public static final Color COLOR = Color.cyan;

    public Vector2D position; // on frame
    public Vector2D velocity; // per second
    // direction in which the nose of the ship is pointing
    // this will be the direction in which thrust is applied
    // it is a unit vector representing the angle by which the ship has rotated
    public Vector2D direction;

    // controller which provides an Action object in each frame
    private BasicController ctrl;

    public BasicShip(BasicController ctrl) {
        this.ctrl = ctrl;
        position = new Vector2D(FRAME_WIDTH/2,FRAME_HEIGHT/2);
        velocity = new Vector2D();
        direction = new Vector2D(10,10);
    }

    public void update() {
        Action action = ctrl.action();

        direction.rotate(action.turn * STEER_RATE * DT);
        if(action.thrust != 0){
            velocity.addScaled(direction,MAG_ACC*action.thrust*DT);
        }
        else{
            velocity.addScaled(direction,DRAG);
        }

        position.addScaled(velocity,DT).wrap(FRAME_WIDTH,FRAME_HEIGHT);

    }
    public void draw(Graphics g){
        //g.drawRect((int) position.x,(int)position.y,20,20);
        g.setColor(Color.cyan);
        g.drawLine((int) position.x, (int) position.y,(int)position.x+(int)direction.x,(int)position.y+(int)direction.y);
        g.fillOval((int) position.x - RADIUS, (int) position.y - RADIUS, 2* RADIUS, 2*RADIUS);
    }
}