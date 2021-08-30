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
	<div class="layui-row">
		<form class="layui-form layui-col-md12 x-so">
			分组：
			<div class="layui-input-inline">
				<select name="groupId">
					<option value=""
					${((groupId!0) == 0)?then('selected="selected"',"") }>全部</option>
					<#list groupList as group>
						<option value="${group.id }"
							${((groupId!0)== group.id)?then('selected="selected"','')}>${group.name }</option>
					</#list>
				</select>
			</div>
			&nbsp;&nbsp; 账号或名称： <input type="text" name="search"
									   placeholder="请输入账号或名称" value="${search!}" autocomplete="off"
									   class="layui-input">
			<button class="layui-btn" lay-submit="" lay-filter="sreach">
				<i class="layui-icon">&#xe615;</i>
			</button>
		</form>
	</div>
	<button class="layui-btn" onclick="x_admin_show('添加账号','admin_view')">
		<i class="layui-icon"></i>添加
	</button>
	<span class="x-right" style="line-height: 40px">页码[${page }/${maxPage }]&nbsp;&nbsp;共有数据：${totalCount }
			条</span>
	<table class="layui-table">
		<thead>
		<tr>
			<th width="50">UID</th>
			<th>手机号</th>
			<th>账号</th>
			<th>名称</th>
			<th width="140">最后一次登录时间</th>
			<th width="140">状态</th>
			<th width="140">所属分组</th>
			<th width="100">操作</th>
		</tr>
		</thead>
		<tbody>
		<#list adminList as admin>
			<tr>
				<td>${admin.uid }</td>
				<td>${admin.phone! }</td>
				<td>${admin.username }</td>
				<td>${admin.nickname }</td>
				<td>${(admin.lastLoginTime == 0)?then("未知", admin.lastLoginTimeDescribe) }</td>
				<td>${(admin.disable)?then("禁用", "可用")}</td>
				<td>${admin.groupName }</td>
				<td class="td-manage"><a title="查看"
										 onclick="x_admin_show('编辑','admin_view?toUid=${admin.uid}')"
										 href="javascript:;"> <i class="layui-icon">&#xe63c;</i>
				</a>
				</td>
			</tr>
		</#list>
		</tbody>
	</table>
	<div class="page">
		<div>
			<a class="prev"
			   href="admin_list?page=1&search=${search!}&groupId=${groupId!}">首页</a>
			<a class="prev"
			   href="admin_list?page=${(page <=1)?then(1,(page-1)) }&search=${search!}&groupId=${groupId!}">上一页</a>
			<a class="next"
			   href="admin_list?page=${(page >= maxPage)?then(maxPage,page + 1)}&search=${search!}&groupId=${groupId!}">下一页</a>
			<a class="next"
			   href="admin_list?page=${maxPage }&search=${search!}&groupId=${groupId!}">尾页</a>
		</div>
	</div>

</div>
</body>
</html>
