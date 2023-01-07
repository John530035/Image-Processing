package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.Features;

/**
 * An implementation of the {@code ImageGUIView} interface. Displays program
 * functionality to the user in an accessible way.
 */
public class GUIView extends JFrame implements ImageGUIView {

  private JButton loadButton;

  private JButton brightenButton;

  private JButton verticalFlipButton;

  private JButton horizontalFlipButton;

  private JButton blurButton;

  private JButton sharpenButton;

  private JButton sepiaButton;

  private JButton greyScaleButton;

  private JButton componentVisualizationButton;

  private JButton saveButton;

  private JButton redButton;

  private JButton greenButton;

  private JButton blueButton;

  private JButton lumaButton;

  private JButton valueButton;

  private JButton intensityButton;

  private JButton cancelVisualizationButton;

  private JPanel componentVisualizationButtonPanel;

  private JPanel fileLoadPanel;

  private JButton fileLoadButton;

  private JLabel fileLoadDisplay;

  private JPanel fileSavePanel;

  private JButton fileSaveButton;

  private JLabel fileSaveDisplay;

  private JPanel brightenPanel;

  private JButton brightenButton1;

  private JTextField brightenInput;

  private JButton cancelBrighten;

  private JLabel imageLabel;

  private JPanel histogramPanel;

  private JPanel imageInfoPanel;

  private ViewListener viewListener;


  /**
   * Constructs an instance of an {@code GUIView} object.
   */
  public GUIView() {
    super();
    this.setTitle("Image Processing");
    this.setLayout(new BorderLayout());

    //operation buttons
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(10, 1));

    this.loadButton = new JButton("Load Image");
    this.loadButton.setActionCommand("load menu");
    buttonPanel.add(this.loadButton);

    this.brightenButton = new JButton("Brighten");
    this.brightenButton.setActionCommand("brighten menu");
    buttonPanel.add(this.brightenButton);

    this.verticalFlipButton = new JButton("Vertical Flip");
    this.verticalFlipButton.setActionCommand("vertical flip");
    buttonPanel.add(this.verticalFlipButton);

    this.horizontalFlipButton = new JButton("Horizontal Flip");
    this.horizontalFlipButton.setActionCommand("horizontal flip");
    buttonPanel.add(this.horizontalFlipButton);

    this.blurButton = new JButton("Blur");
    this.blurButton.setActionCommand("blur");
    buttonPanel.add(this.blurButton);

    this.sharpenButton = new JButton("Sharpen");
    this.sharpenButton.setActionCommand("sharpen");
    buttonPanel.add(this.sharpenButton);

    this.sepiaButton = new JButton("Sepia");
    this.sepiaButton.setActionCommand("sepia");
    buttonPanel.add(this.sepiaButton);

    this.greyScaleButton = new JButton("Greyscale");
    this.greyScaleButton.setActionCommand("greyscale");
    buttonPanel.add(this.greyScaleButton);

    this.componentVisualizationButton = new JButton("Component Visualization");
    this.componentVisualizationButton.setActionCommand("component visualization menu");
    buttonPanel.add(this.componentVisualizationButton);

    this.saveButton = new JButton("Save");
    this.saveButton.setActionCommand("save menu");
    buttonPanel.add(this.saveButton);

    this.add(buttonPanel, BorderLayout.WEST);

    //component visualization buttons
    this.componentVisualizationButtonPanel = new JPanel();
    this.componentVisualizationButtonPanel.setLayout(new GridLayout(7, 1));
    this.componentVisualizationButtonPanel.setVisible(false);

    this.redButton = new JButton("Visualize Red Component");
    this.redButton.setActionCommand("component chosen");
    this.componentVisualizationButtonPanel.add(this.redButton);

    this.greenButton = new JButton("Visualize Green Component");
    this.greenButton.setActionCommand("component chosen");
    this.componentVisualizationButtonPanel.add(this.greenButton);

    this.blueButton = new JButton("Visualize Blue Component");
    this.blueButton.setActionCommand("component chosen");
    this.componentVisualizationButtonPanel.add(this.blueButton);

    this.lumaButton = new JButton("Visualize Luma Component");
    this.lumaButton.setActionCommand("component chosen");
    this.componentVisualizationButtonPanel.add(this.lumaButton);

    this.valueButton = new JButton("Visualize Value Component");
    this.valueButton.setActionCommand("component chosen");
    this.componentVisualizationButtonPanel.add(this.valueButton);

    this.intensityButton = new JButton("Visualize Intensity Component");
    this.intensityButton.setActionCommand("component chosen");
    this.componentVisualizationButtonPanel.add(this.intensityButton);

    this.cancelVisualizationButton = new JButton("Cancel");
    this.cancelVisualizationButton.setActionCommand("cancel visualization");
    this.componentVisualizationButtonPanel.add(this.cancelVisualizationButton);

