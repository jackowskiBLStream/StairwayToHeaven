package com.blstream.stairwaytoheaven.EratostenesSieve;

import java.util.ArrayList;

public class EratosthenesSieve {

    private final static int START_VALUE = 2;
    private int lastNumber;
    private ArrayList<Integer> candidatesForPrime;
    private ArrayList<Integer> primeNumbers;

    public EratosthenesSieve(int lastNumberToCheck) {
        if (lastNumberToCheck >= START_VALUE) {
            candidatesForPrime = new ArrayList<>(lastNumberToCheck - 1);
            lastNumber = lastNumberToCheck;
        }
        else {
            candidatesForPrime = new ArrayList<>();
            primeNumbers = new ArrayList<>();
        }
    }

    protected ArrayList<Integer> initialCandidatesForPrime() {
        if (lastNumber < START_VALUE) {
            return candidatesForPrime;
        }
        for (int i = START_VALUE; i <= lastNumber; i++) {
            candidatesForPrime.add(i);
        }
        primeNumbers = new ArrayList<>(candidatesForPrime);
        return candidatesForPrime;
    }

    protected ArrayList<Integer> findPrimeNumbers() {
        for (Integer currentNumber : candidatesForPrime) {
            ArrayList<Integer> nonPrimeMultiplesFromCurrentNumber = findMultiplesFromCurrentNumber(currentNumber);
            primeNumbers.removeAll(nonPrimeMultiplesFromCurrentNumber);
        }
        return primeNumbers;
    }

    protected ArrayList<Integer> findMultiplesFromCurrentNumber(int numberToCheck) {
        ArrayList<Integer> nonPrimeMultiplesFromCurrentNumber = new ArrayList<>();
        for (int multiplier = 1, currentMultiple; (currentMultiple = multiplier * numberToCheck) <= lastNumber; multiplier++) {
            boolean isMultipleAPrime = isMultiplePossiblyAPrime(numberToCheck, currentMultiple);
            if (!isMultipleAPrime) {
                nonPrimeMultiplesFromCurrentNumber.add(currentMultiple);
            }
        }
        return nonPrimeMultiplesFromCurrentNumber;
    }

    protected boolean isMultiplePossiblyAPrime(int number2Check, int currentMultiple) {
        boolean isAPrime = true;
        if (number2Check != currentMultiple) {
            isAPrime = false;
        }
        return isAPrime;
    }

    public ArrayList<Integer> getPrimeNumbers() {
        initialCandidatesForPrime();
        findPrimeNumbers();
        return primeNumbers;
    }
}
