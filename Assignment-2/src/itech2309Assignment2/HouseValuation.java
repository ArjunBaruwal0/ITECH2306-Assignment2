
package itech2309Assignment2;

public class HouseValuation extends House {

public String houseValueRange;

public HouseValuation(String houseAddress, String priceRange) {
super( houseAddress);
houseValueRange = priceRange;

}
public String getHouseValueRange() {
return houseValueRange;
}

public void setHouseValueRange(String newPriceRange) {
houseValueRange = newPriceRange;
}

}
