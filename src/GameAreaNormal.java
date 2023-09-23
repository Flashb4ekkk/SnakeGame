import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GameAreaNormal extends JPanel implements ActionListener{
    private final int SIZE = 320;
    private final int DOT_SIZE = 16;
    private final int ALL_DOTS = 400;
    private Image dot;
    private Image apple;
    private Image dotHead;
    private Image border;
    private int appleX;
    private int appleY;
    private int[] borderX = new int [400];
    private int[] borderY = new int [400];
    private int[] x = new int[ALL_DOTS];
    private int[] y = new int[ALL_DOTS];
    private int dots;
    private Timer timer;
    private int timeFUser;
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean inGame = true;
    private JButton btnRe;
    private int count = 0;

    public GameAreaNormal(int timeFUser){
        this.timeFUser = timeFUser;
        setBackground(Color.pink);
        loadImages();
        initGame();
        addKeyListener(new FieldKeyListener());
        setFocusable(true);
    }

    private void initGame(){
        dots = 3;
        for (int i = 0; i < dots; i++) {
            x[i] = 48 - i*DOT_SIZE;
            y[i] = 48;
        }
        timer = new Timer(timeFUser,this);
        timer.start();
        createApple();
    }

    private void createApple(){
        appleX = new Random().nextInt(20)*DOT_SIZE;
        appleY = new Random().nextInt(20)*DOT_SIZE;
    }

    private void loadImages(){
        ImageIcon iia = new ImageIcon("apple.png");
        this.apple = iia.getImage();
        ImageIcon iid = new ImageIcon("dot.png");
        this.dot = iid.getImage();
        ImageIcon iidh = new ImageIcon("dotHead.png");
        this.dotHead = iidh.getImage();
        ImageIcon iib = new ImageIcon("border.png");
        this.border = iib.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(inGame){
            g.drawImage(apple,appleX,appleY,this);
            for (int i = 0; i < dots; i++) {
                g.drawImage(dot,x[i],y[i],this);
            }
            g.drawImage(dotHead, x[0], y[0], this);
        }
        else {
            String str = "Game Over, you are bezdar";
            g.setColor(Color.black);
            btnRe = new JButton("Restart");
            btnRe.setSize(150, 40);
            btnRe.setLocation(90, 250); btnRe.setVisible(true);
            btnRe.setFont(new Font("Arial", Font.BOLD, 30));
            add(btnRe);
            btnRe.addActionListener(event -> {
                setVisible(false);

                MainWindow mn = new MainWindow();
            });
            g.setFont(new Font("Arial", Font.BOLD, 12) );
            g.drawString(str,85  ,SIZE/2);
        }
    }

    private void move(){
        for (int i = dots; i > 0; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        if(left){
            x[0] -= DOT_SIZE;
        }
        if(right){
            x[0] += DOT_SIZE;
        } if(up){
            y[0] -= DOT_SIZE;
        } if(down){
            y[0] += DOT_SIZE;
        }
    }

    private void checkApple(){
        if(x[0] == appleX && y[0] == appleY){
            dots++;
            createApple();
        }
    }

    private void checkCollisions(){
        for (int i = dots; i >0 ; i--) {
            if(i>4 && x[0] == x[i] && y[0] == y[i]){
                inGame = false;
            }
        }
        if(x[0]>SIZE-1){
            inGame = false;
        }
        if(x[0]<0){
            inGame = false;
        }
        if(y[0]>SIZE-1){
            inGame = false;
        }
        if(y[0]<0){
            inGame = false;
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(inGame){
            checkApple();
            checkCollisions();
            move();
            count++;
            if(count >= 250 ){
                count = 0;
                uppSpeed();
            }
        }
        repaint();
    }
    public void uppSpeed() {
        timeFUser -= 10;
        timer.setDelay(timeFUser);
        System.out.println(timeFUser);
    }
    class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_LEFT && !right){
                left = true;
                up = false;
                down = false;
            }
            if(key == KeyEvent.VK_RIGHT && !left){
                right = true;
                up = false;
                down = false;
            }

            if(key == KeyEvent.VK_UP && !down){
                right = false;
                up = true;
                left = false;
            }
            if(key == KeyEvent.VK_DOWN && !up){
                right = false;
                down = true;
                left = false;
            }
        }
    }
}