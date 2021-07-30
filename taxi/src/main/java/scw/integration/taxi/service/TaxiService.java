package scw.integration.taxi.service;

import java.util.List;

import scw.integration.taxi.dto.Taxi;
import scw.integration.taxi.dto.TaxiStatus;

/**
 * 车辆服务
 * 
 * @author shuchaowen
 *
 */
public interface TaxiService {
	/**
	 * 获取附近车牌
	 * 
	 * @param taxiStatus
	 * @return
	 */
	List<Taxi> getNearbyTaxis(TaxiStatus taxiStatus);

	/**
	 * 获取taxi信息
	 * 
	 * @param taxiId
	 * @return
	 */
	Taxi getTaxi(String taxiId);
}
