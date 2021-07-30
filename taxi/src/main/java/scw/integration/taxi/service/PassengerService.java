package scw.integration.taxi.service;

import scw.integration.taxi.dto.Member;
import scw.integration.taxi.dto.Passenger;
import scw.integration.taxi.dto.PostOrderRequest;

public interface PassengerService {
	/**
	 * 乘客位置上报
	 * 
	 * @param message
	 */
	void report(Member message);

	Passenger getPassenger(String passengerId);

	/**
	 * 下单
	 * 
	 * @param request
	 */
	void postOrder(PostOrderRequest request);
}
