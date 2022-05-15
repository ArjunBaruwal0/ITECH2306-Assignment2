package itech2309Assignment2testCases;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import itech2309Assignment2.House;
import itech2309Assignment2.HouseInspection;
import itech2309Assignment2.InspectionSchedule;
import itech2309Assignment2.Owner;

public class HouseTester {

	@Test
	public void makeHouseOwner(){
		
		Owner owner1 = new Owner("Arjun Baruwal", 4168116194L);
		House house1 = new House ("12 hms buffalo ave, edwardstown");
		House house2 = new House("15 hms buffalo ave, edwardstown");
		assertEquals(true, house1.addOwner(owner1));
		assertEquals("Arjun Baruwal", house1.getOwner().getSellerName());
		
		assertNull(house1.getOwner());
		
	}
	
	@Test
	public void getHouseScheduleForADay() {
		InspectionSchedule time1 = new InspectionSchedule("10:00am - 10:15am", "Thursday");
		HouseInspection home1 = new HouseInspection("12 hms buffalo ave, edwardstown", "1500-2000", "Open for Inspection");
		
		assertTrue(time1.addHouseInspection(home1));
		assertEquals("12 hms buffalo ave, edwardstown at:      10:00am - 10:15am: Thursday", time1.getInspectionSchedule("Thursday"));
		assertTrue(home1.addTimeSlot(time1));
		
		
	}
	
}
