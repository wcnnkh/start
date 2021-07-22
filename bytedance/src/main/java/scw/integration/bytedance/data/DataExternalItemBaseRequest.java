package scw.integration.bytedance.data;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import scw.integration.bytedance.user.UserRequest;

public class DataExternalItemBaseRequest extends UserRequest {
	private static final long serialVersionUID = 1L;
	@Schema(description = "item_id，仅能查询access_token对应用户上传的视频", required = true)
	@NotNull
	private String item_id;

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
}
