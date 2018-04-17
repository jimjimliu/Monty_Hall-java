 //Course:         ITI 1121 A
 //Author:         Junhan Liu                                   
 //Assignment:     #1                                       
 //Student number: 7228243                                
 //Question:       1 
import java.util.Random;

/**
 * The class <b>MontyHall</b> simulates one game. Is uses three <b>Door</b>
 * objects to simulate the three doors. One game consists of the following steps
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
   String[] names = new String[]{"A","B","C"};
  doors = new Door[3];
  for (int i = 0; i < doors.length; i++) {
   doors[i] = new Door(names[i]);
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
  // REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION
  Door prize = pickADoor();
  prize.setPrize();
  System.out.println("The prize was behind door " + prize.getName());
  Door selected = pickADoor();
  selected.choose();
  System.out.println("The player selected door " + selected.getName());
  Door host = openOtherDoor(prize, selected);
  System.out.println("The host opened door " + host.getName());
  if (selected == prize) {
   System.out.println("Switching strategy would have lost");
  } else {
   System.out.println("Switching strategy would have won");
  }

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
    door.open();
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
  * > java MontyHall
  * The prize was behind door B
  * The player selected door B
  * The host opened door C
  * Switching strategy would have lost
  * </pre>
  * 
  * <pre>
  * > java MontyHall
  * The prize was behind door B
  * The player selected door A
  * The host opened door C
  * Switching strategy would have won
  * </pre>
  * 
  * @param args
  *            ignored for now
  */
 public static void main(String[] args) {

  MontyHall montyHall;

  StudentInfo.display();
  montyHall = new MontyHall();
  montyHall.oneGame();
 }

}