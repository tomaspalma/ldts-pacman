# LDTS - 1LEIC01-01 - Pacman

## Essence & Authors

### What is it?

Our group chose pacman as a game to work on. Pacman is a game created in the 1980s. The game consists of a player controlling pacman (which is a round figure with a mouth).
He's locked in a maze where he must eat power-pellets and pac-dots without being reached by the ghosts. Upon eating a power-pellet, he can also eat the ghosts that are after him.
It is also possible to eat cherries to score more points. If the ghost touches us and the pacman hasn't eaten a power pellet, the player loses a life and the game restarts.
In the beginning, players have 3 lives. When this number reaches zero, the game ends.
When the player eats all edibles that are on the screen, he moves on to the next level.

### Authors
- *Diogo Martins* (*up202108883*@fe.up.pt)
- *Rodrigo Martins* (*up202008868*@fe.up.pt)
- *Tomás Palma* (*up202108880*@fe.up.pt)

**Note:** This report was done during a live intellij session, despite the single author commit 

### IMPLEMENTED FEATURES

The basic features already implemented are:

- **Pacman movement & animation**
 
Pacman is able to move in four directions guided by keyboard input from the player as well as move its mouth
up and down.

- **Read the map of the arena from a file, making it customizable**

The game should be able to have an arena whose structure is designated by text in a file, which makes it 
fairly customizable. Besides that, we also left room in our structure to in the future add other types of loaders that do not 
simply read from a text file

- **Different ghosts strategies**

Each of the for ghosts has its own unique strategy when they are in chase mode or scatter mode, although they all
share the same frigthened and dying strategy.

- **Time controlled  changes on the current state of the ghosts during game execution**

Each level has an internal clock that, based on the current time ellapsed, it will execute every changes on a ghost (either on a ghost
or on all ghosts) that are defined in each state of our state machine and then it will transition to a state whose
time to activate is not lesser than the ellapsed.

- **Level changes when the user passes to the next level**

Although we don't have different maps, we have different levels. Each level has timings for the ghosts to change
their states. The more levels you advance, the lesser time the ghosts take to change state until a level limit
where the time can't be lowered no more.

- **Working menus**

    - Starting menu where the user can choose to exit the game or to start playing
    - Pause menu (*the user needs to press the ENTER key*)
    - Game over menu where the user can choose to play again, to go back to the main menu or to leave the game

- **Sounds**
  - Menu intro music 
  - Pacman eating 
  - Pacman dying
  - Frightened ghost dying

