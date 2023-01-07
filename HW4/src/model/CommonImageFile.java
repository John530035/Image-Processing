package model;

/**
 * An implementation of the {@code ImageFile} interface. This implementation represents the common
 * image file types (.png, .jpg, and .bmp) and contains the information necessary for operating on
 * these images, such as the 2-D array of pixels that make the image.
 */
public final class CommonImageFile extends AbstractImageFile {

  /**
   * Constructs an instance of an {@code CommonImageFile} object.
   *
   * @param image the 2-D array of pixels that make the image
   * @throws IllegalArgumentException if the parameter is null
   */
  public CommonImageFile(Pixel[][] image) throws IllegalArgumentException {
    super(image);
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    for (int r = 0; r < image.length; r++) {
      for (int c = 0; c < image[0].length; c++) {
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
    return 255;
  }

  @Override
  protected ImageFile factory(Pixel[][] pixels) {
    return new CommonImageFile(pixels);
  }
}
