package io.basc.platform.integration.bytedance;

public class ResponseExtra extends ResponseSubCode {
	private static final long serialVersionUID = 1L;
	private String logid;
	private Long now;

	public String getLogid() {
		return logid;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}

	public Long getNow() {
		return now;
	}

	public void setNow(Long now) {
		this.now = now;
	}
}
