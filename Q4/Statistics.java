 //Course:         ITI 1121 A
 //Author:         Junhan Liu                                   
 //Assignment:     #1                                       
 //Student number: 7228243                                
 //Question:       4
/**
 * The class <b>Statistics</b> accumulates information about a series of games:
 * <ol>
 * <li>Number of game played</li>
 * <li>Number of times the switching strategy was successful</li>
 * <li>Number of times the switching strategy was not successful</li>
 * <li>Number of times each door has the prize behind it</li>
 * <li>Number of times each door was chosen by the player</li>
 * <li>Number of times each door was open by the host</li>
 * </ol>
 *
 * @author gvj (gvj@eecs.uottawa.ca)
 *
 */
public class Statistics {

 // ADD HERE YOUR MEMBER VARIABLES
 private int numOfGame;
 private int numOfDoors;
 private int won;
 private int lost;
 private int[] prize;
 private int[] chosen;
 private int[] open;

 /**
  * Initializes the statistics.
  * 
  * @param numberOfDoors
  *            the number of doors used
  */
 public Statistics(int numberOfDoors) {
  this.numOfDoors = numberOfDoors;
  prize = new int[numberOfDoors];
  chosen = new int[numberOfDoors];
  open = new int[numberOfDoors];
 }

 /**
  * Updates statistics after one game.
  * 
  * @param doorArry
  *            the list of Doors used during the game
  */
 public void updateStatistics(Door[] doorArray) {
  for (int i = 0; i < doorArray.length; i++) {
   Door door = doorArray[i];
   if (door.hasPrize()) {
    if (door.isChosen()) {
     lost++;
    } else {
     won++;
    }
    prize[i]++;
   }
   if (door.isChosen()) {
    chosen[i]++;
   } else if (door.isOpen()) {
    open[i]++;
   }
  }
  numOfGame++;
 }

 /**
  * @return Returns the complete statistics information
  */
 public String toString() {
  String str = String.format("Number of games played: %d\n"+
     "Staying strategy won %d games (%.0f%%)\n"+
     "Switching strategy won %d games (%.0f%%)\n"+
     " Selected doors:\n", numOfGame,lost, lost*100.0/numOfGame,won,won*100.0/numOfGame);
  for(int i=0; i<numOfDoors; i++){
   str+=String.format("     door %d: %d (%.0f%%)\n",i+1, chosen[i], chosen[i]*100.0/numOfGame);
  }
  str+= " Winning doors:\n";
  for(int i=0; i<numOfDoors; i++){
   str+=String.format("     door %d: %d (%.0f%%)\n",i+1, prize[i], prize[i]*100.0/numOfGame);
  }
  str+= " Open doors:\n";
  for(int i=0; i<numOfDoors; i++){
   str+=String.format("     door %d: %d (%.0f%%)\n",i+1, open[i], open[i]*100.0/(numOfGame*(numOfDoors-2)));
  }
  return str;
 }

 /**
  * @return Returns the complete statistics information in CSV format
  */
 public String toCSV() {
  String str = String
    .format("Number of games,%d\n" + "Number of doors,%d\n" + ",Win,Loss\n" + "Staying strategy,%d,%d\n"
      + "Switching strategy,%d,%d\n" + ",Selected doors,Winning doors,Open doors\n",

      numOfGame, numOfDoors, lost, won, won, lost);
  for (int i = 0; i < numOfDoors; i++) {
   str += String.format("Door %d,%d,%d,%d\n", i + 1, chosen[i], prize[i], open[i]);
  }
  return str;
 }

}