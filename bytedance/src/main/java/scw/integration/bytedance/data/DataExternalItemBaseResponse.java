package scw.integration.bytedance.data;

import scw.integration.bytedance.ResponseCode;

public class DataExternalItemBaseResponse extends ResponseCode {
	private static final long serialVersionUID = 1L;
	private DataExternalItemBaseResult result;

	public DataExternalItemBaseResult getResult() {
		return result;
	}

	public void setResult(DataExternalItemBaseResult result) {
		this.result = result;
	}
}
