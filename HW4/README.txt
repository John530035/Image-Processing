John Fahy and Kelvin Xu


Assignment 6: Image Processing (Part 3)

How to Use Program
There are three command line inputs that can be used to start the program.
- If the user chooses to enter "java -jar Program.jar -file path-of-script-file" then the program will execute
  whatever the user entered into te script that exists at the location specified by the user.
- If the user chooses to enter "java -jar Program.jar -text" then a terminal will open and the user can enter commands
  one line at a time and the program will execute those commands.
- If the user chooses to enter "java -jar Program.jar" then a graphical user interface will be displayed. The use can
  interact with this interface as detailed in the USEME.txt file to operate the program.
- Note: The correct jar file can be found at Image-Processing/HW4/res/HW6.jar


Completed Functionality
All components of the program are fully functional as specified in the assignment descriptions.
The main method, controller, model, and view all support all required functionality.


Design Changes

Main Method
- The design on the main method was adjusted to support the three ways the user can start the program as detailed above.

Controller
- There are now two implementations of the ImageController interface. The old controller remains to operate the program
  when the user enters a script or enters commands through the terminal. A new controller was implemented to operate
  the program when the user chooses to use the GUI. A new controller was added as the implementation of a controller
  handling script or user input ended up being quite different than the implementation handling GUI input. A new
  Features interface and implementation was adde to handle the functionality of the GUI and facilitate communication
  between the view and the model.

Model
- Neither the model's design nor functionality changed in this iteration of the image processing program.

View
- The design and functionality of the text view remained the same. A new interface and implementation of that interface
  were added to handle the graphical user interface. A new interface and implementation was added as the GUI view
  differed entirely from the Text view.


New Interfaces/Classes

Features
- Interface added to handle the functionality of the GUI that required communication between the view and the model.
  Allows the user to have access to all features of the program through the GUI.

FeaturesImpl
- An implementation of the Features interface.

ImageGUIView
- Interface representing the GUI of the program. Allows the user to operate the program through a GUI.

GUIView
- An implementation of the ImageGUIView.


Assignment 5: Image Processing (Part 2)

Completed Functionality
All components of the program are fully functional as specified in the assignment descriptions.
The main method, controller, model, and view all support all required functionality.


Design Changes

Main method: Neither the design nor the functionality of the main method changed. The main method
still allows the user to enter a script as a command line argument or to use the terminal to enter
commands to use this program.

Controller: The controller's design did not change but new functionality was added. The controller now
supports the new operations (filtering and color transformations). New implementations of the
Command interface were added to execute these new operations. Specifically, FilterCommand and
ColorTransformationCommand were implemented.

Model: The model's design changed slightly and new functionality was added. Methods for
filtering images and performing color transformations were added to the ImageModel interface and
then implemented in the ImageModelImpl model class. Supporting methods were then added to the
ImageFile interface. In assignment 4 ImageFileImpl was the only class implementing the ImageFile interface.
However, because this class was tailored to the requirements of operating on a .ppm file, the
CommonImageFile class was made to operate on more common image file types (.png, .jpg, .bpm). A single
class was not sufficient to support both .ppm files and more common files as the .ppm file
specifically requires a token to denote its type ("P3") and can have a different max RGB value depending
on the individual file. Because these features of the .ppm file are required to operate on the file, and
because it would not make sense for the other file types to have fields for information that is not required,
the CommonImageFile class was required to operate on the other image files types. Nevertheless, because much
functionality of the operations is similar between .ppm image types and the more common image types, the
AbstractImageFile class was made to house shared functionality. In this class much og the operations
are defined.

View: The design and functionality of the ImageTextView interface and the TextView class did not change.


New Interfaces/Classes

ColorTransformationCommand: An implementation of the Command interface. To initialize, it requires the alias
for the ImageFile object in the model that is being modified, a new alias for the modified image, and the mode of
color transformation (greyscale or sepia). When executed, it finds the ImageFile object based on
the alias given and performs the appropriate matrix operations on each pixel to create a new image.
A new version of that image is then loaded into the model with the new alias provided. To greyscale an image,
enter "greyscale image-reference new-image-reference". To sepia an image, enter "sepia image-reference
new-image-reference".

FilterCommand: An implementation of the Command interface. To initialize, it requires the alias
for the ImageFile object in the model that is being modified, a new alias for the modified image,
and the mode of filtering (blur or sharpen). When executed, it finds the ImageFile object based on the
alias given and performs the appropriate matrix operations on each pixel to create a new image.
A new version of that image is then loaded into the model with the new alias provided. To blur and image
enter "blur image-reference new-image-reference". To sharpen an image, enter "sharpen image-reference
new-image-reference".

AbstractImageFile: Abstract class that implements the ImageFile interface and houses functionality
that is shared by ImageFileImpl (class that is used for operating on .ppm files) and CommonImageFile
(class that is used for operating on .png, .jpg, .bmp files).

CommonImageFile: Extends AbstractImageFile and represents .png, .jpg, and .bmp files. Contains
the 2-D Pixel array representing the pixels in the image.




