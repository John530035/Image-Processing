package controller;

import model.ImageModel;

/**
 * Represents a valid command that the client can make in this program. Contains only one method,
 * which executes the functionality of the command.
 */
public interface Command {

  /**
   * Executes the behavior of the particular Command Implementation.
   *
   * @param model the model housing program functionality
   * @throws IllegalArgumentException if the model is null
   */
  void execute(ImageModel model) throws IllegalArgumentException;
}
