package itech2309Assignment2;



public class HouseInspection extends HouseValuation{
String houseStatus;
private InspectionSchedule[] inspectionSchedule;
private Buyer[] buyer;
private int numberOfTimeSlot;
int numberOfHouseInspection, times, buyerNumber;


HouseInspection(String houseAddress, String priceRange, String houseStatus){
super( houseAddress, priceRange);
this.houseStatus = houseStatus;
numberOfTimeSlot = 2;
numberOfHouseInspection = 0;
inspectionSchedule = new InspectionSchedule[numberOfTimeSlot];
buyer = new Buyer[20];
buyerNumber = 0;

}




public String getHouseStatus() {
return houseStatus;
}



public void setHouseStatus(String newStatus) {
houseStatus = newStatus;
}



public boolean addTimeSlot(InspectionSchedule whichTimeSlot) {
      boolean success = false; // We assume it will fail, but if we get to the place where we have achieved the adding, we will change it to true.

if (whichTimeSlot != null) // Check that an actual inspection schedule object was provided, rather than 'null' being provided.
{
if (numberOfHouseInspection < numberOfTimeSlot) // Check that there is space in the array (the schedule) to add another movie.
{
inspectionSchedule[numberOfHouseInspection] = whichTimeSlot; // Store the reference to the Movie object, into the array, in the next place (represented by numberOfMovies)
numberOfHouseInspection++;
success = true; // If we reach here, then we succeeded, so change success to be true (to be returned at the end of method).
} else { // This would happen if the array was full before we called this method.
success = false;
}
}
else { // This will happen when 'null' was provided as the parameter value by the caller method.
success = false;
}

return success;
}


public InspectionSchedule[] getTimeSlot()
{
return inspectionSchedule;
}

public int getNumberOfHouseInspection() {
return numberOfHouseInspection;
}


public boolean addBuyer(Buyer whichBuyer) {
     boolean success = false;

if (whichBuyer != null)
{
if (buyerNumber < buyer.length)
{
buyer[buyerNumber] = whichBuyer;
buyerNumber++;
success = true;
} else {
success = false;
}
}
else {
success = false;
}

return success;
}

public Buyer[] getBuyer()
{
return buyer;
}

public int getBuyerNumber() {
return buyerNumber;
}


public String toString() {
String description = " " + getAddress();

for(int i = 0; i < numberOfHouseInspection; i++) {
description += " / " + inspectionSchedule[i].toString();
}

return description;
}

}