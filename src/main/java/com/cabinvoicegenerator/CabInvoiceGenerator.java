package com.cabinvoicegenerator;

public class CabInvoiceGenerator {

    private static final double MINIMUM_COST_PER_KILOMETER = 10;
    private static final int COST_PER_TIME = 1;
    private static final double MINIMUM_FAIR = 5.0;

    public double calculateFare(double distance, int time) {
        double totalFair = distance * MINIMUM_COST_PER_KILOMETER + time * COST_PER_TIME;
        return Math.max(totalFair, MINIMUM_FAIR);
    }

    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare = 0;
        for (Ride ride : rides) {
            totalFare += this.calculateFare(ride.distance, ride.time);
        }
        return new InvoiceSummary(rides.length, totalFare);
    }
}
