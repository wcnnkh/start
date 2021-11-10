package io.basc.satrt.app.admin.editable;

import io.basc.framework.beans.annotation.Autowired;
import io.basc.framework.context.result.Result;
import io.basc.framework.context.result.ResultFactory;
import io.basc.framework.data.ResourceStorageService;
import io.basc.framework.data.ResourceStorageService.UploadPolicy;
import io.basc.framework.http.HttpMethod;
import io.basc.framework.http.HttpStatus;
import io.basc.framework.mvc.HttpChannel;
import io.basc.framework.security.session.UserSession;
import io.basc.framework.util.XUtils;
import io.basc.framework.web.pattern.annotation.RequestMapping;
import io.basc.start.app.configure.AppConfigure;

import java.util.Date;

@RequestMapping(AppConfigure.ADMIN_CONTROLLER + "/editable")
public class EditableController {
	private final EditorRegistry editorRegistry;
	@Autowired(required = false)
	private ResourceStorageService resourceStorageService;
	@Autowired
	private ResultFactory resultFactory;

	public EditableController(EditorRegistry editorRegistry) {
		this.editorRegistry = editorRegistry;
	}

	@RequestMapping(value = "/**", methods = { HttpMethod.GET, HttpMethod.POST, HttpMethod.DELETE, HttpMethod.PUT })
	public Object editable(HttpChannel httpChannel) {
		Editor editor = editorRegistry.getEditor(httpChannel.getRequest().getPath(),
				httpChannel.getRequest().getRawMethod());
		if (editor == null) {
			httpChannel.getResponse().setStatusCode(HttpStatus.NOT_FOUND);
			return null;
		}
		return editor.doAction(httpChannel);
	}

	@RequestMapping(value = "/generate_upload_policy")
	public Result generateUploadPolicy(UserSession<Long> requestUser, String group, String suffix) {
		if (resourceStorageService == null) {
			return resultFactory.error("不支持资源上传");
		}

		UploadPolicy uploadPolicy = resourceStorageService.generatePolicy(
				group + "/" + requestUser.getUid() + "/" + XUtils.getUUID() + "." + suffix,
				new Date(System.currentTimeMillis() + 60000L));
		return resultFactory.success(uploadPolicy);
	}
}
