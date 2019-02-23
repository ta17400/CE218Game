package game1;

import utilities.Vector2D;

import java.awt.*;

public class Bullet extends GameObject {
    private int bulletLife = 0;
    private static final Color COLOR = Color.cyan;

    public Bullet(Vector2D position, Vector2D velocity,int radius) {
        super(position, velocity,radius);
    }

    public void update() {
        super.update();
        if(bulletLife == 100){
            dead = true;
        }else{
            bulletLife++;
        }

    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(COLOR);
        g.fillOval((int)position.x,(int)position.y,(int)radius, (int)radius);
    }
}
