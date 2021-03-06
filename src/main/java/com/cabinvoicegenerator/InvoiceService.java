package com.cabinvoicegenerator;

public class InvoiceService {

    private static final double MINIMUM_COST_PER_KILOMETER = 10;
    private static final int COST_PER_TIME = 1;
    private static final double MINIMUM_FAIR = 5.0;
    private RideRepository rideRepository;

    public InvoiceService() {
        this.rideRepository =  new RideRepository();
    }

    public double calculateFare(double distance, int time) {
        double totalFair = distance * MINIMUM_COST_PER_KILOMETER + time * COST_PER_TIME;
        return Math.max(totalFair, MINIMUM_FAIR);
    }

    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare = 0;
        for (Ride ride : rides) {
            totalFare += ride.cabRide.calculateCostOfRide(ride);
        }
        return new InvoiceSummary(rides.length, totalFare);
    }
    public void addRides(String userId, Ride[] rides) {
        rideRepository.addRides(userId, rides);
    }

    public InvoiceSummary getInvoiceSummary(String userId) {
        return this.calculateFare(rideRepository.getRides(userId));
    }

    public void setRideRepository(RideRepository rideRepository) {
        this.rideRepository = rideRepository;
    }
}
