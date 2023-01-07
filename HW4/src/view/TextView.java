package view;

import java.io.IOException;

/**
 * An implementation of the {@code ImageTextView} interface. Receives information to output from
 * implementations of the {@code ImageController} interface. Contains an Appendable object to append
 * output to. The output os displayed to the user in text format.
 */
public class TextView implements ImageTextView {

  private Appendable out;

  /**
   * Constructs an instance of an {@code TextView}.
   *
   * @param out the appendable through which text will be outputted
   * @throws IllegalArgumentException if the given appendable is null
   */
  public TextView(Appendable out) throws IllegalArgumentException {
    if (out == null) {
      throw new IllegalArgumentException("Values must not be null!");
    }

    this.out = out;
  }

  /**
   * Outputs information through System.out.
   */
  public TextView() {
    this(System.out);
  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.out.append(message);
  }
}
