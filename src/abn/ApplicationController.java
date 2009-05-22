package abn;

/**
 * This is a controller class that all controllers, other than
 * {@link ActionController ActionController}, should extend.
 * 
 * @author Johan Andersson (johan.rejeep@gmail.com)
 */
public abstract class ApplicationController extends ActionController
{
  /**
   * This method is called after the actual action.
   */
  public void afterFilter()
  {}

  /**
   * This method is called before the actual action. Common functionality can be
   * put in this method to keep the code DRY.
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
