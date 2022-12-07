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

    public static Player winner;
    
    public static boolean game = true;
    
    Pieces pieces;
    
    Pieces selectedPiece;
    int SX;
    int SY;
    
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
                    selectedPiece = select(e.getX(),e.getY());

                }
                if (e.BUTTON3 == e.getButton()) {
                    //right button
                    if (selectedPiece != null && game){
                        int[] movePos = MovePos(e.getX(), e.getY());
                        if (selectedPiece.move(movePos[0], movePos[1], SX, SY)){
                            selectedPiece = null;
                            Player.switchCurrentPlayer();
                        }
                    }
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
                else if (e.VK_ESCAPE == e.getKeyCode()) {
                    reset();
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
        
        g.setFont (new Font ("Times New Roman",Font.PLAIN, 20));
        
        //Move to new class
//        if (numPlayers >= 1)
//        {
            g.setColor(Player.getPlayer1().getColor());
            g.drawString("Score = " + Player.getPlayer1().getScore(), Window.getWidth2()/4-100, 45);
            g.drawString("Wins = " + Player.getPlayer1().getWin(), Window.getWidth2()/4-100, 60);
//        }
//        
//        if (numPlayers >= 2)
//        {
            g.setColor(Player.getPlayer2().getColor());
            g.drawString("Score = " + Player.getPlayer2().getScore(), (Window.getWidth2()/4)*3+20, 45);
            g.drawString("Wins = " + Player.getPlayer2().getWin(), (Window.getWidth2()/4)*3+20, 60);
//        }
        
        if (Player.getNumPlayers() == 4)
        {
            g.setColor(Player.getPlayer4().getColor());
            g.drawString("Score = " + Player.getPlayer4().getScore(), Window.getWidth2()/4-100, Window.WINDOW_HEIGHT-25);
            g.drawString("Wins = " + Player.getPlayer4().getWin(), Window.getWidth2()/4-100,Window.WINDOW_HEIGHT-10); 
        }
//        
       if (Player.getNumPlayers() == 4)
        {
            g.setColor(Player.getPlayer3().getColor());
            g.drawString("Score = " + Player.getPlayer3().getScore(), (Window.getWidth2()/4)*3+20, Window.WINDOW_HEIGHT-25);
            g.drawString("Wins = " + Player.getPlayer3().getWin(), (Window.getWidth2()/4)*3+20, Window.WINDOW_HEIGHT-10);
        }
       Board.draw(g, this);

       Player.draw(g, this);
        
        
        if (!game)        
        {
            winner = Player.getCurrentPlayer();
            g.setColor(Color.WHITE);
            g.setFont (new Font ("Arial",Font.PLAIN, 50));
            if (winner == Player.getPlayer1())
                g.drawString("Player 1 Wins", Window.getWidth2()/4 + 40, Window.getHeight2()/2 + 60);
            else if (winner == Player.getPlayer2())
                g.drawString("Player 2 Wins", Window.getWidth2()/4 + 40, Window.getHeight2()/2 + 40);
            else if (winner == Player.getPlayer3())
                g.drawString("Player 4 Wins", Window.getWidth2()/4 + 40, Window.getHeight2()/2 + 40);
            else if (winner == Player.getPlayer4())
                g.drawString("Player 3 Wins", Window.getWidth2()/4 + 40, Window.getHeight2()/2 + 40);
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
        game = true;
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
    public static void gameOver(){
        game = false;
        winner = Player.getPlayer1();
        if (Player.getPlayer2().getScore() > winner.getScore())
            winner = Player.getPlayer2();
        if (Player.getPlayer3().getScore() > winner.getScore())
            winner = Player.getPlayer3();
        if (Player.getPlayer4().getScore() > winner.getScore())
            winner = Player.getPlayer4();
        winner.addWin();
    }
    public Pieces select(int x, int y){
        Board board;
        if (Player.getNumPlayers() == 4){
            if (x < Window.getX(Window.getWidth2()/4) + 198 
                    && x > Window.getX(Window.getWidth2()/4) + 2 
                    && y < Window.getYNormal(Window.getHeight2()/4*3 - 23) + 98 
                    && y > Window.getYNormal(Window.getHeight2()/4*3 - 23) - 98
                    && Player.getCurrentPlayer() == Player.getPlayer1()){
                System.out.println("Board 1");
                for (int xpos = 0; xpos < Board.numColumns(); xpos ++){
                    for (int ypos = 0; ypos < Board.numRows(); ypos ++){
                        if (x < Window.getX((Window.getWidth2()/4) + (xpos * 48) + 28) + 24 
                                && x > Window.getX((Window.getWidth2()/4) + (xpos * 48) + 28) - 24 
                                && y < Window.getYNormal(((Window.getHeight2()/4)*3) - (ypos * 47) + 48) + 24 
                                && y > Window.getYNormal(((Window.getHeight2()/4)*3) - (ypos * 47) + 48) - 24){
                            System.out.println(xpos + ", " + ypos);
                            SX = xpos;
                            SY = ypos;
                            return(Board.get(xpos, ypos));
                        }
                    }
                }
            }
            else if (x < Window.getX(Window.getWidth2()/4*3) - 2 
                    && x > Window.getX(Window.getWidth2()/4*3) - 198 
                    && y < Window.getYNormal(Window.getHeight2()/4*3 - 23) + 98 
                    && y > Window.getYNormal(Window.getHeight2()/4*3 - 23) - 98
                    && Player.getCurrentPlayer() == Player.getPlayer2()){
                System.out.println("Board 2");
                for (int xpos = 0; xpos < Board.numColumns(); xpos ++){
                    for (int ypos = 0; ypos < Board.numRows(); ypos ++){
                        if (x < Window.getX((Window.getWidth2()/4) * 3 - (xpos * 48) - 29) + 24
                                && x > Window.getX((Window.getWidth2()/4) * 3 - (xpos * 48) - 29) - 24
                                && y < Window.getYNormal(((Window.getHeight2()/4)*3) - (ypos * 47) + 48) + 24
                                && y > Window.getYNormal(((Window.getHeight2()/4)*3) - (ypos * 47) + 48) - 24){
                            System.out.println(xpos + ", " + ypos);
                            SX = 7 - xpos;
                            SY = ypos;
                            return(Board.get(7 - xpos, ypos));
                        }
                    }
                }
            }
            else if (x < Window.getX(Window.getWidth2()/4) + 198 
                    && x > Window.getX(Window.getWidth2()/4) + 2 
                    && y < Window.getYNormal((Window.getHeight2()/4) + 23) + 98 
                    && y > Window.getYNormal((Window.getHeight2()/4) + 23) - 98
                    && Player.getCurrentPlayer() == Player.getPlayer4()){
                System.out.println("Board 4");
                for (int xpos = 0; xpos < Board.numColumns(); xpos ++){
                    for (int ypos = 0; ypos < Board.numRows(); ypos ++){
                        if (x < Window.getX((Window.getWidth2()/4) + (xpos * 48) + 28) + 24
                                && x > Window.getX((Window.getWidth2()/4) + (xpos * 48) + 28) - 24
                                && y < Window.getYNormal(75 + (ypos * 47)) + 24
                                && y > Window.getYNormal(75 + (ypos * 47)) - 24){
                            System.out.println(xpos + ", " + ypos);
                            SX = xpos;
                            SY = 7 - ypos;
                            return(Board.get(xpos, 7 - ypos));
                        }
                    }
                }
            }
            else if (x < Window.getX(Window.getWidth2()/4*3) - 2 
                    && x > Window.getX(Window.getWidth2()/4*3) - 198 
                    && y < Window.getYNormal((Window.getHeight2()/4) + 23) + 98 
                    && y > Window.getYNormal((Window.getHeight2()/4) + 23) - 98
                    && Player.getCurrentPlayer() == Player.getPlayer3()){
                System.out.println("Board 3");
                for (int xpos = 0; xpos < Board.numColumns(); xpos ++){
                    for (int ypos = 0; ypos < Board.numRows(); ypos ++){
                        if (x <  Window.getX((Window.getWidth2()/4) * 3 - (xpos * 48) - 29) + 24 
                                && x >  Window.getX((Window.getWidth2()/4) * 3 - (xpos * 48) - 29) - 24 
                                && y < Window.getYNormal(75 + (ypos * 47)) + 24 
                                && y > Window.getYNormal(75 + (ypos * 47)) - 24){
                            System.out.println(xpos + ", " + ypos);
                            SX = 7 - xpos;
                            SY = 7 - ypos;
                            return(Board.get(7 - xpos, 7 - ypos));
                        }
                    }
                }
            }
        }
        else if (Player.getNumPlayers() == 2){
            if (x < Window.getX(Window.getWidth2()/2) + 98 
                    && x > Window.getX(Window.getWidth2()/2) - 98 
                    && y < Window.getYNormal(Window.getHeight2()/4*3 - 25) + 98 
                    && y > Window.getYNormal(Window.getHeight2()/4*3 - 25) - 98){
                System.out.println("Board 1");
                for (int xpos = 0; xpos < Board.numColumns(); xpos ++){
                    for (int ypos = 0; ypos < Board.numRows(); ypos ++){
                        if (x < Window.getX((Window.getWidth2()/4) + (xpos * 48) + 126) + 24 
                                && x > Window.getX((Window.getWidth2()/4) + (xpos * 48) + 126) - 24 
                                && y < Window.getYNormal(((Window.getHeight2()/4)*3) - (ypos * 47) + 47) + 24 
                                && y > Window.getYNormal(((Window.getHeight2()/4)*3) - (ypos * 47) + 47) - 24){
                            System.out.println(xpos + ", " + ypos);
                            return(Board.get(xpos, ypos));
                        }
                    }
                }
            }
            if (x < Window.getX(Window.getWidth2()/2) + 98 
                    && x > Window.getX(Window.getWidth2()/2) - 98 
                    && y < Window.getYNormal((Window.getHeight2()/4) + 25) + 98 
                    && y > Window.getYNormal((Window.getHeight2()/4) + 25) - 98){
                System.out.println("Board 2");
                for (int xpos = 0; xpos < Board.numColumns(); xpos ++){
                    for (int ypos = 0; ypos < Board.numRows(); ypos ++){
                        if (x < Window.getX((Window.getWidth2()/2) - (xpos * 48) + 71) + 24 
                                && x > Window.getX((Window.getWidth2()/2) - (xpos * 48) + 71) - 24 
                                && y < Window.getYNormal(77 + (ypos * 47)) + 24 
                                && y > Window.getYNormal(77 + (ypos * 47)) - 24){
                            System.out.println(xpos + ", " + ypos);
                            return(Board.get(xpos, ypos));
                        }
                    }
                }
            }
        }
        return(null);
    }
    
    public int[] MovePos(int x, int y){
        int[] board = new int[2];
        if (Player.getNumPlayers() == 4){
            if (x < Window.getX(Window.getWidth2()/4) + 198 
                    && x > Window.getX(Window.getWidth2()/4) + 2 
                    && y < Window.getYNormal(Window.getHeight2()/4*3 - 23) + 98 
                    && y > Window.getYNormal(Window.getHeight2()/4*3 - 23) - 98){
                System.out.println("Board 1");
                for (int xpos = 0; xpos < Board.numColumns(); xpos ++){
                    for (int ypos = 0; ypos < Board.numRows(); ypos ++){
                        if (x < Window.getX((Window.getWidth2()/4) + (xpos * 48) + 28) + 24 
                                && x > Window.getX((Window.getWidth2()/4) + (xpos * 48) + 28) - 24 
                                && y < Window.getYNormal(((Window.getHeight2()/4)*3) - (ypos * 47) + 48) + 24 
                                && y > Window.getYNormal(((Window.getHeight2()/4)*3) - (ypos * 47) + 48) - 24){
                            System.out.println(xpos + ", " + ypos);
                            board[0] = xpos;
                            board[1] = ypos;
                            return(board);
                        }
                    }
                }
            }
            else if (x < Window.getX(Window.getWidth2()/4*3) - 2 
                    && x > Window.getX(Window.getWidth2()/4*3) - 198 
                    && y < Window.getYNormal(Window.getHeight2()/4*3 - 23) + 98 
                    && y > Window.getYNormal(Window.getHeight2()/4*3 - 23) - 98){
                System.out.println("Board 2");
                for (int xpos = 0; xpos < Board.numColumns(); xpos ++){
                    for (int ypos = 0; ypos < Board.numRows(); ypos ++){
                        if (x < Window.getX((Window.getWidth2()/4) * 3 - (xpos * 48) - 29) + 24
                                && x > Window.getX((Window.getWidth2()/4) * 3 - (xpos * 48) - 29) - 24
                                && y < Window.getYNormal(((Window.getHeight2()/4)*3) - (ypos * 47) + 48) + 24
                                && y > Window.getYNormal(((Window.getHeight2()/4)*3) - (ypos * 47) + 48) - 24){
                            System.out.println(xpos + ", " + ypos);
                            board[0] = 7 - xpos;
                            board[1] = ypos;
                            return(board);
                        }
                    }
                }
            }
            else if (x < Window.getX(Window.getWidth2()/4) + 198 
                    && x > Window.getX(Window.getWidth2()/4) + 2 
                    && y < Window.getYNormal((Window.getHeight2()/4) + 23) + 98 
                    && y > Window.getYNormal((Window.getHeight2()/4) + 23) - 98){
                System.out.println("Board 3");
                for (int xpos = 0; xpos < Board.numColumns(); xpos ++){
                    for (int ypos = 0; ypos < Board.numRows(); ypos ++){
                        if (x < Window.getX((Window.getWidth2()/4) + (xpos * 48) + 28) + 24
                                && x > Window.getX((Window.getWidth2()/4) + (xpos * 48) + 28) - 24
                                && y < Window.getYNormal(75 + (ypos * 47)) + 24
                                && y > Window.getYNormal(75 + (ypos * 47)) - 24){
                            System.out.println(xpos + ", " + ypos);
                            board[0] = xpos;
                            board[1] = 7 - ypos;
                            return(board);
                        }
                    }
                }
            }
            else if (x < Window.getX(Window.getWidth2()/4*3) - 2 
                    && x > Window.getX(Window.getWidth2()/4*3) - 198 
                    && y < Window.getYNormal((Window.getHeight2()/4) + 23) + 98 
                    && y > Window.getYNormal((Window.getHeight2()/4) + 23) - 98){
                System.out.println("Board 4");
                for (int xpos = 0; xpos < Board.numColumns(); xpos ++){
                    for (int ypos = 0; ypos < Board.numRows(); ypos ++){
                        if (x <  Window.getX((Window.getWidth2()/4) * 3 - (xpos * 48) - 29) + 24 
                                && x >  Window.getX((Window.getWidth2()/4) * 3 - (xpos * 48) - 29) - 24 
                                && y < Window.getYNormal(75 + (ypos * 47)) + 24 
                                && y > Window.getYNormal(75 + (ypos * 47)) - 24){
                            System.out.println(xpos + ", " + ypos);
                            board[0] = 7 - xpos;
                            board[1] = 7 - ypos;
                            return(board);
                        }
                    }
                }
            }
        }
        else if (Player.getNumPlayers() == 2){
            if (x < Window.getX(Window.getWidth2()/2) + 98 
                    && x > Window.getX(Window.getWidth2()/2) - 98 
                    && y < Window.getYNormal(Window.getHeight2()/4*3 - 25) + 98 
                    && y > Window.getYNormal(Window.getHeight2()/4*3 - 25) - 98){
                System.out.println("Board 1");
                board[0] = 0;
                for (int xpos = 0; xpos < Board.numColumns(); xpos ++){
                    for (int ypos = 0; ypos < Board.numRows(); ypos ++){
                        if (x < Window.getX((Window.getWidth2()/4) + (xpos * 48) + 126) + 24 
                                && x > Window.getX((Window.getWidth2()/4) + (xpos * 48) + 126) - 24 
                                && y < Window.getYNormal(((Window.getHeight2()/4)*3) - (ypos * 47) + 47) + 24 
                                && y > Window.getYNormal(((Window.getHeight2()/4)*3) - (ypos * 47) + 47) - 24){
                            System.out.println(xpos + ", " + ypos);
                            board[1] = xpos;
                            board[2] = ypos;
                            return(board);
                        }
                    }
                }
            }
            else if (x < Window.getX(Window.getWidth2()/2) + 98 
                    && x > Window.getX(Window.getWidth2()/2) - 98 
                    && y < Window.getYNormal((Window.getHeight2()/4) + 25) + 98 
                    && y > Window.getYNormal((Window.getHeight2()/4) + 25) - 98){
                System.out.println("Board 2");
                board[0] = 1;
                for (int xpos = 0; xpos < Board.numColumns(); xpos ++){
                    for (int ypos = 0; ypos < Board.numRows(); ypos ++){
                        if (x < Window.getX((Window.getWidth2()/2) - (xpos * 48) + 71) + 24
                                && x > Window.getX((Window.getWidth2()/2) - (xpos * 48) + 71) - 24
                                && y < Window.getYNormal(77 + (ypos * 47)) + 24
                                && y > Window.getYNormal(77 + (ypos * 47)) - 24){
                            System.out.println(xpos + ", " + ypos);
                            board[1] = xpos;
                            board[2] = ypos;
                            return(board);
                        }
                    }
                }
            }
        }
        return(null);
    }    
}
