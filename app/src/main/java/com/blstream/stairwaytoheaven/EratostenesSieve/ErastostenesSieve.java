package com.blstream.stairwaytoheaven.EratostenesSieve;

import java.util.ArrayList;

public class ErastostenesSieve {
    private static int lastNumber;
    public ArrayList<Integer> candidates4Prime;
    public ArrayList<Integer> primeList;
    private final static int STARTVALUE = 2;

    public ErastostenesSieve(int lastNumber) {
        candidates4Prime = new ArrayList<>(lastNumber - 1);
        primeList = new ArrayList<>();
        this.lastNumber = lastNumber;
    }

    protected ArrayList<Integer> initalcandidates4Prime() {
        for (int i = STARTVALUE; i <= lastNumber; i++) {
            candidates4Prime.add(i);
        }
        return candidates4Prime;
    }

    protected ArrayList<Integer> search4Primes() {
        for (Integer currentNumber : candidates4Prime) {
            isCurrentNumberAPrime(currentNumber);

        }
        return primeList;
    }

    protected ArrayList<Integer> isCurrentNumberAPrime(int number2Check) {
        ArrayList<Integer> nonPrimeMultipleNumbers = new ArrayList<>();
        for (int multiplier = 1; multiplier * number2Check <= lastNumber; multiplier++) {

            boolean currentMultipleNumberIsPrime = IsCurrentMultipleNumerAPrime(number2Check, multiplier);
            int currentMultipleNumber = multiplier * number2Check;
            if(!currentMultipleNumberIsPrime) {
                nonPrimeMultipleNumbers.add(currentMultipleNumber);
            }
        }
        return nonPrimeMultipleNumbers;
    }
    protected boolean IsCurrentMultipleNumerAPrime (int number2Check, int multiple) {
        boolean isAPrime = false;
        int currentMultipleNumber = multiple * number2Check;
        if(number2Check == currentMultipleNumber && multiple == 1) {
                primeList.add(currentMultipleNumber);
                isAPrime = true;
        }
        return isAPrime;
    }

    public ArrayList<Integer> getPrimes() {
        return candidates4Prime;
    }

    public ArrayList<Integer> getCandidates4Prime() {
        return candidates4Prime;
    }
}
