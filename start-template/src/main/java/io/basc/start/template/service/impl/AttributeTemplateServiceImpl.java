package io.basc.start.template.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import io.basc.framework.context.annotation.ConditionalOnParameters;
import io.basc.framework.context.transaction.DataResult;
import io.basc.framework.context.transaction.Result;
import io.basc.framework.context.transaction.ResultFactory;
import io.basc.framework.db.Database;
import io.basc.framework.jdbc.EasySql;
import io.basc.framework.util.CollectionUtils;
import io.basc.start.template.pojo.AttributeDescribe;
import io.basc.start.template.pojo.AttributeTemplate;
import io.basc.start.template.pojo.AttributeValue;
import io.basc.start.template.pojo.ListRequest;
import io.basc.start.template.service.AttributeTemplateService;

@ConditionalOnParameters
public class AttributeTemplateServiceImpl extends TemplateServiceSupport implements AttributeTemplateService {

	public AttributeTemplateServiceImpl(Database db, ResultFactory resultFactory) {
		super(db, resultFactory);
	}

	@Override
	public DataResult<AttributeTemplate> saveOrUpdateAttributeTemplate(AttributeTemplate template) {
		if (db.isPresentById(AttributeTemplate.class, template.getTemplateId())) {
			db.update(template);
		} else {
			db.save(template);
		}
		return resultFactory.success(template);
	}

	@Override
	public AttributeTemplate getAttributeTemplate(long templateId) {
		return db.getById(AttributeTemplate.class, templateId);
	}

	@Override
	public List<AttributeDescribe> getAttributeDescribes(long templateId) {
		return db.getByIdList(AttributeDescribe.class, templateId);
	}

	@Override
	public Result saveOrUpdateAttributeDescribes(List<AttributeDescribe> describes) {
		if (CollectionUtils.isEmpty(describes)) {
			return resultFactory.success();
		}

		for (AttributeDescribe describe : describes) {
			if (db.isPresentById(AttributeDescribe.class, describe.getTemplateId())) {
				db.update(describe);
			} else {
				db.save(describe);
			}
		}
		return resultFactory.success();
	}

	@Override
	public List<AttributeTemplate> getAttributeTemplates(ListRequest request) {
		EasySql sql = new EasySql("select * from attribute_template where 1=1");
		if (request.getParentId() != null) {
			sql.and().eq("parent_template_id", request.getParentId());
		}
		if (request.getDelete() != null) {
			sql.and().eq("delete", request.getDelete());
		}
		return db.query(AttributeTemplate.class, sql).toList();
	}

	@Override
	public Map<String, AttributeDescribe> getAttributeDescribeInNames(long templateId, List<String> names) {
		return db.getInIds(AttributeDescribe.class, names, templateId).toMap();
	}

	@Override
	public List<AttributeValue> getAttributeValues(long sourceId) {
		List<AttributeValue> list = db.getByIdList(AttributeValue.class, sourceId);
		return list == null ? Collections.emptyList() : list;
	}

	@Override
	public Result saveOrUpdateAttributeValue(List<AttributeValue> attributes) {
		if (CollectionUtils.isEmpty(attributes)) {
			return resultFactory.success();
		}

		for (AttributeValue attribute : attributes) {
			AttributeValue old = db.getById(AttributeValue.class, attribute.getSourceId(), attribute.getName());
			if (old == null) {
				db.save(attribute);
			} else {
				db.update(attribute);
			}
		}
		return resultFactory.success();
	}

	@Override
	public Map<String, AttributeValue> getAttributeValueInNames(long sourceId, List<String> names) {
		Map<String, AttributeValue> map = db.getInIds(AttributeValue.class, names, sourceId).toMap();
		return map == null ? Collections.emptyMap() : map;
	}
}
