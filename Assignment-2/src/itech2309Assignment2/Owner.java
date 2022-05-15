package itech2309Assignment2;

public class Owner {
public String sellerName; // Describes the name of the house owner name
public long    sellerNumber; // Describes the name of the house owner number

// House inspection
//private HouseInspection[] inspectionSchedule;
public Owner(String houseOwnerName, long houseOwnerNumber) {

sellerName = houseOwnerName;
sellerNumber = houseOwnerNumber;

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

}