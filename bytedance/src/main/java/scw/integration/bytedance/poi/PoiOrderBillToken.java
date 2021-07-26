package scw.integration.bytedance.poi;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

public class PoiOrderBillToken implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "下载账单token,5分钟内有效")
	private String bill_token;

	public String getBill_token() {
		return bill_token;
	}

	public void setBill_token(String bill_token) {
		this.bill_token = bill_token;
	}

}
