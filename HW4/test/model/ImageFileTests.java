package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests the methods in the ImageFileImpl class.
 */
public class ImageFileTests {

  Pixel topLeft;

  Pixel bottomLeft;

  Pixel topRight;

  Pixel bottomRight;

  Pixel[][] pixelArray;

  ImageFile imageFile;


  @Before
  public void init() {
    this.topLeft = new Pixel(50, 100, 150, 255);
    this.bottomLeft = new Pixel(50, 100, 150, 255);
    this.topRight = new Pixel(50, 100, 150, 255);
    this.bottomRight = new Pixel(50, 100, 150, 255);
    this.pixelArray
            = new Pixel[][]{new Pixel[]{topLeft, topRight}, new Pixel[]{bottomLeft, bottomRight}};
    this.imageFile = new ImageFileImpl("P3", 2, 2, 255, this.pixelArray);
  }

  @Test
  public void testInvalidInitialization() {
    try {
      this.imageFile = new ImageFileImpl("R4", 2, 2, 255, this.pixelArray);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("This program only processes .ppm files", e.getMessage());
    }
    try {
      this.imageFile = new ImageFileImpl("P3", -5, 2, 255, this.pixelArray);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Height of image must not be negative", e.getMessage());
    }
    try {
      this.imageFile = new ImageFileImpl("P3", 2, -2, 255, this.pixelArray);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Width of image must not be negative", e.getMessage());
    }
    try {
      this.imageFile = new ImageFileImpl("P3", 2, 2, -255, this.pixelArray);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Maximum RGB value must not be negative", e.getMessage());
    }
    try {
      this.imageFile = new ImageFileImpl("P3", 2, 2, 255, null);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Pixel array is null", e.getMessage());
    }
  }

  @Test
  public void testValidInitialization() {
    assertEquals("P3\n"
            + "2 2\n"
            + "255\n"
            + "50\n"
            + "100\n"
            + "150\n"
            + "50\n"
            + "100\n"
            + "150\n"
            + "50\n"
            + "100\n"
            + "150\n"
            + "50\n"
            + "100\n"
            + "150\n", this.imageFile.toString());
  }

  @Test
  public void testToString() {
    assertEquals("P3\n"
            + "2 2\n"
            + "255\n"
            + "50\n"
            + "100\n"
            + "150\n"
            + "50\n"
            + "100\n"
            + "150\n"
            + "50\n"
            + "100\n"
            + "150\n"
            + "50\n"
            + "100\n"
            + "150\n", this.imageFile.toString());
  }

  @Test
  public void testGreyScaleRed() {
    assertEquals("P3\n" +
            "2 2\n" +
            "255\n" +
            "50\n" +
            "50\n" +
            "50\n" +
            "50\n" +
            "50\n" +
            "50\n" +
            "50\n" +
            "50\n" +
            "50\n" +
            "50\n" +
            "50\n" +
            "50\n", this.imageFile.greyScale("red").toString());
    assertEquals("P3\n" +
            "2 2\n" +
            "255\n" +
            "50\n" +
            "50\n" +
            "50\n" +
            "50\n" +
            "50\n" +
            "50\n" +
            "50\n" +
            "50\n" +
            "50\n" +
            "50\n" +
            "50\n" +
            "50\n", this.imageFile.greyScale("Red").toString());
  }

  @Test
  public void testGreyScaleGreen() {
    assertEquals("P3\n" +
            "2 2\n" +
            "255\n" +
            "100\n" +
            "100\n" +
            "100\n" +
            "100\n" +
            "100\n" +
            "100\n" +
            "100\n" +
            "100\n" +
            "100\n" +
            "100\n" +
            "100\n" +
            "100\n", this.imageFile.greyScale("green").toString());
    assertEquals("P3\n" +
            "2 2\n" +
            "255\n" +
            "100\n" +
            "100\n" +
            "100\n" +
            "100\n" +
            "100\n" +
            "100\n" +
            "100\n" +
            "100\n" +
            "100\n" +
            "100\n" +
            "100\n" +
            "100\n", this.imageFile.greyScale("Green").toString());
  }

