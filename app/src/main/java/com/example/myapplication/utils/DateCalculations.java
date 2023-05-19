package com.example.myapplication.utils;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateCalculations {

    public int differenceInYears(String date1, String date2)
    {
        int i = Integer.parseInt(date1.substring(6));
        int j = Integer.parseInt(date2.substring(6));
        int p = Integer.parseInt(date1.substring(3,5));
        int q = Integer.parseInt(date2.substring(3,5));

        int z;
        if(q>=p){
            z=(q-p + (j-i)*12)/12;
        }else{
            z=(p-q + (j-i)*12)/12;
        }
        return z;
    }

}
