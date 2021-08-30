package io.basc.start.bytedance.enterprise;

import java.util.List;

import io.basc.start.bytedance.PagingResponseCode;

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
