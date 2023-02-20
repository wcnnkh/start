package io.basc.start.template.pojo;

import io.basc.framework.data.domain.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ListRequest extends PageRequest {
	private static final long serialVersionUID = 1L;
	private Long parentId;
	private Boolean delete;
	private String keyword;
}
