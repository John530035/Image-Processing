package model;

/**
 * An implementation of the {@code ImageFile} interface. This implementation is used to represent
 * a .ppm file and contains information necessary for operating on a .ppm file, such as the
 * token, the dimensions, the maximum RGB value, and the 2-D array of pixels that make the image.
 */
public final class ImageFileImpl extends AbstractImageFile {

  private final String token;

  private final int height;

  private final int width;

  private final int maxVal;

  /**
   * Constructs an ImageFile object.
   *
   * @param token  the image file type
   * @param height the height of the image
   * @param width  the width of the image
   * @param maxVal the maximum RGB value
   * @param image  the Pixels of this ImageFile in an array
   * @throws IllegalArgumentException if the token is not supported if the height is negative if the
   *                                  widht is negative if the maxVal is negative if the image is
   *                                  null
   */
  public ImageFileImpl(String token, int height, int width, int maxVal, Pixel[][] image)
      throws IllegalArgumentException {
    super(image);
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("This program only processes .ppm files");
    }
    if (height < 0) {
      throw new IllegalArgumentException("Height of image must not be negative");
    }
    if (width < 0) {
      throw new IllegalArgumentException("Width of image must not be negative");
    }
    if (maxVal < 0) {
      throw new IllegalArgumentException("Maximum RGB value must not be negative");
    }
    this.token = token;
    this.height = height;
    this.width = width;
    this.maxVal = maxVal;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder(token + "\n")
        .append(width + " " + height + "\n")
        .append(maxVal + "\n");
    for (int r = 0; r < height; r++) {
      for (int c = 0; c < width; c++) {
        Pixel p = image[r][c];
        int[] rgb = p.getRGB();
        builder.append(rgb[0] + "\n")
            .append(rgb[1] + "\n")
            .append(rgb[2] + "\n");
      }
    }

    return builder.toString();
  }

  @Override
  protected int maxValue() {
    return this.maxVal;
  }

  @Override
  protected ImageFile factory(Pixel[][] pixels) {
    return new ImageFileImpl(token, height, width, maxVal, pixels);
  }
}

