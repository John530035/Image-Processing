package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests the methods in the ImageModelImpl class.
 */
public class ImageModelTests {

  private ImageModel model;

  private ImageFile imageFile;

  private ImageFile imageFile2;

  @Before
  public void init() {
    Pixel topLeft = new Pixel(50, 100, 150, 255);
    Pixel topRight = new Pixel(50, 100, 150, 255);
    Pixel bottomLeft = new Pixel(50, 100, 150, 255);
    Pixel bottomRight = new Pixel(50, 100, 150, 255);
    Pixel[][] pixelArray =
            new Pixel[][]{new Pixel[]{topLeft, topRight}, new Pixel[]{bottomLeft, bottomRight}};
    this.model = new ImageModelImpl();
    this.imageFile = new ImageFileImpl("P3", 2, 2, 255, pixelArray);
    this.imageFile2 = new ImageFileImpl("P3", 45, 32, 300, pixelArray);
  }

  @Test
  public void testValidInitialization() {
    try {
      this.model.findFile("any file name");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Version not found!\n", e.getMessage());
    }
  }

  @Test
  public void testLoad() {
    this.model.load("koala", this.imageFile);
    assertEquals(this.model.findFile("koala"), this.imageFile);
    this.model.load("koala", this.imageFile2);
    assertEquals(this.model.findFile("koala"), this.imageFile2);
  }

  @Test
  public void testInvalidFindFile() {
    try {
      this.model.findFile("bad file");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Version not found!\n", e.getMessage());
    }
  }

  @Test
  public void testFindFile() {
    this.model.load("koala", this.imageFile);
    assertEquals(this.imageFile, this.model.findFile("koala"));
    this.model.load("koala", this.imageFile2);
    assertEquals(this.imageFile2, this.model.findFile("koala"));
  }

  @Test
  public void testGreyScale() {
    try {
      this.model.greyScale("red", "koala");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Version not found!\n", e.getMessage());
    }
    this.model.load("koala", this.imageFile);
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
            "50\n", this.model.greyScale("red", "koala").toString());
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
            "100\n", this.model.greyScale("green", "koala").toString());
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
            "150\n", this.model.greyScale("blue", "koala").toString());
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
            "150\n", this.model.greyScale("value", "koala").toString());
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
            "100\n", this.model.greyScale("intensity", "koala").toString());
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
            "92\n", this.model.greyScale("luma", "koala").toString());
  }

  @Test
  public void testHorizontalFlip() {
    try {
      this.model.greyScale("red", "koala");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Version not found!\n", e.getMessage());
    }
    Pixel topLeft = new Pixel(50, 50, 50, 255);
    Pixel bottomLeft = new Pixel(50, 50, 50, 255);
    Pixel topRight = new Pixel(100, 100, 100, 255);
    Pixel bottomRight = new Pixel(100, 100, 100, 255);
    Pixel[][] pixelArray
            = new Pixel[][]{new Pixel[]{topLeft, topRight}, new Pixel[]{bottomLeft, bottomRight}};
    this.imageFile = new ImageFileImpl("P3", 2, 2, 255, pixelArray);
    this.model.load("koala", this.imageFile);
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
            "50\n", this.model.horizontalFlip("koala").toString());
  }

  @Test
  public void testVerticalFlip() {
    try {
      this.model.greyScale("red", "koala");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Version not found!\n", e.getMessage());
    }

    Pixel topLeft = new Pixel(50, 50, 50, 255);
    Pixel bottomLeft = new Pixel(100, 100, 100, 255);
    Pixel topRight = new Pixel(50, 50, 50, 255);
    Pixel bottomRight = new Pixel(100, 100, 100, 255);
    Pixel[][] pixelArray
            = new Pixel[][]{new Pixel[]{topLeft, topRight}, new Pixel[]{bottomLeft, bottomRight}};
    this.imageFile = new ImageFileImpl("P3", 2, 2, 255, pixelArray);
    this.model.load("koala", this.imageFile);
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
            "50\n", this.model.verticalFlip("koala").toString());
  }

  @Test
  public void testBrighten() {
    try {
      this.model.greyScale("red", "koala");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Version not found!\n", e.getMessage());
    }
    this.model.load("koala", this.imageFile);
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
            "200\n", this.model.brighten("koala", 50).toString());
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
            "100\n", this.model.brighten("koala", -50).toString());
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
            "255\n", this.model.brighten("koala", 500).toString());
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
            "0\n", this.model.brighten("koala", -500).toString());
  }

  private ImageModel model2;

  private ImageFile imageFile3;

  @Before
  public void init2() {
    this.model2 = new ImageModelImpl();
    Pixel topLeft2 = new Pixel(0, 50, 100, 255);
    Pixel topMid2 = new Pixel(50, 100, 150, 255);
    Pixel topRight2 = new Pixel(100, 150, 200, 255);
    Pixel midLeft2 = new Pixel(0, 50, 100, 255);
    Pixel midMid2 = new Pixel(50, 100, 150, 255);
    Pixel midRight2 = new Pixel(100, 150, 200, 255);
    Pixel bottomLeft2 = new Pixel(0, 50, 100, 255);
    Pixel bottomMid2 = new Pixel(50, 100, 150, 255);
    Pixel bottomRight2 = new Pixel(100, 150, 200, 255);
    Pixel[][] pixelArray2 = new Pixel[3][3];
    pixelArray2[0] = new Pixel[]{topLeft2, topMid2, topRight2};
    pixelArray2[1] = new Pixel[]{midLeft2, midMid2, midRight2};
    pixelArray2[2] = new Pixel[]{bottomLeft2, bottomMid2, bottomRight2};
    this.imageFile3 = new CommonImageFile(pixelArray2);
  }

  @Test
  public void testBlur() {
    try {
      this.model2.filter("koala", "blur");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Version not found!\n", e.getMessage());
    }

    this.model2.load("koala", this.imageFile3);

    ImageFile output = this.model2.filter("koala", "blur");
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
    try {
      this.model2.filter("koala", "sharpen");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Version not found!\n", e.getMessage());
    }

    this.model2.load("koala", this.imageFile3);

    ImageFile output = this.model2.filter("koala", "sharpen");
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
      this.model2.load("dog", this.imageFile3);
      this.model2.filter("dog", "BAD");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid filter mode", e.getMessage());
    }
  }

  @Test
  public void testGreyScale2() {
    try {
      this.model2.colorTransformation("koala", "greyscale");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Version not found!\n", e.getMessage());
    }

    this.model2.load("koala", this.imageFile3);

    ImageFile output = this.model2.colorTransformation("koala", "greyscale");
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
    try {
      this.model2.colorTransformation("koala", "sepia");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Version not found!\n", e.getMessage());
    }

    this.model2.load("koala", this.imageFile3);

    ImageFile output = this.model2.colorTransformation("koala", "sepia");
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
      this.model2.load("dog", this.imageFile3);
      this.model2.colorTransformation("dog", "BAD");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid color transformation mode", e.getMessage());
    }
  }
}
