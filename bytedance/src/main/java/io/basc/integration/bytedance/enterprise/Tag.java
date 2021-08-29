package io.basc.integration.bytedance.enterprise;

import java.io.Serializable;

public class Tag implements Serializable {
	private static final long serialVersionUID = 1L;
	private String tag_id;
	private String tag_name;

	public String getTag_id() {
		return tag_id;
	}

	public void setTag_id(String tag_id) {
		this.tag_id = tag_id;
	}

	public String getTag_name() {
		return tag_name;
	}

	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}
}
