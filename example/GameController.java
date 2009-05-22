import abn.ApplicationController;


/**
 * TODO: Extend example with before and after filters.
 * 
 * Game controller class.
 * 
 * @author Johan Andersson (johan.rejeep@gmail.com)
 */
public class GameController extends ApplicationController
{
  /**
   * Is called when the user clicks the save button.
   */
  public void save()
  {
    System.out.println("Saving the game...");
  }

  /**
   * Is called when the user click the random button.
   */
  public void methodMissing()
  {
    // The variable name is set in ActionController.
    System.out.println("Random: " + name);
  }
}
