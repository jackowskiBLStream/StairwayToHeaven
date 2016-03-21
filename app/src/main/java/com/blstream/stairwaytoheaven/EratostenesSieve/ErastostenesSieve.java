package com.blstream.stairwaytoheaven.EratostenesSieve;

import java.util.ArrayList;

public class ErastostenesSieve {
    private static int lastNumber;
    //FIXME: why public?
    public ArrayList<Integer> candidates4Prime;
    //FIXME: why public?
    public ArrayList<Integer> primeList;
    private final static int START_VALUE = 2;

    public ErastostenesSieve(int lastNumber) {
        candidates4Prime = new ArrayList<>(lastNumber - 1);
        primeList = new ArrayList<>();
        this.lastNumber = lastNumber;
    }

    //FIXME: initialCandidatesForPrimes
    protected ArrayList<Integer> initalcandidates4Prime() {
        for (int i = START_VALUE; i <= lastNumber; i++) {
            candidates4Prime.add(i);
        }
        return candidates4Prime;
    }

    //FIXME: only 4 primes?
    protected ArrayList<Integer> search4Primes() {
        for (Integer currentNumber : candidates4Prime) {
            isCurrentNumberAPrime(currentNumber);

        }
        return primeList;
    }

    protected ArrayList<Integer> isCurrentNumberAPrime(int number2Check) {
        ArrayList<Integer> nonPrimeMultipleNumbers = new ArrayList<>();
        for (int multiplier = 1; multiplier * number2Check <= lastNumber; multiplier++) {

            boolean currentMultipleNumberIsPrime = IsCurrentMultipleNumberAPrime(number2Check, multiplier);
            int currentMultipleNumber = multiplier * number2Check; //FIXME: move to separate method
            if(!currentMultipleNumberIsPrime) {
                nonPrimeMultipleNumbers.add(currentMultipleNumber);
            }
        }
        return nonPrimeMultipleNumbers;
    }
    // FIXME: why no blank line?
    // FIXME: method name start with capital letter?
    protected boolean IsCurrentMultipleNumberAPrime(int number2Check, int multiple) {
        boolean isAPrime = false;
        int currentMultipleNumber = multiple * number2Check;
        if(number2Check == currentMultipleNumber && multiple == 1) {
                primeList.add(currentMultipleNumber); //FIXME: object state is modified in method which assumes boolean checks only
                isAPrime = true;
        }
        return isAPrime;
    }

    //FIXME: not used
    public ArrayList<Integer> getPrimes() {
        return candidates4Prime;
    }

    //FIXME: not used
    public ArrayList<Integer> getCandidates4Prime() {
        return candidates4Prime;
    }
}
