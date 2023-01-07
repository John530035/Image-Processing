package view;

import java.io.IOException;

/**
 * A view to output information about the status of the program to the client. The view
 * receives the information to output from implementations of the {@code ImageController} interface.
 * The information will be displayed to the client in text form.
 */
public interface ImageTextView {

  /**
   * Outputs the given message by appending it to the {@code ImageTextView} object's appendable
   * field.
   *
   * @param message the message to be outputted
   * @throws IOException if transmission to an appendable fails
   */
  void renderMessage(String message) throws IOException;
}
