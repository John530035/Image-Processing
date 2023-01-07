package controller;

import java.io.IOException;
import view.ImageTextView;

/**
 * Mock Text View for testing.
 */
public class MockTextView implements ImageTextView {
  StringBuilder log;

  public MockTextView(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void renderMessage(String message) throws IOException {
    log.append("render ").append(message);
  }
}