  @Test
  public void testGreyScaleBlue() {
    assertEquals("P3\n" +
            "2 2\n" +
            "255\n" +
            "150\n" +
            "150\n" +
            "150\n" +
            "150\n" +
            "150\n" +
            "150\n" +
            "150\n" +
            "150\n" +
            "150\n" +
            "150\n" +
            "150\n" +
            "150\n", this.imageFile.greyScale("blue").toString());
    assertEquals("P3\n" +
            "2 2\n" +
            "255\n" +
            "150\n" +
            "150\n" +
            "150\n" +
            "150\n" +
            "150\n" +
            "150\n" +
            "150\n" +
            "150\n" +
            "150\n" +
            "150\n" +
            "150\n" +
            "150\n", this.imageFile.greyScale("Blue").toString());
  }

  @Test
  public void testGreyScaleValue() {
    assertEquals("P3\n" +
            "2 2\n" +
            "255\n" +
            "150\n" +
            "150\n" +
            "150\n" +
            "150\n" +
            "150\n" +
            "150\n" +
            "150\n" +
            "150\n" +
            "150\n" +
            "150\n" +
            "150\n" +
            "150\n", this.imageFile.greyScale("value").toString());
    assertEquals("P3\n" +
            "2 2\n" +
            "255\n" +
            "150\n" +
            "150\n" +
            "150\n" +
            "150\n" +
            "150\n" +
            "150\n" +
            "150\n" +
            "150\n" +
            "150\n" +
            "150\n" +
            "150\n" +
            "150\n", this.imageFile.greyScale("Value").toString());
  }

  @Test
  public void testGreyScaleLuma() {
    assertEquals("P3\n" +
            "2 2\n" +
            "255\n" +
            "92\n" +
            "92\n" +
            "92\n" +
            "92\n" +
            "92\n" +
            "92\n" +
            "92\n" +
            "92\n" +
            "92\n" +
            "92\n" +
            "92\n" +
            "92\n", this.imageFile.greyScale("luma").toString());
    assertEquals("P3\n" +
            "2 2\n" +
            "255\n" +
            "92\n" +
            "92\n" +
            "92\n" +
            "92\n" +
            "92\n" +
            "92\n" +
            "92\n" +
            "92\n" +
            "92\n" +
            "92\n" +
            "92\n" +
            "92\n", this.imageFile.greyScale("luma").toString());
  }

  @Test
  public void testGreyScaleIntensity() {
    assertEquals("P3\n" +
            "2 2\n" +
            "255\n" +
            "100\n" +
            "100\n" +
            "100\n" +
            "100\n" +
            "100\n" +
            "100\n" +
            "100\n" +
            "100\n" +
            "100\n" +
            "100\n" +
            "100\n" +
            "100\n", this.imageFile.greyScale("intensity").toString());
    assertEquals("P3\n" +
            "2 2\n" +
            "255\n" +
            "100\n" +
            "100\n" +
            "100\n" +
            "100\n" +
            "100\n" +
            "100\n" +
            "100\n" +
            "100\n" +
            "100\n" +
            "100\n" +
            "100\n" +
            "100\n", this.imageFile.greyScale("Intensity").toString());
  }

  @Test
  public void testHorizontalFlip() {
    this.topLeft = new Pixel(50, 50, 50, 255);
    this.bottomLeft = new Pixel(50, 50, 50, 255);
    this.topRight = new Pixel(100, 100, 100, 255);
    this.bottomRight = new Pixel(100, 100, 100, 255);
    this.pixelArray
            = new Pixel[][]{new Pixel[]{topLeft, topRight}, new Pixel[]{bottomLeft, bottomRight}};
    this.imageFile = new ImageFileImpl("P3", 2, 2, 255, this.pixelArray);
    assertEquals("P3\n" +
            "2 2\n" +
            "255\n" +
            "100\n" +
            "100\n" +
            "100\n" +
            "50\n" +
            "50\n" +
            "50\n" +
            "100\n" +
            "100\n" +
            "100\n" +
            "50\n" +
            "50\n" +
            "50\n", this.imageFile.horizontalFlip().toString());
  }

  @Test
  public void testVerticalFlip() {
    this.topLeft = new Pixel(50, 50, 50, 255);
    this.bottomLeft = new Pixel(100, 100, 100, 255);
    this.topRight = new Pixel(50, 50, 50, 255);
    this.bottomRight = new Pixel(100, 100, 100, 255);
    this.pixelArray
            = new Pixel[][]{new Pixel[]{topLeft, topRight}, new Pixel[]{bottomLeft, bottomRight}};
    this.imageFile = new ImageFileImpl("P3", 2, 2, 255, this.pixelArray);
    assertEquals("P3\n" +
            "2 2\n" +
            "255\n" +
            "100\n" +
            "100\n" +
            "100\n" +
            "100\n" +
            "100\n" +
            "100\n" +
            "50\n" +
            "50\n" +
            "50\n" +
            "50\n" +
            "50\n" +
            "50\n", this.imageFile.verticalFlip().toString());
  }

