<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<#include "include/head.ftl">
</head>
<body>
<div class="x-body">
	<form class="layui-form">
		<div class="layui-form-item">
			<label for="name" class="layui-form-label"> <span
					class="x-red">*</span>KEY
			</label>
			<div class="layui-input-inline">
				<input type="text" name="key" required="" value="${key! }"  <#if key??>readonly="readonly"</#if>
					   lay-verify="required" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label for="name" class="layui-form-label">VALUE
			</label>
			<div class="layui-input-block">
				<textarea name="value" rows="10" style="width:100%">${value!}</textarea>
			</div>
		</div>
		<div class="layui-form-item">
			<label for="L_repass" class="layui-form-label"> </label>
			<button class="layui-btn" lay-filter="add" lay-submit="">
				保存</button>
		</div>
	</form>
</div>
<script>
	layui.use(['form','layer'], function(){
		$ = layui.jquery;
		var form = layui.form
				,layer = layui.layer;

		//监听提交
		form.on('submit(add)', function(data){
			var requestData = data.field;
			$.ajax({
				"url": "save_or_update",
				"method":"POST",
				"dataType":"json",
				"data": requestData,
				success:function(response){
					if(response.code != 0){
						layer.alert(response.msg, {icon: 5});
					}else{
						layer.alert("操作成功", {icon: 6},function () {
							parent.location.reload();
						});
					}
				},
				error:function(){
					layer.msg("网络或系统错误，请稍后重试", {icon: 5});
				}
			})
			return false;
		});


	});
</script>
</body>
</html>