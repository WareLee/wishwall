package bit.work.shop.utils;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * @param
 * @return
 * @throws Exception
 * @author ware E-mail:
 * @version create time: 20172017年3月11日下午6:08:32
 */
public class WXUtils {
	
	// 验证微信服务器地址的有效性
	public static boolean checkSignature(String token, String signature, String timestamp,
			String nonce) {
		String arr[] = new String[] { token, timestamp, nonce };

		// 进行字典排序
		Arrays.sort(arr);

		// 生成字符串
		StringBuffer content = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}

		// 加密
		String temp = getSha1(content.toString());

		return temp.equals(signature);
	}

	// sha1加密算法

	public static String getSha1(String str) {

		if (null == str || 0 == str.length()) {

			return null;

		}

		char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',

		'a', 'b', 'c', 'd', 'e', 'f' };

		try {

			MessageDigest mdTemp = MessageDigest.getInstance("SHA1");

			mdTemp.update(str.getBytes("UTF-8"));

			byte[] md = mdTemp.digest();

			int j = md.length;

			char[] buf = new char[j * 2];

			int k = 0;

			for (int i = 0; i < j; i++) {

				byte byte0 = md[i];

				buf[k++] = hexDigits[byte0 >>> 4 & 0xf];

				buf[k++] = hexDigits[byte0 & 0xf];

			}

			return new String(buf);

		} catch (Exception e) {

			return null;

		}

	}

}
