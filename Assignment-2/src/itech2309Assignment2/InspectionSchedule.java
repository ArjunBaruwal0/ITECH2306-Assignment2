package itech2309Assignment2;

public class InspectionSchedule {
    String inspectionDay;
	String timeSlot;
    
    InspectionSchedule(String timeSlot, String inspectionDay){
    	this.timeSlot = timeSlot;
    	this.inspectionDay = inspectionDay;
    }
    
    public String getTimeSlot() {
    	return timeSlot;
    }
    
    public void setTimeSlot(String newTimeSlot) {
    	timeSlot = newTimeSlot;
    }
    
    public String getInspectionDay() {
    	return timeSlot;
    }
    
    public void setInspectionDay(String newInspectionDay) {
    	inspectionDay = newInspectionDay;
    }
    
    public String toString() {
    	String inspectionTime = "      " + timeSlot + ": " + inspectionDay;
    	return inspectionTime;
    }
}
