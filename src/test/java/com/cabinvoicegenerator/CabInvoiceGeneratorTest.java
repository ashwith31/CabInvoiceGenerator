package com.cabinvoicegenerator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CabInvoiceGeneratorTest {

    CabInvoiceGenerator cabinvoiceGenerator = null;

    @BeforeEach
    public void setUp() {
        cabinvoiceGenerator = new CabInvoiceGenerator();
    }

    @Test
    void givenDistanceAndTime_ShouldReturnTotalFare(){
        double distance = 2.0;
        int time = 5;
        double fare = cabinvoiceGenerator.calculateFare(distance, time);
        Assertions.assertEquals(25,fare);
    }

    @Test
    public void givenLessDistanceAndTime_ShouldReturnTotalFare(){
        double distance = 1.0;
        int time = 5;
        double fair = cabinvoiceGenerator.calculateFare(distance, time);
        Assertions.assertEquals(15, fair);
    }

    @Test
    public void givenMultipleRides_ShouldReturnTotalFare(){
        Ride[] rides  = { new Ride(2.0, 5),
                new Ride(0.1, 1)};
        double fare = cabinvoiceGenerator.calculateFare(rides);
        Assertions.assertEquals(30, fare);
    }
}
