 package itech2309Assignment2;

import java.util.Scanner;

public class MainProgram {

 public static void main(String[] args) {
		boolean exitProgram = false, exitSubMenu = false;
		//while(!exitProgram) {
	      System.out.println("            +------------------------+\r\n"
	                       	+"            | ABC Real Estate Agency |\r\n"
	                    	+"            +------------------------+\r\n"
	        	            +"              Welcome to the program!\r\n");
		
			runMenu();
			exitProgram = true;
		//}
		
		
		House house1 = new House("12 HMS Buffalo Avenue Edwardstown", "Arjun Baruwal", 4168116940L);
		
		House house2 = new House("12 HMS Buffalo Avenue Edwardstown", "Arjun Baruwal", 2535515132L);
		
		
	}
	
 public static void runMenu() {
    boolean	exitMenu = false, isNumber;
    House[] house = new House[15];
    int times = 0;
    
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
		switch(choice) {
		case 1:
			System.out.println("Please enter the full address of the house: ");
			String houseAddress = scan.nextLine();
			
			System.out.println( "Please enter seller/owner's full name: ");
			String sellerName = scan.nextLine();
			
			do {
			System.out.println( "What is a phone number for the organiser: ");
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
			for(int i = 0; i < times; i++) {
				System.out.println("Press " + i + " to valuate house at " + house[i].getAddress());
			}
			 int houseToValuate = scan.nextInt();
			 
			 
			 break;
		case 3:
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
			System.out.println("Please select the valid value.\r\n");
			exitMenu = false;
		}
		
		
	}
 }

}
