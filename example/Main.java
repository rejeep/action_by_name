import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JButton;

/**
 * Main class.
 *
 * @author Johan Andersson (johan.rejeep@gmail.com)
 */
public class Main extends JFrame
{
  public Main()
  {
    setLayout(new BorderLayout());
    
    GameController gameController = new GameController();

    JButton save = new JButton("Click here to save the Game!");
    save.addActionListener(gameController);
    save.setName("save");
    add(save);
    
    pack();
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }
  
  public static void main(String[] args)
  {
    new Main();
  }
}