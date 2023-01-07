package controller;

import model.ImageFile;
import model.ImageModel;

/**
 * An implementation of the {@code Command} interface. This implementation flips the image
 * horizontally. This command requires the image to be flipped and the new reference for the
 * flipped image.
 */
public class HorizontalFlipCommand implements Command {

  private final String baseVersion;

  private final String newVersion;

  /**
   * Constructs an instance of a HorizontalFlipCommand.
   *
   * @param baseVersion the image to be flipped
   * @param newVersion  the new reference for the image
   * @throws IllegalArgumentException if any parameter is null
   */
  public HorizontalFlipCommand(String baseVersion, String newVersion)
          throws IllegalArgumentException {
    if (baseVersion == null || newVersion == null) {
      throw new IllegalArgumentException("Values must not be null!");
    }
    this.baseVersion = baseVersion;
    this.newVersion = newVersion;
  }

  /**
   * Flips an image horizontally, loads the newly flipped image into the program, and associates
   * it with the given reference.
   *
   * @param model the model housing program functionality
   * @throws IllegalArgumentException if the model is null
   *                                  if the model does not have an image with the given name
   */
  @Override
  public void execute(ImageModel model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null!");
    }
    ImageFile image = model.horizontalFlip(this.baseVersion);
    model.load(newVersion, image);
  }
}
