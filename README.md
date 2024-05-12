This repository contains a Java program designed to identify a winner from a list of participants in a tournament, based on criteria specified in the accompanying task description.
The program processes data from a text file and determines the winner(s) using the defined rules.

**Repository Contents:**
1. Player.java: An object class that encapsulates the player information extracted from the text file.
2. Tournament.java: Contains the main() method and serves as the entry point for the program.
3. WinnerFinder.java: An object class that includes helper methods for reading the file, storing data in an appropriate data structure, checking for errors during file reading, and printing results.
4. PlayerCompare.java: A comparator class that implements the Comparator interface to assist the TreeMap data structure in sorting players based on the given criteria.

**Data Structures:**
The program utilizes two main data structures to solve the task:

-**HashMap**: Primarily used during the scanning process. Players are stored in the map with their ID as the key.
This facilitates efficient retrieval (O(1) time complexity in the best-case scenario) when checking if a player is already saved in the map.
-**TreeMap**: Used after the scanning process for sorting. It transforms the unsorted contents of the HashMap into a sorted order with the assistance of the PlayerCompare.java Comparator class.
The TreeMap achieves a time complexity of O(log N) for most operations, including inserting and finding elements. In cases where there is a single winner, the lookup is O(1).
