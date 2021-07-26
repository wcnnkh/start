package scw.integration.bytedance.comment;

public enum CommentApiType {
	/**
	 * 普通用户
	 */
	ORDINARY("/item"),
	/**
	 * 企业号
	 */
	ENTERPRISE("/video");

	private final String contextPath;

	private CommentApiType(String contextPath) {
		this.contextPath = contextPath;
	}

	public String getContextPath() {
		return contextPath;
	}
}
