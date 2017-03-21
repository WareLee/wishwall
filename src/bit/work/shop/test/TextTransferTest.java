package bit.work.shop.test;

import java.io.IOException;

import org.junit.Test;

import bit.work.shop.domain.Userinfo;
import bit.work.shop.utils.TextTransfer;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * @param
 * @return
 * @throws Exception
 * @author ware E-mail:
 * @version create time: 20172017年3月9日下午5:31:25
 */
public class TextTransferTest {
	@Test
	public void jsonStr2PojoTest(){
		String jsonStr="{\"nickname\":\"ware\",\"headimgurl\":\"http://wx.qlogo.cn/mm\",\"privilege\":[\"hahha\",\"dnaggng\"]}";
   
		Userinfo pojo = (Userinfo)TextTransfer.jsonStr2Pojo(jsonStr, Userinfo.class);
		System.out.println(pojo.getHeadimgurl());
		System.out.println(pojo.getNickname());
		
	}
	
	@Test
	public void obj2jsonTest(){
		Integer rows=9;
		
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setExcludes(null);
		JSONArray jsonArray=JSONArray.fromObject(rows, jsonConfig);
		System.out.println(jsonArray);
		
//		JsonConfig jsonConfig=new JsonConfig();
//		jsonConfig.setExcludes(null);
//		JSONObject jsonObject=JSONObject.fromObject(rows, jsonConfig);
		
//		System.out.println(jsonObject);
	} 

}
