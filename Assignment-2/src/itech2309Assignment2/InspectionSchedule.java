package itech2309Assignment2;

public class InspectionSchedule {
    String inspectionDay;
	String timeSlot;
	private HouseInspection[] houseInspection;
	int houseNumber;
    
    public InspectionSchedule(String timeSlot, String inspectionDay){
    	this.timeSlot = timeSlot;
    	this.inspectionDay = inspectionDay;
    	houseInspection = new HouseInspection[2];
    	houseNumber = 0;
    	
    }
    
    public String getTimeSlot() {
    	return timeSlot;
    }
    
    public void setTimeSlot(String newTimeSlot) {
    	timeSlot = newTimeSlot;
    }
    
    public String getInspectionDay() {
    	return inspectionDay;
    }
    
    public void setInspectionDay(String newInspectionDay) {
    	inspectionDay = newInspectionDay;
    }
    
    public boolean addHouseInspection(HouseInspection whichHouseInspection) {
    	boolean success = false;
    	if(whichHouseInspection != null) {
    		if(houseNumber < 2) {
    			success = true;
    			houseInspection[houseNumber] = whichHouseInspection;
    			houseNumber++;
    		}else {
    			success = false;
    		}
    		
    	}else {
    		success = false;
    	}
    	return success;
    }
    
    public HouseInspection[] getHouseInspection() {
    	return houseInspection;
    }
    public boolean checkTimeSlot() {
    	boolean check;
    	if(houseInspection[0] == houseInspection[1]) {
    		check = false;
    	}else {
    		check = true;
    	}
    	return check;
    }
    
    public String getInspectionSchedule(String whichDay) {
    	String description = "";
    	if(getInspectionDay().equalsIgnoreCase(whichDay) ) {
    		for(int i = 0; i < houseNumber; i++) {
    		description += houseInspection[i].getAddress() + " at:" + toString() + "\r\n";
    		}
    	}else {
    		description += "No date available."; 
    	}
    	return description;
    }
    
    public String toString() {
    	String inspectionTime = " " + timeSlot + ": " + inspectionDay;
    	return inspectionTime;
    }
}
