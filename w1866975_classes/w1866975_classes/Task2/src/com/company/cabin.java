package com.company;

public class cabin {

    String mainCabinName;
    private int mainCabinNum;

    public cabin() {

        mainCabinName = "M";

    }

    public void setName(String aName){

        mainCabinName = aName;
    }

    public String getName(){
        return mainCabinName;
    }

    public void setcabinNum(int CName){
        mainCabinNum = CName;
    }


    public int getcabinNum() {
        return mainCabinNum;
    }

}