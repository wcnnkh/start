package io.basc.start.template.service;

import java.util.List;
import java.util.Map;

import io.basc.framework.context.transaction.DataResult;
import io.basc.framework.context.transaction.Result;
import io.basc.start.template.pojo.AttributeDescribe;
import io.basc.start.template.pojo.AttributeTemplate;
import io.basc.start.template.pojo.AttributeValue;
import io.basc.start.template.pojo.ListRequest;

public interface AttributeTemplateService {
	List<AttributeTemplate> getAttributeTemplates(ListRequest request);

	DataResult<AttributeTemplate> saveOrUpdateAttributeTemplate(AttributeTemplate template);

	AttributeTemplate getAttributeTemplate(long templateId);

	List<AttributeDescribe> getAttributeDescribes(long templateId);

	Result saveOrUpdateAttributeDescribes(List<AttributeDescribe> describes);

	Map<String, AttributeDescribe> getAttributeDescribeInNames(long templateId, List<String> names);

	List<AttributeValue> getAttributeValues(long sourceId);

	Result saveOrUpdateAttributeValue(List<AttributeValue> attributes);

	Map<String, AttributeValue> getAttributeValueInNames(long sourceId, List<String> names);
}
