package dataStructures;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

public class DatePrac {
    public static void main(String[] args) {
        int year = 1995;
        int month= 11;
        int date = 03;
        LocalDate datePrev = LocalDate.of(year, month, date);
        int year2 = 2020;
        int month2= 05;
        int date2 = 30;
        LocalDate dateCurr = LocalDate.of(year2, month2, date2);

        Period diff = Period.between(datePrev, dateCurr);
        System.out.println(diff.getYears());
    }
}
