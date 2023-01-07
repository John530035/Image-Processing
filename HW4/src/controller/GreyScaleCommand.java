package controller;

import model.ImageFile;
import model.ImageModel;

/**
 * An implementation of the {@code Command} interface. This implementation greyscales the image.
 * This command requires the mode of greyscaling, the image to be operated on, and the new reference
 * for the processed image. This command supports visualizing the red, blue, green, luma,
 * value, intensity component.
 */
public class GreyScaleCommand implements Command {

  private final String mode;

  private final String baseVersion;

  private final String newVersion;

  /**
   * Constructs an instance of a GreyscaleCommand.
   *
   * @param mode        the mode of grey scale
   *                    can be red-component to visualize the red-component
   *                    can be green-component to visualize the green-component
   *                    can be blue-component to visualize the blue-component
   *                    can be luma-component to visualize the luma-component
   *                    can be value-component to visualize the value-component
   *                    can be intensity-component to visualize the intensity-component
   * @param baseVersion the image to be edited
   * @param newVersion  the new reference for the image
   * @throws IllegalArgumentException if any parameter is null
   */
  GreyScaleCommand(String mode, String baseVersion, String newVersion)
          throws IllegalArgumentException {
    if (mode == null || baseVersion == null || newVersion == null) {
      throw new IllegalArgumentException("Values must not be null!");
    }
    this.mode = mode;
    this.baseVersion = baseVersion;
    this.newVersion = newVersion;
  }

  /**
   * Grey scales the image, loads the new image into the program, and associates it with the given
   * reference.
   *
   * @param model the model housing program functionality
   * @throws IllegalArgumentException if the model is null if the model has no image with the given
   *                                  name
   */
  @Override
  public void execute(ImageModel model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null!");
    }
    ImageFile image = model.greyScale(this.mode, this.baseVersion);
    model.load(this.newVersion, image);
  }
}