    //file open panel
    this.fileLoadPanel = new JPanel();
    this.fileLoadPanel.setLayout(new FlowLayout());
    this.fileLoadPanel.setVisible(false);
    this.fileLoadButton = new JButton("Load a File");
    this.fileLoadButton.setActionCommand("load file");
    this.fileLoadPanel.add(this.fileLoadButton);
    this.fileLoadDisplay = new JLabel("File path will appear here");
    this.fileLoadPanel.add(this.fileLoadDisplay);

    //file save panel
    this.fileSavePanel = new JPanel();
    this.fileSavePanel.setLayout(new FlowLayout());
    this.fileSavePanel.setVisible(false);
    this.fileSaveButton = new JButton("Save Image to File");
    this.fileSaveButton.setActionCommand("save to file");
    this.fileSavePanel.add(this.fileSaveButton);
    this.fileSaveDisplay = new JLabel("File path will appear here");
    this.fileSavePanel.add(this.fileSaveDisplay);

    //brighten panel
    this.brightenPanel = new JPanel();
    this.brightenPanel.setLayout(new FlowLayout());
    this.brightenPanel.setVisible(false);
    this.brightenButton1 = new JButton("Brighten Image");
    this.brightenButton1.setActionCommand("brighten image");
    this.brightenPanel.add(this.brightenButton1);
    this.brightenInput = new JTextField(5);
    this.brightenPanel.add(this.brightenInput);
    this.cancelBrighten = new JButton("Cancel");
    this.cancelBrighten.setActionCommand("cancel brighten");
    this.brightenPanel.add(this.cancelBrighten);

    //options panel
    JPanel optionsPanel = new JPanel();
    optionsPanel.setLayout(new GridLayout(4, 1));
    optionsPanel.setVisible(true);
    optionsPanel.add(this.fileLoadPanel);
    optionsPanel.add(this.componentVisualizationButtonPanel);
    optionsPanel.add(this.brightenPanel);
    optionsPanel.add(this.fileSavePanel);
    this.add(optionsPanel, BorderLayout.EAST);

    //histogram panel
    this.histogramPanel = new JPanel();
    this.histogramPanel.setLayout(new GridLayout(1,256));
    this.histogramPanel.setPreferredSize(new Dimension(700, 100));

    //image panel
    this.imageLabel = new JLabel("", JLabel.CENTER);
    JScrollPane imagePanel = new JScrollPane(this.imageLabel);
    imagePanel.setPreferredSize(new Dimension(700,600));

    //image info panel
    this.imageInfoPanel = new JPanel();
    this.imageInfoPanel.setLayout(new BorderLayout());
    this.imageInfoPanel.setPreferredSize(new Dimension(700, 700));
    this.imageInfoPanel.add(imagePanel, BorderLayout.CENTER);
    this.imageInfoPanel.add(this.histogramPanel, BorderLayout.SOUTH);
    this.add(this.imageInfoPanel, BorderLayout.CENTER);

