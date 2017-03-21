package bit.work.shop.test;

import org.junit.Test;

import bit.work.shop.utils.XssShieldUtil;

/**
 * @param
 * @return
 * @throws Exception
 * @author ware E-mail:
 * @version create time: 20172017年3月11日上午11:08:20
 */
public class XssShieldUtilTest {
	
	@Test
	public void test(){
		
        String value = null;
        value = XssShieldUtil.stripXss("<script language=text/javascript>alert(document.cookie);</script>");
        System.out.println("type-1: '" + value + "'");

        value = XssShieldUtil.stripXss("<script src='' onerror='alert(document.cookie)'></script>");
        System.out.println("type-2: '" + value + "'");

        value = XssShieldUtil.stripXss("</script>");
        System.out.println("type-3: '" + value + "'");

        value = XssShieldUtil.stripXss(" eval(abc);");
        System.out.println("type-4: '" + value + "'");

        value = XssShieldUtil.stripXss(" expression(abc);");
        System.out.println("type-5: '" + value + "'");

        value = XssShieldUtil.stripXss("<img src='' onerror='alert(document.cookie);'></img>");
        System.out.println("type-6: '" + value + "'");

        value = XssShieldUtil.stripXss("<img src='' onerror='alert(document.cookie);'/>");
        System.out.println("type-7: '" + value + "'");

        value = XssShieldUtil.stripXss("<img src='' onerror='alert(document.cookie);'>");
        System.out.println("type-8: '" + value + "'");

        value = XssShieldUtil.stripXss("<script language=text/javascript>alert(document.cookie);");
        System.out.println("type-9: '" + value + "'");

        value = XssShieldUtil.stripXss("<script>window.location='url'");
        System.out.println("type-10: '" + value + "'");

        value = XssShieldUtil.stripXss(" onload='alert(\"abc\");");
        System.out.println("type-11: '" + value + "'");

        value = XssShieldUtil.stripXss("<img src=x<!--'<\"-->>");
        System.out.println("type-12: '" + value + "'");

        value = XssShieldUtil.stripXss("<=img onstop=");
        System.out.println("type-13: '" + value + "'");


    }
}
