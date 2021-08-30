<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh">
<head>
	<#include "include/head.ftl">
</head>
<body class="layui-anim layui-anim-up">
<div class="x-nav">
	<a class="layui-btn layui-btn-small"
	   style="line-height: 1.6em; margin-top: 3px; float: right"
	   href="javascript:location.replace(location.href);" title="刷新"> <i
			class="layui-icon" style="line-height: 30px">ဂ</i></a>
</div>

<div class="x-body">
	<button class="layui-btn" onclick="x_admin_show('添加账号','view')">
		<i class="layui-icon"></i>添加
	</button>
	<table class="layui-table">
		<thead>
		<tr>
			<th>KEY</th>
			<th>VALUE</th>
			<th width="100">操作</th>
		</tr>
		</thead>
		<tbody>
		<#list configMap?keys as key>
			<tr>
				<td>${key }</td>
				<td>${configMap[key]! }</td>
				<td class="td-manage">
					<a title="删除" onclick="deleteConfig(this,'${key}')" href="javascript:;"> <i class="layui-icon">&#xe640;</i></a>
					&nbsp;&nbsp;
					<a title="查看" onclick="x_admin_show('编辑','view?key=${key}')" href="javascript:;"> <i class="layui-icon">&#xe63c;</i></a>
				</td>
			</tr>
		</#list>
		</tbody>
	</table>
</div>
</body>
<script>
	/*用户-删除*/
		function deleteConfig(obj, key) {
			layer.confirm('确认要删除吗？', function(index) {
				//发异步删除数据
				$.ajax({
					"url" : "delete",
					"method" : "POST",
					"dataType" : "json",
					"data" : {
						"key" : key
					},
					success : function(response) {
						if (response.code != 0) {
							layer.alert(response.msg, {
								icon : 5
							});
						} else {
							$(obj).parents("tr").remove();
							layer.msg('删除成功!', {
								icon : 1,
								time : 1000
							});
						}
					},
					error : function() {
						layer.msg("网络或系统错误，请稍后重试", {
							icon : 5
						});
					}
				})
			});
		}
</script>
</html>
