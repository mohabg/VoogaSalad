#The DuBallers#

##Introduction##
This section describes the problem your team is trying to solve by writing this
program, the primary design goals of the project (i.e., where is it most flexible), and the primary 
architecture of the design (i.e., what is closed and what is open). Describe your chosen game genre
and what qualities make it unique that your design will need to support. Discuss the design at a
high-level (i.e., without referencing specific classes, data structures, or code).The goal of this program is to design an authoring environment in which users can design a customized "game." Although our current set genre for game production is "top-down shooters," our overall goal is to expand beyond that: we are aiming to design a program which lets the user create a custom game regardless of genre. As a result, our design for this project avoids the developing the program in such a way that rules about the game/sprites are already decided in order to align with the customs of the genre.

The authoring environment allows the user to design the game; users can drag sprites on to a templating window in order to create the map. As discussed later, sprites encapsulate everything from the player's character(s)/enemy sprites to projectiles/obstacles. After the user has dragged the relevant sprites onto the screen, they are able to choose various attributes for the sprites (health, attack, defense, movement etc..) In addition, the user chooses the objective of the game (time/score/enemy based). As a result, our authoring environment allows complete flexibility for the user to break out of the top-down shooter paradigm. At any point in the game, the user ought to be able to pause the game and in real-time, edit the authoring environment in order to allow for flexbility mid-game.

Once the user has completed their design for the game in the authoring environment, all of the data is converted into a Game Data file through using XStream. This Game Data File is fed into the game-engine in order to allow for the game's algorithmic functionality; the game-engine is the back-end behemoth of the game which accounts for the multitude of objects which encapsulate the rules of the game. This data must be transferred between the game-engine and the authoring environment in real time in order to allow for the user to be able to edit the authoring environment at any time. The game-player loads data-files into the engine in order to display to the user a loaded game.

The game-engine was designed to be as flexible as possbile in order to accomodate the goals layed out at the beginning of the design-document. Rather than having classes which were specific to the functionality of a top-down shooter, our classes are generally abstracted out to allow for fluidity in functionality (based on the user's design). As a result, the back-end controls the "rules" of the game; it updates sprites' positions when the user moves it forward, it keeps track of the health/attack/defense of various sprites, it defines the various intersections between sprites, and it keeps track of when the user has won/lost the level/game. 

The game-player loads data-files for a particular game and uses the game-engine to allow the user to play a loaded game. The game-player also manages the game: it allows the user to view the available games and pick one to load, keeps track of the games' high scores, displays dynamically updated game-status information, allows for the user to replay/switch the game without quitting, and finally allows the user to save progress in the game. 

##Overview##
This section serves as a map of your design for other programmers to gain a general understanding of how
and why the program was divided up, and how the individual parts work together to provide the
desired functionality. Describe specific modules you intend to create, their purpose with regards to
the program's functionality, and how they collaborate with each other, focusing specifically on
each one's API. Include a picture of how the modules are related (these pictures can be hand drawn
and scanned in, created with a standard drawing program, or screen shots from a UML design program).
Discuss specific classes, methods, and data structures, but not individual lines of code.

####Authoring Environment
In a general sense, the authoring environment UI is broken up into four primary parts, with the end goal being the passage of game data in a single or multiple XML files
back to the game engine. There is a menu bar that allows the user to choose options such as File to open new games or create new games. From the menu bar in the authoring environment,
users can also choose to load and edit existing games in addition to clicking a play button to begin playing a game the user has loaded or created. There is also a window called the item window where the user can view available 
objects to be dragged and created in the game environment, whether that be enemy object, user objects, or even obstacle objects. There is also a display window where the user can drag and drop objects onto to create the objects displayed and controlled in the user's game. Finally, each item in the display window can be edited by the user simply by clicking on the item, and a separate pane in the authoring environment will pop up and display that object or character's attributes. From there, the user can choose to set custom attributes, with examples containing health or attack points.
In general, the authoring environment will be an interactive tool for the user to create custom game objects, set custom game rules, and set custom properties for each character in the game.

####Game Engine

####Game Data

####Game Player



