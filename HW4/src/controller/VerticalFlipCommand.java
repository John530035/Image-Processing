package controller;

import model.ImageFile;
import model.ImageModel;

/**
 * An implementation of the {@code Command} interface. This implementation flips an image
 * vertically and associates the new image with a new reference. This command requires the
 * image to be flipped and the new reference for the flipped image.
 */
public class VerticalFlipCommand implements Command {

  private final String baseVersion;

  private final String newVersion;

  /**
   * Constructs an instance of a VerticalFlipCommand.
   *
   * @param baseVersion the image to be flipped
   * @param newVersion  the new reference for the image
   * @throws IllegalArgumentException if any parameter is null
   */
  public VerticalFlipCommand(String baseVersion, String newVersion)
          throws IllegalArgumentException {
    if (baseVersion == null || newVersion == null) {
      throw new IllegalArgumentException("Values must not be null!");
    }
    this.baseVersion = baseVersion;
    this.newVersion = newVersion;
  }

  /**
   * Flips an image vertically. Loads the newly flipped image into the program with the
   * new reference.
   *
   * @param model the model housing program functionality
   * @throws IllegalArgumentException if the model is null
   *                                  if the model has no image with the given name
   */
  public void execute(ImageModel model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null!");
    }

    ImageFile flipped = model.verticalFlip(this.baseVersion);
    model.load(this.newVersion, flipped);
  }
}
