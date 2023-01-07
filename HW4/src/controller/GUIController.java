package controller;


import model.ImageModel;
import view.ImageGUIView;

/**
 * An implementation of a {@code ImageController} object. Handles user inputs and responds
 * appropriately and facilitates communication between the model and view for processing images.
 * Displays a graphical interface for the user to interact with and use to operate the program.
 */
public class GUIController implements ImageController {

  private final ImageGUIView view;

  private final Features features;

  /**
   * Constructs an instance of an {@code GUIController} object.
   *
   * @param model the model housing program functionality
   * @param view the graphical view the user can interact with
   */
  public GUIController(ImageModel model, ImageGUIView view) {
    ImageModel modell = model;
    this.view = view;
    this.features = new FeaturesImpl(modell, this.view);
  }

  /**
   * Executes the program and displays the graphical user interface.
   */
  @Override
  public void execute() {
    this.view.addFeatures(this.features);
  }
}
