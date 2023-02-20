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
		<xblock>
		<button class="layui-btn"
			onclick="x_admin_show('添加分组','group_view?parentId=${parentId}')">
			<i class="layui-icon"></i>添加
		</button>
		<#if !isTop && parentGroup??> 
		<a class="layui-btn"
			href="group_list?parentId=${parentGroup.parentId }"> 上一级 </a> 
			</#if> 
		</xblock>

		<table class="layui-table">
			<thead>
				<tr>
					<th>ID</th>
					<th>名称</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<#list groupList as group>
				<tr>
					<td>${group.id }</td>
					<td><a href="group_list?parentId=${group.id }">${group.name
							}(点击进入下一级分组)</a></td>
					<td>${(group.disable)?then("禁用", "启用") }</td>
					<td><a title="查看/修改"
						onclick="x_admin_show('编辑','group_view?id=${group.id}&parentId=${group.parentId}')"
						href="javascript:;"> <i class="layui-icon">&#xe63c;</i>
					</a></td>
				</tr>
				</#list>
			</tbody>
		</table>
	</div>
</body>
</html>