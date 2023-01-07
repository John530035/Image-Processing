package view;

import java.awt.image.BufferedImage;

import controller.Features;

/**
 * Represents a graphical user interface that the user can interact with.
 * All program functionality is streamlined to improve usability.
 */
public interface ImageGUIView {

  /**
   * Displays the image for the user to view.
   *
   * @param bImage the image to dispay
   */
  void displayImage(BufferedImage bImage);

  /**
   * Adds features defined in the {@code Features} interface as callbacks for buttons
   * on the GUI. Establishes communication between the view and controller.
   * @param f the features implementation
   */
  void addFeatures(Features f);

  /**
   * Displays the histogram for the user to view.
   *
   * @param histo the histogram to be displayed
   */
  void displayHistogram(Histogram histo);

  /**
   * Renders error messages to the user in a readable fashion.
   *
   * @param msg the error message to be displayed
   */
  void renderErrorMessag(String msg);
}
