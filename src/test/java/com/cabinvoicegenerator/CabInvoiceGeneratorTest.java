package com.cabinvoicegenerator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CabInvoiceGeneratorTest {
    Ride[] rides = null;
    InvoiceSummary expectedInvoiceSummary;

    @Mock
    RideRepository rideRepository;

    @InjectMocks
    InvoiceService invoiceService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
    }
    public void setUp() {
        invoiceService = new InvoiceService();
        rideRepository = new RideRepository();
        invoiceService.setRideRepository(rideRepository);
        rides = new Ride[]{
                new Ride(CabRide.NORMAL, 2.0, 5),
                new Ride(CabRide.PREMIUM, 0.1, 1)
        };
        expectedInvoiceSummary = new InvoiceSummary(2, 45.0);
    }
    @Test
    void givenDistanceAndTime_ShouldReturnTotalFare(){
        double distance = 2.0;
        int time = 5;
        double fare = invoiceService.calculateFare(distance, time);
        Assertions.assertEquals(25,fare);
    }

    @Test
    public void givenLessDistanceAndTime_ShouldReturnTotalFare(){
        double distance = 1.0;
        int time = 5;
        double fair = invoiceService.calculateFare(distance, time);
        Assertions.assertEquals(15, fair);
    }

    @Test
    public void givenMultipleRides_ShouldReturnTotalFareAndAverageFare(){
        Ride[] rides  = { new Ride(CabRide.NORMAL,2.0, 5),
                new Ride(CabRide.PREMIUM, 0.1, 1)};
        InvoiceSummary summary = invoiceService.calculateFare(rides);
        InvoiceSummary expectedInvoice = new InvoiceSummary(2, 45.0);
        Assertions.assertEquals(expectedInvoice, summary);
    }

    @Test
    public void givenUserIdAndRides_ShouldReturnInvoice() {
        String userId = "Ashwith";
        Ride[] rides = {new Ride(CabRide.NORMAL, 2.0, 5),
                new Ride(CabRide.NORMAL,0.1, 1),
        };
        when(rideRepository.getRides(userId)).thenReturn(rides);
        InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30);
        Assertions.assertEquals(expectedInvoiceSummary,  summary);
    }

    @Test
    public void givenUserIDAndMultipleRideList_ShouldReturnInvoiceSummary() {
        String userId = "Ashwith";
        Ride[] rides = {new Ride(CabRide.NORMAL, 2.0, 5),
                new Ride(CabRide.PREMIUM, 0.1, 1)
        };
        when(rideRepository.getRides(userId)).thenReturn(rides);
        InvoiceSummary actualSummary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 45.0);
        Assertions.assertEquals(expectedInvoiceSummary, actualSummary);
    }

    @Test
    void givenDistanceAndTime_WhenCalculatedForPremiumRide_ShouldReturnInvoiceSummary() {
        String userId = "Ashwith";
        Ride [] rides = new Ride[]{new Ride(CabRide.PREMIUM, 2.0, 5),
                new Ride(CabRide.PREMIUM, 0.1, 1),
                new Ride(CabRide.PREMIUM, 1.0, 5),
        };
        when(rideRepository.getRides(userId)).thenReturn(rides);
        InvoiceSummary actualSummary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(3, 85.0);
        Assertions.assertEquals(expectedInvoiceSummary, actualSummary);
    }

    @Test
    void givenDistanceAndTime_WhenCalculatedForNormalRide_ShouldReturnInvoiceSummary() {
        String userId = "Ashwith";
        Ride [] rides = new Ride[]{new Ride(CabRide.NORMAL, 2.0, 5),
                new Ride(CabRide.NORMAL, 0.1, 1),
                new Ride(CabRide.NORMAL, 1.0, 5),
        };
        when(rideRepository.getRides(userId)).thenReturn(rides);
        InvoiceSummary actualSummary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(3, 45.0);
        Assertions.assertEquals(expectedInvoiceSummary, actualSummary);
    }

}
