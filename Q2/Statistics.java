 //Course:         ITI 1121 A
 //Author:         Junhan Liu                                   
 //Assignment:     #1                                       
 //Student number: 7228243                                
 //Question:       2
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
 private int won;
 private int lost;
 private int[] prize;
 private int[] chosen;
 private int[] open;

 /**
  * Initializes the statistics.
  */
 public Statistics() {
  prize = new int[3];
  chosen = new int[3];
  open = new int[3];
 }

 /**
  * Updates statistics after one game.
  * 
  * @param door1
  *            the first door in the game
  * @param door2
  *            the second door in the game
  * @param door3
  *            the third door in the game
  */
 public void updateStatistics(Door door1, Door door2, Door door3) {
  oneDoor(door1, 0);
  oneDoor(door2, 1);
  oneDoor(door3, 2);
  numOfGame++;
 }

 /**
  * Updates statistics for one single door.
  * 
  * @param door
  *            the door for which statistics are updated
  * @param index
  *            index of that door (0, 1 or 2)
  */
 private void oneDoor(Door door, int index) {
  if (door.hasPrize()) {
   if (door.isChosen()) {
    lost++;
   } else {
    won++;
   }
   prize[index]++;
  }
  if (door.isChosen()) {
   chosen[index]++;
  } else if (door.isOpen()) {
   open[index]++;
  }
 }

 /**
  * @return Returns the complete statistics information
  */
 public String toString() {
  // REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION
  return String.format("Number of games played: %d\n"+
  "Staying strategy won %d games (%.0f%%)\n"+
  "Switching strategy won %d games (%.0f%%)\n"+
  " Selected doors:\n"+
  "     door 1: %d (%.0f%%)\n"+
  "     door 2: %d (%.0f%%)\n"+
  "     door 3: %d (%.0f%%)\n"+
  " Winning doors:\n"+
  "     door 1: %d (%.0f%%)\n"+
  "     door 2: %d (%.0f%%)\n"+
  "     door 3: %d (%.0f%%)\n"+
  " Open doors:\n"+
  "     door 1: %d (%.0f%%)\n"+
  "     door 2: %d (%.0f%%)\n"+
  "     door 3: %d (%.0f%%)\n", numOfGame,lost, lost*100.0/numOfGame,won,won*100.0/numOfGame, 
   chosen[0], chosen[0]*100.0/numOfGame,
   chosen[1], chosen[1]*100.0/numOfGame,
   chosen[2], chosen[2]*100.0/numOfGame,
   
   prize[0], prize[0]*100.0/numOfGame,
   prize[1], prize[1]*100.0/numOfGame,
   prize[2], prize[2]*100.0/numOfGame,
   
   open[0], open[0]*100.0/numOfGame,
   open[1], open[1]*100.0/numOfGame,
   open[2], open[2]*100.0/numOfGame
    );
 }

}
