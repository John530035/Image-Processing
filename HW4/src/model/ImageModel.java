package model;

/**
 * A model that houses the functionality of the image processing program. The model
 * allows the user to load images, perform processing operations on them, and save them.
 * The functionality of the model occurs when prompted by the controller.
 */
public interface ImageModel {

  /**
   * Loads the given file image into the program and associates it with the given version name.
   *
   * @param version the name which this given image file will be associated with
   * @param image   the image being loaded into the program
   */
  void load(String version, ImageFile image);

  /**
   * Returns the image associated with the given version name.
   *
   * @param version the name of the image to be returned
   * @return the image assocoated with the given version name
   * @throws IllegalArgumentException if the program does not have an image associated with
   *                                  the given name
   */
  ImageFile findFile(String version) throws IllegalArgumentException;

  /**
   * Greyscales the image associated with the give version using the given mode.
   *
   * @param mode        the kind of greyscale to be performed
   * @param baseVersion the name of the image to be greyscaled
   * @return a new image that has been greyscaled
   * @throws IllegalArgumentException if the program does not have an image associated with
   *                                  the given name
   */
  ImageFile greyScale(String mode, String baseVersion) throws IllegalArgumentException;

  /**
   * Flips the image associated with the given name along the vertical axis.
   *
   * @param baseVersion the name of the image to be flipped
   * @return a new image that has been flipped horizontally
   * @throws IllegalArgumentException if the program does not have an image associated with
   *                                  the given name
   */
  ImageFile horizontalFlip(String baseVersion) throws IllegalArgumentException;

  /**
   * Flips the image associated with the given name along the horizontal axis.
   *
   * @param baseVersion the name of the image to be flipped
   * @return an new image that has been flipped vertically
   * @throws IllegalArgumentException if the program does not have an image associated with
   *                                  the given name
   */
  ImageFile verticalFlip(String baseVersion) throws IllegalArgumentException;

  /**
   * Brightens or darkens the image associated with the given name/version.
   *
   * @param baseVersion the name of the photo to be brightened/darkened
   * @param increment   the value by which to brighten or darken this image
   *                    positive value to brighten the image
   *                    negative value to darkend the image
   * @return a new image that has been brightened/darkened
   * @throws IllegalArgumentException if the program does not have an image associated with
   *                                  the given name
   */
  ImageFile brighten(String baseVersion, int increment) throws IllegalArgumentException;

  /**
   * Filters the image associated with the given name. This method supports both
   * blurring and image and sharpening and image/making it clearer.
   *
   * @param baseVersion the name of the image to be filtered
   * @param mode        the mode of filtering
   *                    the mode can be blur to blur the image
   *                    the mode can be sharpen to sharpen the image
   * @return the new ImageFile that has been filtered
   * @throws IllegalArgumentException if the program does not have an image associated with the
   *                                  given name
   *                                  if the given mode is not a valid filtering mode
   */
  ImageFile filter(String baseVersion, String mode) throws IllegalArgumentException;

  /**
   * Performs a color transformation on the image with the given name. This method
   * supports both greyscaling an image and giving an image a sepia tone.
   *
   * @param baseVersion the name of the image to be operated on
   * @param mode        the color transformation mode
   *                    the mode can be greyscale to greyscale the image
   *                    the mode can be sepia to give the image a sepia tone
   * @return the new ImageFile on which the color transformation has been performed
   * @throws IllegalArgumentException if the program does not have an image associated with
   *                                  the given name
   *                                  if the given mode is not a valid color transformation mode
   */
  ImageFile colorTransformation(String baseVersion, String mode) throws IllegalArgumentException;
}
