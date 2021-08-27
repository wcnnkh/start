package io.basc.platform.integration.bytedance.data;

import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

public class FansData implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "粉丝活跃天数分布 item: [\"0-4\",\"5~8\",\"9~12\",\"13~16\",\"17~20\",\"20+\"]", example = "[map[item:0-4 value:1000] map[item:5~8 value:500] map[item:9~12 value:400] map[item:13~16 value:300] map[item:17~20 value:300] map[item:20+ value:300]]")
	private List<FansProfileDistribution> active_days_distributions;
	@Schema(description = "粉丝年龄分布 item: [\"1-23\", \"24-30\", \"31-40\", \"41-50\", \"50-\"]", example = "[map[item:1-23 value:5000] map[item:24-30 value:3000] map[item:31-40 value:1000] map[item:41-50 value:600] map[item:50- value:400]]")
	private List<FansProfileDistribution> age_distributions;
	@Schema(description = "所有粉丝的数量", example = "10000")
	private Long all_fans_num;
	@Schema(description = "粉丝设备分布 item: [\"苹果\",\"华为\",\"三星\",\"小米\"...]", example = "[map[item:苹果 value:500] map[item:华为 value:300]]")
	private List<FansProfileDistribution> device_distributions;
	@Schema(description = "粉丝流量贡献 flow: [\"vv\",\"like_cnt\",\"comment_cnt\",\"share_video_cnt\"]", example = "[map[all_sum:1000 fans_sum:1000 flow:vv] map[all_sum:800 fans_sum:800 flow:like_cnt] map[all_sum:500 fans_sum:500 flow:comment_cnt] map[all_sum:300 fans_sum:300 flow:share_video_cnt]]")
	private List<FansProfileDistribution> flow_contributions;
	@Schema(description = "粉丝性别分布 item: [\"1\",\"2\"] (男:1,女:2)", example = "[map[item:1 value:6000] map[item:2 value:4000]]")
	private List<FansProfileDistribution> gender_distributions;
	@Schema(description = "粉丝地域分布 item: [\"北京\",\"福建\",\"香港\"...]", example = "[map[item:北京 value:6000] map[item:上海 value:4000]]")
	private List<FansProfileDistribution> geographical_distributions;
	@Schema(description = "粉丝兴趣分布 item: [\"生活\"\",\"美食\",\"旅行\"...]", example = "[map[item:时尚 value:1000] map[item:亲子 value:800] map[item:生活 value:500]]")
	private List<FansProfileDistribution> interest_distributions;

	public List<FansProfileDistribution> getActive_days_distributions() {
		return active_days_distributions;
	}

	public void setActive_days_distributions(List<FansProfileDistribution> active_days_distributions) {
		this.active_days_distributions = active_days_distributions;
	}

	public List<FansProfileDistribution> getAge_distributions() {
		return age_distributions;
	}

	public void setAge_distributions(List<FansProfileDistribution> age_distributions) {
		this.age_distributions = age_distributions;
	}

	public Long getAll_fans_num() {
		return all_fans_num;
	}

	public void setAll_fans_num(Long all_fans_num) {
		this.all_fans_num = all_fans_num;
	}

	public List<FansProfileDistribution> getDevice_distributions() {
		return device_distributions;
	}

	public void setDevice_distributions(List<FansProfileDistribution> device_distributions) {
		this.device_distributions = device_distributions;
	}

	public List<FansProfileDistribution> getFlow_contributions() {
		return flow_contributions;
	}

	public void setFlow_contributions(List<FansProfileDistribution> flow_contributions) {
		this.flow_contributions = flow_contributions;
	}

	public List<FansProfileDistribution> getGender_distributions() {
		return gender_distributions;
	}

	public void setGender_distributions(List<FansProfileDistribution> gender_distributions) {
		this.gender_distributions = gender_distributions;
	}

	public List<FansProfileDistribution> getGeographical_distributions() {
		return geographical_distributions;
	}

	public void setGeographical_distributions(List<FansProfileDistribution> geographical_distributions) {
		this.geographical_distributions = geographical_distributions;
	}

	public List<FansProfileDistribution> getInterest_distributions() {
		return interest_distributions;
	}

	public void setInterest_distributions(List<FansProfileDistribution> interest_distributions) {
		this.interest_distributions = interest_distributions;
	}
}
