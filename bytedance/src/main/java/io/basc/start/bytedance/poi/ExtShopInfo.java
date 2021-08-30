package io.basc.start.bytedance.poi;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

public class ExtShopInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "外部商户ID")
	private String ext_shop_id;
	@Schema(description = "抖音商户ID")
	private String shop_id;

	public String getExt_shop_id() {
		return ext_shop_id;
	}

	public void setExt_shop_id(String ext_shop_id) {
		this.ext_shop_id = ext_shop_id;
	}

	public String getShop_id() {
		return shop_id;
	}

	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}
}
