package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests the methods in the Pixel class.
 */
public class PixelTests {

  private Pixel pixel;

  private Pixel redPixel;

  private Pixel greenPixel;

  private Pixel bluePixel;

  private Pixel valuePixel;

  private Pixel intensityPixel;

  private Pixel lumaPixel;

  private Pixel brightPixel;

  private Pixel darkPixel;

  private Pixel maxBrightPixel;

  private Pixel maxDarkPixel;

  @Before
  public void init() {
    this.pixel = new Pixel(50, 150, 100, 255);
    this.redPixel = new Pixel(50, 50, 50, 255);
    this.greenPixel = new Pixel(150, 150, 150, 255);
    this.bluePixel = new Pixel(100, 100, 100, 255);
    this.valuePixel = new Pixel(150, 150, 150, 255);
    this.intensityPixel = new Pixel(100, 100, 100, 255);
    this.lumaPixel = new Pixel(125, 125, 125, 255);
    this.brightPixel = new Pixel(100, 200, 150, 255);
    this.darkPixel = new Pixel(0, 100, 50, 255);
    this.maxBrightPixel = new Pixel(255, 255, 255, 255);
    this.maxDarkPixel = new Pixel(0, 0, 0, 255);
  }

  @Test
  public void testInvalidInitializationNegativeValue() {
    this.pixel = new Pixel(-50, 50, 50, 255);
    assertArrayEquals(new int[]{0, 50, 50}, this.pixel.getRGB());

    this.pixel = new Pixel(50, -50, 50, 255);
    assertArrayEquals(new int[]{50, 0, 50}, this.pixel.getRGB());

    this.pixel = new Pixel(50, 50, -50, 255);
    assertArrayEquals(new int[]{50, 50, 0}, this.pixel.getRGB());

    try {
      this.pixel = new Pixel(50, 50, 50, -255);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("RGB value or max value is negative", e.getMessage());
    }
  }

  @Test
  public void testValidInitialization() {
    int[] expected = {50, 150, 100};
    assertArrayEquals(expected, this.pixel.getRGB());
  }

  @Test
  public void testGetRGB() {
    int[] expected = {50, 150, 100};
    assertArrayEquals(expected, this.pixel.getRGB());
  }

  @Test
  public void testInvalidGreyScale() {
    try {
      this.pixel.greyScale("orange");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid grey scale mode", e.getMessage());
    }
  }

  @Test
  public void testGreyScale() {
    assertArrayEquals(this.redPixel.getRGB(), this.pixel.greyScale("red").getRGB());
    assertArrayEquals(this.greenPixel.getRGB(), this.pixel.greyScale("green").getRGB());
    assertArrayEquals(this.bluePixel.getRGB(), this.pixel.greyScale("blue").getRGB());
    assertArrayEquals(this.valuePixel.getRGB(), this.pixel.greyScale("value").getRGB());
    assertArrayEquals(this.intensityPixel.getRGB(), this.pixel.greyScale("intensity").getRGB());
    assertArrayEquals(this.lumaPixel.getRGB(), this.pixel.greyScale("luma").getRGB());
  }

  @Test
  public void testBrighten() {
    assertArrayEquals(this.brightPixel.getRGB(), this.pixel.brighten(50).getRGB());
    assertArrayEquals(this.darkPixel.getRGB(), this.pixel.brighten(-50).getRGB());
    assertArrayEquals(this.maxBrightPixel.getRGB(), this.pixel.brighten(500).getRGB());
    assertArrayEquals(this.maxDarkPixel.getRGB(), this.pixel.brighten(-500).getRGB());
  }
}
