package model;

/**
 * Represents a modifiable digital image file. This interface defines all of the operations
 * necessary for altering the contents of an image file. The image file's contents can be converted
 * to string form, can be rendered in greyscale, can be flipped vertically and horizontally,
 * can be brightened, can be filtered, and can undergo color transformations.
 */
public interface ImageFile {

  /**
   * Formats and returns the contents of this ImageFile.
   * Includes the file type, dimensions, maximum RGB value, and
   * the RGB values of each pixel.
   *
   * @return formatted contents of this ImageFile
   */
  String toString();

  /**
   * Greyscales this ImageFile.
   * The way
   *
   * @param mode the grey scale mode
   * @return a new ImageFile whose pixels have been greyscaled
   */
  ImageFile greyScale(String mode);

  /**
   * Flips this ImageFile horizontally.
   *
   * @return a new ImageFile that is the horizontally flipped version of this ImageFile
   */
  ImageFile horizontalFlip();

  /**
   * Flips this image vertically.
   *
   * @returna new ImageFile that is the vertically flipped version of this ImageFile
   */
  ImageFile verticalFlip();

  /**
   * Brightens or darkens this ImageFile.
   *
   * @param increment the number to increase or decrease the RGB value by
   * @return a new ImageFile that is brightened or darkened version of this ImageFile
   */
  ImageFile brighten(int increment);

  /**
   * Filters the ImageFile. Can blur or sharpen the image.
   *
   * @param mode the filter mode
   *             can be blur to blur the image
   *             can be sharpen to sharpen the image
   * @return a new ImageFile that is the filtered version of this ImageFile
   * @throws IllegalArgumentException if the mode is not a valid filter mode
   */
  ImageFile filter(String mode) throws IllegalArgumentException;

  /**
   * Performs a color transformation operation on the ImageFile. Can greyscale the image
   * or give the image a sepia tone.
   *
   * @param mode the color transformation mode
   *             can be greyscale to greyscale the image
   *             can be sepia to give the image a sepia tone
   * @return a new ImageFile that is the outcome of performing a color transformation operation
   *         on this ImageFile
   * @throws IllegalArgumentException if the mode is not a valid color transformation mode
   */
  ImageFile colorTransformation(String mode) throws IllegalArgumentException;

  /**
   * Retrieves a copy of the pixel at the given row and column.
   *
   * @param row the row of the pixel to be returned
   * @param col the column of the pixel to be returned
   * @return a copy of the pixel at the given row and column
   */
  Pixel getPixelAt(int row, int col);

  /**
   * Returns the width of the 2-D array of pixels.
   *
   * @return the width of the 2-D array of pixels
   */
  int getWidth();

  /**
   * Returns the height of the 2-D array of pixels.
   *
   * @return the height of the 2-D array of pixels
   */
  int getHeight();
}
