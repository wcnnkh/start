package io.basc.start.editable;

import java.io.Serializable;

import lombok.Data;

@Data
public class EditableAttributes implements Serializable {
	private static final long serialVersionUID = 1L;
	private EditableType type;
	private boolean readonly;
}
