package game1;

import utilities.*;

import java.util.ArrayList;
import java.util.List;

import static game1.Constants.DELAY;

public class Game {
    public static final int N_INITIAL_ASTEROIDS = 5;
    public static List<GameObject> objects;
    public static Ship ship;
    public static Keys ctrl;

    private static int score;
    public static boolean endGame;
    public int level =1;
    public int asteroidCount;

    public Game() {
        endGame = false;
        ctrl = new Keys();
        objects = new ArrayList<>();
        newLevel(1);
        ship = new Ship(ctrl);
        objects.add(ship);

    }
    public void newLevel(int level){
        int temp = N_INITIAL_ASTEROIDS;
        temp = temp * level;

        for (int i = 0; i < temp; i++) {
            objects.add(Asteroid.makeRandomAsteroid());
        }
    }

    public void incScore(GameObject object){
        if(object instanceof Asteroid){
            Asteroid obj = (Asteroid) object;
            if(obj.size == 3){
                addScore(100);
            }
            else if(obj.size ==2){
                addScore(200);
            }
            else if(obj.size == 1){
                addScore(300);
            }
        }
    }

    public int getScore(){
        return score;
    }

    public void addScore(int amount){
        score += amount;
        if(score % 10000 == 0){
            ship.addLife(1);
        }
    }

    public static void main(String[] args) throws Exception {
        new Sprite();
        Game game = new Game();
        View view = new View(game);
        new JEasyFrame(view, "Basic Game").addKeyListener(ctrl);
        while (true) {
            if(!game.endGame) {
                game.update();
                view.repaint();
                //SoundManager.BGM();
                Thread.sleep(DELAY);
            }
            else{
                System.out.println("Finished");
                System.exit(1);
            }
        }
    }

    public void update(){
        asteroidCount = 0;
        List<GameObject> alive = new ArrayList<>();
        for(GameObject gameObject : objects){
            gameObject.update();
            if(!gameObject.dead){
                if(gameObject instanceof Asteroid){
                    asteroidCount++;
                }
                alive.add(gameObject);

            }else{
                if(!ship.dead){
                    incScore(gameObject);
                }
                if (gameObject instanceof Asteroid) {
                    Asteroid asteroid = (Asteroid) gameObject;
                    if(asteroid.size == 3){
                        SoundManager.largeAsteroids();
                    }
                    else if(asteroid.size == 2){
                        SoundManager.mediumAteroids();
                    }
                    else{
                        SoundManager.smallAsteroids();
                    }
                    if (asteroid.asteroids != null) {
                        alive.addAll(asteroid.asteroids);
                        asteroid.asteroids = null;
                    }
                }
            }
        }
        if(ship.bullet != null){
            alive.add(ship.bullet);
            ship.bullet = null;
        }

        synchronized (Game.class){
            objects.clear();
            objects.addAll(alive);
        }

        if(asteroidCount <=0){
            level++;
            newLevel(level);
        }

        for(int i=0; i<alive.size()-1; i++){
            for(int j=i+1; j<alive.size(); j++){
                if (!(alive.get(i).getClass() == alive.get(j).getClass())) {
                    if (!alive.get(i).equals(alive.get(j))) {
                        alive.get(i).collisionHandling(alive.get(j));
                    }
                }
            }
        }
    }

}