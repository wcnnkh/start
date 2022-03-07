package io.basc.start.tencent.wx.api;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class GenerateUrlRequest extends JumpWxa {
	private static final long serialVersionUID = 1L;
	private boolean expire;
	private Integer expireType;
	private Long expireTime;
	private Long expireInterval;
}
