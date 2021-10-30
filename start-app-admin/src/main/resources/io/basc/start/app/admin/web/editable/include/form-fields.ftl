<#list fields as field>
	<#assign readonly=(field.readonly || (postUrl == "update" && field.primaryKey))>
	<#if postUrl == "add" && (field.autoFill || readonly)>
	<#else>
		<div class="layui-form-item">
			<label for="name" class="layui-form-label">
				<#if (field.required)!false>
					<span class="x-red">*</span>
				</#if>
				${field.describe}
			</label>
				<#if field.type == "SELECT">
					<#include "form-select.ftl">
				<#elseif field.type == "IMAGE">
					<#if (info[field.name])?? && (info[field.name]) != ''>
						<#list info[field.name]?split(",") as imageUrl>
							<#include "form-image.ftl">
						</#list>
						<#if field.multiple>
							<#include "form-image.ftl">
						</#if>
					<#else>
						 <#include "form-image.ftl">
					</#if>
				<#elseif field.type == "TEXTAREA">
					<#include "form-textarea.ftl">
				<#else>
					<#include "form-input.ftl">
				</#if>
		</div>
	</#if>
</#list>
<script>
	function uploadFormImages(successCallback){
		uploadImagesByPolicy("${_request.contextPath}/admin/editable/generate_upload_policy", $("input.upload-input"), successCallback);
	}
</script>