package game1;


import utilities.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import static game1.Constants.FRAME_HEIGHT;
import static game1.Constants.FRAME_WIDTH;

public class Asteroid extends GameObject{
    public static final int RADIUS = 10;
    public static final int MAX_SPEED = 100;
    public int size;
    public ArrayList<GameObject> asteroids = null;
    private Image asteroidImage = Constants.ASTEROID;
    AffineTransform asteroidImageTransformation;

    public Asteroid(double x, double y, double vx, double vy, int size)
    {
        super(new Vector2D(x,y),new Vector2D(vx,vy),RADIUS);
        this.size = size;
        double imWidth = asteroidImage.getWidth(null);
        double imHeight = asteroidImage.getHeight(null);
//        double stretchx = (imWidth > Constants.FRAME_WIDTH? 1 :
//                Constants.FRAME_WIDTH/imWidth);
//        double stretchy = (imHeight > Constants.FRAME_HEIGHT? 1 :
//                Constants.FRAME_HEIGHT/imHeight);
//        asteroidImageTransformation = new AffineTransform();
//        asteroidImageTransformation.scale(stretchx, stretchy);
    }

    public void spawnAsteroids(){
        if(this.size == 2) {
            System.out.println("Size2 " + size);
            Asteroid mediumAsteroid1 = new Asteroid(this.position.x + 5,
                    this.position.y + 5,
                    (Math.random() - 0.5) *2*(MAX_SPEED + 1),
                    (Math.random() - 0.5) *2*(MAX_SPEED + 1),
                    this.size);
            Asteroid mediumAsteroid2 = new Asteroid(this.position.x + 5,
                    this.position.y + 5,
                    (Math.random() - 0.5) *2*(MAX_SPEED + 1),
                    (Math.random() - 0.5) *2*(MAX_SPEED + 1),
                    this.size);            asteroids = new ArrayList<>();
            asteroids.add(mediumAsteroid1);
            asteroids.add(mediumAsteroid2);
        }
        else if(this.size == 1){
            Asteroid smallAsteroid1 = new Asteroid(this.position.x + 5, this.position.y + 5,
                    (Math.random() - 0.5) *2*(MAX_SPEED + 1),
                    (Math.random() - 0.5) *2*(MAX_SPEED + 1),
                    this.size);
            Asteroid smallAsteroid2 = new Asteroid(this.position.x + 5, this.position.y + 5,
                    (Math.random() - 0.5) *2*(MAX_SPEED + 1),
                    (Math.random() - 0.5) *2*(MAX_SPEED + 1),
                    this.size);            asteroids = new ArrayList<>();
            asteroids.add(smallAsteroid1);
            asteroids.add(smallAsteroid2);
        }
    }

    public static Asteroid makeRandomAsteroid(){

        //Change the range to [-100,100] for x,y
        int size = 3;

        return(new Asteroid(
                Math.random() *(FRAME_WIDTH + 1),
                Math.random() *(FRAME_HEIGHT + 1),
                (Math.random() - 0.5) *2*(MAX_SPEED + 1),
                (Math.random() - 0.5) *2*(MAX_SPEED + 1),
                size));

    }

    public void update(){
        super.update();
    }

    public void hit(){
        super.hit();
        this.size-=1;
        spawnAsteroids();
    }

    public void draw(Graphics2D g){
        g.setColor(Color.red);
        if(size == 3){

           g.drawImage(asteroidImage,(int) this.position.x - this.RADIUS, (int) this.position.y - this.RADIUS, 4* this.RADIUS, 4*this.RADIUS,null);
            //g.fillOval((int) this.position.x - this.RADIUS, (int) this.position.y - this.RADIUS, 4* this.RADIUS, 4*this.RADIUS);
        }
        else if(size == 2){
            g.drawImage(asteroidImage,(int) this.position.x - this.RADIUS, (int) this.position.y - this.RADIUS, 3* this.RADIUS, 3*this.RADIUS,null);
            //g.fillOval((int) this.position.x - this.RADIUS, (int) this.position.y - this.RADIUS, 3* this.RADIUS, 3*this.RADIUS);
        }
        else if(size == 1){
            g.drawImage(asteroidImage,(int) this.position.x - this.RADIUS, (int) this.position.y - this.RADIUS, 3* this.RADIUS, 3*this.RADIUS,null);
            //g.fillOval((int) this.position.x - this.RADIUS, (int) this.position.y - this.RADIUS, 2*this.RADIUS, 2*this.RADIUS);

        }
    }

    @Override
    public String toString() {
        return("ASTEROID: " + size);
    }
}
