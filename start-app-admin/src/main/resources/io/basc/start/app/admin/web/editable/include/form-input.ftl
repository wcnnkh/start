<div class="layui-input-inline">
	<input type="text" name="${field.name}"
	   <#if (readonly!false)>readonly="readonly"</#if>
	   <#if ((field.required)!false)>lay-verify="required"</#if> 
	   placeholder="<#if !(readonly!false)>请输入</#if>${field.describe}" autocomplete="off" value="${(info[field.name])!''}"
	   class="layui-input"/>
</div>