  @Test
  public void Brighten() {
    assertEquals("P3\n" +
            "2 2\n" +
            "255\n" +
            "100\n" +
            "150\n" +
            "200\n" +
            "100\n" +
            "150\n" +
            "200\n" +
            "100\n" +
            "150\n" +
            "200\n" +
            "100\n" +
            "150\n" +
            "200\n", this.imageFile.brighten(50).toString());
    assertEquals("P3\n" +
            "2 2\n" +
            "255\n" +
            "255\n" +
            "255\n" +
            "255\n" +
            "255\n" +
            "255\n" +
            "255\n" +
            "255\n" +
            "255\n" +
            "255\n" +
            "255\n" +
            "255\n" +
            "255\n", this.imageFile.brighten(500).toString());
    assertEquals("P3\n" +
            "2 2\n" +
            "255\n" +
            "0\n" +
            "50\n" +
            "100\n" +
            "0\n" +
            "50\n" +
            "100\n" +
            "0\n" +
            "50\n" +
            "100\n" +
            "0\n" +
            "50\n" +
            "100\n", this.imageFile.brighten(-50).toString());
    assertEquals("P3\n" +
            "2 2\n" +
            "255\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "0\n", this.imageFile.brighten(-500).toString());
  }



  private Pixel[][] pixelArray2;

  private ImageFile imageFile2;

  @Before
  public void init2() {
    Pixel topLeft2 = new Pixel(0, 50, 100, 255);
    Pixel topMid2 = new Pixel(50, 100, 150, 255);
    Pixel topRight2 = new Pixel(100, 150, 200, 255);
    Pixel midLeft2 = new Pixel(0, 50, 100, 255);
    Pixel midMid2 = new Pixel(50, 100, 150, 255);
    Pixel midRight2 = new Pixel(100, 150, 200, 255);
    Pixel bottomLeft2 = new Pixel(0, 50, 100, 255);
    Pixel bottomMid2 = new Pixel(50, 100, 150, 255);
    Pixel bottomRight2 = new Pixel(100, 150, 200, 255);
    this.pixelArray2 = new Pixel[3][3];
    this.pixelArray2[0] = new Pixel[]{topLeft2, topMid2, topRight2};
    this.pixelArray2[1] = new Pixel[]{midLeft2, midMid2, midRight2};
    this.pixelArray2[2] = new Pixel[]{bottomLeft2, bottomMid2, bottomRight2};
    this.imageFile2 = new CommonImageFile(this.pixelArray2);
  }

  @Test
  public void testInvalidInitialization2() {
    try {
      this.imageFile2 = new CommonImageFile(null);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Pixel array is null", e.getMessage());
    }
    try {
      this.imageFile2 = new ImageFileImpl("sd", 2, 2, 2, null);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Pixel array is null", e.getMessage());
    }
  }

  @Test
  public void testValidInitialization2() {
    this.imageFile2 = new CommonImageFile(this.pixelArray2);
    assertEquals(3, this.imageFile2.getHeight());
    assertEquals(3, this.imageFile2.getWidth());
    this.imageFile2 = new ImageFileImpl("P3", 3, 3, 255, this.pixelArray2);
    assertEquals(3, this.imageFile2.getHeight());
    assertEquals(3, this.imageFile2.getWidth());
  }

  @Test
  public void testToString2() {
    try {
      this.imageFile2.toString();
      fail();
    } catch (UnsupportedOperationException e) {
      assertEquals("No toString implementation necessary", e.getMessage());
    }
  }

  @Test
  public void testBlur() {
    ImageFile output = this.imageFile2.filter("blur");
    int[][][] filteredPixelArray = new int[3][3][3];
    filteredPixelArray[0] = new int[3][3];
    filteredPixelArray[0][0] = new int[]{9, 37, 65};
    filteredPixelArray[0][1] = new int[]{37, 75, 112};
    filteredPixelArray[0][2] = new int[]{46, 75, 103};
    filteredPixelArray[1][0] = new int[]{12, 50, 87};
    filteredPixelArray[1][1] = new int[]{50, 100, 150};
    filteredPixelArray[1][2] = new int[]{62, 100, 137};
    filteredPixelArray[2][0] = new int[]{9, 37, 65};
    filteredPixelArray[2][1] = new int[]{37, 75, 112};
    filteredPixelArray[2][2] = new int[]{46, 75, 103};
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        for (int k = 0; k < 3; k++) {
          assertEquals(filteredPixelArray[i][j][k], output.getPixelAt(i, j).getRGB()[k]);
        }
      }
    }
  }

  @Test
  public void testSharpen() {
    ImageFile output = this.imageFile2.filter("sharpen");
    int[][][] filteredPixelArray = new int[3][3][3];
    filteredPixelArray[0] = new int[3][3];
    filteredPixelArray[0][0] = new int[]{0, 37, 93};
    filteredPixelArray[0][1] = new int[]{93, 187, 255};
    filteredPixelArray[0][2] = new int[]{131, 187, 243};
    filteredPixelArray[1][0] = new int[]{0, 93, 187};
    filteredPixelArray[1][1] = new int[]{150, 255, 255};
    filteredPixelArray[1][2] = new int[]{187, 255, 255};
    filteredPixelArray[2][0] = new int[]{0, 37, 93};
    filteredPixelArray[2][1] = new int[]{93, 187, 255};
    filteredPixelArray[2][2] = new int[]{131, 187, 243};
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        for (int k = 0; k < 3; k++) {
          assertEquals(filteredPixelArray[i][j][k], output.getPixelAt(i, j).getRGB()[k]);
        }
      }
    }
  }

  @Test
  public void testFilterException() {
    try {
      ImageFile output = this.imageFile2.filter("BAD");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid filter mode", e.getMessage());
    }
  }

  @Test
  public void testGreyScale() {
    ImageFile output = this.imageFile2.colorTransformation("greyscale");
    int[][][] greyScaleArray = new int[3][3][3];
    greyScaleArray[0] = new int[3][3];
    greyScaleArray[0][0] = new int[]{42, 42, 42};
    greyScaleArray[0][1] = new int[]{92, 92, 92};
    greyScaleArray[0][2] = new int[]{142, 142, 142};
    greyScaleArray[1][0] = new int[]{42, 42, 42};
    greyScaleArray[1][1] = new int[]{92, 92, 92};
    greyScaleArray[1][2] = new int[]{142, 142, 142};
    greyScaleArray[2][0] = new int[]{42, 42, 42};
    greyScaleArray[2][1] = new int[]{92, 92, 92};
    greyScaleArray[2][2] = new int[]{142, 142, 142};
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        for (int k = 0; k < 3; k++) {
          assertEquals(greyScaleArray[i][j][k], output.getPixelAt(i, j).getRGB()[k]);
        }
      }
    }
  }

  @Test
  public void testSepia() {
    ImageFile output = this.imageFile2.colorTransformation("sepia");
    int[][][] sepiaArray = new int[3][3][3];
    sepiaArray[0] = new int[3][3];
    sepiaArray[0][0] = new int[]{57, 51, 39};
    sepiaArray[0][1] = new int[]{124, 111, 86};
    sepiaArray[0][2] = new int[]{192, 171, 133};
    sepiaArray[1][0] = new int[]{57, 51, 39};
    sepiaArray[1][1] = new int[]{124, 111, 86};
    sepiaArray[1][2] = new int[]{192, 171, 133};
    sepiaArray[2][0] = new int[]{57, 51, 39};
    sepiaArray[2][1] = new int[]{124, 111, 86};
    sepiaArray[2][2] = new int[]{192, 171, 133};
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        for (int k = 0; k < 3; k++) {
          assertEquals(sepiaArray[i][j][k], output.getPixelAt(i, j).getRGB()[k]);
        }
      }
    }
  }

  @Test
  public void testColorTransformationException() {
    try {
      ImageFile output = this.imageFile2.colorTransformation("BAD");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid color transformation mode", e.getMessage());
    }
  }

  @Test
  public void testGetWidth() {
    assertEquals(3, this.imageFile2.getWidth());
  }

  @Test
  public void testGetHeight() {
    assertEquals(3, this.imageFile2.getHeight());
  }

  @Test
  public void testGetPixelAt() {
    int[] rgb1 = new int[]{0, 50, 100};
    int[] rgb2 = new int[]{50, 100, 150};
    int[] rgb3 = new int[]{100, 150, 200};
    assertArrayEquals(rgb1, this.imageFile2.getPixelAt(0, 0).getRGB());
    assertArrayEquals(rgb2, this.imageFile2.getPixelAt(1, 1).getRGB());
    assertArrayEquals(rgb3, this.imageFile2.getPixelAt(2, 2).getRGB());
  }
}
