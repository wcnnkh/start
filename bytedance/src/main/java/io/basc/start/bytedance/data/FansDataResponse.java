package io.basc.start.bytedance.data;

import io.basc.start.bytedance.ResponseCode;

public class FansDataResponse extends ResponseCode {
	private static final long serialVersionUID = 1L;
	private FansData fans_data;

	public FansData getFans_data() {
		return fans_data;
	}

	public void setFans_data(FansData fans_data) {
		this.fans_data = fans_data;
	}
}
