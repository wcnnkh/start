<div class="page">
	<div>
		<a class="prev"
		   href="${_request.contextPath}${_request.path}?page=1<#list _request.parameterMap?keys as key>
				<#list _request.parameterMap[key] as value>
					&${key}=${value}
				</#list>
			</#list>">首页</a>
		<a class="prev"
		   href="${_request.contextPath}${_request.path}?page=${(page?? && page > 1)?then((page-1), 1) }<#list _request.parameterMap?keys as key>
				<#list _request.parameterMap[key] as value>
					&${key}=${value}
				</#list>
			</#list>">上一页</a>
		<a class="next"
		   href="${_request.contextPath}${_request.path}?page=${(page?? && page >= maxPage)?then(page + 1, maxPage)}<#list _request.parameterMap?keys as key>
				<#list _request.parameterMap[key] as value>
					&${key}=${value}
				</#list>
			</#list>">下一页</a>
		<a class="next"
		   href="${_request.contextPath}${_request.path}?page=${maxPage }<#list _request.parameterMap?keys as key>
				<#list _request.parameterMap[key] as value>
					&${key}=${value}
				</#list>
			</#list>">尾页</a>
	</div>
</div>