import controller.Controller;
import controller.GUIController;
import controller.ImageController;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Scanner;

import model.ImageModel;
import model.ImageModelImpl;
import view.GUIView;
import view.ImageGUIView;
import view.ImageTextView;
import view.TextView;

/**
 * The Main class.
 */
public class Main {

  /**
   * Main method that takes command line arguments.
   *
   * @param args command line arguments. Can be a script, blank to use the terminal, or the
   *             operations for the program.
   */

  public static void main(String[] args) {
    ImageModel model = new ImageModelImpl();
    ImageController controller = null;

    switch (args.length) {
      case 0:
        ImageGUIView view = new GUIView();
        controller = new GUIController(model, view);
        break;

      case 1:
        if (args[0].equals("-text")) {
          ImageTextView textView = new TextView();
          controller = new Controller(new InputStreamReader(System.in), model, textView);
        }
        else if (args[0].equals("-file")) {
          System.out.println("Please enter the file name!");
          return;
        }
        else {
          System.out.println("Unrecognized parameter!");
          return;
        }
        break;

      case 2:
        if (args[0].equals("-file")) {
          String fileContents = Main.fileInput(args[1]);
          if (fileContents.equals("")) {
            System.out.println("File " + args[1] + " not found");
            return;
          }

          ImageTextView textView = new TextView();
          controller = new Controller(new StringReader(fileContents), model, textView);
        }
        else {
          System.out.println("Enter a valid command!");
          return;
        }

        break;
      default:
        String argsContents = Main.argsContents(args);
        System.out.println(argsContents);
        ImageTextView textView = new TextView();
        controller = new Controller(new StringReader(argsContents), model, textView);
    }

    if (controller != null) {
      controller.execute();
    }
  }


  private static String argsContents(String[] args) {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < args.length; i ++) {
      builder.append((args[i]));
      if (i + 1 != args.length) {
        builder.append(" ");
      }
    }
    return builder.toString();
  }

  private static String fileInput(String fileName) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(fileName));
    } catch (FileNotFoundException e) {
      return "";
    }

    StringBuilder builder = new StringBuilder();
    while (sc.hasNextLine()) {
      String nextLine = sc.nextLine();
      if (nextLine.length() != 0) {
        builder.append(nextLine + System.lineSeparator());
      }
    }
    return builder.toString();
  }
}
