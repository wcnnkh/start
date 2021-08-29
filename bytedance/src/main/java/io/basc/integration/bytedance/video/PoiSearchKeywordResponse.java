package io.basc.integration.bytedance.video;

import io.basc.integration.bytedance.PagingResponseCode;

import java.util.List;

public class PoiSearchKeywordResponse extends PagingResponseCode {
	private static final long serialVersionUID = 1L;
	private List<Poi> pois;

	public List<Poi> getPois() {
		return pois;
	}

	public void setPois(List<Poi> pois) {
		this.pois = pois;
	}
}
