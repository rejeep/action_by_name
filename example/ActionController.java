import java.awt.Component;
import java.lang.reflect.Method;
import java.util.EventObject;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Comments are removed to make it easier to read.
 *
 * @author Johan Andersson (johan.rejeep@gmail.com)
 */
public abstract class ActionController implements ActionListener
{
  protected EventObject event;
  protected String name;

  public void actionPerformed( ActionEvent e )
  {
    this.event = e;

    callActionByName();
  }


  private void callActionByName()
  {
    name = ((Component)event.getSource()).getName();

    try
    {
      callAction();
    }
    catch(NoSuchMethodException e)
    {
      try
      {
        callAction("methodMissing");
      }
      catch(Exception ex)
      {
        System.err.println("methodMissing must be defined.");
      }
    }
    catch(Exception e) {}
  }

  private void callAction() throws NoSuchMethodException, Exception
  {
    callAction(null);
  }

  private void callAction(String action) throws NoSuchMethodException, Exception
  {
    if(action == null)
    {
      action = name;
    }

    Method method = this.getClass().getMethod(action);

    method.invoke(this);
  }
}