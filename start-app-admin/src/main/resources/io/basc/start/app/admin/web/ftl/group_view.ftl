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
					class="x-red">*</span>分组名
				</label>
				<div class="layui-input-inline">
					<input type="text" id="name" name="name" required="" value="${(group.name)!}"
						lay-verify="required" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label for="status" class="layui-form-label"> <span
					class="x-red">*</span>状态
				</label>
				<div class="layui-input-inline">
					<select name="disable">
						<option value="true" ${((group.disable)!false)?then("selected='selected'","")}>禁用</option>
						<option value="false" ${((group.disable)!false)?then("","selected='selected'")}>启用</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label for="name" class="layui-form-label"> <span
					class="x-red">*</span>权限
				</label>
				<div class="layui-input-block">
					<ul id="actionList" class="ztree"></ul>
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
		$(function() {
			var setting = {
				check : {
					enable : true
				},
				data : {
					simpleData : {
						enable : true
					}
				}
			};

			$.ajax({
				"url" : "group_action_list?groupId=${(group.id)!}&parentId=${parentId}",
				"method" : "GET",
				"dataType" : "json",
				success : function(response) {
					if (response.code != 0) {
						layer.alert(response.msg, {
							icon : 5
						});
					} else {
						$.fn.zTree.init($("#actionList"), setting,
								response.data);
					}
				},
				error : function() {
					layer.msg("网络或系统错误，请稍后重试", {
						icon : 5
					});
				}
			})
		})

		layui.use([ 'form', 'layer' ], function() {
			$ = layui.jquery;
			var form = layui.form, layer = layui.layer;

			//监听提交
			form.on('submit(add)', function(data) {
				var treeObj = $.fn.zTree.getZTreeObj("actionList");
				var nodes = treeObj.getCheckedNodes(true);
				if(nodes.length == 0){
					layer.alert("请选择权限", {
						icon : 5
					});
					return false;
				}
	
				var ids = new Array();
				for(var i=0; i<nodes.length; i++){
					ids.push(nodes[i].id);
				}
				
				var requestData = data.field;
				requestData.ids = ids.join(",");
				requestData.parentId = ${parentId};
				requestData.id = "${(group.id)!}";
				
				$.ajax({
					"url" : "group_add_or_update",
					"method" : "POST",
					"dataType" : "json",
					"data" : requestData,
					success : function(response) {
						if (response.code != 0) {
							layer.alert(response.msg, {
								icon : 5
							});
						} else {
							layer.alert("操作成功", {
								icon : 6
							}, function() {
								parent.location.reload();
							});
						}
					},
					error : function() {
						layer.msg("网络或系统错误，请稍后重试", {
							icon : 5
						});
					}
				})
				return false;
			});

		});
	</script>
</body>
</html>