##User Interface##
This section describes how the user will interact with your program (keep it simple to
start). Describe the overall appearance of program's user interface components and how users
interact with these components (especially those specific to your program, i.e., means of input
other than menus or toolbars). Include one or more pictures of the user interface (these pictures
can be hand drawn and scanned in, created with a standard drawing program, or screen shots from a
dummy program that serves as a exemplar). Describe how a game is represented to the designer and
what support is provided to make it easy to create a game. Finally, describe any erroneous
situations that are reported to the user (i.e., bad input data, empty data, etc.).

#### Authoring Environment Interface
![Authoring Environment Interface](https://github.com/duke-compsci308-spring2016/voogasalad_TheDuballers/blob/master/DESIGN/authoringUI.png "Authoring Environment Interface")

#### Authoring Environment Interface Explanation
The authoring environment will be divided into four sections: a menu bar, an item window, a level-editing window, and a sprite-properties window. 

**Menu Bar**
The menu bar will be at the top of the screen. It will have clickable menu items that the user can click to save authoring environments, load authoring environments, and edit authoring environment settings, and more depending on the program is extended. In the menu bar the user will also have access to a button that brings up a pop-up window, where the user can edit basic game settings. Finally, the menu bar will contain a play button that allows the user to play the game that he or she has built.

**Item Window**
The item window will be on the left-hand side of the screen. The item window will contain all of elements that the user can place and set in their authoring environment. The elements will be divided into categories and each of these categories will be given its own tab in a tabPane. Categories will include player elements, enemy elements, environment elements, background elements, and perhaps more, such as power-up elements, depending on the extensions that we choose to implement. Every tab pane will include the option for a user to add their own image, so that they can create programs that are not limited to default images. It should be noted that added images will automatically be created as sprites for the category that the user has currently selected. Every single element in the item window, regardless of the category that is in, will be drag-and droppable.

**Level-Editing Window**
The level-editing window will be the largest window on the user interface, and will be a tabPane in the middle of the screen. In this window, the user can drag and drop elements, placing them wherever he or she so desires. The user will also be able to resize the current level using plus/minus buttons, so that the level can be made wider or narrower, longer or shorter. The user can interact with the tabPane and create a new tab. Every single tab in the level-editing window represents a new level. All of the elements that appear on any level will be endlessly editable, and will be closely linked to the sprite-properties window, which will be discussed next.

**Sprite-Properties Window**
The sprite-properties window will be on the right side of the screen. It will be filled with a vBox that can be filled with any amount of hBoxes (the number of hBoxes will in essence be dictated by our editable-properties factory). The sprite-properties window will display all editable properties for the sprite that was most recently click on in the level-editing window. The way in which these properties will be edited will change based upon what the property actually is. For example, health might be changed based with a numerical ticker, and movement type might be changed with radio buttons. There are many options. Please see the editable-properties factory for more detail. Changes that the user makes in this window will affect the actually sprites that exist in the level-editing window.

**Erroneous Sitations**
Erroneous situations that will be reported to the user include: loading an improper file for the authoring environment. The user might try to load a file that is null or is not an XML document. The user may try to upload an image for a sprite, but that image type may not be supported. Certain sprite-properties will have ranges, and the user may attempt to apply values that are outside of these ranges, or other values that are invalid (inputting strings instead of int, etc.). Each of these situations is not allowed, so specific and descriptive errors will be thrown. The user will be notified with an error pop-up window.

#### Game Player Interface
![Game Player Interface](https://github.com/duke-compsci308-spring2016/voogasalad_TheDuballers/blob/master/DESIGN/PlayerUI.png "Game Player Interface")

### Game Player Interface Explanation

##Design Details##
This section describes each module introduced in the Overview in detail (as well as any other
sub-modules that may be needed but are not significant to include in a high-level description of the
program). Describe how each module handles specific features given in the assignment specification,
what resources it might use, how it collaborates with other modules, and how each could be extended
to include additional requirements (from the assignment specification or discussed by your team).
Note, each sub-team should have its own API for others in the overall team or for new team members
to write extensions. Finally, justify the decision to create each module with respect to the
design's key goals, principles, and abstractions.

#### Authoring Environment Design Details
The Authoring Environment is currently split up as so:
> - The MenuBar displayed in the Authoring Environment will be created by a class, MenuBarCreator, which will have instances of classes that each create one particular Menu Item component of the overall Menu Bar, for example, FileMenu would be a class that creates the File Menu Item. Thus, MenuBarCreator would have multiple instances of multiple menu item wrapper classes and put them together, allowing each class to be created separately will ultimately allow us to add new menu item components to the main menu bar easily.
> - The ItemWindow is the component of the Authoring Environment that displays small draggable icons of different characters. For this class, we decided to keep most in a Tabpane, and have each class in the creation of the tabpane have access to the sprites available, which would allow us to display the images of the sprites in the ItemWindow in addition to keeping the information about each of sprites and their properties.
> - Our setup of our Sprites objects took significant time to design. For each sprite object, we will have subclasses in each sprite. One subclass will be the Model of the sprite, that is, the sprite with only its properties and no other components; the model will be the part of the sprite sent to the back end. The other subclass of the sprite will be the View aspect of the sprite, that contains its image and other visual display properties to be shown on the Main Display window. By separating our sprite objects this way, we are able to separate the components of the sprite depending on the front end or back end needs. This way, only relevant information is passed to front end and back end.
> - One aspect of our Authoring Environment allows for a pane in the Authoring Environment appear with a list of editable properties according to which specific object is clicked. Since different sprite objects have different properties, we have to figure out a way to generate the proper number of user editable objects in addition to the correct editable object associated with each type of property (for example, health is a numerical measure that could be easily modified with a slider for new values). For this, we decided to use a Factory that contains a Map of Properties to their corresponding Fx object (ex. Health Property and Slider). In this way, we are able to generate the proper HBox components to be displayed for each editable property, with the HBox containing the label of the name of the property and the corresponding Fx object needed to change the value of that property. All the HBoxes will be placed into a VBox that will be displayed in the Settings Window, so the user can scroll up and down the window and change properties one row at a time.
> - Our Display Window that holds the sprites is simple, with it primarily being a TabPane as well to accomodate the user if he/she decides to edit multiple levels of a game rather than limiting them to just one. We want the user to be able to scroll up and down the background of the game, placing objects anywhere in the background, so we decided to place a Canvas within a Scrollpane for this aspect of the Authoring Environment.

#### Game Engine Design Details

#### Game Data Design Details

#### Game Player Design Details

##Example games##
Describe three example games from our genre in detail that differ significantly. Clearly identify how the functional differences in
these games is supported by your design and enabled by your authoring environment. Use these
examples to help make concrete the abstractions in your design.

###Common Features####
*Enemies and player can attack
*Score and lives/health
*Multiple levels
*Multiple enemy types

###Space Invaders###
*Player and enemies can only shoot vertically, and the player can only move horizontally
..*Player and enemies will both be sprites, which can have their movement and orientation defined/restricted. User can modify these restrictions
*Pattened enemy movements (no AI)
..*Sprites velocites can have defined patterns, via the setPattern() method. 
*Game over if enemies reaches the ground
..*User can specify win/lost conditions, e.g. collision of an enemy with a specific sprite (such as the ground)


###Galaga###
*Creative power-ups for enemies(being abducted by tractor beam)
..*This would certainly not be in the basic implementation, but we would generate a new object, have the user define it's movements and whether it's movements are dependent on another
sprite(in this case they are because it is a conjoined twin of the main character's ship), and have it disappear after some time
*bonus levels 
..*Players design each individual level, with special rules and sprites, so the a player can easily design a bonus level in our rules)
*mother ships
..* Players design their own sprites, they can design a mother ship by making it bigger, making its shots more damaging, and giving it a shield(shield is another sprite)
*level icons
..*level icons can be damage free sprites that don't do anything when you intersect them
*power up where user has twin ship appended to it(twice the firing power)

###Robotron: 2084###
*Enemies swarm player
..*Sprites behavior has a setTarget method
*Multiple type of weapons
..*setSpawn can specify what projectile a sprite shoots
*Advanced animations

*Enemies AND Friendlies
..*Use ID to differentiate between behavior on attack/collision
*Animations between levels
..* Animations between levels are short levels whose win conditions are just a matter of time. The user will choose not to "interact" with these levels should they wish to make
an animation. The user can either upload an animation via some file type to play as a background in this "level" or they can design the level as they would design a ship pattern
*Enemies/Obstacles change apperance
..*Each sprite has a list of images assigned to it, we just trigger which image is showing at any given time
*Sprites fade in (not instaneous or from off-screen)
..*
*Score pops up from killed enemy
..* There already is an event handler when an enemy dies in case the user wants to update the score, just have the user upload an image that will act as a temporary sprite whose coordinates take
the locaiton of the person that was just killed(we will provide some default animations)
*Projectiles can reflect off walls
We already include functionality to set boundaries. When a projectile hits a vertical wall, its x velocity is negated. When it hits a horizontal wall, the y velocity is negated

##Design Considerations##

###Inheritance vs. Composition###
> - Originally, we planned on using an inheritance hierarchy to represent all the sprites, in the back end. We had an abstract Sprite superclas
which had two subclasses. One was a Character class, which was also abstract. The main difference between this class and its superclass was the
fact that it had the capabilities to attack and defend. Furthermore, this class had two subclasses; Player and Enemy. These classes were 
concrete. The significant distinction between them was that the Player was user controlled while the Enemy was AI controlled. The second subclass
of Sprite was the Item class. This class was concrete, not abstract, and was made to represent the various items that the user may choose to 
create, such as weapons and power ups. However, after giving it some thought, we realized that all of these classes are really not that different
from each other. In addition, the hierarchy was fairly large and had the potential to get larger. To combat this, we thought it would be better
to use composition. By doing so, we would have only one concrete Sprite class that had some basic common features such as its location on the
canvas, and an image for its graphical representation. The user would then choose with attributes they want to give the sprite, such as methods
of attack or movement, which would then be separate classes that would be added to the Sprite. An inheritance hierarchy is still being used, but
to represent the different behaviors of the sprite. This makes it easily extendable, as new behaviors can inherit common features from their
superclasses, which means the only code that will be necessary to write would be what is unique to that behavior.

###Strategy Design Pattern###
> -This design pattern ties in with our decision to use composition, as described above. Using this pattern, 

###Observable Design Pattern###
> - 

###XStream vs Serializable###
> - Serializable is not human-readable so there is no way to see what is being stored just by looking at the text of the file. Serializable, however, presents a much easier and short set of method calls needed to fully serialize an object into a file and deserialize back to the original object. XStream, we found, was the middle ground between the simplicity but non-readability of Serializable and the difficulty but readability of XML files. With XStream, many of the original benefits of Serializable are preserved, that is, being able to have the program automatically save objects as files. XStream is also very human readable as objects are stored as XML files in the end, and this helps with allowing us, the programmers, to determine whether not data has been saved correctly; this feature could potentially be very helpful one day as our saved objects contain more and more data.

###Factory Design Pattern -- SAMMY###
> - The editable-properties factory (name temporary) is the meat of the sprite-properties window. The factory will be fed in a list of sprite properties and will create a number of hBoxes that are filled with the property name as well as the JavaFX element that the user can utilize to change that property. In the simplest sense, the factory links properties to JavaFx elements. For example, a health property might be inputted into the factory and an hBox containing “Health:” as well as a number ticker will be outputted. The factory does contain an assumption and dependency though. Every property must be placed into a map and linked with the JavaFX element that should allow the program user to edit the property. If a new property is created, it must be added to the map within the factory class. This map allows the factory to know how to fill its hBoxes. As a team we understand that this design choice isn’t perfect, but it is the best solution that we can come up with. We need sprite properties to be connected to JavaFX elements. We thought about having each property class extend or contain its appropriate JavaFX element, but this would send unnecessary front-end information to the back-end, since the back-end will be utilizing these property classes. We thought that this was a worse design choice. 
