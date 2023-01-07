package view;

import java.io.IOException;

/**
 * Corrupt Appendable for testing.
 */
public class CorruptAppendable implements Appendable {
  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException("Thrown IOException");
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException("Thrown IOException");
  }

  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException("Thrown IOException");
  }
}
