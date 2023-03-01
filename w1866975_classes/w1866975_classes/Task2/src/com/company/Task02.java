package com.company;
import java.util.*;
import java.io.*;

public class Task02 {

    //MAIN CLASS--

    public static void main(String[] args) throws IOException{

        Scanner input = new Scanner(System.in);

        cabin[] cabins = new cabin[12];
        passenger[][] passengers = new passenger[12][3];

        for (int i = 0; i < cabins.length; i++) {
            cabins[i] = new cabin();
        }

        for (int x = 0; x < passengers.length; x++){
            for (int i = 0; i < passengers[x].length; i++){
                passengers[x][i] = new passenger();
            }
        }

        initialise(cabins, passengers);

        boolean eLoop = true;
        while (eLoop) {
            System.out.println("""

                    -Cruise Ship Boarding-
                    
                    Select a option from above List
                    
                    Press 'A' for - Add Customer to Cabin
                    Press 'V' for - View All Cabins
                    Press 'E' for - Display Empty Cabins
                    Press 'D' for - Delete Customer from Cabin
                    Press 'F' for - Find cabin from Customer Name
                    Press 'S' for - Store program data into file
                    Press 'L' for - Load program data from file
                    Press 'O' for - View passengers Ordered alphabetically by name
                    Press 'T' for - View Expenses
                    Press 'Q' for - Quit from program
                    

                    Please Enter Your option:\s""");

            switch (Character.toUpperCase(input.next().charAt(0))) {
                case 'A' -> addCustomer(cabins, passengers);
                case 'V' -> viewAll(cabins, passengers);
                case 'E' -> emptyCabins(cabins);
                case 'D' -> deleteCustomer(cabins, passengers);
                case 'F' -> findCustomer(cabins, passengers);
                case 'S' -> storeData(cabins, passengers);
                case 'L' -> loadData();
                case 'O' -> NameOrderAlphabetically(passengers);
                case 'T' -> expenses(cabins, passengers);
                case 'Q' -> eLoop = false;
                default -> System.out.println("Invalid Option selected");
            }
        }
    }

    //METHODS-----------------------------------------------------------------------------

    //INITIALISE METHOD--
    public static void initialise(cabin[] shipRef, passenger[][] cabinRef){
        for (int i = 0; i < shipRef.length; i++){
            shipRef[i].setcabinNum(40);
        }

        for (int i = 0; i < cabinRef.length; i++){
            for(int k = 0; k < cabinRef[i].length; k++){
                cabinRef[i][k].setFirstName("empty");
                cabinRef[i][k].setSurname("empty");
                cabinRef[i][k].setExpenses(0);
            }
        }
    }

    //ADD CUSTOMER METHOD
    public static void addCustomer(cabin[] cabins, passenger[][] passengers){

        int cabinNum;
        Scanner input = new Scanner(System.in);

        System.out.println("Enter a cabin number from 0 to 11");
        cabinNum = input.nextInt();
        if (cabinNum >= 0 && cabinNum < 12){

            cabins[cabinNum].setcabinNum(cabinNum);
            System.out.println("Which position do you wish to assign the passenger in cabin no " + cabinNum+ " (Select 0,1,2)");
            int passengerLocation = input.nextInt();
            if (passengerLocation >= 0 && passengerLocation < 3){


                if (passengers[cabinNum][passengerLocation].getFirstName().equals("empty")){
                    System.out.println("Enter Passenger " + passengerLocation + " First name:");
                    passengers[cabinNum][passengerLocation].setFirstName(input.next());
                    System.out.println("Enter Passenger " + passengerLocation + " Surname:");
                    passengers[cabinNum][passengerLocation].setSurname(input.next());

                    double expenses = 0;
                    do {
                        try {
                            System.out.println("Enter Passenger " + passengerLocation + " Expense:");
                            expenses = input.nextInt();
                            passengers[cabinNum][passengerLocation].setExpenses(expenses);

                        } catch (InputMismatchException e) {
                            System.out.print("Invalid Expense. "+ "\n");
                        }
                        input.nextLine();
                    } while (expenses <= 0);

                } else {
                    System.out.println(passengerLocation + " Cabin already received by  customer");
                }
            } else {
                System.out.println("Please enter cabin location 0, 1, 2");
                addCustomer(cabins, passengers);
            }

        }else {
            System.out.println("Maximum Cabins are 0 to 11, Please select a cabin from 0 to 11 \n");
            addCustomer(cabins, passengers);
        }

    }

    //VIEW ALL METHOD--
    public static void viewAll(cabin[] cabins, passenger[][] passengers){
        for (int i = 0; i < cabins.length; i++){
            if (cabins[i].getcabinNum() != 40){
                for (int k = 0; k < passengers[i].length; k++){
                    if (!"empty".equalsIgnoreCase(passengers[i][k].getFirstName())){
                        System.out.println("\n" + "Cabin number: " + cabins[i].getcabinNum() + " Passenger " + k + "\n");
                        System.out.println("Passenger Firstname: " + passengers[i][k].getFirstName() + "\n" + "Passenger Surname: " + passengers[i][k].getSurname() + "\n" + "Passenger Expenses: " + passengers[i][k].getExpenses());

                    }else {
                        System.out.println("\n"+"  Cabin number: " + i + " Passenger: " + k + " empty");
                    }
                }
            } else {
                System.out.println("\n" + "Cabin " + i + " is Empty");
            }
        }
    }


    //EMPTY CABINS METHOD--
    public static void emptyCabins(cabin[] cabins){

        for (int i = 0; i < cabins.length; i++){
            if (cabins[i].getcabinNum() == 40){
                System.out.println("cabin " + i + " is empty");
            }
        }
    }


