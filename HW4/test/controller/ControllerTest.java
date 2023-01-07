package controller;



import java.io.InputStreamReader;
import java.io.StringReader;
import model.ImageModel;
import model.ImageModelImpl;
import org.junit.Before;
import org.junit.Test;
import view.ImageTextView;
import view.TextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests the Controller.
 */
public class ControllerTest {
  ImageModel model;

  StringBuilder output;

  ImageTextView view;

  @Before
  public void init() {
    this.model = new ImageModelImpl();
    this.output = new StringBuilder();
    this.view = new TextView(output);
  }

  private StringBuilder[] setInteractions(Interaction[] interactions, ImageModel m,
                                          ImageTextView v) throws IllegalStateException {
    StringBuilder fakeUserInput = new StringBuilder();
    StringBuilder expectedOutput = new StringBuilder();

    for (Interaction interaction : interactions) {
      interaction.apply(fakeUserInput, expectedOutput);
    }

    StringReader input = new StringReader(fakeUserInput.toString());

    output = new StringBuilder();

    this.model = new ImageModelImpl();
    this.view = new TextView(output);

    Controller controller = new Controller(input, this.model, this.view);
    controller.execute();

    return new StringBuilder[]{expectedOutput, output};
  }

  @Test
  public void testInitialization() {
    StringReader input = new StringReader("");
    Readable rd = new InputStreamReader(System.in);
    try {
      new Controller(rd, model, view);
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      new Controller(input, model, view);
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      new Controller(null, model, view);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Invalid readable\n")) {
        fail();
      }
    }

