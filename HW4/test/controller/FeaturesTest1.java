package controller;

import java.io.File;

import model.ImageModel;
import model.ImageModelImpl;

import org.junit.Before;
import org.junit.Test;

import view.GUIView;
import view.ImageGUIView;

import static org.junit.Assert.assertEquals;

public class FeaturesTest1 {

  private Features features;

  private ImageModel model;

  private ImageGUIView mockView;

  private StringBuilder log;

  private StringBuilder log1;

  private StringBuilder log2;

  private StringBuilder log3;


  @Before
  public void init() {
    ImageGUIView view = new GUIView();
    this.log = new StringBuilder();
    this.log1 = new StringBuilder();
    this.log2 = new StringBuilder();
    this.log3 = new StringBuilder();
    this.mockView = new MockGUIView1(this.log, this.log1, this.log2);
    this.model = new ImageModelImpl();
    this.features = new FeaturesImpl(this.model, view);
  }

  @Test
  public void testLoadException() {
    this.features = new FeaturesImpl(null, this.mockView);
    this.features.load("res/Dog.jpg");
    assertEquals("Model cannot be null!\n", this.log2.toString());
  }

  @Test
  public void testLoad() {
    this.features = new FeaturesImpl(this.model, this.mockView);
    this.features.load("KoalaCopy.ppm");
    assertEquals("-1017776\n" +
            "-11639974\n" +
            "-10793250\n" +
            "-9496744\n" +
            "-82853\n" +
            "-11736896\n" +
            "-849053\n" +
            "-13892920\n" +
            "-11636514\n" +
            "3, 3", this.log.toString());
    assertEquals("2562, 50", this.log1.toString());
  }

  @Test
  public void testBrightenException() {
    this.features = new FeaturesImpl(this.model, this.mockView);
    this.features.load("res/Dog.jpg");
    this.features.brighten("");
    assertEquals("No number entered!", this.log2.toString());
  }

  @Test
  public void testBrighten() {
    this.features = new FeaturesImpl(this.model, this.mockView);
    this.features.load("KoalaCopy.ppm");
    this.features.brighten("10");
    assertEquals("-1017776\n" +
            "-11639974\n" +
            "-10793250\n" +
            "-9496744\n" +
            "-82853\n" +
            "-11736896\n" +
            "-849053\n" +
            "-13892920\n" +
            "-11636514\n" +
            "3, 3-359846\n" +
            "-10982044\n" +
            "-10135320\n" +
            "-8838814\n" +
            "-14747\n" +
            "-11078966\n" +
            "-191123\n" +
            "-13234990\n" +
            "-10978584\n" +
            "3, 3", this.log.toString());
    assertEquals("2562, 502562, 50", this.log1.toString());
  }


  @Test
  public void testVerticalFlip() {
    this.features = new FeaturesImpl(this.model, this.mockView);
    this.features.load("KoalaCopy.ppm");
    this.features.verticalFlip();
    assertEquals("-1017776\n" +
            "-11639974\n" +
            "-10793250\n" +
            "-9496744\n" +
            "-82853\n" +
            "-11736896\n" +
            "-849053\n" +
            "-13892920\n" +
            "-11636514\n" +
            "3, 3-10793250\n" +
            "-11639974\n" +
            "-1017776\n" +
            "-11736896\n" +
            "-82853\n" +
            "-9496744\n" +
            "-11636514\n" +
            "-13892920\n" +
            "-849053\n" +
            "3, 3", this.log.toString());
    assertEquals("2562, 502562, 50", this.log1.toString());
  }

