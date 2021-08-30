package io.basc.start.bytedance.data;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

public class Album implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "猫眼id：只有电影榜返回，可能为空", example = "1250696")
	private String maoyan_id;
	@Schema(description = "片名", example = "囧妈")
	private String name;
	@Schema(description = "英文片名", example = "Lost in Russia")
	private String name_en;
	@Schema(description = "2020-01-25")
	private String release_date;
	@Schema(description = "导演", example = "[徐峥]")
	private String[] directors;
	@Schema(description = "视频讨论度", example = "789200")
	private Long discussion_hot;
	@Schema(description = "海报", example = "https://p3-dy.bytecdn.cn/obj/compass/9ac412ae054b57f69c0967a8eb5e25c9")
	private String poster;
	@Schema(description = "类型标签", example = "[喜剧]")
	private String[] tags;
	@Schema(description = "类型：1=电影 2=电视剧 3=综艺", example = "1")
	private Integer type;
	@Schema(description = "演员", example = "[徐峥 袁泉 沈腾 吴云芳 陈奇 黄梅莹 欧丽娅 贾冰 郭京飞]")
	private String[] actors;
	@Schema(description = "地区", example = "[中国]")
	private String[] areas;
	@Schema(description = "热度值", example = "1.361e+06")
	private Long hot;
	@Schema(description = "搜索指数", example = "684900")
	private Long search_hot;
	@Schema(description = "影片ID", example = "6399487713065566465")
	private String id;
	@Schema(description = "账号影响力", example = "789200")
	private Long influence_hot;
	@Schema(description = "话题热度值", example = "684900")
	private Long topic_hot;

	public String getMaoyan_id() {
		return maoyan_id;
	}

	public void setMaoyan_id(String maoyan_id) {
		this.maoyan_id = maoyan_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName_en() {
		return name_en;
	}

	public void setName_en(String name_en) {
		this.name_en = name_en;
	}

	public String getRelease_date() {
		return release_date;
	}

	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}

	public String[] getDirectors() {
		return directors;
	}

	public void setDirectors(String[] directors) {
		this.directors = directors;
	}

	public Long getDiscussion_hot() {
		return discussion_hot;
	}

	public void setDiscussion_hot(Long discussion_hot) {
		this.discussion_hot = discussion_hot;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String[] getActors() {
		return actors;
	}

	public void setActors(String[] actors) {
		this.actors = actors;
	}

	public String[] getAreas() {
		return areas;
	}

	public void setAreas(String[] areas) {
		this.areas = areas;
	}

	public Long getHot() {
		return hot;
	}

	public void setHot(Long hot) {
		this.hot = hot;
	}

	public Long getSearch_hot() {
		return search_hot;
	}

	public void setSearch_hot(Long search_hot) {
		this.search_hot = search_hot;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getInfluence_hot() {
		return influence_hot;
	}

	public void setInfluence_hot(Long influence_hot) {
		this.influence_hot = influence_hot;
	}

	public Long getTopic_hot() {
		return topic_hot;
	}

	public void setTopic_hot(Long topic_hot) {
		this.topic_hot = topic_hot;
	}
}
