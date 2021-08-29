package io.basc.app.admin.web.editable.support;

import io.basc.app.admin.web.editable.DataManager;
import io.basc.app.admin.web.editable.Editor;
import io.basc.app.admin.web.editable.annotation.Editable;
import io.basc.app.admin.web.editable.annotation.Image;
import io.basc.app.admin.web.editable.annotation.Readonly;
import io.basc.app.admin.web.editable.annotation.Select;
import io.basc.app.admin.web.editable.annotation.SelectOption;
import io.basc.app.admin.web.editable.annotation.Textarea;
import io.basc.app.admin.web.editable.form.ImageInput;
import io.basc.app.admin.web.editable.form.Input;
import io.basc.app.admin.web.editable.form.SelectInput;
import io.basc.app.admin.web.editable.form.TextareaInput;
import io.basc.app.user.security.SecurityProperties;
import io.basc.framework.core.annotation.AnnotatedElementUtils;
import io.basc.framework.core.reflect.ReflectionUtils;
import io.basc.framework.http.HttpMethod;
import io.basc.framework.mapper.Field;
import io.basc.framework.mapper.FieldFeature;
import io.basc.framework.mapper.MapperUtils;
import io.basc.framework.mvc.HttpChannel;
import io.basc.framework.orm.annotation.PrimaryKey;
import io.basc.framework.orm.sql.annotation.AutoIncrement;
import io.basc.framework.util.Pair;
import io.basc.framework.web.model.Page;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class EditorParent implements Editor {
	private final Class<?> editableClass;
	private final DataManager dataManager;
	private final SecurityProperties securityProperties;

	public EditorParent(DataManager dataManager, Class<?> editableClass, SecurityProperties securityProperties) {
		this.editableClass = editableClass;
		this.dataManager = dataManager;
		this.securityProperties = securityProperties;
	}

	public SecurityProperties getSecurityProperties() {
		return securityProperties;
	}

	public Class<?> getEditableClass() {
		return editableClass;
	}

	public DataManager getDataManager() {
		return dataManager;
	}

	@Override
	public String getPath() {
		return securityProperties.getController() + "/" + editableClass.getName() + "/root";
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
		Editable editable = editableClass.getAnnotation(Editable.class);
		return editable == null ? getId() : editable.name();
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
		Integer page = httpChannel.getValue("page").getAsInteger();
		if (page == null || page < 1) {
			page = 1;
		}

		Integer limit = httpChannel.getValue("limit").getAsInteger();
		if (limit == null || limit < 1) {
			limit = 10;
		}

		io.basc.framework.util.page.Page<Object> pagination = dataManager.list(editableClass, requestBean, page, limit);
		Page view = new Page("/admin/editable/list.ftl");
		long maxPage = pagination == null ? 1 : pagination.getPages();
		long currentPage = Math.min(page, maxPage);
		view.put("page", currentPage);
		view.put("limit", limit);
		view.put("list", pagination == null ? null : pagination.rows());
		view.put("totalCount", pagination == null ? 0 : pagination.getTotal());
		view.put("query", requestBean);
		view.put("fields", getInputs(requestBean));
		view.put("maxPage", maxPage);
		view.put("name", getName());
		return view;
	}

	private Input createInput(Object query, Field field) {
		Select selects = field.getAnnotation(Select.class);
		if (selects != null) {
			SelectInput select = new SelectInput(selects);
			select.setQueryClass(selects.value());

			if (select.getQueryClass().isEnum()) {
				Field optionField = MapperUtils.getFields(select.getQueryClass()).all()
						.accept(FieldFeature.IGNORE_STATIC).accept(FieldFeature.SUPPORT_GETTER)
						.accept((f) -> f.isAnnotationPresent(SelectOption.class)).first();
				Enum<?>[] values = ReflectionUtils.values(select.getQueryClass());
				List<Pair<String, String>> list = new ArrayList<Pair<String, String>>();
				for (int i = 0; i < values.length; i++) {
					String key = values[i].name();
					String value = String.valueOf(optionField == null ? key : optionField.getGetter().get(values[i]));
					list.add(new Pair<String, String>(key, value));
				}
				select.setOptions(list);
			} else {
				select.setOptions(dataManager.queryOptions(selects.value(), null));
			}
			return select;
		}

		Image images = field.getAnnotation(Image.class);
		if (images != null) {
			return new ImageInput(images);
		}

		if (field.isAnnotationPresent(Textarea.class)) {
			return new TextareaInput();
		}
		return new Input();
	}

	@Override
	public List<Input> getInputs(Object query) {
		List<Input> list = new ArrayList<Input>();
		for (Field field : MapperUtils.getFields(editableClass).entity().all()) {
			Input input = createInput(query, field);
			input.setAutoFill(field.isAnnotationPresent(AutoIncrement.class));
			input.setName(field.getGetter().getName());
			input.setDescribe(AnnotatedElementUtils.getDescription(field));
			input.setPrimaryKey(field.getGetter().isAnnotationPresent(PrimaryKey.class));
			if (input.getDescribe() == null) {
				input.setDescribe(input.getName());
			}

			if (field.isAnnotationPresent(Readonly.class)) {
				input.setReadonly(true);
			}

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
