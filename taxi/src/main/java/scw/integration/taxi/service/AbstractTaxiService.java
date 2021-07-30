package scw.integration.taxi.service;

import scw.integration.taxi.OrderEventDispatcher;

public abstract class AbstractTaxiService implements TaxiService {
	private final OrderEventDispatcher orderEventDispatcher;

	public AbstractTaxiService(OrderEventDispatcher orderEventDispatcher) {
		this.orderEventDispatcher = orderEventDispatcher;
	}
}