  @Test
  public void testHorizontalFlip() {
    this.features = new FeaturesImpl(this.model, this.mockView);
    this.features.load("KoalaCopy.ppm");
    this.features.horizontalFlip();
    assertEquals("-1017776\n" +
            "-11639974\n" +
            "-10793250\n" +
            "-9496744\n" +
            "-82853\n" +
            "-11736896\n" +
            "-849053\n" +
            "-13892920\n" +
            "-11636514\n" +
            "3, 3-849053\n" +
            "-13892920\n" +
            "-11636514\n" +
            "-9496744\n" +
            "-82853\n" +
            "-11736896\n" +
            "-1017776\n" +
            "-11639974\n" +
            "-10793250\n" +
            "3, 3", this.log.toString());
    assertEquals("2562, 502562, 50", this.log1.toString());
  }

  @Test
  public void testBlur() {
    this.features = new FeaturesImpl(this.model, this.mockView);
    this.features.load("KoalaCopy.ppm");
    this.features.blur();
    assertEquals("-1017776\n" +
            "-11639974\n" +
            "-10793250\n" +
            "-9496744\n" +
            "-82853\n" +
            "-11736896\n" +
            "-849053\n" +
            "-13892920\n" +
            "-11636514\n" +
            "3, 3-10274513\n" +
            "-9938855\n" +
            "-13023136\n" +
            "-8441015\n" +
            "-7442556\n" +
            "-11571323\n" +
            "-10481342\n" +
            "-10602633\n" +
            "-13482642\n" +
            "3, 3", this.log.toString());
    assertEquals("2562, 502562, 50", this.log1.toString());
  }

  @Test
  public void testSharpen() {
    this.features = new FeaturesImpl(this.model, this.mockView);
    this.features.load("KoalaCopy.ppm");
    this.features.sharpen();
    assertEquals("-1017776\n"
            + "-11639974\n"
            + "-10793250\n"
            + "-9496744\n"
            + "-82853\n"
            + "-11736896\n"
            + "-849053\n"
            + "-13892920\n"
            + "-11636514\n"
            + "3, 3-28898\n"
            + "-1969215\n"
            + "-9982235\n"
            + "-46188\n"
            + "-1\n"
            + "-7667713\n"
            + "-65443\n"
            + "-4756737\n"
            + "-11751169\n"
            + "3, 3", this.log.toString());
    assertEquals("2562, 502562, 50", this.log1.toString());

  }

  @Test
  public void testSepia() {
    this.features = new FeaturesImpl(this.model, this.mockView);
    this.features.load("KoalaCopy.ppm");
    this.features.sepia();
    assertEquals("-1017776\n"
            + "-11639974\n"
            + "-10793250\n"
            + "-9496744\n"
            + "-82853\n"
            + "-11736896\n"
            + "-849053\n"
            + "-13892920\n"
            + "-11636514\n"
            + "3, 3-3558517\n"
            + "-8688043\n"
            + "-7767457\n"
            + "-11713226\n"
            + "-5963\n"
            + "-730711\n"
            + "-8754092\n"
            + "-13094361\n"
            + "-6386322\n"
            + "3, 3", this.log.toString());
    assertEquals("2562, 502562, 50", this.log1.toString());
  }

  @Test
  public void testGreyscale() {
    this.features = new FeaturesImpl(this.model, this.mockView);
    this.features.load("KoalaCopy.ppm");
    this.features.greyscale();
    assertEquals("-1017776\n"
            + "-11639974\n"
            + "-10793250\n"
            + "-9496744\n"
            + "-82853\n"
            + "-11736896\n"
            + "-849053\n"
            + "-13892920\n"
            + "-11636514\n"
            + "3, 3-7434610\n"
            + "-10658467\n"
            + "-10790053\n"
            + "-13750738\n"
            + "-3947581\n"
            + "-3947581\n"
            + "-12434878\n"
            + "-15132391\n"
            + "-9408400\n"
            + "3, 3", this.log.toString());
    assertEquals("2562, 502562, 50", this.log1.toString());
  }

