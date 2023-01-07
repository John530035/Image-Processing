package model;

import java.util.HashMap;
import java.util.Map;

/**
 * An implementation of the {@code ImageModel} interface. This implementation handles all of the
 * functionality of the image processing program, including loading an image to the program,
 * processing the image, and saving the image. The images are stored in this class and are
 * associated with a particular reference.
 */
public class ImageModelImpl implements ImageModel {

  private final Map<String, ImageFile> versions;

  /**
   * Constructs an instance of an ImageModelImpl.
   */
  public ImageModelImpl() {
    this.versions = new HashMap<>();
  }

  @Override
  public void load(String version, ImageFile image) {
    if (this.versions.containsKey(version)) {
      this.versions.replace(version, image);
    } else {
      this.versions.put(version, image);
    }
  }

  @Override
  public ImageFile findFile(String version) throws IllegalArgumentException {
    if (!this.versions.containsKey(version)) {
      throw new IllegalArgumentException("Version not found!\n");
    }

    return this.versions.get(version);
  }

  @Override
  public ImageFile greyScale(String mode, String baseVersion) throws IllegalArgumentException {
    return this.findFile(baseVersion).greyScale(mode);
  }

  @Override
  public ImageFile horizontalFlip(String baseVersion) throws IllegalArgumentException {
    return this.findFile(baseVersion).horizontalFlip();
  }

  @Override
  public ImageFile verticalFlip(String baseVersion) throws IllegalArgumentException {
    return this.findFile(baseVersion).verticalFlip();
  }

  @Override
  public ImageFile brighten(String baseVersion, int increment) throws IllegalArgumentException {
    return this.findFile(baseVersion).brighten(increment);
  }

  @Override
  public ImageFile filter(String baseVersion, String mode) throws IllegalArgumentException {
    return this.findFile(baseVersion).filter(mode);
  }

  @Override
  public ImageFile colorTransformation(String baseVersion, String mode)
          throws IllegalArgumentException {
    return this.findFile(baseVersion).colorTransformation(mode);
  }
}
