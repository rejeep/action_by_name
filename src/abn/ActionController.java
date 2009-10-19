package abn;

import java.awt.Component;
import java.lang.reflect.Method;
import java.util.EventObject;


/**
 * <p>
 * Inspired by Ruby On Rails (http://www.rubyonrails.org), this class along with
 * {@link ApplicationController ApplicationController}, provides a simple way to
 * call controller actions by just setting a name on components.
 * </p>
 * <p>
 * This class handles all controller actions. All controllers should extend
 * {@link ApplicationController ApplicationController}, which in turn extend
 * this class. This will result in that all controllers will handle some events
 * the same way.
 * </p>
 * <h1>Usage</h1>
 * <p>
 * If you want to connect a <code>Component</code> (say for example a
 * <code>JButton</code>) event to a controller, you add a listener as usual:
 * </p>
 * 
 * <pre>
 * JButton button = new JButton(&quot;Some button&quot;);
 * button.addActionListener(controller);
 * </pre>
 * <p>
 * Note that the event should <strong>always</strong> go to the controller.
 * </p>
 * <p>
 * After this there's one more thing to do. And that is to give the
 * <code>Component</code> a name with the
 * {@link java.awt.Component#setName(String name) setName} method.
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
 * <code>action</code> name method in the controller named
 * <code>controller</code> will be called when <code>button</code> is clicked.
 * </p>
 * <p>
 * However. If there is no method named <code>action</code> in the controller. A
 * "method missing" method ({@link ActionController#methodMissing methodMissing}
 * ) is called. This is handy for dynamic action handling. So in your
 * controller, you can override this method to get some other behavior.
 * </p>
 * <p>
 * Before an action is called, {@link ActionController#beforeFilter
 * beforeFilter} is called. And after {@link ActionController#afterFilter
 * afterFilter}. Read comments for resp. method for more information.
 * </p>
 * <p>
 * Two variables are by default available in the sub-controllers.
 * <ul>
 * <li>event - The event that was fired.</li>
 * <li>name - The name of the component that the event was fired on.</li>
 * </ul>
 * </p>
 * <h1>Troubleshooting</h1>
 * <p>
 * If the above does not work. Start by reading the comments in
 * {@link ApplicationController ApplicationController}.
 * </p>
 * <p>
 * If you done that and it still doesn't work, make sure that the application
 * controller implements the listener class you want to use. Say for example
 * that you want to add a <code>ActionListener</code> to your controller. Then
 * {@link ApplicationController ApplicationController} must implement
 * <code>ActionListener</code> and override all methods in that interface. In
 * this case it's only <code>actionPerformed</code>.
 * </p>
 * 
 * <p>
 * One more thing to check is if your controller object is created or not.
 * Because if you send null to <code>addActionListener</code>, you don't get any
 * errors.
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
      methodMissing();
    }
    catch(NullPointerException e)
    {
      methodMissing();
    }
    catch(Exception e)
    {
      // Some other error occurred.
      e.printStackTrace();
    }
  }

  /**
   * This method is called after the actual action.
   */
  public void afterFilter()
  {}

  /**
   * This method is called before the actual action.
   */
  public void beforeFilter()
  {}

  /**
   * If there's an event connected that is to be called by the component name
   * and there's no method with the same name, this method is called. Override
   * it in your controllers.
   */
  public void methodMissing()
  {}
}
