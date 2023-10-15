package io.basc.start.template.service.impl;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.basc.framework.context.annotation.Autowired;
import io.basc.framework.context.transaction.ResultFactory;
import io.basc.framework.db.Database;
import io.basc.framework.mapper.Copy;
import io.basc.framework.util.CollectionUtils;
import io.basc.start.template.pojo.Attribute;
import io.basc.start.template.pojo.AttributeDescribe;
import io.basc.start.template.pojo.AttributeValue;
import io.basc.start.template.service.AttributeTemplateService;

public class TemplateServiceSupport {
	protected final Database db;
	protected final ResultFactory resultFactory;
	@Autowired
	protected AttributeTemplateService attributeTemplateService;

	public TemplateServiceSupport(Database db, ResultFactory resultFactory) {
		this.db = db;
		this.resultFactory = resultFactory;
	}

	public Map<String, Attribute> getAttributes(long sourceId, long templateId) {
		List<AttributeValue> attributeValueMap = attributeTemplateService.getAttributeValues(sourceId);
		if (CollectionUtils.isEmpty(attributeValueMap)) {
			return Collections.emptyMap();
		}

		Map<String, AttributeDescribe> attributeDescribeMap = attributeTemplateService.getAttributeDescribeInNames(
				templateId, attributeValueMap.stream().map((e) -> e.getName()).collect(Collectors.toList()));

		Map<String, Attribute> map = new LinkedHashMap<>();
		for (AttributeValue value : attributeValueMap) {
			Attribute attribute = new Attribute();
			Copy.copy(value, attribute);
			attribute.setDescribe(attributeDescribeMap.get(value.getName()));
		}
		return map;
	}
}