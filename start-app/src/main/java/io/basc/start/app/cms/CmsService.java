package io.basc.start.app.cms;

import java.util.List;

public interface CmsService {
	/**
	 * 保存或修改横版
	 * 
	 * @param template
	 * @param attrs
	 * @return
	 */
	Template saveOrUpdate(Template template, List<TemplateAttr> attrs);

	/**
	 * 保存或更新渠道
	 * 
	 * @param channel
	 * @param attrValues
	 * @return
	 */
	Channel saveOrUpdate(Channel channel, List<TemplateAttrValue> attrValues);

	/**
	 * 保存或更新内容
	 * 
	 * @param content
	 * @param attrValues
	 * @return
	 */
	Content saveOrUpdate(Content content, List<TemplateAttrValue> attrValues);

	Channel getChannel(long channelId);

	List<Channel> getSubChannelList(long channelId);
}
