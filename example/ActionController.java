/**
 * <code>ActionController</code> with out comments and other methods
 * to make it more readable.
 *
 *
 * @author Johan Andersson (johan.rejeep@gmail.com)
 */
public class ActionController implements ActionListener
{
  // ...
  
  public void actionPerformed(ActionEvent e)
  {
    this.event = e;

    callActionByName();
  }
  
  // ...
}