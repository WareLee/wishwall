package bit.work.shop.action.Base;

import java.io.IOException;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * ActionSupport 提供了:
 * 			1.addActionError(),添加错误信息到页面
 * 			2.getText(),获取国际化文件
 * ModelDriven 
 * 			1.所有的子类都有一个model对象(这样通过属性驱动就可以将请求参数匹配到model中,注:ware不完全确定是否是属性驱动的方式)
 * 			2.将一个model添加到值栈中valueStack
 * 通用Action实现
 * @author zhaoqx
 *
 * @param <T>
 */
public class BaseAction extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	// 将对象转为json并返回
		public void witerObject2json(Object object,String excludes[]) throws IOException{
			
			JsonConfig jsonConfig=new JsonConfig();
			jsonConfig.setExcludes(excludes);
			JSONObject jsonObject=JSONObject.fromObject(object, jsonConfig);
			
			ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
			ServletActionContext.getResponse().getWriter().write(jsonObject.toString());
		}
		
		// 将list转为json并返回
		public void witerList2json(Object object,String excludes[]) throws IOException{
			
			JsonConfig jsonConfig=new JsonConfig();
			jsonConfig.setExcludes(excludes);
			JSONArray jsonArray=JSONArray.fromObject(object, jsonConfig);
			
			ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
			ServletActionContext.getResponse().getWriter().write(jsonArray.toString());
		}
	
}
