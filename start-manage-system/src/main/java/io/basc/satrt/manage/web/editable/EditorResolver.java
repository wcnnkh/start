package io.basc.satrt.manage.web.editable;

import java.util.List;

public interface EditorResolver {
	boolean canResolve(Class<?> clazz);

	List<Editor> resolve(Class<?> clazz);
}
