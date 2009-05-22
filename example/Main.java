import java.awt.FlowLayout;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;


/**
 * Main class.
 * 
 * @author Johan Andersson (johan.rejeep@gmail.com)
 */
public class Main extends JFrame
{
  public Main()
  {
    setLayout(new FlowLayout());

    GameController gameController = new GameController();

    // Save button that show the default usage.
    JButton save = new JButton("Click here to save the Game!");
    save.addActionListener(gameController);
    save.setName("save");
    add(save);

    // Shows how to use the "method missing" functionality.
    Random generator = new Random();
    String[] fruits = { "apple", "apricot", "orange", "pear", "plum", "peach", "watermelon" };
    int index = generator.nextInt(fruits.length);
    String randomName = fruits[index];

    JButton random = new JButton("Click here to show what random picked!");
    random.addActionListener(gameController);
    random.setName(randomName);
    add(random);

    pack();
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  public static void main(String[] args)
  {
    new Main();
  }
}
