#The DuBallers#

##Introduction##
The goal of our program is to design an authoring environment in which users can design a customized game. Although our current set genre for game production is top-down shooters, our overall goal is to expand beyond that: we are aiming to design a program which lets the user create a custom game regardless of genre. As a result, our design for this project avoids the developing of the program in such a way that rules about the game/sprites are already decided in order to align with the customs of the genre.

The authoring environment allows the user to design the game. Users can click on sprites, making them appear in the main authoring window, where they can then drag and drop the sprites. As discussed later, sprites encapsulate everything from the player's character(s)/enemy sprites to projectiles/obstacles. After the user has dragged the relevant sprites onto the screen, they are able to choose various attributes for the sprites (health, attack, defense, movement etc..) In addition, the user chooses the objective of the game (time/score/enemy based). As a result, our authoring environment allows complete flexibility for the user to break out of the top-down shooter paradigm. At any point in the game, the user ought to be able to pause the game and in real-time, edit the authoring environment in order to allow for flexbility mid-game.

Once the user has completed their design for the game in the authoring environment, all of the data is converted into a Game Data file through using XStream. This Game Data File is fed into the game-engine in order to allow for the game's algorithmic functionality; the game-engine is the back-end behemoth of the game which accounts for the multitude of objects which encapsulate the rules of the game. This data must be transferred between the game-engine and the authoring environment in real time in order to allow for the user to be able to edit the authoring environment at any time. The game-player loads data-files into the engine in order to display to the user a loaded game.

