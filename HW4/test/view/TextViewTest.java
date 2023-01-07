package view;



import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests the TextView class.
 */
public class TextViewTest {

  ImageTextView textView1;
  ImageTextView textView2;
  StringBuilder out;

  @Before
  public void init() {
    this.out = new StringBuilder();
    this.textView1 = new TextView(this.out);
    this.textView2 = new TextView();
  }

  @Test
  public void testValidInitialization() {
    this.out = new StringBuilder();
    assertEquals("", this.out.toString());
    try {
      this.textView1 = new TextView(this.out);
    } catch (IllegalArgumentException e) {
      fail();
    }

    assertEquals("", this.out.toString());

    try {
      this.textView1 = new TextView();
    } catch (IllegalArgumentException e) {
      fail();
    }
  }

  @Test
  public void testInvalidInitialization() {
    try {
      this.textView1 = new TextView(null);
    } catch (IllegalArgumentException e) {
      if (!e.getMessage().equals("Values must not be null!")) {
        fail();
      }
    }
  }


  @Test
  public void testRenderMessage() {
    assertEquals("", this.out.toString());
    try {
      this.textView1.renderMessage("3");
    } catch (IOException e) {
      fail();
    }

    assertEquals("3", this.out.toString());

    this.out = new StringBuilder();
    this.textView1 = new TextView(this.out);
    try {
      this.textView1.renderMessage("");
    } catch (IOException e) {
      fail();
    }

    assertEquals("", this.out.toString());

    this.out = new StringBuilder();
    this.textView1 = new TextView(this.out);
    try {
      this.textView1.renderMessage("Fort\nnite");
    } catch (IOException e) {
      fail();
    }

    assertEquals("Fort\nnite", this.out.toString());

    this.out = new StringBuilder();
    this.textView1 = new TextView(this.out);
    try {
      this.textView1.renderMessage("I have a ");
    } catch (IOException e) {
      fail();
    }

    assertEquals("I have a ", this.out.toString());

    try {
      this.textView1.renderMessage("donut");
    } catch (IOException e) {
      fail();
    }

    assertEquals("I have a donut", this.out.toString());
  }

  @Test
  public void testInvalidRender() {
    Appendable corrupt = new CorruptAppendable();
    this.textView1 = new TextView(corrupt);

    try {
      this.textView1.renderMessage("3");
      fail();
    } catch (IOException e) {
      if (!e.getMessage().equals("Thrown IOException")) {
        fail();
      }
    }

    try {
      this.textView1.renderMessage("I have a");
      fail();
    } catch (IOException e) {
      if (!e.getMessage().equals("Thrown IOException")) {
        fail();
      }
    }

    try {
      this.textView1.renderMessage("");
      fail();
    } catch (IOException e) {
      if (!e.getMessage().equals("Thrown IOException")) {
        fail();
      }
    }
  }
}