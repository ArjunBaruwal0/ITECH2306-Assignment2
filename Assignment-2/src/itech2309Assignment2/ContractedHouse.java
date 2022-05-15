package itech2309Assignment2;

public class ContractedHouse {
String houseAddress;
String priceRange;
String houseStatus;
int housePrice;
private Buyer buyer;
private Owner owner;


ContractedHouse(String houseAddress, String priceRange, String houseStatus, int housePrice, Buyer buyer, Owner owner){
this.houseAddress = houseAddress;
this.priceRange = priceRange;
this.houseStatus = houseStatus;
this.housePrice = housePrice;
this.buyer = buyer;
this.owner = owner;
}


public String getAddress() {
return houseAddress;
}

public String getPriceRange() {
return priceRange;
}

public String getHouseStatus() {
return houseStatus;
}

public int getHousePrice() {
return housePrice;
}


public String toString() {
String description = owner.getSellerName() + " has sold house at " + getAddress();
description += ". Contracted to " + buyer.getBuyerName() + " at the price of $" + getHousePrice();
return description;
}





}