We also have some tests both simple and using mocks 
(such as [this one in EntityViewer](https://github.com/FEUP-LDTS-2022/project-l01gr01/blob/intermediate-delivery/src/test/groovy/org/ldts/pacman/viewers/EntityViewerTest.groovy)

### PLANNED FEATURES

All of the planned features except better menus were implemented.

### DESIGN

The UML for the designs were split into pieces because of the complexity of the UML because of the 
amount of classes we ended up creating in our program. Also, more specificly zoomed in UML about the design patterns we implemented
into our program can be found in the specific topics about them in this report.

### SOLID respecting design architecture

We wanted a design architecture that respected SOLID principles, specially the single responsabilty one, and,
because of that we decided to go with the MVC approcha given in the theoretical classes.

- **Model**: Contains the structure of the elements that are part of our game and the definition of attributes or functions that will be used by the other two components from the MVC approach. In addition, the model will only contain information about a certain element and other functions that can be called by one or both of the other counterparts of the MVC structure.

- **Viewers**: Contains the logic to render the elements of our game and only that. In other words, the only purpose of the view is to draw the entities we want to draw on the screen for the user to see and update the rendered images based on changes to the model.

- **Controllers**: Contains the logic to allow the user to interact with pacman element via keyboard input and also logic to control the way the ghosts move, as well as the way collisions between pacman and other elements occur and, for example, what is the behaviour of the edibles in the game when a certain action occurs, such as pacman eating them.

Although there are many viewers, models and controllers there is one major concrete implementation of them which is in the Arena.

The arena viewer, controller and model are the main classes of each of its MVC components. The arena viewer calls the draw functions of the viewers of the other classes, the arena controller calls the step functions of the controllers of the other classes and the arena model has information about all the other entities.

**The implementation**

***Model***

Movable entities

Ghost strategies and states

***Controllers***

![](https://cdn.discordapp.com/attachments/1019715937009672223/1055496658412113950/image.png)

***Viewers***

![](https://cdn.discordapp.com/attachments/1019715937009672223/1055496955431751801/image.png)

### Implement different strategies for each ghost

**Problem in Context**

Ghosts should behave differently according to their colour and the game's circumstances (for instance, pacman having just eaten a power-pellet).

**The Pattern**

Self-evidently, the pattern to be applied in this situation is the **Strategy** pattern.
Different strategies are implemented separately in different classes that implement their respective interface depending on the type
(Chase strategy to tell how the ghosts will behave in the chase stage of the game, as well as the scatter and frightened stragies that
are to tell the ghosts what to do in those situations), and these
strategies are then stored as a dynamically-mutating attribute of the ghosts themselves.

The strategies are going to be different depending on the ghost and the type of ghost, although in regular ghosts,
they share the same dying and frightened strategy.

Each strategy has then a generate next position method that is used to move the ghosts and make them move in different ways
because each of the method in each strategy can have a different way of determining the next position.

**Implementation**

![](https://cdn.discordapp.com/attachments/1019715937009672223/1055454702944395284/image.png)
![](https://cdn.discordapp.com/attachments/1019715937009672223/1055456130890997891/image.png)
![](https://cdn.discordapp.com/attachments/1019715937009672223/1055458355180748842/image.png)
![](https://cdn.discordapp.com/attachments/1019715937009672223/1055460461233045634/image.png)
![](https://cdn.discordapp.com/attachments/1019715937009672223/1055490721240580116/image.png)

**Consequences**

- Despite raising the number of classes, it brings increased scalability and ease of development,
because we will have less code in just one place, making it easier to maintain. Also,
it is way easier to make changes to an existing strategy if more than one concrete class is following it, because
we would just need to change it in one place, instead of going to each class that had that strategy in order to change it.

### Dynamic way to determine which type of strategy a ghost is currently using

**Problem in Context**

The problem is not to choose if they will use a RunAwayFrightenedStrategy or an AgressiveChaseStrategy, but rather
whether they will be using a frightened strategy or a chasing strategy or a scatter, etc 

And we wanted to determine that without a lot of switch or if-else statements.

**The pattern**

So, we implemented the state pattern, where each ghost has a certain state and then when it wants to move it asks the state
to execute the method to generate the next position and then each state will execute the methods from the respective strategies
of the ghost.


For example, the chasing state will execute whatever the chasing strategy attribute in a ghost may be.

**The Implementation**

![](https://cdn.discordapp.com/attachments/1019715937009672223/1055463302563647559/image.png)

**Consequences**

- Removing the need for switch or if-else statements and more respect to the **Open-Closed principle**
- As many other patterns, it generates more files, despite the code in each file becoming more organized.
- It provides a nicer way to implement more ghost states in the future without the need to worry about increasing
the number of lines of conditional statements that may have arisen if we were not to choose to do it this way.

### Ability for the regular ghosts to change themselves when a power pellet is eaten

**Problem in context**

When a power pellet is eaten, the ghosts need to switch the state from whatever state they are in to the frightened state.

So, we needed a clean way in order to establish this special relationship between the regular ghosts and the power pellets without making
the code look  bad.

**The Pattern**

We decided to implement the observer pattern, making this part of the code about the relationship between the regular ghosts
and the power pellet more logical because if a ghost changes when a power pellet is eaten it makes a lot of sense
to establish that relationship as one being an observer and the other one an observable that notifies the ones
that are watching its state.

**The Implementation**

![](https://cdn.discordapp.com/attachments/1019715937009672223/1055467717982949397/image.png)

**Consequences**

- It will be way more easier if we wanted to add another entity that depends on something that the power pellet does because
what it would take would be to just add another observer into the power pellet, making it respect the **open-closed principle**
- One universally documented consequence is that the subscribers are notified in random order, but that doesn't look
like a problem in the context of this program.
- It improves scalability and ease of development as it removes the need to add specific code to the ghost controller to
periodically use information from the arena to check if a power pellet was removed every time the game loop runs which would be
undesirable in terms of efficiency. 
- It uses more files, despite making the code itself in each file become or organized.

### Ability for the arena controller to make changes when pacman eats edibles or hits a ghost

**The problem in context**

If we delegated the work to the pacman controller to change the arena itself when it detects pacman ate an edible or hit a ghost
we would be violating the **Single-Responsability principle** and, because of that, we wanted some way to notify the arena controller
itself and then the arena controller changes the arena because that's his responsability, not pacman's nor pacman controllers'.

**The Pattern**

As the example above, we also chose to implement hte observer pattern here so that the arena controller is an observer
of pacman and as a response pre-defined to each of the possible notifications the pacman can issue to its subscribers.

**The Implementation**

![](https://cdn.discordapp.com/attachments/1019715937009672223/1055478083236401193/image.png)

In this case the arena controller implements the pacman observer interface while pacman itself implements the
PacmanObservable interface.

**Consequences**

- It will be more easier if we wanted to add another entity observing what pacman is doing than if we had a bunch of conditinals.
- One universally documented consequence is that the subscribers are notified in random order, but that doesn't look
  like a problem in the context of this program.
- It removes the need for the arena controller to always be checking if pacman does anything worth noticing, letting
the pacman controller detect that and then inform its parent controller, the arena controller.

### Isolate the ability of using a gui from a specific implementation of one

**Problem in Context**

In order to be able to render text into the screen we are using a fairly large API called *Lanterna* which contains
a lot of functionality and methods that we are not going to use. In addition, due to the fact that a library can
change or be discontinued, it is important that we design our relation with this dependency in such a way
that in the future if we wanted to change our *GUI* library we would be able to as seamlessly as possible.

**The Pattern**

In order to isolate the concrete implementation of the *Lanterna* Library we followed the Facade pattern in order to create
a buffer between the complex implementation of the Lanterna library and our game which will not use all the functionality
of Lanterna.

**The implementation**

![](https://cdn.discordapp.com/attachments/1039541372723662868/1045298116083064893/image.png)

- [Abstract general gui](https://github.com/FEUP-LDTS-2022/project-l01gr01/blob/intermediate-delivery/src/main/java/org/ldts/pacman/gui/GUI.java)
- [Concrete lanterna implementation](https://github.com/FEUP-LDTS-2022/project-l01gr01/blob/intermediate-delivery/src/main/java/org/ldts/pacman/gui/GUIForLanterna.java)

**Consequences**

- As it was already said in the section of the *Problem in Context*, this pattern allows us to isolate us
from the complex implementation of Lanterna, as well as it allows us to switch gui libraries more easily
as the code in our game uses an object that implements a GUI, which can be any. We would just have to change
the place of the creation of the concrete object in our main Game class.

### Our game should be able to switch between different states

**The problem in context**

We did not want our game to have just the game and arena part where the user could play. 
We wanted the user to also interact with a menu designed to provide the user with more choice
about the game. However, it would not be wise to have a bunch of *if* or *switch* statements
inside our main class Game in order to choose in which state the game would be and the different
behaviours that it would have in a particular state

**The Pattern**

The pattern we decided to follow was the State pattern, in order to provide our application
the ability to switch from different behaviours that depend on the current state (e.g. if
we are in the menu or if we are in the gaming part itself)

**The implementation**

![](https://cdn.discordapp.com/attachments/1019715937009672223/1045674367209713664/image.png)

- [ArenaState](https://github.com/FEUP-LDTS-2022/project-l01gr01/blob/intermediate-delivery/src/main/java/org/ldts/pacman/states/ArenaState.java)
- [MenuState](https://github.com/FEUP-LDTS-2022/project-l01gr01/blob/intermediate-delivery/src/main/java/org/ldts/pacman/states/MenuState.java)
- [State](https://github.com/FEUP-LDTS-2022/project-l01gr01/blob/intermediate-delivery/src/main/java/org/ldts/pacman/states/State.java)

We currently only have one state because of this being an intermediate delivery. However, we decided to already follow with this pattern
as it provides more scalability and we  are eventually going to add a menu state to the picture.

**Consequences**

- Instead of having a lot of conditionals in the code of the Game class in order to alter the behaviour
depending on the current state we want our game to be.

- As it is with other patterns, one of the downsides is the increase in the number of files created. However, the text of the code itself
stays more well organized

### Ability for timing controlled ghost state switches to turn the game more dynamic

Although this technically isn't a formal design pattern given in classes, it's a design matter.

**The problem in context**

Like the more popular versions of pacman, we anted t he ability to be able to, depending on time, 
switch the ghosts from scatter state to chase state after some time. In the real Pacman game the ghosts
will change their state from chase to scatter and vice versa without player interaction, just based on timing with each level
having its own timings and phases.

**The implementation**

We chose to implement two state machines (*NFA's*):
- One to control the start sequences that, in the case of the regular ghosts, will determine when each of the three ghosts
that start inside the ghost house will leave it
- Another to control during sequences that in the case of regular ghosts will result in a transition to a certain state
all at the same time

#### KNOWN CODE SMELLS AND REFACTORING SUGGESTIONS

**Example of such a subsection**:

------

#### REFUSED BEQUEST

ScatteringState and GhostHouseState don't apply any changes to the Ghost, so their applyChangesToGhost overridden method
is empty. However, if GhostState didn't have this method we would need to have two virtually equal abstract classes, one
with, and the other one without, that method.

#### FEATURE ENVY

Feature envy is inherent to the use of the model-view-controller design pattern, since the controllers and the viewers
access, almost exclusively, the data of the model. So in order to preserve the **Single-Responsibility principle** it's
necessary that the modules that manipulate and show the data envy the modules that hold the data itself.

#### LARGE CLASS

Arena and ArenaController are both very large because they hold all data related to gameplay and manipulate it,
respectively.

### MESSAGE CHAINS

Methods need to delegate functionality to other objects which may lead to many successive requests for an object, like
*getModel().getLevels().get(this.currentLevel).getClock()*. If corrected by hiding the delegation in a new method,
though, it may become unclear what object is actually executing

### TESTING

- Screenshot of coverage report.
- Link to mutation testing report.

## Units and mutants we didn't cover and why

- We didn't cover some part of the GameLevel because of clock-related methods that were passing when running
individually but failing when running all the tests at the same time.
- We didn't cover some parts of GUI methods because of errors we were having about heap usage to its 
max size when running some unit tests for said component.
- It's very difficult to perform behaviour testing when a function from a class calls another function from the same class, although
in some cases we were able to perform state testing.