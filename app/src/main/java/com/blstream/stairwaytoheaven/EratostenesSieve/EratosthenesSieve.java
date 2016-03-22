package com.blstream.stairwaytoheaven.EratostenesSieve;

import java.util.ArrayList;

public class EratosthenesSieve {

    private final static int START_VALUE = 2;
    private static int lastNumber;
    private ArrayList<Integer> candidatesForPrime;
    private ArrayList<Integer> primeNumbers;

    public EratosthenesSieve(int lastNumberToCheck) {
        candidatesForPrime = new ArrayList<>(lastNumberToCheck - 1);
        lastNumber = lastNumberToCheck;
    }

    protected ArrayList<Integer> initialCandidatesForPrime() {
        if (lastNumber < START_VALUE) {
            return null;
        }
        for (int i = START_VALUE; i <= lastNumber; i++) {
            candidatesForPrime.add(i);
        }
        primeNumbers = new ArrayList<>(candidatesForPrime);
        return candidatesForPrime;
    }

    protected ArrayList<Integer> getPrimeNumbers() {
        for (Integer currentNumber : candidatesForPrime) {
            ArrayList nonPrimeMultiplesFromCurrentNumber = findMultiplesFromCurrentNumber(currentNumber);
            primeNumbers.removeAll(nonPrimeMultiplesFromCurrentNumber);
        }
        return primeNumbers;
    }

    protected ArrayList<Integer> findMultiplesFromCurrentNumber(int numberToCheck) {
        ArrayList<Integer> nonPrimeMultiplesFromCurrentNumber = new ArrayList<Integer>();
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
}
