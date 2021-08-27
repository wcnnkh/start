package io.basc.platform.integration.bytedance.enterprise;

import io.basc.platform.integration.bytedance.PagingResponseCode;

import java.util.List;

public class EnterpriseImCardListResponse extends PagingResponseCode {
	private static final long serialVersionUID = 1L;
	private List<Card> cards;

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
}
