package scw.integration.bytedance.enterprise;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

public class EnterpriseImCardSaveRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "	卡片id，创建时不传；更新时必传。同一个用户的卡片id不可重复")
	private String card_id;
	@Schema(description = "卡片类型: \"question_list\"(问题列表)", example = "question_list", required = true)
	@NotNull
	private String card_type;
	@Schema(description = "``` 卡片内容字段 json序列化成string， { \"question_list\": { \"text\": \"有什么疑问呢\", \"questions\": [{ \"name\": \"问题1\", \"text\": \"关键词1\" }, { \"name\": \"问题2\", \"text\": \"关键词2\" } ] } } ```", example = "{ \"text\": \"有什么疑问呢\", \"questions\": [ {\"name\":\"问题1\",\"text\":\"关键词1\"}, {\"name\":\"问题2\",\"text\":\"关键词2\"} ] }", required = true)
	@NotNull
	private String content;
}
