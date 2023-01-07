package controller;

/**
 * A controller for the client to perform image processing on digital image files. It handles
 * commands from the client, modifies the model / image, and displays a view.
 */
public interface ImageController {

  /**
   * Executes the program. Communicates with the model to perform operations with
   * the images and communicates with the view to output status updates.
   */
  void execute();
}
