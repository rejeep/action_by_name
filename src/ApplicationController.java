/**
 * This is a controller class that all controllers, other than {@link
 * ActionController ActionController}, should extend.
 *
 * @author Johan Andersson (johan.rejeep@gmail.com)
 */
public abstract class ApplicationController extends ActionController
{
  /**
   * <p>If there's an event connected that is to be called by the
   * component name and there's no method with the same name, this
   * method is called. Override it in your controllers.</p>
   */
  public void methodMissing() {}
}
