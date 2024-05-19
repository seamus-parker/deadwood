public void gameFlow(int numberOfPlayers){
    boolean endOfTurn = false;
	int numberOfDays = 4;
	if (numberOfPlayers < 4){
	    numberOfDays = 3;
	}
	for (int i = 0; i < numberOfDays;i++){
	    for (int totalCardsOnSets = 10;;){
	        for (int j=1; j <= numberOfPlayers;){
	            for (endOfTurn = false;;){
	                //player of turn order j (maybe be worth it to have player id numbers hold thier turn order)
	                //display players attributs and location
	                //ask player wich action they would like to take
	                //(Move,Act,Accept role, upgrade, practice, or endTurn)
	                //(Move) display the sets that the player could move too (Possibly the roles and budget on those sets too)
	                //(Move) ask player wich they would like to move to, or allow them to return to the menu
	                //(Act) call on act function then display the sucess or failure message 
	            	//(Act) if player was successful check number of cardsOnSets and set j = number of players +1 if there are 1 cardsOnSets
	                //(act) if there is 1 card eject for this loop after setting j to number of players +1
	                //(accept role) Display all roles on the currect set and player rank
	                //(accept role) ask player wich role they would like and set it as thier role (or return to previous menu)
	                //(upgrade) check if player is in the casting office, display message directing them to the office if not 
	                //(upgrade) if player is in office display thier highest possible rank they can afford 
	                //(upgrade) ask player what currency they would like to use and check they have enough before setting new rank
	                //(practice) add practice chip to player and display message
	                //(endTurn) display message and set boolean end of turn to true
                    endOfTurn = true;
	            }
	        }
	        //display the total number of cards remaining on sets
	    }
	    //call on end day functions and display end of day messages
	}
	//call on game end functions to count scores and declare the winner
}

