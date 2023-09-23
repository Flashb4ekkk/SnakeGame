import javax.swing.*;

public class MainWindow extends JFrame {
    public MainWindow (){
        setTitle("Snake");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(780, 400);
        setSize(340,360);
        add(new StarterWindow()); //new GameArea()
        setVisible(true);

        System.out.println("it's will be try for next commit");
        System.out.println("123");
        System.out.println("123");

        TryForGitProject obj1 = new TryForGitProject();
        TryForGitProject obj2 = new TryForGitProject(3);

    }
}