    try {
      new Controller(null, null, view);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Invalid readable\n")) {
        fail();
      }
    }

    try {
      new Controller(null, null, null);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Invalid readable\n")) {
        fail();
      }
    }

    try {
      new Controller(null, model, null);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Invalid readable\n")) {
        fail();
      }
    }

    try {
      new Controller(input, null, view);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Invalid model\n")) {
        fail();
      }
    }

    try {
      new Controller(input, null, null);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Invalid model\n")) {
        fail();
      }
    }

    try {
      new Controller(input, model, null);
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Invalid view\n")) {
        fail();
      }
    }
  }

  @Test
  public void testQuit() {
    StringBuilder welcome = new StringBuilder()
        .append("Welcome!\n")
        .append("Enter \"i\" for guide\n");
    StringBuilder quitMsg = new StringBuilder()
        .append("Program Quit");

    Interaction[] interactions = {
        inputs("q"),
        prints(welcome.toString()),
        prints(quitMsg.toString())
    };

    StringBuilder[] outputs = this.setInteractions(interactions, model, view);
    assertEquals(outputs[0].toString(), outputs[1].toString());

    interactions = new Interaction[]{
        inputs("quit"),
        prints(welcome.toString()),
        prints(quitMsg.toString())
    };

    outputs = this.setInteractions(interactions, model, view);
    assertEquals(outputs[0].toString(), outputs[1].toString());

    interactions = new Interaction[]{
        inputs("QUIT"),
        prints(welcome.toString()),
        prints(quitMsg.toString())
    };

    outputs = this.setInteractions(interactions, model, view);
    assertEquals(outputs[0].toString(), outputs[1].toString());

    interactions = new Interaction[]{
        inputs("Q"),
        prints(welcome.toString()),
        prints(quitMsg.toString())
    };

    outputs = this.setInteractions(interactions, model, view);
    assertEquals(outputs[0].toString(), outputs[1].toString());

    interactions = new Interaction[]{
        inputs("random q"),
        prints(welcome.toString()),
        prints("Please input valid operation\n"),
        prints(quitMsg.toString())
    };
    outputs = this.setInteractions(interactions, model, view);
    assertEquals(outputs[0].toString(), outputs[1].toString());

    interactions = new Interaction[]{
        inputs("brighten quit"),
        prints(welcome.toString()),
        prints("Please input the operation parameters correctly\n"),
        prints(quitMsg.toString())
    };

    outputs = this.setInteractions(interactions, model, view);
    assertEquals(outputs[0].toString(), outputs[1].toString());

    interactions = new Interaction[]{
        inputs("load quit quit quit"),
        prints(welcome.toString()),
        prints("Invalid file type!\n"),
        prints(quitMsg.toString())
    };

    outputs = this.setInteractions(interactions, model, view);
    assertEquals(outputs[1].toString(), outputs[1].toString());

    Command loadCMD = new LoadCommand("KoalaCopy.ppm", "koala");
    loadCMD.execute(model);
    interactions = new Interaction[]{
        inputs("load KoalaCopy.ppm koala save C:\\NoExist\\ koala quit"),
        prints(welcome.toString()),
        prints("Invalid file path!\n"),
        prints(quitMsg.toString())
    };

    outputs = this.setInteractions(interactions, model, view);
    assertEquals(outputs[0].toString(), outputs[1].toString());

  }

  @Test
  public void testWaitForValidCommand() {
    StringBuilder welcome = new StringBuilder()
        .append("Welcome!\n")
        .append("Enter \"i\" for guide\n");
    StringBuilder quitMsg = new StringBuilder()
        .append("Program Quit");

    Interaction[] interactions = {
        inputs("wait "),
        prints(welcome.toString()),
        prints("Please input valid operation\n"),
        inputs("what are the commands again quit"),
        prints("Please input valid operation\n"),
        prints("Please input valid operation\n"),
        prints("Please input valid operation\n"),
        prints("Please input valid operation\n"),
        prints("Please input valid operation\n"),
        prints(quitMsg.toString())
    };

    StringBuilder[] outputs = this.setInteractions(interactions, model, view);
    assertEquals(outputs[0].toString(), outputs[1].toString());

    interactions = new Interaction[]{
        inputs("brighten 12 now later "),
        prints(welcome.toString()),
        prints("Version not found!\n"),
        inputs("vertical-flip flip unflip q"),
        prints("Version not found!\n"),
        prints(quitMsg.toString())
    };

    outputs = this.setInteractions(interactions, model, view);
    assertEquals(outputs[0].toString(), outputs[1].toString());
  }

  @Test
  public void testInvalidInput() {
    StringBuilder welcome = new StringBuilder()
        .append("Welcome!\n")
        .append("Enter \"i\" for guide\n");
    StringBuilder quitMsg = new StringBuilder()
        .append("Program Quit");

    Interaction[] interactions = {
        inputs("fjasflkdj"),
        prints(welcome.toString()),
        prints(quitMsg.toString())
    };

    try {
      this.setInteractions(interactions, model, view);
      fail();
    } catch (IllegalStateException e) {
      if (!e.getMessage().equals("Something went wrong with the input!")) {
        fail();
      }
    }

    interactions = new Interaction[]{
        inputs("load KoalaCopy.ppm koala"),
        prints(welcome.toString()),
        prints(quitMsg.toString())
    };

    try {
      this.setInteractions(interactions, model, view);
      fail();
    } catch (IllegalStateException e) {
      if (!e.getMessage().equals("Something went wrong with the input!")) {
        fail();
      }
    }
  }

  @Test
  public void testInfoMessage() {
    StringBuilder welcome = new StringBuilder()
        .append("Welcome!\n")
        .append("Enter \"i\" for guide\n");
    StringBuilder quitMsg = new StringBuilder()
        .append("Program Quit");
    StringBuilder infoMsg = new StringBuilder()
        .append("Welcome!\n"
            + "Enter \"i\" for guide\n"
            + "Enter \"load file-path photo-reference\" to load an image to the program." +
                " Use the reference to refer to that photo.\n"
            + "Enter \"brighten increment photo-reference new-photo-reference\" to brighten" +
                " the photo with the given reference. Use the new reference to refer to the " +
                "brightened photo.\n"
            + "Enter \"horizontal-flip photo-reference new-photo-reference\" to flip the photo" +
                " with the given reference horizontally. Use the new reference to refer to the" +
                " flipped photo.\n"
            + "Enter \"vertical-flip photo-reference new-photo-reference\" to flip the photo " +
                "with the given reference vertically. Use the new reference to refer to the f" +
                "lipped photo.\n"
            + "Enter \"red-component photo-reference new-photo-reference\" to greyscale the " +
                "photo with the given reference with the red component. Use the new reference " +
                "to refer to the greyscaled photo.\n"
            + "Enter \"green-component photo-reference new-photo-reference\" to greyscale the " +
                "photo with the given reference with the green component. Use the new reference " +
                "to refer to the greyscaled photo.\n"
            + "Enter \"blue-component photo-reference new-photo-reference\" to greyscale the " +
                "photo with the given reference with the blue component. Use the new reference " +
                "to refer to the greyscaled photo.\n"
            + "Enter \"value-component photo-reference new-photo-reference\" to greyscale the " +
                "photo with the given reference by using the value component. Use the new reference " +
                "to refer to the greyscaled photo.\n"
            + "Enter \"luma-component photo-reference new-photo-reference\" to greyscale the p" +
                "hoto with the given reference by using the luma component. Use the new " +
                "reference to refer to the greyscaled photo.\n"
            + "Enter \"intensity-component photo-reference new-photo-reference\" to " +
                "greyscale the photo with the given reference by using the intensity component. " +
                "Use the new reference to refer to the greyscaled photo.\n"
            + "Enter \"blur photo-reference new-photo-reference\" to blur the photo with the " +
                "given referenceUse the new reference to refer to the blurred photo.\n"
            + "Enter \"sharpen photo-reference new-photo-reference\" to sharpen the photo with " +
                "the given referenceUse the new reference to refer to the sharpened photo.\n"
            + "Enter \"sepia photo-reference new-photo-reference\" to use the sepia filter on " +
                "the photo with the given referenceUse the new reference to refer to the photo " +
                "with the sepia filter.\n"
            + "Enter \"greyscale photo-reference new-photo-reference\" to greyscale the photo" +
                " with luma with the given referenceUse the new reference to refer to the " +
                "greyscaled photo.\n"
            + "Enter \"save file-path photo-reference\" to save the photo with the given reference" +
                " to the given filepath.\n"
            + "Program Quit");

    Interaction[] interactions = {
        inputs("i quit"),
        prints(infoMsg.toString()),
    };

    StringBuilder[] outputs = this.setInteractions(interactions, model, view);
    assertEquals(outputs[0].toString(), outputs[1].toString());
  }

  // full command tests in CommandTest class
  @Test
  public void testCommands() {

    try {
      this.model.findFile("koala");
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Version not found!\n")) {
        fail();
      }
    }

    try {
      this.model.findFile("koala-vertical");
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Version not found!\n")) {
        fail();
      }
    }

    try {
      this.model.findFile("koala-darken");
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Version not found!\n")) {
        fail();
      }
    }

    try {
      this.model.findFile("koala-horizontal");
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Version not found!\n")) {
        fail();
      }
    }

    try {
      this.model.findFile("koala-brighten");
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Version not found!\n")) {
        fail();
      }
    }

    try {
      this.model.findFile("koala-new-grey");
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Version not found!\n")) {
        fail();
      }
    }

    try {
      this.model.findFile("koala-sepia");
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Version not found!\n")) {
        fail();
      }
    }

    try {
      this.model.findFile("koala-blur");
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Version not found!\n")) {
        fail();
      }
    }

    try {
      this.model.findFile("koala-sharpen");
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Version not found!\n")) {
        fail();
      }
    }

    try {
      this.model.findFile("koala-grey1");
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Version not found!\n")) {
        fail();
      }
    }

    try {
      this.model.findFile("koala-grey2");
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Version not found!\n")) {
        fail();
      }
    }

    try {
      this.model.findFile("koala-grey3");
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Version not found!\n")) {
        fail();
      }
    }

    try {
      this.model.findFile("koala-grey4");
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Version not found!\n")) {
        fail();
      }
    }

    try {
      this.model.findFile("koala-grey5");
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Version not found!\n")) {
        fail();
      }
    }

    try {
      this.model.findFile("koala-grey6");
      fail();
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Version not found!\n")) {
        fail();
      }
    }


    StringBuilder welcome = new StringBuilder()
        .append("Welcome!\n")
        .append("Enter \"i\" for guide\n");
    StringBuilder quitMsg = new StringBuilder()
        .append("Program Quit");

    Interaction[] interactions = {
        inputs("load KoalaCopy.ppm koala "),
        inputs("save KoalaSaveTest.ppm koala "),
        inputs("vertical-flip koala koala-vertical "),
        inputs("horizontal-flip koala koala-horizontal "),
        inputs("brighten 100 koala koala-brighten "),
        inputs("brighten -100 koala koala-darken "),
        inputs("red-component koala koala-grey1 "),
        inputs("green-component koala koala-grey2 "),
        inputs("blue-component koala koala-grey3 "),
        inputs("value-component koala koala-grey4 "),
        inputs("luma-component koala koala-grey5 "),
        inputs("intensity-component koala koala-grey6 "),
        inputs("greyscale koala koala-new-grey "),
        inputs("sepia koala koala-sepia "),
        inputs("blur koala koala-blur "),
        inputs("sharpen koala koala-sharpen q"),
        prints(welcome.toString()),
        prints(quitMsg.toString())
    };

    StringBuilder[] outputs = this.setInteractions(interactions, model, view);
    assertEquals(outputs[0].toString(), outputs[1].toString());

    try {
      this.model.findFile("koala");
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      this.model.findFile("koala-vertical");
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      this.model.findFile("koala-darken");
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      this.model.findFile("koala-horizontal");
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      this.model.findFile("koala-brighten");
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      this.model.findFile("koala-new-grey");
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      this.model.findFile("koala-sepia");
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      this.model.findFile("koala-blur");
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      this.model.findFile("koala-sharpen");
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      this.model.findFile("koala-grey1");
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      this.model.findFile("koala-grey2");
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      this.model.findFile("koala-grey3");
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      this.model.findFile("koala-grey4");
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      this.model.findFile("koala-grey5");
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      this.model.findFile("koala-grey6");
    } catch (IllegalArgumentException e) {
      fail();
    }
  }

  // Mocks test for model and view
  @Test
  public void testModelInputs() {
    StringBuilder welcome = new StringBuilder()
        .append("Welcome!\n")
        .append("Enter \"i\" for guide\n");
    StringBuilder quitMsg = new StringBuilder()
        .append("Program Quit");
    StringBuilder msg = new StringBuilder()
        .append("load koala\n")
        .append("find koala\n")
        .append("vertical-flip koala\n")
        .append("load koala-vertical\n")
        .append("horizontal-flip koala\n")
        .append("load koala-horizontal\n")
        .append("brighten koala increment: 100\n")
        .append("load koala-brighten\n")
        .append("brighten koala increment: -100\n")
        .append("load koala-darken\n")
        .append("grey koala mode: red\n")
        .append("load koala-grey1\n")
        .append("grey koala mode: green\n")
        .append("load koala-grey2\n")
        .append("grey koala mode: blue\n")
        .append("load koala-grey3\n")
        .append("grey koala mode: value\n")
        .append("load koala-grey4\n")
        .append("grey koala mode: luma\n")
        .append("load koala-grey5\n")
        .append("grey koala mode: intensity\n")
        .append("load koala-grey6\n");

    StringBuilder log = new StringBuilder();
    this.model = new MockImageModel(log);

    Interaction[] interactions = {
        inputs("load KoalaCopy.ppm koala "),
        inputs("save KoalaSaveTest.ppm koala "),
        inputs("vertical-flip koala koala-vertical "),
        inputs("horizontal-flip koala koala-horizontal "),
        inputs("brighten 100 koala koala-brighten "),
        inputs("brighten -100 koala koala-darken "),
        inputs("red-component koala koala-grey1 "),
        inputs("green-component koala koala-grey2 "),
        inputs("blue-component koala koala-grey3 "),
        inputs("value-component koala koala-grey4 "),
        inputs("luma-component koala koala-grey5 "),
        inputs("intensity-component koala koala-grey6 "),
        inputs("greyscale koala koala-new-grey "),
        inputs("sepia koala koala-sepia "),
        inputs("blur koala koala-blur "),
        inputs("sharpen koala koala-sharpen q"),
        prints(msg.toString()),
    };

    StringBuilder fakeUserInput = new StringBuilder();
    StringBuilder expectedOutput = new StringBuilder();

    for (Interaction interaction : interactions) {
      interaction.apply(fakeUserInput, expectedOutput);
    }

    StringReader input = new StringReader(fakeUserInput.toString());

    output = new StringBuilder();

    Controller controller = new Controller(input, this.model, this.view);
    controller.execute();

    assertEquals(expectedOutput.toString(), log.toString());
  }

  @Test
  public void testViewInputs() {
    StringBuilder msg = new StringBuilder()
        .append("render Welcome!\n")
        .append("render Enter \"i\" for guide\n")
        .append("render Program Quit");

    StringBuilder log = new StringBuilder();
    this.view = new MockTextView(log);

    Interaction[] interactions = {
        inputs("quit"),
        prints(msg.toString()),
    };

    StringBuilder fakeUserInput = new StringBuilder();
    StringBuilder expectedOutput = new StringBuilder();

    for (Interaction interaction : interactions) {
      interaction.apply(fakeUserInput, expectedOutput);
    }

    StringReader input = new StringReader(fakeUserInput.toString());

    output = new StringBuilder();

    Controller controller = new Controller(input, this.model, this.view);
    controller.execute();

    assertEquals(expectedOutput.toString(), log.toString());
  }

  static Interaction prints(String... lines) {
    return (input, output) -> {
      for (String line : lines) {
        output.append(line);
      }
    };
  }

  static Interaction inputs(String in) {
    return (input, output) -> {
      input.append(in + "\n");
    };
  }


}