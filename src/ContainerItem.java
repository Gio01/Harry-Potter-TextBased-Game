import java.util.ArrayList;

public class ContainerItem extends Item {
	private ArrayList <Item> container;
	
	public ContainerItem (String pName, String pType, String pDesc){
		super(pName, pType, pDesc);
		container = new ArrayList <Item> ();
	}
	
	public void add (Item pItem){
		container.add (pItem);
	}
	
	public Item removeReturn1 (String pName){
		Item temp1;
		for (Item temp: container){
			if ((temp.getName()).equals(pName)){
				temp1 = temp;
				container.remove(temp);
				return temp1;
			}
		}
		return null;
	}
	
	public Item removeReturn2 (int index){
		Item temp1;
		for (int i = 0; i< container.size(); i++){
			if (i == index){
				temp1 = container.get(i);
				container.remove(container.get(i));
				return temp1;
			}
		}
		return null;
	}
	
	public int count (){
		return (container.size () - 1); 
	}

	public boolean isThere (String pName){
		for (Item temp: container){
			if ((temp.getName()).equals(pName)){
				return true;
			}
		}
		return false;
	}
	
	public String getDescription (){
		return super.getDescription () + printItemNames ();
	}
	
	public String printItemNames (){
		String names = "";
		for (Item temp : container){
			names = names + temp.getName () + "\n"; 
		}
		return names;
	}

}
