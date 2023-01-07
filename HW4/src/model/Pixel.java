package model;

/**
 * Represents a single pixel. This class contains all of the information for a pixel that is
 * necessary for operating on the pixel. The class contains the rgb values for the three channels
 * of the pixel as well as the maximum value for the rgb channels.
 */
public final class Pixel {

  private int redValue;

  private int greenValue;

  private int blueValue;

  private int maxValue;

  /**
   * Constructs a Pixel object.
   *
   * @param redValue the value of the red channel of the pixel
   * @param greenValue the value of the green channel of the pixel
   * @param blueValue the value of the blue channel of the pixel
   * @param maxValue the maximum value of each channel of the pixel
   * @throws IllegalArgumentException if the provided RGB values or maximum value are
   *                                  negative or if the RGB values exceed the
   */
  public Pixel(int redValue, int greenValue, int blueValue, int maxValue)
          throws IllegalArgumentException {

    if (maxValue < 0) {
      throw new IllegalArgumentException("RGB value or max value is negative");
    }

    this.maxValue = maxValue;
    this.redValue = Math.min(Math.max(redValue, 0), maxValue);
    this.greenValue = Math.min(Math.max(greenValue, 0), maxValue);
    this.blueValue = Math.min(Math.max(blueValue, 0), maxValue);
  }

  /**
   * Returns an array of this Pixel's RGB values.
   *
   * @return an array of this Pixel's RGB values
   */
  public int[] getRGB() {
    int[] rgb = new int[3];
    rgb[0] = redValue;
    rgb[1] = greenValue;
    rgb[2] = blueValue;

    return rgb;
  }

  /**
   * Greyscales this Pixel with the given mode.
   *
   * @param mode the type of grey scale
   * @return a new Pixel whose RGB values have been grey scaled
   * @throws IllegalArgumentException if the given mode is invalid
   */
  public Pixel greyScale(String mode) throws IllegalArgumentException {
    switch (mode.toLowerCase()) {
      case "red":
        return new Pixel(redValue, redValue, redValue, maxValue);
      case "blue":
        return new Pixel(blueValue, blueValue, blueValue, maxValue);
      case "green":
        return new Pixel(greenValue, greenValue, greenValue, maxValue);
      case "value":
        int max = Math.max(Math.max(redValue, greenValue), blueValue);
        return new Pixel(max, max, max, maxValue);
      case "intensity":
        int average = (redValue + greenValue + blueValue) / 3;
        return new Pixel(average, average, average, maxValue);
      case "luma":
        int value = (int) (redValue * 0.2126 + greenValue * 0.7152 + blueValue * 0.0722);
        return new Pixel(value, value, value, maxValue);
      default:
        throw new IllegalArgumentException("Invalid grey scale mode");
    }
  }

  /**
   * Brightens this Pixel by the given increment.
   *
   * @param increment the increment by which to brighten or darken this Pixel
   * @return a new Pixel that is brighter or darker than this Pixel
   */
  public Pixel brighten(int increment) {
    int red = redValue + increment;
    int green = greenValue + increment;
    int blue = blueValue + increment;

    return new Pixel(Math.max(Math.min(red, maxValue), 0),
        Math.max(Math.min(green, maxValue), 0),
        Math.max(Math.min(blue, maxValue), 0), maxValue);
  }
}

