package controller;



import model.ImageFile;
import model.ImageModel;
import model.ImageModelImpl;
import org.junit.Before;

import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests the Commands.
 */
public class CommandTest {

  private Command brightenCMD;

  private Command greyScaleCMD;

  private Command horizontalCMD;

  private Command verticalCMD;

  private Command blurCMD;

  private Command sharpenCMD;

  private Command sepiaCMD;

  private Command greyScaleCMD2;

  private Command loadCMD;

  private Command saveCMD;

  private ImageModel model;

  @Before
  public void init() {
    this.model = new ImageModelImpl();
    this.brightenCMD = new BrightenCommand(10, "koala", "koala-brighten");
    this.greyScaleCMD = new GreyScaleCommand("red", "koala", "koala-red");
    this.horizontalCMD = new HorizontalFlipCommand("koala", "koala-horizontal");
    this.verticalCMD = new VerticalFlipCommand("koala", "koala-vertical");
    this.loadCMD = new LoadCommand("KoalaCopy.ppm", "koala");
    this.saveCMD = new SaveCommand("Koala-2.ppm", "koala");
    this.blurCMD = new FilterCommand("koala", "koala-blur", "blur");
    this.sharpenCMD = new FilterCommand("koala", "koala-sharpen", "sharpen");
    this.sepiaCMD = new ColorTransformationCommand("koala", "koala-sepia", "sepia");
    this.greyScaleCMD2 = new ColorTransformationCommand("koala", "koala-new-grey", "greyscale");
  }

  @Test
  public void testValidInitialization() {
    try {
      new BrightenCommand(10, "koala", "koala-brighten");
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      new BrightenCommand(99999, "based", "newer");
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      new BrightenCommand(0, "baseball", "newerBaseball");
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      new BrightenCommand(-123, "base", "new");
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      new GreyScaleCommand("blue", "koala", "koala-blue");
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      new GreyScaleCommand("green", "", "new");
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      new GreyScaleCommand("", "base", "new");
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      new GreyScaleCommand("rederr", "base", "");
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      new HorizontalFlipCommand("koala", "koala-horizontal");
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      new HorizontalFlipCommand("", "new");
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      new HorizontalFlipCommand("base", "");
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      new HorizontalFlipCommand("", "");
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      new VerticalFlipCommand("koala", "koala-vertical");
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      new VerticalFlipCommand("", "new");
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      new VerticalFlipCommand("base", "");
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      new VerticalFlipCommand("", "");
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      new LoadCommand("aasdf", "koala");
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      new LoadCommand("slkdfj", "");
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      new LoadCommand("", "dsaf");
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      new LoadCommand("", "");
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      new SaveCommand("Koala-2.ppm", "koala");
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      new SaveCommand("slkdfj", "");
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      new SaveCommand("", "dsaf");
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      new SaveCommand("", "");
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      new FilterCommand("koala", "koala-blur", "blur");
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      new FilterCommand("", "new", "BlUr");
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      new FilterCommand("base", "", "sharpen");
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      new FilterCommand("", "", "ShARpEN");
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      new ColorTransformationCommand("koala", "koala-sepia", "sepia");
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      new ColorTransformationCommand("", "new", "SEPia");
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      new ColorTransformationCommand("base", "", "greyscale");
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      new ColorTransformationCommand("", "", "greySCALE");
    } catch (IllegalArgumentException e) {
      fail();
    }
  }

  @Test
  public void testInvalidInitialization() {
    try {
      new BrightenCommand(0, null, "yes");
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Values must not be null!")) {
        fail();
      }
    }

