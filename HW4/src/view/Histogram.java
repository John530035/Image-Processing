package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Map;
import javax.swing.JPanel;

/**
 * Represents a histogram. Contains functionality required to display the histogram.
 */
public class Histogram extends JPanel {

  private Map<Integer, Integer>[] bars;

  /**
   * Constructs an instance of an {@code Histogram} object.
   * @param bars the data to be displayed
   */
  public Histogram(Map<Integer, Integer>[] bars) {
    this.bars = bars;
  }

  @Override
  protected void paintComponent(Graphics g) {
    // paint bars

    int width = (getWidth() / 255) - 2;
    int x = 1;

    for (int i = 0; i < 256; i++) {
      for (int j = 0; j < 4; j++) {
        Color c;
        switch (j) {
          case 0:
            c = new Color(255, 0, 0, 40);
            break;
          case 1:
            c = new Color(0, 255, 0, 40);
            break;
          case 2:
            c = new Color(0, 0, 255, 40);
            break;
          case 3:
            c = new Color(128,128,128,40);
            break;
          default:
            throw new IllegalStateException();
        }

        g.setColor(c);
        int height = bars[j].getOrDefault(i, 0);
        g.fillRect(x, getHeight() - height, width, height);
      }

      x += (width + 2);
    }
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(256 * 10 + 2, 50);
  }
}
