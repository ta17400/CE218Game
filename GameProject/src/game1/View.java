package game1;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;


import static game1.Constants.FRAME_WIDTH;
import static game1.Game.ship;


public class View extends JComponent {


    private Game game;
    Image milkyway = Constants.MILKYWAY;
    AffineTransform bgTransf;
    public View(Game game){
        this.game = game;
        double imWidth = milkyway.getWidth(null);
        double imHeight = milkyway.getHeight(null);
        double stretchx = (imWidth > Constants.FRAME_WIDTH? 1 :
                Constants.FRAME_WIDTH/imWidth);
        double stretchy = (imHeight > Constants.FRAME_HEIGHT? 1 :
                Constants.FRAME_HEIGHT/imHeight);
        bgTransf = new AffineTransform();
        bgTransf.scale(stretchx, stretchy);
    }

    @Override
    public void paintComponent(Graphics g0) {
        Graphics2D g = (Graphics2D) g0;
        g.drawImage(milkyway, bgTransf,null);
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
