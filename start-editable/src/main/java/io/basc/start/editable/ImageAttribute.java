package io.basc.start.editable;

import java.io.Serializable;

import lombok.Data;

@Data
public class ImageAttribute implements Serializable {
	private static final long serialVersionUID = 1L;
	private boolean multiple;
	private Integer width;
	private Integer height;
}
