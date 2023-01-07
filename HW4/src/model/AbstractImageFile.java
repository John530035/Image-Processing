package model;

//abstract class housing shared functionality between the different implementations
//of the ImageFile interface
abstract class AbstractImageFile implements ImageFile {

  protected final Pixel[][] image;

  protected AbstractImageFile(Pixel[][] image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Pixel array is null");
    }
    this.image = image;
  }

  @Override
  public abstract String toString();

  @Override
  public ImageFile greyScale(String mode) {
    Pixel[][] gray = new Pixel[getHeight()][getWidth()];

    for (int r = 0; r < getHeight(); r++) {
      for (int c = 0; c < getWidth(); c++) {
        gray[r][c] = this.image[r][c].greyScale(mode);
      }
    }

    return factory(gray);
  }

  @Override
  public ImageFile horizontalFlip() {
    Pixel[][] flipped = new Pixel[getHeight()][getWidth()];

    for (int r = 0; r < getHeight(); r++) {
      for (int c = 0; c < getWidth(); c++) {
        flipped[r][c] = image[r][(getWidth() - 1) - c];
      }
    }

    return factory(flipped);
  }

  @Override
  public ImageFile verticalFlip() {
    Pixel[][] flipped = new Pixel[getHeight()][getWidth()];
    for (int r = getHeight() - 1; r >= 0; r--) {
      for (int c = 0; c < getWidth(); c++) {
        flipped[r][c] = this.image[(getHeight() - 1) - r][c];
      }
    }

    return factory(flipped);
  }

  @Override
  public ImageFile brighten(int increment) {
    Pixel[][] brightened = new Pixel[getHeight()][getWidth()];

    for (int r = 0; r < getHeight(); r++) {
      for (int c = 0; c < getWidth(); c++) {
        brightened[r][c] = this.image[r][c].brighten(increment);
      }
    }

    return factory(brightened);
  }

  @Override
  public ImageFile filter(String mode) throws IllegalArgumentException {
    double[][] filterMatrix = this.filterMatrix(mode);

    int height = this.getHeight();
    int width = this.getWidth();

    int maxOffset = filterMatrix.length / 2;

    Pixel[][] filtered = new Pixel[height][width];

    for (int r = 0; r < height; r++) {
      for (int c = 0; c < width; c++) {
        double[] rgb = new double[3];

        for (int channel = 0; channel < 3; channel++) {
          double totalGridValue = 0;

          for (int i = -maxOffset; i < maxOffset + 1; i++) {
            for (int j = -maxOffset; j < maxOffset + 1; j++) {
              if (r + i < 0 || r + i >= height) {
                continue;
              }

              if (j + c < 0 || j + c >= width) {
                continue;
              }

              Pixel p = this.image[r + i][c + j];
              double value = p.getRGB()[channel] * filterMatrix[i + maxOffset][j + maxOffset];
              totalGridValue += value;

            }

          }

          rgb[channel] = totalGridValue;
        }

        filtered[r][c] = new Pixel((int) Math.floor(rgb[0]),
                (int) Math.floor(rgb[1]),
                (int) Math.floor(rgb[2]), 255);
      }
    }

    return factory(filtered);
  }

  //generates the correct filter matrix based on the mode being performed
  private double[][] filterMatrix(String mode) throws IllegalArgumentException {
    double[][] filterMatrix;
    switch (mode.toLowerCase()) {
      case "blur":
        filterMatrix = new double[3][3];
        filterMatrix[0] = new double[]{0.0625, 0.125, 0.0625};
        filterMatrix[1] = new double[]{0.125, 0.25, 0.125};
        filterMatrix[2] = new double[]{0.0625, 0.125, 0.0625};
        return filterMatrix;
      case "sharpen":
        filterMatrix = new double[5][5];
        filterMatrix[0] = new double[]{-0.125, -0.125, -0.125, -0.125, -0.125};
        filterMatrix[1] = new double[]{-0.125, 0.25, 0.25, 0.25, -0.125};
        filterMatrix[2] = new double[]{-0.125, 0.25, 1, 0.25, -0.125};
        filterMatrix[3] = new double[]{-0.125, 0.25, 0.25, 0.25, -0.125};
        filterMatrix[4] = new double[]{-0.125, -0.125, -0.125, -0.125, -0.125};
        return filterMatrix;
      default:
        throw new IllegalArgumentException("Invalid filter mode!\n");
    }
  }

  @Override
  public ImageFile colorTransformation(String mode) throws IllegalArgumentException {
    double[][] colorTransformationMatrix = this.colorTransformationMatrix(mode);

    int height = this.getHeight();
    int width = this.getWidth();

    Pixel[][] transformed = new Pixel[height][width];

    for (int r = 0; r < height; r++) {
      for (int c = 0; c < width; c++) {
        int[] rgb = this.image[r][c].getRGB();
        double redValue = 0;
        double greenValue = 0;
        double blueValue = 0;

        for (int i = 0; i < 3; i++) {
          for (int j = 0; j < 3; j++) {
            if (i == 0) {
              redValue += colorTransformationMatrix[i][j] * rgb[j];
            }

            if (i == 1) {
              greenValue += colorTransformationMatrix[i][j] * rgb[j];
            }

            if (i == 2) {
              blueValue += colorTransformationMatrix[i][j] * rgb[j];
            }

          }
        }

        transformed[r][c] = new Pixel((int) Math.floor(redValue),
                (int) Math.floor(greenValue),
                (int) Math.floor(blueValue), 255);
      }
    }

    return factory(transformed);
  }

  //generates the correct color transformation matrix depending on the mode being performed
  private double[][] colorTransformationMatrix(String mode) throws IllegalArgumentException {
    double[][] colorTransformationMatrix = new double[3][3];
    switch (mode.toLowerCase()) {
      case "greyscale":
        colorTransformationMatrix[0] = new double[]{0.2126, 0.7152, 0.0722};
        colorTransformationMatrix[1] = new double[]{0.2126, 0.7152, 0.0722};
        colorTransformationMatrix[2] = new double[]{0.2126, 0.7152, 0.0722};
        break;
      case "sepia":
        colorTransformationMatrix[0] = new double[]{0.393, 0.769, 0.189};
        colorTransformationMatrix[1] = new double[]{0.349, 0.686, 0.168};
        colorTransformationMatrix[2] = new double[]{0.272, 0.534, 0.131};
        break;
      default:
        throw new IllegalArgumentException("Invalid color transformation mode\n");
    }
    return colorTransformationMatrix;
  }

  @Override
  public Pixel getPixelAt(int row, int col) {
    int[] rgb = this.image[row][col].getRGB();
    Pixel copy = new Pixel(rgb[0], rgb[1], rgb[2], this.maxValue());
    return this.image[row][col];
  }

  //abstract method used to return the correct max value for the file type
  protected abstract int maxValue();

  @Override
  public int getWidth() {
    return image[0].length;
  }

  @Override
  public int getHeight() {
    return image.length;
  }

  //factory method to return the correct ImageFile
  protected abstract ImageFile factory(Pixel[][] pixels);
}
