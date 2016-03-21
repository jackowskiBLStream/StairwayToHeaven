package com.blstream.stairwaytoheaven.EratostenesSieve;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by user on 21-Mar-16.
 */
public class ErastostenesSieveTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testInitialCandidates4Primes() {
        // given
        int lastNumer = 30;
        ArrayList<Integer> expectedList = new ArrayList<Integer>(
                Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
                        16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30));
        // when
        ErastostenesSieve erastostenesSieve = new ErastostenesSieve(lastNumer);
        // then
        assertEquals(expectedList, erastostenesSieve.initalcandidates4Prime());
    }

    @Test
         public void testNonPrimeIsCurrentMultipleNumerAPrime() {
        // given
        int number2Check = 3;
        int lastNumer = 30;
        int mutliplier = 2;
        // when
        ErastostenesSieve erastostenesSieve = new ErastostenesSieve(lastNumer);
        erastostenesSieve.initalcandidates4Prime();
        // then
        assertFalse(erastostenesSieve.IsCurrentMultipleNumerAPrime(number2Check, mutliplier));
    }
    @Test
    public void testPrimeIsCurrentMultipleNumerAPrime() {
        // given
        int number2Check = 13;
        int lastNumer = 30;
        int multiplier = 1;
        // when
        ErastostenesSieve erastostenesSieve = new ErastostenesSieve(lastNumer);
        erastostenesSieve.initalcandidates4Prime();
        // then
        assertTrue(erastostenesSieve.IsCurrentMultipleNumerAPrime(number2Check, multiplier));
    }

    @Test
    public void testNonPrimers1IsCurrentNumberAPrime() {
        // given
        int lastNumer = 10;
        int number2Check = 3;
        ArrayList<Integer> expectedNonPrimes = new ArrayList<Integer>(
                Arrays.asList(6, 9));
        // when
        ErastostenesSieve erastostenesSieve = new ErastostenesSieve(lastNumer);
        erastostenesSieve.initalcandidates4Prime();
        // then
        assertEquals(expectedNonPrimes, erastostenesSieve.isCurrentNumberAPrime(number2Check));
    }

    @Test
    public void testNonPrimers2IsCurrentNumberAPrime() {
        // given
        int lastNumer = 42;
        int number2Check = 6;
        ArrayList<Integer> expectedNonPrimes = new ArrayList<Integer>(
                Arrays.asList(12, 18, 24, 30, 36, 42));
        // when
        ErastostenesSieve erastostenesSieve = new ErastostenesSieve(lastNumer);
        erastostenesSieve.initalcandidates4Prime();
        // then
        assertEquals(expectedNonPrimes, erastostenesSieve.isCurrentNumberAPrime(number2Check));
    }

//
//    @Test
//    public void testSearch4Primes() {
//        // given
//        int lastNumer = 30;
//        ArrayList<Integer> expectedPrimes = new ArrayList<Integer>(
//                Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29));
//        // when
//        ErastostenesSieve erastostenesSieve = new ErastostenesSieve(lastNumer);
//        erastostenesSieve.initalcandidates4Prime();
//        // then
//        assertEquals(expectedPrimes, erastostenesSieve.search4Primes());
//    }

}