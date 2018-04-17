 //Course:         ITI 1121 A
 //Author:         Junhan Liu                                   
 //Assignment:     #1                                       
 //Student number: 7228243                                
 //Question:       3 
import java.util.Random;

import javax.swing.JOptionPane;

/**
 * The class <b>MontyHall</b> simulates one or several games. Is uses three
 * <b>Door</b> objects to simulate the three doors. One game consists of the
 * following steps
 * <ol>
 * <li>Resets all three doors</li>
 * <li>Simulates the selection of one of the doors by the player</li>
 * <li>Simulates opening of an empty door by the host</li>
 * <li>provide the outcome for switching and not switching door</li>
 * </ol>
 * 
 * @author gvj (gvj@eecs.uottawa.ca)
 *
 */
public class MontyHall {

 // ADD YOUR INSTANCE VARIABLES HERE
 Door[] doors;

 /**
  * Initializes the three doors.
  */
 public MontyHall() {
  doors = new Door[3];
  String[] names = new String[]{"A","B","C"};
  for (int i = 0; i < doors.length; i++) {
   doors[i] = new Door(names[i]);
  }
 }

 /**
  * Runs a series of Monty Hall games and displays the resulting statistics
  * in a message dialog or on the standard output
  * 
  * @param numberOfGames
  *            the number of games to simulate
  * @param commandLine
  *            if true, sends the results to standard output, else uses a
  *            message dialog
  */
 public void runGames(int numberOfGames, boolean commandLine) {
  Statistics stats = new Statistics();
  for (int i = 0; i < numberOfGames; i++) {
   oneGame();
   stats.updateStatistics(doors[0], doors[1], doors[2]);
   for (int j = 0; j < doors.length; j++) {
    doors[j].reset();
   }
  }
  if (commandLine) {
   // System.out.println(stats.toString());
   System.out.println(stats.toCSV());
  } else {
   JOptionPane.showMessageDialog(null, stats.toCSV(), "Results", JOptionPane.INFORMATION_MESSAGE);
  }
 }

 /**
  * Simulates one Monty Hall game.
  * <ol>
  * <li>Resets all three doors</li>
  * <li>Simulates the selection of one of the doors by the player</li>
  * <li>Simulates opening of an empty door by the host</li>
  * <li>prints the outcome for switching and not switching door to standard
  * output</li>
  * </ol>
  */
 public void oneGame() {
  Door prize = pickADoor();
  prize.setPrize();
  Door selected = pickADoor();
  selected.choose();
  Door host = openOtherDoor(prize, selected);
  host.open();
 }

 /**
  * Simulates a random selection of one of the three doors.
  * 
  * @return the door randomly selected
  */
 private Door pickADoor() {
  Random rand = new Random();
  Door door = doors[rand.nextInt(doors.length)];
  return door;
 }

 /**
  * Simulates the opening of one of the other doors once the player selected
  * one. It should open a door chosen randomly among the ones that don't have
  * the prize and that are not selected by the player
  * 
  * @param prizeDoor
  *            the door that hides the prize
  * @param selectedDoor
  *            the door that was selected by the player
  * @return the door opened
  */
 private Door openOtherDoor(Door prizeDoor, Door selectedDoor) {
  for (Door door : doors) {
   if (door != prizeDoor && door != selectedDoor) {
    return door;
   }
  }
  // can't reach
  return null;
 }

 /**
  * The main method of this program. Examples of the execution of the program
  * from the command line:
  * 
  * <pre>
  * > java MontyHall 5
  * 
  * Number of games played: 5
  * Staying strategy won 2 games (40%)
  * Switching strategy won 3 games (60%)
  * Selected doors:
  *  door 1: 1 (20%)
  *  door 2: 3 (60%)
  *  door 3: 1 (20%)
  * Winning doors:
  *  door 1: 1 (20%)
  *  door 2: 1 (20%)
  *  door 3: 3 (60%)
  * Open doors:
  *  door 1: 2 (40%)
  *  door 2: 2 (40%)
  *  door 3: 1 (20%)
  * </pre>
  * 
  * @param args
  *            (optional) the number of games to simulate
  */
 public static void main(String[] args) {

  MontyHall montyHall;
  int numberOfGames;
  boolean commandLine = false;

  StudentInfo.display();

  if (args.length == 1) {
   numberOfGames = Integer.parseInt(args[0]);
   commandLine = true;
  } else {
   numberOfGames = Integer.parseInt(JOptionPane.showInputDialog("Input the number of games to play", "1000"));
  }

  montyHall = new MontyHall();
  montyHall.runGames(numberOfGames, commandLine);
 }

}