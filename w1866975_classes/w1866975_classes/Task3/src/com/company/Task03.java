package com.company;
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Task03 {

    //MAIN METHOD--
    public static void main(String[] args) throws IOException{

        Scanner input = new Scanner(System.in);

        queue WaitingList = new queue(6); //creating the queue class
        cabin[] cabins = new cabin[12]; //crating the cabin class
        passenger[][] passengers = new passenger[12][3]; // creating passenger class and 2D array to store passenger details

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
                case 'A' -> addCustomer(WaitingList, cabins, passengers);
                case 'V' -> viewAll(cabins, passengers);
                case 'E' -> emptyCabins(cabins);
                case 'D' -> deleteCustomer(cabins, WaitingList, passengers);
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


    //ADD CUSTOMER METHOD--
    public static void addCustomer(queue WaitingList, cabin[] cabins, passenger[][] passengers) {

        int cabinNum;

        Scanner input = new Scanner(System.in);

        System.out.println("Enter a cabin number from 0 to 11");
        cabinNum = input.nextInt();
        if (cabinNum >= 0 && cabinNum < 12) {

            cabins[cabinNum].setcabinNum(cabinNum);
            System.out.println("Which position do you wish to assign the passenger in cabin no " + cabinNum+ " (Select 0,1,2)");
            int passengerLocation = input.nextInt();
            if (passengerLocation >= 0 && passengerLocation < 3) {

                if (passengers[cabinNum][passengerLocation].getFirstName().equals("empty")) {
                    System.out.println("Enter Passenger " + passengerLocation + " First name:");
                    passengers[cabinNum][passengerLocation].setFirstName(input.next());
                    System.out.println("Enter Passenger " + passengerLocation + " Surname:");
                    passengers[cabinNum][passengerLocation].setSurname(input.next());

                    int expenses = 0;
                    do {
                        try {
                            System.out.println("Enter Passenger " + passengerLocation + " Expense:");
                            expenses = input.nextInt();
                            passengers[cabinNum][passengerLocation].setExpenses(expenses);

                        } catch (InputMismatchException e) {
                            System.out.print("Invalid Expense. " + "\n");
                        }
                        input.nextLine();
                    } while (expenses <= 0);

                } else {
                    System.out.println(passengerLocation + " Cabin already received by  customer");
                }
            } else {
                System.out.println("Please enter cabin location 0 - 3");
                addCustomer(WaitingList, cabins, passengers);
            }

        } else {
            System.out.println("Maximum Cabins are 0 to 11, Please select a cabin from 0 to 11\n");
            addCustomer(WaitingList, cabins, passengers);
        }

        addQueue(passengers, WaitingList, cabins);
    }

    //ADD QUEUE METHOD--
    public static void addQueue(passenger[][] passengers, queue WaitingList, cabin[] cabins) {
        Scanner input = new Scanner(System.in);

        for (int i = 0; i < 1; i++) {

            if (cabins[i].getcabinNum() != 40 && cabins[i + 1].getcabinNum() != 40 && cabins[i + 2].getcabinNum() != 40 && cabins[i + 3].getcabinNum() != 40
                    && cabins[i + 4].getcabinNum() != 40 && cabins[i + 5].getcabinNum() != 40 && cabins[i + 6].getcabinNum() != 40 && cabins[i + 7].getcabinNum() != 40
                    && cabins[i + 8].getcabinNum() != 40 && cabins[i + 9].getcabinNum() != 40 && cabins[i + 10].getcabinNum() != 40 && cabins[i + 11].getcabinNum() != 40) {

                System.out.println("""
                        Do you want to add Passenger to Queue:
                        
                        Press 'Y' for Yes
                        Press 'N' for No\s""");

                switch (Character.toUpperCase(input.next().charAt(0))) {
                    case 'Y':
                        System.out.println("Enter First name to Queue");
                        WaitingList.EnQueue(input.next());
                        break;

                    case 'N':
                        break;

                    default:
                        System.out.println("You have choose a wrong character");
                }
            }
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
    public static void deleteCustomer(cabin[] cabins, queue WaitingList, passenger[][] passengers){
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

        if (WaitingList.endName() != null){
            cabins[cabinNum].setcabinNum(cabinNum);
            passengers[cabinNum][0].setFirstName(WaitingList.firstName());
            System.out.println(WaitingList.firstName() +  " Enter your Last name ");
            passengers[cabinNum][0].setSurname(input.next());
            int expenses = 0;
            do {
                try {
                    System.out.println(WaitingList.firstName() + " Enter your Expense ");
                    expenses = input.nextInt();
                    passengers[cabinNum][0].setExpenses(expenses);

                } catch (InputMismatchException e) {
                    System.out.print("Invalid expense. "+ "\n");
                }
                input.nextLine();
            } while (expenses <= 0);

            WaitingList.DeQueue();
        }
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

        try (PrintWriter out = new PrintWriter(new FileWriter("D:\\Studies\\IIT\\IIT SE Beng(Hons)\\Programming\\CW\\w1866975_classes\\Task3\\src\\com\\company\\task3.txt"))) {
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
            File filelocation = new File("D:\\Studies\\IIT\\IIT SE Beng(Hons)\\Programming\\CW\\w1866975_classes\\Task3\\src\\com\\company\\task3.txt");
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

                default:
                    System.out.println("\n"+"Please choose correct option");

            }
        }
    }
}
