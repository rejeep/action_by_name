package abn;

import java.awt.Component;
import java.lang.reflect.Method;
import java.util.EventObject;


/**
 * <p>
 * This class handles all controller actions. All controllers should extend
 * ApplicationController, that in turn extend this class. This means that all
 * controllers will handle events the same way.
 * </p>
 * <h4>Usage</h4>
 * <p>
 * If you want to connect an event to a <code>Component</code>, Say for example
 * a <code>JButton</code>, you add a listener as usual:
 * </p>
 * 
 * <pre>
 * JButton button = new JButton(&quot;Some button&quot;);
 * utton.addActionListener(controller);
 * </pre>
 * <p>
 * Note that the event should <strong>always</strong> go to the controller.
 * </p>
 * <p>
 * After this there's one more thing to do. And that is to give the Component a
 * name with the {@link java.awt.Component#setName(String name) setName} method.
 * </p>
 * 
 * <pre>
 * button.setName(&quot;action&quot;);
 * </pre>
 * <p>
 * The name of the method that will be called in the controller, that was
 * connected to the component, is the one with the same name as the component
 * name. This however, is only true, if there's a method in the controller with
 * that name, taking no arguments. So for the above example the
 * <code>action</code> method in the <code>controller</code> controller will be
 * called when <code>button</code> is clicked.
 * </p>
 * <p>
 * However. If there is no method named action in the controller. A method
 * defined in {@link ApplicationController#methodMissing ApplicationController}
 * called <code>methodMissing</code> is called. This is handy for dynamic action
 * calling. So in your controller, you can override this method to get some
 * other behavior.
 * </p>
 * <p>
 * Before the action is called, a method called
 * {@link ApplicationController#beforeFilter beforeFilter} is called before and
 * a method called {@link ApplicationController#afterFilter afterFilter} is
 * called after. Read comments in {@link ApplicationController
 * ApplicationController} for more information.
 * </p>
 * <p>
 * Two variables are by default available in the controllers.
 * <ul>
 * <li>event - Is the event that was fired.</li>
 * <li>name - Is the name of the component that the event was fired on.</li>
 * </ul>
 * </p>
 * <h4>Troubleshooting</h4>
 * <p>
 * If the above does not work. Start by reading the comments in
 * {@link ApplicationController ApplicationController}.
 * </p>
 * <p>
 * If you done that and it still doesn't work, make sure that this class
 * implements the listener class you want to use. Say for example that you want
 * to add a <code>ActionListener</code> to your controller. Then this class must
 * implement <code>ActionListener</code> and have all methods from that
 * interface. In this case it's only <code>actionPerformed</code>.
 * <p>
 * And in the method you should first set the event instance variable to the
 * event that is passed to the method. And then call
 * {@link ActionController#callActionByName callActionByName}.<br/> Example:
 * 
 * <pre>
 * public void actionPerformed(ActionEvent e)
 * {
 *   this.event = e;
 * 
 *   callActionByName();
 * }
 * </pre>
 * 
 * </p>
 * <p>
 * One more thing to check is if your controller is created or not. Because if
 * you send null to <code>addActionListener</code>, you don't get any errors.
 * </p>
 * 
 * @author Johan Andersson (johan.rejeep@gmail.com)
 */
public abstract class ActionController
{
  /**
   * The event that was fired. Controllers can via this variable get the
   * Component that the event was fired on.
   */
  protected EventObject event;

  /**
   * The name of the component that the event was fired on.
   */
  protected String name;

  // Event actions here...

  /**
   * Calls the method on this class whose name is same as <code>name</code>.
   * 
   * @exception NoSuchMethodException If the default method does not exits.
   * @exception Exception If any other error occur.
   */
  private void callAction() throws NoSuchMethodException, Exception
  {
    callAction(null);
  }

  /**
   * Calls the method on this class whose name is same as <code>action</code>.
   * 
   * @param action a <code>String</code> value.
   * @exception NoSuchMethodException If no method named <code>action</code>
   *              exits.
   * @exception Exception If any other error occur.
   */
  private void callAction(String action) throws NoSuchMethodException, Exception
  {
    if(action == null)
    {
      action = name;
    }

    Method method = this.getClass().getMethod(action);

    method.invoke(this);
  }

  /**
   * Calls the method in the controller with the same name as the components
   * name. If no such method exists, {@link ApplicationController#methodMissing
   * methodMissing} is called.
   */
  private void callActionByName()
  {
    // The action that is to be called is the method with the same
    // name as the name for the component that triggered the event.
    name = ((Component)event.getSource()).getName();

    // Try to call the action with the same name as the component.
    try
    {
      callAction("beforeFilter");
      callAction();
      callAction("afterFilter");
    }
    catch(NoSuchMethodException e)
    {
      // There is no such method, so we "rescue call" methodMissing.
      try
      {
        callAction("methodMissing");
      }
      catch(Exception ex)
      {
        System.err.println("methodMissing must be defined.");
      }
    }
    catch(Exception e)
    {
      // Some other error occurred.
    }
  }
}
