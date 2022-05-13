package itech2309Assignment2;



public class House {
	public String fullAddress;			// Describes where the house is, e.g. street number, street name, suburb/town, etc
	public String sellerName;			// Describes the name of the house owner name
	public long    sellerNumber;			// Describes the name of the house owner number
	
	// House inspection 
	//private HouseInspection[] inspectionSchedule;	
	public House(String houseAddress, String houseOwnerName, long houseOwnerNumber) {
		fullAddress = houseAddress;
		sellerName = houseOwnerName;
		sellerNumber = houseOwnerNumber;
		
	}
	
	// method to get house address
	public String getAddress() {
		return fullAddress;
	}
	
	// method to get house owner/seller name
	public String getSellerName() {
		return sellerName;
	}
	
	// method to get house owner/seller number
		public long getSellerNumber() {
			return sellerNumber;
		}
		
		public void setSellerNumber(long newNumber) {
			sellerNumber = newNumber;
		}
		
		public void setSellerName(String newName) {
			sellerName = newName;
		}
		public void setFullAddress(String newAddress) {
			fullAddress = newAddress;
		}


}
