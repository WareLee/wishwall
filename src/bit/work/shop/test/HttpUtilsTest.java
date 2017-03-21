package bit.work.shop.test;

import java.util.HashMap;
import java.util.Map;


import org.junit.Test;

import bit.work.shop.domain.Userinfo;
import bit.work.shop.utils.HttpKits;
import bit.work.shop.utils.PropertiesUtils;

/**
 * @param
 * @return
 * @throws Exception
 * @author ware E-mail:
 * @version create time: 20172017年3月9日下午4:28:07
 */
public class HttpUtilsTest {
	@Test
	public void wxCodeTest(){
		// 获取code
		String forcode=PropertiesUtils.getKeyValue("FORODEURL");
		forcode=forcode.replace("APPID", PropertiesUtils.getKeyValue("APPID"));
		forcode=forcode.replace("REDIRECT_URI", PropertiesUtils.getKeyValue("REDIRECTURI"));
		forcode=forcode.replace("SCOPE", "snsapi_userinfo");
		forcode=forcode.replace("STATE", "123");
		System.out.println(forcode);
//		HttpKits.toGet(forcode, null);
	}
	
	@Test
	public void doGetTest2(){
		String path="http://api.douban.com/people/aka/notes?start-index=1&max-results=1";
//		HttpKits.toGet(path,null);
		//{"tags":["热门","最新","经典","可播放","豆瓣高分","冷门佳片","华语","欧美","韩国","日本","动作","喜剧","爱情","科幻","悬疑","恐怖","治愈"]}
		// 成功
	}
	public void doPostTest2(){
		String path="http://api.douban.com/people/aka/notes";
//		HttpKits.toPost(path, null, params)(path, new String("start-index=1&max-results=1"));
	}
	@Test
	public void doGetTest(){
		String path="https://movie.douban.com/j/search_tags?type=movie";
//		HttpKits.toGet(path,Userinfo.class);
		//{"tags":["热门","最新","经典","可播放","豆瓣高分","冷门佳片","华语","欧美","韩国","日本","动作","喜剧","爱情","科幻","悬疑","恐怖","治愈"]}
		// 成功
	}
	public void doPostTest(){
		String path="http://api.douban.com/people/aka/notes";
		Map<String,String> map=new HashMap<>();
		map.put("start-index","1");
		map.put("max-results","1");
//		HttpUtils.doPost(path, map);
	}

}
