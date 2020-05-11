package Olya;

import java.io.IOException;

public class Errors_Exceptions extends Exception {
    private int number;
    public int getNumber(){return number;}
    public Errors_Exceptions(String message, int num) {

        super(message);
        number = num;


    }
    }

