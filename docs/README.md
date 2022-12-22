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

#### Strucuture Paradigm

We chose to develop our pacman game using an MVC approach given in the theoretical classes. 

- **Model**: Contains the structure of the elements that are part of our game and the definition of attributes or functions that will be used by the other two components from the MVC approach. In addition, the model will only contain information about a certain element and other functions that can be called by one or both of the other counterparts of the MVC structure.

- **Viewers**: Contains the logic to render the elements of our game and only that. In other words, the only purpose of the view is to draw the entities we want to draw on the screen for the user to see and update the rendered images based on changes to the model.

- **Controllers**: Contains the logic to allow the user to interact with pacman element via keyboard input and also logic to control the way the ghosts move, as well as the way collisions between pacman and other elements occur and, for example, what is the behaviour of the edibles in the game when a certain action occurs, such as pacman eating them.

Although there are many viewers, models and controllers there is one major concrete implementation of them which is in the Arena.

The arena viewer, controller and model are the main classes of each of its MVC components. The arena viewer calls the draw functions of the viewers of the other classes, the arena controller calls the step functions of the controllers of the other classes and the arena model has information about all the other entities.

![UML Of MVC](https://cdn.discordapp.com/attachments/1019715937009672223/1045676212871254088/main1.drawio1.png)

### Implement different strategies for each ghost

**Problem in Context**

Ghosts should behave differently according to their colour and the game's circumstances (for instance, pacman having just eaten a power-pellet).

**The Pattern**

Self-evidently, the pattern to be applied in this situation is the **Strategy** pattern.
Different strategies are implemented separately in different classes that implement their respective interface depending on the type
(Chase strategy to tell how the ghosts will behave in the chase stage of the game, as well as the scatter and frightened stragies that
are to tell the ghosts what to do in those situations), and these
strategies are then stored as a dynamically-mutating attribute of the ghosts themselves.

The strategies are going to be different depending on the ghost and the type of ghost

**Implementation**

![](https://cdn.discordapp.com/attachments/1019715937009672223/1045672757498757130/image.png)
![](https://cdn.discordapp.com/attachments/1019715937009672223/1045673929169186887/image.png)
![](https://cdn.discordapp.com/attachments/1019715937009672223/1045674997911400509/image.png)

**Consequences**

- Despite raising the number of classes, it brings increased scalability and ease of development,
because we will have less code in just one place, making it easier to maintain. Also,
it is way easier to make changes to an existing stratregy if more than one concrete class is following it, because
we would just need to change it in one place, instead of going to each class that had that strategy in order to change it.

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

### We have to tell to more than one entity that one specific common event occured (e.g. when a cherry is picked)

**The problem in context**

When a cherry is picked, a certain number of ghosts need to be notified of that event. So we need to  have a central publisher
that informs all the ghosts that need to be notified that a cherry was picked up and that they need to change its state
and enter the frightening stage.

**The Pattern**

We are thinking of implementing an observer pattern where the ghosts are the observers and the power pellets are
publishers that notify the ghosts when they are eaten.

**The implementation**

There is no concrete implementation yet

**Consequences**

- - It improves scalability and ease of development as it removes the need to add specific code to the ghost controller to
periodically use information from the arena to check if one was removed every time the game loop runs which would be
undesirable in terms of efficiency. So, by having the power pellet notify the ghosts only when it is eaten, then
we don't need the ghosts to always be checking if the power pellet was eaten, executing the respective change of state only
when the power pellet notifies them.

#### KNOWN CODE SMELLS AND REFACTORING SUGGESTIONS

> This section should describe 3 to 5 different code smells that you have identified in your current implementation, and suggest ways in which the code could be refactored to eliminate them. Each smell and refactoring suggestions should be described in its own subsection.

**Example of such a subsection**:

------

#### DATA CLASS

#### FEATURE ENVY

#### LONG CLASS

### TESTING

- Screenshot of coverage report.
- Link to mutation testing report.

## Units we didn't cover and why

- We didn't cover some part of the GameLevel because of clock-related methods that were passing when running
individually but failling when running all the tests at the same time.
- We didn't cover some parts of GUI methods because of errors we were having about heap usage to its 
max size when running some unit tests for said component