package com.example.hilos_android.clases;

import java.util.concurrent.Callable;

public class FactorialCalculator implements Callable<Long> {
    private int number;
    public FactorialCalculator(int number) {
        this.number = number;
    }
    @Override
    public Long call() throws Exception {
        return calculateFactorial(number);
    }
    private long calculateFactorial(int n) {
        if (n <= 0 || n ==1 ) {
            return 1;
        } else {
            return calculateFactorial(n-1)+calculateFactorial(n-2);
        }
    }
}

// 1 ,1 ,2 ,3 ,5 ,8 ,13 , 21 , 34
//  i j  i   j  i  j