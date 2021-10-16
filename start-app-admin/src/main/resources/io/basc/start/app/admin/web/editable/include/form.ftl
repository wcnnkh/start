<form class="layui-form">
	<#include "/editable/include/form-fields.ftl">
	<div class="layui-form-item">
		<label for="L_repass" class="layui-form-label"> </label>
		<button class="layui-btn" lay-filter="add" lay-submit="">保存</button>
	</div>
</form>
<script>
	layui.use(['form','layer'], function(){
		$ = layui.jquery;
		var form = layui.form
				,layer = layui.layer;

		form.on('submit(add)', function(data){
			uploadFormImages(function(images){
				var requestData = data.field;
				for(var key in images){
					requestData[key] = images[key].join(",");
				}
				$.ajax({
					"url": "${postUrl}",
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
			})
			return false;
		});
	});
</script>