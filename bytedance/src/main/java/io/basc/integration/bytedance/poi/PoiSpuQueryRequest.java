package io.basc.integration.bytedance.poi;

import java.io.Serializable;

public class PoiSpuQueryRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	private String spu_ext_id;

	public String getSpu_ext_id() {
		return spu_ext_id;
	}

	public void setSpu_ext_id(String spu_ext_id) {
		this.spu_ext_id = spu_ext_id;
	}
}
