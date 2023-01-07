package controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.ImageFile;
import model.ImageFileImpl;
import model.ImageModel;
import model.Pixel;

/**
 * An implementation of the {@code Command} interface. This implementation saves an image to a
 * file path. This command requires the image to be saved and the file path to save the image to.
 */
public class SaveCommand implements Command {

  private final String version;
  private final String fileLocation;

  /**
   * Constructs an instance of a SaveCommand.
   *
   * @param fileLocation the location to save the file
   * @param version      the image to save
   * @throws IllegalArgumentException if any parameter is null
   */
  public SaveCommand(String fileLocation, String version) throws IllegalArgumentException {
    if (version == null || fileLocation == null) {
      throw new IllegalArgumentException("Values must not be null!\n");
    }
    this.version = version;
    this.fileLocation = fileLocation;
  }

  /**
   * Retrieves the contents of the image to be saved and writes the image to the specified
   * file path.
   *
   * @param model the model housing program functionality
   */
  public void execute(ImageModel model) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null!\n");
    }

    BufferedImage outputImage;

    ImageFile image = model.findFile(this.version);

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

    try {
      String[] fileSplit = fileLocation.split("\\.");

      if (fileSplit.length != 2) {
        throw new IllegalArgumentException("Invalid file path!\n");
      }

      String fileType = fileSplit[1];

      File myObj = new File(fileLocation);
      switch (fileType.toLowerCase()) {
        case "jpg":
          myObj.createNewFile();
          ImageIO.write(outputImage, "jpg", myObj);
          break;
        case "png":
          myObj.createNewFile();
          ImageIO.write(outputImage, "png", myObj);
          break;
        case "bmp":
          myObj.createNewFile();
          ImageIO.write(outputImage, "bmp", myObj);
          break;
        case "ppm":
          Pixel[][] img = new Pixel[height][width];
          for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
              img[r][c] = image.getPixelAt(r, c);
            }
          }

          ImageFile imageF = new ImageFileImpl("P3", height, width, 255, img);
          savePPM(model, imageF);
          break;
        default:
          throw new IllegalArgumentException("NOT A FILETYPE!\n");
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("Can't create file at location!\n");
    }
  }

  //retrieves the contents of the .ppm file and saves it to the specified file path
  private void savePPM(ImageModel model, ImageFile image) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null!\n");
    }

    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }

    String fileInfo = image.toString();

    try {
      File myObj = new File(fileLocation);
      myObj.createNewFile();
    } catch (IOException e) {
      throw new IllegalArgumentException("Can't create file at location!\n");
    }
    try (FileWriter writer = new FileWriter(fileLocation)) {
      writer.append(fileInfo);
      writer.close();
    } catch (IOException e) {
      throw new IllegalArgumentException("Can't write to file!\n");
    }
  }
}
