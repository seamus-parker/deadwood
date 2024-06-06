github repo link https://github.com/seamus-parker/deadwood
# deadwood
To run the program, first compile all java files ("javac filename.java"), then to run the game use "java Deadwood.java x", where x is the desired number of players.
The program takes a command line input for the number of players.
After taking player names for input and setting up the game the game selects a random starting player
The program displays player attributes and the actions that it is possible to take.
The player may input the commands until they either choose to end the turn or run out of possible actions
Aside from the players actions there are inputs that can be given to recive information

Player action commands (case sensetive)
1. "move"
2. "work" (take role)
3. "act"
4. "rehearse"
5. "upgrade"

   
List of additional commands (case sensative)
1. "upgrade menu", displays ranks and thier prices
2. "end turn", ends the current players turn (Players turn ends automatically when they have no more actions as well)
3. "locations" provides name and location for all players and identifies the active player
4. "where" provides details on the current set the active player is located
5. "info" provides information on that active player (currency, rank, etc.)
6. "end game", ends the game immediately
In certain cases such as selecting move location and desired role, you must type exactly what is written (e.g. "Crusty Prospector", "Train Station")


GUI update

Program is complied and ran the similar to before, command line arguments are no longer necassary however.
When ran the player is asked how many players. if the number of players is outside the 2-8 range the program prompts again
(inputing letters will cause an error) 
Each player can input the name of thier choice (can be letters and/or numbers) then the game begins.
Once the game begins the active players legal actions are availible in the button menu
Move activates the momvent buttons (located on the name of the locations)
act and rehease preform thier respective task
work is used to tale a role, after clicking work the menu changes to buttons of all eligble roles to the player
upgrading is done only in the casting office, and while upgrading the menu shows buttons for each rank you can afford 
(and the currency you would be using).
