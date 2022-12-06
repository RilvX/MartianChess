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

    boolean gameStart;
    boolean showStart;
    boolean showInst;
    boolean showPlayers;
    boolean hoverOne;
    boolean hoverTwo;
    int xpos;
    int ypos;

    
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

                    if (e.getX() >= 60 && e.getX() <= 310 && e.getY() >= 300 && e.getY() <= 370){
                        showPlayers = true;
                    }
                    if (e.getX() >= 60 && e.getX() <= 310 && e.getY() >= 400 && e.getY() <= 470){
                        showInst = true;
                    }

                    if (e.getX() >= 375 && e.getX() <= 450 && e.getY() >= 300 && e.getY() <= 375){
                        gameStart = false;
                    }

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
        if (e.getX() >= 60 && e.getX() <= 310 && e.getY() >= 300 && e.getY() <= 370){
            hoverOne = true;
        }
        else
        {
            hoverOne = false;
        }

        if (e.getX() >= 60 && e.getX() <= 310 && e.getY() >= 400 && e.getY() <= 470){
            hoverTwo = true;
        }
        else{
            hoverTwo = false;
        }


        xpos = e.getX();
        ypos = e.getY();
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
        
        for (Pieces obj : Pieces.pieces)
            obj.Draw(g,this);
        
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
        
        if (gameOver)        
        {
            g.setColor(Color.red);
            g.setFont (new Font ("Arial",Font.PLAIN, 50));
            g.drawString("Player" + "1" + "Wins", 60, 400);
        }

        if (gameStart)
        {
            g.setColor(Color.red);
            g.fillRect(0, 0, Window.xsize, Window.ysize);

            g.setColor(Color.black);
            g.setFont (new Font ("Arial",Font.PLAIN, 50));
            
            g.drawString("Welcome to Martian Chess!", (Window.getWidth2()/8), (Window.getHeight2()/2));

            g.setFont (new Font ("Arial",Font.PLAIN, 20));
            g.drawString("By: Thomas, Lucas, Caiden", (Window.getWidth2()/8), (Window.getHeight2()/3));

            g.setFont (new Font ("Arial",Font.PLAIN, 30));

            g.setColor(Color.black);
            g.fillRect(Window.getWidth2()/10, 295, 250, 75);
            g.fillRect(Window.getWidth2()/10, 395, 250, 75);

            if (hoverOne)
            {
                g.setColor(Color.white);
                g.fillRect(Window.getWidth2()/10, 295, 250, 75);
            }

            if (hoverTwo)
            {
                g.setColor(Color.white);
                g.fillRect(Window.getWidth2()/10, 395, 250, 75);
            }

            g.setColor(Color.blue);
            g.drawString("PLAY", (Window.getWidth2()/8), (Window.getHeight2() - 150));
            g.drawString("Intructions", (Window.getWidth2()/8), (Window.getHeight2() - 50));

            if (showPlayers)
            {
                g.setColor(Color.black);
                g.fillRect(375, 295, 75, 75);
                g.fillRect(475, 295, 75, 75);
                g.fillRect(575, 295, 75, 75);
                g.fillRect(675, 295, 75, 75);

                g.setColor(Color.white);
                g.drawString("1", (Window.getWidth2() - 400), (Window.getHeight2() - 150));
                g.drawString("2", (Window.getWidth2() - 300), (Window.getHeight2() - 150));
                g.drawString("3", (Window.getWidth2() - 200), (Window.getHeight2() - 150));
                g.drawString("4", (Window.getWidth2() - 100), (Window.getHeight2() - 150));
            }
            if (showInst)
            {
            g.setFont (new Font ("Arial",Font.PLAIN, 20));
            g.setColor(Color.black);
            g.drawString("Use arrow keys and enter for selection.", (Window.getWidth2() - 400), (Window.getHeight2() - 150));
            }

        }
        g.setFont (new Font ("Arial",Font.PLAIN, 10));
        g.drawString("X: " + xpos, (Window.getWidth2()/4)*3+20, Window.WINDOW_HEIGHT-25);
        g.drawString("Y: " + ypos, (Window.getWidth2()/4)*3+20, Window.WINDOW_HEIGHT-10);
        
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
        gameStart = true;
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
}