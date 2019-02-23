package game1;

import javax.swing.*;
import java.awt.*;

import static game1.Constants.BG_COLOR;
import static game1.Constants.FRAME_WIDTH;
import static game1.Game.ship;


public class View extends JComponent {


    private Game game;

    public View(Game game){
        this.game = game;
    }

    @Override
    public void paintComponent(Graphics g0) {
        Graphics2D g = (Graphics2D) g0;
        g.setColor(BG_COLOR);
        g.fillRect(0, 0, getWidth(), getHeight());
        synchronized (Game.class){
            for (GameObject object : game.objects)
                object.draw(g);
        }
        g.setColor(Color.WHITE);
        g.drawString("Score: "+ game.getScore(),10,20);

        g.drawString("Lives: "+ ship.getLife(),FRAME_WIDTH -50,20);
    }

     @Override
    public Dimension getPreferredSize(){
        return Constants.FRAME_SIZE;
     }
}
