package io.basc.start.cms.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import io.basc.framework.context.transaction.DataResult;
import io.basc.framework.context.transaction.Result;
import io.basc.framework.context.transaction.ResultFactory;
import io.basc.framework.db.DB;
import io.basc.framework.mapper.Copy;
import io.basc.framework.sql.EasySql;
import io.basc.start.cms.pojo.Category;
import io.basc.start.cms.pojo.CategoryInfo;
import io.basc.start.cms.pojo.Content;
import io.basc.start.cms.pojo.ContentInfo;
import io.basc.start.cms.pojo.Sku;
import io.basc.start.cms.pojo.SkuInfo;
import io.basc.start.cms.service.CmsService;
import io.basc.start.template.pojo.ListRequest;
import io.basc.start.template.service.impl.TemplateServiceSupport;

/**
 * TODO 还未完成
 * 
 * @author wcnnkh
 *
 */
public class CmsServiceImpl extends TemplateServiceSupport implements CmsService {

	public CmsServiceImpl(DB db, ResultFactory resultFactory) {
		super(db, resultFactory);
	}

	@Override
	public CategoryInfo getCategory(long categoryId) {
		Category category = db.getById(Category.class, categoryId);
		if (category == null) {
			return null;
		}

		CategoryInfo info = new CategoryInfo();
		Copy.copy(category, info);
		info.setAttributes(getAttributes(categoryId, category.getTemplateId()));
		return info;
	}

	@Override
	public List<CategoryInfo> getCatetoryList(ListRequest request) {
		EasySql sql = new EasySql("select * from category where 1=1");
		if (request.getParentId() != null) {
			sql.and().eq("parentId", request.getParentId());
		}

		if (request.getDelete() != null) {
			sql.and().eq("delete", request.getDelete());
		}

		List<Category> list = db.query(Category.class, sql).toList();
		if (list == null) {
			return Collections.emptyList();
		}

		return list.stream().map((category) -> {
			CategoryInfo info = new CategoryInfo();
			Copy.copy(category, info);
			info.setAttributes(getAttributes(category.getId(), category.getTemplateId()));
			return info;
		}).collect(Collectors.toList());
	}

	@Override
	public DataResult<Category> saveOrUpdateCatetory(Category category) {
		db.saveOrUpdate(category);
		return resultFactory.success(category);
	}

	@Override
	public ContentInfo getContent(long contentId) {
		Content content = db.getById(Content.class, contentId);
		if (content == null) {
			return null;
		}

		ContentInfo info = new ContentInfo();
		Copy.copy(content, info);
		info.setAttributes(getAttributes(contentId, content.getTemplateId()));
		return info;
	}

	@Override
	public List<ContentInfo> getContentList(ListRequest request) {
		EasySql sql = new EasySql("select * from content where 1=1");
		if (request.getDelete() != null) {
			sql.and().eq("delete", request.getDelete());
		}

		List<Content> list = db.query(Content.class, sql).toList();
		if (list == null) {
			return Collections.emptyList();
		}

		return list.stream().map((content) -> {
			ContentInfo info = new ContentInfo();
			Copy.copy(content, info);
			info.setAttributes(getAttributes(content.getId(), content.getTemplateId()));
			return info;
		}).collect(Collectors.toList());
	}

	@Override
	public DataResult<Content> saveOrUpdateContent(Content content) {
		db.saveOrUpdate(content);
		return resultFactory.success(content);
	}

	@Override
	public SkuInfo getSku(long skuId) {
		Sku sku = db.getById(Sku.class, skuId);
		if (sku == null) {
			return null;
		}

		SkuInfo info = new SkuInfo();
		Copy.copy(sku, info);
		info.setAttributes(getAttributes(skuId, sku.getTemplateId()));
		return info;
	}

	@Override
	public List<SkuInfo> getSkuList(long contentId, ListRequest request) {
		EasySql sql = new EasySql("select * from sku where contentId=?", contentId);
		if (request.getParentId() != null) {
			sql.and().eq("parentId", request.getParentId());
		}

		if (request.getDelete() != null) {
			sql.and().eq("delete", request.getDelete());
		}

		List<Sku> list = db.query(Sku.class, sql).toList();
		if (list == null) {
			return Collections.emptyList();
		}

		return list.stream().map((sku) -> {
			SkuInfo info = new SkuInfo();
			Copy.copy(sku, info);
			info.setAttributes(getAttributes(sku.getId(), sku.getTemplateId()));
			return info;
		}).collect(Collectors.toList());
	}

	@Override
	public DataResult<Sku> save(Sku sku) {
		db.save(sku);
		return resultFactory.success(sku);
	}

	@Override
	public Result delete(long skuId) {
		Sku sku = db.getById(Sku.class, skuId);
		if (sku == null) {
			return resultFactory.error("规格不存在");
		}

		sku.setDelete(true);
		db.update(sku);
		return resultFactory.success();
	}

}
