package controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;

import model.ImageFileImpl;
import model.ImageModel;
import model.CommonImageFile;
import model.Pixel;

/**
 * An implementation of the {@code Command} interface. This implementation loads an image into the
 * program and associates it with a reference. This command requires the file path
 * of the image and the reference to be assigned to the image at the file path.
 */
public class LoadCommand implements Command {

  private final String filePath;

  private final String version;

  private final List<String> validExtensions = Arrays.asList(new String[]{".ppm"});

  /**
   * Constructs an instance of a LoadCommand.
   *
   * @param filePath the path of the file to be loaded
   * @param version  the reference of the image
   * @throws IllegalArgumentException if any parameter is null
   */
  public LoadCommand(String filePath, String version) throws IllegalArgumentException {
    if (filePath == null || version == null) {
      throw new IllegalArgumentException("Values must not be null!");
    }

    this.filePath = filePath;
    this.version = version;
  }

  private boolean validateExtension(String filePath) {
    for (String ext : this.validExtensions) {
      if (filePath.endsWith(ext)) {
        return true;
      }
    }

    return false;
  }

  /**
   * Loads an image into the program and associates the image with the given reference.
   *
   * @param model the model housing program functionality
   * @throws IllegalArgumentException if the model is null
   *                                  if the file type is not supported by this program
   *                                  if the file path is invalid
   */
  public void execute(ImageModel model) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null!\n");
    }

    String[] fileSplit = filePath.split("\\.");
    if (fileSplit.length != 2) {
      throw new IllegalArgumentException("Invalid file path!\n");
    }
    String fileType = fileSplit[1];
    switch (fileType.toLowerCase()) {
      case "jpg":
      case "png":
      case "bmp":
        Pixel[][] img = loadCommon();
        model.load(version, new CommonImageFile(img));
        break;
      case "ppm":
        loadPPM(model);
        break;
      default:
        throw new IllegalArgumentException("NOT A FILETYPE!\n");
    }
  }

  //retrieves the contents of .png, .jpg, and .bmp file types
  private Pixel[][] loadCommon() {
    BufferedImage bImage;


    try {
      bImage = ImageIO.read(new File(filePath));
      int width = bImage.getWidth();
      int height = bImage.getHeight();
      Pixel[][] image = new Pixel[height][width];
      for (int r = 0; r < height; r++) {
        for (int c = 0; c < width; c++) {
          int rgb = bImage.getRGB(c, r);
          Color color = new Color(rgb);
          int red = color.getRed();
          int green = color.getGreen();
          int blue = color.getBlue();
          Pixel p = new Pixel(red, green, blue, 255);
          image[r][c] = p;
        }
      }

      return image;

    } catch (IOException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  //retrieves the contents of a ppm file and loads it into the model
  private void loadPPM(ImageModel model) throws IllegalArgumentException {
    try {
      Scanner sc = new Scanner(new FileInputStream(this.filePath));
      if (!validateExtension(this.filePath)) {
        throw new IllegalArgumentException("Invalid file type!\n");
      }

      String token = sc.next();
      String s = sc.next();
      int width;
      int height;

      if (s.equals("#")) {
        sc.nextLine();
        width = sc.nextInt();
        height = sc.nextInt();
      } else {
        width = Integer.parseInt(s);
        height = sc.nextInt();
      }


      Pixel[][] image = new Pixel[height][width];
      int maxValue = sc.nextInt();
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          int r = sc.nextInt();
          int g = sc.nextInt();
          int b = sc.nextInt();
          image[i][j] = new Pixel(r, g, b, maxValue);
        }
      }

      model.load(version, new ImageFileImpl(token, height, width, maxValue, image));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Invalid file path!\n");
    }
  }

}
