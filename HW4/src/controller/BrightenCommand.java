package controller;

import model.ImageFile;
import model.ImageModel;

/**
 * An implementation of {@code Command} interface. This command brightens or darkens
 * an image by a given increment and then associates the new image with a new reference.
 * This command requires the increment, the image to be brightened, and the new reference.
 */
public class BrightenCommand implements Command {

  private final int increment;

  private final String baseVersion;

  private final String newVersion;

  /**
   * Constructs an instance of a BrightenCommand.
   *
   * @param increment   the value by which to brighten or darken the image
   * @param baseVersion the image to brighten/darken
   * @param newVersion  the new reference for the image
   * @throws IllegalArgumentException if any parameters are null
   */
  public BrightenCommand(int increment, String baseVersion, String newVersion)
          throws IllegalArgumentException {
    if (baseVersion == null || newVersion == null) {
      throw new IllegalArgumentException("Values must not be null!");
    }
    this.increment = increment;
    this.baseVersion = baseVersion;
    this.newVersion = newVersion;
  }

  /**
   * Brightens or darkens the image, loads the new image into the program, and associates it
   * with the new reference.
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
    ImageFile image = model.brighten(this.baseVersion, this.increment);
    model.load(this.newVersion, image);
  }
}
