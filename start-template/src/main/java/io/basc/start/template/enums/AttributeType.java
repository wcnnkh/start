package io.basc.start.template.enums;

public enum AttributeType {
	TEXT(1, "文本"), NUMBER(2, "数字"), SELECT(3, "下拉列表"), IMAGE(4, "单个图片"), IMAGES(5, "多个图片"),;

	private final int type;
	private final String describe;

	AttributeType(int type, String describe) {
		this.type = type;
		this.describe = describe;
	}

	public int getType() {
		return type;
	}

	public String getDescribe() {
		return describe;
	}
}