The game-engine is designed to be as flexible as possbile in order to accomodate the goals layed out at the beginning of the design-document. Rather than having classes which were specific to the functionality of a top-down shooter, our classes are generally abstracted out to allow for fluidity in functionality (based on the user's design). As a result, the back-end controls the "rules" of the game; it updates sprites' positions when the user moves it forward, it keeps track of the health/attack/defense of various sprites, it defines the various intersections between sprites, and it keeps track of when the user has won/lost the level/game.

The game-player loads data-files for a particular game and uses the game-engine to allow the user to play a loaded game. The game-player also manages the game: it allows the user to view the available games and pick one to load, keeps track of the games' high scores, displays dynamically updated game-status information, allows for the user to replay/switch the game without quitting, and finally allows the user to save progress in the game.

##Overview##


####Authoring Environment
In a general sense, the authoring environment UI is broken up into four primary parts, with the end goal being the passage of game data in a single or multiple XML files back to the game engine. There is a MenuBarCreator class that allows the user to choose options from classes such as FileMenu to open new games or create new games. Classes like FileMenu create separate MenuItem objects that will be passed to the main MenuBarCreator class to be assembled. From the menu bar in the authoring environment, users can also choose to load and edit existing games in addition to clicking a play button to begin playing a game the user has loaded or created. There is also a window contained in the ItemWindow where the user can view available 
objects to be dragged and created in the game environment, whether that be enemy object, user objects, or even obstacle objects. The ItemWindow will have classes such as the ItemTab class to create multiple tabs for selecting different types of sprites to be placed in the environment. Each object will be a ViewSprite class that contains a Model subclass in addition to a View subclass. The view subclass of the sprite will be used to display objects in the front end. The model subclass will be used to send data to the back end. There is also a display window contained in the DisplayWindow class that will contain other classes to generate the game creation window where the user can drag and drop objects onto to create the objects displayed and controlled in the user's game. Each window will be placed in a TabPane creator class that will create different tabs for different windows. Finally, the settings windwo will be created in the SettingsWindow class that will fill itself according to the information given to it by the sprite properties. The SettingsWindow class will also contain a front end item factory to determine which Fx objects need to be made and displayed for which property that the user is editing. Each item in the display window can be edited by the user simply by clicking on the item, and a separate pane in the authoring environment will pop up and display that object or character's attributes. From there, the user can choose to set custom attributes, with examples containing health or attack points. In general, the authoring environment will be an interactive tool for the user to create custom game objects, set custom game rules, and set custom properties for each character in the game.

####Game Engine

The game engine is set up so that there is a high level game class which controls several level classes that the user customizes. The game class encapsulates a list of levels which represent the game which the user has authored. In each level
class, there are a set of sprites, goals, keyboard mappings, and a time object. The level class is in charge of updating its own objects for every frame of the game; it keeps track of the goals the user has achieved, updates sprite positions, updates collisions, and removes dead sprites. When the required number of goals are fulfilled, the Level class sets the isFinished boolean instance to true. We demonstrated an implementation of the visitor design pattern in both goal and keyboard action functionality; rather than having an unreasonable number of conditional statements to determine whether the level's goal has been finished, the design pattern allows the level to call the object's (goal) accept method. All of the objects that will appear on the screen will be implemented using the Sprite class. The key feature of this class is that its attributes will be defined by classes that implement the Behavior interface.  These classes will define specific features of
each sprite. Key features to mention are the sprite's attacking, defending and moving cacpabilities. These three features are subclasses of Behavior, and will also be abstract. As a result, we demonstrate a thorough understanding of both inheritance and composition; behaviors are constructed through the Behavior hierarchy while being added as instances to the Sprite. In addition, the Sprite contains an instance of SpriteProperties which is a wrapper class for dimensions, and orientation. This class is responsible for delineating between user-controlled and enemy Sprites. Finally, all sprites contain instances of Health, and a list of Collision objects that get applied when the sprite collides. 



####Game Data
The Game Data will be composed of an actual GameData class and a GameLoader class. The GameData will have all the game information, including GUI/view components, and will be shared by the front end and engine. The GameLoader will save and load this information into XML files. A GameLoader separate from the data was necessary in order to pass around the different player classes (which wouldn't make sense for the entire data).

####Game Player
The game player will be the link between the data and the game engine, and it will display components that allow the user to see and interact with the game. We decided to divide the Game player as into gameplayer, heads up display (HUD), highscoretable, and gamepreferences. The gameplayer is the module that handles the basic running of the game and displays the information handled by the game engine. It will be able to start, pause, and save games through methods in the game engine and its instance of a gameloader object, which loads and saves xml files. This module will have access to all the others (including data), since it provides the basic framework for playing a game. HUD and highscoretable are GUI elements that are added to the main window (which will be part of the gameplayer module). The HUD elements and HighScoreEntryItems will be basically wrappers for data objects, which will be created using the GameData that is kept in gameplayer. The highscore table is populated with lists, and it will be extendable/reusable for displaying other lists of varied elements (e.g. pre-existing games). The preferences will basically reuse the code from the authoring environment.


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
The menu bar will be at the top of the screen. It will have clickable menu items that the user can click to save authoring environments, load authoring environments, and edit authoring environment settings, and more depending on how the program is extended. In the menu bar the user will also have access to a button that brings up a pop-up window, where the user can edit basic game settings. Finally, the menu bar will contain a play button that allows the user to play the game that he or she has built.

**Item Window**
The item window will be on the left-hand side of the screen. The item window will contain all of elements that the user can place and set in their authoring environment. The elements will be divided into categories and each of these categories will be given its own tab in a tabPane. Categories will include player elements, enemy elements, environment elements, background elements, and perhaps more, such as power-up elements, depending on the extensions that we choose to implement. Every tab pane will include the option for a user to add their own image, so that they can create programs that are not limited to default images. It should be noted that added images will automatically be created as sprites for the category that the user has currently selected.

**Main Window**
The main window will be the largest window on the user interface, and will be a tabPane in the middle of the screen. In this window, the user can drag and drop elements, placing them wherever he or she so desires. The user will also be able to resize the current level, so that the level can be made wider or narrower, longer or shorter. The user can interact with the tabPane and create a new tab. Every single tab in the man window represents a new level. All of the elements that appear on any level will be editable, and will be closely linked to the sprite-properties window, which will be discussed next.

**Settings Window**
The settings window will be on the right side of the screen. It will be filled with a vBox that can be filled with any amount of hBoxes (the number of hBoxes will in essence be dictated by our editable-properties factory). The settings window will display all editable properties for the sprite that was most recently click on in the main window. The way in which these properties will be edited will change based upon what the property actually is. For example, health might be changed based with a numerical ticker, and movement type might be changed with radio buttons. There are many options. Please see the editable-properties factory for more detail. Changes that the user makes in this window will affect the actually sprites that exist in the main window.

**Erroneous Sitations**
Erroneous situations that will be reported to the user are rare but wil include: loading an improper file for the authoring environment. The user might try to load a file that is null or is not an XML document. The user may try to upload an image for a sprite, but that image type may not be supported. Certain sprite-properties will have ranges, and the user may attempt to apply values that are outside of these ranges, or other values that  are invalid (inputting strings instead of int, etc.). Each of these situations is not allowed, so specific and descriptive errors will be thrown. The user will be notified with an error pop-up window.

#### Game Player Interface
![Game Player Interface](https://github.com/duke-compsci308-spring2016/voogasalad_TheDuballers/blob/master/DESIGN/PlayerUI.png "Game Player Interface")

### Game Player Interface Explanation 

The game will be displayed (along with the HUD) in a main screen that only has a pause button. Upon clicking this button, users will see a menu with four choices: replay, switch, save, and settings. Replay will return to the main screen but restart the game. Clicking on switch will open the list of possible games (in a table like the high score one). Save will just run the save() method and return to the main screen. Settings will go to a new screen that contains the possible changes (same as the possible settings in the authoring environment). If a player finishes a game (i.e. wins or loses), then the high score table will be displayed.

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
**Game**
> - The Game class encapsulates a list of levels, and it cycles through those levels based on the current level's goals to effectively simulate a game.
>	- This class contains a GameInfo object, which contains important general information about the Game (author, title, date created, date modified), as an instance variable
>	- It also contains, as previously mentioned, a list of levels, and it has an integer representing the current level of the game
> - The GameEditor class holds a Game object as an instance field, and it has the ability to edit essentially all of the different aspects of a game, such as adding, changing, and removing goals to updating key press results.
> - The GameEngine class is the primary class of the Game Engine, as this contains the methods that the front end is able to access as well as the game loop through which a Game is run
>	- This is the class that the front end can access, as it encapsulates a GameEditor, so it can perform any of the important commands that the GameEditor can perform

**Level**
> - The level class contains the previous/next level, sprites, goals, time, score, and keyboard mappings for the relevant level which the user has authored in the environment.
> 	- The levelProperties instance variable is a custom object which holds a multitude of information about the level. 
> 		- Contains level ID/name
> 		- Contains instances of both previous/next level
> 		- Contains instances of both score and time
> 		- Contains the number of goals which the user has to complete before beating the level.
> 		- Contains a mapping from KeyCodes to KeyboardAction enumerations.
> 	- The spriteMap instance variable maps the Sprite's IDs to the relevant Sprite object with that ID.
> 	- The goalMap instance variable maps the goals's ID to the relevant goal with that ID.
> 	- The keyboardActionMap instance variable maps KeyboardActions, which are a custom enumeration to the relative keyboard action.
> 	- The currentSpriteID holds the integer ID of the sprite which the user is currently controlling.
> 	- The goalCount instance holds the number of goals which the user has finished in the level.
> 		- Everytime a user has finished a goal, the goalCount instance goes up.
> 	- The isFinished boolean indicates whether the level is completed or not.
> 		- When the required number of goals (stored in levelProperties) is met in the level, the isFinished boolean is set to true.
> - The level class utilizes the update() & handleKeyPress()/handleKeyRelease() methods in order to update its own objects for every frame of the game. 
> - Update() method
> 		- The update() method updates the sprite positions, checks collision, and checks the completion of goals.

	@Override
	public void update() {
		updateSprites();
		checkCollisions();
		if (completeGoals()) {
			setisFinished(true);
		}
		
	}
> - updateSprites()
> 	- The updateSprites() method iterates through the sprites within the sprite map and calls their update method.
> 	- After updating the sprite, this method removes dead sprites.
> - checkCollisions()
> 	- The checkCollisions() method iterates through the sprites contained within the level and uses a CollisionHandler and a CollisionChecker in order to resolve the collisions within the frame.
> 		- The CollisionChecker object determines whether two sprites are intersecting.
> 		- The CollisionHandler object applies the result of a collision if the two sprites do intersect.
> 	- If two sprites are intersecting, we must iterate through all of both of their respective Collisions and utilize CollisionHandler.

> - completeGoals()
> 	- The completeGoals() method utilizes a GoalChecker object in order to check whether the goals stored within the goalMap have been finished. It calls the goal's acceptVisitor method to determine whether the isFinished boolean is set to true. 
> 	- Returns a boolean based on the number of completed goals versus required number of goals to pass the level.

**Goal**
> - Goal functionality was implemented through the utilization of the visitor design pattern.
> 	- The visitor design pattern allows developers to represent a function to be performed on the elements of an object structure. 
> 	- The visitor design pattern promotes flexbility because it allows developers to modify/define new operations without changing the structure/data of elements. 
> 		- This visitor hierarchy promotes flexibility because it enables an abstraction of functionality (in this case checking goals). Rather than having the elements themselves check functionality, elements are designed to be simpler in this pattern. New functionality can be early added by created a new Visitor sub-class as opposed to having to change the structure of the element.
> - There are four components to Goal functionality.
> 	- GoalProperties
> 		- The GoalProperties class is a wrapper class for the data stored in the Goals class.
> 			- String goalName: name of the gaol
> 			- boolean isFinished: determines whether this partcular goal has been finished
> 			- Goals myGoal: goal enumeration type
> 			- totalPoints: total points towards the goal.

> 	- IGoal & Goal
> 	- The goal class defines the Goals enumeration and has an instance of goalProperties.
> 		- The design of this class mirrors the purpose of the visitor design pattern; the goal object itself does not contain a lot of data--it delegates checking functionality to Visitors.
> 	- The Goals enumeration contains the various kinds of goals implemented based on the variety of sub-classes to the goal class.
	
> 	- IGoalVisitor & Goal Visitor
> 	- GoalFactory
> - There is currently one concrete goals class: points goal.




The implementation proceeds as follows. Create a Visitor class hierarchy that defines a pure virtual visit() method in the abstract base class for each concrete derived class in the aggregate node hierarchy. Each visit() method accepts a single argument - a pointer or reference to an original Element derived class.

Each operation to be supported is modelled with a concrete derived class of the Visitor hierarchy. The visit() methods declared in the Visitor base class are now defined in each derived subclass by allocating the "type query and cast" code in the original implementation to the appropriate overloaded visit() method.

Add a single pure virtual accept() method to the base class of the Element hierarchy.  accept() is defined to receive a single argument - a pointer or reference to the abstract base class of the Visitor hierarchy.

Each concrete derived class of the Element hierarchy implements the accept() method by simply calling the visit() method on the concrete derived instance of the Visitor hierarchy that it was passed, passing its "this" pointer as the sole argument.

Everything for "elements" and "visitors" is now set-up. When the client needs an operation to be performed, (s)he creates an instance of the Vistor object, calls the accept() method on each Element object, and passes the Visitor object.

The accept() method causes flow of control to find the correct Element subclass. Then when the visit() method is invoked, flow of control is vectored to the correct Visitor subclass. accept() dispatch plus visit() dispatch equals double dispatch.

The Visitor pattern makes adding new operations (or utilities) easy - simply add a new Visitor derived class. But, if the subclasses in the aggregate node hierarchy are not stable, keeping the Visitor subclasses in sync requires a prohibitive amount of effort.

An acknowledged objection to the Visitor pattern is that is represents a regression to functional decomposition - separate the algorithms from the data structures. While this is a legitimate interpretation, perhaps a better perspective/rationale is the goal of promoting non-traditional behavior to full object status.



When the required number of goals are fulfilled, the Level class sets the isFinished boolean instance to true. We demonstrated an implementation of the visitor design pattern in both goal and keyboard action functionality; rather than having an unreasonable number of conditional statements to determine whether the level's goal has been finished, the design pattern allows the level to call the object's (goal) accept method. 
**Keyboard Actions**
> -
 
**Sprite**

The sprite class represents all of the user or enemy controlled objects that appear on the screen. This means that this single
class must represent pretty much everything the user sees when they play this game. In order to do this, we decided to use composition, 
and give each sprite the ability to use certain classes that define its key features. These classes can be split up into three
categories: Health, Collision, and Behavior. Health is quite simple and is really just a wrapper class for a double and boolean. 
Collision is an abstract class that is responsible for the implementations of different events that occur when things collide on
the screen (i.e. causing damage or adding points). When two things collide, the CollisionHandler uses method reflection in order to see if the first collision class 
has an implementation for when it collides with the second, and vice versa. If it does, then the method gets invoked. Therefore, to add 
a new collision, you just need to create a new class and implement the events that you want to occur when this class collides with
specific collision classes. Behavior is an interface that represents the actions a sprite can execute. There
are, at the moment, three abstract classes that implement this interface: Attack, Defense, and Movement. This interface consists of
the apply(Sprite sprite) method, which is how the Sprite executes the behavior. Thus, a sprite can execute all of its actions through
this method, without needing to know anything about what kind of action it is. In addition, all you need to do to add a new Behavior
is implement the apply method. As a result, both Collisions and Behaviors abide by the Open/Closed Principle.



#### Game Data Design Details
The Game Data portion consists of two primary classes: GameData and GameLoader.

> - The GameData class is responsible for holding the current state of the game through GameModels and GameViews instances, which can be updated by the setModels() and setViews() methods and have accompanying getters. There is only one instance of the GameData class, effectively making it a singleton; however, the only class that has access to this instance is the GamePlayer class. The GamePlayer class is also the only class that updates the GameData explicitly through method calls because all model data is updated through dependency injection into the backend. The GameModels class stores a List of Models which correspond to each View stored in the List of Views in the GameViews class. 

> - The GameLoader class has the primary methods: loadGame() and saveGame(). The loadGame() method simply takes in a file path to the stored game file and parses the game file using XStream method calls and returns the decoded objects to GamePlayer. The saveGame() method takes in a Map<Model, View> to write to XML and store it in the file system with a specified name. 


#### Game Player Design Details
The game player is primarily responsible for displaying high scores, dynamically displaying data through the heads up display (HUD), facilitating game preferences changes, and implementing replay, switch, and save game functionality.

> - The HighScoreTable class will hold an instance to a VBox that hosts the high scores for the specified game. This VBox is populated with a List of HighScoreEntry objects, which are added to the class through the addHighScore() method. The HighScoreEntry class is responsible for presenting a single player's high score for a game. This presentation will include: the player's name, the player's score, the time it took to achieve that score, and the maximum level at which the score was earned. Each of these properties is stored in a HighScoreEntryItem<T> object and is stored in a List that populates the HBox instance within the HighScoreEntry class. Because the HighScoreEntry class's HBox instance can hold a variable number of items, these entries can be compounded in the future with more relevant high score information. The HighScoreEntryItem<T> class is simple a wrapper for a high score-relevant data item of type T.

> - The HUDView class is responsible for displaying the complete HUD that will be visible during gameplay. The HUD will be represented through the JavaFX GUI object, BorderPane, which allows us to specifically place objects in the top, bottom, left, or right portions of the game screen. Each section of the HUD will be represented by a HUDElement, which consists of a Pane and can be initialized to either an HBox or VBox through its constructor. Because all HUDElements are indistinguishable from each other with respect to which section of the HUD it occupies, in the HUDView class, all HUDElements will be stored in a Map<HUDEnum, HUDElement> where HUDEnum is an enum class that has values: TOP, BOTTOM, LEFT, and RIGHT. This also serves the purpose of users specifying which HUDElement to add an object to through the add() and remove() methods in the HUDView class. In order to populate the HUDElement Pane instances, we created the HUDItem<T> class, which wraps an object of type T that one would typically find on a HUD. Since the objects stored in the HUDItem<T> class will be the same instance of the data it represents (through dependency injection), we can ensure that this data updates dynamically and no views need to be updated manually. 

> - The GamePreferences class is simply a stripped version of the settings panel in the authoring environment that deals with generic game settings, such as speed, difficulty, immortality, etc. Because of this, the GamePreferences class will simply reuse the code and class structure for the analogous piece in the authoring environment, as described above.

> - The replaying, switching, pausing, and saving of games will be handled by the GamePlayer class. The GamePlayer class has a GameLoader object, which handles the loading and switching of games, a GameData object that holds the game data specific to the game currently being played, a GameEngine object which handles the backend for the game, and Buttons that are linked to the replay(), pause(), save(), and switch() methods. The replay() method simply implemented by retrieving the GameFile class from the GameData class and passing it as an argument to the GameLoader class's loadGame() method, which subsequently reloads the same game on the screen, from the beginning. The pause() method simply calls the stopPlay() method in the GameEngine class to stop the changes of any models and prevent keystrokes from affecting game models. The save() method calls the saveGame() method in GameLoader, which is passed the current state of the game from the GameData object's method, getCurrentGame(). Also, the switch() method simply opens the file directory where games are stored and the user choice is passed to the GameLoader class, which loads the chosen game. The GameLoader class uses XStream to read and write game files through simple XStream API calls. 

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

###Observable Design Pattern###
> - We decided to implement the Observer design pattern to facilitate the communication between a view and its corresponding model for every sprite. This avoids the need to manually update the rendering of the game in the GUI in accordance with the changes the game engine would make to the image's respective model. This was accomplished through the use of JavaFX's Observer/Observable classes, which allow us to trigger a notification to an object's observers when something has changed within the object. We had many alternatives with regard to implementation, such as ObservableLists, Listeners, etc.; however, we wanted to stray away from using JavaFX data structures because of XStream's tendency to generate massive XML files for them, so Observer/Observable presented itself as the only option. The choice to use the observable design pattern over the model-view-controller architecture was motivated by implementation restrictions as well as ease. While the model-view-controller architecture is more robust with the ability of the controller to hold many instances of different views and models, that luxury wasn't going to be exploited with the implementation of a sprite because of its simple need of a single model and view. Additionally, the use of Observer/Observable between the model and the view almost made the controller redundant in that the communication between the two classes became automatic, thus rendering the controller's primary use of facilitating communication completely useless. 

###XStream vs Serializable###
> - Serializable is not human-readable so there is no way to see what is being stored just by looking at the text of the file. Serializable, however, presents a much easier and short set of method calls needed to fully serialize an object into a file and deserialize back to the original object. XStream, we found, was the middle ground between the simplicity but non-readability of Serializable and the difficulty but readability of XML files. With XStream, many of the original benefits of Serializable are preserved, that is, being able to have the program automatically save objects as files. XStream is also very human readable as objects are stored as XML files in the end, and this helps with allowing us, the programmers, to determine whether not data has been saved correctly; this feature could potentially be very helpful one day as our saved objects contain more and more data.

###Factory Design Pattern###
> - The editable-properties factory (name temporary) is the meat of the sprite-properties window. The factory will be fed in a list of sprite properties and will create a number of hBoxes that are filled with the property name as well as the JavaFX element that the user can utilize to change that property. In the simplest sense, the factory links properties to JavaFx elements. For example, a health property might be inputted into the factory and an hBox containing “Health:” as well as a number ticker will be outputted. The factory does contain an assumption and dependency though. Every property must be placed into a map and linked with the JavaFX element that should allow the program user to edit the property. If a new property is created, it must be added to the map within the factory class. This map allows the factory to know how to fill its hBoxes. As a team we understand that this design choice isn’t perfect, but it is the best solution that we can come up with. We need sprite properties to be connected to JavaFX elements. We thought about having each property class extend or contain its appropriate JavaFX element, but this would send unnecessary front-end information to the back-end, since the back-end will be utilizing these property classes. We thought that this was a worse design choice. 
