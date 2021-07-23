package scw.integration.bytedance.enterprise;

import io.swagger.v3.oas.annotations.media.Schema;
import scw.integration.bytedance.ResponseCode;

public class EnterpriseImCardSaveResponse extends ResponseCode {
	private static final long serialVersionUID = 1L;
	@Schema(description = "卡片id", example = "open.234325344")
	private String card_id;

	public String getCard_id() {
		return card_id;
	}

	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}

}
