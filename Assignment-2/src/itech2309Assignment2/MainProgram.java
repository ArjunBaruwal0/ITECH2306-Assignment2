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
   //  InspectionSchedule[] valuateHouse = new InspectionSchedule[];
    HouseInspection[] inspectionHouse = new HouseInspection[20];
    int times = 0, homeValuateIndex = 0, openHouse = 0;
    
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
			    			+ "\r\n Do you want to make it open for inspection(y/n): ");
				    
				    String houseValue = scan1.nextLine();
				    if(houseValue == "y") {
				    	String houseStatus = "Open for inspection";
				    	inspectionHouse[openHouse] = new HouseInspection(valuateHouse[houseToBeReady].getAddress(), valuateHouse[houseToBeReady].getSellerName(), valuateHouse[houseToBeReady].getSellerNumber(),  valuateHouse[houseToBeReady].getHouseValueRange(), houseStatus );
				    	openHouse++;
			
				    	valuateHouse = removeValuateHouse(valuateHouse, houseToBeReady);
				    	homeValuateIndex = homeValuateIndex - 1;
				    	
				    	System.out.println(inspectionHouse[openHouse--].getHouseStatus());
				    	
				    	// to get time for the inspection 
				    	
					    
				    }else {
				    	
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
			System.out.println("Please select the invalid value.\r\n");
			exitMenu = false;
		}
		
		
	}
 }

private static void showValuatedHouse(HouseValuation[] valuateHouse, int homeValuateIndex) {
	 int houseNum;
	 System.out.println( "List of houses requiring photos:\r\n");
	 for(int i = 0; i < homeValuateIndex; i++) {
		 houseNum = i + 1;
			System.out.println("   " + houseNum +". "+  valuateHouse[i].getAddress());
		}
	 System.out.println("   0. to exit/return to main menu");
	
}

private static void showNewlyAddedHouse( House[] house, int times) {
     int houseNum;
	 System.out.println( "List of newly added houses:\r\n");
	 for(int i = 0; i < times; i++) {
		 houseNum = i + 1;
			System.out.println("   " + houseNum +". "+ house[i].getAddress());
		}
	 System.out.println("   0. to exit/return to main menu");
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

private static void timeSlotForHOuseInspection() {
	String day ;
	String time ; 		
	Scanner scan = new Scanner(System.in);
	System.out.println("Please choose the day to make it open for:\r\n"
			+ "1. Thursday\r\n "
			+ "2. Friday\r\n "
			+ "3. Saturday\r\n "
			+ "4. Go Back \r\n ");
	
	int choice = scan.nextInt();
	if(choice > 0 && choice < 5) {
		switch(choice) {
		case 1:
			System.out.println("Please choose the timeslot for inspection on Thursday:\r\n"
					+ "1. 2:00pm - 2:15pm\r\n "
					+ "2. 2:30pm - 2:45pm\r\n "
					+ "3. 3:00pm - 3:15pm\r\n "
					+ "4. 3:30pm - 3:45pm\r\n "
					+ "5. 4:00pm - 4:15pm\r\n "
					+ "6. 4:30pm - 4:45pm\r\n "
					+ "7. 5:00pm - 5:15pm\r\n "
					+ "8. 5:30pm - 5:45pm\r\n ");
			break;
		case 2:
			System.out.println("Please choose the timeslot for inspection on Friday:\r\n"
					+ "1. 2:00pm - 2:15pm\r\n "
					+ "2. 2:30pm - 2:45pm\r\n "
					+ "3. 3:00pm - 3:15pm\r\n "
					+ "4. 3:30pm - 3:45pm\r\n "
					+ "5. 4:00pm - 4:15pm\r\n "
					+ "6. 4:30pm - 4:45pm\r\n "
					+ "7. 5:00pm - 5:15pm\r\n "
					+ "8. 5:30pm - 5:45pm\r\n ");
			break;
		case 3:
			System.out.println("Please choose the timeslot for inspection on Saturday:\r\n"
					+ "1.  9:00am - 9:15am\r\n "
					+ "2.  9:30am - 9:45am\r\n "
					+ "3. 10:00am - 10:15am\r\n "
					+ "4. 10:30am - 10:45am\r\n "
					+ "5. 11:00am - 11:15am\r\n "
					+ "6. 11:30am - 11:45am\r\n "
					+ "7. 12:00pm - 12:15pm\r\n "
					+ "8. 12:30pm - 12:45pm\r\n ");
			break;
		case 4:
			
			break;
		default:
			System.out.println("Some error has occur! Please choose again.\r\n");
			
		}
		
	}else {
		System.out.println("Please select the invalid value.\r\n");
	}
	
}


}
