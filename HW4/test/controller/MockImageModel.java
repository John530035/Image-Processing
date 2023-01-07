package controller;

import model.ImageFile;
import model.ImageFileImpl;
import model.ImageModel;
import model.Pixel;

/**
 * Mock Model for testing.
 */
public class MockImageModel implements ImageModel {

  private final StringBuilder log;

  private ImageFile fakeImage;

  /**
   * Constructs instance of mock image object.
   *
   * @param log log to append to
   */
  public MockImageModel(StringBuilder log) {
    this.log = log;
    Pixel[][] pixels = new Pixel[1][1];
    pixels[0][0] = new Pixel(1, 1, 1, 1);
    this.fakeImage = new ImageFileImpl("P3", 1, 1, 1, pixels);
  }

  @Override
  public void load(String version, ImageFile image) {
    log.append(String.format("load %s\n", version));
  }

  @Override
  public ImageFile findFile(String version) throws IllegalArgumentException {
    log.append(String.format("find %s\n", version));
    return fakeImage;
  }

  @Override
  public ImageFile greyScale(String mode, String baseVersion) throws IllegalArgumentException {
    log.append(String.format("grey %s mode: %s\n", baseVersion, mode));
    return fakeImage;
  }

  @Override
  public ImageFile horizontalFlip(String baseVersion) throws IllegalArgumentException {
    log.append(String.format("horizontal-flip %s\n", baseVersion));
    return fakeImage;
  }

  @Override
  public ImageFile verticalFlip(String baseVersion) throws IllegalArgumentException {
    log.append(String.format("vertical-flip %s\n", baseVersion));
    return fakeImage;
  }

  @Override
  public ImageFile brighten(String baseVersion, int increment) throws IllegalArgumentException {
    log.append(String.format("brighten %s increment: %d\n", baseVersion, increment));
    return fakeImage;
  }

  @Override
  public ImageFile filter(String baseVersion, String mode) throws IllegalArgumentException {
    log.append(String.format("filter %s mode: %d\n", baseVersion, mode));
    return fakeImage;
  }

  @Override
  public ImageFile colorTransformation(String baseVersion, String mode)
      throws IllegalStateException {
    log.append(String.format("colorTransform %s mode: %d\n", baseVersion, mode));
    return fakeImage;
  }
}
