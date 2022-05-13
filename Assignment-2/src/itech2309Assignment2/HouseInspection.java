package itech2309Assignment2;

public class HouseInspection extends HouseValuation{
	String houseStatus;
	
	HouseInspection(String houseAddress, String houseOwnerName, long houseOwnerNumber, String priceRange, String houseStatus){
		super( houseAddress, houseOwnerName, houseOwnerNumber, priceRange);
		this.houseStatus = houseStatus;
	}
	
	public String getHouseStatus() {
		return houseStatus;
	}
	public void setHouseStatus(String newStatus) {
		houseStatus = newStatus;
	}

}
