package controller;

import model.ImageModel;
import model.ImageModelImpl;

import org.junit.Before;
import org.junit.Test;

import view.ImageGUIView;

import static org.junit.Assert.assertEquals;

/**
 * Tests the GUIController methods.
 */
public class GUIControllerTest {

  private GUIController guiController;
  private ImageModel model;
  private ImageGUIView mockView;

  private StringBuilder log;

  @Before
  public void init() {
    this.log = new StringBuilder();
    this.model = new ImageModelImpl();
    this.mockView = new MockGUIView1(this.log, this.log, this.log);
    this.guiController = new GUIController(this.model, this.mockView);
  }

  @Test
  public void testExecute() {
    this.guiController.execute();
    assertEquals("SUCCESSFUL ADDITION OF FEATURES", this.log.toString());
  }
}
