# Skeleton for the IMAS practical work

This is the practical work skeleton for the subject "Introduction
to MultiAgent Systems" performed in Universitat Rovira i Virgili.


# Goal

Build a skeleton from where students are asked to design and develop
a multi agent system performing several tasks, with communication as
the main cooperation mechanism.


# Scenario

A 2D city with several kind of agents (ambulances, firemen, hospitals)
are represented in the map, with private vehicles moving around.
The city map is formed by buildings and street cells where private 
vehicles and mobile agents can move throughout.

See the IMAS1415.md document to see the practical definition for the
first semester of the academic course 2014-15.

To make it easy, the game will run in simulation steps. All necessary
communication is expected to be performed in every single simulation step


# Content

This project has a NetBeans project content to make it easy to start
with. To build the multi agent system, we use JADE.

There are two run profiles:

1. Run the multi agent system, showing up the city map in 2D.
1. Run the game settings builder.

You can build your running scripts starting with those profiles.

# Authors

* Other teachers build the first skeleton.
* Based on that, the current version was revisited by: 
 * Jordi Pujol Ahulló <jordi.pujol@urv.cat>, Universitat Rovira i Virgili.


# License

This work is distributed under [GPL license](http://www.gnu.org/copyleft/gpl.html).

```
    IMAS base code for the practical work.
    Copyright (C) 2014 DEIM - URV

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
```

JADE is also included in binary format (*.jar) in the `lib/` directory,
 with its corresponding license there.