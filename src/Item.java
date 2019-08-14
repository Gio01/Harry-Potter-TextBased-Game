
public class Item {

	private String name;
	private String type;
	private String description;
	private boolean isSpecial;
	
	public Item (String pName, String pType, String pDesc){
		name = pName; 
		type = pType;
		description = pDesc;
		setSpecial(false);
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isSpecial() {
		return isSpecial;
	}

	public void setSpecial(boolean isSpecial) {
		this.isSpecial = isSpecial;
	}

	public String toString() {
		return "Item: \n" + "\t  Name: " + getName() + "\n\t  Type: " + getType() +
					"\n\t  Description: " + getDescription();
	}

	
}
