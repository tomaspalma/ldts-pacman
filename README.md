# Pacman - LDTS - project-l01gr01

*Note on the commits:* 80,000 commits of tomaspalma are due to the upload of the pitest folder

As it's widely known, in this game (the more popular versions as well as this project),
you control pacman and the goal is to keep avoiding hitting ghosts when they are in a state
where they can kill you and collect as many points as possible.

As it's usual, the behaviour of the ghosts vary depending on if you eat a special edible (*for example,
a power pellet*) and also, on certain timings, because during the game, depending on the value indicating on the internal
clock of the game, the ghosts will switch modes. For example, during the game they will alternatively switch from chase mode
to scatter mode.

Also, when you eat all edibles of a level, you go to a new level that, despite being the same map, the timings of the ghosts
change. For example, the more levels you advance, the less time the ghosts will take to leave the ghost house and the less time
they will be in scatter mode.

Although pacman is an extremely popular game, there are some things that we should explain, especially
about ghost behaviour and strategies, that will be explained at the end of this document in order
not to obfuscate more important aspects for the evaluation of this project such as screenshots and a simple description
that is written above.

<hr>

## Screenshots

### Menus

#### Starting Menu

![](https://cdn.discordapp.com/attachments/1019715937009672223/1055107937078620190/image.png)

#### Pause Menu

You can go to this menu by pressing the *ESC* key of your keyboard. Unfortantely, it is not possible for you to change that key
unless you change the code.

![](https://cdn.discordapp.com/attachments/1019715937009672223/1055108801583386724/image.png)

#### Game Over Menu

![](https://cdn.discordapp.com/attachments/1019715937009672223/1055109291239030824/image.png)

Yes, the menus are not very visually appealing.

<hr>

### Gameplay

![](https://cdn.discordapp.com/attachments/1019715937009672223/1055111527633518612/image.png)

### Gameplay Demo

![](https://cdn.discordapp.com/attachments/1019715937009672223/1055171205788872834/2022-12-21-13-31-04_1.gif)

There is a slight pause in the middle but that's because we're trying to show that, after some time, the ghosts go to
scatter mode and each other will start to go to their respective corners.

# Features

# Ghost behaviour

**There are different kinds of strategies:**

1) Strategy used when they are in chase mode where they can move "inteligently" and can kill pacman.
2) Strategy used when they scatter. They can still kill pacman but they basically go to each
of the four corners of the map and during a certain amount of time they just stay there going in circles,
in order to give the player a break.
3) Strategy used when they are frightened. All of them move randomly while frightened.
4) Strategy when they die, which is basically going to the ghost house and then leave.

**Every single regular ghost has a different strategy:**

## Blinky 

The red ghost

### Chasing

This ghost will always target the tile where pacman is at.

So if you stop your pacman, this annoying ghost will come meet you and kindly kill you.

You must do what you can to avoid and keep living.

## Scattering

## Clyde

The orange ghost

### Chasing

This ghost will always target the tile where pacman is at.

So if you stop your pacman, this annoying ghost will come meet you and kindly kill you.

You must do what you can to avoid and keep living.

## Scattering
