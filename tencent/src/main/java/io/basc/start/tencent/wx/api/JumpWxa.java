package io.basc.start.tencent.wx.api;

import lombok.Data;

@Data
public class JumpWxa {
	private String path;
	private String query;
	private String envVersion;
}
