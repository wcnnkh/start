package io.basc.start.bytedance.video;

import java.util.List;

import io.basc.start.bytedance.PagingResponseCode;

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
