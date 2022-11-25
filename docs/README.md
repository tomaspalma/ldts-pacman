# LDTS - 1LEIC01-01 - Pacman

## Essence & Authors

### What is it?

Our group chose pacman as a game to work on. Pacman is a game created in the 1980s. The game consists of a player controlling pacman (which is a round figure with a mouth).
He's locked in a maze where he must eat power-pellets and pac-dots without being reached by the ghosts. Upon eating a power-pellet, he can also eat the ghosts that are after him.
It is also possible to eat cherries to score more points. If the ghost touches us and the pacman hasn't eaten a power pellet, the player loses a life and the game restarts.
In the beginning, players have 3 lives. When this number reaches zero, the game ends.
When the player eats all pac-dots on screen, he moves on to the next level.

### Authors
- *Diogo Martins* (*up202108883*@fe.up.pt)
- *Rodrigo Martins* (*up202008868*@fe.up.pt)
- *Tomás Palma* (*up202108880*@fe.up.pt)

**Note:** This report was done during a live intellij session, despite the single author commit 

### IMPLEMENTED FEATURES

As this is an intermediate delivery, most of the features are yet to be implemented as a lot of the time spent until now
was targeted more on how we would structure our game following good design patterns as this is the main goal of this project
and this course.

The basic features already implemented are:

- **Drawing on the screen** - The contents of the arena are drawn on the screen
- **Pacman movement** - Pacman should be able to move in four directions restricted by the walls of the maze.
- **Read the map of the arena from a file** - The game should be able to have an arena whose structure is designated by
- 
### PLANNED FEATURES

- **Ghost strategies** - Different coloured ghosts should have different approaches on how to follow pacman. Although the structure
and the classes for each ghost and strategy are created, there are no monsters drawn yet.
- **Dead-house** - When killed by pacman, ghosts should be sent to a place on the map from which they will later get back
get out and rejoin the game.
what's written in a file.

### MOCK-UPs

**Mock-up of Main Menu**
![Main Menu Mock](https://cdn.discordapp.com/attachments/1039541372723662868/1045290904942039040/Sem_Titulo-1.jpg)

**Mock-up of game action**
![GameAction](https://cdn.discordapp.com/attachments/1039541372723662868/1045290844585984020/image.png)

### Current state of gameplay

![](https://cdn.discordapp.com/attachments/1019715937009672223/1045756575735889950/image.png)

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

This also goes along with the *SOLID* principles of dependency inversion.

**The implementation**

![](https://cdn.discordapp.com/attachments/1039541372723662868/1045298116083064893/image.png)

- [Abstract general gui](https://github.com/FEUP-LDTS-2022/project-l01gr01/blob/intermediate-delivery/src/main/java/org/ldts/pacman/gui/GUI.java)
- [Concrete lanterna implementation](https://github.com/FEUP-LDTS-2022/project-l01gr01/blob/intermediate-delivery/src/main/java/org/ldts/pacman/gui/GUIForLanterna.java)

**Consequences**

- As it was already said in the section of the *Problem in Context*, this pattern allows us to isolate us
from the complex implementation of Lanterna, as well as it allows us to switch gui libraries more easily
as the code in our game uses an object that implements a GUI, which can be any. We would just have to change
the place of the creation of the concrete object in our main Game class.

#### Our game should be able to switch between different states

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

#### We have to tell to more than one entity that one specific common event occured (e.g. when a cherry is picked)

**The problem in context**

When a cherry is picked, a certain number of ghosts need to be notified of that event. So we need to  have a central publisher
that informs all the ghosts that need to be notified that a cherry was picked up and that they need to change its state
and enter the frightening stage.

**The Pattern**

We are thinking of implementing an observer pattern.

**The implementation**

**Consequences**

- It improves scalability as it 

#### KNOWN CODE SMELLS AND REFACTORING SUGGESTIONS

> This section should describe 3 to 5 different code smells that you have identified in your current implementation, and suggest ways in which the code could be refactored to eliminate them. Each smell and refactoring suggestions should be described in its own subsection.

**Example of such a subsection**:

------

#### DATA CLASS

The `PlatformSegment` class is a **Data Class**, as it contains only fields, and no behavior. This is problematic because […].

A way to improve the code would be to move the `isPlatformSegmentSolid()` method to the `PlatformSegment` class, as this logic is purely concerned with the `PlatformSegment` class.

### TESTING

- Screenshot of coverage report.
- Link to mutation testing report.