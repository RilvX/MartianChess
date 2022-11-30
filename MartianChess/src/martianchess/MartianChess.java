package martianchess;

import java.io.*; 
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class MartianChess extends JFrame implements Runnable {
    boolean animateFirstTime = true;
    Image image;
    Graphics2D g;
    
    Image background;
    
    
    Pieces pieces;
    
    boolean gameOver;
    
    static int timeCount;
    
    int score;
    int wins = 0;
    
    static MartianChess frame;
    public static void main(String[] args) {
        frame = new MartianChess();
        frame.setSize(Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        
        Player.Reset();
    }

    public MartianChess() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.BUTTON1 == e.getButton()) {
                    //left button
                    select(e.getX(),e.getY());

                }
                if (e.BUTTON3 == e.getButton()) {
                    //right button
                    reset();
                }
                repaint();
            }
        });

    addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseDragged(MouseEvent e) {
        repaint();
      }
    });

    addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseMoved(MouseEvent e) {
          
        repaint();
      }
    });

        addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                if (gameOver)
                    return;
                
                if (e.VK_UP == e.getKeyCode()) {
                    
                } else if (e.VK_DOWN == e.getKeyCode()) {
                    
                } else if (e.VK_LEFT == e.getKeyCode()) {
                    
                } else if (e.VK_RIGHT == e.getKeyCode()) {
                    
                } else if (e.VK_SPACE == e.getKeyCode()) {
                }
                repaint();
            }
        });
        init();
        start();
    }
    Thread relaxer;
////////////////////////////////////////////////////////////////////////////
    public void init() {
        requestFocus();
    }
////////////////////////////////////////////////////////////////////////////
    public void destroy() {
    }

////////////////////////////////////////////////////////////////////////////
    public void paint(Graphics gOld) {
        if (image == null || Window.xsize != getSize().width || Window.ysize != getSize().height) {
            Window.xsize = getSize().width;
            Window.ysize = getSize().height;
            image = createImage(Window.xsize, Window.ysize);
            g = (Graphics2D) image.getGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        }
//fill background
        g.setColor(Color.black);
        g.fillRect(0, 0, Window.xsize, Window.ysize);

        int x[] = {Window.getX(0), Window.getX(Window.getWidth2()), Window.getX(Window.getWidth2()), Window.getX(0), Window.getX(0)};
        int y[] = {Window.getY(0), Window.getY(0), Window.getY(Window.getHeight2()), Window.getY(Window.getHeight2()), Window.getY(0)};
//fill border
        g.setColor(Color.white);
        g.fillPolygon(x, y, 4);
// draw border
        g.setColor(Color.red);
        g.drawPolyline(x, y, 5);

        if (animateFirstTime) {
            gOld.drawImage(image, 0, 0, null);
            return;
        }

        g.drawImage(background,Window.getX(0),Window.getY(0),Window.getWidth2(),Window.getHeight2(),this);
        
        
        
        //Move to new class
//        if (numPlayers >= 1)
//        {
            g.setColor(Player.getPlayer1().getColor());
            g.drawString("Score = " + score, Window.getWidth2()/4-100, 45);
            g.drawString("Wins = " + score, Window.getWidth2()/4-100, 60);
//        }
//        
//        if (numPlayers >= 2)
//        {
            g.setColor(Player.getPlayer2().getColor());
            g.drawString("Score = " + score, (Window.getWidth2()/4)*3+20, 45);
            g.drawString("Wins = " + score, (Window.getWidth2()/4)*3+20, 60);
//        }
        
        if (Player.getNumPlayers() >= 3)
        {
            g.setColor(Player.getPlayer3().getColor());
            g.drawString("Score = " + score, Window.getWidth2()/4-100, Window.WINDOW_HEIGHT-25);
            g.drawString("Wins = " + score, Window.getWidth2()/4-100,Window.WINDOW_HEIGHT-10); 
        }
//        
       if (Player.getNumPlayers() >= 4)
        {
            g.setColor(Player.getPlayer4().getColor());
            g.drawString("Score = " + score, (Window.getWidth2()/4)*3+20, Window.WINDOW_HEIGHT-25);
            g.drawString("Wins = " + score, (Window.getWidth2()/4)*3+20, Window.WINDOW_HEIGHT-10);
        }
       Player.getPlayer1().getBoard().Draw(g, this);
       Player.getPlayer2().getBoard().Draw(g, this);
       if (Player.getNumPlayers() == 4){
           Player.getPlayer3().getBoard().Draw(g, this);
           Player.getPlayer4().getBoard().Draw(g, this);
       }

       Player.draw(g, this);
        
        
        if (gameOver)        
        {
            g.setColor(Color.red);
            g.setFont (new Font ("Arial",Font.PLAIN, 50));
            g.drawString("Player" + "1" + "Wins", 60, 400);
        }
        
        gOld.drawImage(image, 0, 0, null);
        
    }

////////////////////////////////////////////////////////////////////////////
// needed for     implement runnable
    public void run() {
        while (true) {
            animate();
            repaint();
            double seconds = 0.02;    //time that 1 frame takes.
            int miliseconds = (int) (1000.0 * seconds);
            try {
                Thread.sleep(miliseconds);
            } catch (InterruptedException e) {
            }
        }
    }
/////////////////////////////////////////////////////////////////////////
    public void reset() {
        timeCount = 0;
        gameOver = false;
        score = 0;
        Player.Reset();
    }
/////////////////////////////////////////////////////////////////////////
    public void animate() {
        if (animateFirstTime) {
            animateFirstTime = false;
            if (Window.xsize != getSize().width || Window.ysize != getSize().height) {
                Window.xsize = getSize().width;
                Window.ysize = getSize().height;
            }
            background = Toolkit.getDefaultToolkit().getImage("./woodBackground.JPG");  
            Pieces.Init(); 
            reset();
        }
        
        if (gameOver)
            return;
        
        
        timeCount++;
    }

////////////////////////////////////////////////////////////////////////////
    public void start() {
        if (relaxer == null) {
            relaxer = new Thread(this);
            relaxer.start();
        }
    }
////////////////////////////////////////////////////////////////////////////
    public void stop() {
        if (relaxer.isAlive()) {
            relaxer.stop();
        }
        relaxer = null;
    }
    public void select(int x, int y){
        if (x < Window.getX(Window.getWidth2()/4) + 198 && x > Window.getX(Window.getWidth2()/4) + 2 && y < Window.getYNormal(Window.getHeight2()/4*3 - 23) + 98 && y > Window.getYNormal(Window.getHeight2()/4*3 - 23) - 98)
            System.out.println("Board 1");
        else if (x < Window.getX(Window.getWidth2()/4*3) - 2 && x > Window.getX(Window.getWidth2()/4*3) - 198 && y < Window.getYNormal(Window.getHeight2()/4*3 - 23) + 98 && y > Window.getYNormal(Window.getHeight2()/4*3 - 23) - 98)
            System.out.println("Board 2");
        else if (x < Window.getX(Window.getWidth2()/4) + 198 && x > Window.getX(Window.getWidth2()/4) + 2 && y < Window.getYNormal((Window.getHeight2()/4) + 23) + 98 && y > Window.getYNormal((Window.getHeight2()/4) + 23) - 98)
            System.out.println("Board 3");
        else if (x < Window.getX(Window.getWidth2()/4*3) - 2 && x > Window.getX(Window.getWidth2()/4*3) - 198 && y < Window.getYNormal((Window.getHeight2()/4) + 23) + 98 && y > Window.getYNormal((Window.getHeight2()/4) + 23) - 98)
            System.out.println("Board 4");
    }
}