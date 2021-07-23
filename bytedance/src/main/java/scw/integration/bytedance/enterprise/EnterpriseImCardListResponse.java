package scw.integration.bytedance.enterprise;

import java.util.List;

import scw.integration.bytedance.PagingResponseCode;

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
