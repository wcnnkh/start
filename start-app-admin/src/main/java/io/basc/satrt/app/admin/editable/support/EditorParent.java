package io.basc.satrt.app.admin.editable.support;

import io.basc.framework.convert.TypeDescriptor;
import io.basc.framework.core.reflect.ReflectionUtils;
import io.basc.framework.data.domain.PageRequest;
import io.basc.framework.env.Sys;
import io.basc.framework.http.HttpMethod;
import io.basc.framework.mapper.Field;
import io.basc.framework.mapper.MapperUtils;
import io.basc.framework.mvc.HttpChannel;
import io.basc.framework.orm.ForeignKey;
import io.basc.framework.orm.repository.CurdRepositoryRegistry;
import io.basc.framework.orm.support.OrmUtils;
import io.basc.framework.util.Pair;
import io.basc.framework.util.StringUtils;
import io.basc.framework.util.page.Pagination;
import io.basc.framework.web.message.model.ModelAndView;
import io.basc.satrt.app.admin.editable.Editor;
import io.basc.satrt.app.admin.editable.form.ImageInput;
import io.basc.satrt.app.admin.editable.form.Input;
import io.basc.satrt.app.admin.editable.form.SelectInput;
import io.basc.satrt.app.admin.editable.form.TextareaInput;
import io.basc.start.app.configure.AppConfigure;
import io.basc.start.editable.EditableAttributes;
import io.basc.start.editable.EditableMapper;
import io.basc.start.editable.EditableType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EditorParent implements Editor {
	private final EditableMapper mapper;
	private final Class<?> editableClass;
	private final AppConfigure appConfigure;
	private final CurdRepositoryRegistry curdRepositoryRegistry;

	public EditorParent(EditableMapper mapper, CurdRepositoryRegistry curdRepositoryRegistry, Class<?> editableClass,
			AppConfigure appConfigure) {
		this.editableClass = editableClass;
		this.curdRepositoryRegistry = curdRepositoryRegistry;
		this.mapper = mapper;
		this.appConfigure = appConfigure;
	}

	public AppConfigure getAppConfigure() {
		return appConfigure;
	}

	public Class<?> getEditableClass() {
		return editableClass;
	}

	public EditableMapper getMapper() {
		return mapper;
	}

	public CurdRepositoryRegistry getCurdRepositoryRegistry() {
		return curdRepositoryRegistry;
	}

	@Override
	public String getPath() {
		return getAppConfigure().getAdminController() + "/editable/" + editableClass.getName() + "/root";
	}

	@Override
	public String getMethod() {
		return HttpMethod.GET.name();
	}

	@Override
	public String getId() {
		return editableClass.getName() + "#root";
	}

	@Override
	public String getParentId() {
		return null;
	}

	@Override
	public String getName() {
		String name = mapper.getComment(editableClass);
		return StringUtils.isEmpty(name) ? getId() : name;
	}

	@Override
	public boolean isMenu() {
		return true;
	}

	@Override
	public Map<String, String> getAttributeMap() {
		return Collections.emptyMap();
	}

	@Override
	public Object doAction(HttpChannel httpChannel) {
		Object requestBean = httpChannel.getInstance(editableClass);
		Integer page = httpChannel.getInteger("page");
		if (page == null || page < 1) {
			page = 1;
		}

		Integer limit = httpChannel.getInteger("limit");
		if (limit == null || limit < 1) {
			limit = 10;
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		Pagination<Object> pagination = curdRepositoryRegistry.getCurdRepository((Class) editableClass)
				.pagingQuery(requestBean, new PageRequest(page, limit)).shared();
		ModelAndView view = new ModelAndView("/io/basc/start/app/admin/web/editable/list.ftl");
		long maxPage = pagination == null ? 1 : pagination.getPages();
		long currentPage = Math.min(page, maxPage);
		view.put("page", currentPage);
		view.put("limit", limit);
		view.put("list", pagination == null ? Collections.emptyList() : pagination.getList());
		view.put("totalCount", pagination == null ? 0 : pagination.getTotal());
		view.put("info", requestBean);
		view.put("fields", getInputs(requestBean).stream().map((field) -> {
			field.setRequired(false);
			return field;
		}).collect(Collectors.toList()));
		view.put("maxPage", maxPage);
		view.put("name", getName());
		return view;
	}

	private Input createInput(EditableAttributes attributes, Object query, Field field) {
		if (attributes.getType() == EditableType.SELECT) {
			ForeignKey foreignKey = mapper.getForeignKey(editableClass, field.getGetter());
			SelectInput select = new SelectInput();
			if (foreignKey.getEntityClass().isEnum()) {
				Object[] values = ReflectionUtils.values(foreignKey.getEntityClass());
				List<Pair<String, String>> options = mapper.parseOptions(foreignKey.getEntityClass(),
						Arrays.asList(values), foreignKey.getName(), TypeDescriptor.valueOf(String.class),
						TypeDescriptor.valueOf(String.class), Sys.env.getConversionService());
				select.setOptions(options);
			} else {
				List<?> list = curdRepositoryRegistry.getCurdRepository(foreignKey.getEntityClass()).queryAll()
						.collect(Collectors.toList());
				List<Pair<String, String>> options = mapper.parseOptions(foreignKey.getEntityClass(), list,
						foreignKey.getName(), TypeDescriptor.valueOf(String.class),
						TypeDescriptor.valueOf(String.class), Sys.env.getConversionService());
				select.setOptions(options);
			}
			return select;
		} else if (attributes.getType() == EditableType.IMAGE) {
			return new ImageInput(mapper.getImageAttributes(editableClass, field.getGetter()));
		} else if (attributes.getType() == EditableType.TEXTAREA) {
			return new TextareaInput();
		}
		return new Input();
	}

	@Override
	public List<Input> getInputs(Object query) {
		List<Input> list = new ArrayList<Input>();
		for (Field field : MapperUtils.getFields(editableClass).entity().all()) {
			EditableAttributes attributes = mapper.getEditableAttributes(editableClass, field.getGetter());
			if (attributes == null) {
				continue;
			}

			Input input = createInput(attributes, query, field);
			input.setAutoFill(OrmUtils.getMapping().isAutoIncrement(editableClass, field.getGetter()));
			input.setName(field.getGetter().getName());
			input.setDescribe(OrmUtils.getMapping().getComment(editableClass, field.getGetter()));
			input.setPrimaryKey(OrmUtils.getMapping().isPrimaryKey(editableClass, field.getGetter()));
			if (input.getDescribe() == null) {
				input.setDescribe(input.getName());
			}

			if (attributes.isReadonly()) {
				input.setReadonly(true);
			}

			input.setRequired(
					!OrmUtils.getMapping().isNullable(editableClass, field.getGetter()) || input.isPrimaryKey());
			list.add(input);
		}
		return list;
	}

	@Override
	public String toString() {
		return editableClass.getName() + "[id=" + getId() + ", parentId=" + getParentId() + ", path=" + getPath()
				+ ", method=" + getMethod() + ", name=" + getName() + "]";
	}
}
