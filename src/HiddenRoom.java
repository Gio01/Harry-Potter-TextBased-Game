
public class HiddenRoom extends Location {

	private int state; // 1 means unlocked, 0 means locked
	private Item matchingObject;
	
	public HiddenRoom(String pName, String pDescription, Item matching) {
		super(pName, pDescription);
		state = 0;
		matchingObject = matching;
		
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Item getMatchingObject() {
		return matchingObject;
	} 

}
