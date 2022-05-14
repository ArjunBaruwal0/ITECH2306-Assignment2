package itech2309Assignment2;



public class HouseInspection extends HouseValuation{
	String houseStatus;
	private InspectionSchedule[] inspectionSchedule;
	private int numberOfTimeSlot;
	int numberOfHouseInspection;
	int times;
	
	HouseInspection(String houseAddress, String houseOwnerName, long houseOwnerNumber, String priceRange, String houseStatus){
		super( houseAddress, houseOwnerName, houseOwnerNumber, priceRange);
		this.houseStatus = houseStatus;
		numberOfTimeSlot = 2;
		numberOfHouseInspection = 0;
		inspectionSchedule = new InspectionSchedule[numberOfTimeSlot];
		
	}
	
	public String getHouseStatus() {
		return houseStatus;
	}
	
	public int getNumberOfHouseInspection() {
		return numberOfHouseInspection;
	}
	
	public void setHouseStatus(String newStatus) {
		houseStatus = newStatus;
	}

	public boolean removeTimeSlot(InspectionSchedule whichTimeSlot, int index) {
		boolean success = false;
		if (whichTimeSlot != null)			
		{
			if (index < numberOfTimeSlot)	
			{
				InspectionSchedule[] houseCopy = new InspectionSchedule[numberOfTimeSlot];

			for (int i = 0, j = 0; i < numberOfTimeSlot; i++) {
			    if (i != index) {
			    	houseCopy[j++] = inspectionSchedule[i];
			    }
			    
			}
				
				success = true;	
			}else {								
				success = false;
			}
		}
	//	inspectionSchedule[] = houseCopy[];
		return success;
	}
	public boolean addTimeSlot(InspectionSchedule whichTimeSlot) {
      boolean success = false;		// We assume it will fail, but if we get to the place where we have achieved the adding, we will change it to true.
		
		if (whichTimeSlot != null)			// Check that an actual inspection schedule object was provided, rather than 'null' being provided.
		{
			if (numberOfHouseInspection < numberOfTimeSlot)		// Check that there is space in the array (the schedule) to add another movie.
			{
				inspectionSchedule[numberOfHouseInspection] = whichTimeSlot;		// Store the reference to the Movie object, into the array, in the next place (represented by numberOfMovies)
				numberOfHouseInspection++;					
				success = true;						// If we reach here, then we succeeded, so change success to be true (to be returned at the end of method).
			} else {								// This would happen if the array was full before we called this method.
				success = false;
			}
		}
		else {				// This will happen when 'null' was provided as the parameter value by the caller method.
			success = false;
		}
		
		return success;
	}
	
	public InspectionSchedule[] getTimeSlot()
	{
		return inspectionSchedule; 
	}
	
	public String toString() {
		String	description = " " + getAddress();
		
		for(int i = 0; i < numberOfHouseInspection; i++) {
			description += " / " + inspectionSchedule[i].toString();
		}
	
				return description;
	}
	
}
