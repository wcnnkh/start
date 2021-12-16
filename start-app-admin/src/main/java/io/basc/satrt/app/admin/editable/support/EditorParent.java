package io.basc.satrt.app.admin.editable.support;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.basc.framework.core.reflect.ReflectionUtils;
import io.basc.framework.http.HttpMethod;
import io.basc.framework.mapper.Field;
import io.basc.framework.mapper.FieldFeature;
import io.basc.framework.mapper.MapperUtils;
import io.basc.framework.mvc.HttpChannel;
import io.basc.framework.orm.support.OrmUtils;
import io.basc.framework.util.Pair;
import io.basc.framework.util.page.Pagination;
import io.basc.framework.web.message.model.ModelAndView;
import io.basc.satrt.app.admin.editable.Editor;
import io.basc.satrt.app.admin.editable.form.ImageInput;
import io.basc.satrt.app.admin.editable.form.Input;
import io.basc.satrt.app.admin.editable.form.SelectInput;
import io.basc.satrt.app.admin.editable.form.TextareaInput;
import io.basc.start.app.configure.AppConfigure;
import io.basc.start.data.DataService;
import io.basc.start.data.annotation.Editable;
import io.basc.start.data.annotation.Image;
import io.basc.start.data.annotation.Readonly;
import io.basc.start.data.annotation.Select;
import io.basc.start.data.annotation.SelectOption;
import io.basc.start.data.annotation.Textarea;

public class EditorParent implements Editor {
	private final Class<?> editableClass;
	private final DataService dataService;
	private final AppConfigure appConfigure;

	public EditorParent(DataService dataService, Class<?> editableClass, AppConfigure appConfigure) {
		this.editableClass = editableClass;
		this.dataService = dataService;
		this.appConfigure = appConfigure;
	}

	public AppConfigure getAppConfigure() {
		return appConfigure;
	}

	public Class<?> getEditableClass() {
		return editableClass;
	}

	public DataService getDataService() {
		return dataService;
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
		Editable editable = editableClass.getAnnotation(Editable.class);
		return editable == null ? getId() : editable.title();
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

		Pagination<Object> pagination = dataService.list(editableClass, requestBean, page, limit).shared();
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
				select.setOptions(dataService.queryOptions(selects.value(), null));
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
			input.setAutoFill(OrmUtils.getMapping().isAutoIncrement(editableClass, field.getGetter()));
			input.setName(field.getGetter().getName());
			input.setDescribe(OrmUtils.getMapping().getComment(editableClass, field.getGetter()));
			input.setPrimaryKey(OrmUtils.getMapping().isPrimaryKey(editableClass, field.getGetter()));
			if (input.getDescribe() == null) {
				input.setDescribe(input.getName());
			}

			if (field.isAnnotationPresent(Readonly.class)) {
				input.setReadonly(true);
			}

			input.setRequired(!OrmUtils.getMapping().isNullable(editableClass, field.getGetter()) || input.isPrimaryKey());
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
