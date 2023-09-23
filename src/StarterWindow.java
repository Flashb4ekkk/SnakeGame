import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class StarterWindow extends JPanel implements ActionListener {
    private Image dot;
    private Image apple;
    private JButton btnS;
    private JButton btnSHM;
    public StarterWindow() {
        setBackground(Color.pink);
        loadImages();
        setFocusable(true);
        btnS = new JButton("Play");
        btnSHM = new JButton("Play Hard Mod");
        btnS.setLocation(30, 350); btnS.setSize(80, 40);
        btnS.setFont(new Font("Arial", Font.BOLD, 30));
        btnSHM.setLocation(150, 350); btnSHM.setSize(80, 40);
        btnSHM.setFont(new Font("Arial", Font.BOLD, 30));
        btnS.setVisible(true); add(btnSHM);
        btnSHM.setVisible(true); add(btnS);
        btnS.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                setVisible(false);
                JFrame jf = new JFrame("Snake");
                jf.add(new GameAreaNormal(150)); jf.setVisible(true);
                jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
                jf.setLocation(780, 400);
                jf.setSize(340,360);

            }
        });
        btnSHM.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                setVisible(false);
                JFrame jf = new JFrame("Snake");
                jf.add(new GameAreaHard(100, 7)); jf.setVisible(true);
                jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
                jf.setLocation(780, 400);
                jf.setSize(340,360);
            }
        });
    }

    public void loadImages() {
        ImageIcon iia = new ImageIcon("apple.png");
        apple = iia.getImage();
        ImageIcon iid = new ImageIcon("dot.png");
        dot = iid.getImage();
    }

    @Override
    public void actionPerformed(ActionEvent e) {



    }
}

