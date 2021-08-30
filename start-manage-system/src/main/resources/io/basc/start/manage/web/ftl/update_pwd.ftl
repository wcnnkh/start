<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <#include "include/head.ftl">
  </head>
  
  <body>
    <div class="x-body">
        <form action="" method="post" class="layui-form layui-form-pane">
                <div class="layui-form-item">
                    <label for="oldPwd" class="layui-form-label">
                        <span class="x-red">*</span>旧密码
                    </label>
                    <div class="layui-input-inline">
                        <input type="password" id="oldPwd" name="oldPwd" required="" lay-verify="oldPwd"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="pwd1" class="layui-form-label">
                        <span class="x-red">*</span>新密码
                    </label>
                    <div class="layui-input-inline">
                        <input type="password" id="pwd1" name="pwd1" required="" lay-verify="pwd1"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
                 <div class="layui-form-item">
                    <label for="pwd2" class="layui-form-label">
                        <span class="x-red">*</span>再次输入新密码
                    </label>
                    <div class="layui-input-inline">
                        <input type="password" id="pwd2" name="pwd2" required="" lay-verify="pwd2"
                        autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                <button class="layui-btn" lay-submit="" lay-filter="update">确认修改</button>
              </div>
            </form>
    </div>
    <script>
        layui.use(['form','layer'], function(){
            $ = layui.jquery;
          var form = layui.form
          ,layer = layui.layer;
        
          //自定义验证规则
          form.verify({
        	oldPwd: [/(.+){6,16}$/, '密码必须6到16位']
            ,pwd1: [/(.+){6,16}$/, '密码必须6到16位']
            ,pwd2: function(value){
                if($('#pwd1').val()!=$('#pwd2').val()){
                    return '两次密码不一致';
                }
            }
          });

          //监听提交
          form.on('submit(update)', function(data){
            console.log(data);
            $.ajax({
        		"url": "update_pwd",
        		"method":"POST",
        		"dataType":"json",
        		"data":{
        			"oldPwd":$("#oldPwd").val(),
        			"newPwd":$("#pwd1").val()
        		},
        		success:function(response){
        			if(response.code != 0){
        				layer.msg(response.msg, {icon: 5});
        				return ;
        			}
        			layer.msg("修改成功", {icon: 6}, function(){
        				// 获得frame索引
                        var index = parent.layer.getFrameIndex(window.name);
                        //关闭当前frame
                        parent.layer.close(index);
        			});
        		},
        		error:function(){
        			layer.msg("网络或系统错误，请稍后重试");
        		}
        	})
            return false;
          });
        });
    </script>
  </body>

</html>