    try {
      new BrightenCommand(-123, "koala", null);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Values must not be null!")) {
        fail();
      }
    }

    try {
      new BrightenCommand(3, null, null);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Values must not be null!")) {
        fail();
      }
    }

    try {
      new GreyScaleCommand("ice", null, null);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Values must not be null!")) {
        fail();
      }
    }

    try {
      new GreyScaleCommand("blue", "koala", null);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Values must not be null!")) {
        fail();
      }
    }

    try {
      new GreyScaleCommand("ice", null, "zoomers");
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Values must not be null!")) {
        fail();
      }
    }

    try {
      new GreyScaleCommand(null, null, null);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Values must not be null!")) {
        fail();
      }
    }

    try {
      new GreyScaleCommand(null, "llamas", null);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Values must not be null!")) {
        fail();
      }
    }

    try {
      new GreyScaleCommand(null, "llamas", "llamas-two");
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Values must not be null!")) {
        fail();
      }
    }

    try {
      new GreyScaleCommand(null, null, "carrot");
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Values must not be null!")) {
        fail();
      }
    }

    try {
      new HorizontalFlipCommand("Koala", null);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Values must not be null!")) {
        fail();
      }
    }

    try {
      new HorizontalFlipCommand(null, null);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Values must not be null!")) {
        fail();
      }
    }

    try {
      new HorizontalFlipCommand(null, "Koala-grey");
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Values must not be null!")) {
        fail();
      }
    }

    try {
      new VerticalFlipCommand("Koala", null);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Values must not be null!")) {
        fail();
      }
    }

    try {
      new VerticalFlipCommand(null, null);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Values must not be null!")) {
        fail();
      }
    }

    try {
      new VerticalFlipCommand(null, "Koala-grey");
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Values must not be null!")) {
        fail();
      }
    }

    try {
      new HorizontalFlipCommand("Koala", null);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Values must not be null!")) {
        fail();
      }
    }

    try {
      new HorizontalFlipCommand(null, null);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Values must not be null!")) {
        fail();
      }
    }

    try {
      new LoadCommand(null, "Koala-grey");
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Values must not be null!")) {
        fail();
      }
    }

    try {
      new LoadCommand(null, null);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Values must not be null!")) {
        fail();
      }
    }

    try {
      new LoadCommand("asdf", null);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Values must not be null!")) {
        fail();
      }
    }

    try {
      new SaveCommand(null, "Koala-grey");
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Values must not be null!\n")) {
        fail();
      }
    }

    try {
      new SaveCommand(null, null);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Values must not be null!\n")) {
        fail();
      }
    }

    try {
      new SaveCommand("asffds", null);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Values must not be null!\n")) {
        fail();
      }
    }

    try {
      new FilterCommand(null, null, null);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Values must not be null!")) {
        fail();
      }
    }

    try {
      new FilterCommand("blur", null, "yes");
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Values must not be null!")) {
        fail();
      }
    }

    try {
      new FilterCommand("sharpen", "koala", null);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Values must not be null!")) {
        fail();
      }
    }

    try {
      new FilterCommand("SHARPENefadsf", null, null);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Values must not be null!")) {
        fail();
      }
    }

    try {
      new ColorTransformationCommand(null, null, null);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Values must not be null!")) {
        fail();
      }
    }

    try {
      new ColorTransformationCommand("sepia", null, "yes");
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Values must not be null!")) {
        fail();
      }
    }

    try {
      new ColorTransformationCommand("greyscale", "koala", null);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Values must not be null!")) {
        fail();
      }
    }

    try {
      new ColorTransformationCommand("SHARPEN", null, null);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Values must not be null!")) {
        fail();
      }
    }
  }

  @Test
  public void testLoadExecute() {
    try {
      this.model.findFile("koala");
      fail();
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      if (!e.getMessage().equals("Version not found!\n")) {
        fail();
      }
    }

    this.loadCMD.execute(this.model);
    try {
      this.model.findFile("koala");
    } catch (IllegalArgumentException e) {
      fail();
    }

    ImageFile img = this.model.findFile("koala");
    StringBuilder builder = new StringBuilder()
        .append("P3\n")
        .append("3 3\n")
        .append("255\n")
        .append("240\n")
        .append("120\n")
        .append("80\n")
        .append("111\n")
        .append("23\n")
        .append("88\n")
        .append("243\n")
        .append("11\n")
        .append("99\n")
        .append("78\n")
        .append("99\n")
        .append("90\n")
        .append("254\n")
        .append("188\n")
        .append("91\n")
        .append("44\n")
        .append("2\n")
        .append("200\n")
        .append("91\n")
        .append("78\n")
        .append("222\n")
        .append("76\n")
        .append("232\n")
        .append("192\n")
        .append("78\n")
        .append("112\n")
        .append("222\n");
    assertEquals(builder.toString(), img.toString());

    try {
      this.model.findFile("koalaPNG");
      fail();
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      if (!e.getMessage().equals("Version not found!\n")) {
        fail();
      }
    }

    this.loadCMD = new LoadCommand("koala.png", "koalaPNG");
    this.loadCMD.execute(this.model);
    try {
      this.model.findFile("koalaPNG");
    } catch (IllegalArgumentException e) {
      fail();
    }

    img = this.model.findFile("koalaPNG");
    String expected = "240\n"
        + "120\n"
        + "80\n"
        + "111\n"
        + "23\n"
        + "88\n"
        + "243\n"
        + "11\n"
        + "99\n"
        + "78\n"
        + "99\n"
        + "90\n"
        + "254\n"
        + "188\n"
        + "91\n"
        + "44\n"
        + "2\n"
        + "200\n"
        + "91\n"
        + "78\n"
        + "222\n"
        + "76\n"
        + "232\n"
        + "192\n"
        + "78\n"
        + "112\n"
        + "222\n";
    assertEquals(expected, img.toString());

    try {
      this.model.findFile("koalaJPG");
      fail();
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      if (!e.getMessage().equals("Version not found!\n")) {
        fail();
      }
    }

    this.loadCMD = new LoadCommand("koala.jpg", "koalaJPG");
    this.loadCMD.execute(this.model);
    try {
      this.model.findFile("koalaJPG");
    } catch (IllegalArgumentException e) {
      fail();
    }

    img = this.model.findFile("koalaJPG");
    expected = "221\n"
        + "123\n"
        + "184\n"
        + "105\n"
        + "7\n"
        + "68\n"
        + "171\n"
        + "50\n"
        + "166\n"
        + "160\n"
        + "62\n"
        + "123\n"
        + "253\n"
        + "155\n"
        + "216\n"
        + "115\n"
        + "0\n"
        + "110\n"
        + "34\n"
        + "104\n"
        + "129\n"
        + "162\n"
        + "232\n"
        + "255\n"
        + "54\n"
        + "101\n"
        + "179\n";
    assertEquals(expected, img.toString());

    try {
      this.model.findFile("koalaBMP");
      fail();
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      if (!e.getMessage().equals("Version not found!\n")) {
        fail();
      }
    }

    this.loadCMD = new LoadCommand("koala.bmp", "koalaBMP");
    this.loadCMD.execute(this.model);
    try {
      this.model.findFile("koalaBMP");
    } catch (IllegalArgumentException e) {
      fail();
    }

    img = this.model.findFile("koalaBMP");
    expected = "240\n"
        + "120\n"
        + "80\n"
        + "111\n"
        + "23\n"
        + "88\n"
        + "243\n"
        + "11\n"
        + "99\n"
        + "78\n"
        + "99\n"
        + "90\n"
        + "254\n"
        + "188\n"
        + "91\n"
        + "44\n"
        + "2\n"
        + "200\n"
        + "91\n"
        + "78\n"
        + "222\n"
        + "76\n"
        + "232\n"
        + "192\n"
        + "78\n"
        + "112\n"
        + "222\n";
    assertEquals(expected, img.toString());
  }

  @Test
  public void testInvalidLoadExecute() {
    try {
      this.loadCMD.execute(null);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Model cannot be null!\n")) {
        fail();
      }
    }

    this.loadCMD = new LoadCommand("invalid.ppm", "invalid");
    try {
      this.loadCMD.execute(this.model);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Invalid file path!\n")) {
        fail();
      }
    }

    this.loadCMD = new LoadCommand("asfsafsd", "invalid");
    try {
      this.loadCMD.execute(this.model);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Invalid file path!\n")) {
        fail();
      }
    }

    this.loadCMD = new LoadCommand("fake.png", "sdafsafdsfsdaf");
    try {
      this.loadCMD.execute(this.model);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Can't read input file!")) {
        System.out.print(e.getMessage());
        fail();
      }
    }

    this.loadCMD = new LoadCommand("fake.jpg", "sdafsafdsfsdaf");
    try {
      this.loadCMD.execute(this.model);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Can't read input file!")) {
        System.out.print(e.getMessage());
        fail();
      }
    }

    this.loadCMD = new LoadCommand("fake.bmp", "sdafsafdsfsdaf");
    try {
      this.loadCMD.execute(this.model);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Can't read input file!")) {
        System.out.print(e.getMessage());
        fail();
      }
    }

    this.loadCMD = new LoadCommand("fake.fakeExtension", "sdafsafdsfsdaf");
    try {
      this.loadCMD.execute(this.model);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("NOT A FILETYPE!\n")) {
        fail();
      }
    }
  }

  @Test
  public void testInvalidExecutes() {
    try {
      this.brightenCMD.execute(null);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Model cannot be null!")) {
        fail();
      }
    }

    try {
      this.greyScaleCMD.execute(null);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Model cannot be null!")) {
        fail();
      }
    }

    try {
      this.verticalCMD.execute(null);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Model cannot be null!")) {
        fail();
      }
    }

    try {
      this.horizontalCMD.execute(null);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Model cannot be null!")) {
        fail();
      }
    }

    try {
      this.saveCMD.execute(null);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Model cannot be null!\n")) {
        fail();
      }
    }

    try {
      this.greyScaleCMD2.execute(null);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Model cannot be null!\n")) {
        fail();
      }
    }

    try {
      this.blurCMD.execute(null);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Model cannot be null!\n")) {
        fail();
      }
    }

    try {
      this.sharpenCMD.execute(null);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Model cannot be null!\n")) {
        fail();
      }
    }

    try {
      this.sepiaCMD.execute(null);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Model cannot be null!\n")) {
        fail();
      }
    }
  }

  @Test
  public void testBrightenExecute() {
    this.loadCMD.execute(this.model);
    try {
      this.model.findFile("koala-brighten");
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Version not found!\n")) {
        fail();
      }
    }

    this.brightenCMD.execute(this.model);

    try {
      this.model.findFile("koala-brighten");
    } catch (IllegalArgumentException e) {
      fail();
    }

    ImageFile img = this.model.findFile("koala-brighten");
    StringBuilder builder = new StringBuilder()
        .append("P3\n")
        .append("3 3\n")
        .append("255\n")
        .append("250\n")
        .append("130\n")
        .append("90\n")
        .append("121\n")
        .append("33\n")
        .append("98\n")
        .append("253\n")
        .append("21\n")
        .append("109\n")
        .append("88\n")
        .append("109\n")
        .append("100\n")
        .append("255\n")
        .append("198\n")
        .append("101\n")
        .append("54\n")
        .append("12\n")
        .append("210\n")
        .append("101\n")
        .append("88\n")
        .append("232\n")
        .append("86\n")
        .append("242\n")
        .append("202\n")
        .append("88\n")
        .append("122\n")
        .append("232\n");
    assertEquals(builder.toString(), img.toString());

    this.brightenCMD = new BrightenCommand(-100, "koala", "koala-darken");
    try {
      this.model.findFile("koala-darken");
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Version not found!\n")) {
        fail();
      }
    }

    this.brightenCMD.execute(this.model);

    try {
      this.model.findFile("koala-darken");
    } catch (IllegalArgumentException e) {
      fail();
    }

    img = this.model.findFile("koala-darken");
    builder = new StringBuilder()
        .append("P3\n")
        .append("3 3\n")
        .append("255\n")
        .append("140\n")
        .append("20\n")
        .append("0\n")
        .append("11\n")
        .append("0\n")
        .append("0\n")
        .append("143\n")
        .append("0\n")
        .append("0\n")
        .append("0\n")
        .append("0\n")
        .append("0\n")
        .append("154\n")
        .append("88\n")
        .append("0\n")
        .append("0\n")
        .append("0\n")
        .append("100\n")
        .append("0\n")
        .append("0\n")
        .append("122\n")
        .append("0\n")
        .append("132\n")
        .append("92\n")
        .append("0\n")
        .append("12\n")
        .append("122\n");

    assertEquals(builder.toString(), img.toString());
  }

  @Test
  public void testGreyScaleExecute() {
    this.loadCMD.execute(this.model);
    try {
      this.model.findFile("koala-red");
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Version not found!\n")) {
        fail();
      }
    }

    this.greyScaleCMD.execute(this.model);

    try {
      this.model.findFile("koala-red");
    } catch (IllegalArgumentException e) {
      fail();
    }

    ImageFile img = this.model.findFile("koala-red");
    StringBuilder builder = new StringBuilder()
        .append("P3\n")
        .append("3 3\n")
        .append("255\n")
        .append("240\n")
        .append("240\n")
        .append("240\n")
        .append("111\n")
        .append("111\n")
        .append("111\n")
        .append("243\n")
        .append("243\n")
        .append("243\n")
        .append("78\n")
        .append("78\n")
        .append("78\n")
        .append("254\n")
        .append("254\n")
        .append("254\n")
        .append("44\n")
        .append("44\n")
        .append("44\n")
        .append("91\n")
        .append("91\n")
        .append("91\n")
        .append("76\n")
        .append("76\n")
        .append("76\n")
        .append("78\n")
        .append("78\n")
        .append("78\n");
    assertEquals(builder.toString(), img.toString());

    this.greyScaleCMD = new GreyScaleCommand("green", "koala", "koala-green");
    try {
      this.model.findFile("koala-green");
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Version not found!\n")) {
        fail();
      }
    }

    this.greyScaleCMD.execute(this.model);

    try {
      this.model.findFile("koala-green");
    } catch (IllegalArgumentException e) {
      fail();
    }

    img = this.model.findFile("koala-green");
    builder = new StringBuilder()
        .append("P3\n")
        .append("3 3\n")
        .append("255\n")
        .append("120\n")
        .append("120\n")
        .append("120\n")
        .append("23\n")
        .append("23\n")
        .append("23\n")
        .append("11\n")
        .append("11\n")
        .append("11\n")
        .append("99\n")
        .append("99\n")
        .append("99\n")
        .append("188\n")
        .append("188\n")
        .append("188\n")
        .append("2\n")
        .append("2\n")
        .append("2\n")
        .append("78\n")
        .append("78\n")
        .append("78\n")
        .append("232\n")
        .append("232\n")
        .append("232\n")
        .append("112\n")
        .append("112\n")
        .append("112\n");

    assertEquals(builder.toString(), img.toString());

    this.greyScaleCMD = new GreyScaleCommand("blue", "koala", "koala-blue");
    try {
      this.model.findFile("koala-blue");
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Version not found!\n")) {
        fail();
      }
    }

    this.greyScaleCMD.execute(this.model);

    try {
      this.model.findFile("koala-blue");
    } catch (IllegalArgumentException e) {
      fail();
    }

    img = this.model.findFile("koala-blue");
    builder = new StringBuilder()
        .append("P3\n")
        .append("3 3\n")
        .append("255\n")
        .append("80\n")
        .append("80\n")
        .append("80\n")
        .append("88\n")
        .append("88\n")
        .append("88\n")
        .append("99\n")
        .append("99\n")
        .append("99\n")
        .append("90\n")
        .append("90\n")
        .append("90\n")
        .append("91\n")
        .append("91\n")
        .append("91\n")
        .append("200\n")
        .append("200\n")
        .append("200\n")
        .append("222\n")
        .append("222\n")
        .append("222\n")
        .append("192\n")
        .append("192\n")
        .append("192\n")
        .append("222\n")
        .append("222\n")
        .append("222\n");

    assertEquals(builder.toString(), img.toString());

    this.greyScaleCMD = new GreyScaleCommand("value", "koala", "koala-value");
    try {
      this.model.findFile("koala-value");
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Version not found!\n")) {
        fail();
      }
    }

    this.greyScaleCMD.execute(this.model);

    try {
      this.model.findFile("koala-value");
    } catch (IllegalArgumentException e) {
      fail();
    }

    img = this.model.findFile("koala-value");
    builder = new StringBuilder()
        .append("P3\n")
        .append("3 3\n")
        .append("255\n")
        .append("240\n")
        .append("240\n")
        .append("240\n")
        .append("111\n")
        .append("111\n")
        .append("111\n")
        .append("243\n")
        .append("243\n")
        .append("243\n")
        .append("99\n")
        .append("99\n")
        .append("99\n")
        .append("254\n")
        .append("254\n")
        .append("254\n")
        .append("200\n")
        .append("200\n")
        .append("200\n")
        .append("222\n")
        .append("222\n")
        .append("222\n")
        .append("232\n")
        .append("232\n")
        .append("232\n")
        .append("222\n")
        .append("222\n")
        .append("222\n");

    assertEquals(builder.toString(), img.toString());

    this.greyScaleCMD = new GreyScaleCommand("intensity", "koala", "koala-intensity");
    try {
      this.model.findFile("koala-intensity");
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Version not found!\n")) {
        fail();
      }
    }

    this.greyScaleCMD.execute(this.model);

    try {
      this.model.findFile("koala-intensity");
    } catch (IllegalArgumentException e) {
      fail();
    }

    img = this.model.findFile("koala-intensity");
    builder = new StringBuilder()
        .append("P3\n")
        .append("3 3\n")
        .append("255\n")
        .append("146\n")
        .append("146\n")
        .append("146\n")
        .append("74\n")
        .append("74\n")
        .append("74\n")
        .append("117\n")
        .append("117\n")
        .append("117\n")
        .append("89\n")
        .append("89\n")
        .append("89\n")
        .append("177\n")
        .append("177\n")
        .append("177\n")
        .append("82\n")
        .append("82\n")
        .append("82\n")
        .append("130\n")
        .append("130\n")
        .append("130\n")
        .append("166\n")
        .append("166\n")
        .append("166\n")
        .append("137\n")
        .append("137\n")
        .append("137\n");

    assertEquals(builder.toString(), img.toString());

    this.greyScaleCMD = new GreyScaleCommand("luma", "koala", "koala-luma");
    try {
      this.model.findFile("koala-luma");
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Version not found!\n")) {
        fail();
      }
    }

    this.greyScaleCMD.execute(this.model);

    try {
      this.model.findFile("koala-luma");
    } catch (IllegalArgumentException e) {
      fail();
    }

    img = this.model.findFile("koala-luma");
    builder = new StringBuilder()
        .append("P3\n")
        .append("3 3\n")
        .append("255\n")
        .append("142\n")
        .append("142\n")
        .append("142\n")
        .append("46\n")
        .append("46\n")
        .append("46\n")
        .append("66\n")
        .append("66\n")
        .append("66\n")
        .append("93\n")
        .append("93\n")
        .append("93\n")
        .append("195\n")
        .append("195\n")
        .append("195\n")
        .append("25\n")
        .append("25\n")
        .append("25\n")
        .append("91\n")
        .append("91\n")
        .append("91\n")
        .append("195\n")
        .append("195\n")
        .append("195\n")
        .append("112\n")
        .append("112\n")
        .append("112\n");

    assertEquals(builder.toString(), img.toString());
  }

  @Test
  public void testHorizontalFlipExecute() {
    this.loadCMD.execute(this.model);
    try {
      this.model.findFile("koala-horizontal");
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Version not found!\n")) {
        fail();
      }
    }

    this.horizontalCMD.execute(this.model);

    try {
      this.model.findFile("koala-horizontal");
    } catch (IllegalArgumentException e) {
      fail();
    }

    ImageFile img = this.model.findFile("koala-horizontal");

    assertEquals("P3\n" +
        "3 3\n" +
        "255\n" +
        "243\n" +
        "11\n" +
        "99\n" +
        "111\n" +
        "23\n" +
        "88\n" +
        "240\n" +
        "120\n" +
        "80\n" +
        "44\n" +
        "2\n" +
        "200\n" +
        "254\n" +
        "188\n" +
        "91\n" +
        "78\n" +
        "99\n" +
        "90\n" +
        "78\n" +
        "112\n" +
        "222\n" +
        "76\n" +
        "232\n" +
        "192\n" +
        "91\n" +
        "78\n" +
        "222\n", img.toString());
  }

  @Test
  public void testVerticalFlipExecute() {
    this.loadCMD.execute(this.model);
    try {
      this.model.findFile("koala-vertical");
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Version not found!\n")) {
        fail();
      }
    }

    this.verticalCMD.execute(this.model);

    try {
      this.model.findFile("koala-vertical");
    } catch (IllegalArgumentException e) {
      fail();
    }

    ImageFile img = this.model.findFile("koala-vertical");
    StringBuilder builder = new StringBuilder()
        .append("P3\n")
        .append("3 3\n")
        .append("255\n")
        .append("91\n")
        .append("78\n")
        .append("222\n")
        .append("76\n")
        .append("232\n")
        .append("192\n")
        .append("78\n")
        .append("112\n")
        .append("222\n")
        .append("78\n")
        .append("99\n")
        .append("90\n")
        .append("254\n")
        .append("188\n")
        .append("91\n")
        .append("44\n")
        .append("2\n")
        .append("200\n")
        .append("240\n")
        .append("120\n")
        .append("80\n")
        .append("111\n")
        .append("23\n")
        .append("88\n")
        .append("243\n")
        .append("11\n")
        .append("99\n");

    assertEquals(builder.toString(), img.toString());
  }

  @Test
  public void testFilterExecute() {
    this.loadCMD.execute(this.model);
    try {
      this.model.findFile("koala-blur");
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Version not found!\n")) {
        fail();
      }
    }

    this.blurCMD.execute(this.model);

    try {
      this.model.findFile("koala-blur");
    } catch (IllegalArgumentException e) {
      fail();
    }

    ImageFile img = this.model.findFile("koala-blur");

    assertEquals("P3\n"
        + "3 3\n"
        + "255\n"
        + "99\n"
        + "57\n"
        + "47\n"
        + "127\n"
        + "51\n"
        + "73\n"
        + "96\n"
        + "17\n"
        + "66\n"
        + "104\n"
        + "88\n"
        + "89\n"
        + "142\n"
        + "111\n"
        + "132\n"
        + "94\n"
        + "55\n"
        + "119\n"
        + "57\n"
        + "72\n"
        + "96\n"
        + "79\n"
        + "111\n"
        + "133\n"
        + "50\n"
        + "69\n"
        + "110\n", img.toString());

    this.loadCMD.execute(this.model);
    try {
      this.model.findFile("koala-sharpen");
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Version not found!\n")) {
        fail();
      }
    }

    this.sharpenCMD.execute(this.model);

    try {
      this.model.findFile("koala-sharpen");
    } catch (IllegalArgumentException e) {
      fail();
    }

    img = this.model.findFile("koala-sharpen");

    assertEquals("P3\n"
        + "3 3\n"
        + "255\n"
        + "255\n"
        + "143\n"
        + "30\n"
        + "255\n"
        + "75\n"
        + "148\n"
        + "255\n"
        + "0\n"
        + "93\n"
        + "225\n"
        + "243\n"
        + "193\n"
        + "255\n"
        + "255\n"
        + "255\n"
        + "183\n"
        + "106\n"
        + "255\n"
        + "103\n"
        + "174\n"
        + "229\n"
        + "138\n"
        + "255\n"
        + "255\n"
        + "76\n"
        + "176\n"
        + "255\n", img.toString());

    this.sharpenCMD = new FilterCommand("koala-sharpen", "koala-sharpen", "fake");
    try {
      this.sharpenCMD.execute(this.model);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Invalid filter mode!\n")) {
        fail();
      }
    }
  }

  @Test
  public void testColorTransformationCMD() {
    this.loadCMD.execute(this.model);
    try {
      this.model.findFile("koala-sepia");
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Version not found!\n")) {
        fail();
      }
    }

    this.sepiaCMD.execute(this.model);

    try {
      this.model.findFile("koala-sepia");
    } catch (IllegalArgumentException e) {
      fail();
    }

    ImageFile img = this.model.findFile("koala-sepia");

    assertEquals("P3\n"
        + "3 3\n"
        + "255\n"
        + "201\n"
        + "179\n"
        + "139\n"
        + "77\n"
        + "69\n"
        + "54\n"
        + "122\n"
        + "108\n"
        + "84\n"
        + "123\n"
        + "110\n"
        + "85\n"
        + "255\n"
        + "232\n"
        + "181\n"
        + "56\n"
        + "50\n"
        + "39\n"
        + "137\n"
        + "122\n"
        + "95\n"
        + "244\n"
        + "217\n"
        + "169\n"
        + "158\n"
        + "141\n"
        + "110\n", img.toString());

    this.loadCMD.execute(this.model);
    try {
      this.model.findFile("koala-new-grey");
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Version not found!\n")) {
        fail();
      }
    }

    this.greyScaleCMD2.execute(this.model);

    try {
      this.model.findFile("koala-new-grey");
    } catch (IllegalArgumentException e) {
      fail();
    }

    img = this.model.findFile("koala-new-grey");

    assertEquals("P3\n"
        + "3 3\n"
        + "255\n"
        + "142\n"
        + "142\n"
        + "142\n"
        + "46\n"
        + "46\n"
        + "46\n"
        + "66\n"
        + "66\n"
        + "66\n"
        + "93\n"
        + "93\n"
        + "93\n"
        + "195\n"
        + "195\n"
        + "195\n"
        + "25\n"
        + "25\n"
        + "25\n"
        + "91\n"
        + "91\n"
        + "91\n"
        + "195\n"
        + "195\n"
        + "195\n"
        + "112\n"
        + "112\n"
        + "112\n", img.toString());

    this.sepiaCMD = new ColorTransformationCommand("koala-sepia", "koala-sepia", "fake");
    try {
      this.sepiaCMD.execute(this.model);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Invalid color transformation mode\n")) {
        fail();
      }
    }
  }

  @Test
  public void testSaveCommandExecute() {
    this.loadCMD.execute(this.model);
    this.loadCMD = new LoadCommand("Koala-2.ppm", "koala2");

    try {
      this.loadCMD.execute(this.model);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Invalid file path!\n")) {
        fail();
      }
    }

    this.saveCMD.execute(this.model);
    try {
      this.loadCMD.execute(this.model);
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      this.model.findFile("koala2");
    } catch (IllegalArgumentException e) {
      fail();
    }

    this.loadCMD = new LoadCommand("Koala-2.png", "koala2");

    try {
      this.loadCMD.execute(this.model);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Can't read input file!")) {
        fail();
      }
    }

    this.saveCMD = new SaveCommand("Koala-2.png", "koala2");
    this.saveCMD.execute(this.model);
    try {
      this.loadCMD.execute(this.model);
    } catch (IllegalArgumentException e) {
      fail();
    }

    this.loadCMD = new LoadCommand("Koala-2.jpg", "koala2");

    try {
      this.loadCMD.execute(this.model);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Can't read input file!")) {
        fail();
      }
    }

    this.saveCMD = new SaveCommand("Koala-2.jpg", "koala2");
    this.saveCMD.execute(this.model);
    try {
      this.loadCMD.execute(this.model);
    } catch (IllegalArgumentException e) {
      fail();
    }

    this.loadCMD = new LoadCommand("Koala-2.bmp", "koala2");

    try {
      this.loadCMD.execute(this.model);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Can't read input file!")) {
        fail();
      }
    }

    this.saveCMD = new SaveCommand("Koala-2.bmp", "koala2");
    this.saveCMD.execute(this.model);
    try {
      this.loadCMD.execute(this.model);
    } catch (IllegalArgumentException e) {
      fail();
    }

    this.saveCMD = new SaveCommand("fake.fakeExtension", "koala2");
    try {
      this.saveCMD.execute(this.model);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("NOT A FILETYPE!\n")) {
        fail();
      }
    }

    this.loadCMD = new LoadCommand("koala.bmp", "koalaBMP");
    this.loadCMD.execute(this.model);
    this.loadCMD = new LoadCommand("KoalaBMPToPPM.ppm", "koalaBMP");

    try {
      this.loadCMD.execute(this.model);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Invalid file path!\n")) {
        fail();
      }
    }

    this.saveCMD = new SaveCommand("KoalaBMPToPPM.ppm", "koalaBMP");
    this.saveCMD.execute(this.model);
    try {
      this.loadCMD.execute(this.model);
    } catch (IllegalArgumentException e) {
      fail();
    }

    this.loadCMD = new LoadCommand("KoalaBMPToJPG.jpg", "koalaBMP");
    try {
      this.loadCMD.execute(this.model);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Can't read input file!")) {
        fail();
      }
    }

    this.saveCMD = new SaveCommand("KoalaBMPToJPG.jpg", "koalaBMP");
    this.saveCMD.execute(this.model);
    try {
      this.loadCMD.execute(this.model);
    } catch (IllegalArgumentException e) {
      fail();
    }
  }
}
