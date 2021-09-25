package com.cabinvoicegenerator;

public class CabInvoiceGenerator {

    private static final double MINIMUM_COST_PER_KILOMETER = 10;
    private static final int COST_PER_TIME = 1;

    public double calculateFare(double distance, int time) {
        return distance = MINIMUM_COST_PER_KILOMETER*distance + COST_PER_TIME*time;
    }
}
