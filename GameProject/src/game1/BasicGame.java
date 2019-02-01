package game1;

import utilities.BasicController;
import utilities.BasicKeys;
import utilities.JEasyFrame;

import java.util.ArrayList;
import java.util.List;

import static game1.Constants.DELAY;

public class BasicGame {
    public static final int N_INITIAL_ASTEROIDS = 5;
    public static List<BasicAsteroid> asteroids;
    public static BasicShip ship;
    public static BasicKeys ctrl;

    public BasicGame() {
        ctrl = new BasicKeys();
        ship = new BasicShip(ctrl);
        asteroids = new ArrayList<BasicAsteroid>();
        for (int i = 0; i < N_INITIAL_ASTEROIDS; i++) {
            asteroids.add(BasicAsteroid.makeRandomAsteroid());
        }
    }

    public static void main(String[] args) throws Exception {
        BasicGame game = new BasicGame();
        BasicView view = new BasicView(game);
        new JEasyFrame(view, "Basic Game").addKeyListener(ctrl);
        while (true) {
            game.update();
            view.repaint();
            Thread.sleep(DELAY);
        }
    }

    public void update() {
        for (BasicAsteroid a : asteroids) {
            a.update();
        }
        ship.update();
    }
}