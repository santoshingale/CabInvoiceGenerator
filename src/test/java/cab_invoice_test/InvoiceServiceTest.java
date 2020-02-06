package cab_invoice_test;

import invoice_generator.InvoiceService;
import invoice_generator.InvoiceSummary;
import invoice_generator.Ride;
import invoice_generator.RideRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class InvoiceServiceTest {

    InvoiceService invoiceService =null;

    @Before
    public void setUp()throws Exception{
        invoiceService = new InvoiceService(new RideRepository());
    }

    @Test
    public void givenDistanceAndTime_shouldReturnTotalFare() {
        double distance = 2.0;
        int time = 5;
        double fare = invoiceService.addRides(distance, time);
        Assert.assertEquals(25, fare, 0.0);
    }

    @Test
    public void givenLessDistanceAndTime_shouldReturnMinimumnFare() {
        double fare = invoiceService.addRides(0.2, 1);
        Assert.assertEquals(5, fare, 0.0);
    }

    @Test
    public void givenMultipleRides_shouldReturnInvoiceSummary() {
        Ride[] rides = {new Ride(2.0, 5),
                new Ride(0.1,1)
        };
        InvoiceSummary summary = invoiceService.calculateFare(rides);
        InvoiceSummary invoiceSummary = new InvoiceSummary(2, 30);
        Assert.assertEquals(invoiceSummary,summary);
    }

    @Test
    public void givenUserIdAndRides_shouldRerturnInvoiceSummary() {
        String userId="scfdas";
        Ride[] rides = {new Ride(2.0, 5),
                new Ride(0.1,1)
        };
        invoiceService.addRides(userId,rides);
        InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary invoiceSummary = new InvoiceSummary(2, 30);
        Assert.assertEquals(invoiceSummary,summary);
    }
}
