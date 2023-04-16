package io.basc.start.editable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import io.basc.framework.convert.ConversionService;
import io.basc.framework.convert.TypeDescriptor;
import io.basc.framework.core.reflect.ReflectionUtils;
import io.basc.framework.lang.Nullable;
import io.basc.framework.mapper.Field;
import io.basc.framework.mapper.Structure;
import io.basc.framework.orm.ObjectRelationalFactory;
import io.basc.framework.orm.Property;
import io.basc.framework.util.Assert;
import io.basc.framework.util.CollectionUtils;
import io.basc.framework.util.Pair;
import io.basc.framework.util.StringUtils;

public interface EditableMapper extends EditableResolver, ObjectRelationalFactory {
	/**
	 * 格式选项
	 * 
	 * @param entityClass
	 * @param entitys
	 * @param keyName             如果名称为空,那么将使用主键或自身
	 * @param keyTypeDescriptor
	 * @param valueTypeDescriptor
	 * @param conversionService
	 * @return
	 */
	@SuppressWarnings("unchecked")
	default <K, V, S> List<Pair<K, V>> parseOptions(Class<? extends S> entityClass, Collection<? extends S> entitys,
			@Nullable String keyName, TypeDescriptor keyTypeDescriptor, TypeDescriptor valueTypeDescriptor,
			ConversionService conversionService) {
		if (CollectionUtils.isEmpty(entitys)) {
			return Collections.emptyList();
		}

		Structure<? extends Property> fields = getStructure(entityClass).getters().shared();
		Field keyField = null;
		if (StringUtils.isEmpty(keyName)) {
			// 主键
			keyField = fields.all().filter((e) -> isPrimaryKey(entityClass, e.getGetter())).findFirst()
					.orElse(null);
		} else {
			keyField = fields.getByName(keyName);
			Assert.requiredArgument(keyField != null, "keyField");
		}

		Field valueField = fields.all().stream().filter((e) -> isDisplay(entityClass, e.getGetter())).findFirst()
				.orElse(null);

		TypeDescriptor keyType = keyField == null ? TypeDescriptor.valueOf(String.class)
				: new TypeDescriptor(keyField.getGetter());
		TypeDescriptor valueType = valueField == null ? TypeDescriptor.valueOf(String.class)
				: new TypeDescriptor(valueField.getGetter());

		Object[] values = ReflectionUtils.values(entityClass);
		List<Pair<K, V>> list = new ArrayList<Pair<K, V>>();
		for (Object entity : values) {
			if (entity == null) {
				continue;
			}

			Object key = keyField == null ? entity : keyField.get(entity);
			Object value = valueField == null ? entity : valueField.get(entity);
			K k = (K) conversionService.convert(key, keyType, keyTypeDescriptor);
			V v = (V) conversionService.convert(value, valueType, valueTypeDescriptor);
			list.add(new Pair<>(k, v));
		}
		return list;
	}

}
