# Mazela-Man


## Introduction

In this tutorial we will build a simple game using [FXGL](https://github.com/AlmasB/FXGL) -
a Java / JavaFX / Kotlin Game Library. FXGL is built on top of JavaFX, but you don't need
to know JavaFX to follow the tutorial. We will use version 11.13 of FXGL.

The tutorial is divided into a number of chapters that each introduces a new part of the game,
and usually a new part of FXGL. Each chapter builds on the previous, so it's advisable to read
and execute them in order. Each chapter is also a Maven module that contains the code for the
game as it is at the beginning of the chapter, and a README file that describes the steps to 
perform in the chapter. Following the tutorial closely will result in code that looks like the
code contained in the following chapter. Experimentation is encouraged, but if you lose your 
way in the process, you can always start over with a working code base in the following chapter.

All resources used in the tutorial, and a few extras, can be found [here](reources).


## Requirements

The following pieces of software are needed to follow the tutorial:

* [Java 11](https://adoptopenjdk.net). FXGL version 11.13 requires Java 11 or later.
* [Tiled 1.2.3](https://github.com/mapeditor/tiled/releases/tag/v1.2.3). FXGL works best with 
  version 1.2.3 of the Tiled map editor. You only need to install Tiled if you want to edit 
  level files yourself. Otherwise, you can just copy the edited file from the next chapter. 
* [IntelliJ IDEA](https://www.jetbrains.com/idea), [Eclipse](https://www.eclipse.org/eclipseide), 
  or another IDE.  

You also need to clone the Git repository of the tutorial. You can do this in your IDE, or
on the command line:

```shell
git clone https://github.com/dykstrom/mazela-man.git
```


## Resources

* [FXGL Library](https://github.com/AlmasB/FXGL)
* [FXGL Example Games](https://github.com/AlmasB/FXGLGames)


## Credits

Graphics by drakzlin at [OpenGameArt.org](https://opengameart.org).
Sounds from [FXGL samples](https://github.com/AlmasB/FXGL), and [Classic Gaming](http://www.classicgaming.cc).


## Getting Started

Let's get started with the first part of the tutorial:

[[Start](00-introduction/README.md)]
