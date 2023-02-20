<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<#include "include/head.ftl">
</head>
<body class="login-bg">
    
    <div class="login layui-anim layui-anim-up">
        <div class="message">${adminWebsiteName!}</div>
        <div id="darkbannerwrap"></div>
        
        <form method="post" class="layui-form" >
            <input name="username" placeholder="账号"  type="text" lay-verify="required" class="layui-input" >
            <hr class="hr15">
            <input name="password" lay-verify="required" placeholder="密码"  type="password" class="layui-input">
            <hr class="hr15">
            <input value="登录" lay-submit lay-filter="login" style="width:100%;" type="submit">
            <hr class="hr20" >
        </form>
    </div>

    <script>
        $(function  () {
            layui.use('form', function(){
              var form = layui.form;
              // layer.msg('玩命卖萌中', function(){
              //   //关闭后的操作
              //   });
              //监听提交
              form.on('submit(login)', function(data){
            	$.ajax({
            		"url": "login",
            		"method":"POST",
            		"dataType":"json",
            		"data":data.field,
            		success:function(response){
            			if(response.code != 0){
            				layer.msg(response.msg, {icon: 5});
            				return ;
            			}
            			window.location.href = "";
            		},
            		error:function(){
            			layer.msg("网络或系统错误，请稍后重试", {icon: 5});
            		}
            	})
                return false;
              });
            });
        })
    </script>
</body>
</html>