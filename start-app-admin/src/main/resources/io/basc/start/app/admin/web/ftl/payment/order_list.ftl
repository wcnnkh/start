<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh">
<head>
	<#include "/admin/ftl/include/head.ftl">
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
			 模糊条件：
			 <input type="text" name="query"
									   placeholder="请输入订单号或订单名称" value="${query!}" autocomplete="off"
									   class="layui-input">
			订单状态：
			<div class="layui-input-inline">
				<select name="paymentStatus">
					<option value="">全部</optoins>
					<#list paymentStatusConfigs as config>
						<option value="${config}" <#if config.getStatus()==(paymentStatus.getStatus())!-1>selected='selected'</#if>>${config.getDescribe()}</optoin>
					</#list>
				</select>
			</div>
			&nbsp;&nbsp;
			物流状态：
			<div class="layui-input-inline">
				<select name="logisticsStatus">
					<option value="">全部</optoins>
					<#list logisticsStatusConfigs as config>
						<option value="${config}" <#if config.getStatus()==(logisticsStatus.getStatus())!0>selected='selected'</#if>>${config.getDescribe()}</optoin>
					</#list>
				</select>
			</div>
			<button class="layui-btn" lay-submit="" lay-filter="sreach">
				<i class="layui-icon">&#xe615;</i>
			</button>
		</form>
	</div>
	<span class="x-right" style="line-height: 40px">页码[${page!'1' }/${maxPage}]&nbsp;&nbsp;共有数据：${totalCount }
			条</span>
	<table class="layui-table">
		<thead>
		<tr>
			<th>订单号</th>
			<th>订单名称</th>
			<th>实付金额(元)</th>
			<th>支付方式</th>
			<th>创建时间</th>
			<th>订单状态</th>
			<th>物流状态</th>
		</tr>
		</thead>
		<tbody>
		<#list list as order>
			<tr>
				<td>${order.id }</td>
				<td>${order.name }</td>
				<td>${order.priceDescribe }</td>
				<td>${order.getPaymentMethod().getDescribe()}</td>
				<td>${order.ctsDescribe}</td>
				<td>${order.getPaymentStatus().getDescribe()}</td>
				<td>${(order.toLogisticsStatus().getDescribe())!}</td>
			</tr>
		</#list>
		</tbody>
	</table>
	<#include "/admin/ftl/include/pagination.ftl" />
</div>
</body>
</html>



