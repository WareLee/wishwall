package bit.work.shop.test;

import org.junit.Test;

/**
 * @param
 * @return
 * @throws Exception
 * @author ware E-mail:
 * @version create time: 20172017年3月10日下午5:35:14
 */
public class InterceptorTest {
	
	@Test
	public void test(){
		String str="login_temp";
		System.out.println(str.indexOf("login"));
		
		String str2="ahfhdhlogin_temp";
		System.out.println(str2.indexOf("login"));
		
		String str3="ahfhdhlgin_temp";
		System.out.println(str3.indexOf("login"));
	}

}