  @Test
  public void testComponentVisualization() {
    this.features = new FeaturesImpl(this.model, this.mockView);
    this.features.load("KoalaCopy.ppm");
    this.features.componentVisualization("red");
    assertEquals("-1017776\n"
            + "-11639974\n"
            + "-10793250\n"
            + "-9496744\n"
            + "-82853\n"
            + "-11736896\n"
            + "-849053\n"
            + "-13892920\n"
            + "-11636514\n"
            + "3, 3-986896\n"
            + "-11645362\n"
            + "-10790053\n"
            + "-9474193\n"
            + "-65794\n"
            + "-11776948\n"
            + "-789517\n"
            + "-13882324\n"
            + "-11645362\n"
            + "3, 3", this.log.toString());
    assertEquals("2562, 502562, 50", this.log1.toString());

    this.log = new StringBuilder();
    this.log1 = new StringBuilder();
    this.log2 = new StringBuilder();
    this.log3 = new StringBuilder();
    this.mockView = new MockGUIView1(this.log, this.log1, this.log2);
    this.features = new FeaturesImpl(this.model, this.mockView);
    this.features.load("KoalaCopy.ppm");
    this.features.componentVisualization("ReD");
    assertEquals("-1017776\n"
            + "-11639974\n"
            + "-10793250\n"
            + "-9496744\n"
            + "-82853\n"
            + "-11736896\n"
            + "-849053\n"
            + "-13892920\n"
            + "-11636514\n"
            + "3, 3-986896\n"
            + "-11645362\n"
            + "-10790053\n"
            + "-9474193\n"
            + "-65794\n"
            + "-11776948\n"
            + "-789517\n"
            + "-13882324\n"
            + "-11645362\n"
            + "3, 3", this.log.toString());
    assertEquals("2562, 502562, 50", this.log1.toString());

    this.log = new StringBuilder();
    this.log1 = new StringBuilder();
    this.log2 = new StringBuilder();
    this.log3 = new StringBuilder();
    this.mockView = new MockGUIView1(this.log, this.log1, this.log2);
    this.features = new FeaturesImpl(this.model, this.mockView);
    this.features.load("KoalaCopy.ppm");
    this.features.componentVisualization("green");
    assertEquals("-1017776\n"
            + "-11639974\n"
            + "-10793250\n"
            + "-9496744\n"
            + "-82853\n"
            + "-11736896\n"
            + "-849053\n"
            + "-13892920\n"
            + "-11636514\n"
            + "3, 3-8882056\n"
            + "-10263709\n"
            + "-11645362\n"
            + "-15263977\n"
            + "-4408132\n"
            + "-1513240\n"
            + "-16053493\n"
            + "-16645630\n"
            + "-9408400\n"
            + "3, 3", this.log.toString());
    assertEquals("2562, 502562, 50", this.log1.toString());

    this.log = new StringBuilder();
    this.log1 = new StringBuilder();
    this.log2 = new StringBuilder();
    this.log3 = new StringBuilder();
    this.mockView = new MockGUIView1(this.log, this.log1, this.log2);
    this.features = new FeaturesImpl(this.model, this.mockView);
    this.features.load("KoalaCopy.ppm");
    this.features.componentVisualization("blue");
    assertEquals("-1017776\n"
            + "-11639974\n"
            + "-10793250\n"
            + "-9496744\n"
            + "-82853\n"
            + "-11736896\n"
            + "-849053\n"
            + "-13892920\n"
            + "-11636514\n"
            + "3, 3-11513776\n"
            + "-10855846\n"
            + "-2171170\n"
            + "-10987432\n"
            + "-10790053\n"
            + "-4144960\n"
            + "-10263709\n"
            + "-3618616\n"
            + "-2171170\n"
            + "3, 3", this.log.toString());
    assertEquals("2562, 502562, 50", this.log1.toString());

    this.log = new StringBuilder();
    this.log1 = new StringBuilder();
    this.log2 = new StringBuilder();
    this.log3 = new StringBuilder();
    this.mockView = new MockGUIView1(this.log, this.log1, this.log2);
    this.features = new FeaturesImpl(this.model, this.mockView);
    this.features.load("KoalaCopy.ppm");
    this.features.componentVisualization("intensity");
    assertEquals("-1017776\n"
            + "-11639974\n"
            + "-10793250\n"
            + "-9496744\n"
            + "-82853\n"
            + "-11736896\n"
            + "-849053\n"
            + "-13892920\n"
            + "-11636514\n"
            + "3, 3-7171438\n"
            + "-10921639\n"
            + "-8224126\n"
            + "-11908534\n"
            + "-5131855\n"
            + "-5855578\n"
            + "-9079435\n"
            + "-11382190\n"
            + "-7763575\n"
            + "3, 3", this.log.toString());
    assertEquals("2562, 502562, 50", this.log1.toString());

    this.log = new StringBuilder();
    this.log1 = new StringBuilder();
    this.log2 = new StringBuilder();
    this.log3 = new StringBuilder();
    this.mockView = new MockGUIView1(this.log, this.log1, this.log2);
    this.features = new FeaturesImpl(this.model, this.mockView);
    this.features.load("KoalaCopy.ppm");
    this.features.componentVisualization("value");
    assertEquals("-1017776\n"
            + "-11639974\n"
            + "-10793250\n"
            + "-9496744\n"
            + "-82853\n"
            + "-11736896\n"
            + "-849053\n"
            + "-13892920\n"
            + "-11636514\n"
            + "3, 3-986896\n"
            + "-10263709\n"
            + "-2171170\n"
            + "-9474193\n"
            + "-65794\n"
            + "-1513240\n"
            + "-789517\n"
            + "-3618616\n"
            + "-2171170\n"
            + "3, 3", this.log.toString());
    assertEquals("2562, 502562, 50", this.log1.toString());

    this.log = new StringBuilder();
    this.log1 = new StringBuilder();
    this.log2 = new StringBuilder();
    this.log3 = new StringBuilder();
    this.mockView = new MockGUIView1(this.log, this.log1, this.log2);
    this.features = new FeaturesImpl(this.model, this.mockView);
    this.features.load("KoalaCopy.ppm");
    this.features.componentVisualization("luma");
    assertEquals("-1017776\n"
            + "-11639974\n"
            + "-10793250\n"
            + "-9496744\n"
            + "-82853\n"
            + "-11736896\n"
            + "-849053\n"
            + "-13892920\n"
            + "-11636514\n"
            + "3, 3-7434610\n"
            + "-10658467\n"
            + "-10790053\n"
            + "-13750738\n"
            + "-3947581\n"
            + "-3947581\n"
            + "-12434878\n"
            + "-15132391\n"
            + "-9408400\n"
            + "3, 3", this.log.toString());
    assertEquals("2562, 502562, 50", this.log1.toString());

    this.log = new StringBuilder();
    this.log1 = new StringBuilder();
    this.log2 = new StringBuilder();
    this.log3 = new StringBuilder();
    this.mockView = new MockGUIView1(this.log, this.log1, this.log2);
    this.features = new FeaturesImpl(this.model, this.mockView);
    this.features.componentVisualization("luma");
    assertEquals("", this.log.toString());
    assertEquals("", this.log1.toString());

    this.log = new StringBuilder();
    this.log1 = new StringBuilder();
    this.log2 = new StringBuilder();
    this.log3 = new StringBuilder();
    this.mockView = new MockGUIView1(this.log, this.log1, this.log2);
    this.features = new FeaturesImpl(this.model, this.mockView);
    this.features.load("KoalaCopy.ppm");
    this.features.componentVisualization("fake");
    assertEquals("-1017776\n"
            + "-11639974\n"
            + "-10793250\n"
            + "-9496744\n"
            + "-82853\n"
            + "-11736896\n"
            + "-849053\n"
            + "-13892920\n"
            + "-11636514\n"
            + "3, 3", this.log.toString());
    assertEquals("2562, 50", this.log1.toString());
  }