    //DELETE CUSTOMER METHOD--
    public static void deleteCustomer(cabin[] cabins, passenger[][] passengers){

        Scanner input = new Scanner(System.in);
        System.out.println("Enter cabin number to delete(0-11)");
        int cabinNum = input.nextInt();
        cabins[cabinNum].setcabinNum(40);

        for (int i = 0; i < 3; i++){
            passengers[cabinNum][i].setFirstName("empty");
            passengers[cabinNum][i].setSurname("empty");
            passengers[cabinNum][i].setExpenses(0);
        }

        System.out.println("Cabin Passengers Successfully Deleted");
    }

    //FIND CUSTOMER METHOD--
    public static void findCustomer(cabin[] cabins, passenger[][] passengers){

        Scanner input = new Scanner(System.in);

        System.out.println("Enter Customer Name: ");
        String passengerName = input.next();

        boolean checkCustomerName = false;
        for(int i= 0; i < cabins.length; i++){
            for (int k = 0; k < passengers[i].length; k++){

                if (passengerName.equalsIgnoreCase(passengers[i][k].getFirstName()) && !checkCustomerName){
                    System.out.println(passengerName + " customer in cabin number " + i + ", " + k + " person" + "\n");
                    checkCustomerName = true;
                }
            }
        }
        if (!checkCustomerName){
            System.out.println(passengerName + " Name of the customer does not match with current Data.\n");
        }
    }

    //STORE DATA METHOD--
    public static void storeData(cabin[] cabins, passenger[][] passengers) throws IOException{

        try (PrintWriter out = new PrintWriter(new FileWriter("D:\\Studies\\IIT\\IIT SE Beng(Hons)\\Programming\\CW\\w1866975_classes\\Task2\\src\\com\\company\\task2.txt"))) {
            for (int i = 0; i < cabins.length; i++){
                for (int k = 0; k < passengers[i].length; k++){

                    if (cabins[i].getcabinNum() != 40){
                        if (!"empty".equalsIgnoreCase(passengers[i][k].getFirstName())){
                            out.println("\n" + "Cabin number: " + cabins[i].getcabinNum() + " Passenger " + k + "\n");
                            out.println("Passenger firstname: " + passengers[i][k].getFirstName() + "\n" + "Passenger surname: "
                                    + passengers[i][k].getSurname() + "\n" + "Passenger Expenses: "
                                    + passengers[i][k].getExpenses());
                        } else {
                            out.println("\n" + "Cabin " + i + " Passenger " + k + " is Empty");
                        }

                    }else {
                        out.println("\n"+"Cabin " + i + " is Empty");
                        break;
                    }
                }
            }
        }
        System.out.println("Successfully wrote to the file");
    }

    //LOAD DATA METHOD--
    public static void loadData(){

        try {
            File filelocation = new File("D:\\Studies\\IIT\\IIT SE Beng(Hons)\\Programming\\CW\\w1866975_classes\\Task2\\src\\com\\company\\task2.txt");
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

    //NAME BY ORDER METHOD--
    public static void NameOrderAlphabetically(passenger[][] passengers){

        String[] NameList = new String[36];

        int index = 0;

        for (int i = 0; i < passengers.length; i++){
            for (int k = 0; k < passengers[i].length; k++){

                NameList[index++] = passengers[i][k].getFirstName();
            }
        }


        for (int i = 0; i < NameList.length - 1; i++){
            for (int j = 0; j < NameList.length - i -1; j++){
                if (NameList[j].compareTo(NameList[j + 1]) > 0){

                    String temp = NameList[j];
                    NameList[j] = NameList[j + 1];
                    NameList[j+ 1] = temp;
                }
            }
        }

        for (int i = 0; i < NameList.length; i++){
            if (!"empty".equalsIgnoreCase(NameList[i])){
                System.out.println(NameList[i]);
            }
        }

    }


    //EXPENSES METHOD--
    public static void expenses(cabin[] cabins, passenger[][] passengers){

        Scanner input = new Scanner(System.in);

        int Total = 0;
        boolean endLoop = true;
        while (endLoop) {

            System.out.println("""
                    Press 'S' for View expense of a single passenger
                    Press 'A' for View expenses of all passengers
                    Press 'T' for View Total expenses of all passengers
                    
                    Press 'M' for jump back to Main program \s""");

            switch (Character.toUpperCase(input.next().charAt(0))) {

                case 'S':
                    System.out.println("Enter Passenger name: ");
                    String name = input.next();
                    boolean checkCustomerName = false;
                    for (int i = 0; i < passengers.length; i++) {
                        for (int k = 0; k < passengers[i].length; k++) {
                            if (name.equalsIgnoreCase(passengers[i][k].getFirstName())) {
                                System.out.println("\n"+name + " passenger expense is " + passengers[i][k].getExpenses());
                                checkCustomerName = true;
                            }
                        }
                    }
                    if (!checkCustomerName){
                        System.out.println(name + " Customer name not match with the records\n");

                    }
                    break;

                case 'A':
                    for (int i = 0; i < cabins.length; i++) {
                        for (int k = 0; k < passengers[i].length; k++) {

                            if (passengers[i][k].getExpenses() > 0) {
                                System.out.println("\n"+passengers[i][k].getFirstName() + " Passenger expense is: " + passengers[i][k].getExpenses());
                            }
                        }
                    }
                    break;

                case 'T':
                    for (int i = 0; i < cabins.length; i++) {
                        for (int k = 0; k < passengers[i].length; k++) {

                            if (passengers[i][k].getExpenses() > 0) {
                                Total += passengers[i][k].getExpenses();
                            }
                        }
                    }
                    System.out.println("\n"+"Total Expense: " + Total);
                    break;

                case 'M':
                    endLoop = false;
                    break;

                default:
                    System.out.println("\n"+"Please choose correct option");

            }
        }
    }
}

