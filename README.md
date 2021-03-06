<h1 align="center">
  <br>
  <img src="https://raw.githubusercontent.com/StylexTV/Maple/main/image/cover.png">
  <br>
</h1>

<h4 align="center">🍁 Source code of the Maple bot, made with ❤️ in Java.</h4>

<p align="center">
  <a href="https://GitHub.com/StylexTV/Maple/stargazers/">
    <img alt="stars" src="https://img.shields.io/github/stars/StylexTV/Maple.svg?color=ffdd00"/>
  </a>
  <a href="https://www.codacy.com/gh/StylexTV/Maple/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=StylexTV/Maple&amp;utm_campaign=Badge_Grade">
    <img alt="Codacy Badge" src="https://app.codacy.com/project/badge/Grade/a1b260d90028428fbf9ce33f4fcf311e"/>
  </a>
  <a>
    <img alt="Code size" src="https://img.shields.io/github/languages/code-size/StylexTV/Maple.svg"/>
  </a>
  <a>
    <img alt="GitHub repo size" src="https://img.shields.io/github/repo-size/StylexTV/Maple.svg"/>
  </a>
  <a>
    <img alt="Lines of Code" src="https://tokei.rs/b1/github/StylexTV/Maple?category=code"/>
  </a>
</p>

## Overview
[Maple](https://maplemc.github.io/) is a free and powerful path-finding bot that lets you navigate the vast landscapes of [Minecraft](https://www.minecraft.net/) without any user intervention.

Additionally, Maple focuses on efficiency and performance, while also paying attention to reliability and an unobtrusive look.
The bot is mainly controlled by [chat input](https://github.com/StylexTV/Maple#commands) and the underlying pathing algorithm is called [A*](https://en.wikipedia.org/wiki/A*_search_algorithm), a perfect algorithm for navigating voxel worlds like Minecraft.

If you simply want to use Maple, you can download the latest version from the [download page](https://maplemc.github.io/). With the appropriate version of [Fabric](https://fabricmc.net/use/) installed, you should be good to go.

This project is heavily inspired by Baritone, another pathfinder for Minecraft. Check out their repository at this [link](https://github.com/cabaletta/baritone).

## Features
Maple is equipped with many useful features that allow it to find the way to any destination and overcome any obstacle.

### 🌎 Long distance travel
Maple is able to travel for thousands of blocks without any user input, i.e. completely autonomously. It achieves this by using path segmentation and calculating the next segments when they are needed. Moreover, Maple is so reliable that it can walk for miles without slipping even once.

### 📚 Caching
To convert the Minecraft world into a more manageable format and to remember chunks even when they are no longer loaded, Maple makes use of caching. The software automatically converts the blocks around you into either `AIR, SOLID, WATER` or `DANGER`, making pathfinding much easier, for example.

### 📍 Waypoints
Waypoints are a way to remember important places in your Minecraft world. You can create them manually using `wp create <name> [x y z]`, but Maple also automatically generates waypoints when you die or sleep in a bed. When created, a marker will appear showing you the location of the waypoint. If you want to travel back to a specific waypoint, use the command `wp goto <name>`. For more information on waypoints, see the [Commands section](https://github.com/StylexTV/Maple/#commands).

### 🧱 Breaking/Placing blocks
Maple is not only able to traverse the world, but also knows how to interact with it. It will automatically destroy blocks that are in its way or place blocks to create a more efficient path. These functions can be deactivated if desired (see [Options section](https://github.com/StylexTV/Maple/#options)).

### 🔥 Avoiding dangers
Maple knows which blocks are safe to walk on and which blocks can cause damage (e.g. lava, fire, cacti, ...). It will never choose a path that can hurt the player in any way (this includes fall damage).

## Commands
All your interactions with the bot take place via the in-game chat functionality.

In order for Maple to recognize your command, it must start with a `#`.
You can use the following list as a guide or simply use the ingame command `#help`.

Name | Usages | Aliases | Description
--- | --- | --- | ---
help | help [page]<br/>help [command] | - | Gives useful information about commands.
goto | goto \<x> \<y> \<z> [radius]<br/>goto \<x> \<z><br/>goto \<y><br/>goto \<block_type> | - | Starts moving to a custom goal.
mine | mine \<block_type> | - | Starts mining a specified block type.
farm | - | - | Starts harvesting nearby crops.
follow | follow \<entity_type><br/>follow \<name> | - | Starts following a specified entity.
stop | - | cancel | Stops the current task.
pause | - | - | Pauses the current task.
resume | - | - | Resumes the current task.
waypoint | waypoint create \<name> [x y z]<br/>waypoint delete \<name><br/>waypoint list [page]<br/>waypoint info \<name><br/>waypoint goto \<name> | wp | Used to create and travel to waypoints.
home | - | h | Travel to the nearest waypoint marked as *HOME*.
lost | - | - | Travel to the nearest waypoint.
tunnel | - | - | Tunnel in the direction you are looking.
thisway | thisway [distance] | forward | Move a specified number of blocks in the direction you are facing.
axis | - | - | Travel to the nearest axis.
eta | - | - | Displays the estimated time to reach the current destination.
modified | modified [page] | - | Shows all modified options.
version | - | ver, v | Shows the installed version of Maple.

## Options
With the help of options you can better customize the behavior and functionality of Maple to your personal needs.

Use the command `#help option` in combination with the table of available options below to start customizing the bot.

  * #### allowBreak
    Whether or not the bot should be allowed to break blocks in order to get to the specified goal.
    Disabling could lead to the possibility of the bot not being able to reach the destination.

  * #### allowPlace
    Whether or not the bot should be allowed to place blocks in order to get to the specified goal.
    Disabling could lead to the possibility of the bot not being able to reach the destination.

## Progress
Below is a table showing the progress of systems, functions and features in Maple:

Name | Progress | Included in latest build
--- | --- | ---
Walking | 🟢 Stable | ✔️
Swimming | 🟢 Stable | ✔️
Jumping | 🟢 Stable | ✔️
Breaking blocks | 🟢 Stable | ✔️
Placing blocks | 🟢 (somewhat) Stable | ✔️
Avoiding blocks | 🟢 Stable | ✔️
Avoiding mobs | 🟡 Unstable | ✔️
Parkour | 🟢 Stable | ✔️
MLGs | 🟢 Stable | ✔️
Path segmentation | 🟢 Implemented | ✔️
Backtracking | 🟢 Implemented | ✔️

## Including
Feel free to include Maple in your projects/clients as long as your use of this project complies with our [license](https://github.com/StylexTV/Maple/blob/main/LICENSE).

## API
TODO

## Installation & setup
Before installing the Maple project on your PC, make sure that you are using at least [JDK 16](https://www.oracle.com/java/technologies/javase-jdk16-downloads.html). To check your Java version, open a command prompt or your terminal and enter `java -version`.

> **Note:** This guide is for installing the Maple project to **modify/build** it. If you just want to use Maple, you can find the latest build on the [download page](https://maplemc.github.io/).

#### Download
Clone or download the project using the button at the top of this page. Make sure to unzip the contents of the ZIP file if you have chosen to download the project.

#### Generating Sources
If you wish, you can generate the Minecraft source code for reference using `gradlew genSources`. However, this step is purely optional.

#### Eclipse
If your IDE of choice is Eclipse, run `gradlew eclipse`.
Then open Eclipse and select `Import > Existing Gradle Project > Select Folder`. Follow the wizard instructions and click *Finish*.

#### IntelliJ
If you prefer to use IntelliJ, open IDEA, select `File | Open` and select the `build.gradle` file.
After Gradle is done setting up, close and re-open the project to fix run configurations not displaying correctly.
