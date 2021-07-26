package scw.integration.bytedance.enterprise;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

public class EnterpriseImCardDeleteRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "卡片名称（已废弃）", example = "open.234325344", required = true)
	@NotNull
	private String card_id;
	@Schema(description = "卡片id")
	private String id;

	public String getCard_id() {
		return card_id;
	}

	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
