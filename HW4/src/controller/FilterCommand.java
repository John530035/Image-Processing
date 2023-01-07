package controller;

import model.ImageFile;
import model.ImageModel;

/**
 * An implementation of the {@code Command} interface. This implementation filters an image. This
 * command requires the image to be filtered, the new reference for the filtered image, and the
 * mode of filtering. This command supports blurring and image and sharpening an image.
 */
public class FilterCommand implements Command {

  private final String baseVersion;

  private final String newVersion;

  private final String mode;

  /**
   * Constructs an instance of a {@code FilterCommand} object.
   *
   * @param baseVersion the image to be filtered
   * @param newVersion  the new reference for the filtered image
   * @param mode        the mode of filtering
   *                    can be blur to blur the image
   *                    can be sharpen to sharpen the image
   * @throws IllegalArgumentException if any parameter is null
   */
  public FilterCommand(String baseVersion, String newVersion, String mode)
          throws IllegalArgumentException {
    if (baseVersion == null || newVersion == null || mode == null) {
      throw new IllegalArgumentException("Values must not be null!");
    }
    this.baseVersion = baseVersion;
    this.newVersion = newVersion;
    this.mode = mode;
  }

  /**
   * Filters the image, loads the new image into the program, and associates it with the new
   * reference. This method can blur or sharpen an image.
   *
   * @param model the model housing program functionality
   * @throws IllegalArgumentException if the model is null
   */
  @Override
  public void execute(ImageModel model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null!\n");
    }
    ImageFile image = model.filter(this.baseVersion, this.mode);
    model.load(this.newVersion, image);
  }
}
