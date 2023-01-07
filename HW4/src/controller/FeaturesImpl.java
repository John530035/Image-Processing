package controller;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import model.ImageFile;
import model.ImageModel;
import model.Pixel;
import view.Histogram;
import view.ImageGUIView;

/**
 * An implementation of the {@code Features} interface. Fully implements all functionality.
 * Facilitates communication between the model and the view.
 */
public class FeaturesImpl implements Features {

  private ImageModel model;

  private ImageGUIView view;

  private String currentVersion;

  /**
   * Constructs an instance of a {@code FeaturesImpl} object. Takes in a model and a view.
   *
   * @param model the model housing program functionality
   * @param view the graphical view displayed to the user
   */
  public FeaturesImpl(ImageModel model, ImageGUIView view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void load(String filePath) {
    this.currentVersion = "current image";
    Command loadCommand = new LoadCommand(filePath, this.currentVersion);
    try {
      loadCommand.execute(this.model);
      ImageFile initialImage = this.model.findFile(this.currentVersion);
      BufferedImage bImage = this.makeBufferedImage(initialImage);
      this.view.displayImage(bImage);
      Map<Integer, Integer>[] componentValues = this.generateComponentValues(initialImage);
      this.view.displayHistogram(new Histogram(componentValues));
    } catch (IllegalArgumentException e) {
      this.view.renderErrorMessag(e.getMessage());
    }
  }

  //returns whether an image is loaded into the program
  private boolean isLoaded() {
    if (currentVersion == null) {
      view.renderErrorMessag("No image loaded!");
      return false;
    }

    return true;
  }

  @Override
  public void brighten(String increment) {
    if (isLoaded()) {
      try {
        int inc = Integer.parseInt(increment);
        ImageFile brightImage = this.model.brighten(this.currentVersion, inc);
        this.model.load(this.currentVersion, brightImage);
        BufferedImage bImage = this.makeBufferedImage(brightImage);
        this.view.displayImage(bImage);
        Map<Integer, Integer>[] componentValues = this.generateComponentValues(brightImage);
        this.view.displayHistogram(new Histogram(componentValues));
      } catch (NumberFormatException e) {
        view.renderErrorMessag("No number entered!");
      }
    }
  }

  @Override
  public void verticalFlip() {
    if (isLoaded()) {
      ImageFile verticalFlipImage = this.model.verticalFlip(this.currentVersion);
      this.model.load(this.currentVersion, verticalFlipImage);
      BufferedImage bImage = this.makeBufferedImage(verticalFlipImage);
      this.view.displayImage(bImage);
      Map<Integer, Integer>[] componentValues = this.generateComponentValues(verticalFlipImage);
      this.view.displayHistogram(new Histogram(componentValues));
    }
  }

  @Override
  public void horizontalFlip() {
    if (isLoaded()) {
      ImageFile horizontalFlipImage = this.model.horizontalFlip(this.currentVersion);
      this.model.load(this.currentVersion, horizontalFlipImage);
      BufferedImage bImage = this.makeBufferedImage(horizontalFlipImage);
      this.view.displayImage(bImage);
      Map<Integer, Integer>[] componentValues = this.generateComponentValues(horizontalFlipImage);
      this.view.displayHistogram(new Histogram(componentValues));
    }
  }

  @Override
  public void blur() {
    if (isLoaded()) {
      ImageFile blurImage = this.model.filter(this.currentVersion, "blur");
      this.model.load(this.currentVersion, blurImage);
      BufferedImage bImage = this.makeBufferedImage(blurImage);
      this.view.displayImage(bImage);
      Map<Integer, Integer>[] componentValues = this.generateComponentValues(blurImage);
      this.view.displayHistogram(new Histogram(componentValues));
    }

  }

  @Override
  public void sharpen() {
    if (isLoaded()) {
      ImageFile sharpenImage = this.model.filter(this.currentVersion, "sharpen");
      this.model.load(this.currentVersion, sharpenImage);
      BufferedImage bImage = this.makeBufferedImage(sharpenImage);
      this.view.displayImage(bImage);
      Map<Integer, Integer>[] componentValues = this.generateComponentValues(sharpenImage);
      this.view.displayHistogram(new Histogram(componentValues));
    }
  }

  @Override
  public void sepia() {
    if (isLoaded()) {
      ImageFile sepiaImage = this.model.colorTransformation(this.currentVersion, "sepia");
      this.model.load(this.currentVersion, sepiaImage);
      BufferedImage bImage = this.makeBufferedImage(sepiaImage);
      this.view.displayImage(bImage);
      Map<Integer, Integer>[] componentValues = this.generateComponentValues(sepiaImage);
      this.view.displayHistogram(new Histogram(componentValues));
    }
  }

  @Override
  public void greyscale() {
    if (isLoaded()) {
      ImageFile greyscaleImage = this.model.colorTransformation(this.currentVersion, "greyscale");
      this.model.load(this.currentVersion, greyscaleImage);
      BufferedImage bImage = this.makeBufferedImage(greyscaleImage);
      this.view.displayImage(bImage);
      Map<Integer, Integer>[] componentValues = this.generateComponentValues(greyscaleImage);
      this.view.displayHistogram(new Histogram(componentValues));
    }
  }

  @Override
  public void componentVisualization(String mode) {
    if (isLoaded()) {
      try {
        ImageFile componentVisualizationiImage = this.model.greyScale(mode, this.currentVersion);
        this.model.load(this.currentVersion, componentVisualizationiImage);
        BufferedImage bImage = this.makeBufferedImage(componentVisualizationiImage);
        this.view.displayImage(bImage);
        Map<Integer, Integer>[] componentValues =
                this.generateComponentValues(componentVisualizationiImage);
        this.view.displayHistogram(new Histogram(componentValues));
      } catch (IllegalArgumentException e) {
        view.renderErrorMessag(e.getMessage());
      }
    }

  }

  @Override
  public void save(String filePath) {
    if (isLoaded()) {
      Command saveCommand = new SaveCommand(filePath, this.currentVersion);

      try {
        saveCommand.execute(this.model);
      } catch (IllegalArgumentException e) {
        view.renderErrorMessag(e.getMessage());
      }
    }
  }

  //convrts an ImageFile into a buffered image to display
  private BufferedImage makeBufferedImage(ImageFile image) {
    BufferedImage outputImage;

    int width = image.getWidth();
    int height = image.getHeight();

    outputImage = new BufferedImage(width, height,
        BufferedImage.TYPE_INT_RGB);

    for (int r = 0; r < height; r++) {
      for (int c = 0; c < width; c++) {
        Pixel p = image.getPixelAt(r, c);
        int red = p.getRGB()[0];
        int green = p.getRGB()[1];
        int blue = p.getRGB()[2];
        int rgb = new Color(red, green, blue).getRGB();
        outputImage.setRGB(c, r, rgb);
      }
    }
    return outputImage;
  }

  //generates the histogram values
  private Map<Integer, Integer>[] generateComponentValues(ImageFile imageFile) {
    Map<Integer, Integer> redMap = new HashMap<Integer, Integer>();
    Map<Integer, Integer> greenMap = new HashMap<Integer, Integer>();
    Map<Integer, Integer> blueMap = new HashMap<Integer, Integer>();
    Map<Integer, Integer> intensityMap = new HashMap<Integer, Integer>();

    for (int i = 0; i < imageFile.getHeight(); i++) {
      for (int j = 0; j < imageFile.getWidth(); j++) {
        Pixel p = imageFile.getPixelAt(i, j);
        int red = p.getRGB()[0];
        int green = p.getRGB()[1];
        int blue = p.getRGB()[2];
        int intensity = red + green + blue / 3;
        redMap.put(red, redMap.getOrDefault(red, 0) + 1);
        greenMap.put(green, greenMap.getOrDefault(green, 0) + 1);
        blueMap.put(blue, blueMap.getOrDefault(blue, 0) + 1);
        intensityMap.put(intensity, intensityMap.getOrDefault(intensity, 0) + 1);
      }
    }

    Map<Integer, Integer> normalRed = this.normalizeData(redMap);
    Map<Integer, Integer> normalGreen = this.normalizeData(greenMap);
    Map<Integer, Integer> normalBlue = this.normalizeData(blueMap);
    Map<Integer, Integer> normalIntensity = this.normalizeData(intensityMap);

    return new Map[]{normalRed, normalGreen, normalBlue, normalIntensity};
  }

  //normalizes the histogram values
  private Map<Integer, Integer> normalizeData(Map<Integer, Integer> data) {
    int max = Collections.max(data.values());
    int min = Collections.min(data.values());

    for (Map.Entry<Integer, Integer> entry : data.entrySet()) {
      double scaled = ((double) (entry.getValue() - min) / (double) (max - min));
      int normalized = (int) (scaled * 100);
      data.put(entry.getKey(), normalized);
    }
    return data;
  }
}
