<div class="layui-input-block">
	<textarea style="width:100%" rows="5" name="${field.name}" <#if (readonly!false)>readonly="readonly"</#if> <#if (field.required)!false>lay-verify="required"</#if> placeholder="请输入${field.describe}">${(info[field.name])!''}</textarea>
</div>