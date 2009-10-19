package abn;

/**
 * <p>
 * This controller class should be the super class of all application
 * controllers, other than {@link ActionController ActionController}.
 * </p>
 * 
 * <p>
 * Put all event listener methods in this class. All listeners must do two
 * things:
 * <ul>
 * <li><b>Set the event variable:</b> this.event = e;</li>
 * <li><b>Call the action:</b> callActionByName();</li>
 * </ul>
 * </p>
 * 
 * <h1>Example</h1>
 * 
 * <pre>
 * public void actionPerformed(ActionEvent e)
 * {
 *   this.event = e;
 * 
 *   // ...
 * 
 *   callActionByName();
 * }
 * </pre>
 * 
 * @author Johan Andersson (johan.rejeep@gmail.com)
 */
public abstract class ApplicationController extends ActionController
{
  // Event actions here...
}
