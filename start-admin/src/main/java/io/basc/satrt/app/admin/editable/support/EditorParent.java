package io.basc.satrt.app.admin.editable.support;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.basc.framework.convert.TypeDescriptor;
import io.basc.framework.core.reflect.ReflectionUtils;
import io.basc.framework.env.Sys;
import io.basc.framework.http.HttpMethod;
import io.basc.framework.mapper.Field;
import io.basc.framework.mvc.HttpChannel;
import io.basc.framework.orm.ForeignKey;
import io.basc.framework.orm.Property;
import io.basc.framework.orm.repository.CurdOperations;
import io.basc.framework.util.Pair;
import io.basc.framework.util.StringUtils;
import io.basc.framework.util.page.Pagination;
import io.basc.framework.web.message.model.ModelAndView;
import io.basc.satrt.app.admin.editable.Editor;
import io.basc.satrt.app.admin.editable.form.ImageInput;
import io.basc.satrt.app.admin.editable.form.Input;
import io.basc.satrt.app.admin.editable.form.SelectInput;
import io.basc.satrt.app.admin.editable.form.TextareaInput;
import io.basc.start.editable.EditableAttributes;
import io.basc.start.editable.EditableMapper;
import io.basc.start.editable.EditableType;
import io.basc.start.usercenter.security.UsercenterSecurityConfigure;

public class EditorParent implements Editor {
	private final EditableMapper mapper;
	private final Class<?> editableClass;
	private final UsercenterSecurityConfigure appConfigure;
	private final CurdOperations repository;

	public EditorParent(EditableMapper mapper, CurdOperations repository, Class<?> editableClass,
			UsercenterSecurityConfigure appConfigure) {
		this.editableClass = editableClass;
		this.repository = repository;
		this.mapper = mapper;
		this.appConfigure = appConfigure;
	}

	public UsercenterSecurityConfigure getAppConfigure() {
		return appConfigure;
	}

	public Class<?> getEditableClass() {
		return editableClass;
	}

	public EditableMapper getMapper() {
		return mapper;
	}

	public CurdOperations getRepository() {
		return repository;
	}

	@Override
	public String getPath() {
		return getAppConfigure().getController() + "/editable/" + editableClass.getName() + "/root";
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
		Integer page = httpChannel.getAsInt("page");
		if (page < 1) {
			page = 1;
		}

		Integer limit = httpChannel.getAsInt("limit");
		if (limit == null || limit < 1) {
			limit = 10;
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		Pagination<Object> pagination = getRepository().query((Class) editableClass, requestBean).jumpToPage(page,
				limit);
		ModelAndView view = new ModelAndView("/io/basc/start/admin/web/editable/list.ftl");
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
						TypeDescriptor.valueOf(String.class), Sys.getEnv().getConversionService());
				select.setOptions(options);
			} else {
				List<?> list = getRepository().queryAll(foreignKey.getEntityClass()).collect(Collectors.toList());
				List<Pair<String, String>> options = mapper.parseOptions(foreignKey.getEntityClass(), list,
						foreignKey.getName(), TypeDescriptor.valueOf(String.class),
						TypeDescriptor.valueOf(String.class), Sys.getEnv().getConversionService());
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
		for (Property field : getMapper().getStructure(editableClass).entity().all()) {
			EditableAttributes attributes = mapper.getEditableAttributes(editableClass, field.getGetter());
			if (attributes == null) {
				continue;
			}

			Input input = createInput(attributes, query, field);
			input.setAutoFill(field.isAutoIncrement());
			input.setName(field.getGetter().getName());
			input.setDescribe(field.getComment());
			input.setPrimaryKey(field.isPrimaryKey());
			if (input.getDescribe() == null) {
				input.setDescribe(input.getName());
			}

			if (attributes.isReadonly()) {
				input.setReadonly(true);
			}

			input.setRequired(!field.isNullable() || input.isPrimaryKey());
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
