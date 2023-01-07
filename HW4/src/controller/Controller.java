package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Function;


import model.ImageModel;
import view.ImageTextView;

/**
 * An implementation of a {@code ImageController} object. Handles user inputs and responds
 * appropriately and facilitates communication between the model and view for processing images.
 */
public class Controller implements ImageController {

  private final Readable in;

  private final ImageModel model;

  private final ImageTextView view;

  /**
   * Constructs the object with the necessary values for it to operate. It takes in user input and
   * responds by modifying the model and view.
   *
   * @param in    the client's input
   * @param model the model/data that is being modified
   * @param view  what the client will see
   * @throws IllegalArgumentException if parameter null
   */
  public Controller(Readable in, ImageModel model, ImageTextView view)
          throws IllegalArgumentException {
    if (in == null) {
      throw new IllegalArgumentException("Invalid readable\n");
    }
    if (model == null) {
      throw new IllegalArgumentException("Invalid model\n");
    }
    if (view == null) {
      throw new IllegalArgumentException("Invalid view\n");
    }
    this.in = in;
    this.model = model;
    this.view = view;
  }

  @Override
  public void execute() throws IllegalStateException {
    Scanner sc = new Scanner(this.in);
    Map<String, Function<Scanner, Command>> commandMap = this.loadMap(sc);
    Boolean quit = false;

    this.welcomeMessage();
    while (!quit) {
      String input;
      try {
        input = sc.next();
      } catch (NoSuchElementException e) {
        throw new IllegalStateException("Something went wrong with the input!");
      }

      if (input.startsWith("#")) {
        sc.nextLine();
        continue;
      }

      switch (input.toLowerCase()) {
        case "i":
          this.displayGuide();
          break;
        case "q":
        case "quit":
          quit = true;
          this.quitProgram();
          break;
        default:
          Function<Scanner, Command> func = commandMap.getOrDefault(input, null);
          if (func != null) {
            try {
              Command cmd = func.apply(sc);
              cmd.execute(this.model);
            } catch (NoSuchElementException e) {
              writeMessage("Please input the operation parameters correctly\n");
            } catch (IllegalArgumentException e) {
              writeMessage(e.getMessage());
            }
          } else {
            writeMessage("Please input valid operation\n");
          }
      }
    }
  }

  private Map<String, Function<Scanner, Command>> loadMap(Scanner sc) {
    Map<String, Function<Scanner, Command>> commandMap
            = new HashMap<String, Function<Scanner, Command>>();
    commandMap.put("load", s -> new LoadCommand(sc.next(), sc.next()));
    commandMap.put("brighten", s -> new BrightenCommand(sc.nextInt(), sc.next(), sc.next()));
    commandMap.put("horizontal-flip", s -> new HorizontalFlipCommand(sc.next(), sc.next()));
    commandMap.put("vertical-flip", s -> new VerticalFlipCommand(sc.next(), sc.next()));
    commandMap.put("red-component", s -> new GreyScaleCommand("red", sc.next(), sc.next()));
    commandMap.put("green-component", s -> new GreyScaleCommand("green", sc.next(), sc.next()));
    commandMap.put("blue-component", s -> new GreyScaleCommand("blue", sc.next(), sc.next()));
    commandMap.put("value-component", s -> new GreyScaleCommand("value", sc.next(), sc.next()));
    commandMap.put("luma-component", s -> new GreyScaleCommand("luma", sc.next(), sc.next()));
    commandMap.put("intensity-component", s -> new GreyScaleCommand("intensity",
            sc.next(), sc.next()));
    commandMap.put("blur", s -> new FilterCommand(sc.next(), sc.next(), "blur"));
    commandMap.put("sharpen", s -> new FilterCommand(sc.next(), sc.next(), "sharpen"));
    commandMap.put("greyscale", s -> new ColorTransformationCommand(sc.next(), sc.next(),
            "greyscale"));
    commandMap.put("sepia", s -> new ColorTransformationCommand(sc.next(), sc.next(), "sepia"));
    commandMap.put("save", s -> new SaveCommand(sc.next(), sc.next()));
    return commandMap;
  }

  private void welcomeMessage() throws IllegalStateException {
    try {
      this.view.renderMessage("Welcome!\n");
      this.view.renderMessage("Enter \"i\" for guide\n");
    } catch (IOException e) {
      throw new IllegalStateException("Internal failure. Reenter input\n");
    }
  }

  private void displayGuide() throws IllegalStateException {
    try {
      this.view.renderMessage("Enter \"load file-path photo-reference\" to "
              + "load an image to the program. Use the reference to refer to that photo.\n");
      this.view.renderMessage("Enter \"brighten increment photo-reference "
              + "new-photo-reference\" to brighten the photo with the given reference. "
              + "Use the new reference to refer to the brightened photo.\n");
      this.view.renderMessage("Enter \"horizontal-flip photo-reference "
              + "new-photo-reference\" to flip the photo with the given reference" +
              " horizontally. Use the new reference to refer to the flipped photo.\n");
      this.view.renderMessage("Enter \"vertical-flip photo-reference "
              + "new-photo-reference\" to flip the photo with the given reference" +
              " vertically. Use the new reference to refer to the flipped photo.\n");
      this.view.renderMessage("Enter \"red-component-flip photo-reference "
              + "new-photo-reference\" to greyscale the photo with the given reference" +
              " with the red component. Use the new reference to refer to the greyscaled photo.\n");
      this.view.renderMessage("Enter \"green-component-flip photo-reference "
              + "new-photo-reference\" to greyscale the photo with the given reference" +
              " with the green component. Use the new reference to refer to the greyscaled " +
              "photo.\n");
      this.view.renderMessage("Enter \"blue-component-flip photo-reference "
              + "new-photo-reference\" to greyscale the photo with the given reference" +
              " with the blue component. Use the new reference to refer to the greyscaled " +
              "photo.\n");
      this.view.renderMessage("Enter \"value-component-flip photo-reference "
              + "new-photo-reference\" to greyscale the photo with the given reference" +
              " by using the value component. "
              + "Use the new reference to refer to the greyscaled photo.\n");
      this.view.renderMessage("Enter \"luma-component-flip photo-reference "
              + "new-photo-reference\" to greyscale the photo with the given reference" +
              " by using the luma component. "
              + "Use the new reference to refer to the greyscaled photo.\n");
      this.view.renderMessage("Enter \"intensity-component-flip photo-reference "
              + "new-photo-reference\" to greyscale the photo with the given reference" +
              " by using the intensity component. "
              + "Use the new reference to refer to the greyscaled photo.\n");
      this.view.renderMessage("Enter \"save file-path "
              + "photo-reference\" to save the photo with the given reference" +
              " to the given filepath.\n");
    } catch (IOException e) {
      throw new IllegalStateException("Internal failure. Renter input.\n");
    }

  }

  private void writeMessage(String message) throws IllegalStateException {
    try {
      view.renderMessage(message);

    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  private void quitProgram() throws IllegalStateException {
    try {
      this.view.renderMessage("Program Quit");
    } catch (IOException e) {
      throw new IllegalStateException("Internal failure. Renter input.\n");
    }
    return;
  }
}
