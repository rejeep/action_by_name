/**
 * Main class.
 *
 * @author Johan Andersson (johan.rejeep@gmail.com)
 */
public class Main extends JFrame
{
  public Main()
  {
    setLayout( new BorderLayout() );
    
    Controller controller = new Controller();
    
    JButton save = new JButton();
    save.addActionListener( controller );
    save.setName("save");
    add(save);
    
    pack();
    setVisible( true );
    setDefaultCloseOperation( EXIT_ON_CLOSE );
  }
  
  public static void main( String[] args )
  {
    new Main();
  }
}