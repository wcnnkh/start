package io.basc.satrt.app.admin.editable;

import io.basc.start.editable.EditableMapper;

import java.util.List;

public interface EditorMapper extends EditableMapper {
	List<Editor> resolve(Class<?> clazz);
}
