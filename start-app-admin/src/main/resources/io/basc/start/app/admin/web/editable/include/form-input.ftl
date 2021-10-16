<div class="layui-input-inline">
	<input type="text" name="${field.name}"
	   <#if (readonly!false)>readonly="readonly"</#if>
	   <#if (field.required)!false>lay-verify="required"</#if> 
	   placeholder="请输入${field.describe}" autocomplete="off" value="${(info[field.name])!''}"
	   class="layui-input"/>
</div>