package com.company;
import java.util.Scanner;
import java.io.*;

public class Task01 {

    public static void main(String[] args) {

        String[] hotel = new String[12];
        String roomName;
        int roomNum;

        initialise(hotel);
        boolean runValue=true;

        while (runValue){

            mainDisplay(hotel);


            Scanner input = new Scanner(System.in);
            String optionValue;
            System.out.println("Enter your Option here: ");
            optionValue = input.next();

            switch (Character.toUpperCase(optionValue.charAt(0))) {
                case 'V':
                    viewAll(hotel);
                    break;

                case 'A':
                    addCustomer(hotel);
                    break;

                case 'E':
                    displayEmpty(hotel);
                    break;

                case 'D':
                    deleteCustomer(hotel);
                    break;

                case 'F':
                    findCabin(hotel);
                    break;

                case 'S':
                    storeData(hotel);
                    break;

                case 'L':
                    loadData(hotel);
                    break;

                case 'O':
                    orderName(hotel);
                    break;

            }

            System.out.println("\nEnter 'Y' to Run the program again or Enter 'Q' to Quit program ");
            if (input.next().equalsIgnoreCase("Y")){
                runValue=true;
            }
            else if (input.next().equalsIgnoreCase("Q")){
                runValue=false;
            }
            else{
                System.out.println("Invalid Input");
                runValue=true;
            }

        }
    }


////----METHODS--------------------------------------------------------------------

//INITIALISE--
    private static void initialise(String[] hotel) {
        for (int i = 0; i<hotel.length; i++){
            hotel[i]="Empty";
        }
    }


//MAIN DISPLAY METHOD--
    public static void mainDisplay(String[] hotel){
        System.out.println();
        System.out.println("Cruise Ship Boarding");
        System.out.println();
        System.out.println("Press 'A' for Add Customer to Cabin");
        System.out.println("Press 'V' for View All Cabins");
        System.out.println("Press 'E' for Display Empty Cabins");
        System.out.println("Press 'D' for Delete Customer from Cabin");
        System.out.println("Press 'F' for Find cabin from Customer Name");
        System.out.println("Press 'S' for Store program data into file");
        System.out.println("Press 'L' for Load program data from file");
        System.out.println("Press 'O' for View passengers Ordered alphabetically by name");
        System.out.println();
    }

//VIEW ALL METHOD--
    public static void viewAll(String[] hotel){
        for (int i = 0; i < hotel.length; i++) {
            System.out.println("Room No : " + i + " occupied by " + hotel[i]);
        }
    }

//ADD CUSTOMER METHOD--
    public static void addCustomer(String[] hotel){
        Scanner input = new Scanner(System.in);
        String roomName;
        int roomNum = 0;

        System.out.println("Enter Cabin number 0 to 11 : ");
        roomNum= input.nextInt();
        System.out.println("Enter Customer name for room No " +roomNum+ " : ");
        roomName = input.next();
        hotel[roomNum] = roomName;
        System.out.println("\n"+roomName + " added to Cabin no : " + roomNum);

    }

// DISPLAY EMPTY CABINS METHOD--
    public static void displayEmpty(String[] hotel){
        for (int i = 0; i < hotel.length; i++ ){
            if (hotel[i].equals("Empty")){
                System.out.println("Room No : "+(i)+" is "+hotel[i]);
            }
        }
    }

// DELETE CUSTOMER METHOD--
    public static void deleteCustomer(String[] hotel){
        Scanner input = new Scanner(System.in);
        int roomNum = 0;

        while(true){

            System.out.println("\nEnter Cabin number to delete passenger : ");
            roomNum = input.nextInt();

            if (roomNum<12 && roomNum>-1){
                hotel[roomNum] = "Empty";
                System.out.println("Passenger removed from Cabin No : "+roomNum);
                break;
            }else{
                System.out.println("Invalid Input");
            }
        }
    }

// FIND CABIN METHOD--
    public static void findCabin(String[] hotel){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Customer Name to find the Cabin");
        String find = input.next();

        for (int i = 0; i < hotel.length; i++ ){
            if (hotel[i].equals(find)){
                System.out.println("\n"+find + " is in Cabin no : "+(i));
            }
        }

    }

//STORE DATA METHOD--
    public static void storeData(String[] hotel){
        try {
            FileWriter myWriter = new FileWriter("D:\\Studies\\IIT\\IIT SE Beng(Hons)\\Programming\\CW\\Task1\\src\\com\\company\\task1");
            for (int i = 0; i < hotel.length; i++) {
                myWriter.write("Room No : " + i + " occupied by " + hotel[i] + "\n");
            }

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        }
        catch (IOException e)
        {
            System.out.println("An error occurred.");
        }

    }

//LOAD DATA METHOD--
    public static void loadData(String[] hotel){
        try {
            File filelocation = new File("D:\\Studies\\IIT\\IIT SE Beng(Hons)\\Programming\\CW\\Task1\\src\\com\\company\\task1");
            Scanner fileReader = new Scanner(filelocation);
            while (fileReader.hasNextLine()){
                String data = fileReader.nextLine();
                System.out.println(data);
            }
            fileReader.close();
        } catch (FileNotFoundException e){
            System.out.println("An error occurred. ");
            e.printStackTrace();
        }

    }

// NAME BY ORDER METHOD--
    public static void orderName(String[] hotel){
        String temp;
        for (int i = 0; i < hotel.length; i++) {
            for (int j = i + 1; j < hotel.length; j++) {
                // comparing adjacent strings
                if (hotel[j].compareTo(hotel[i]) < 0) {
                    temp = hotel[i];
                    hotel[i] = hotel[j];
                    hotel[j] = temp;
                }
            }
            if (hotel[i].equals("Empty")){
                System.out.print("");
            }
            else {
                System.out.println(hotel[i]);
            }
        }
    }

}
