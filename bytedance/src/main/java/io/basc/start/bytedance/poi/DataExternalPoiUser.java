package io.basc.start.bytedance.poi;

import io.basc.framework.util.Pair;
import io.basc.start.bytedance.ResponseCode;

import java.util.List;

public class DataExternalPoiUser extends ResponseCode {
	private static final long serialVersionUID = 1L;
	private List<Pair<String, String>> city_list;
	private List<Pair<String, String>> gender_list;
	private List<Pair<String, String>> age_list;

	public List<Pair<String, String>> getCity_list() {
		return city_list;
	}

	public void setCity_list(List<Pair<String, String>> city_list) {
		this.city_list = city_list;
	}

	public List<Pair<String, String>> getGender_list() {
		return gender_list;
	}

	public void setGender_list(List<Pair<String, String>> gender_list) {
		this.gender_list = gender_list;
	}

	public List<Pair<String, String>> getAge_list() {
		return age_list;
	}

	public void setAge_list(List<Pair<String, String>> age_list) {
		this.age_list = age_list;
	}
}
