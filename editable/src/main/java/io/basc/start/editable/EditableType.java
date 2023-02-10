package io.basc.start.editable;

/**
 * 可编辑的类型
 * 
 * @author wcnnkh
 *
 */
public enum EditableType {
	DEFAULT(0),
	IMAGE(1), 
	TEXTAREA(2), 
	SELECT(3), ;

	private final int value;

	private EditableType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static EditableType forValue(int value) {
		for (EditableType type : values()) {
			if (type.value == value) {
				return type;
			}
		}
		return null;
	}
}
