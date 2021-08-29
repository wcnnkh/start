<div class="layui-input-inline">
	<#assign value=ToString((info[field.name])!'')>
	<select name="${field.name}" <#if (readonly!false)>disabled="disabled"</#if>>
		<option value="">未选择</option>
		<#if (field.options)??>
			<#list field.options as option>
				<option value="${option.key}" <#if (info[field.name])?? && (value == option.key)>selected="selected"</#if>>${option.value}</option>
			</#list>
		</#if>
	</select>
</div>