  @Test
  public void testSave() {
    this.features = new FeaturesImpl(this.model, this.mockView);
    this.features.load("KoalaCopy.ppm");
    assertEquals("-1017776\n"
            + "-11639974\n"
            + "-10793250\n"
            + "-9496744\n"
            + "-82853\n"
            + "-11736896\n"
            + "-849053\n"
            + "-13892920\n"
            + "-11636514\n"
            + "3, 3", this.log.toString());

    this.features.load("KoalaCopySaveTest.jpg");
    assertEquals("-1017776\n"
            + "-11639974\n"
            + "-10793250\n"
            + "-9496744\n"
            + "-82853\n"
            + "-11736896\n"
            + "-849053\n"
            + "-13892920\n"
            + "-11636514\n"
            + "3, 3", this.log.toString());

    this.features.save("KoalaCopySaveTest.jpg");

    this.features.load("KoalaCopySaveTest.jpg");

    assertEquals("-1017776\n"
            + "-11639974\n"
            + "-10793250\n"
            + "-9496744\n"
            + "-82853\n"
            + "-11736896\n"
            + "-849053\n"
            + "-13892920\n"
            + "-11636514\n"
            + "3, 3-2262088\n"
            + "-6275461\n"
            + "-14522239\n"
            + "-9894076\n"
            + "-156712\n"
            + "-6100737\n"
            + "-5557594\n"
            + "-9240466\n"
            + "-13212237\n"
            + "3, 3", this.log.toString());

    this.features.load("KoalaCopySaveTest.ppm");
    assertEquals("-1017776\n"
            + "-11639974\n"
            + "-10793250\n"
            + "-9496744\n"
            + "-82853\n"
            + "-11736896\n"
            + "-849053\n"
            + "-13892920\n"
            + "-11636514\n"
            + "3, 3-2262088\n"
            + "-6275461\n"
            + "-14522239\n"
            + "-9894076\n"
            + "-156712\n"
            + "-6100737\n"
            + "-5557594\n"
            + "-9240466\n"
            + "-13212237\n"
            + "3, 3", this.log.toString());

    this.features.save("KoalaCopySaveTest.ppm");

    this.features.load("KoalaCopySaveTest.ppm");

    assertEquals("-1017776\n"
            + "-11639974\n"
            + "-10793250\n"
            + "-9496744\n"
            + "-82853\n"
            + "-11736896\n"
            + "-849053\n"
            + "-13892920\n"
            + "-11636514\n"
            + "3, 3-2262088\n"
            + "-6275461\n"
            + "-14522239\n"
            + "-9894076\n"
            + "-156712\n"
            + "-6100737\n"
            + "-5557594\n"
            + "-9240466\n"
            + "-13212237\n"
            + "3, 3-2262088\n"
            + "-6275461\n"
            + "-14522239\n"
            + "-9894076\n"
            + "-156712\n"
            + "-6100737\n"
            + "-5557594\n"
            + "-9240466\n"
            + "-13212237\n"
            + "3, 3", this.log.toString());

    // Saving invalid file types should not create a new file
    this.features.save("KoalaCopySaveTest.jpeg");
    this.features.load("KoalaCopySaveTest.jpeg");
    assertEquals("-1017776\n"
            + "-11639974\n"
            + "-10793250\n"
            + "-9496744\n"
            + "-82853\n"
            + "-11736896\n"
            + "-849053\n"
            + "-13892920\n"
            + "-11636514\n"
            + "3, 3-2262088\n"
            + "-6275461\n"
            + "-14522239\n"
            + "-9894076\n"
            + "-156712\n"
            + "-6100737\n"
            + "-5557594\n"
            + "-9240466\n"
            + "-13212237\n"
            + "3, 3-2262088\n"
            + "-6275461\n"
            + "-14522239\n"
            + "-9894076\n"
            + "-156712\n"
            + "-6100737\n"
            + "-5557594\n"
            + "-9240466\n"
            + "-13212237\n"
            + "3, 3", this.log.toString());

    File f = new File("KoalaCopySaveTest.jpg");
    f.delete();

  }

}
