package io.basc.start.cms.service;

import java.util.List;

import io.basc.framework.context.transaction.DataResult;
import io.basc.framework.context.transaction.Result;
import io.basc.start.cms.pojo.Category;
import io.basc.start.cms.pojo.CategoryInfo;
import io.basc.start.cms.pojo.Content;
import io.basc.start.cms.pojo.ContentInfo;
import io.basc.start.cms.pojo.Sku;
import io.basc.start.cms.pojo.SkuInfo;
import io.basc.start.template.pojo.ListRequest;

public interface CmsService {
	// 分类
	CategoryInfo getCategory(long categoryId);

	List<CategoryInfo> getCatetoryList(ListRequest request);

	DataResult<Category> saveOrUpdateCatetory(Category category);

	// 内容
	ContentInfo getContent(long contentId);

	List<ContentInfo> getContentList(ListRequest request);

	DataResult<Content> saveOrUpdateContent(Content content);

	// 规格

	SkuInfo getSku(long skuId);

	List<SkuInfo> getSkuList(long contentId, ListRequest request);

	DataResult<Sku> save(Sku sku);

	Result delete(long skuId);
}