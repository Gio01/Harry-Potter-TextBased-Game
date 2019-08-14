import java.util.ArrayList;
import java.util.HashMap;

public class Location {

	private String name;
	private String description;
	private ArrayList <Item> items;
	private HashMap <String, Location> myMap;

	public Location(String pName, String pDescription) {
		name = pName;
		description = pDescription;
		items = new ArrayList <Item>();
		myMap = new HashMap <String, Location> (); 
		
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void addItem (Item pItem){
		items.add(pItem);
	}

	public boolean isThere (String pName){
		for (Item temp : items){
			if (temp.getName().equals(pName))
				return true;
		}

		return false;
	}

	public Item getItem (String pName){
		for (Item temp : items){
			if (temp.getName().equals(pName))
				return temp;
		}
		return null;
	}

	public int count(){
		int count = items.size ();
		return count;
	}

	public Item removeReturn (String pName){
		Item temp= null;
		for (int j =0;  j < items.size() ; j++){
			if (items.get(j).getName().equals(pName))
				temp = items.get(j);
			items.remove(j);
		}
		return temp;
	}
	
	public String printItemNames (){
		String names =  "";
		for (Item temp : items){
			names = names +"\n" + temp.getName();
		}
		return names;
	}
	
	public void addDirection (String direction, Location loc){
		myMap.put(direction, loc);
	}
	
	public Location getLocation (String direction){
		return myMap.get(direction);
	}
}