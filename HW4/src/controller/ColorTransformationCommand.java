package controller;

import model.ImageFile;
import model.ImageModel;

/**
 * An implementation of the {@code Command} interface. This implementation performs a color
 * transformation operation on an image. This implementation requires the image to be operated on,
 * the new reference for the processed image, and the color transformation mode. This command
 * supports greyscaling an image and giving an image a sepia tone.
 */
public class ColorTransformationCommand implements Command {

  private final String baseVersion;

  private final String newVersion;

  private final String mode;

  /**
   * Constructs an instance of a {@code ColorTransformationCommand} object.
   *
   * @param baseVersion the image to be processed
   * @param newVersion  the new reference for the image
   * @param mode        the color transformation mode
   *                    can be greyscale to greyscale the image
   *                    can be sepia to give the image a sepia tone
   * @throws IllegalArgumentException if the parameters are null
   */
  public ColorTransformationCommand(String baseVersion, String newVersion, String mode)
          throws IllegalArgumentException {
    if (baseVersion == null || newVersion == null || mode == null) {
      throw new IllegalArgumentException("Values must not be null!");
    }
    this.baseVersion = baseVersion;
    this.newVersion = newVersion;
    this.mode = mode;
  }

  /**
   * Performs the color transformation operation, loads the new image into the model, and associates
   * the new image with the new reference. This method cna greyscale an image or give it a sepia
   * tone.
   *
   * @param model the model housing program functionality
   * @throws IllegalArgumentException if the model is null
   */
  @Override
  public void execute(ImageModel model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null!\n");
    }
    ImageFile image = model.colorTransformation(this.baseVersion, this.mode);
    model.load(this.newVersion, image);
  }
}
