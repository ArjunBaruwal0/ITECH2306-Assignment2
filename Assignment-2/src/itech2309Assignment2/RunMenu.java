package itech2309Assignment2;

import java.util.Scanner;

public class RunMenu{
	/*
	 *  method that run the program which is called in main method
	 */
	
	public void stillRunning(){
		/*
		 * Global Variable declaration for whole program
		 */
		boolean exitMenu = false;
	    House[] house = new House[20];      // class for house
	    Owner[] owner = new Owner[20];      //class for owner
	    HouseValuation[] valuateHouse = new HouseValuation[20];   // subclass of house after valuation
	    InspectionSchedule[] inspectionSchedule = new InspectionSchedule[20];  // class of house inspection schedule
	    HouseInspection[] inspectionHouse = new HouseInspection[20];          //subclass of house valuation after photo and inspection time is added
	    ContractedHouse[] contractedHouse = new ContractedHouse[20];          // class for contracted house
	    Buyer[] buyer = new Buyer[20];
	    int times = 0, homeValuateIndex = 0, openHouse = 0, inspectionNumber = 0, ownerNumber = 0, buyerNumber = 0, contractedHouseNumber = 0;
	   
	    while(!exitMenu) {
	    	Scanner scan = new Scanner(System.in);
	    	int choice = showMainMenu();
	    
	if(choice>0 && choice<9) {
	boolean exitSubMenu = false;

	switch(choice) {
	case 1: // to add house/owner 
	do {
	System.out.println(" What you want to add: \r\n"
	+ " 1. House\r\n"
	+ " 2. owner\r\n"
	+ " 0. exit to main menu");
	
	int choiceToAdd = scan.nextInt();
	

	if(choiceToAdd == 1) {
	house[times] = addHouse(house, times);
	times++;

	// check if owner class is null or not if yes then add new owner
	if(owner[0] != null) {
	System.out.println(" Now select house owner: \r\n");
	
	    for(int i = 0; i < ownerNumber; i++) {                                // for loop to get register owner
	        System.out.println((i + 1) + ". " + owner[i].getSellerName());
	     }
	    
	    //option to add new owner
	    System.out.println(ownerNumber+1 +". add new owner");             
	    int ownerToAdd = scan.nextInt();
	    
	    if(ownerToAdd > 0 && ownerToAdd <= ownerNumber) {
	            house[times-1].addOwner(owner[ownerToAdd -1]);
	    }else if(ownerToAdd == ownerNumber+1) {
	           owner[ownerNumber] =  addOwner(owner, ownerNumber);
	           house[times-1].addOwner(owner[ownerNumber]);
	           ownerNumber++;
	    }else {
	            System.out.println(" Invalid selection: \r\n");
	    }
	   
	   
	   }else {     // add new owner
	       owner[ownerNumber] =  addOwner(owner, ownerNumber);
	       house[times-1].addOwner(owner[ownerNumber]);
	       ownerNumber++;
	   }

	// to add owner only when user select option 2 from sub-menu
	}else if(choiceToAdd == 2) {
	        owner[ownerNumber] =  addOwner(owner, ownerNumber);
	        ownerNumber++;
	}else if(choiceToAdd == 0) {  //exit option
		exitSubMenu = true;
		
	}else {   // if user enter more then option given
		System.out.println(" Invalid selection: \r\n");
	}
	}while(!exitSubMenu);

	break;
	
	case 2:  //for list of newly added house and add price range for those house 

while(!exitSubMenu ) {
	if(house[0] != null) {
	
	   int houseToValuate = showNewlyAddedHouse( house, times);    // show the list and get user selection
	   if(houseToValuate <= times && houseToValuate > 0 ) {        
		   
		   houseToValuate = houseToValuate - 1;
	    
	       System.out.println("\r\nEnter the price/value range for the house($xxxx - $xxxx):");
	       String houseValue = scan.nextLine();
	   
	   
	       valuateHouse[homeValuateIndex] = new HouseValuation(house[houseToValuate].getAddress(), houseValue );
	       homeValuateIndex++;
	   
	       house=removeHouse(house, houseToValuate);         // method to remove house from House class 
	       times = times - 1;
	 
	   }else if (houseToValuate == 0){          
	          exitSubMenu = true;
	   
	   }else {
	       System.out.println("\r\nInvalid entry can you please enter again. ");
	       exitSubMenu = false;
	   }
	}else {
		 System.out.println("\r\n Sorry! no new house has been added recently. \r\n");
	       exitSubMenu = true;
	}
	}

	break;
	
	case 3: // list of houses to be photographed and make it open for inspection

	while(!exitSubMenu) {
	 if(valuateHouse[0] != null) {
		 
	   int houseToBeReady =  showValuatedHouse(valuateHouse, homeValuateIndex);
	   if( houseToBeReady <= homeValuateIndex && houseToBeReady > 0 ) {
	    houseToBeReady = houseToBeReady - 1;
	   //option to add photo then move it to house inspection
	    
	    
	    
	    
	    System.out.println(  "House at " + valuateHouse[houseToBeReady].getAddress()
	    + "\r\n Do you want to make it open for inspection(y/n):");
	   
	   String houseValue = scan.nextLine();
	   String yesString = "y";
	   if(houseValue.equalsIgnoreCase(yesString)) {
	   
	    String houseStatus = "Open for inspection";
	    inspectionHouse[openHouse] = new HouseInspection(valuateHouse[houseToBeReady].getAddress(),  valuateHouse[houseToBeReady].getHouseValueRange(), houseStatus);
	   
	   
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
	    if(!(inspectionSchedule[inspectionNumber].checkTimeSlot())) {
	    boolean addHouseToSchedule = inspectionSchedule[inspectionNumber].addHouseInspection(inspectionHouse[putInspectionSchedule]);
	    }else {
	    	System.out.println("invalid selection.");
	    	exitSubMenu = false;
	    }
	    inspectionNumber++;
	   
	    System.out.println("Do you want to add next schedule for this house (y/n):");
	    askUser = scan.nextLine();
	   
	    }while(askUser.equalsIgnoreCase(yesString) && check);
	   
	   }
	 
	   }else if (houseToBeReady == 0){          
	    exitSubMenu = true;
	   
	   }else {
	    System.out.println("Invalid entry can you please enter again. ");
	    exitSubMenu = false;
	   }
	   
		}else {
			 System.out.println("\r\n Sorry! Houses have not been valuated yet! \r\n");
		       exitSubMenu = true;
		}
	}

	break;
	
	case 4:  // list of house for inspection from main menu
	while(!exitSubMenu) { 
		if(inspectionHouse[0] != null) {
	int bookHouseInspection = showListOfHouseOpenForInspection(inspectionHouse, openHouse);

	if(bookHouseInspection <= openHouse) {


	System.out.println("\r\n Select the number in reference to what you wnat to do for house at: ");
	System.out.println(inspectionHouse[bookHouseInspection-1].toString() + "\r\n");
	System.out.println(" 1. Cancel inspection schedule \r\n"
	+ " 2. Book for inspection \r\n"
	+ " 3. Make an offer \r\n"
	+ " 0. Exit/return to main menu \r\n");

	int userChoice = scan.nextInt();

	if(userChoice <4 && userChoice> 0) {
	bookHouseInspection -= 1;
	switch(userChoice) {
	case 1:  // cancel schedule for a house from sub-menu
	inspectionSchedule = changeInspectionSchedule(inspectionSchedule, inspectionHouse, bookHouseInspection, openHouse); //to cancel inspection schedule
	break;
	
	case 2:  // book inspection schedule for a house from sub-menu

	buyer[buyerNumber] = addNewBuyer(buyer, inspectionHouse, inspectionSchedule, bookHouseInspection, buyerNumber);
	boolean check = inspectionHouse[bookHouseInspection].addBuyer(buyer[buyerNumber]);
	buyerNumber++;
	inspectionNumber++;
	
	if(check) {
		System.out.println("You have been added to the inspection.");
	}
	else {
		System.out.println("Some error has occur. Please try again!");
	}
	break;
	
	case 3:  // make offer to inspected house
	Buyer[] specificBuyer= inspectionHouse[bookHouseInspection].getBuyer();
	if(specificBuyer != null) {
	System.out.println( "House at " + inspectionHouse[bookHouseInspection].getAddress()
	+"\r\n Choose your name to make an offer:");

	for(int i = 0; i < inspectionHouse[bookHouseInspection].getBuyerNumber(); i++) {
	System.out.println((i+1) + ". " +specificBuyer[i].getBuyerName() + "\r\n");
	}
	int potentialBuyer = scan.nextInt();
	System.out.println("Hello! " + specificBuyer[potentialBuyer-1].getBuyerName()
	+". House at " + inspectionHouse[bookHouseInspection].getAddress() + " has an price range of " + inspectionHouse[bookHouseInspection].getHouseValueRange());

	System.out.println("Please give your price for this house: ");
	int priceOffer = scan.nextInt();
	boolean addBuyerPrice = specificBuyer[potentialBuyer-1].setBuyerPrice(priceOffer);
	if(addBuyerPrice) 
		System.out.println("Price has been added. Thank you! Please wait for owner response.\r\n");
	     exitSubMenu = true;
	}
	else {
	System.out.println("Price has not been added. Please try again!\r\n");
	exitSubMenu = false;
	}

	break;
	case 4:
	exitSubMenu = true;
	break;
	default:
	System.out.println("Some error has occur! Please try again.\r\n");

	}

	}else if(userChoice == 0) {
	exitSubMenu = true;
	}else {
	System.out.println("Please select the valid value.\r\n");
	exitSubMenu = false;
	}
	}else {
	System.out.println("Please select valid value.");
	}
	}else {
			System.out.println("Hpuses have not been open for inspection yet!");
		}
	}

	break;
	
	
	case 5: //view current offer made by buyer's from main menu
	while(!exitSubMenu) {
	if(buyer[0] != null) {
	int checkHouseOffer = showListOfhouse(inspectionHouse, openHouse);
	if(checkHouseOffer <= openHouse && checkHouseOffer > 0) {
		checkHouseOffer -= 1;
	Buyer[] specificBuyer= inspectionHouse[checkHouseOffer].getBuyer();
	 for(int i = 0; i < inspectionHouse[checkHouseOffer].getBuyerNumber(); i++) {
	 if(specificBuyer[i].getBuyerPrice() != 0 ) {
	System.out.println((i+1) + ". " +specificBuyer[i].getBuyerName() + "|| Price Offered: $" + specificBuyer[i].getBuyerPrice() );
	 }
	 System.out.println("0. Exit to main menu ");
	 System.out.println("Select number to select the offer or to exit :");
	 int userExit = scan.nextInt();
	 if(userExit > 0  && userExit <= inspectionHouse[checkHouseOffer].getBuyerNumber()) {
		 System.out.println("What you want to do: \r\n"
		 		+ "1. Accept Offer \r\n"
		 		+ "2. Reject Offer \r\n"
		 		+ "0. Exit to main menu ");
		 int ownerDecision = scan.nextInt();
		 if(ownerDecision == 1) {
			 contractedHouse[contractedHouseNumber] = new ContractedHouse(inspectionHouse[checkHouseOffer].getAddress(),
					 inspectionHouse[checkHouseOffer].getHouseValueRange(), "Contracted for sale ", specificBuyer[userExit - 1].getBuyerPrice(),
					 specificBuyer[userExit - 1], inspectionHouse[checkHouseOffer].getOwner());
			 contractedHouseNumber++;
			 System.out.println("Offer has been acepted.");
			   //remove house from house inspection
		 }else if(ownerDecision == 2) {
			 //remove from specific buyer
		 }
		 else if(ownerDecision == 0) {
			 exitSubMenu = true;
		 }
		 else {
			 System.out.println("Invalid input! Please try again.");
		 }
		 
	 
	 
	 }else if(userExit == 0) {
	 exitSubMenu = true;
	 }else {
	 System.out.println("Invalid value.");
	 }
	}
	}else {
	System.out.println("Please select valid value.");
	}
	}else {
		System.out.println("No buyers has been found.");
		exitSubMenu = true;
	}
	}
	break;
	case 6: // to view list of houses contracted for sale
		int houseNum;
		System.out.println( "List of houses contracted for sale:\r\n");
		for(int i = 0; i < contractedHouseNumber; i++) {
		houseNum = i + 1;
		System.out.println("   " + houseNum +". "+ contractedHouse[i].toString() );
		}
		
		System.out.println(" Please press 0 to exit");
		int userDecision = scan.nextInt();
		if(userDecision == 0) {
			exitSubMenu = true;
		}else {
			System.out.println(" Invalid value! Please try again");
		}
		
	break;
	case 7:
		while(!exitSubMenu) {
			if(inspectionSchedule[0] != null) {
		       System.out.println( "Please enter the day you want to check all day inspection schedule:\r\n");
		       String day = scan.nextLine();
		       if(day != "") {
		    	   for(int i = 0; i < inspectionNumber-1; i++) {
		    		   System.out.println(inspectionSchedule[i].getInspectionSchedule(day));
		    	   }
		    	   exitSubMenu = true;
		       } else {
		    	   System.out.println( "Enter a day you want to check.");
		    	   exitSubMenu = false;
		       }
		
		      
			}else {
				System.out.println( "No record has been found.");
			}
		}
		break;
	case 8:
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

	private Buyer addNewBuyer(Buyer[] buyer, HouseInspection[] inspectionHouse, InspectionSchedule[] inspectionSchedule,
			int bookHouseInspection, int buyerNumber) {
		Scanner scan1 = new Scanner(System.in);
		long buyerPhoneNumber = 0;
	    
		InspectionSchedule[] timeSlot= inspectionHouse[bookHouseInspection].getTimeSlot();
		System.out.println("House at " + inspectionHouse[bookHouseInspection].getAddress() + " has following inspection time: \r\n");
		for(int i = 0; i < inspectionHouse[bookHouseInspection].getNumberOfHouseInspection(); i++) {
		System.out.println((i+1) + ". " +timeSlot[i].toString() + "\r\n");
		}

		String buyerName = null;
		

		System.out.println("Please select time slot which you want to book:");
		int chooseTimeSlot = scan1.nextInt();
		if( chooseTimeSlot < inspectionHouse[bookHouseInspection].getNumberOfHouseInspection()) {
		System.out.println("House at " + inspectionHouse[bookHouseInspection].getAddress() +  " schedule time on " + timeSlot[chooseTimeSlot - 1].toString());
		System.out.println("\r\n Please enter your details.");
		System.out.println( "Please enter your full name: \r\n");
		buyerName = scan1.nextLine();
		
		boolean isNumber = false; 
	    buyerPhoneNumber = 0;
		

		do {
		System.out.println( "Please enter your contact number: ");
		if(scan1.hasNextInt()) {
		    buyerPhoneNumber = scan1.nextInt();
		    isNumber = true;
		}
		else{
		System.out.println( " It is not a number.");
		isNumber = false;
		scan1.next();
		}

		}while(!(isNumber));
		}
		
		buyer[buyerNumber] = new Buyer(buyerName, buyerPhoneNumber);
		
		return buyer[buyerNumber];
	}

	private int showMainMenu() {
		Scanner scan = new Scanner(System.in);
		System.out.println("    Please select one of the following options:\r\n"
		+ "    Press 1 to add new house for sell\r\n"
		+ "    Press 2 for list of newly added houses for valuation\r\n"
		+ "    Press 3 for list of houses to be photographed\r\n"
		+ "    Press 4 for list of houses open for inspection\r\n"
		+ "    Press 5 for list of current offers of potential buyers for a house\r\n"
		+ "    Press 6 for list of contrated houses to be sold\r\n"
		+ "    Press 7 to check all the schedule for a day\r\n"
		+ "    Press 8 to end the program\r\n");

		int choice = scan.nextInt();
		scan.nextLine();
		
		return choice;
	}

	private static int showListOfhouse(HouseInspection[] inspectionHouse, int openHouse) {
		Scanner scan = new Scanner(System.in);
		int houseNum;

		System.out.println( "List of houses open for sale:\r\n");
		for(int i = 0; i < openHouse; i++) {
		houseNum = i + 1;
		System.out.println("   " + houseNum +". "+  inspectionHouse[i].getAddress());
		}
		int userHouseChoice = scan.nextInt();
		 
		return userHouseChoice;

	}

	private static House addHouse(House[] house, int times) {

	Scanner scan = new Scanner(System.in);
	System.out.println("Please enter the full address of the house: ");
	String houseAddress = scan.nextLine();
	house[times] = new House (houseAddress);
	
	return house[times];
	}

	private static Owner addOwner(Owner[] owner, int ownerNumber) {
	System.out.println( "Please enter seller/owner's full name: ");
	Scanner scan = new Scanner(System.in);
	String sellerName = scan .nextLine();

	int phoneNumber = 0;
	boolean isNumber;
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

	owner[ownerNumber] = new Owner(sellerName, phoneNumber);
	
	return owner[ownerNumber];
	}

	private static InspectionSchedule[] changeInspectionSchedule(InspectionSchedule[] inspectionSchedule, HouseInspection[] inspectionHouse, int inspectionNumber,  int openHouse) {

	int changeHouseInspection = inspectionNumber;


	InspectionSchedule[] timeSlot= inspectionHouse[changeHouseInspection].getTimeSlot();
	System.out.println(inspectionHouse[changeHouseInspection].getAddress() + "\r\n");
	for(int i = 0; i < inspectionHouse[changeHouseInspection].getNumberOfHouseInspection(); i++) {
	System.out.println((i+1) + ". " +timeSlot[i].toString() + "\r\n");
	}

	System.out.println("Please select time slot which you want to remove:");
	Scanner scan = new Scanner(System.in);
	int cancelTimeSlot = scan.nextInt();
	if( cancelTimeSlot <= inspectionHouse[changeHouseInspection].getNumberOfHouseInspection()) {
	System.out.println("House at " + inspectionHouse[changeHouseInspection].getAddress() +  " schedule time on " + timeSlot[cancelTimeSlot - 1].toString());
	inspectionSchedule = removeTimeSlot(timeSlot, cancelTimeSlot - 1 );
	   
	}
	else {
	System.out.println("Please select valid value.");
	}
	return inspectionSchedule;
	
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
	int userHouseChoice = scan.nextInt();
	
	return userHouseChoice;


	}

	private static int showValuatedHouse(HouseValuation[] valuateHouse, int homeValuateIndex) {
	int houseNum;
	Scanner scan = new Scanner(System.in);
	System.out.println( "List of houses requiring photos:\r\n");
	for(int i = 0; i < homeValuateIndex; i++) {
	houseNum = i + 1;
	System.out.println("   " + houseNum +". "+  valuateHouse[i].getAddress());
	}
	System.out.println("   0. Exit/return to main menu");
	
	   System.out.println( "Press any related number to do either add photo or return to main menu: \r\n");
	   int houseToBeReady = scan.nextInt();
	   
	   return houseToBeReady;

	}

	private static int showNewlyAddedHouse( House[] house, int times) {
	     int houseNum;
	     Scanner scan = new Scanner(System.in);
	System.out.println( "\r\nList of newly added houses:");
	for(int i = 0; i < times; i++) {
	houseNum = i + 1;
	System.out.println("   " + houseNum +". "+ house[i].getAddress());
	}
	System.out.println("   0. Exit/return to main menu");
	
	System.out.println( "\r\nPress any related number to do either vaulate or return to main menu: \r\n");
	   int houseToValuate = scan.nextInt();
	  
	   return houseToValuate;
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