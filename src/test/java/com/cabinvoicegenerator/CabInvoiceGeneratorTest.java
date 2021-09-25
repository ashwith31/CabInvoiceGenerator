package com.cabinvoicegenerator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CabInvoiceGeneratorTest {

    @Test
    void givenDistanceAndTime_ShouldReturnTotalFare(){
        CabInvoiceGenerator cabInvoiceGenerator = new CabInvoiceGenerator();
        double distance = 2.0;
        int time = 5;
        double fare = cabInvoiceGenerator.calculateFare(distance, time);
        Assertions.assertEquals(25,fare);
    }

    @Test
    public void givenLessDistanceAndTime_ShouldReturnTotalFare() {
        CabInvoiceGenerator cabinvoiceGenerator = new CabInvoiceGenerator();
        double distance = 1.0;
        int time = 5;
        double fair = cabinvoiceGenerator.calculateFare(distance, time);
        Assertions.assertEquals(15, fair);
    }

    @Test
    public void givenMultipleRides_ShouldReturnTotalFare(){
        CabInvoiceGenerator invoiceGenerator = new CabInvoiceGenerator();
        Ride[] rides  = { new Ride(2.0, 5),
                new Ride(0.1, 1)};
        double fare = invoiceGenerator.calculateFare(rides);
        Assertions.assertEquals(30, fare);
    }
}