Assignment 4: Image Processing
We created an image processing program that currently only works with ASCII-PPM image files.
The program starts with a welcome message to greet the user. Next, the user has the option to
input a command, display what commands are available, or quit the program. To start processing
the image, the program requires the user to first load in the PPM file into the model, by using
the load command and providing a file path and alias for the file. Then, the user is able to modify
the image file by calling its alias and the commands available. They can save, brighten, greyscale,
horizontally flip, or vertically flip the image. The program continues to run until there is no longer
input from the user or the user exits. The user can stop the program with “q” or “quit” without worrying
about capitalization.


ImageModel
The ImageModel interface is the central hub for handling all image files and their different
versions. The client can get or manipulate images from classes implementing this interface with,
like mentioned in the starting paragraph.


ImageModelImpl
A specific implementation of the ImageModel interface that organizes all image files and their
versions by using a Map<String, ImageFile> object. An instance of this class can be initialized
publicly with no parameters. It also creates an empty private Map upon initialization for the
handling of the image versions.


ImageFile
The ImageFile interface represents a modifiable digital image file. Objects implementing this
interface can be grey scaled, horizontally flipped, vertically flipped, and brightened. They can
also return a String version of the Object, which can be turned into a file.


ImageFileImpl
An implementation of the ImageFile interface. This implementation works with the assigned image
file types for this assignment, which are PPM files. Therefore, these objects requires parameters
for the file’s token, image size, and max RGB value. It also uses a 2D Pixel (object) Array for
representing the actual image as pixels.


Pixel
A class used to represent a pixel on a computer screen. The client has to initialize a Pixel
object with its RGB values as integers. The client can access the RGB values of a Pixel object
as a String[], and they can also brighten/darken or grey scale the pixel.


ImageView
An ImageView interface is a way to visually display what is happening to the image file to the client. 


TextView
A TextView object is an implementation of the ImageView interface. This implementation can
render messages to the inputted output stream. It is used to send out messages in texts to
the clients  in this program.


ImageController
An ImageController interface is used to regulate what the client can do with the program.
It handles the inputs from the client, communicates with the model to perform operations with the
images, and communicates with the view to output status updates


Controller
An implementation of the ImageController interface. Welcomes the client and communicates the
client’s inputs to the model and view. It supports all necessary commands for image processing for assignment 4.


Command
The Command interface represents one available way that the client can interact with the program though text.


LoadCommand
An implementation of the Command interface. It takes in a file path to an image
file and its alias upon initialization. When executed, the command will create a ImageFile
object based on the image file, and load it into the given model with its alias. To use do
"save file-path photo-reference", where photo-reference is the alias for the image.


SaveCommand
An implementation of the Command interface. To initialize, it requires the output file path for
the file and the version of the ImageFile object in the model. When executed, it finds the
ImageFile object based on the alias given, converts it into a PPM file, and determines where
it will be placed on the client’s computer based on the given file path. Basically, it converts
a version in the model into a PPM file. To use, do "load file-path photo-reference", where
photo-reference is the alias for the image.


BrightenCommand
An implementation of the Command interface. To initialize, it requires an integer value of how
much to increment the RGB values of the image by, the alias for the ImageFile object in the model
that is being modified, and a new alias for the modified image. When executed, it finds the
ImageFile object based on the alias given, applies the integer value to the RGB values of every
pixel in the image, and creates a new alias/version of the image in the model. Basically, it
brightens or darkens the image version given. To use, do "brighten increment photo-reference
new-photo-reference", where photo-reference is the alias for the image.


GreyScaleCommand
An implementation of the Command interface. To initialize, it requires a String of what mode of
grey scale to apply to the image, the alias for the ImageFile object in the model that is being
modified, and a new alias for the modified image. The modes of greyscale supported are: red,
green, blue, intensity, value, and luma. When executed, it finds the ImageFile object based on
the alias given, applies the greyscale to the RGB values of every pixel in the image, and creates
a new alias/version of the image in the model. Basically, it applies a greyscale filter to the
image version given. To use, do "x-component increment photo-reference new-photo-reference", where
photo-reference is the alias for the image, and "x-component" is replaced with "red", "green",
"blue", "value", "luma", or "intensity" for "x".


HorizontalFlipCommand
An implementation of the Command interface. To initialize, it requires the alias for the ImageFile
object in the model that is being modified, and a new alias for the modified image. When executed,
it finds the ImageFile object based on the alias given, flips the image horizontally, and creates a
new alias/version of the image in the model. Basically, it flips horizontally the image version given.
To use, do "horizontal-flip photo-reference new-photo-reference", where photo-reference is the alias for the image.


VerticalFlipCommand
An implementation of the Command interface. To initialize, it requires the alias for the ImageFile
object in the model that is being modified, and a new alias for the modified image. When executed,
it finds the ImageFile object based on the alias given, flips the image vertically, and creates a
new alias/version of the image in the model. Basically, it flips horizontally the image vertically
given. To use, do "vertical-flip photo-reference new-photo-reference", where photo-reference is the
alias for the image.


Script
For the program to run through a script written by the client, the client simply has to enter the parameter "-file",
followed by file path to the script when referencing the JAR file. For the script itself, 
the commands and their parameters have to be separated by spaces or new lines. Any
text that does not want to be read have to be placed on a line with a "#" at the front. To end the
program without error, an input to quit the program is necessary in script. The script has to be a text file, as well.
NOTE: For the provided example script, please navigate to the directory the script is in before executing the JAR!


Credit for Photos
The photos used in this project are ours, and we authorize the use of it for this project.
