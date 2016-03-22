package com.blstream.stairwaytoheaven.EratostenesSieve;

import com.blstream.stairwaytoheaven.Interfaces.PrimeProvider;

import java.util.ArrayList;

/**
 * Class handles finding primes by using the Eratosthenes Sieve Algorithm.
 * The list of candidates searched for primes is created on a basis of given last number.
 */
public class EratosthenesSieve implements PrimeProvider {

    private final static int START_VALUE = 2;
    private int lastNumber;
    private ArrayList<Integer> candidatesForPrime;
    private ArrayList<Integer> primeNumbers;

    public EratosthenesSieve(int lastNumberToCheck) {
        if (lastNumberToCheck >= START_VALUE) {
            candidatesForPrime = new ArrayList<>(lastNumberToCheck - 1);
            lastNumber = lastNumberToCheck;
        } else {
            candidatesForPrime = new ArrayList<>();
            primeNumbers = new ArrayList<>();
        }
    }

    /**
     * FIXME: Czo to ??
     * @return
     */
    public ArrayList<Integer> getPrimeNumbers() {
        initialCandidatesForPrime();
        findPrimeNumbers();
        return primeNumbers;
    }


    /**
     * Method initialize the list of candidates for primes with values between constant START_VALUE and given lastNumber
     *
     * @return candidatesForPrimes ArrayList<Integer> - initialized list of number candidates for primes
     */
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

    /**
     * Method searches for primes in list of candidates for primes
     * by using method findMultiplesFromCurrentNumber which finds multiples
     * generated from current candidate number. It removes in a loop found multiples from list of primes.
     *
     * @return primeNumbers ArrayList<Integer> - list of found primes
     */
    protected ArrayList<Integer> findPrimeNumbers() {
        for (Integer currentNumber : candidatesForPrime) {
            ArrayList<Integer> nonPrimeMultiplesFromCurrentNumber = findMultiplesFromCurrentNumber(currentNumber);
            primeNumbers.removeAll(nonPrimeMultiplesFromCurrentNumber);
        }
        return primeNumbers;
    }

    /**
     * Method searches for multiples for current number from the list of candidates
     *
     * @param candidateNumber int - current number from list of candidates for primes
     * @return multiplesFromCurrentNumber ArrayList<Integer> - list of found multiples from current number
     */
    protected ArrayList<Integer> findMultiplesFromCurrentNumber(int candidateNumber) {
        ArrayList<Integer> multiplesFromCurrentNumber = new ArrayList<>();
        //FIXME: code me like one of your latino boys :)
        for (int multiplier = 1, currentMultiple; (currentMultiple = multiplier * candidateNumber) <= lastNumber; multiplier++) {
            boolean isAMultiple = isAMultipleFromCurrentNumber(candidateNumber, currentMultiple);
            if (!isAMultiple) {
                multiplesFromCurrentNumber.add(currentMultiple);
            }
        }
        return multiplesFromCurrentNumber;
    }

    /**
     * Method checks if current multiple is a multiple of current number from the list of candidates.
     *
     * @param candidateNumber int - current number from list of candidates for primes
     * @param currentMultiple int - current possible multiple of current number
     * @return isAMultipleFromCurrentNumber boolean - defines if a current multiple is a multiple
     * from current number from the list of candidates
     */
    protected boolean isAMultipleFromCurrentNumber(int candidateNumber, int currentMultiple) {
        boolean isAMultipleFromCurrentNumber = true;
        if (candidateNumber != currentMultiple) {
            isAMultipleFromCurrentNumber = false;
        }
        return isAMultipleFromCurrentNumber;
    }
}
