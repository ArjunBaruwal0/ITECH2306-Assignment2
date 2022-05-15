package itech2309Assignment2;

public class Buyer {
public String buyerName; // Describes the name of the house owner name
public long    buyerNumber; // Describes the name of the house owner number
private int buyerPrice;
// House inspection
//private HouseInspection[] inspectionSchedule;
public Buyer(String buyerName, long buyerNumber) {

this.buyerName = buyerName;
this.buyerNumber = buyerNumber;

}


public int getBuyerPrice() {
return buyerPrice;
}

public boolean setBuyerPrice(int newPrice) {
  boolean success = false;
	if(newPrice > 0) {
	buyerPrice = newPrice;
	success = true;
	}else {
		success = false;
	}
	
	return success;
}

public String getBuyerName() {
return buyerName;
}


public long getBuyerNumber() {
return buyerNumber;
}

public void setBuyerNumber(long newNumber) {
buyerNumber = newNumber;
}

public void setBuyerName(String newName) {
buyerName = newName;
}



}