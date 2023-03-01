package com.company;

public class passenger {

    private String FirstName;
    private String Surname;
    private double Expenses;

    public passenger(){
        FirstName="fn";
        Surname="sn";
        Expenses= 0.0;
    }

    public void setFirstName(String Firstname) {this.FirstName=Firstname;}

    public void setSurname(String Surname) {this.Surname=Surname;}

    public void setExpenses(double expenses) {Expenses=expenses;}

    public String getFirstName() {return FirstName;}

    public String getSurname() {return Surname;}

    public double getExpenses() {return Expenses;}


}
