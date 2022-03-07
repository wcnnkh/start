package io.basc.start.tencent.wx.api;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class GenerateUrlRequest extends JumpWxa {
	private boolean expire;
	private Integer expireType;
	private Long expireTime;
	private Long expireInterval;
}
