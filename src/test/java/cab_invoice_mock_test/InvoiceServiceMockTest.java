package cab_invoice_mock_test;

import invoice_generator.InvoiceService;
import invoice_generator.InvoiceSummary;
import invoice_generator.Ride;
import invoice_generator.RideRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InvoiceServiceMockTest {

    InvoiceService invoiceService =null;

    @Mock
    RideRepository rideRepository;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp()throws Exception{
        invoiceService = new InvoiceService(rideRepository);
    }
    @Test
    public void givenUserIdAndRides_shouldRerturnInvoiceSummary() {

        String userId="scfdas";
        Ride[] rides = {new Ride(2.0, 5),
                new Ride(0.1,1)
        };
        invoiceService.addRides(userId,rides);
        Mockito.doNothing().when(rideRepository).addRide(userId,rides);
        when(rideRepository.getRides(userId)).thenReturn(rides);
        //when(rideRepository.getRides(userId)).thenThrow(new Exception("hiii"));

        InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary invoiceSummary = new InvoiceSummary(2, 30);
        Assert.assertEquals(invoiceSummary,summary);
    }

    @Test
    public void givenUserIdWhichIsInvalid_shouldRerturnException() {
        String userId="scfdas";
        try {
            when(rideRepository.getRides(userId)).thenThrow(new RuntimeException("UserName doesn't exist"));
            invoiceService.getInvoiceSummary(userId);
        } catch (RuntimeException e) {
            Assert.assertEquals(e.getMessage(),"UserName doesn't exist");
            e.printStackTrace();
        }
    }

    @Test
    public void givenUserIdAsNull_shouldRerturnException() {
        String userId=null;
        try {
            when(rideRepository.getRides(null)).thenThrow(new NullPointerException());
            invoiceService.getInvoiceSummary(userId);
        } catch (RuntimeException e) {
            Assert.assertEquals(e.getMessage(),null);
            e.printStackTrace();
        }
    }
}
