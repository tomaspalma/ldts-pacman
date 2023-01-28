# Pacman - LDTS - project-l01gr01

*Note on the commits:* 80,000 additions of tomaspalma are due to the upload of the pitest folder

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

<hr>

### Gameplay

![](https://cdn.discordapp.com/attachments/1019715937009672223/1055111527633518612/image.png)

### Gameplay Demo

![](https://cdn.discordapp.com/attachments/1019715937009672223/1055171205788872834/2022-12-21-13-31-04_1.gif)

There is a slight pause in the middle but that's because we're trying to show that, after some time, the ghosts go to
scatter mode and each other will start to go to their respective corners.

This project was develop by Diogo Martins (*202108883*), Rodrigo Martins (*202008868*)
and Tomás Palma (*202108880*)

Project Grade: 18.7 / 20
