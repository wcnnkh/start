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
					class="x-red">*</span>账号
			</label>
			<div class="layui-input-inline">
				<input type="text" name="username" required="" value="${(admin.username)! }"
					   lay-verify="required" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label for="name" class="layui-form-label">密码
			</label>
			<div class="layui-input-inline">
				<input type="text" name="password"
					   autocomplete="off" class="layui-input">
			</div>
			<#if admin??>
			<div class="layui-form-mid layui-word-aux">(如无需修改请不要埴写)</div>
			</#if>
		</div>

		<div class="layui-form-item">
			<label for="name" class="layui-form-label"> <span
					class="x-red">*</span>名称
			</label>
			<div class="layui-input-inline">
				<input type="text" name="nickname" required="" value="${(admin.nickname)! }"
					   lay-verify="required" autocomplete="off" class="layui-input">
			</div>
		</div>

		<div class="layui-form-item">
			<label for="name" class="layui-form-label"> <span
					class="x-red">*</span>分组
			</label>
			<div class="layui-input-inline">
				<select name="groupId">
					<#list groupList as group>
						<option value="${group.id }" ${(group.id==((admin.permissionGroupId)!0))?then("selected='selected'","") }>${group.name }</option>
					</#list>
				</select>
			</div>
		</div>

		<div class="layui-form-item">
			<label for="status" class="layui-form-label"> <span
					class="x-red">*</span>状态
			</label>
			<div class="layui-input-inline">
				<select name="disable">
					<option value="false" ${((admin.disable)!false)?then("", "selected='selected'") }>启用</option>
					<option value="true" ${((admin.disable)!false)?then("selected='selected'","") }>禁用</option>
				</select>
			</div>
		</div>

		<div class="layui-form-item">
			<label for="L_repass" class="layui-form-label"> </label>
			<button class="layui-btn" lay-filter="add" lay-submit="">保存</button>
		</div>
	</form>
</div>
<script>
	layui.use(['form','layer'], function(){
		$ = layui.jquery;
		var form = layui.form
				,layer = layui.layer;

		form.on('submit(add)', function(data){
			var requestData = data.field;
			requestData.toUid = "${(admin.uid)!}";
			$.ajax({
				"url": "admin_create_or_update",
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