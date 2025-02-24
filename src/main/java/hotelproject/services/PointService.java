package hotelproject.services;

public interface PointService {
   
	  boolean chargePoints(String userId, int amount);
	   boolean usePoints(String userId, int amount);
}
