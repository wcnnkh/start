package io.basc.start.tencent.wx.api.offiaccount.message.ordinary;

import io.basc.start.tencent.wx.api.offiaccount.message.Message;

/**
 * 公众号普通消息
 * @author shuchaowen
 *
 */
public abstract class OrdinaryMessage extends Message{
	private static final long serialVersionUID = 1L;
	private long msgId;//消息id，64位整型
	
	public long getMsgId() {
		return msgId;
	}
	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}
}
