package itech2309Assignment2;



public class House {
public String fullAddress; // Describes where the house is, e.g. street number, street name, suburb/town, etc

private Owner owner;


public House(String houseAddress) {
fullAddress = houseAddress;


}

// method to get house address
public String getAddress() {
return fullAddress;
}

public void setFullAddress(String newAddress) {
fullAddress = newAddress;
}

public boolean addOwner(Owner whichOwner) {
 boolean success = false;

if (whichOwner != null)
{
owner = whichOwner;
success = true;
}
else {
success = false;
}



return success;
}

public Owner getOwner() {
return owner;
}

}

