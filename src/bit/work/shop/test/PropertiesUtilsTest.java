package bit.work.shop.test;

import org.junit.Test;

import bit.work.shop.utils.PropertiesUtils;

/**
 * @param
 * @return
 * @throws Exception
 * @author ware E-mail:
 * @version create time: 20172017年3月9日下午5:13:34
 */
public class PropertiesUtilsTest {
	@Test
	public void test(){
		System.out.println(PropertiesUtils.getKeyValue("FORODEURL"));
	}

}
