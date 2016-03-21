package com.blstream.stairwaytoheaven.EratostenesSieve;

import java.util.ArrayList;

public class ErastostenesSieve {
    private static int lastNumber;
    public ArrayList<Integer> candidates4Prime;
    private final static int STARTVALUE = 2;

    public ErastostenesSieve(int lastNumber) {
        candidates4Prime = new ArrayList<>(lastNumber - 1);
        this.lastNumber = lastNumber;
        initalcandidates4Prime();
        search4Primes();
    }

    private void initalcandidates4Prime() {
        for (int i = STARTVALUE; i <= lastNumber; i++) {
            candidates4Prime.add(i);
        }
    }

    private void search4Primes() {
        for (Integer currentNumber : candidates4Prime) {
            deleteNonPrimeDerivedFromCurrentNumber(currentNumber);
        }
    }

    private void deleteNonPrimeDerivedFromCurrentNumber(int number2Check) {
        for (int multiple = 1; multiple * number2Check <= lastNumber; multiple++) {
            int currentMultipleNumber = multiple * number2Check;
            candidates4Prime.remove(currentMultipleNumber);
        }
    }

    public ArrayList<Integer> getCandidates4Prime() {
        return candidates4Prime;
    }
}
