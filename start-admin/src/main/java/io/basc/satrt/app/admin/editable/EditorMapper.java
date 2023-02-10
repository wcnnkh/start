package io.basc.satrt.app.admin.editable;

import java.util.List;

import io.basc.start.editable.EditableMapper;

public interface EditorMapper extends EditableMapper {
	List<Editor> resolve(Class<?> clazz);
}
