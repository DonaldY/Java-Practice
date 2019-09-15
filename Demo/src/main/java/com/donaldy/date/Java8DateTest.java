package com.donaldy.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Java8DateTest {

    public static void main(String[] args) {

        DateTimeFormatter fmt =  DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate date = LocalDate.now();
    }
}
