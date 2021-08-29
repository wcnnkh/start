package io.basc.app.admin.web;

import io.basc.app.user.security.SecurityProperties;
import io.basc.framework.beans.annotation.Autowired;
import io.basc.framework.context.result.Result;
import io.basc.framework.context.result.ResultFactory;
import io.basc.framework.core.utils.StringUtils;
import io.basc.framework.http.HttpMethod;
import io.basc.framework.mvc.annotation.ActionAuthority;
import io.basc.framework.mvc.annotation.Controller;
import io.basc.framework.value.Value;
import io.basc.framework.web.model.Page;
import io.basc.framework.zookeeper.ZookeeperCloudPropertyFactory;

import java.util.HashMap;
import java.util.Map;

@ActionAuthority(value = "Zookeeper配置中心", menu = true)
@Controller(value= SecurityProperties.ADMIN_CONTROLLER + "/zookeeper/config", methods={HttpMethod.GET, HttpMethod.POST})
public class ZookeeperConfigController {
	private final ZookeeperCloudPropertyFactory cloudPropertyFactory;
	@Autowired
	private ResultFactory resultFactory;
	
	public ZookeeperConfigController(ZookeeperCloudPropertyFactory cloudPropertyFactory){
		this.cloudPropertyFactory = cloudPropertyFactory;
	}
	
	@ActionAuthority(menu=true, value="配置列表")
	@Controller(value="list")
	public Page list(){
		Page page = new Page("/admin/ftl/config_list.ftl");
		Map<String, String> configMap = new HashMap<String, String>();
		for(String key : cloudPropertyFactory){
			Value value = cloudPropertyFactory.getValue(key);
			if(value == null){
				continue;
			}
			
			String text = value.getAsString();
			if(text == null){
				continue;
			}
			
			text = text.replaceAll("\\n", "<br/>");
			configMap.put(key, text);
		}
		page.put("configMap", configMap);
		return page;
	}
	
	@ActionAuthority(value="添加/修改配置(界面)")
	@Controller(value="view")
	public Page view(String key){
		Page page = new Page("/admin/ftl/config_view.ftl");
		page.put("key", key);
		page.put("value", cloudPropertyFactory.getValue(key).getAsString());
		return page;
	}
	
	@ActionAuthority(value="添加/修改配置操作")
	@Controller(value="save_or_update")
	public Result saveOrUpdate(String key, String value){
		if(StringUtils.isEmpty(key, value)){
			return resultFactory.parameterError();
		}
		
		cloudPropertyFactory.put(key, value);
		return resultFactory.success();
	}
	
	@ActionAuthority(value="删除配置操作")
	@Controller(value="delete")
	public Result delete(String key){
		if(StringUtils.isEmpty(key)){
			return resultFactory.parameterError();
		}
		
		cloudPropertyFactory.remove(key);
		return resultFactory.success();
	}
}
