package controller;

import view.Histogram;
import view.ImageGUIView;

import java.awt.image.BufferedImage;

/**
 * Mock GUIView for testing purposes.
 */
public class MockGUIView1 implements ImageGUIView {

  private StringBuilder log;

  private StringBuilder log1;

  private StringBuilder log2;

  public MockGUIView1(StringBuilder log, StringBuilder log1, StringBuilder log2) {
    this.log = log;
    this.log1 = log1;
    this.log2 = log2;
  }

  @Override
  public void displayImage(BufferedImage bImage) {
    this.log.append(bImage.getRGB(0, 0) + "\n");
    this.log.append(bImage.getRGB(0, 1) + "\n");
    this.log.append(bImage.getRGB(0, 2) + "\n");
    this.log.append(bImage.getRGB(1, 0) + "\n");
    this.log.append(bImage.getRGB(1, 1) + "\n");
    this.log.append(bImage.getRGB(1, 2) + "\n");
    this.log.append(bImage.getRGB(2, 0) + "\n");
    this.log.append(bImage.getRGB(2, 1) + "\n");
    this.log.append(bImage.getRGB(2, 2) + "\n");
    this.log.append(bImage.getHeight() + ", " + bImage.getWidth());
  }

  @Override
  public void addFeatures(Features f) {
    this.log.append("SUCCESSFUL ADDITION OF FEATURES");
  }

  @Override
  public void displayHistogram(Histogram histo) {
    this.log1.append(histo.getPreferredSize().width + ", " + histo.getPreferredSize().height);
  }

  @Override
  public void renderErrorMessag(String msg) {
    this.log2.append(msg);
  }
}
