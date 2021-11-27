package io.basc.star.aliyun.sms;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AliyunSendSmsResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	private AliyunSendSmsRequest request;
	private boolean success;
	private String message;
    public String bizId;
    public String code;
    public String requestId;
}