    //max window
    this.setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH);

    this.viewListener = new ViewListener();

    //pack, visible
    pack();
    this.setVisible(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  @Override
  public void addFeatures(Features f) {
    this.loadButton.addActionListener(evt -> this.viewListener.actionPerformed(evt));
    this.fileLoadButton.addActionListener(evt -> f.load(this.fileLoadDisplay.getText()));
    this.fileLoadButton.addActionListener(evt -> this.viewListener.actionPerformed(evt));

    this.brightenButton.addActionListener(evt -> this.viewListener.actionPerformed(evt));
    this.brightenButton1.addActionListener(evt -> f.brighten(this.brightenInput.getText()));
    this.brightenButton1.addActionListener(evt -> this.viewListener.actionPerformed(evt));
    this.cancelBrighten.addActionListener(evt -> this.viewListener.actionPerformed(evt));

    this.verticalFlipButton.addActionListener(evt -> f.verticalFlip());
    this.horizontalFlipButton.addActionListener(evt -> f.horizontalFlip());
    this.blurButton.addActionListener(evt -> f.blur());
    this.sharpenButton.addActionListener(evt -> f.sharpen());

    this.sepiaButton.addActionListener(evt -> f.sepia());
    this.greyScaleButton.addActionListener(evt -> f.greyscale());

    this.verticalFlipButton.addActionListener(evt -> this.viewListener.actionPerformed(evt));
    this.horizontalFlipButton.addActionListener(evt -> this.viewListener.actionPerformed(evt));
    this.blurButton.addActionListener(evt -> this.viewListener.actionPerformed(evt));
    this.sharpenButton.addActionListener(evt -> this.viewListener.actionPerformed(evt));
    this.sepiaButton.addActionListener(evt -> this.viewListener.actionPerformed(evt));
    this.greyScaleButton.addActionListener(evt -> this.viewListener.actionPerformed(evt));


    this.componentVisualizationButton.addActionListener(
        evt -> this.viewListener.actionPerformed(evt));
    this.redButton.addActionListener(evt -> f.componentVisualization("red"));
    this.redButton.addActionListener(evt -> this.viewListener.actionPerformed(evt));
    this.greenButton.addActionListener(evt -> f.componentVisualization("green"));
    this.greenButton.addActionListener(evt -> this.viewListener.actionPerformed(evt));
    this.blueButton.addActionListener(evt -> f.componentVisualization("blue"));
    this.blueButton.addActionListener(evt -> this.viewListener.actionPerformed(evt));
    this.lumaButton.addActionListener(evt -> f.componentVisualization("luma"));
    this.lumaButton.addActionListener(evt -> this.viewListener.actionPerformed(evt));
    this.intensityButton.addActionListener(evt -> f.componentVisualization("intensity"));
    this.intensityButton.addActionListener(evt -> this.viewListener.actionPerformed(evt));
    this.valueButton.addActionListener(evt -> f.componentVisualization("value"));
    this.valueButton.addActionListener(evt -> this.viewListener.actionPerformed(evt));
    this.cancelVisualizationButton.addActionListener(
        evt -> this.viewListener.actionPerformed(evt));

    this.saveButton.addActionListener(evt -> this.viewListener.actionPerformed(evt));
    this.fileSaveButton.addActionListener(evt -> f.save(this.fileSaveDisplay.getText()));
    this.fileSaveButton.addActionListener(evt -> this.viewListener.actionPerformed(evt));
  }

  @Override
  public void displayImage(BufferedImage bImage) {
    ImageIcon icon = new ImageIcon(bImage);
    this.imageLabel.setIcon(icon);
    this.repaint();
  }

  @Override
  public void displayHistogram(Histogram histo) {
    Component[] components = this.histogramPanel.getComponents();
    for (Component c : components) {
      this.histogramPanel.remove(c);
    }
    this.imageInfoPanel.remove(this.histogramPanel);
    this.histogramPanel.add(histo);
    this.imageInfoPanel.add(this.histogramPanel, BorderLayout.SOUTH);

  }

  //action listener class to handle events that are contained within the view and
  //therefore do not need to be handled
  //by the controller
  private class ViewListener implements ActionListener {

    private void clearPanel() {
      componentVisualizationButtonPanel.setVisible(false);
      brightenPanel.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      switch (e.getActionCommand()) {
        case "component visualization menu":
          clearPanel();
          componentVisualizationButtonPanel.setVisible(true);
          break;
        case "component chosen":
          componentVisualizationButtonPanel.setVisible(false);
          break;
        case "cancel visualization":
          componentVisualizationButtonPanel.setVisible(false);
          break;
        case "load menu":
          clearPanel();
          fileLoadPanel.setVisible(true);
          final JFileChooser fileChooser = new JFileChooser(".");
          FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG, PNG, BMP, PPM images",
              "jpg", "png", "bmp", "ppm");
          fileChooser.setFileFilter(filter);
          int retValue = fileChooser.showOpenDialog(null);
          if (retValue == JFileChooser.APPROVE_OPTION) {
            File f = fileChooser.getSelectedFile();
            fileLoadDisplay.setText(f.getAbsolutePath());
          } else {
            fileLoadPanel.setVisible(false);
          }
          break;
        case "load file":
          fileLoadPanel.setVisible(false);
          break;
        case "save menu":
          clearPanel();
          fileSavePanel.setVisible(true);
          final JFileChooser fileChooser1 = new JFileChooser(".");
          int retvalue = fileChooser1.showSaveDialog(null);
          if (retvalue == JFileChooser.APPROVE_OPTION) {
            File f = fileChooser1.getSelectedFile();
            fileSaveDisplay.setText(f.getAbsolutePath());
          } else {
            fileSavePanel.setVisible(false);
          }
          break;
        case "save to file":
          fileSavePanel.setVisible(false);
          break;
        case "brighten menu":
          clearPanel();
          brightenPanel.setVisible(true);
          break;
        case "brighten image":
          brightenPanel.setVisible(false);
          break;
        case "cancel brighten":
          brightenPanel.setVisible(false);
          break;
        case "horizontal flip":
        case "vertical flip":
        case "blur":
        case "sharpen":
        case "sepia":
        case "greyscale":
          clearPanel();
          brightenPanel.setVisible(true);
          brightenPanel.setVisible(false);
          break;
        default:
          throw new IllegalArgumentException("Illegal action committed!\n");
      }
    }
  }

  @Override
  public void renderErrorMessag(String msg) {
    JOptionPane.showMessageDialog(this, msg);
  }
}
