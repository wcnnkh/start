package io.basc.integration.bytedance.poi;

import javax.validation.constraints.NotNull;

import io.basc.integration.bytedance.oauth.ClientRequest;
import io.swagger.v3.oas.annotations.media.Schema;

public class PoiOrderBillTokenRequest extends ClientRequest {
	private static final long serialVersionUID = 1L;
	@Schema(required = true)
	@NotNull
	private String bill_date;

	public String getBill_date() {
		return bill_date;
	}

	public void setBill_date(String bill_date) {
		this.bill_date = bill_date;
	}
}
