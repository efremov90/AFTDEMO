package Olya;

import java.io.IOException;

public class Errors_Exceptions extends Exception {
    private int number;
    public Errors_Exceptions(String message, int num) {

        super(message);
        number = num;
    }
    @Override
    public String toString() {
        return "MyException{"
                + "number=" + number
                + ", message=" + getMessage()
                + "} ";
    }
    }

