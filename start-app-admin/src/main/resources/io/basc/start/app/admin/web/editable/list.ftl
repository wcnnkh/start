<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh">
<head>
	<#include "../ftl/include/head.ftl">
</head>
<body class="layui-anim layui-anim-up">
<div class="x-nav">
	<a class="layui-btn layui-btn-small"
	   style="line-height: 1.6em; margin-top: 3px; float: right"
	   href="javascript:location.replace(location.href);" title="刷新"> <i
			class="layui-icon" style="line-height: 30px">ဂ</i></a>
</div>

<div class="x-body">
	<div class="layui-row">
		<form class="layui-form layui-col-md12 x-so">
			<#list fields as field>
				<#assign info=query>
				${field.describe}:
				<div class="layui-input-inline">
					<#if field.type = "SELECT" && (field.options)??>
						<#include "include/form-select.ftl">
					<#else>
						<#include "include/form-input.ftl">
					</#if>
				</div>
				&nbsp;&nbsp
			</#list>
			<button class="layui-btn" lay-submit="" lay-filter="sreach">
				<i class="layui-icon">&#xe615;</i>
			</button>
		</form>
	</div>
	<button class="layui-btn" onclick="x_admin_show('添加${name}','add')">
		<i class="layui-icon"></i>添加
	</button>
	<span class="x-right" style="line-height: 40px">页码[${page!1 }/${maxPage!1 }]&nbsp;&nbsp;共有数据：${totalCount!0 }条</span>
	<table class="layui-table">
		<thead>
			<tr>
				<#list fields as field>
					<th>${field.describe}</th>
				</#list>
				<th width="50">操作</th>
			</tr>
		</thead>
		<tbody>
		<#if list??>
			<#list list as item>
				<tr>
					<#list fields as field>
						<#assign info=item>
						<#assign readonly=true>
						<#assign value=ToString((item[field.name])!'')>
						<td fieldName="${field.name}" fieldValue="${value}" width="100">
							<#if field.type == "SELECT">
								<#include "../ftl/include/form-select.ftl">
							<#else>
								${value}
							</#if>
						</td>
					</#list>
					<td class="td-manage">
						<a title="删除" onclick="deleteInfo(this)" href="javascript:;"> <i class="layui-icon">&#xe640;</i></a>
					&nbsp;&nbsp;
					<a title="查看/修改"
											 onclick="x_admin_show('查看/修改','info?__seize_a_seat=client<#list fields as f><#if f.primaryKey>&${f.name}=${item[f.name]}</#if></#list>')"
											 href="javascript:;"> <i class="layui-icon">&#xe63c;</i>
					</a>
					</td>
				</tr>
			</#list>
		</#if>
		</tbody>
	</table>
	<#include "../ftl/include/pagination.ftl" />
</div>
</body>
<script>
	function deleteInfo(obj) {
			layer.confirm('确认要删除吗？', function(index) {
				var requestData = {};
				$(obj).parents("tr").find("td[fieldName]").each(function(index){
					requestData[$(this).attr("fieldName")] = $(this).attr("fieldValue");
				})
				
				//发异步删除数据
				$.ajax({
					"url" : "delete",
					"method" : "POST",
					"dataType" : "json",
					"data" : requestData,
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
