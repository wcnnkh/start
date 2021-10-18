package io.basc.satrt.app.admin.editable;

import io.basc.framework.lang.AlreadyExistsException;
import io.basc.framework.mvc.security.HttpActionAuthorityManager;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EditorRegistry {
	private final HttpActionAuthorityManager httpActionAuthorityManager;
	private final ConcurrentHashMap<String, Map<String, Editor>> map = new ConcurrentHashMap<String, Map<String, Editor>>();

	public EditorRegistry(HttpActionAuthorityManager httpActionAuthorityManager) {
		this.httpActionAuthorityManager = httpActionAuthorityManager;
	}

	public void register(Editor editor) {
		String path = editor.getPath();
		Map<String, Editor> methodMap = map.get(path);
		if (methodMap == null) {
			methodMap = new HashMap<String, Editor>();
			Map<String, Editor> oldMethodMap = map.putIfAbsent(path, methodMap);
			if (oldMethodMap != null) {
				methodMap = oldMethodMap;
			}
		}

		if (methodMap.containsKey(editor.getMethod())) {
			throw new AlreadyExistsException(editor.toString());
		}

		methodMap.put(editor.getMethod(), editor);
		httpActionAuthorityManager.register(editor);
	}

	public Editor getEditor(String path, String httpMethod) {
		Map<String, Editor> methodMap = map.get(path);
		if (methodMap == null) {
			return null;
		}

		return methodMap.get(httpMethod);
	}
}
