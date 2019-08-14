import java.util.ArrayList;
import java.util.Scanner;

public class Driver {

	public static Location myLocation;
	public static ContainerItem myInventory;
	public static ArrayList <HiddenRoom> hidden = new ArrayList <HiddenRoom> ();
	
	public static void main (String [ ] args){
		Location snapePotion = new Location ("SnapePotion", "You are in Snape's Potion's class now");
		Location forbiddenForest = new Location ("ForbiddenForest", "You are in the forbidden forest, be careful");
		Location quiddichField = new Location ("QuiddichField", "Let's play ball!");
		Location alexanders = new Location ("Alexanders", "Alexander has the wand for you!");
		Location dumbledoreOffice = new Location ("Dumbledore'sOffice", "The headmaster's office");
		Location gryffindor = new Location ("GryffindorCommonRoom", "Harry's home");
		Location hut = new Location ("Hagrid'sHut", "Find Hagrid");
		Location hall = new Location ("TheGreatHall", "Have a snack!");
		
		Item tongue = new Item ("ParselTongue", "Unlockable", "Opens Chamber of Secrets");
		tongue.setSpecial(true);
		HiddenRoom chamber = new HiddenRoom ("ChamberofSecrets", "Careful of the snake!", tongue);
		hidden.add(chamber);
		
		Item key = new Item("VKey","Unlockable","Opens door to Voldemort");
		key.setSpecial(true);
		HiddenRoom voldemort = new HiddenRoom("Voldemort'sCave", "Battle", key);
		hidden.add(voldemort);
		
		snapePotion.addDirection("North", gryffindor);
		snapePotion.addDirection("South", quiddichField);
		snapePotion.addDirection("East", hut);
		snapePotion.addDirection("West", dumbledoreOffice);
		
		forbiddenForest.addDirection("North", hut);
		forbiddenForest.addDirection("South", chamber);
		forbiddenForest.addDirection("East", null);
		forbiddenForest.addDirection("West", quiddichField);
		
		quiddichField.addDirection("North", snapePotion);
		quiddichField.addDirection("South", null);
		quiddichField.addDirection("East", forbiddenForest);
		quiddichField.addDirection("West", hall);
		
		alexanders.addDirection("North", null);
		alexanders.addDirection("South", null);
		alexanders.addDirection("East", dumbledoreOffice);
		alexanders.addDirection("West", null);
		
		dumbledoreOffice.addDirection("North", voldemort);
		dumbledoreOffice.addDirection("South", hall);
		dumbledoreOffice.addDirection("East", snapePotion);
		dumbledoreOffice.addDirection("West", alexanders);
		
		voldemort.addDirection("North", null);
		voldemort.addDirection("South", dumbledoreOffice);
		voldemort.addDirection("East", gryffindor);
		voldemort.addDirection("West", null);

		chamber.addDirection("North", forbiddenForest);
		chamber.addDirection("South", null);
		chamber.addDirection("East", null);
		chamber.addDirection("West", null);
		
		gryffindor.addDirection("North", null);
		gryffindor.addDirection("South", snapePotion);
		gryffindor.addDirection("East", voldemort);
		gryffindor.addDirection("West", null);
		
		hut.addDirection("North", null);
		hut.addDirection("South", forbiddenForest);
		hut.addDirection("East", null);
		hut.addDirection("West", snapePotion);
		
		hall.addDirection("North", dumbledoreOffice);
		hall.addDirection("South", null);
		hall.addDirection("East", quiddichField);
		hall.addDirection("West", null);

		myLocation = dumbledoreOffice;
		
		myInventory = new ContainerItem ("Backpack", "Storage", "You can store things inside");
		
		
		Item phoenix = new Item ("Fawkes", "Mystical Creature", "Red Bird");
		Item dumbledore = new Item ("Dumbledore", "Wizard", "Old man with a long white beard and glasses");
		Item elderWand = new Item ("ElderWand", "Wand", "Powerful wand");
		Item owl = new Item("Hedwig", "Mystical Creature", "White Owl");
		Item broom = new Item("Nimbus", "Almighty Broom", "Flying Broom");
		Item wands = new Item("Nameless", "Weapon", "Magical Stick");
		Item voldemortWizard = new Item("Voldemort", "Evil Wizard","Ugly Bald man");
		
		ContainerItem pensieve = new ContainerItem ("Pensieve", "Bowl of water", "Drop your memories here. ");
		Item tomMemories = new Item ("TomMemories", "Memories", "Tom as a little boy");
		pensieve.add(tomMemories);
		
		ContainerItem desk = new ContainerItem ("Desk", "desk", "Look further to see Dumbledore's secrets!");
		Item ring = new Item ("Ring", "Horcrux ", "Beware, this is dangerous");
		desk.add(ring);
		
		gryffindor.addItem(phoenix);
		dumbledoreOffice.addItem(dumbledore);
		snapePotion.addItem(elderWand);
		dumbledoreOffice.addItem(pensieve);
		hut.addItem(desk);
		hall.addItem(owl);
		forbiddenForest.addItem(tongue);
		quiddichField.addItem(broom);
		alexanders.addItem(wands);
		dumbledoreOffice.addItem(key);
		voldemort.addItem(voldemortWizard);
		
		
		while (true){
		System.out.println("Please enter a command: ");
		Scanner s = new Scanner(System.in);
		String userCommand = s.nextLine();
		String [] command = userCommand.split(" ");
		
		switch (command[0]) {
		case "quit" : {
			return; //takes you out of the main
		}
		case "look" : {
			System.out.println(myLocation.getDescription() + "\n Items: " + myLocation.printItemNames());
			break;
		}
		case "examine" :{
			if(command.length == 2) {
				if (myLocation.isThere(command [1])){
					String name = command[1];
					System.out.println(myLocation.getItem(name).getName());
					System.out.println(myLocation.getItem(name).getDescription());
				}
				else
					System.out.println("Sorry, that item does not exist!");
				break;
			}	
		}
		case "take" :{
			Item temp = null;
			if (command.length== 2){
				if(myLocation.isThere(command[1])){
					if(myLocation.getItem(command[1]) instanceof ContainerItem) {
						System.out.println("You can't take this item.");
					}
					else {
						temp = myLocation.removeReturn(command[1]);
						myInventory.add(temp);
						System.out.println("It has been added");
						if (temp.isSpecial()){
							for(HiddenRoom temp1 : hidden){
								if ((temp1.getMatchingObject()).equals(temp)){
									temp1.setState(1);
									System.out.println("You have unlocked: " + temp1.getName());
								}
							}
						}
					}
				}
				else 
					System.out.println ("That item does not exist!");
			}
			else if (command.length == 4){
				if(myLocation.isThere(command[3])){
					if(myLocation.getItem(command[3]) instanceof ContainerItem) {
						if(((ContainerItem)myLocation.getItem(command[3])).isThere(command[1])){
							temp = ((ContainerItem)myLocation.getItem(command[3])).removeReturn1(command[1]);
							myInventory.add(temp);
							System.out.println("It has been added");
							if (temp.isSpecial()){
								for(HiddenRoom temp1 : hidden){
									if ((temp1.getMatchingObject()).equals(temp)){
										temp1.setState(1);
										System.out.println("You have unlocked: " + temp1.getName());
									}
								}
							}
						}
						else { 
							System.out.println ("That Item does not exist! Please retype your command: ");
						}
					}
					
				}
			  else {
				  System.out.println("This Container does not exist!");
			  }
			}
	break;
	}
		case "drop" :{
			Item temp = null;
			if(command.length > 1) {
				if (myInventory.isThere(command [1])){	
					temp = myInventory.removeReturn1(command [1]);	
					myLocation.addItem(temp);
					System.out.println("Dropped");
				}
				else 
					System.out.println ("That does not exist!");
				break;
			}
			else {
				System.out.println("Nothing was inputed! Please type a valid input!");
				break;
			}
		}
		case "put" :{
			Item temp = null;
			if (command.length == 4){
				if(myInventory.isThere(command[1])){
					if(myLocation.isThere(command [3])){
						if(myLocation.getItem(command[3]) instanceof ContainerItem) {
							temp = (myInventory.removeReturn1(command[1]));
							ContainerItem i = ((ContainerItem)myLocation.getItem(command [3]));
							i.add(temp);
							System.out.println("It has been added");
						}
				}
				else {
					System.out.println("This Container does not exist!");
				}
			}
				else {
					System.out.println("This Item cannot be put here!");
				}
			}
			else{
				System.out.println("Error! Please retype your command: ");
		}
		break;
		}
		case "inventory" :{
			System.out.println(myInventory.printItemNames());
			break;
		}
		case "go" :{
			if(command.length > 1) {
				Location loc = myLocation.getLocation(command[1]);
				if ((command [1]).equals("North") || 
					(command [1]).equals("South") || 
					(command [1]).equals("East")  || 
					(command [1]).equals("West")){
					if (loc == null) {
						System.out.println("Sorry, there is nothing there, pick another direction!");
					}
					else {
						if ((loc instanceof HiddenRoom )){
							if (((HiddenRoom)loc).getState() == 0){
								System.out.println ("Sorry, that Location is locked");
							}
							else
								myLocation = loc;
						}
						else 
							myLocation = loc;
					}
				}
			
				else {
					System.out.println("Sorry, that is not a valid direction!");
					break;
				}
				break;
			}
			else {
				System.out.println("Nothing was inputed! Please type a valid input!");
				break;
			}
		}
		case "help" :{
			help(); 
			break;
		}
		default : {
			System.out.println("I don't know how to do that.");
			break;
		}
		};
		}
	}
	
	public static void help (){
		System.out.println("Here are all of the commands you can choose: ");
		System.out.println("Type 'help' to get the list of commands \n" 
							+ "Type 'look' to see what is around you \n"
							+ "Type 'examine' and the name of the item that you want to examine to get the name and description \n"
							+ "Type 'take' and the name of the item that you wish to take to get an item from your Location and into your Inventory \n"
							+ "Type 'take', the name of the item that you wish to take, 'from', and the name of the container that holds the item to get an item from your Location and into your Inventory \n"
							+ "Type 'put', the name of the item that you wish to drop, 'in', and the name of the container that you want to put your item in \n"
							+ "Type 'inventory' to get a list of what you have in your backpack \n"
							+ "Type 'drop' and the name of the item you want to drop to take something out of your inventory and drop it in your location \n"
							+ "Type 'go' and the name of the orientation you want to go to \n"
							+ "Type 'quit' to exit the game from where you are \n");
	}
}
