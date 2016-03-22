package com.blstream.stairwaytoheaven.EratostenesSieve;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EratosthenesSieveTest {

    @Test
    public void testInitialCandidatesForPrimesCompareElementsOfListWithMuster() {
        // given
        int lastNumber = 30;
        List<Integer> expectedList =
                Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
                        16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30);
        // when
        EratosthenesSieve eratosthenesSieve = new EratosthenesSieve(lastNumber);
        // then
        assertEquals(expectedList, eratosthenesSieve.initialCandidatesForPrime());
    }

    @Test
    public void testInitialCandidatesForPrimesCountInitializedList() {
        // given
        int lastNumber = 30;
        int numberOfPrimeCandidates = 29;
        // when
        EratosthenesSieve eratosthenesSieve = new EratosthenesSieve(lastNumber);
        // then
        assertEquals(numberOfPrimeCandidates, eratosthenesSieve.initialCandidatesForPrime().size());
    }

    @Test
    public void testPrimeIsMultiplePossiblyAPrime() {
        // given
        int number2Check = 19;
        int lastNumber = 30;
        int currentMultiple = 19;
        // when
        EratosthenesSieve eratosthenesSieve = new EratosthenesSieve(lastNumber);
        eratosthenesSieve.initialCandidatesForPrime();
        // then
        assertTrue(eratosthenesSieve.isMultiplePossiblyAPrime(number2Check, currentMultiple));
    }

    @Test
    public void testNonPrimeIsMultiplePossiblyAPrime() {
        // given
        int number2Check = 5;
        int lastNumber = 30;
        int currentMultiple = 25;
        // when
        EratosthenesSieve eratosthenesSieve = new EratosthenesSieve(lastNumber);
        eratosthenesSieve.initialCandidatesForPrime();
        // then
        assertFalse(eratosthenesSieve.isMultiplePossiblyAPrime(number2Check, currentMultiple));
    }

    @Test
    public void testPossiblePrimeIsMultiplePossiblyAPrime() {
        // given
        int number2Check = 30;
        int lastNumber = 30;
        int currentMultiple = 30;
        // when
        EratosthenesSieve eratosthenesSieve = new EratosthenesSieve(lastNumber);
        eratosthenesSieve.initialCandidatesForPrime();
        // then
        assertTrue(eratosthenesSieve.isMultiplePossiblyAPrime(number2Check, currentMultiple));
    }

    @Test
    public void testNonPrimeFindMultiplesFromCurrentNumber() {
        // given
        int lastNumber = 30;
        int number2Check = 6;
        List<Integer> expectedNonPrimes = Arrays.asList(12, 18, 24, 30);
        // when
        EratosthenesSieve eratosthenesSieve = new EratosthenesSieve(lastNumber);
        eratosthenesSieve.initialCandidatesForPrime();
        // then
        assertEquals(expectedNonPrimes, eratosthenesSieve.findMultiplesFromCurrentNumber(number2Check));
    }

    @Test
    public void testPossibleNonPrimeFindMultiplesFromCurrentNumber() {
        // given
        int lastNumber = 30;
        int number2Check = 6;
        List<Integer> expectedNonPrimes = Arrays.asList(6, 12, 18, 24, 30);
        // when
        EratosthenesSieve eratosthenesSieve = new EratosthenesSieve(lastNumber);
        eratosthenesSieve.initialCandidatesForPrime();
        // then
        assertFalse(eratosthenesSieve.findMultiplesFromCurrentNumber(number2Check).equals(expectedNonPrimes));
    }

    @Test
    public void testPrimeFindMultiplesFromCurrentNumber() {
        // given
        int lastNumber = 30;
        int number2Check = 6;
        List<Integer> expectedNonPrimes = Arrays.asList(12, 14, 16, 18, 20, 22, 24, 26, 28, 30);
        // when
        EratosthenesSieve eratosthenesSieve = new EratosthenesSieve(lastNumber);
        eratosthenesSieve.initialCandidatesForPrime();
        // then
        assertFalse(eratosthenesSieve.findMultiplesFromCurrentNumber(number2Check).equals(expectedNonPrimes));
    }

    @Test
    public void testGetPrimeNumbers() {
        // given
        int lastNumber = 30;
        List<Integer> expectedPrimes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        // when
        EratosthenesSieve eratosthenesSieve = new EratosthenesSieve(lastNumber);
        eratosthenesSieve.initialCandidatesForPrime();
        // then
        assertEquals(expectedPrimes, eratosthenesSieve.findPrimeNumbers());
    }

    @Test
    public void testLastNumberTwoGetPrimeNumbers() {
        // given
        int lastNumber = 2;
        List<Integer> expectedPrimes = new ArrayList<>();
        expectedPrimes.add(lastNumber);
        // when
        EratosthenesSieve eratosthenesSieve = new EratosthenesSieve(lastNumber);
        eratosthenesSieve.initialCandidatesForPrime();
        // then
        assertEquals(expectedPrimes, eratosthenesSieve.findPrimeNumbers());
    }

    @Test
    public void testMultiNumbersGetPrimeNumbers() {
        // given
        int lastNumber = 1000;
        List<Integer> expectedPrimes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41,
                43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131,
                137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227,
                229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317,
                331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431,
                433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541,
                547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643,
                647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719, 727, 733, 739, 743, 751, 757,
                761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877,
                881, 883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997);
        // when
        EratosthenesSieve eratosthenesSieve = new EratosthenesSieve(lastNumber);
        eratosthenesSieve.initialCandidatesForPrime();
        // then
        assertEquals(expectedPrimes, eratosthenesSieve.findPrimeNumbers());
    }

    @Test
    public void testLastNumberOneGetPrimeNumbers() {
        // given
        int lastNumber = 1;
        // when
        EratosthenesSieve eratosthenesSieve = new EratosthenesSieve(lastNumber);
        eratosthenesSieve.initialCandidatesForPrime();
        // then
        assertEquals(new ArrayList<>().size(), eratosthenesSieve.findPrimeNumbers().size());

    }

    @Test
    public void testLastNumberMinIntegerFindPrimeNumbers() {
        // given
        int lastNumber = Integer.MIN_VALUE;
        // when
        EratosthenesSieve eratosthenesSieve = new EratosthenesSieve(lastNumber);
        eratosthenesSieve.initialCandidatesForPrime();
        // then
        assertEquals(new ArrayList<>().size(), eratosthenesSieve.findPrimeNumbers().size());

    }
}