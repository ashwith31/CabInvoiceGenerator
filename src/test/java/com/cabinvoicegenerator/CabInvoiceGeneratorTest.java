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
    public void givenMultipleRides_ShouldReturnTotalFareAndAverageFare(){
        Ride[] rides  = { new Ride(2.0, 5),
                new Ride(0.1, 1)};
        InvoiceSummary summary = cabinvoiceGenerator.calculateFare(rides);
        InvoiceSummary expectedInvoice = new InvoiceSummary(2, 30.0);
        Assertions.assertEquals(expectedInvoice, summary);
    }

    @Test
    public void givenUserIdAndRides_ShouldReturnInvoice() {
        String userId = "Ashwith";
        Ride[] rides = {new Ride(2.0, 5),
                new Ride(0.1, 1),
        };
        cabinvoiceGenerator.addRides(userId,rides);
        InvoiceSummary summary = cabinvoiceGenerator.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30);
        Assertions.assertEquals(expectedInvoiceSummary,  summary);
    }
}
