package invoice_generator;

import java.sql.Array;
import java.util.*;

public class RideRepository {

    Map<String, ArrayList<Ride>> userRides=null;

    public RideRepository() {
        this.userRides = new HashMap<>();
    }

    public void addRide(String userId, Ride[] rides) {
        ArrayList<Ride> rideList = this.userRides.get(userId);
        if (rideList==null){
            this.userRides.put(userId,new ArrayList<>((Arrays.asList(rides))));
        }
    }

    public Ride[] getRides(String userId) {

        Ride[] rides = this.userRides.get(userId).toArray(new Ride[0]);
        for(Ride ride : rides)
            System.out.println(ride);
        return rides;
    }
}
