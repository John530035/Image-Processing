package controller;

/**
 * Defines the operations that the user choose from through the graphical interface. Retrieves
 * information from the model and displays it in the view.
 */
public interface Features {

  /**
   * Handles the image file at the specified file path. Loads the image into the program,
   * retrieves the image to displays to the user, and generates a histogram to display to the user.
   * @param filePath the location of the file to load
   */
  void load(String filePath);

  /**
   * Brightens the image by the given increment. Retrieves the brightened image to display
   * to the user and generates a new histogram to display to the user.
   * @param increment the increment by which to brighten the image
   */
  void brighten(String increment);

  /**
   * Vertically flips the image. Retrieves the flipped image to display to the user and
   * generates a new histogram to display to the user.
   */
  void verticalFlip();

  /**
   * Horizontally flips the image. Retrieves the flipped image to display to the user and
   * generates a new histogram to display to the user.
   */
  void horizontalFlip();

  /**
   * Blurs the image. Retrieves the blurred image to display to the user and
   * generates a new histogram to display to the user.
   */
  void blur();

  /**
   * Sharpens the image. Retrieves the sharpened image to display to the user and
   * generates a new histogram to display to the user.
   */
  void sharpen();

  /**
   * Gives the image a sepia tone. Retrieves the new image to display to the user and
   * generates a new histogram to display to the user.
   */
  void sepia();

  /**
   * Greyscales the image. Retrieves the greyscaled image to display to the user and
   * generates a new histogram to display to the user.
   */
  void greyscale();

  /**
   * Visualizes the component of the image specifed by the mode. Retrieves the new image to
   * display to the user and generates a new histogram to display to the user.
   * @param mode the component of the image to visualize
   */
  void componentVisualization(String mode);

  /**
   * Saves the image to the given file path.
   * @param filePath the file to save the image to
   */
  void save(String filePath);
}
