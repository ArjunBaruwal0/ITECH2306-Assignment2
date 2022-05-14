 package itech2309Assignment2;

import java.util.Scanner;

public class MainProgram {

 public static void main(String[] args) {
	      System.out.println("            +------------------------+\r\n"
	                       	+"            | ABC Real Estate Agency |\r\n"
	                    	+"            +------------------------+\r\n"
	        	            +"              Welcome to the program!\r\n");
		
			runMenu();
			
	}
	
 public static void runMenu() {
    boolean	exitMenu = false, isNumber;
    House[] house = new House[20];
    HouseValuation[] valuateHouse = new HouseValuation[20];
   InspectionSchedule[] inspectionSchedule = new InspectionSchedule[20];
    HouseInspection[] inspectionHouse = new HouseInspection[20];
    int times = 0, homeValuateIndex = 0, openHouse = 0, inspectionNumber = 0;
    
    while(!exitMenu) {
    int choice;
    long phoneNumber = 0;
    Scanner scan = new Scanner(System.in);
		System.out.println("    Please select one of the following options:\r\n"
				+ "    Press 1 to add new house for sell\r\n"
				+ "    Press 2 for list of newly added houses for valuation\r\n"
				+ "    Press 3 for list of houses to be photographed\r\n"
				+ "    Press 4 for list of houses open for inspection\r\n"
				+ "    Press 5 for list of current offers of potential buyers for a house\r\n"
				+ "    Press 6 for list of contrated houses to be sold\r\n"
				+ "    Press 7 to end the program\r\n");
		
		choice = scan.nextInt();
		scan.nextLine();
		
		if(choice>0 && choice<8) {
		 boolean	exitSubMenu = false;
		switch(choice) {
		case 1:
			System.out.println("Please enter the full address of the house: ");
			String houseAddress = scan.nextLine();
			
			System.out.println( "Please enter seller/owner's full name: ");
			String sellerName = scan.nextLine();
			
			do {
			System.out.println( "Please enter seller/owner's contact number: ");
			if(scan.hasNextInt()) {
			     phoneNumber = scan.nextInt();
			     isNumber = true;
			}
			else{
				System.out.println( " It is not a number.");
				 isNumber = false;
				scan.next();
			}
			
			}while(!(isNumber));
			
			house[times] = new House (houseAddress, sellerName, phoneNumber);
			times++;
			
			 break;
		case 2:
		
		while(!exitSubMenu) {
			showNewlyAddedHouse( house, times);
			
			System.out.println( "Press any related number to do either vaulate or return to main menu: \r\n");
		    int houseToValuate = scan.nextInt();
		    if(houseToValuate <= times && houseToValuate > 0 ) {
		    	houseToValuate = houseToValuate - 1;
		    	Scanner scan1 = new Scanner(System.in);
		    	System.out.println("\r\n Enter the price/value range for the house:");
			    
			    String houseValue = scan1.nextLine();
			    
			    
			    valuateHouse[homeValuateIndex] = new HouseValuation(house[houseToValuate].getAddress(), house[houseToValuate].getSellerName(), house[houseToValuate].getSellerNumber(), houseValue );
			    homeValuateIndex++;
			    
			    house=removeHouse(house, houseToValuate);
			    times = times - 1;
			  
		    }else if (houseToValuate == 0){           
		    	exitSubMenu = true;
		    	
		    }else {
		    	System.out.println("Invalid entry can you please enter again. ");
		    	exitSubMenu = false;
		    }
		    
		    
		}
			
		
			 
			 break;
		case 3:
			
			while(!exitSubMenu) {
				showValuatedHouse(valuateHouse, homeValuateIndex);
				
				System.out.println( "Press any related number to do either add photo or return to main menu: \r\n");
			    int houseToBeReady = scan.nextInt();
			    if( houseToBeReady <= homeValuateIndex && houseToBeReady > 0 ) {
			    	houseToBeReady = houseToBeReady - 1;
			    	
			    	Scanner scan1 = new Scanner(System.in);
			    	System.out.println(  "House at " + valuateHouse[houseToBeReady].getAddress()
			    			+ "\r\n Do you want to make it open for inspection(y/n):");
				    
				    String houseValue = scan1.nextLine();
				    String yesString = "y";
				    if(houseValue.equalsIgnoreCase(yesString)) {
				    	
				    	String houseStatus = "Open for inspection";
				    	inspectionHouse[openHouse] = new HouseInspection(valuateHouse[houseToBeReady].getAddress(), valuateHouse[houseToBeReady].getSellerName(), valuateHouse[houseToBeReady].getSellerNumber(),  valuateHouse[houseToBeReady].getHouseValueRange(), houseStatus);
				    	
				    	
				    	int putInspectionSchedule = openHouse;
				    	openHouse++;
			
				    	valuateHouse = removeValuateHouse(valuateHouse, houseToBeReady);
				    	homeValuateIndex = homeValuateIndex - 1;
				    	
				    	String askUser;
				    	boolean check;
				    	
				    	do {
				    	String[] dateTime = timeSlotForHOuseInspection();
				    	
				    	inspectionSchedule[inspectionNumber] =  new InspectionSchedule(dateTime[0], dateTime[1]);
				    	
				    	
				    	check = inspectionHouse[putInspectionSchedule].addTimeSlot(inspectionSchedule[inspectionNumber]);
				    	inspectionNumber++;
				    	
				    	System.out.println("Do you want to add next schedule for this house (y/n):");
				    	askUser = scan1.nextLine();
				    	
				    	}while(askUser.equalsIgnoreCase(yesString) && check);
					    
				    }
				  
			    }else if (houseToBeReady == 0){           
			    	exitSubMenu = true;
			    	
			    }else {
			    	System.out.println("Invalid entry can you please enter again. ");
			    	exitSubMenu = false;
			    }
			    
			    
			}
				
			 break;
		case 4:
			while(!exitSubMenu) {
			 int userChoice = showListOfHouseOpenForInspection(inspectionHouse, openHouse);
			 
			 switch(userChoice) {
			 case 1:
				 changeInspectionSchedule(inspectionSchedule, inspectionHouse, inspectionNumber, openHouse);
				 break;
			 case 2:
				 bookForInspection(inspectionHouse, openHouse);
				 break;
			 case 3:
				 exitSubMenu = true;
				 break;
			default:
				System.out.println("Some error has occur! Please try again.");
				
			 }
			 
			 
			 if(userChoice <= openHouse && userChoice> 0) {
					System.out.println("House at " + inspectionHouse[userChoice].getAddress());
					System.out.println(inspectionHouse[userChoice].toString());
					
					
				}else if(userChoice == 0) {
					exitSubMenu = true;
				}else {
					System.out.println("Please select the valid value.\r\n");
					exitSubMenu = false;
				}
			}
			 
			 break;
		case 5:
			
			 break;
		case 6:
			 break;
		case 7:
			System.out.println("Thank you for using our program!");
			exitMenu = true;
			
			 break;
		default:
			System.out.println("Some error occur during excuting the program! Please wait!\r\n");	
		}
		}
		else {
			System.out.println("Please select the valid value.\r\n");
			exitMenu = false;
		}
		
		
	}
 }

private static void bookForInspection(HouseInspection[] inspectionHouse, int openHouse) {
	Scanner scan = new Scanner(System.in);
	displayHouse(inspectionHouse, openHouse);
	
	System.out.println( "Please select house number to book inspection schedule: \r\n");
	int bookHouseInspection = scan.nextInt();
	
	if(bookHouseInspection <= openHouse) {
		bookHouseInspection -= 1;
		System.out.println(inspectionHouse[bookHouseInspection].toString());
		
	}else {
		System.out.println("Please select valid value.");
	}
	
}

private static void changeInspectionSchedule(InspectionSchedule[] inspectionSchedule, HouseInspection[] inspectionHouse, int inspectionNumber,  int openHouse) {
	Scanner scan = new Scanner(System.in);
	displayHouse(inspectionHouse, openHouse);
	
	 System.out.println( "Please select house number to change inspection schedule: \r\n");
		int changeHouseInspection = scan.nextInt();
		
		if(changeHouseInspection <= openHouse) {
			changeHouseInspection -= 1;
			InspectionSchedule[] timeSlot= inspectionHouse[changeHouseInspection].getTimeSlot();
			System.out.println(inspectionHouse[changeHouseInspection].getAddress() + "\r\n");
			for(int i = 0; i < inspectionHouse[changeHouseInspection].getNumberOfHouseInspection(); i++) {
				System.out.println((i+1) + ". " +timeSlot[i].toString() + "\r\n");	
			}
			
			System.out.println("Please select time slot which you want to remove:"
					+inspectionHouse[changeHouseInspection].getNumberOfHouseInspection());
			int cancelTimeSlot = scan.nextInt();
			if( cancelTimeSlot < inspectionHouse[changeHouseInspection].getNumberOfHouseInspection()) {
				System.out.println("House at " + inspectionHouse[changeHouseInspection].getAddress() +  " schedule time on " + timeSlot[cancelTimeSlot - 1].toString());
				timeSlot = removeTimeSlot(timeSlot, cancelTimeSlot - 1 );
				
				System.out.println("Do you want to add new time(y/n): ");
				   String addTimeSlot = scan.nextLine();
				    String yesString = "y";
				    if(addTimeSlot.equalsIgnoreCase(yesString)) {
				
			         	String[] dayTime = timeSlotForHOuseInspection();
			         	inspectionSchedule[inspectionNumber] =  new InspectionSchedule(dayTime[0], dayTime[1]);
		    	
		    	
		            	boolean check = inspectionHouse[changeHouseInspection].addTimeSlot(inspectionSchedule[inspectionNumber]);
		            	inspectionNumber++;
				    }
		    	
			}
			else {
				System.out.println("Please select valid value.");
			}
			
			
		}else {
			System.out.println("Please select valid value.");
		}
	 
	 
	 
	
}

private static InspectionSchedule[]  removeTimeSlot(InspectionSchedule[] timeSlot, int index) {
	InspectionSchedule[] newTimeSlot = new InspectionSchedule[timeSlot.length]; 

	for (int i = 0, j = 0; i < timeSlot.length; i++) {
	    if (i != index) {
	    	newTimeSlot[j++] = timeSlot[i];
	    }
	    
	}
	
	return newTimeSlot;
}
	



private static void displayHouse(HouseInspection[] inspectionHouse, int openHouse) {
	int houseNum;
	
	 System.out.println( "List of houses open for inspection:\r\n");
	 for(int i = 0; i < openHouse; i++) {
		 houseNum = i + 1;
			System.out.println("   " + houseNum +". "+  inspectionHouse[i].toString());
		}
	 

}

private static int showListOfHouseOpenForInspection(HouseInspection[] inspectionHouse, int openHouse) {
	
	
	Scanner scan = new Scanner(System.in);
	displayHouse(inspectionHouse, openHouse);
	 
	 System.out.println(" Select the number in reference to what you wnat to do: \r\n");
	 System.out.println(" 1. Cancel inspection schedule \r\n"
	 		+ " 2. Book for inspection \r\n"
	 		+ " 3. Make an offer \r\n"
	 		+ " 0. Exit/return to main menu \r\n");

	 
	 int userChoice = scan.nextInt();
	 return userChoice;
	
}

private static void showValuatedHouse(HouseValuation[] valuateHouse, int homeValuateIndex) {
	 int houseNum;
	 System.out.println( "List of houses requiring photos:\r\n");
	 for(int i = 0; i < homeValuateIndex; i++) {
		 houseNum = i + 1;
			System.out.println("   " + houseNum +". "+  valuateHouse[i].getAddress());
		}
	 System.out.println("   0. Exit/return to main menu");
	
}

private static void showNewlyAddedHouse( House[] house, int times) {
     int houseNum;
	 System.out.println( "List of newly added houses:\r\n");
	 for(int i = 0; i < times; i++) {
		 houseNum = i + 1;
			System.out.println("   " + houseNum +". "+ house[i].getAddress());
		}
	 System.out.println("   0. Exit/return to main menu");
}

private static House[] removeHouse(House[] house, int houseToValuate) {
	House[] houseCopy = new House[house.length];

	for (int i = 0, j = 0; i < house.length; i++) {
	    if (i != houseToValuate) {
	    	houseCopy[j++] = house[i];
	    }
	    
	}
	
	return houseCopy;
}

private static HouseValuation[] removeValuateHouse(HouseValuation[] valuateHouse, int houseToBeReady) {
	HouseValuation[] houseCopy = new HouseValuation[valuateHouse.length];

	for (int i = 0, j = 0; i < valuateHouse.length; i++) {
	    if (i != houseToBeReady) {
	    	houseCopy[j++] = valuateHouse[i];
	    }
	    
	}
	
	return houseCopy;
}

private static String[] timeSlotForHOuseInspection() {
	String day = "";
	String time = "" ; 	
	String[] dateTime;
	boolean exitTimeSelection = false;
	Scanner scan = new Scanner(System.in);
	do {
	System.out.println("Please choose the day to make it open for:\r\n"
			+ "1. Thursday\r\n "
			+ "2. Friday\r\n "
			+ "3. Saturday\r\n "
			+ "\r\n ");
	
	int choice = scan.nextInt();
	int timeSelected;
	if(choice > 0 && choice < 4) {
		switch(choice) {
		case 1:
			day = "Thursday";
			System.out.println("Please choose the timeslot for inspection on Thursday:\r\n"
					+ "1. 2:00pm - 2:15pm\r\n "
					+ "2. 2:30pm - 2:45pm\r\n "
					+ "3. 3:00pm - 3:15pm\r\n "
					+ "4. 3:30pm - 3:45pm\r\n "
					+ "5. 4:00pm - 4:15pm\r\n "
					+ "6. 4:30pm - 4:45pm\r\n "
					+ "7. 5:00pm - 5:15pm\r\n "
					+ "8. 5:30pm - 5:45pm\r\n ");
			timeSelected = scan.nextInt();
			switch(timeSelected) {
			case 1:
				time = "2:00pm - 2:15pm";
			break;
			case 2:
				time = "2:30pm - 2:45pm";
				break;
			case 3:
				time = "3:00pm - 3:15pm";
				break;
			case 4:
				time = "3:30pm - 3:45pm";
				break;
			case 5:
				time = "4:00pm - 4:15pm";
				break;
			case 6:
				time = "4:30pm - 4:45pm";
				break;
			case 7:
				time = "5:00pm - 5:15pm";
				break;
			case 8:
				time = "5:30pm - 5:45pm";
				break;
				default:
					System.out.println("\r\n Invalid selection. Please select one of the timeslot.");
			}
			break;
		case 2:
			day = "Friday";
			System.out.println("Please choose the timeslot for inspection on Friday:\r\n"
					+ "1. 2:00pm - 2:15pm\r\n "
					+ "2. 2:30pm - 2:45pm\r\n "
					+ "3. 3:00pm - 3:15pm\r\n "
					+ "4. 3:30pm - 3:45pm\r\n "
					+ "5. 4:00pm - 4:15pm\r\n "
					+ "6. 4:30pm - 4:45pm\r\n "
					+ "7. 5:00pm - 5:15pm\r\n "
					+ "8. 5:30pm - 5:45pm\r\n ");
			timeSelected = scan.nextInt();
			switch(timeSelected) {
			case 1:
				time = "2:00pm - 2:15pm";
			break;
			case 2:
				time = "2:30pm - 2:45pm";
				break;
			case 3:
				time = "3:00pm - 3:15pm";
				break;
			case 4:
				time = "3:30pm - 3:45pm";
				break;
			case 5:
				time = "4:00pm - 4:15pm";
				break;
			case 6:
				time = "4:30pm - 4:45pm";
				break;
			case 7:
				time = "5:00pm - 5:15pm";
				break;
			case 8:
				time = "5:30pm - 5:45pm";
				break;
				default:
					System.out.println("\r\n Invalid selection. Please select one of the timeslot.");
			}
			break;
		case 3:
			day = "Saturday";
			System.out.println("Please choose the timeslot for inspection on Saturday:\r\n"
					+ "1.  9:00am - 9:15am\r\n "
					+ "2.  9:30am - 9:45am\r\n "
					+ "3. 10:00am - 10:15am\r\n "
					+ "4. 10:30am - 10:45am\r\n "
					+ "5. 11:00am - 11:15am\r\n "
					+ "6. 11:30am - 11:45am\r\n "
					+ "7. 12:00pm - 12:15pm\r\n "
					+ "8. 12:30pm - 12:45pm\r\n ");
			
			timeSelected = scan.nextInt();
			switch(timeSelected) {
			case 1:
				time = "9:00am - 9:15am";
			break;
			case 2:
				time = "9:30am - 9:45am";
				break;
			case 3:
				time = "10:00am - 10:15am";
				break;
			case 4:
				time = "10:30am - 10:45am";
				break;
			case 5:
				time = "11:00am - 11:15am";
				break;
			case 6:
				time = "11:30am - 11:45am";
				break;
			case 7:
				time = "12:00am - 12:15am";
				break;
			case 8:
				time = "12:30am - 12:45am";
				break;
				default:
					System.out.println("\r\n Invalid selection. Please select one of the timeslot.");
			}
			break;
			
		default:
			System.out.println("Some error has occur! Please choose again.\r\n");
			
		}
		
	}else {
		System.out.println("Please select the invalid value.\r\n");
		exitTimeSelection = true;
	}
	dateTime = new String[3];
	dateTime[0] = day;
	dateTime[1] = time;
	}while(exitTimeSelection);
	
	
	return dateTime;
}


}
