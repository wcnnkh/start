package io.basc.integration.bytedance.data;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

public class FansProfileDistribution implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "分布的种类")
	private String item;
	@Schema(description = "分布的数值")
	private Long value;
}
