import javax.swing.*;

public class MainWindow extends JFrame {
    public MainWindow (){
        setTitle("Snake");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(780, 400);
        setSize(340,360);
        add(new StarterWindow()); //new GameArea()
        setVisible(true);

    }
}

