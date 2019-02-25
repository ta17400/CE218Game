package game1;

import utilities.Action;
import utilities.Controller;
import utilities.SoundManager;
import utilities.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;

import static game1.Constants.DT;
import static game1.Constants.FRAME_HEIGHT;
import static game1.Constants.FRAME_WIDTH;

public class Ship extends GameObject {
    public static final int RADIUS = 8;
    public static final int DRAWING_SCALE = 10;

    public Bullet bullet = null;

    // rotation velocity in radians per second
    public static final double STEER_RATE = 2* Math.PI;

    // acceleration when thrust is applied
    public static final double MAG_ACC = 100;

    // constant speed loss factor
    public static final double DRAG = 0.50;

    public static final Color COLOR = Color.cyan;

    public Vector2D direction;

    // controller which provides an Action object in each frame
    private Controller ctrl;
    private int life;

    public Ship(Controller ctrl) {
        super(new Vector2D(FRAME_WIDTH/2,FRAME_HEIGHT/2),
        new Vector2D(),RADIUS);
        this.life = 5;

        this.ctrl = ctrl;
        direction = new Vector2D(0,-1);
    }

    public void update() {
        super.update();
        Action action = ctrl.action();

        direction.rotate(action.turn * STEER_RATE * DT);

        if(action.thrust==0) {
            if (velocity.x < 0) {
                velocity.add(DRAG, 0);
            } else if (velocity.x > 0) {
                velocity.subtract(DRAG, 0);
            }

            if (velocity.y < 0) {
                velocity.add(0, DRAG);
            } else if (velocity.y > 0) {
                velocity.subtract(0, DRAG);
            }
        }
        else{
            velocity.addScaled(direction,MAG_ACC*action.thrust*DT);
        }

        if(action.shoot) {
            mkBullet();
            action.shoot = false;
        }
    }

    private void mkBullet(){
        bullet = new Bullet(new Vector2D(position),new Vector2D(velocity),RADIUS);
        SoundManager.fire();
        bullet.position.add((direction.x*25),(direction.y*25));
        bullet.velocity.addScaled(direction,MAG_ACC);
    }

    public void addLife(int amount) {
        this.life += amount;
    }
    public int getLife(){
        return life;
    }

    public void reset(){
        this.position.x = FRAME_WIDTH/2;
        this.position.y = FRAME_HEIGHT/2;
        this.velocity.x =0;
        this.velocity.y=0;
        direction = new Vector2D(0,-1);
    }

    public void hit(){
        super.hit();
        if(this.life > 0){
            this.life -= 1;
            this.dead = false;
            reset();
            SoundManager.play(SoundManager.bangLarge);

        }
        else{
            Game.endGame = true;
        }
    }
    @Override
    public String toString() {
        return("Ship");
    }

    public void draw(Graphics2D g){
        Action action = ctrl.action();
        int[] XP = {0,2,0,-2};
        int[] YP = {-2,2,0,2};
        int[] XPTHRUST = {-1,0,1};
        int[] YPTHRUST = {0,4,0};
        //int[] XPTHRUST = {}
        //g.drawRect((int) position.x,(int)position.y,20,20);
       // tion.x - RADIUS, (int) position.y - RADIUS, 2* RADIUS, 2*RADIUS);
        AffineTransform at = g.getTransform();
        g.translate(position.x, position.y);
        double rot = direction.angle() + Math.PI / 2;
        g.rotate(rot);
        g.scale(DRAWING_SCALE, DRAWING_SCALE);
        if (action.thrust !=0) {
            g.setColor(Color.red);
            g.fillPolygon(XPTHRUST,YPTHRUST,XPTHRUST.length);
        }
        g.setColor(COLOR);
        g.fillPolygon(XP, YP, XP.length);

        g.setTransform(at);
    }
}